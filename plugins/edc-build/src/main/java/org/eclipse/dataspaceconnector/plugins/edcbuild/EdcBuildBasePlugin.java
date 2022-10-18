/*
 *  Copyright (c) 2022 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *
 */

package org.eclipse.dataspaceconnector.plugins.edcbuild;

import org.eclipse.dataspaceconnector.plugins.autodoc.AutodocPlugin;
import org.eclipse.dataspaceconnector.plugins.modulenames.ModuleNamesPlugin;
import org.eclipse.dataspaceconnector.plugins.testsummary.TestSummaryPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.quality.CheckstylePlugin;
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin;

/**
 * Defines the capabilities of the EDC build as specified in the Gradle Documentation
 *
 * @see <a href="https://docs.gradle.org/current/userguide/designing_gradle_plugins.html">Gradle Documentation</a>
 */
public class EdcBuildBasePlugin implements Plugin<Project> {
    private static void defineCapabilities(Project target) {
        target.getPluginManager().apply(ModuleNamesPlugin.class);
        target.getPluginManager().apply(AutodocPlugin.class);
        target.getPluginManager().apply(CheckstylePlugin.class);
        target.getPluginManager().apply(MavenPublishPlugin.class);
        target.getPluginManager().apply(JavaPlugin.class);
        target.getPluginManager().apply(TestSummaryPlugin.class);
    }

    @Override
    public void apply(Project target) {
        defineCapabilities(target);
    }

}