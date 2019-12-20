/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Geoprism(tm).
 *
 * Geoprism(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Geoprism(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Geoprism(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package net.geoprism.account;

@com.runwaysdk.business.ClassSignature(hash = -1614949638)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoprismUserView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class GeoprismUserViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery

{

  public GeoprismUserViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public GeoprismUserViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return net.geoprism.account.GeoprismUserView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getEmail()
  {
    return getEmail(null);

  }
 
  public com.runwaysdk.query.SelectableChar getEmail(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.account.GeoprismUserView.EMAIL, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getEmail(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.account.GeoprismUserView.EMAIL, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getFirstName()
  {
    return getFirstName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getFirstName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.account.GeoprismUserView.FIRSTNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getFirstName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.account.GeoprismUserView.FIRSTNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getInactive()
  {
    return getInactive(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getInactive(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(net.geoprism.account.GeoprismUserView.INACTIVE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getInactive(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(net.geoprism.account.GeoprismUserView.INACTIVE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getLastName()
  {
    return getLastName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getLastName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.account.GeoprismUserView.LASTNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getLastName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.account.GeoprismUserView.LASTNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getNewInstance()
  {
    return getNewInstance(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getNewInstance(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(net.geoprism.account.GeoprismUserView.NEWINSTANCE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getNewInstance(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(net.geoprism.account.GeoprismUserView.NEWINSTANCE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableUUID getOid()
  {
    return getOid(null);

  }
 
  public com.runwaysdk.query.SelectableUUID getOid(String alias)
  {
    return (com.runwaysdk.query.SelectableUUID)this.getSelectable(net.geoprism.account.GeoprismUserView.OID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableUUID getOid(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableUUID)this.getSelectable(net.geoprism.account.GeoprismUserView.OID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getPhoneNumber()
  {
    return getPhoneNumber(null);

  }
 
  public com.runwaysdk.query.SelectableChar getPhoneNumber(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.account.GeoprismUserView.PHONENUMBER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getPhoneNumber(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.account.GeoprismUserView.PHONENUMBER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getRoles()
  {
    return getRoles(null);

  }
 
  public com.runwaysdk.query.SelectableChar getRoles(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.account.GeoprismUserView.ROLES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getRoles(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.account.GeoprismUserView.ROLES, alias, displayLabel);

  }
 
  public net.geoprism.GeoprismUserQuery.GeoprismUserQueryReferenceIF getUser()
  {
    return getUser(null);

  }
 
  public net.geoprism.GeoprismUserQuery.GeoprismUserQueryReferenceIF getUser(String alias)
  {

    return (net.geoprism.GeoprismUserQuery.GeoprismUserQueryReferenceIF)this.getSelectable(net.geoprism.account.GeoprismUserView.USER, alias, null);

  }
 
  public net.geoprism.GeoprismUserQuery.GeoprismUserQueryReferenceIF getUser(String alias, String displayLabel)
  {

    return (net.geoprism.GeoprismUserQuery.GeoprismUserQueryReferenceIF)this.getSelectable(net.geoprism.account.GeoprismUserView.USER, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getUsername()
  {
    return getUsername(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUsername(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.account.GeoprismUserView.USERNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUsername(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.account.GeoprismUserView.USERNAME, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends GeoprismUserView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<GeoprismUserView>(this.getMdClassIF(), valueIterator);
  }

}
