/*
 *  Copyright 2023 Red Hat
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.patternfly.component.icon;

import org.jboss.elemento.TypedBuilder;
import org.patternfly.component.ComponentType;
import org.patternfly.component.SubComponent;

import elemental2.dom.HTMLElement;

abstract class IconSubComponent<E extends HTMLElement, B extends TypedBuilder<E, B>> extends SubComponent<E, B> {

    IconSubComponent(String name, E element) {
        super(ComponentType.Icon, name, element);
    }
}
