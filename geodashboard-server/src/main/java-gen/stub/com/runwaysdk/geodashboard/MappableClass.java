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

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdClass;

public class MappableClass extends MappableClassBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -931571965;

  public MappableClass()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    MdClass mdClass = this.getWrappedMdClass();

    if (mdClass != null)
    {
      return mdClass.getKey();
    }

    return super.buildKey();
  }

  public static MappableClass getMappableClass(String type)
  {
    MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(type);

    return getMappableClass(mdClass);
  }

  public static MappableClass getMappableClass(MdClassDAOIF _mdClass)
  {
    MdClass mdClass = MdClass.get(_mdClass.getId());

    MappableClassQuery query = new MappableClassQuery(new QueryFactory());
    query.WHERE(query.getWrappedMdClass().EQ(mdClass));

    OIterator<? extends MappableClass> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }

  }

  public static MappableClass[] getAll()
  {
    MappableClassQuery query = new MappableClassQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getWrappedMdClass().getDisplayLabel().localize());

    OIterator<? extends MappableClass> iterator = query.getIterator();

    try
    {
      List<? extends MappableClass> classes = iterator.getAll();

      return classes.toArray(new MappableClass[classes.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  public JSONObject toJSON() throws JSONException
  {
    MdClassDAOIF mdClass = (MdClassDAOIF) MdClassDAO.get(this.getWrappedMdClassId());
    String label = mdClass.getDisplayLabel(Session.getCurrentLocale());

    JSONObject object = new JSONObject();
    object.put("label", label);
    object.put("id", this.getId());
    object.put("type", mdClass.getKey());
    object.put("selected", false);
    object.put("attributes", this.getAttributeJSON(mdClass));

    return object;
  }

  private JSONArray getAttributeJSON(MdClassDAOIF mdClass) throws JSONException
  {
    JSONArray array = new JSONArray();

    List<? extends MdAttributeDAOIF> mdAttributes = mdClass.getAllDefinedMdAttributes();

    for (MdAttributeDAOIF mdAttribute : mdAttributes)
    {
      if (!mdAttribute.isSystem())
      {
        JSONObject object = new JSONObject();
        object.put("label", mdAttribute.getDisplayLabel(Session.getCurrentLocale()));
        object.put("id", mdAttribute.getId());
        object.put("selected", false);

        array.put(object);
      }
    }

    return array;
  }
}
