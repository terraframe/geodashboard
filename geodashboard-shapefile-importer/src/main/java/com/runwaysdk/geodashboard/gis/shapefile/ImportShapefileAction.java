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
package com.runwaysdk.geodashboard.gis.shapefile;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.geodashboard.gis.LocalizedWizardDialog;
import com.runwaysdk.geodashboard.gis.Localizer;
import com.runwaysdk.system.gis.geo.Universal;

public class ImportShapefileAction extends Action implements Reloadable
{
  private Universal[] universal;

  public ImportShapefileAction(Universal[] universal)
  {
    this.universal = universal;

    this.setText(Localizer.getMessage("IMPORT_SHAPE_FILE"));
  }

  @Override
  public void run()
  {
    Shell shell = Display.getCurrent().getActiveShell();
    ShapeFileWizard wizard = new ShapeFileWizard(universal);

    LocalizedWizardDialog dialog = new LocalizedWizardDialog(shell, wizard);
    dialog.setBlockOnOpen(true);
    dialog.open();
  }
}
