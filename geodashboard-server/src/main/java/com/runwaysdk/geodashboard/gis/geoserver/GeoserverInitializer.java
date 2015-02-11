package com.runwaysdk.geodashboard.gis.geoserver;

import it.geosolutions.geoserver.rest.GeoServerRESTReader;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.geodashboard.SessionParameterFacade;
import com.runwaysdk.geodashboard.gis.persist.DashboardLayer;
import com.runwaysdk.geodashboard.util.Iterables;
import com.runwaysdk.geodashboard.util.Predicate;
import com.runwaysdk.session.Request;
import com.runwaysdk.session.SessionFacade;

public class GeoserverInitializer implements UncaughtExceptionHandler, Reloadable
{
  private static boolean                        initialized = false;

  private static final ReentrantLock            lock        = new ReentrantLock();

  private static final Log                      initLog     = LogFactory.getLog(GeoserverInitializer.class);

  private static final ScheduledExecutorService scheduler   = Executors.newScheduledThreadPool(1);

  public static class SessionPredicate implements Predicate<String>, Reloadable
  {

    @Override
    public boolean evaulate(String viewName)
    {
      // We must remove the viewName from the list if the session is still active.
      // Thus it will not be in the list of views to delete.
      String sessionId = DashboardLayer.getSessionId(viewName);

      if (sessionId != null)
      {
        return SessionFacade.containsSession(sessionId);
      }

      // By default remove all viewName
      return true;
    }

  }

  public static class CleanupRunnable implements Runnable, Reloadable
  {
    @Override
    @Request
    public void run()
    {
      try
      {
        runInTransaction();
      }
      catch (Exception e)
      {
        initLog.error(e.getMessage(), e);
      }
    }

    @Transaction
    private void runInTransaction()
    {
      List<String> viewNames = Database.getViewsByPrefix(DashboardLayer.DB_VIEW_PREFIX);

      new Iterables<String>().remove(viewNames, new SessionPredicate());

      for (String viewName : viewNames)
      {
        // First remove the geoserver layer
        GeoserverFacade.removeLayer(viewName);

        // Clear the session map
        String sessionId = DashboardLayer.getSessionId(viewName);
        SessionParameterFacade.clear(sessionId);
      }

      // Second delete the database views
      Database.dropViews(viewNames);

    }
  }

  public static class CheckThread implements Runnable, Reloadable
  {

    private static final Log log = LogFactory.getLog(CheckThread.class);

    public CheckThread()
    {
      super();
    }

    @Override
    public void run()
    {
      GeoServerRESTReader reader = GeoserverProperties.getReader();

      while (true)
      {
        try
        {
          lock.lock();

          log.debug("Attempting to check existence of geoserver");

          if (reader.existGeoserver())
          {
            log.debug("Geoserver available.");

            GeoserverFacade.publishWorkspace();
            GeoserverFacade.publishStore();

            initialized = true;
            log.debug("Geoserver initialized.");
            return; // we are done here
          }
          else
          {
            try
            {
              log.debug("Waiting for Geoserver to start.");
              Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
              // allow another try
              log.warn(e);
            }
          }
        }
        catch (Throwable t)
        {
          // we couldn't hit the application correctly, so log the error
          // and quit the loop to avoid excessive logging
          log.error("Unable to start the application.", t);
          return;
        }
        finally
        {
          lock.unlock();
        }
      }
    }

  }

  public static boolean isInitialized()
  {
    try
    {
      lock.lock();

      return initialized;
    }
    finally
    {
      lock.unlock();
    }
  }

  public static void setup()
  {
    GeoserverInitializer init = new GeoserverInitializer();

    try
    {
      initLog.debug("Attempting to initialize context.");

      // create another thread to avoid blocking the one starting the webapps.
      Thread t = new Thread(new CheckThread());
      t.setUncaughtExceptionHandler(init);
      t.setDaemon(true);
      t.start();

      initLog.debug("Context initialized...[" + GeoserverInitializer.class + "] started.");
    }
    catch (Throwable t)
    {
      initLog.error("Could not initialize context.", t);
    }

    // Start the mapping database view cleanup thread
    scheduler.scheduleWithFixedDelay(new CleanupRunnable(), 1, 5, TimeUnit.MINUTES);
  }

  /**
   * Log the error.
   */
  @Override
  public void uncaughtException(Thread t, Throwable e)
  {
    initLog.error(t, e);
  }

  public static void shutdown()
  {
    // Shutdown the mapping database view cleanup thread
    scheduler.shutdownNow();
  }
}