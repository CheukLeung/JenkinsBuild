// Copyright (C) 2014 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.googlesource.gerrit.plugins.jenkinsbuild;

import com.google.gerrit.extensions.annotations.Exports;
import com.google.gerrit.server.config.ProjectConfigEntry;
import com.google.inject.AbstractModule;

class Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(ProjectConfigEntry.class)
        .annotatedWith(Exports.named("enabled"))
        .toInstance(new ProjectConfigEntry(
                "Enable JenkinsBuild",
                true)
        );
    bind(ProjectConfigEntry.class)
        .annotatedWith(Exports.named("jenkins"))
        .toInstance(new ProjectConfigEntry(
                "Jenkins server address",
                "localhost")
        );
    bind(ProjectConfigEntry.class)
        .annotatedWith(Exports.named("port"))
        .toInstance(new ProjectConfigEntry(
                "Jenkins server port",
                "8080")
        );
    bind(ProjectConfigEntry.class)
        .annotatedWith(Exports.named("user"))
        .toInstance(new ProjectConfigEntry(
                "Account to access Jenkins",
                "gerrit")
        );
    bind(ProjectConfigEntry.class)
        .annotatedWith(Exports.named("ssh-key"))
        .toInstance(new ProjectConfigEntry(
                "SSH key file for the user to access Jenkins",
                "/home/gerrit2/.ssh/id_rsa")
        );
  }
}
