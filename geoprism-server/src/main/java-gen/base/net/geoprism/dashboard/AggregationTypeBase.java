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
package net.geoprism.dashboard;

@com.runwaysdk.business.ClassSignature(hash = -1048083767)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregationType.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class AggregationTypeBase extends com.runwaysdk.system.EnumerationMaster implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "net.geoprism.dashboard.AggregationType";
  private static final long serialVersionUID = -1048083767;
  
  public AggregationTypeBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AggregationTypeQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    AggregationTypeQuery query = new AggregationTypeQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static AggregationType get(String id)
  {
    return (AggregationType) com.runwaysdk.business.Business.get(id);
  }
  
  public static AggregationType getByKey(String key)
  {
    return (AggregationType) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static AggregationType getEnumeration(String enumName)
  {
    return (AggregationType) com.runwaysdk.business.Business.getEnumeration(net.geoprism.dashboard.AggregationType.CLASS ,enumName);
  }
  
  public static AggregationType lock(java.lang.String id)
  {
    AggregationType _instance = AggregationType.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static AggregationType unlock(java.lang.String id)
  {
    AggregationType _instance = AggregationType.get(id);
    _instance.unlock();
    
    return _instance;
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
