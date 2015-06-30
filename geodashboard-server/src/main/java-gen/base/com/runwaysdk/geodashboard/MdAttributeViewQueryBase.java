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
package com.runwaysdk.geodashboard;

@com.runwaysdk.business.ClassSignature(hash = -286377747)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MdAttributeView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class MdAttributeViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public MdAttributeViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public MdAttributeViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return com.runwaysdk.geodashboard.MdAttributeView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getAttributeName()
  {
    return getAttributeName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getAttributeName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(com.runwaysdk.geodashboard.MdAttributeView.ATTRIBUTENAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getAttributeName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(com.runwaysdk.geodashboard.MdAttributeView.ATTRIBUTENAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getAttributeType()
  {
    return getAttributeType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getAttributeType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(com.runwaysdk.geodashboard.MdAttributeView.ATTRIBUTETYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getAttributeType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(com.runwaysdk.geodashboard.MdAttributeView.ATTRIBUTETYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public com.runwaysdk.query.SelectableChar getDisplayLabel(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(com.runwaysdk.geodashboard.MdAttributeView.DISPLAYLABEL, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getDisplayLabel(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(com.runwaysdk.geodashboard.MdAttributeView.DISPLAYLABEL, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(com.runwaysdk.geodashboard.MdAttributeView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(com.runwaysdk.geodashboard.MdAttributeView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getMdAttributeId()
  {
    return getMdAttributeId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getMdAttributeId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(com.runwaysdk.geodashboard.MdAttributeView.MDATTRIBUTEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getMdAttributeId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(com.runwaysdk.geodashboard.MdAttributeView.MDATTRIBUTEID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getMdClassId()
  {
    return getMdClassId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getMdClassId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(com.runwaysdk.geodashboard.MdAttributeView.MDCLASSID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getMdClassId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(com.runwaysdk.geodashboard.MdAttributeView.MDCLASSID, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends MdAttributeView> getIterator()
  {
    com.runwaysdk.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.runwaysdk.query.ViewIterator<MdAttributeView>(this.getMdClassIF(), valueIterator);
  }

}
