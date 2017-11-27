package net.geoprism;

@com.runwaysdk.business.ClassSignature(hash = -307824719)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to RoleView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class RoleViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public RoleViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public RoleViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return net.geoprism.RoleView.CLASS;
  }
  public com.runwaysdk.query.SelectableBoolean getAssigned()
  {
    return getAssigned(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getAssigned(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(net.geoprism.RoleView.ASSIGNED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getAssigned(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(net.geoprism.RoleView.ASSIGNED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public com.runwaysdk.query.SelectableChar getDisplayLabel(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.RoleView.DISPLAYLABEL, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getDisplayLabel(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.RoleView.DISPLAYLABEL, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getGroupName()
  {
    return getGroupName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getGroupName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.RoleView.GROUPNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getGroupName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.RoleView.GROUPNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.RoleView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.RoleView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getRoleId()
  {
    return getRoleId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getRoleId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.RoleView.ROLEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getRoleId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(net.geoprism.RoleView.ROLEID, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends RoleView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<RoleView>(this.getMdClassIF(), valueIterator);
  }

}
