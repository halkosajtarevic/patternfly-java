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
package org.patternfly.components.navigation;

import org.jboss.elemento.EventCallbackFn;
import org.patternfly.components.SubComponent;
import org.patternfly.core.Aria;

import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLLIElement;
import elemental2.dom.MouseEvent;

import static org.jboss.elemento.Elements.a;
import static org.jboss.elemento.Elements.li;
import static org.jboss.elemento.EventType.click;
import static org.patternfly.core.Dataset.navigationItem;
import static org.patternfly.layout.Classes.component;
import static org.patternfly.layout.Classes.current;
import static org.patternfly.layout.Classes.item;
import static org.patternfly.layout.Classes.link;
import static org.patternfly.layout.Classes.modifier;
import static org.patternfly.layout.Classes.nav;

public class NavigationItem extends SubComponent<HTMLLIElement, NavigationItem> {

    // ------------------------------------------------------ factory methods

    public static NavigationItem navigationItem(String id, String title) {
        return new NavigationItem(id, title, null);
    }

    public static NavigationItem navigationItem(String id, String title, String href) {
        return new NavigationItem(id, title, href);
    }

    // ------------------------------------------------------ instance

    public final String id;
    final HTMLAnchorElement a;

    NavigationItem(String id, String text, String href) {
        super(li().css(component(nav, item)).element());
        this.id = id;

        add(a = a().css(component(nav, link))
                .data(navigationItem, id)
                .textContent(text)
                .element());
        if (href != null) {
            a.href = href;
        }
    }

    @Override
    public NavigationItem that() {
        return this;
    }

    // ------------------------------------------------------ click handler

    public NavigationItem onClick(EventCallbackFn<MouseEvent> callback) {
        a(a).on(click, callback);
        return this;
    }

    // ------------------------------------------------------ internals

    void select() {
        a.classList.add(modifier(current));
        a.setAttribute(Aria.current, "page");
        a.scrollIntoView(false);
    }
}