/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesource.gerrit.plugins.jenkinsbuild;

import com.google.gerrit.extensions.restapi.RestModifyView;
import com.google.gerrit.extensions.webui.UiAction;
import com.google.gerrit.server.CurrentUser;
import com.google.gerrit.server.IdentifiedUser;
import com.google.gerrit.server.change.RevisionResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 *
 * @author cheuk
 */
class JenkinsbuildRevisionAction implements UiAction<RevisionResource>,
    RestModifyView<RevisionResource, JenkinsbuildRevisionAction.Input>{

  private final Provider<CurrentUser> user;

  static class Input {
    String label;
  }

  @Inject
  JenkinsbuildRevisionAction(Provider<CurrentUser> user) {
    this.user = user;
  }

  @Override
  public String apply(RevisionResource rev, Input input) {
    return String.format(
            "Building change %s, patch set %s on Jenkins with label %s",
            rev.getChange().getId().toString(),
            rev.getPatchSet().getPatchSetId(),
            input.label);
  }

  @Override
  public Description getDescription(RevisionResource r) {
    return new Description()
        .setLabel("Build on Jenkins")
        .setTitle("Build on Jenkins")
        .setVisible(user.get() instanceof IdentifiedUser);
  }
}
