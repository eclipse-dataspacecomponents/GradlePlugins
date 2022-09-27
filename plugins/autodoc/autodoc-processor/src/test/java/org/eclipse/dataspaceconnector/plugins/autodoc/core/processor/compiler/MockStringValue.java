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

package org.eclipse.dataspaceconnector.plugins.autodoc.core.processor.compiler;

import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;

class MockStringValue implements AnnotationValue {
    private final String value;

    MockStringValue(String value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public <R, P> R accept(AnnotationValueVisitor<R, P> v, P p) {
        return v.visitString(value, p);
    }
}
