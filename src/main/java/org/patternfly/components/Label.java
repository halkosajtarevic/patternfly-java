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
package org.patternfly.components;

import org.jboss.elemento.HtmlContent;
import org.patternfly.resources.Constants;

import elemental2.dom.HTMLElement;

import static org.jboss.elemento.Elements.span;
import static org.patternfly.resources.CSS.component;
import static org.patternfly.resources.CSS.modifier;
import static org.patternfly.resources.Constants.label;

/**
 * PatternFly label component.
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/core/components/label/">https://www.patternfly.org/v4/documentation/core/components/label</a>
 */
public class Label extends BaseComponent<HTMLElement, Label> implements HtmlContent<HTMLElement, Label> {

    // ------------------------------------------------------ factory methods

    public static Label label(String text) {
        return new Label(text);
    }

    public static Label label(String text, boolean compact) {
        return new Label(text, compact);
    }

    // ------------------------------------------------------ instance

    Label(String text) {
        this(text, false);
    }

    Label(String text, boolean compact) {
        super(span().css(component(label)).textContent(text).element(), "Label");
        if (compact) {
            element.classList.add(modifier(Constants.compact));
        }
    }

    @Override
    public Label that() {
        return this;
    }
}
