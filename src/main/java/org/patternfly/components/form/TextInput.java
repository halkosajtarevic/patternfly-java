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
package org.patternfly.components.form;

import java.util.function.Consumer;

import org.jboss.elemento.InputElementBuilder;
import org.patternfly.components.BaseComponent;
import org.patternfly.components.ComponentType;
import org.patternfly.core.Aria;
import org.patternfly.core.ChangeHandler;
import org.patternfly.core.HasValue;
import org.patternfly.core.Modifiers.Disabled;
import org.patternfly.core.Modifiers.Invalid;
import org.patternfly.core.Modifiers.Plain;
import org.patternfly.core.Modifiers.Readonly;
import org.patternfly.core.Modifiers.Required;
import org.patternfly.layout.Classes;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLInputElement;

import static org.jboss.elemento.Elements.failSafeRemoveFromParent;
import static org.jboss.elemento.Elements.i;
import static org.jboss.elemento.Elements.input;
import static org.jboss.elemento.Elements.span;
import static org.jboss.elemento.Elements.wrapInputElement;
import static org.jboss.elemento.EventType.change;
import static org.jboss.elemento.InputType.text;
import static org.patternfly.core.Aria.hidden;
import static org.patternfly.core.Aria.invalid;
import static org.patternfly.layout.Classes.component;
import static org.patternfly.layout.Classes.formControl;
import static org.patternfly.layout.Classes.modifier;
import static org.patternfly.layout.Classes.status;
import static org.patternfly.layout.Classes.utilities;
import static org.patternfly.layout.Icons.exclamationCircle;
import static org.patternfly.layout.Icons.fas;

/**
 * A text input is used to gather free-form text from a user.
 *
 * @see <a href=
 *      "https://www.patternfly.org/components/forms/text-input">https://www.patternfly.org/components/forms/text-input</a>
 */
public class TextInput extends BaseComponent<HTMLElement, TextInput> implements
        HasValue<String>,
        Disabled<HTMLElement, TextInput>,
        Invalid<HTMLElement, TextInput>,
        Plain<HTMLElement, TextInput>,
        Readonly<HTMLElement, TextInput>,
        Required<HTMLElement, TextInput> {

    // ------------------------------------------------------ factory methods

    public static TextInput textInput(String id) {
        return new TextInput(id);
    }

    // ------------------------------------------------------ instance

    private final HTMLInputElement inputElement;
    private boolean leftTruncatedApplied;
    private HTMLElement iconContainer;
    private HTMLElement invalidIcon;

    TextInput(String id) {
        super(span().css(component(formControl))
                .add(input(text)
                        .id(id)
                        .name(id)
                        .aria(invalid, false))
                .element(),
                ComponentType.TextInput);
        inputElement = (HTMLInputElement) element().firstElementChild;
    }

    @Override
    public TextInput that() {
        return this;
    }

    // ------------------------------------------------------ public API

    @Override
    public String value() {
        return inputElement.value;
    }

    public TextInput value(String value) {
        inputElement.value = value;
        return this;
    }

    public TextInput placeholder(String placeholder) {
        inputElement.placeholder = placeholder;
        return this;
    }

    public TextInput onChange(ChangeHandler<String> handler) {
        inputElement.addEventListener(change.getName(), e -> handler.onChange(inputElement.value));
        return this;
    }

    /** Provides access to the underlying input element using a fluent API style */
    public TextInput consumeInputElement(Consumer<InputElementBuilder<HTMLInputElement>> inputElementConsumer) {
        inputElementConsumer.accept(inputElement());
        return this;
    }

    /** Returns the underlying input element */
    public InputElementBuilder<HTMLInputElement> inputElement() {
        return wrapInputElement(inputElement);
    }

    // ------------------------------------------------------ add methods

    public TextInput addIcon(String icon) {
        failSafeIconContainer().appendChild(span().css(component(formControl, Classes.icon))
                .add(i().css(icon).aria(hidden, true))
                .element());
        return this;
    }

    // ------------------------------------------------------ modifiers

    @Override
    public TextInput disabled(boolean disabled) {
        inputElement.disabled = disabled;
        return Disabled.super.disabled(disabled);
    }

    @Override
    public TextInput readonly(boolean readonly) {
        inputElement.readOnly = readonly;
        return Readonly.super.readonly(readonly);
    }

    @Override
    public TextInput plain(boolean plain) {
        if (plain) {
            // plain requires readonly
            readonly();
        }
        return Plain.super.plain(plain);
    }

    @Override
    public TextInput invalid(boolean invalid) {
        if (invalid) {
            failSafeIconContainer().appendChild(failSafeInvalidIcon());
        } else {
            failSafeRemoveFromParent(failSafeInvalidIcon());
        }
        inputElement.setAttribute(Aria.invalid, !invalid);
        return Invalid.super.invalid(invalid);
    }

    @Override
    public TextInput required(boolean required) {
        inputElement.required = required;
        return this;
    }

    // ------------------------------------------------------ internals

    private HTMLElement failSafeIconContainer() {
        if (iconContainer == null) {
            add(iconContainer = span().css(component(formControl, utilities)).element());
        }
        return iconContainer;
    }

    private HTMLElement failSafeInvalidIcon() {
        if (invalidIcon == null) {
            invalidIcon = span().css(component(formControl, Classes.icon), modifier(status))
                    .add(i().css(fas(exclamationCircle)).aria(hidden, true))
                    .element();
        }
        return invalidIcon;
    }
}