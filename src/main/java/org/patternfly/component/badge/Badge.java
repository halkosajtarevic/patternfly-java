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
package org.patternfly.component.badge;

import java.util.function.Function;

import org.patternfly.component.BaseComponent;
import org.patternfly.component.ComponentType;
import org.patternfly.core.HasValue;
import org.patternfly.handler.ChangeHandler;
import org.patternfly.layout.Classes;

import elemental2.dom.HTMLElement;

import static org.jboss.elemento.Elements.span;
import static org.patternfly.layout.Classes.badge;
import static org.patternfly.layout.Classes.component;
import static org.patternfly.layout.Classes.modifier;
import static org.patternfly.layout.Classes.read;
import static org.patternfly.layout.Classes.unread;

/**
 * A badge is used to annotate other information like a label or an object name.
 *
 * @see <a href= "https://www.patternfly.org/components/badge/html">https://www.patternfly.org/components/badge/html</a>
 */
public class Badge extends BaseComponent<HTMLElement, Badge> implements HasValue<Integer> {

    // ------------------------------------------------------ factory

    public static Badge badge(int count) {
        return new Badge(count);
    }

    public static Badge badge(int count, int limit) {
        return new Badge(count, limit);
    }

    // ------------------------------------------------------ instance

    private final HTMLElement valueElement;
    private int value;
    private int limit;
    private HTMLElement screenReader;
    private ChangeHandler<Badge, Integer> onChange;
    private Function<Integer, String> display;
    private Function<Integer, String> maxDisplay;

    Badge(int count) {
        this(count, Integer.MAX_VALUE);
    }

    Badge(int count, int limit) {
        super(ComponentType.Badge, span().css(component(badge)).element());
        this.valueElement = add(span()).element();
        this.limit = limit;
        count(count);
    }

    // ------------------------------------------------------ builder

    /** Marks the badge as read. */
    public Badge read() {
        element().classList.remove(modifier(unread));
        element().classList.add(modifier(read));
        return this;
    }

    /** Marks the badge as unread. */
    public Badge unread() {
        element().classList.remove(modifier(read));
        element().classList.add(modifier(unread));
        return this;
    }

    /** Sets the count of this badge. */
    public Badge count(int count) {
        boolean changed = value != count;
        value = count;
        updateValue();
        if (changed && onChange != null) {
            onChange.onChange(this, value);
        }
        return this;
    }

    /**
     * Use a function to render the text for the value of {@link #count()}. If not set {@code String.valueOf(count)} is used
     * implicitly.
     */
    public Badge display(Function<Integer, String> display) {
        this.display = display;
        updateValue();
        return this;
    }

    /**
     * Use a function to render the text if {@link #count()} &gt; {@link #limit(int)}. If not set {@code count + "+"} is used
     * implicitly.
     */
    public Badge maxDisplay(Function<Integer, String> maxDisplay) {
        this.maxDisplay = maxDisplay;
        updateValue();
        return this;
    }

    /** Sets the limit of this badge */
    public Badge limit(int limit) {
        this.limit = limit;
        updateValue();
        return this;
    }

    public Badge screenReader(String text) {
        if (screenReader == null) {
            add(screenReader = span().css(Classes.screenReader).element());
        }
        screenReader.textContent = text;
        return this;
    }

    @Override
    public Badge that() {
        return this;
    }

    // ------------------------------------------------------ events

    public Badge onChange(ChangeHandler<Badge, Integer> onChange) {
        this.onChange = onChange;
        return this;
    }

    // ------------------------------------------------------ api

    public int count() {
        return value;
    }

    @Override
    public Integer value() {
        return value;
    }

    // ------------------------------------------------------ internal

    private void updateValue() {
        if (value > limit) {
            if (maxDisplay != null) {
                valueElement.textContent = maxDisplay.apply(value);
            } else {
                valueElement.textContent = limit + "+";
            }
        } else {
            if (display != null) {
                valueElement.textContent = display.apply(value);
            } else {
                valueElement.textContent = String.valueOf(value);
            }
        }
    }
}
