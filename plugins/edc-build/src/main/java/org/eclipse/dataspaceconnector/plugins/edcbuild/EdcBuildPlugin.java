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

import org.eclipse.dataspaceconnector.plugins.edcbuild.extensions.BuildExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import static java.util.List.of;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.checkstyle;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.defaultDependencies;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.dependencyAnalysis;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.jacoco;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.jar;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.java;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.mavenPom;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.mavenPublishing;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.nexusPublishing;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.repositories;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.rootBuildScript;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.signing;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.swagger;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.tests;

/**
 * Adds (opinionated) conventions (=configuration) for various plugins.
 */
public class EdcBuildPlugin implements Plugin<Project> {
    @Override
    public void apply(Project target) {

        // apply all plugins
        target.getPlugins().apply(EdcBuildBasePlugin.class);

        // register the extension(s)
        target.getExtensions().create("edcBuild", BuildExtension.class, target.getObjects());

        // apply the conventions
        of(
                rootBuildScript(),
                java(),
                repositories(),
                defaultDependencies(),
                checkstyle(),
                mavenPublishing(),
                signing(),
                mavenPom(),
                jacoco(),
                dependencyAnalysis(),
                tests(),
                jar(),
                nexusPublishing(),
                swagger()
        ).forEach(c -> c.apply(target));

    }
}
