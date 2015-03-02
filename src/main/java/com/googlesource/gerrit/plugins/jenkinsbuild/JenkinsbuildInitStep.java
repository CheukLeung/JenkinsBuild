/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesource.gerrit.plugins.jenkinsbuild;

import com.google.gerrit.extensions.annotations.PluginName;
import com.google.gerrit.pgm.init.AllProjectsConfig;
import com.google.gerrit.pgm.init.InitStep;
import com.google.gerrit.pgm.util.ConsoleUI;
import javax.inject.Inject;
import org.eclipse.jgit.lib.Config;

/**
 *
 * @author cheuk
 */
public class JenkinsbuildInitStep implements InitStep {

  private final String pluginName;
  private final ConsoleUI ui;
  private final AllProjectsConfig allProjectsConfig;

  @Inject
  public JenkinsbuildInitStep(@PluginName String pluginName, ConsoleUI ui,
      AllProjectsConfig allProjectsConfig) {
    this.pluginName = pluginName;
    this.ui = ui;
    this.allProjectsConfig = allProjectsConfig;
  }

  @Override
  public void run() throws Exception {
  }

  @Override
  public void postRun() throws Exception {
    ui.message("\n");
    ui.header(pluginName + " Integration");
    boolean enabled = ui.yesno(true, "By default enabled for all projects");
    Config cfg = allProjectsConfig.load();
    if (enabled) {
      cfg.setBoolean("plugin", pluginName, "enabled", enabled);
    } else {
      cfg.unset("plugin", pluginName, "enabled");
    }
    allProjectsConfig.save(pluginName, "Initialize " + pluginName + " Integration");
  }

}
