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
package net.geoprism.context;

import java.io.File;

import com.runwaysdk.business.ontology.CompositeStrategy;
import com.runwaysdk.business.ontology.OntologyStrategyBuilderIF;
import com.runwaysdk.business.ontology.OntologyStrategyFactory;
import com.runwaysdk.business.ontology.OntologyStrategyIF;
import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.dataaccess.transaction.Transaction;

import com.runwaysdk.session.Request;
import com.runwaysdk.system.gis.geo.GeoEntity;
import com.runwaysdk.system.gis.geo.LocatedIn;
import com.runwaysdk.system.metadata.ontology.DatabaseAllPathsStrategy;
import com.runwaysdk.system.metadata.ontology.SolrOntolgyStrategy;

import net.geoprism.GeoprismPatcher;
import net.geoprism.GeoprismPatcherIF;
import net.geoprism.PluginUtil;

public class PatchingContextListener implements ServerContextListener
{
  protected GeoprismPatcherIF patcher;

  @Override
  public void initialize()
  {
    patcher = PluginUtil.getPatcher();
    patcher.initialize(new File(DeployProperties.getDeployBin(), "metadata"));
  }

  @Override
  public void startup()
  {
    if (patcher == null)
    {
      initialize();
    }

    patcher.run();
  }

  @Override
  public void shutdown()
  {
  }
}
