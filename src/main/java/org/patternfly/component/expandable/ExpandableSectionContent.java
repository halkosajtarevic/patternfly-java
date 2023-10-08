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
package org.patternfly.component.expandable;

import org.jboss.elemento.Id;
import org.patternfly.component.ComponentType;
import org.patternfly.component.SubComponent;

import elemental2.dom.HTMLDivElement;

import static org.jboss.elemento.Elements.div;
import static org.patternfly.core.Attributes.role;
import static org.patternfly.layout.Classes.component;
import static org.patternfly.layout.Classes.content;
import static org.patternfly.layout.Classes.expandableSection;

public class ExpandableSectionContent extends SubComponent<HTMLDivElement, ExpandableSectionContent> {

    // ------------------------------------------------------ factory methods

    public static ExpandableSectionContent expandableSectionContent() {
        return new ExpandableSectionContent();
    }

    // ------------------------------------------------------ instance

    final String id;

    ExpandableSectionContent() {
        super(div().css(component(expandableSection, content))
                .apply(e -> e.hidden = true)
                .attr(role, "region")
                .element());

        id(this.id = Id.unique(ComponentType.ExpandableSection.id, "cnt"));
    }

    @Override
    public ExpandableSectionContent that() {
        return this;
    }
}
