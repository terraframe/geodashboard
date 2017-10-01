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
package net.geoprism.data.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingProgressMonitor implements ProgressMonitorIF
{
  private static Logger logger = LoggerFactory.getLogger(ImportRunnable.class);
  
  private DataImportState state;
  
  private double currentRow;
  
  private String filename;
  
  public LoggingProgressMonitor(String filename)
  {
    this.currentRow = 0;
    this.setFilename(filename);
    this.setState(DataImportState.INITIAL);
  }
  
  public void setFilename(String filename)
  {
    this.filename = filename;
  }
  
  public void setState(DataImportState state)
  {
    this.state = state;
    
    logger.info("Spreadsheet importer entering state [" + state.toString() + "] on file [" + filename + "].");
  }
  
  public DataImportState getState()
  {
    return this.state;
  }
  
  public void setCurrentRow(double rowNum)
  {
    this.currentRow = rowNum;
    
    if (rowNum % 50 == 0)
    {
      logger.info("Spreadsheet importer processing row [" + rowNum + "]");
    }
  }
  
  public double getCurrentRow()
  {
    return this.currentRow;
  }
}