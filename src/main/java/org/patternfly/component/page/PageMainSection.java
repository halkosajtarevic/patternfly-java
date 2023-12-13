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
package org.patternfly.component.page;

import java.util.EnumSet;
import java.util.stream.Stream;

import org.patternfly.core.Logger;
import org.patternfly.style.Brightness;
import org.patternfly.style.Modifiers.Fill;
import org.patternfly.style.Modifiers.NoFill;

import elemental2.dom.HTMLElement;

import static java.util.stream.Collectors.joining;
import static org.jboss.elemento.Elements.section;
import static org.patternfly.style.Brightness.dark100;
import static org.patternfly.style.Brightness.dark200;
import static org.patternfly.style.Brightness.light;
import static org.patternfly.style.Classes.component;
import static org.patternfly.style.Classes.main;
import static org.patternfly.style.Classes.page;
import static org.patternfly.style.Classes.section;

/**
 * Container for a section in a {@link PageMainGroup} or {@link PageMain} component. Note: By default, the last/only section
 * will grow to fill the available vertical space. You can change this behavior using {@link #fill()} and {@link #noFill()}.
 *
 * @see <a href=
 *      "https://www.patternfly.org/components/page/html#usage">https://www.patternfly.org/components/page/html#usage</a>
 */
public class PageMainSection extends PageSectionBuilder<HTMLElement, PageMainSection> implements
        Fill<HTMLElement, PageMainSection>, NoFill<HTMLElement, PageMainSection> {

    // ------------------------------------------------------ factory

    public static PageMainSection pageMainSection() {
        return new PageMainSection();
    }

    // ------------------------------------------------------ instance

    static final String SUB_COMPONENT_NAME = "pms";

    PageMainSection() {
        super(SUB_COMPONENT_NAME, section().css(component(page, main, section)).element());
    }

    // ------------------------------------------------------ builder

    public PageMainSection background(Brightness brightness) {
        if (!EnumSet.of(light, dark100, dark200).contains(brightness)) {
            Logger.unsupported("PF5/PageMainSection", element(),
                    "Background " + brightness + " not supported. Valid values: " +
                            Stream.of(light, dark100, dark200).map(Brightness::name).collect(joining(" ")));
            return this;
        }
        return css(brightness.modifier());
    }

    @Override
    public PageMainSection that() {
        return this;
    }
}
