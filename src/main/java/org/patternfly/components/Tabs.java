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

import java.util.function.Consumer;

import org.jboss.elemento.By;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HtmlContent;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.Id;
import org.jboss.elemento.IsElement;
import org.patternfly.core.Aria;
import org.patternfly.core.SelectHandler;
import org.patternfly.layout.Classes;

import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLUListElement;
import org.patternfly.layout.Icons;

import static org.jboss.elemento.Elements.*;
import static org.jboss.elemento.Elements.button;
import static org.jboss.elemento.Elements.section;
import static org.jboss.elemento.EventType.click;
import static org.patternfly.layout.Classes.component;
import static org.patternfly.layout.Icons.angleLeft;
import static org.patternfly.layout.Icons.angleRight;
import static org.patternfly.layout.Icons.fas;
import static org.patternfly.layout.Classes.modifier;
import static org.patternfly.layout.Classes.*;

/**
 * PatternFly label component.
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/core/components/label/">https://www.patternfly.org/v4/documentation/core/components/label</a>
 */
public class Tabs extends BaseComponent<HTMLDivElement, Tabs> implements HtmlContent<HTMLDivElement, Tabs>, Aria<Tabs> {

    // ------------------------------------------------------ factory methods

    public static Tabs tabs() {
        return new Tabs();
    }

    // ------------------------------------------------------ instance

    public static final String ARIA_SCROLL_LEFT = "ARIA_SCROLL_LEFT";
    public static final String ARIA_SCROLL_RIGHT = "ARIA_SCROLL_RIGHT";
    private final HTMLButtonElement scrollLeft;
    private final HTMLButtonElement scrollRight;
    private final HtmlContentBuilder<HTMLUListElement> tabs;
    private SelectHandler<TabContent> onSelect;

    protected Tabs() {
        super(div().element(), "Tabs");
        this.scrollLeft = button().css(component("tabs", scrollButton)).aria("label", "Scroll left")
                .add(Icon.icon(fas(angleLeft))).element();
        this.scrollRight = button().css(component("tabs", scrollButton)).aria("label", "Scroll right")
                .add(Icon.icon(fas(angleRight))).element();
        this.tabs = ul().css(component("tabs", list));
        add(div().css(component("tabs")).add(scrollLeft).add(tabs).add(scrollRight));
    }

    @Override
    public Tabs that() {
        return this;
    }

    // ------------------------------------------------------ public API

    public <E extends HTMLElement> Tabs add(String title, IsElement<E> panel) {
        return add(Id.build(title), title, panel.element());
    }

    public Tabs add(String title, HTMLElement panel) {
        return add(Id.build(title), title, panel);
    }

    public <E extends HTMLElement> Tabs add(String id, String title, IsElement<E> panel) {
        return add(id, title, panel.element());
    }

    public Tabs add(String id, String title, HTMLElement panel) {
        return add(id, tabDisplay -> tabDisplay.textContent(title), panel);
    }

    public <E extends HTMLElement> Tabs add(String id, Consumer<HtmlContentBuilder<HTMLButtonElement>> display,
            IsElement<E> panel) {
        return add(id, display, panel.element());
    }

    public Tabs add(String id, Consumer<HtmlContentBuilder<HTMLButtonElement>> tabDisplay, HTMLElement panel) {
        return add(id, tabDisplay, panelDisplay -> panelDisplay.add(panel));
    }

    public Tabs add(String id, Consumer<HtmlContentBuilder<HTMLButtonElement>> tabDisplay,
            Consumer<HtmlContentBuilder<HTMLElement>> panelDisplay) {
        String tabId = Id.build(id, tab);
        String contentId = Id.build(id, content);

        HtmlContentBuilder<HTMLButtonElement> tab = button().css(component("tabs", Classes.button)).id(tabId)
                .aria(controls, contentId).on(click, e -> select(id));
        tabDisplay.accept(tab);
        tabs.add(li().css(component("tabs", item)).add(tab));

        HtmlContentBuilder<HTMLElement> panel = section().css(component(tabContent)).id(contentId).aria(labelledBy, tabId)
                .attr("role", tabpanel).attr("tabindex", 0);
        panelDisplay.accept(panel);
        panel.element().hidden = true;
        add(panel);

        if (tabs.element().childElementCount == 1) {
            select(id, false);
        }
        return this;
    }

    public Tabs select(String id) {
        return select(id, true);
    }

    public Tabs select(String id, boolean fireEvent) {
        String tabId = Id.build(id, tab);
        String contentId = Id.build(id, content);
        HTMLButtonElement button = tabs.find(By.id(tabId));
        HTMLElement content = find(By.id(contentId));

        if (button != null && content != null) {
            for (HTMLElement li : children(tabs)) {
                Elements.toggle(li, modifier(current), tabId.equals(((HTMLElement) li.firstElementChild).id));
            }
            for (HTMLElement section : findAll(By.element("section").and(By.selector("." + component(tabContent))))) {
                section.hidden = !contentId.equals(section.id);
            }
            if (fireEvent && onSelect != null) {
                TabContent tabContent = new TabContent(button, content);
                onSelect.onSelect(tabContent);
            }
        }
        return this;
    }

    // ------------------------------------------------------ events

    public Tabs onSelect(SelectHandler<TabContent> onSelect) {
        this.onSelect = onSelect;
        return this;
    }

    // ------------------------------------------------------ modifier

    public Tabs fill() {
        return css(modifier("fill"));
    }

    // ------------------------------------------------------ aria

    @Override
    public Tabs label(String label) {
        return this;
    }

    @Override
    public Tabs label(String target, String label) {
        switch (target) {
            case ARIA_SCROLL_LEFT:
                button(scrollLeft).aria("label", label);
                break;
            case ARIA_SCROLL_RIGHT:
                button(scrollRight).aria("label", label);
                break;
        }
        return this;
    }

    // ------------------------------------------------------ inner classes

    public static class TabContent {

        public final HTMLButtonElement tab;
        public final HTMLElement content;

        public TabContent(HTMLButtonElement tab, HTMLElement content) {
            this.tab = tab;
            this.content = content;
        }
    }
}
