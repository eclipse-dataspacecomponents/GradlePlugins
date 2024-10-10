/*
 * Copyright (c) 2024 Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * SPDX-License-Identifier: Apache-2.0
 *
 * Contributors:
 *       Bayerische Motoren Werke Aktiengesellschaft (BMW AG) - initial API and implementation
 *
 */

package org.eclipse.edc.plugins.autodoc.tasks;

import org.gradle.api.artifacts.Dependency;
import org.gradle.api.internal.artifacts.dependencies.DefaultProjectDependency;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;

import static java.io.InputStream.nullInputStream;

public class ResolveManifestTask extends AbstractManifestResolveTask {

    public static final String NAME = "resolveManifests";


    @Override
    protected InputStream resolveManifest(DependencySource autodocManifest) {
        var uri = autodocManifest.uri();
        try {
            var file = new File(uri);
            if (file.exists()) {
                return new FileInputStream(file);
            }

            getLogger().info("File {} does not exist", file);
            return nullInputStream();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Optional<DependencySource> createSource(Dependency dependency) {
        if (dependency instanceof DefaultProjectDependency localDepdendency) {
            var manifestFile = localDepdendency.getDependencyProject().getLayout().getBuildDirectory().file("edc.json");
            if (manifestFile.isPresent()) {
                return Optional.of(new DependencySource(localDepdendency, manifestFile.get().getAsFile().toURI(), MANIFEST_CLASSIFIER, MANIFEST_TYPE));
            } else {
                getLogger().debug("No manifest file found for dependency {}", dependency);
            }

        } else {
            getLogger().debug("Dependency {} is not a DefaultProjectDependency", dependency);
        }

        return Optional.empty();
    }

}
