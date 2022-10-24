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

package org.eclipse.dataspaceconnector.plugins.edcbuild.conventions;

import org.gradle.api.Project;

import java.nio.file.Path;

import static java.util.List.of;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.openApiMerger;
import static org.eclipse.dataspaceconnector.plugins.edcbuild.conventions.Conventions.swaggerGenerator;

/**
 * Wrapper convention for the swagger generator and the openapi merger.
 *
 * @see SwaggerGeneratorConvention
 * @see OpenApiMergerConvention
 */
class SwaggerConvention implements EdcConvention {
    public static Path defaultOutputDirectory(Project project) {
        return Path.of(project.getRootProject().getProjectDir().getAbsolutePath(), "resources/openapi/yaml");
    }

    @Override
    public void apply(Project target) {
        of(
                swaggerGenerator(),
                openApiMerger()
        ).forEach(c -> c.apply(target));
    }
}
