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

package org.eclipse.dataspaceconnector.autodoc.core.processor.compiler;

import java.util.List;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.util.SimpleAnnotationValueVisitor9;

/**
 * Returns the value of an annotation attribute as a collection of strings.
 */
class AnnotationStringValuesResolver extends SimpleAnnotationValueVisitor9<List<String>, List<String>> {

    @Override
    public List<String> visitString(String value, List<String> values) {
        values.add(value);
        return values;
    }

    @Override
    public List<String> visitArray(List<? extends AnnotationValue> vals, List<String> values) {
        vals.forEach(annotationValue -> annotationValue.accept(this, values));
        return values;
    }


}
