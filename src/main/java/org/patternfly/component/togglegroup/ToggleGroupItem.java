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
package org.patternfly.component.togglegroup;

import org.jboss.elemento.HTMLContainerBuilder;
import org.patternfly.component.icon.InlineIcon;
import org.patternfly.core.ElementDelegate;
import org.patternfly.core.SelectionMode;
import org.patternfly.core.WithIcon;
import org.patternfly.core.WithText;
import org.patternfly.handler.ComponentHandler;
import org.patternfly.style.Classes;
import org.patternfly.style.Modifiers.Disabled;

import elemental2.dom.Event;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;

import static org.jboss.elemento.Elements.button;
import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.failSafeRemoveFromParent;
import static org.jboss.elemento.Elements.insertFirst;
import static org.jboss.elemento.Elements.removeChildrenFrom;
import static org.jboss.elemento.Elements.span;
import static org.jboss.elemento.EventType.click;
import static org.patternfly.core.Aria.pressed;
import static org.patternfly.style.Classes.component;
import static org.patternfly.style.Classes.icon;
import static org.patternfly.style.Classes.item;
import static org.patternfly.style.Classes.modifier;
import static org.patternfly.style.Classes.text;
import static org.patternfly.style.Classes.toggleGroup;

public class ToggleGroupItem extends ToggleGroupSubComponent<HTMLDivElement, ToggleGroupItem> implements
        Disabled<HTMLDivElement, ToggleGroupItem>,
        ElementDelegate<HTMLDivElement, ToggleGroupItem>,
        WithIcon<HTMLDivElement, ToggleGroupItem>,
        WithText<HTMLDivElement, ToggleGroupItem> {

    // ------------------------------------------------------ factory

    public static ToggleGroupItem toggleGroupItem(String id) {
        return new ToggleGroupItem(id, null);
    }

    public static ToggleGroupItem toggleGroupItem(String id, String text) {
        return new ToggleGroupItem(id, text);
    }

    // ------------------------------------------------------ instance

    static final String SUB_COMPONENT_NAME = "tgi";
    public final String id;
    private final HTMLContainerBuilder<HTMLButtonElement> button;
    private HTMLElement textElement;
    private HTMLElement iconContainer;

    ToggleGroupItem(String id, String text) {
        super(SUB_COMPONENT_NAME, div().css(component(toggleGroup, item)).element());
        this.id = id;
        this.button = button().css(component(toggleGroup, Classes.button))
                .aria(pressed, false)
                .on(click, this::onClick);
        element().appendChild(button.element());
        text(text);
    }

    @Override
    public HTMLElement delegate() {
        return button.element();
    }

    // ------------------------------------------------------ builder

    @Override
    public ToggleGroupItem disabled(boolean disabled) {
        button.element().disabled = disabled;
        return this;
    }

    @Override
    public ToggleGroupItem icon(InlineIcon icon) {
        removeChildrenFrom(iconContainer);
        failSafeIconContainer().appendChild(icon.element());
        return this;
    }

    @Override
    public ToggleGroupItem removeIcon() {
        failSafeRemoveFromParent(iconContainer);
        iconContainer = null;
        return this;
    }

    @Override
    public ToggleGroupItem text(String text) {
        if (text == null) {
            failSafeRemoveFromParent(textElement);
            textElement = null;
        } else {
            failSafeTextElement().textContent = text;
        }
        return this;
    }

    @Override
    public ToggleGroupItem that() {
        return this;
    }

    // ------------------------------------------------------ events

    public ToggleGroupItem onClick(ComponentHandler<ToggleGroupItem> clickHandler) {
        button.on(click, e -> clickHandler.handle(e, this));
        return this;
    }

    // ------------------------------------------------------ api

    @Override
    public boolean isDisabled() {
        return button.element().disabled;
    }

    // ------------------------------------------------------ internal

    void onClick(Event event) {
        ToggleGroup toggleGroup = lookupComponent();
        if (toggleGroup.selectionMode == SelectionMode.single) {
            if (!isSelected()) {
                toggleGroup.select(this);
            }
        } else if (toggleGroup.selectionMode == SelectionMode.multi) {
            toggleGroup.select(this, !isSelected(), true);
        }
    }

    void markSelected(boolean selected) {
        button.aria(pressed, selected);
        button.classList().toggle(modifier(Classes.selected), selected);
    }

    boolean isSelected() {
        return Boolean.parseBoolean(button.element().getAttribute(pressed));
    }

    private HTMLElement failSafeTextElement() {
        if (textElement == null) {
            button.add(textElement = span().css(component(toggleGroup, text)).element());
        }
        return textElement;
    }

    private HTMLElement failSafeIconContainer() {
        if (iconContainer == null) {
            insertFirst(button.element(), iconContainer = span().css(component(toggleGroup, icon)).element());
        }
        return iconContainer;
    }
}
