package net.geoprism.data.etl.excel;

@com.runwaysdk.business.ClassSignature(hash = 1048044283)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DataUploaderImportJob.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DataUploaderImportJobBase extends com.runwaysdk.system.scheduler.ExecutableJob implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "net.geoprism.data.etl.excel.DataUploaderImportJob";
  private static final long serialVersionUID = 1048044283;
  
  public DataUploaderImportJobBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static DataUploaderImportJobQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    DataUploaderImportJobQuery query = new DataUploaderImportJobQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static DataUploaderImportJob get(String id)
  {
    return (DataUploaderImportJob) com.runwaysdk.business.Business.get(id);
  }
  
  public static DataUploaderImportJob getByKey(String key)
  {
    return (DataUploaderImportJob) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public void executeAuthenticated()
  {
    String msg = "This method should never be invoked.  It should be overwritten in net.geoprism.data.etl.excel.DataUploaderImportJob.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void executeAuthenticated(java.lang.String id)
  {
    DataUploaderImportJob _instance = DataUploaderImportJob.get(id);
    _instance.executeAuthenticated();
  }
  
  public static DataUploaderImportJob lock(java.lang.String id)
  {
    DataUploaderImportJob _instance = DataUploaderImportJob.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static DataUploaderImportJob unlock(java.lang.String id)
  {
    DataUploaderImportJob _instance = DataUploaderImportJob.get(id);
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
