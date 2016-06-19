/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package net.geoprism.gis.sld;

import java.awt.Color;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.geoprism.dashboard.DashboardStyle;
import net.geoprism.dashboard.DashboardThematicStyle;
import net.geoprism.dashboard.condition.wrapper.And;
import net.geoprism.dashboard.condition.wrapper.Category;
import net.geoprism.dashboard.condition.wrapper.Condition;
import net.geoprism.dashboard.condition.wrapper.Equal;
import net.geoprism.dashboard.condition.wrapper.Gradient;
import net.geoprism.dashboard.condition.wrapper.GreaterThan;
import net.geoprism.dashboard.condition.wrapper.GreaterThanOrEqual;
import net.geoprism.dashboard.condition.wrapper.IsBetween;
import net.geoprism.dashboard.condition.wrapper.IsLike;
import net.geoprism.dashboard.condition.wrapper.IsNull;
import net.geoprism.dashboard.condition.wrapper.LessThan;
import net.geoprism.dashboard.condition.wrapper.LessThanOrEqual;
import net.geoprism.dashboard.condition.wrapper.NotEqual;
import net.geoprism.dashboard.condition.wrapper.Or;
import net.geoprism.dashboard.condition.wrapper.Primitive;
import net.geoprism.dashboard.layer.CategoryIcon;
import net.geoprism.gis.geoserver.GeoserverProperties;
import net.geoprism.gis.wrapper.AttributeType;
import net.geoprism.gis.wrapper.FeatureStrategy;
import net.geoprism.gis.wrapper.FeatureType;
import net.geoprism.gis.wrapper.Layer;
import net.geoprism.gis.wrapper.Map;
import net.geoprism.gis.wrapper.MapVisitor;
import net.geoprism.gis.wrapper.ReferenceLayer;
import net.geoprism.gis.wrapper.Style;
import net.geoprism.gis.wrapper.ThematicLayer;
import net.geoprism.gis.wrapper.ThematicStyle;
import net.geoprism.localization.LocalizationFacade;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.system.gis.geo.GeoEntity;
import com.runwaysdk.transport.conversion.ConversionException;
import com.runwaysdk.vault.VaultDAO;
import com.runwaysdk.vault.VaultDAOIF;
import com.runwaysdk.vault.VaultFileDAO;
import com.runwaysdk.vault.VaultFileDAOIF;

/**
 * Traverses an object graph of map Component objects and creates an SLD document.
 */
public class SLDMapVisitor implements MapVisitor, com.runwaysdk.generation.loader.Reloadable
{
  private static class Provider implements com.runwaysdk.generation.loader.Reloadable
  {
    protected SLDMapVisitor visitor;

    private Provider(SLDMapVisitor visitor)
    {
      this.visitor = visitor;
    }

    protected NodeBuilder node(String node)
    {
      return this.visitor.node(node);
    }

    protected NodeBuilder node(String ns, String node)
    {
      return this.visitor.node(ns, node);
    }

    protected NodeBuilder css(String name, Object value)
    {
      return this.visitor.css(name, value);
    }

    protected NodeBuilder css(String name)
    {
      return this.visitor.css(name);
    }
  }

  private static abstract class Symbolizer extends Provider implements com.runwaysdk.generation.loader.Reloadable
  {
    protected Layer layer;

    protected Style style;

    private Symbolizer(SLDMapVisitor visitor, Layer layer, Style style)
    {
      super(visitor);

      this.style = style;
      this.layer = layer;
    }

    protected abstract String getSymbolizerName();

    protected Node getSLD()
    {
      return node(this.getSymbolizerName()).build();
    }

    protected Node getPropertyValueNode(ThematicLayer tLayer)
    {
      AttributeType type = tLayer.getAttributeType();

      if (type.equals(AttributeType.NUMBER))
      {
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getNumberInstance(LocalizationFacade.getLocale());
        String pattern = decimalFormat.toPattern();
        DecimalFormatSymbols symbols = decimalFormat.getDecimalFormatSymbols();

        Node format = node(OGC, "Literal").text(pattern).build();
        Node property = this.getBasicPropertyValueNode(tLayer);
        Node minus = node(OGC, "Literal").text(symbols.getMinusSign()).build();
        Node decimal = node(OGC, "Literal").text(symbols.getDecimalSeparator()).build();
        Node separator = node(OGC, "Literal").text(symbols.getGroupingSeparator()).build();

        Node function = node(OGC, "Function").attr("name", "numberFormat2").build();
        function.appendChild(format);
        function.appendChild(property);
        function.appendChild(minus);
        function.appendChild(decimal);
        function.appendChild(separator);

        return function;
      }
      else if (type.equals(AttributeType.DATE))
      {
        SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, LocalizationFacade.getLocale());
        String pattern = dateFormat.toLocalizedPattern();

        return this.getMomentNode(tLayer, pattern);
      }
      else if (type.equals(AttributeType.DATETIME))
      {
        SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT, LocalizationFacade.getLocale());
        String pattern = dateFormat.toLocalizedPattern();

        return this.getMomentNode(tLayer, pattern);
      }
      else if (type.equals(AttributeType.TIME))
      {
        SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT, LocalizationFacade.getLocale());
        String pattern = dateFormat.toLocalizedPattern();

        return this.getMomentNode(tLayer, pattern);
      }
      else
      {
        return this.getBasicPropertyValueNode(tLayer);
      }
    }

    protected Node getMomentNode(ThematicLayer tLayer, String pattern)
    {
      Node format = node(OGC, "Literal").text(pattern).build();
      Node property = this.getBasicPropertyValueNode(tLayer);

      Node function = node(OGC, "Function").attr("name", "if_then_else").child(node(OGC, "Function").attr("name", "isNull").child(this.getBasicPropertyValueNode(tLayer)), node(OGC, "Literal").text(""), node(OGC, "Function").attr("name", "dateFormat").child(format, property)).build();
      // function.appendChild(format);
      // function.appendChild(property);

      return function;
    }

    protected Node getBasicPropertyValueNode(ThematicLayer tLayer)
    {
      return node(OGC, "PropertyName").text(tLayer.getAttribute().toLowerCase()).build();
    }

    protected void addLabelSymbolizer(Node parent)
    {
      TextSymbolizer text = this.getLabelSymbolizer();
      if (text != null)
      {
        parent.appendChild(text.getSLD());
      }
    }

    protected TextSymbolizer getLabelSymbolizer()
    {
      boolean thematic = layer instanceof ThematicLayer;

      if (thematic && style.getEnableLabel() && style.getEnableValue())
      {
        ThematicLayer tLayer = (ThematicLayer) layer;
        
        Node node = node(OGC, "Function").attr("name", "if_then_else").child(
            node(OGC, "Function").attr("name", "isNull").child(
                node(OGC, "PropertyName").text(tLayer.getAttribute().toLowerCase())).build(),
            node(OGC, "PropertyName").text(GeoEntity.DISPLAYLABEL.toLowerCase()).build(),
            node(OGC, "Function").attr("name", "Concatenate").child(
                node(OGC, "PropertyName").text(GeoEntity.DISPLAYLABEL.toLowerCase()).build(),
                node(OGC, "Literal").text(" (").build(),
                this.getPropertyValueNode(tLayer),
                node(OGC, "Literal").text(")").build()).build()).build();
        
        return new TextSymbolizer(visitor, layer, style, node);
      }
      else if (style.getEnableLabel())
      {
        Node[] nodes = new Node[] { node(OGC, "PropertyName").text(GeoEntity.DISPLAYLABEL.toLowerCase()).build() };

        return new TextSymbolizer(visitor, layer, style, nodes);
      }
      else if (thematic && style.getEnableValue())
      {
        ThematicLayer tLayer = (ThematicLayer) layer;
        
        Node node = node(OGC, "Function").attr("name", "if_then_else").child(
            node(OGC, "Function").attr("name", "isNull").child(
                node(OGC, "PropertyName").text(tLayer.getAttribute().toLowerCase())).build(),
            node(OGC, "Literal").text("").build(),
            node(OGC, "Function").attr("name", "Concatenate").child(
                node(OGC, "Literal").text(" (").build(),
                this.getPropertyValueNode(tLayer),
                node(OGC, "Literal").text(")").build()).build()).build();

        return new TextSymbolizer(visitor, layer, style, node);
      }

      return null;
    }

    protected NumberFormat getRuleNumberFormatter()
    {
      return new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(LocalizationFacade.getLocale()));
    }
    
    protected JSONArray getCategories(String cats)
    {
      try
      {
        JSONObject catsJSON = new JSONObject(cats);
        
        if(catsJSON.has("catLiElems"))
        {
          return catsJSON.getJSONArray("catLiElems");
        }
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException("Can not parse categories json. There may be missing data.", e);
      }
      
      return new JSONArray();
    }
  }

  private static class PointSymbolizer extends Symbolizer implements com.runwaysdk.generation.loader.Reloadable
  {
    private PointSymbolizer(SLDMapVisitor visitor, Layer layer, Style style)
    {
      super(visitor, layer, style);
    }

    @Override
    protected String getSymbolizerName()
    {
      return "FeatureTypeStyle";
    }

    @Override
    protected Node getSLD()
    {
      NumberFormat formatter = this.getRuleNumberFormatter();
      Node root = super.getSLD();

      node("FeatureTypeName").text(style.getName()).build(root);

      if (this.visitor.currentLayer.getFeatureStrategy() == FeatureStrategy.BASICPOINT)
      {

        int radius = style.getBasicPointSize();

        if (style instanceof ThematicStyle)
        {
          ThematicStyle tStyle = (ThematicStyle) style;
          
          if (tStyle.getSecondaryAttributeDAO() != null)
          {
            try
            {
              String attributeName = tStyle.getSecondaryAttributeDAO().definesAttribute().toLowerCase();
  
              JSONArray array = tStyle.getSecondaryAttributeCategoriesAsJSON();
              for (int i = 0; i < array.length(); i++)
              {
                JSONObject category = array.getJSONObject(i);
                String key = category.getString(ThematicStyle.VAL);
                String color = category.getString(ThematicStyle.COLOR);
  
                NodeBuilder[] filterNodes = new NodeBuilder[] { 
                    node(OGC, "PropertyIsEqualTo").child(
                    node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED"), 
                    node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED")), 
                    node(OGC, "And").child(
                        node(OGC, "Not").child(
                            node(OGC, "Or").child(
                                node(OGC, "PropertyIsNull").child(
                                    node(OGC, "PropertyName").text(attributeName)), 
                                    node(OGC, "PropertyIsEqualTo").child(
                                        node(OGC, "Literal").text("NEVER"), 
                                        node(OGC, "Literal").text("TRUE")))), 
                                        node(OGC, "PropertyIsEqualTo").child(
                                            node(OGC, "PropertyName").text(attributeName), 
                                            node(OGC, "Literal").text(key))) 
                                            };
  
                this.createRule(root, filterNodes, color, key, radius);
              }
  
              String fill = this.style.getPointFill();
              NodeBuilder[] filterNodes = this.getElseNode(attributeName, array);
              String label = LocalizationFacade.getFromBundles("Other");
  
              this.createRule(root, filterNodes, fill, label, radius);
            }
            catch (JSONException e)
            {
              throw new ProgrammingErrorException(e);
            }
          }
          else
          {
            String fill = this.style.getPointFill();

            createRule(root, null, fill, null, radius);
          }
        }
        else if (style instanceof DashboardStyle)
        {
          String fill = this.style.getPointFill();

          createRule(root, null, fill, null, radius);
        }
      }
      else if (this.visitor.currentLayer.getFeatureStrategy() == FeatureStrategy.GRADIENTPOINT)
      {
        int numCategories = 5; // 5 is the default
        double categoryLen;
        
        if(this.style instanceof ThematicStyle)
        {
          ThematicStyle tStyle = (ThematicStyle) this.style;
          int radius = tStyle.getGradientPointSize();
          String wkn = tStyle.getGradientPointWellKnownName();

          Double fillOpacity = tStyle.getGradientPointFillOpacity();
          String stroke = tStyle.getGradientPointStroke();
          int width = tStyle.getGradientPointStrokeWidth();
          Double strokeOpacity = tStyle.getGradientPointStrokeOpacity();
          
          // SLD generation
          ThematicLayer tLayer = (ThematicLayer) layer;
  
          // attribute must be lowercase to work with postgres
          String attribute = tLayer.getAttribute().toLowerCase();
  
          HashMap<String, Double> minMaxMap = null;
          if(this.visitor.currentLayer instanceof ThematicLayer)
          {
            ThematicLayer currentTLayer = (ThematicLayer) this.visitor.currentLayer;
            minMaxMap = currentTLayer.getLayerMinMax(attribute);
          }
          double minAttrVal = minMaxMap.get("min");
          double maxAttrVal = minMaxMap.get("max");
  
          if (minAttrVal == maxAttrVal)
          {
            // min/max are the same suggesting there is only one feature (i.e. gradient on a single polygon)
            numCategories = 1;
            categoryLen = 1.0;
          }
          else
          {
            numCategories = tStyle.getNumGradientPointCategories();
            categoryLen = ( maxAttrVal - minAttrVal ) / numCategories;
          }
  
          HashMap<Integer, Color> gradientColors = interpolateColor(numCategories, this.visitor.currentLayer.getFeatureStrategy(), tStyle);
  
          for (int i = 0; i < numCategories; i++)
          {
            double currentCatMin;
            if (numCategories == 1)
            {
              currentCatMin = 0;
            }
            else
            {
              currentCatMin = minAttrVal + ( i * categoryLen );
            }
            double currentCatMax = minAttrVal + ( ( i + 1 ) * categoryLen );
  
            int currentColorPos = i + 1;
            Color currentColor = gradientColors.get(currentColorPos);
            String currentColorHex = String.format("#%02x%02x%02x", currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue());
  
            String currentCatMinDisplay = formatter.format(currentCatMin);
            String currentCatMaxDisplay = formatter.format(currentCatMax);
            
            Node ruleNode = node("Rule").child(
                node("Name").text(currentCatMinDisplay + " - " + currentCatMaxDisplay),
                node("Title").text(currentCatMinDisplay + " - " + currentCatMaxDisplay),
                node(OGC, "Filter").child(
                    node(OGC, "And").child(
                        node(OGC, "Not").child(
                            node(OGC, "And").child(
                                node(OGC, "PropertyIsEqualTo").child(
                                    node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED"),
                                    node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED")),
                                node(OGC, "Or").child(
                                    node(OGC, "PropertyIsNull").child(
                                        node(OGC, "PropertyName").text(attribute)),
                                    node(OGC, "PropertyIsEqualTo").child(
                                        node(OGC, "Literal").text("NEVER"),
                                        node(OGC, "Literal").text("TRUE"))))),
                        node(OGC, "PropertyIsBetween").child(node(OGC, "PropertyName").text(attribute),
                            node(OGC, "LowerBoundary").child(node(OGC, "Literal").text(currentCatMin)),
                            node(OGC, "UpperBoundary").child(node(OGC, "Literal").text(currentCatMax))))),
                            this.getSymbolNode(wkn, currentColorHex, fillOpacity, stroke, width, strokeOpacity, radius, false, "")
                            
                ).build(root);
  
            // Adding labels
            this.addLabelSymbolizer(ruleNode);
          }
        }
      }
      else if (this.visitor.currentLayer.getFeatureStrategy() == FeatureStrategy.BUBBLE)
      {
        ThematicLayer tLayer = (ThematicLayer) layer;
        ThematicStyle tStyle = (ThematicStyle) style;

        double minAttrVal = 0;
        double maxAttrVal = 0;
        int minSize = 0;
        int maxSize = 0;

        // attribute must be lowercase to work with postgres
        String attribute = tLayer.getAttribute().toLowerCase();

        if(this.visitor.currentLayer instanceof ThematicLayer)
        {
          ThematicLayer currentTLayer = (ThematicLayer) this.visitor.currentLayer;
          HashMap<String, Double> minMaxMap = currentTLayer.getLayerMinMax(attribute);
          minAttrVal = minMaxMap.get("min");
          maxAttrVal = minMaxMap.get("max");
        }

        minSize = tStyle.getBubbleMinSize();
        maxSize = tStyle.getBubbleMaxSize();

        // 
        // Secondary attributes only apply to bubble layer types so calling createRule with bubble params is appropriate
        //
        if (tStyle.getSecondaryAttributeDAO() != null)
        {
          try
          {
            String attributeName = tStyle.getSecondaryAttributeDAO().definesAttribute().toLowerCase();

            JSONArray array = tStyle.getSecondaryAttributeCategoriesAsJSON();
            for (int i = 0; i < array.length(); i++)
            {
              Boolean isRangeCat = false;
              JSONObject category = array.getJSONObject(i);
              Boolean catOtherCat = category.getBoolean("otherCat");
              String catMaxVal = null;
              Boolean rangeAllMin = false;
              Boolean rangeAllMax = false;
              String catVal = category.getString(ThematicStyle.VAL);
              String color = category.getString(ThematicStyle.COLOR);
              
              if(catOtherCat == false && category.has("isRangeCat") && category.getBoolean("isRangeCat") == true)
              {
                catMaxVal = category.getString("valMax");
                isRangeCat = category.getBoolean("isRangeCat"); 
                
                if(catVal.length() == 0)
                {
                  catVal = "ALLLESSTHAN";
                }
                if(catMaxVal.length() == 0)
                {
                  catMaxVal = "ALLGREATERTHAN";
                }
                if(category.has("rangeAllMin"))
                {
                  rangeAllMin = category.getBoolean("rangeAllMin");
                }
                if(category.has("rangeAllMax"))
                {
                  rangeAllMax = category.getBoolean("rangeAllMax");
                }
              }
              
              // If this category is a defined category (i.e. not the other category)
              if (catOtherCat == false)
              {
                if(isRangeCat == true)
                {
                  
                  NodeBuilder[] filterNodes = new NodeBuilder[] { 
                      node(OGC, "PropertyIsEqualTo").child(
                          node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED"), 
                          node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED")
                      ), 
                      node(OGC, "Not").child(
                          node(OGC, "Or").child(
                              node(OGC, "PropertyIsNull").child(
                                  node(OGC, "PropertyName").text(attributeName)
                              ), node(OGC, "PropertyIsEqualTo").child(
                                  node(OGC, "Literal").text("NEVER"), 
                                  node(OGC, "Literal").text("TRUE")
                              )
                          )
                      ),
                      this.getCategoryRangeNode(attribute, catVal, catMaxVal, rangeAllMin, rangeAllMax)
                  };

                  this.createRule(root, filterNodes, color, catVal, minAttrVal, maxAttrVal, minSize, maxSize);
                  
                }
                else
                {
                  NodeBuilder[] filterNodes = new NodeBuilder[] { 
                      node(OGC, "PropertyIsEqualTo").child(
                          node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED"), 
                          node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED")
                      ), 
                      node(OGC, "And").child(
                          node(OGC, "Not").child(
                              node(OGC, "Or").child(
                                  node(OGC, "PropertyIsNull").child(
                                      node(OGC, "PropertyName").text(attributeName)
                                  ), node(OGC, "PropertyIsEqualTo").child(
                                      node(OGC, "Literal").text("NEVER"), 
                                      node(OGC, "Literal").text("TRUE")
                                  )
                              )
                          ), 
                          node(OGC, "PropertyIsEqualTo").child(
                              node(OGC, "PropertyName").text(attributeName), 
                              node(OGC, "Literal").text(catVal)
                          )
                      ) 
                  };

                  this.createRule(root, filterNodes, color, catVal, minAttrVal, maxAttrVal, minSize, maxSize);
                }
              }
              
            }
          }
          catch (JSONException e)
          {
            throw new ProgrammingErrorException(e);
          }
        }
        else
        {
          String fill = tStyle.getBubbleFill();

          createRule(root, null, fill, null, minAttrVal, maxAttrVal, minSize, maxSize);
        }
      }
      else if (this.visitor.currentLayer.getFeatureStrategy() == FeatureStrategy.CATEGORYPOINT)
      {

        String catVal;
        String catTitle;
        String catColor;
        boolean catOtherCat = false;
        boolean catOtherEnabled = true;
        boolean isOntologyCat;
        boolean isRangeCat = false;
        String catMaxVal = null;
        boolean rangeAllMin = false;
        boolean rangeAllMax = false;
        ArrayList<String> catValTracking = new ArrayList<String>();

        ThematicLayer tLayer = (ThematicLayer) layer;
        // attribute must be lowercase to work with postgres
        String attribute = tLayer.getAttribute().toLowerCase();

        if (style instanceof DashboardThematicStyle)
        {
          DashboardThematicStyle dTStyle = (DashboardThematicStyle) style;

          Double fillOpacity = dTStyle.getCategoryPointFillOpacity();
          String stroke = dTStyle.getCategoryPointStroke();
          int width = dTStyle.getCategoryPointStrokeWidth();
          Double strokeOpacity = dTStyle.getCategoryPointStrokeOpacity();
          int radius = dTStyle.getCategoryPointSize();
          String currentLayerName = tLayer.getName(); //this.visitor.currentLayer.getName();
          String wkn = dTStyle.getCategoryPointWellKnownName();
            
          String cats = dTStyle.getCategoryPointStyles();
          if (cats.length() > 0)
          {
            JSONArray catsArrJSON = this.getCategories(cats);

            // SLD for all the categories scraped from the client
            for (int i = 0; i < catsArrJSON.length(); i++)
            {
              boolean enableIcon = false;
              String iconId = null;
              CategoryIcon icon = null;
              String iconPath = null;
              int categoryRadius = radius;
              
              try
              {
                JSONObject thisObj = catsArrJSON.getJSONObject(i);
                catVal = thisObj.getString("val");
                catTitle = catVal;
                catColor = thisObj.getString("color");
                isOntologyCat = thisObj.getBoolean("isOntologyCat");
                
                if(thisObj.has("enableIcon"))
                {
                  enableIcon = thisObj.getBoolean("enableIcon");
                  
                  if(enableIcon)
                  {
                    try
                    {
                      iconId = thisObj.getString("icon");
                      categoryRadius = thisObj.getInt("iconSize");
                    }
                    catch(JSONException e)
                    {
                      throw new ProgrammingErrorException(e);
                    }
                    
                    try
                    {
                      icon = CategoryIcon.get(iconId);
                      iconPath =  icon.getFilePath();
                    }
                    catch (Exception e)
                    {
                      //throw new ProgrammingErrorException(e);
                      
                      enableIcon = false; // to force the default point symbol
                    }
                  }
                }

                if (isOntologyCat == false)
                {
                  // 'other' attributes only relevant for non-ontology categories
                  catOtherCat = thisObj.getBoolean("otherCat");
                  catOtherEnabled = thisObj.getBoolean("otherEnabled");
                  isRangeCat = thisObj.getBoolean("isRangeCat");
                  
                  if(catOtherCat == false && isRangeCat == true)
                  {
                    catMaxVal = thisObj.getString("valMax");
                    
                    if(catVal.length() == 0)
                    {
                      catVal = "ALLLESSTHAN";
                    }
                    if(catMaxVal.length() == 0)
                    {
                      catMaxVal = "ALLGREATERTHAN";
                    }
                    if(thisObj.has("rangeAllMin"))
                    {
                      rangeAllMin = thisObj.getBoolean("rangeAllMin");
                    }
                    if(thisObj.has("rangeAllMax"))
                    {
                      rangeAllMax = thisObj.getBoolean("rangeAllMax");
                    }
                  }
                }
              }
              catch (JSONException e)
              {
                String msg = "Can not parse JSON during SLD generation.";
                throw new ProgrammingErrorException(msg, e);
              }

              // If this category is a defined category (i.e. not the other category)
              if (catOtherCat == false)
              {
                if (tLayer.getAttributeType().equals(AttributeType.NUMBER) && catVal != null && catVal.length() > 0)
                {
                  if(isRangeCat == true)
                  {
                    if(catMaxVal != null && (rangeAllMax || catMaxVal.length() > 0))
                    {
                      try
                      {
                        String catMin = formatter.format(new Double(catVal));
                        
                        String catMax;
                        if(rangeAllMax)
                        {
                          catMax = "ALLGREATERTHAN";
                        }
                        else
                        {
                          catMax = formatter.format(new Double(catMaxVal));
                        }
                        catTitle = catMin.concat(" - ").concat(catMax);
                      }
                      catch (Exception e)
                      {
                        // The category isn't actually a number so it can't be localized
                      }
                    }
                  }
                  else
                  {
                    try
                    {
                      catTitle = formatter.format(new Double(catVal));
                    }
                    catch (Exception e)
                    {
                      // The category isn't actually a number so it can't be localized
                    }
                  }
                }
                
                Node ruleNode = null;
                if(isRangeCat == true)
                {
                  ruleNode = node("Rule").child(
                      node("Name").text(catTitle),
                      node("Title").text(catTitle),
                      node(OGC, "Filter").child(
                          node(OGC, "And").child(
                              node(OGC, "Not").child(
                                  node(OGC, "And").child(
                                      node(OGC, "PropertyIsEqualTo").child(
                                          node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED"),
                                          node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED")),
                                      node(OGC, "Or").child(
                                          node(OGC, "PropertyIsNull").child(
                                              node(OGC, "PropertyName").text(attribute)),
                                          node(OGC, "PropertyIsEqualTo").child(
                                              node(OGC, "Literal").text("NEVER"),
                                              node(OGC, "Literal").text("TRUE")
                                          )
                                      )
                                  )
                              ),
                              this.getCategoryRangeNode(attribute, catVal, catMaxVal, rangeAllMin, rangeAllMax)
                          )
                      ),
                      this.getSymbolNode(wkn, catColor, fillOpacity, stroke, width, strokeOpacity, categoryRadius, false, "")
                                  
                      ).build(root);
                }
                else
                {
                    ruleNode = node("Rule").child(
                        node("Name").text(catVal),
                        node("Title").text(catTitle),
                        node(OGC, "Filter").child(
                            node(OGC, "And").child(
                                node(OGC, "PropertyIsEqualTo").child(
                                    node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED"),
                                    node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED")),
                                node(OGC, "And").child(
                                    node(OGC, "Not").child(
                                        node(OGC, "Or").child(
                                            node(OGC, "PropertyIsNull").child(
                                                node(OGC, "PropertyName").text(attribute)),
                                            node(OGC, "PropertyIsEqualTo").child(
                                                node(OGC, "Literal").text("NEVER"),
                                                node(OGC, "Literal").text("TRUE")
                                                )
                                            )
                                        ),
                                    node(OGC, "PropertyIsEqualTo").child(
                                        node(OGC, "PropertyName").text(attribute),
                                        node(OGC, "Literal").text(catVal)
                                        )
                                    )
                                )
                            ),
                            this.getSymbolNode(wkn, catColor, fillOpacity, stroke, width, strokeOpacity, categoryRadius, enableIcon, iconPath)).build(root);
                }
                
                //
                // Adding labels
                //
                this.addLabelSymbolizer(ruleNode);

                if(isRangeCat == true)
                {
                  String combined = catVal.concat("::").concat(catMaxVal);
                  catValTracking.add(combined);
                }
                else
                {
                  catValTracking.add(catVal);  
                }
              }
              else
              {
                // 'OTHER' Point styles
                if (catOtherEnabled == true)
                {
                     NodeBuilder wrapperAndNode = node(OGC, "And");
                  
                     String label = LocalizationFacade.getFromBundles("Other");
                     
                     if(isRangeCat == true)
                     {
                       // Build 'OTHER' exclusion fragments
                       for (String otherCatVal : catValTracking)
                       {
                         NodeBuilder otherNotNode = node(OGC, "Not");
                         
                         String[] rangeVals = otherCatVal.split("::");
                         String minVal = rangeVals[0];
                         String maxVal = rangeVals[1];
                         otherNotNode.child(
                             this.getCategoryRangeNode(attribute, minVal, maxVal, rangeAllMin, rangeAllMax)
                         );
                         
                         wrapperAndNode.child(otherNotNode);
                       }
                       
                       node("Rule").child(
                           node("Name").text(label), 
                           node("Title").text(label), 
                               node(OGC, "Filter").child(
                                   node(OGC, "And").child(
                                       node(OGC, "PropertyIsEqualTo").child(
                                           node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED"), 
                                           node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED")
                                       ), 
                                       wrapperAndNode
                                   )
                               ),
                               this.getSymbolNode(wkn, catColor, fillOpacity, stroke, width, strokeOpacity, radius, false, "")).build(root);
                     }
                     else
                     {
                       NodeBuilder otherOrNode = node(OGC, "Or");
                       
                       // Build 'OTHER' exclusion fragments
                       for (String otherCatVal : catValTracking)
                       {
                         otherOrNode.child(node(OGC, "PropertyIsEqualTo").child(
                             node(OGC, "PropertyName").text(attribute), node(OGC, "Literal").text(otherCatVal)));
                       }
                       
                       node("Rule").child(
                           node("Name").text(label), 
                           node("Title").text(label), 
                                       node(OGC, "Filter").child(
                                           node(OGC, "And").child(
                                           node(OGC, "PropertyIsEqualTo").child(
                                               node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED"), 
                                               node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED")), 
                                               node(OGC, "Not").child(
                                                   otherOrNode.child(
//                                                   node(OGC, "Or").child(
                                                       node(OGC, "PropertyIsNull").child(
                                                           node(OGC, "PropertyName").text(attribute)
                                                       )
//                                                   )
                                                       
                                                   )
                                               )
                                           )
                                       ),
                                       this.getSymbolNode(wkn, catColor, fillOpacity, stroke, width, strokeOpacity, radius, false, "")).build(root);
                     }
                   
                }
              }
            }
          }
          else
          {
            //
            // The categories data does not exist. Rather than throwing an error which will make the map unusable
            // and prevent the user from fixing the issue without help from a developer we will render basic polygons.
            // This isn't the prettiest way to handle this but helps to maintain app uptime in obscure situations.
            //
            node("Rule").child(
                node("Name").text(currentLayerName),
                node("Title").text(currentLayerName),
                node("PolygonSymbolizer").child(
                    node("Geometry").child(node(OGC, "PropertyName").text("geom")),
                    node("Fill").child(css("fill", "#E60000"), css("fill-opacity", "0.6")),
                    node("Stroke").child(css("stroke", "#8A0000"), css("stroke-width", "1"),
                        css("stroke-opacity", "0.6")))).build(root);

          }
        }
      }

      return root;
    }


    private NodeBuilder[] getElseNode(String attributeName, JSONArray array) throws JSONException
    {
      NodeBuilder[] nodes = new NodeBuilder[] { node(OGC, "PropertyIsEqualTo").child(node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED"), node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED")), node(OGC, "Or").child(node(OGC, "Not").child(node(OGC, "Or").child(this.getNotChildren(attributeName, array))), node(OGC, "PropertyIsNull").child(node(OGC, "PropertyName").text(attributeName))) };

      return nodes;
    }

    private NodeBuilder[] getNotChildren(String attributeName, JSONArray array) throws JSONException
    {
      List<NodeBuilder> list = new LinkedList<NodeBuilder>();
      for (int i = 0; i < array.length(); i++)
      {
        JSONObject category = array.getJSONObject(i);
        String key = category.getString(ThematicStyle.VAL);
        NodeBuilder builder = node(OGC, "PropertyIsEqualTo").child(node(OGC, "PropertyName").text(attributeName), node(OGC, "Literal").text(key));
        list.add(builder);
      }

      return list.toArray(new NodeBuilder[list.size()]);
    }
    
    private void createRule(Node root, NodeBuilder[] filterNodes, String fill, String postfix, int radius)
    {
      //NumberFormat formatter = this.getRuleNumberFormatter();
      
        // attribute must be lowercase to work with postgres
        //String attribute = tLayer.getAttribute().toLowerCase();
        Double opacity = style.getPointOpacity();
        String stroke = style.getPointStroke();
        Integer width = style.getPointStrokeWidth();
        Double strokeOpacity = style.getPointStrokeOpacity();
        String wkn = style.getPointWellKnownName();
        //Integer rotation = style.getPointRotation();
        String name = layer.getName();

        if (postfix != null)
        {
          name += " " + postfix;
        }

        Node ruleNode = node("Rule").build(root);
        node("Name").text(name).build(ruleNode);
        node("Title").text(name).build(ruleNode);

        if (filterNodes != null)
        {
          NodeBuilder filterNode = node(OGC, "Filter").child(node(OGC, "And").child(filterNodes));
          filterNode.build(ruleNode);
        }

        // Point styles
        Node pointSymbolNode = node("PointSymbolizer").build(ruleNode);
        Node graphicNode = node("Graphic").build(pointSymbolNode);
        Node markNode = node("Mark").build(graphicNode);
        node("WellKnownName").text(wkn).build(markNode);
        node("Fill").child(
            this.getFillNode(style, fill), 
            css("fill-opacity", opacity)).build(markNode);
        node("Stroke").child(
            css("stroke", stroke), 
            css("stroke-width", width), 
            css("stroke-opacity", strokeOpacity)).build(markNode);
        node("Size").text(radius).build(graphicNode);

        // Adding labels
        this.addLabelSymbolizer(ruleNode);
    }

    private void createRule(Node root, NodeBuilder[] filterNodes, String fill, String postfix, double minAttrVal, double maxAttrVal, int minSize, int maxSize)
    {
      ThematicLayer tLayer = (ThematicLayer) layer;
      ThematicStyle tStyle = (ThematicStyle) style;

      // attribute must be lowercase to work with postgres
      String attribute = tLayer.getAttribute().toLowerCase();
      Double opacity = tStyle.getBubbleOpacity();
      String stroke = tStyle.getBubbleStroke();
      Integer width = tStyle.getBubbleStrokeWidth();
      Double strokeOpacity = tStyle.getBubbleStrokeOpacity();
      String wkn = tStyle.getBubbleWellKnownName();
      Integer rotation = tStyle.getBubbleRotation();

      NumberFormat formatter = this.getRuleNumberFormatter();

      if (tStyle.getBubbleContinuousSize() == true)
      {
        NodeBuilder sizeNode = interpolateSize(minAttrVal, maxAttrVal);
        Node ruleNode = node("Rule").build(root);
        String currentCatMinDisplay = formatter.format(minAttrVal);
        String currentCatMaxDisplay = formatter.format(maxAttrVal);

        String name = currentCatMinDisplay + " - " + currentCatMaxDisplay;

        if (postfix != null)
        {
          name += " " + postfix;
        }

        node("Name").text(name).build(ruleNode);
        node("Title").text(name).build(ruleNode);

        if (filterNodes != null)
        {
          NodeBuilder filterNode = node(OGC, "Filter").child(node(OGC, "And").child(filterNodes));

          filterNode.build(ruleNode);
        }

        Node bubbleSymbolNode = node("PointSymbolizer").build(ruleNode);

        node("Graphic").child(
            node("Mark").child(
                node("WellKnownName").text(wkn),
                node("Fill").child(
                    this.getFillNode(tStyle, fill), 
                    css("fill-opacity", opacity)),
                node("Stroke").child(
                    css("stroke", stroke), 
                    css("stroke-width", width),
                    css("stroke-opacity", strokeOpacity))), 
                    sizeNode, 
                    node("Rotation").text(rotation))
            .build(bubbleSymbolNode);

        // Adding labels
        this.addLabelSymbolizer(ruleNode);
      }
      else
      {
        int numCategories = 5; // 5 is the default
        if (minSize == maxSize || minAttrVal == maxAttrVal)
        {
          // min/max are the same suggesting there is only one feature (i.e. gradient on a single polygon)
          numCategories = 1;
        }
        else
        {
          numCategories = tStyle.getNumBubbleSizeCategories();
        }

        double categoryLen = ( maxAttrVal - minAttrVal ) / numCategories;
        int pointSizeCatLen = ( maxSize - minSize ) / numCategories;

        for (int i = 0; i < numCategories; i++)
        {
          double currentCatMin = minAttrVal + ( i * categoryLen );
          double currentCatMax = minAttrVal + ( ( i + 1 ) * categoryLen );

          double currentPointSizeRaw = minSize + ( ( i + 1 ) * pointSizeCatLen );
          int currentPointSize = (int) Math.round(currentPointSizeRaw);

          String currentCatMinDisplay = formatter.format(currentCatMin);
          String currentCatMaxDisplay = formatter.format(currentCatMax);

          Node ruleNode = node("Rule").build(root);
          String name = currentCatMinDisplay + " - " + currentCatMaxDisplay;

          if (postfix != null)
          {
            name += " " + postfix;
          }

          node("Name").text(name).build(ruleNode);
          node("Title").text(name).build(ruleNode);

          Node filterNode = node(OGC, "Filter").build(ruleNode);
          Node firstAndNode = node(OGC, "And").build(filterNode);

          if (filterNodes != null)
          {
            for (NodeBuilder filter : filterNodes)
            {
              filter.build(firstAndNode);
            }
          }

          Node notNode = node(OGC, "Not").build(firstAndNode);
          Node secondAndNode = node(OGC, "And").build(notNode);

          Node firstPropEqualToNode = node(OGC, "PropertyIsEqualTo").build(secondAndNode);
          node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED").build(firstPropEqualToNode);
          node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED").build(firstPropEqualToNode);

          Node orNode = node(OGC, "Or").build(secondAndNode);
          Node propIsNullNode = node(OGC, "PropertyIsNull").build(orNode);
          node(OGC, "PropertyName").text(attribute).build(propIsNullNode);

          Node propEqualToNode = node(OGC, "PropertyIsEqualTo").build(orNode);
          node(OGC, "Literal").text("NEVER").build(propEqualToNode);
          node(OGC, "Literal").text("TRUE").build(propEqualToNode);

          Node propIsBetween = node(OGC, "PropertyIsBetween").build(firstAndNode);
          node(OGC, "PropertyName").text(attribute).build(propIsBetween);
          node(OGC, "LowerBoundary").child(node(OGC, "Literal").text(currentCatMin)).build(propIsBetween);
          node(OGC, "UpperBoundary").child(node(OGC, "Literal").text(currentCatMax)).build(propIsBetween);

          // Point styles
          Node pointSymbolNode = node("PointSymbolizer").build(ruleNode);
          Node graphicNode = node("Graphic").build(pointSymbolNode);
          Node markNode = node("Mark").build(graphicNode);
          node("WellKnownName").text("circle").build(markNode);
          node("Fill").child(this.getFillNode(tStyle, fill), css("fill-opacity", opacity)).build(markNode);

          node("Stroke").child(css("stroke", stroke), css("stroke-width", width), css("stroke-opacity", strokeOpacity)).build(markNode);
          node("Size").text(currentPointSize).build(graphicNode);

          // Adding labels
          this.addLabelSymbolizer(ruleNode);
        }
      }
    }
    
    private NodeBuilder getSymbolNode(String wellKnownType, String fill, Double fillOpacity, String strokeColor, int width, Double strokeOpacity, int radius, boolean customIcon, String iconPath)
    {
      NodeBuilder symbolNode;
      
      if(customIcon)
      {
        symbolNode = node("PointSymbolizer").child(
            node("Graphic").child(
              node("ExternalGraphic").child(
                  node("OnlineResource xlink:type='simple' xlink:href='file://"+ iconPath +"'"),
                  node("Format").text("image/png")
                ), 
                node("Size").text(radius))
          );
      }
      else
      {
        symbolNode = node("PointSymbolizer").child(
          node("Graphic").child(
            node("Mark").child(
              node("WellKnownName").text(wellKnownType),
              node("Fill").child(
                css("fill", fill),
                css("fill-opacity", fillOpacity)),
              node("Stroke").child(
                css("stroke", strokeColor), 
                css("stroke-width", width),
                css("stroke-opacity", strokeOpacity)
                )
              ), 
              node("Size").text(radius))
        );
      }
      
      return symbolNode;
    }
    
    private NodeBuilder getCategoryRangeNode(String attribute, String lowRange, String highRange, boolean rangeAllMin, boolean rangeAllMax)
    {
      NodeBuilder catNode = null;
      
      if(rangeAllMin)
      {
        catNode = node(OGC, "PropertyIsLessThanOrEqualTo").child(
            node(OGC, "PropertyName").text(attribute),
            node(OGC, "Literal").text(highRange)
        );
      }
      else if(rangeAllMax)
      {
        catNode = node(OGC, "PropertyIsGreaterThanOrEqualTo").child(
            node(OGC, "PropertyName").text(attribute),
            node(OGC, "Literal").text(lowRange)
        );
      }
      else
      {
        catNode = node(OGC, "PropertyIsBetween").child(
            node(OGC, "PropertyName").text(attribute),
            node(OGC, "LowerBoundary").child(
                node(OGC, "Literal").text(lowRange)
            ),
            node(OGC, "UpperBoundary").child(
                node(OGC, "Literal").text(highRange)
            )
        );
      }
      
      return catNode;
    }

    private NodeBuilder getFillNode(Style style, String fill)
    {
      if(layer instanceof ThematicLayer)
      {
        if(style instanceof ThematicStyle)
        {
          ThematicStyle tStyle = (ThematicStyle) style;
          if (tStyle.getSecondaryAttributeDAO() != null)
          {
            NodeBuilder node = css("fill").child(
                node(OGC, "Function").attr("name", "if_then_else").child(
                    node(OGC, "Function").attr("name", "isNull").child(
                        this.getRecodeNode(tStyle)),
                    node("ogc:Literal").text(fill), 
                    this.getRecodeNode(tStyle)));
    
            return node;
          }
        }
      }

      return css("Fill", fill);
    }

    private NodeBuilder getRecodeNode(ThematicStyle tStyle)
    {
      try
      {
        String attributeName = tStyle.getSecondaryAttributeDAO().definesAttribute().toLowerCase();

        List<NodeBuilder> children = new LinkedList<NodeBuilder>();
        children.add(node(OGC, "PropertyName").text(attributeName));

        JSONArray array = tStyle.getSecondaryAttributeCategoriesAsJSON();
        for (int i = 0; i < array.length(); i++)
        {
          JSONObject category = array.getJSONObject(i);
          String key = category.getString(ThematicStyle.VAL);
          String color = category.getString(ThematicStyle.COLOR);

          children.add(node(OGC, "Literal").text(key));
          children.add(node(OGC, "Literal").text(color));
        }

        NodeBuilder recode = node(OGC, "Function").attr("name", "Recode").child(children.toArray(new NodeBuilder[children.size()]));

        return recode;
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }

    private NodeBuilder interpolateSize(double minAttrVal, double maxAttrVal)
    {
      if (this.visitor.currentLayer.getFeatureStrategy() == FeatureStrategy.BUBBLE)
      {
        ThematicStyle tStyle = (ThematicStyle) style;
        ThematicLayer tLayer = (ThematicLayer) layer;
        // attribute must be lowercase to work with postgres
        String attribute = tLayer.getAttribute().toLowerCase();

        // thematic interpolation
        NodeBuilder builder = node("Size").child(
            node(OGC, "Function").attr("name", "if_then_else").child(
                node(OGC, "Function").attr("name", "isNull").child(
                    node(OGC, "PropertyName").text(attribute)),
                node(OGC, "Literal").text(minAttrVal),
                node(OGC, "Function").attr("name", "Interpolate").child(
                    node(OGC, "PropertyName").text(attribute),
                    node(OGC, "Literal").text(minAttrVal),   // property to interpolate
                    node(OGC, "Literal").text(tStyle.getBubbleMinSize()), 
                    node(OGC, "Literal").text(maxAttrVal), 
                    node(OGC, "Literal").text(tStyle.getBubbleMaxSize()),
                    node(OGC, "Literal").text("numeric"))));  // interpolation method
        
        return builder;
      }
      else
      {
        // non-thematic
        return node("Size").text(11);
      }
    }
  }

  /**
   * Build <PolygonSymbolizer> tag and contents a <PolygonSymbolizer> encloses the styling params and the geometry field
   * used for rendering features
   * 
   */
  private static class PolygonSymbolizer extends Symbolizer implements com.runwaysdk.generation.loader.Reloadable
  {
    private PolygonSymbolizer(SLDMapVisitor visitor, Layer layer, Style style)
    {
      super(visitor, layer, style);
    }

    @Override
    protected String getSymbolizerName()
    {
      return "FeatureTypeStyle";
    }
    
    private NodeBuilder getCategoryRangeNode(String attribute, String lowRange, String highRange, boolean rangeAllMin, boolean rangeAllMax)
    {
      NodeBuilder catNode = null;
      
      if(rangeAllMin)
      {
        catNode = node(OGC, "PropertyIsLessThanOrEqualTo").child(
            node(OGC, "PropertyName").text(attribute),
            node(OGC, "Literal").text(highRange)
        );
      }
      else if(rangeAllMax)
      {
        catNode = node(OGC, "PropertyIsGreaterThanOrEqualTo").child(
            node(OGC, "PropertyName").text(attribute),
            node(OGC, "Literal").text(lowRange)
        );
      }
      else
      {
        catNode = node(OGC, "PropertyIsBetween").child(
            node(OGC, "PropertyName").text(attribute),
            node(OGC, "LowerBoundary").child(
                node(OGC, "Literal").text(lowRange)
            ),
            node(OGC, "UpperBoundary").child(
                node(OGC, "Literal").text(highRange)
            )
        );
      }
      
      return catNode;
    }

    @Override
    protected Node getSLD()
    {
      NumberFormat formatter = this.getRuleNumberFormatter();
      Node root = super.getSLD();
      String currentLayerName = this.visitor.currentLayer.getName();

      node("FeatureTypeName").text(style.getName()).build(root);

      if (this.visitor.currentLayer.getFeatureStrategy() == FeatureStrategy.GRADIENTPOLYGON)
      {
        int numCategories = 5; // 5 is the default
        double categoryLen;
        
        if(this.style instanceof ThematicStyle)
        {
          ThematicStyle tStyle = (ThematicStyle) this.style;
          
          Double fillOpacity = tStyle.getGradientPolygonFillOpacity();
          String stroke = tStyle.getGradientPolygonStroke();
          int width = tStyle.getGradientPolygonStrokeWidth();
          Double strokeOpacity = tStyle.getGradientPolygonStrokeOpacity();
          
          // SLD generation
          ThematicLayer tLayer = (ThematicLayer) layer;
  
          // attribute must be lowercase to work with postgres
          String attribute = tLayer.getAttribute().toLowerCase();
  
          HashMap<String, Double> minMaxMap = null;
          if(this.visitor.currentLayer instanceof ThematicLayer)
          {
            ThematicLayer currentTLayer = (ThematicLayer) this.visitor.currentLayer;
            minMaxMap = currentTLayer.getLayerMinMax(attribute);
          }
          double minAttrVal = minMaxMap.get("min");
          double maxAttrVal = minMaxMap.get("max");
  
          if (minAttrVal == maxAttrVal)
          {
            // min/max are the same suggesting there is only one feature (i.e. gradient on a single polygon)
            numCategories = 1;
            categoryLen = 1.0;
          }
          else
          {
            numCategories = tStyle.getNumGradientPolygonCategories();
            categoryLen = ( maxAttrVal - minAttrVal ) / numCategories;
          }
  
          HashMap<Integer, Color> gradientColors = interpolateColor(numCategories, this.visitor.currentLayer.getFeatureStrategy(), style);
  
          for (int i = 0; i < numCategories; i++)
          {
            double currentCatMin;
  
            if (numCategories == 1)
            {
              currentCatMin = 0;
            }
            else
            {
              currentCatMin = minAttrVal + ( i * categoryLen );
            }
            double currentCatMax = minAttrVal + ( ( i + 1 ) * categoryLen );
  
            int currentColorPos = i + 1;
  
            Color currentColor = gradientColors.get(currentColorPos);
            String currentColorHex = String.format("#%02x%02x%02x", currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue());
  
            String currentCatMinDisplay = formatter.format(currentCatMin);
            String currentCatMaxDisplay = formatter.format(currentCatMax);
  
            Node ruleNode = node("Rule").child(node("Name").text(currentCatMinDisplay + " - " + currentCatMaxDisplay), 
                node("Title").text(currentCatMinDisplay + " - " + currentCatMaxDisplay),
                node(OGC, "Filter").child(
                    node(OGC, "And").child(
                    node(OGC, "Not").child(
                        node(OGC, "And").child(
                        node(OGC, "PropertyIsEqualTo").child(
                            node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED"), 
                            node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED")), 
                            node(OGC, "Or").child(
                                node(OGC, "PropertyIsNull").child(
                                    node(OGC, "PropertyName").text(attribute)), 
                                    node(OGC, "PropertyIsEqualTo").child(
                                        node(OGC, "Literal").text("NEVER"), 
                                        node(OGC, "Literal").text("TRUE"))))), 
                                        node(OGC, "PropertyIsBetween").child(
                                            node(OGC, "PropertyName").text(attribute), 
                                            node(OGC, "LowerBoundary").child(
                                                node(OGC, "Literal").text(currentCatMin)), 
                                            node(OGC, "UpperBoundary").child(
                                                node(OGC, "Literal").text(currentCatMax))))),
                node("PolygonSymbolizer").child(node("Geometry").child(
                    node(OGC, "PropertyName").text("geom")), 
                    node("Fill").child(
                        css("fill", currentColorHex), 
                        css("fill-opacity", fillOpacity)), 
                        node("Stroke").child(
                            css("stroke", stroke), 
                            css("stroke-width", width), 
                            css("stroke-opacity", strokeOpacity)))).build(root);
  
            // Adding labels
            this.addLabelSymbolizer(ruleNode);
          }
        }
      }
      else if (this.visitor.currentLayer.getFeatureStrategy() == FeatureStrategy.CATEGORYPOLYGON)
      {
        String catVal;
        String catTitle;
        String catColor;
        boolean catOtherCat = false;
        boolean catOtherEnabled = true;
        boolean isOntologyCat;
        boolean isRangeCat = false;
        String catMaxVal = null;
        boolean rangeAllMin = false;
        boolean rangeAllMax = false;
        ArrayList<String> catValTracking = new ArrayList<String>();

        ThematicLayer tLayer = (ThematicLayer) layer;
        // attribute must be lowercase to work with postgres
        String attribute = tLayer.getAttribute().toLowerCase();

        if (style instanceof DashboardThematicStyle)
        {
          DashboardThematicStyle dTStyle = (DashboardThematicStyle) style;
          NodeBuilder otherPolySymbolNode = node("PolygonSymbolizer");

          Double fillOpacity = dTStyle.getCategoryPolygonFillOpacity();
          String stroke = dTStyle.getCategoryPolygonStroke();
          int width = dTStyle.getCategoryPolygonStrokeWidth();
          Double strokeOpacity = dTStyle.getCategoryPolygonStrokeOpacity();
            
          // ontology logic
          String cats = dTStyle.getCategoryPolygonStyles();
          if (cats.length() > 0)
          {
            JSONArray catsArrJSON = this.getCategories(cats);

            // SLD for all the categories scraped from the client
            for (int i = 0; i < catsArrJSON.length(); i++)
            {
              try
              {
                JSONObject thisObj = catsArrJSON.getJSONObject(i);
                catVal = thisObj.getString("val");
                catTitle = catVal;
                catColor = thisObj.getString("color");
                isOntologyCat = thisObj.getBoolean("isOntologyCat");

                if (isOntologyCat == false)
                {
                  // 'other' attributes only relevant for non-ontology categories
                  catOtherCat = thisObj.getBoolean("otherCat");
                  catOtherEnabled = thisObj.getBoolean("otherEnabled");
                  
                  if(catOtherCat == false)
                  {
                    isRangeCat = thisObj.getBoolean("isRangeCat");
                    if (isRangeCat)
                    {
                      catMaxVal = thisObj.getString("valMax");
                      
                      if(catVal.length() == 0)
                      {
                        catVal = "ALLLESSTHAN";
                      }
                      if(catMaxVal.length() == 0)
                      {
                        catMaxVal = "ALLGREATERTHAN";
                      }
                      if(thisObj.has("rangeAllMin"))
                      {
                        rangeAllMin = thisObj.getBoolean("rangeAllMin");
                      }
                      if(thisObj.has("rangeAllMax"))
                      {
                        rangeAllMax = thisObj.getBoolean("rangeAllMax");
                      }
                    }
                  }
                }
              }
              catch (JSONException e)
              {
                String msg = "Can not parse JSON during SLD generation.";
                throw new ProgrammingErrorException(msg, e);
              }

              // If this category is a defined category (i.e. not the other category)
              if (catOtherCat == false)
              {
                if (tLayer.getAttributeType().equals(AttributeType.NUMBER) && catVal != null && catVal.length() > 0)
                {
                  if(isRangeCat == true)
                  {
                    if(catMaxVal != null && catMaxVal.length() > 0)
                    {
                      try
                      {
                        String catMin = formatter.format(new Double(catVal));
                        String catMax;
                        if(rangeAllMax)
                        {
                          catMax = "ALLGREATERTHAN";
                        }
                        else
                        {
                          catMax = formatter.format(new Double(catMaxVal));
                        }
                        catTitle = catMin.concat(" - ").concat(catMax);
                      }
                      catch (Exception e)
                      {
                        // The category isn't actually a number so it can't be localized
                      }
                    }
                  }
                  else
                  {
                    try
                    {
                      catTitle = formatter.format(new Double(catVal));
                    }
                    catch (Exception e)
                    {
                      // The category isn't actually a number so it can't be localized
                    }
                  }
                }

                Node ruleNode = null;
                if(isRangeCat == true)
                {
                  ruleNode = node("Rule").child(
                      node("Name").text(catVal), 
                      node("Title").text(catTitle), 
                      node(OGC, "Filter").child(
                          node(OGC, "And").child(
                              node(OGC, "PropertyIsEqualTo").child(
                                  node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED"), 
                                  node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED")
                              ), 
                              node(OGC, "And").child(
                                  node(OGC, "Not").child(
                                      node(OGC, "Or").child(
                                          node(OGC, "PropertyIsNull").child(
                                              node(OGC, "PropertyName").text(attribute)
                                          ), 
                                          node(OGC, "PropertyIsEqualTo").child(
                                              node(OGC, "Literal").text("NEVER"), 
                                              node(OGC, "Literal").text("TRUE")
                                          )
                                      )
                                  ), 
                                  this.getCategoryRangeNode(attribute, catVal, catMaxVal, rangeAllMin, rangeAllMax)
                              )
                          )
                      ),
                      node("PolygonSymbolizer").child(
                          node("Geometry").child(
                              node(OGC, "PropertyName").text("geom")), 
                              node("Fill").child(
                                  css("fill", catColor), 
                                  css("fill-opacity", fillOpacity)), 
                                  node("Stroke").child(
                                      css("stroke", stroke), 
                                      css("stroke-width", width), 
                                      css("stroke-opacity", strokeOpacity)))
                      ).build(root);
                }
                else
                {
                  ruleNode = node("Rule").child(
                      node("Name").text(catVal), 
                      node("Title").text(catTitle), 
                      node(OGC, "Filter").child(
                          node(OGC, "And").child(
                              node(OGC, "PropertyIsEqualTo").child(
                                  node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED"), 
                                  node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED")), 
                                  node(OGC, "And").child(
                                      node(OGC, "Not").child(
                                          node(OGC, "Or").child(
                                              node(OGC, "PropertyIsNull").child(
                                                  node(OGC, "PropertyName").text(attribute)), 
                                                  node(OGC, "PropertyIsEqualTo").child(
                                                      node(OGC, "Literal").text("NEVER"), 
                                                      node(OGC, "Literal").text("TRUE")))), 
                                                      node(OGC, "PropertyIsEqualTo").child(
                                                          node(OGC, "PropertyName").text(attribute), 
                                                          node(OGC, "Literal").text(catVal))))),
                      node("PolygonSymbolizer").child(
                          node("Geometry").child(
                              node(OGC, "PropertyName").text("geom")), 
                              node("Fill").child(
                                  css("fill", catColor), 
                                  css("fill-opacity", fillOpacity)), 
                                  node("Stroke").child(
                                      css("stroke", stroke), 
                                      css("stroke-width", width), 
                                      css("stroke-opacity", strokeOpacity)))
                      ).build(root);
                }

                //
                // Adding labels
                //
                this.addLabelSymbolizer(ruleNode);

                if(isRangeCat == true)
                {
                  String combined = catVal.concat("::").concat(catMaxVal);
                  catValTracking.add(combined);
                }
                else
                {
                  catValTracking.add(catVal);  
                }
              }
              else
              {
                // 'OTHER' Polygon styles
                if (catOtherEnabled == true)
                {
                  otherPolySymbolNode.child(
                      node("Geometry").child(
                          node(OGC, "PropertyName").text("geom")
                      ), 
                      node("Fill").child(
                          css("fill", catColor), 
                          css("fill-opacity", fillOpacity)
                      ), 
                      node("Stroke").child(
                          css("stroke", stroke), 
                          css("stroke-width", width), 
                          css("stroke-opacity", strokeOpacity)
                      )
                  );
                }
              }
            }

            //
            // Build the 'OTHER' rule
            //
            if (catOtherEnabled == true)
            {
              
              NodeBuilder wrapperAndNode = node(OGC, "And");
              
              String label = LocalizationFacade.getFromBundles("Other");
              
              if(isRangeCat == true)
              {
                
                // Build 'OTHER' exclusion fragments
                for (String otherCatVal : catValTracking)
                {
                  NodeBuilder otherNotNode = node(OGC, "Not");
                  
                  String[] rangeVals = otherCatVal.split("::");
                  String minVal = rangeVals[0];
                  String maxVal = rangeVals[1];
                  otherNotNode.child(
                      this.getCategoryRangeNode(attribute, minVal, maxVal, rangeAllMin, rangeAllMax)
                  );
                  
                  wrapperAndNode.child(otherNotNode);
                }
                
                node("Rule").child(
                    node("Name").text(label), 
                    node("Title").text(label), 
                    otherPolySymbolNode.child(
                        node("Stroke").child(
                            css("stroke", stroke), 
                            css("stroke-width", width), 
                            css("stroke-opacity", strokeOpacity)
                        )
                    ), 
                    node(OGC, "Filter").child(
                        node(OGC, "And").child(
                            node(OGC, "PropertyIsEqualTo").child(
                                node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED"), 
                                node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED")
                            ), 
                            wrapperAndNode
                        )
                    )
                ).build(root);
              }
              else
              {
                NodeBuilder otherOrNode = node(OGC, "Or");
                
                // Build 'OTHER' exclusion fragments
                for (String otherCatVal : catValTracking)
                {
                  otherOrNode.child(
                      node(OGC, "PropertyIsEqualTo").child(
                          node(OGC, "PropertyName").text(attribute), 
                          node(OGC, "Literal").text(otherCatVal)
                      )
                  );
                }
                
                node("Rule").child(
                    node("Name").text(label), 
                    node("Title").text(label), 
                    node(OGC, "Filter").child(
                        node(OGC, "And").child(
                            node(OGC, "PropertyIsEqualTo").child(
                                node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED"), 
                                node(OGC, "Literal").text("ALL_LABEL_CLASSES_ENABLED")
                            ), 
                            node(OGC, "Not").child(
                                otherOrNode.child(
                                    node(OGC, "Or").child(
                                        node(OGC, "PropertyIsNull").child(
                                            node(OGC, "PropertyName").text(attribute)
                                        )
                                    )
                                )
                            )
                        )
                    ),
                    otherPolySymbolNode.child(
                        node("Stroke").child(
                            css("stroke", stroke), 
                            css("stroke-width", width), 
                            css("stroke-opacity", strokeOpacity)
                        )
                    )
                ).build(root);
              }
            }
          }
          else
          {
            //
            // The categories data does not exist. Rather than throwing an error which will make the map unusable
            // and prevent the user from fixing the issue without help from a developer we will render basic polygons.
            // This isn't the prettiest ways to handle this but helps to maintain app uptime in obscure situations.
            //
            node("Rule").child(
                node("Name").text(currentLayerName), 
                node("Title").text(currentLayerName), 
                node("PolygonSymbolizer").child(
                    node("Geometry").child(
                        node(OGC, "PropertyName").text("geom")
                    ), node("Fill").child(
                        css("fill", "#E60000"), 
                        css("fill-opacity", "0.6")
                    ), node("Stroke").child(
                        css("stroke", "#8A0000"), 
                        css("stroke-width", "1"), 
                        css("stroke-opacity", "0.6")
                    )
                )
            ).build(root);
          }
        }
      }
      else
      {
        Integer width = this.style.getPolygonStrokeWidth();
        Double fillOpacity = this.style.getPolygonFillOpacity();
        String stroke = this.style.getPolygonStroke();
        Double strokeOpacity = this.style.getPolygonStrokeOpacity();
        String fill = this.style.getPolygonFill();
        
        // Basic polygon
        Node ruleNode = node("Rule").child(node("Name").text("basic"), node("Title").text("basic"), node("PolygonSymbolizer").child(node("Geometry").child(node(OGC, "PropertyName").text("geom")), node("Fill").child(css("fill", fill), css("fill-opacity", fillOpacity)), node("Stroke").child(css("stroke", stroke), css("stroke-width", width), css("stroke-opacity", strokeOpacity)))).build(root);

        //
        // Adding labels
        //
        this.addLabelSymbolizer(ruleNode);
      }

      return root;
    }

  }

  private static class LineSymbolizer extends Symbolizer implements com.runwaysdk.generation.loader.Reloadable
  {
    private LineSymbolizer(SLDMapVisitor visitor, Layer layer, Style style)
    {
      super(visitor, layer, style);
    }

    @Override
    protected String getSymbolizerName()
    {
      return "LineSymbolizer";
    }

    @Override
    protected Node getSLD()
    {
      Node root = super.getSLD();

      return root;
    }
  }

  private static class TextSymbolizer extends Symbolizer implements com.runwaysdk.generation.loader.Reloadable
  {
    private Node[]  nodes;

    private String  color;

    private String  font;

    private String  halo;

    private Integer haloWidth;

    private Integer size;

    private TextSymbolizer(SLDMapVisitor visitor, Layer layer, Style style, Node... nodes)
    {
      super(visitor, layer, style);
      this.nodes = nodes;
      this.color = style.getLabelColor();
      this.font = style.getLabelFont();
      this.halo = style.getLabelHalo();
      this.haloWidth = style.getLabelHaloWidth();
      this.size = style.getLabelSize();
    }

    @Override
    protected String getSymbolizerName()
    {
      return "TextSymbolizer";
    }

    @Override
    protected Node getSLD()
    {
      Node root = super.getSLD();

      node("Label").child(nodes).build(root);
      node("Font").child(css("font-size", size), css("font-family", font)).build(root);

      // Label Positioning
      if (this.visitor.currentLayer.getFeatureType().compareTo(FeatureType.POINT) == 0)
      {
        node("LabelPlacement").child(node("PointPlacement").child(node("AnchorPoint").child(node("AnchorPointX").text("0.0"), node("AnchorPointY").text("0.5")))).build(root);
      }
      else
      {
        node("LabelPlacement").child(node("PointPlacement").child(node("AnchorPoint").child(node("AnchorPointX").text("0.5"), node("AnchorPointY").text("0.5")))).build(root);
      }

      node("Halo").child(node("Radius").text(haloWidth), node("Fill").child(css("fill", halo))).build(root);
      node("Fill").child(css("fill", color)).build(root);

      // vendor options
      node("VendorOption").attr("name", "group").text(GeoserverProperties.getLabelGroup()).build(root);
      node("VendorOption").attr("name", "conflict-resolution").text(GeoserverProperties.getLabelConflictResolution()).build(root);
      node("VendorOption").attr("name", "spaceAround").text(GeoserverProperties.getLabelSpaceAround()).build(root);
      node("VendorOption").attr("name", "goodnessOfFit").text(GeoserverProperties.getLabelGoodnessOfFit()).build(root);
      node("VendorOption").attr("name", "autoWrap").text(GeoserverProperties.getLabelAutoWrap()).build(root);

      return root;
    }
  }

  /**
   * Builder class to simplify node creation.
   */
  private static class NodeBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    private Document doc;

    private Node     el;

    private NodeBuilder(SLDMapVisitor visitor, String ns, String node)
    {
      this.doc = visitor.doc;

      if (ns != null)
      {
        this.el = this.doc.createElement(ns + ":" + node);
      }
      else
      {
        this.el = this.doc.createElement(node);
      }
    }

    /**
     * This should only be called on Elements
     * 
     * @param name
     * @param value
     * @return
     */
    private NodeBuilder attr(String name, String value)
    {
      ( (Element) this.el ).setAttribute(name, value);
      return this;
    }

    private NodeBuilder text(Object o)
    {
      if (o != null)
      {
        text(o.toString());
      }

      return this;
    }

    private NodeBuilder text(String text)
    {
      if (text != null)
      {
        el.appendChild(this.doc.createTextNode(text));
      }

      return this;
    }
//    
//    private NodeBuilder cdata(String text)
//    {
//      if (text != null)
//      {
//        el.appendChild(this.doc.createCDATASection(text));
//      }
//      
//      return this;
//    }

    private Node build(Node parent)
    {
      parent.appendChild(this.el);
      return this.el;
    }

    private Node build()
    {
      return this.el;
    }

    /**
     * Creates new builder that appends children to the current node.
     * 
     * @param ns
     * @param name
     * @return
     */
    private NodeBuilder child(NodeBuilder... children)
    {
      for (NodeBuilder child : children)
      {
        this.el.appendChild(child.el);
      }

      return this;
    }

    private NodeBuilder child(Node... children)
    {
      for (Node child : children)
      {
        this.el.appendChild(child);
      }

      return this;
    }
  }

  /**
   * The containing SLD Document.
   */
  private Document                                          doc;

  private static final String                               OGC = "ogc";

  private Map                                               map;

  private Stack<Node>                                       parents;

  private java.util.Map<String, Node>                       layerToNodeMap;

  private java.util.Map<Node, LinkedList<DocumentFragment>> layers;

  private FeatureType                                       featureType;

  private java.util.Map<Condition, Node>                    conditions;

  private Node                                              root;

  private Layer                                             currentLayer;
  
  public SLDMapVisitor()
  {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = null;

    try
    {
      builder = factory.newDocumentBuilder();
    }
    catch (ParserConfigurationException e)
    {
      throw new ConversionException(e);
    }

    this.map = null;
    this.doc = builder.newDocument();
    this.doc.setStrictErrorChecking(false);
    this.doc.setXmlStandalone(true);

    this.layerToNodeMap = new HashMap<String, Node>();
    this.parents = new Stack<Node>();
    this.layers = new LinkedHashMap<Node, LinkedList<DocumentFragment>>();
    this.root = null;
    this.conditions = new HashMap<Condition, Node>();
  }

  private NodeBuilder node(String ns, String node)
  {
    return new NodeBuilder(this, ns, node);
  }

  /**
   * Utility method to create a CSS parameter node.
   * 
   * @param name
   * @param value
   * @return
   */
  private NodeBuilder css(String name, Object value)
  {
    return new NodeBuilder(this, null, "CssParameter").attr("name", name).text(value);
  }

  private NodeBuilder css(String name)
  {
    return new NodeBuilder(this, null, "CssParameter").attr("name", name);
  }

  private NodeBuilder node(String node)
  {
    return new NodeBuilder(this, null, node);
  }

  public String getSLD(Layer layer)
  {
    Node layerNode = this.layerToNodeMap.get(layer.getId());

    Node sld = this.root.cloneNode(true);

    List<DocumentFragment> frags = this.layers.get(layerNode);
    for (DocumentFragment frag : frags)
    {
      layerNode.appendChild(frag);
    }

    sld.appendChild(layerNode);

    return printNode(sld);
  }

  public List<String> getSLD()
  {
    List<String> slds = new LinkedList<String>();
    for (Layer layer : this.map.getLayers())
    {
      slds.add(this.getSLD(layer));
    }

    return slds;
  }

  private String printNode(Node node)
  {
    try
    {
      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer transformer = tf.newTransformer();
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
      transformer.setOutputProperty(OutputKeys.METHOD, "xml");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

      StringWriter writer = new StringWriter();
      transformer.transform(new DOMSource(node), new StreamResult(writer));
      return writer.toString();
    }
    catch (TransformerException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Override
  public void visit(Map map)
  {
    this.map = map;

    this.root = this.node("StyledLayerDescriptor").attr("xmlns", "http://www.opengis.net/sld").attr("xmlns:xlink", "http://www.w3.org/1999/xlink").attr("xmlns:sld", "http://www.opengis.net/sld").attr("xmlns:ogc", "http://www.opengis.net/ogc").attr("xmlns:gml", "http://www.opengis.net/gml").attr("version", "1.0.0").build(this.doc);

    for (Layer layer : map.getLayers())
    {
      layer.accepts(this);
    }
  }

  @Override
  public void visit(ThematicLayer layer)
  {
    this.root = this.node("StyledLayerDescriptor").attr("xmlns", "http://www.opengis.net/sld").attr("xmlns:xlink", "http://www.w3.org/1999/xlink").attr("xmlns:sld", "http://www.opengis.net/sld").attr("xmlns:ogc", "http://www.opengis.net/ogc").attr("xmlns:gml", "http://www.opengis.net/gml").attr("version", "1.0.0").build(this.doc);

    // We're starting a new layer so clear the prior structures
    this.parents.clear();
    this.conditions.clear();

    this.featureType = layer.getFeatureType();

    Node layerNode = this.node("NamedLayer").child(this.node("Name").text(layer.getName())).build();

    Node userStyle = this.node("UserStyle").child(node("Title").text(layer.getName())).build(layerNode);

    parents.push(userStyle);

    this.currentLayer = layer;
    layerToNodeMap.put(layer.getId(), layerNode);
    this.layers.put(layerNode, new LinkedList<DocumentFragment>());

    for (Style style : layer.getStyles())
    {
      style.accepts(this);
    }
  }

  @Override
  public void visit(ReferenceLayer layer)
  {
    
    this.root = this.node("StyledLayerDescriptor").attr("xmlns", "http://www.opengis.net/sld").attr("xmlns:xlink", "http://www.w3.org/1999/xlink").attr("xmlns:sld", "http://www.opengis.net/sld").attr("xmlns:ogc", "http://www.opengis.net/ogc").attr("xmlns:gml", "http://www.opengis.net/gml").attr("version", "1.0.0").build(this.doc);

    // We're starting a new layer so clear the prior structures
    this.parents.clear();
    this.conditions.clear();

    this.featureType = layer.getFeatureType();

    Node layerNode = this.node("NamedLayer").child(this.node("Name").text(layer.getName())).build();

    Node userStyle = this.node("UserStyle").child(node("Title").text(layer.getName())).build(layerNode);

    parents.push(userStyle);

    this.currentLayer = layer;
    layerToNodeMap.put(layer.getId(), layerNode);
    this.layers.put(layerNode, new LinkedList<DocumentFragment>());

    for (Style style : layer.getStyles())
    {
      style.accepts(this);
    }
  }

  /**
   * Each Style is translated into a custom SLD Rule.
   */
  @Override
  public void visit(Style style)
  {
//    DocumentFragment rulesFragment = this.doc.createDocumentFragment();
//
//    Node rule = this.node("Rule").child(this.node("Name").text(style.getName())).build();
//
//    if (this.virtual)
//    {
//      Node fts = this.node("FeatureTypeStyle").child(rule).build();
//      rulesFragment.appendChild(fts);
//    }
//    else
//    {
//      rulesFragment.appendChild(rule);
//    }

    Symbolizer symbolizer;
    if (this.featureType == FeatureType.POINT)
    {
      symbolizer = new PointSymbolizer(this, this.currentLayer, style);
    }
    else if (this.featureType == FeatureType.POLYGON)
    {
      symbolizer = new PolygonSymbolizer(this, this.currentLayer, style);
    }
    else if (this.featureType == FeatureType.LINE)
    {
      symbolizer = new LineSymbolizer(this, this.currentLayer, style);
    }
    // TODO text symbolizer
    else
    {
      throw new ProgrammingErrorException("Geometry type [" + this.featureType + "] is not supported for SLD generation.");
    }

    this.parents.pop().appendChild(symbolizer.getSLD());
  }

  @Override
  public void visit(ThematicStyle style)
  {
    Symbolizer symbolizer;
    if (this.featureType == FeatureType.POINT)
    {
      symbolizer = new PointSymbolizer(this, this.currentLayer, style);
    }
    else if (this.featureType == FeatureType.POLYGON)
    {
      symbolizer = new PolygonSymbolizer(this, this.currentLayer, style);
    }
    else if (this.featureType == FeatureType.LINE)
    {
      symbolizer = new LineSymbolizer(this, this.currentLayer, style);
    }
    // TODO text symbolizer
    else
    {
      throw new ProgrammingErrorException("Geometry type [" + this.featureType + "] is not supported for SLD generation.");
    }

    // append the rule to user styles
    this.parents.pop().appendChild(symbolizer.getSLD());
  }

  @Override
  public void visit(Or component)
  {
    Node or = this.node(OGC, "Or").build(this.parents.peek());

    this.parents.push(or);

    component.getLeftCondition().accepts(this);
    component.getRightCondition().accepts(this);

    this.parents.pop();
  }

  @Override
  public void visit(And component)
  {
    Node and = this.node(OGC, "And").build(this.parents.peek());

    this.parents.push(and);

    component.getLeftCondition().accepts(this);
    component.getRightCondition().accepts(this);

    this.parents.pop();
  }

  /**
   * Utility method that writes a Primitive condition.
   * 
   * @param name
   * @param cond
   */
  private void primitive(String name, Primitive cond)
  {
    Node parent = this.parents.peek();

    node(OGC, name).child(node(OGC, "PropertyName").text(cond.getThematicLayer().getAttribute()), node(OGC, "Literal").text(cond.getValue())).build(parent);
  }

  @Override
  public void visit(Equal component)
  {
    primitive("PropertyIsEqualTo", component);
  }

  @Override
  public void visit(NotEqual component)
  {
    // TODO
  }

  @Override
  public void visit(GreaterThan component)
  {
    primitive("PropertyIsGreaterThan", component);
  }

  @Override
  public void visit(GreaterThanOrEqual component)
  {
    primitive("PropertyIsGreaterThanOrEqualTo", component);
  }

  @Override
  public void visit(LessThan component)
  {
    primitive("PropertyIsLessThan", component);
  }

  @Override
  public void visit(LessThanOrEqual component)
  {
    primitive("PropertyIsLessThanOrEqualTo", component);
  }

  @Override
  public void visit(IsBetween cond)
  {
    node("PropertyIsBetween").child(node("PropertyName").text(cond.getThematicLayer().getAttribute()), node("LowerBoundary").text(cond.getLowerBound()), node("UpperBoundary").text(cond.getUpperBound())).build(this.parents.peek());
  }

  @Override
  public void visit(IsLike component)
  {
    primitive("PropertyIsLike", component);
  }

  @Override
  public void visit(IsNull component)
  {
    node("PropertyIsNull").child(node("PropertyName").text(component.getThematicLayer().getAttribute())).build(this.parents.peek());
  }

  @Override
  public void visit(Gradient component)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void visit(Category component)
  {
    // TODO Auto-generated method stub

  }
  
  public static HashMap<Integer, Color> interpolateColor(int numCategories, FeatureStrategy featureStrategy, Style style)
  {
    HashMap<Integer, Color> colorRGBList = new HashMap<Integer, Color>();
    
    if(style instanceof ThematicStyle)
    {
      ThematicStyle tStyle = (ThematicStyle) style;
      String minFill = null;
      String maxFill = null;
      
      if (featureStrategy == FeatureStrategy.GRADIENTPOLYGON)
      {
        minFill = tStyle.getGradientPolygonMinFill();
        maxFill = tStyle.getGradientPolygonMaxFill();
        
      }
      else if (featureStrategy == FeatureStrategy.GRADIENTPOINT)
      {
        minFill = tStyle.getGradientPointMinFill();
        maxFill = tStyle.getGradientPointMaxFill();
      }

      Color minFillRGB = Color.decode(minFill);
      Color maxFillRGB = Color.decode(maxFill);
      colorRGBList.put(1, minFillRGB);
      colorRGBList.put(numCategories, maxFillRGB);

      // RGB color values
      int r1 = minFillRGB.getRed();
      int g1 = minFillRGB.getGreen();
      int b1 = minFillRGB.getBlue();

      int r2 = maxFillRGB.getRed();
      int g2 = maxFillRGB.getGreen();
      int b2 = maxFillRGB.getBlue();

      double stepIncrease = ( 100.0 / numCategories ) / 100.0;
      double stepVal = ( 100.0 / numCategories ) / 100.0;

      // Build category colors between min/max category values.
      // If only 1 category the color value will be the max color set by the user
      for (int i = 0; i < numCategories; i++)
      {
        int red = (int) ( r1 + ( stepVal * ( r2 - r1 ) ) );
        int green = (int) ( g1 + ( stepVal * ( g2 - g1 ) ) );
        int blue = (int) ( b1 + ( stepVal * ( b2 - b1 ) ) );
        Color newColor = new Color(red, green, blue);
        colorRGBList.put(i + 1, newColor);
        stepVal = stepVal + stepIncrease; // reseting stepVal to the next color increment value (i.e. .20 becomes .40)
      }
    }
    
    return colorRGBList;
  }

}
