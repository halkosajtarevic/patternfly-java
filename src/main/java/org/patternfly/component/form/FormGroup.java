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
package org.patternfly.component.form;

import org.patternfly.component.ComponentReference;
import org.patternfly.component.SubComponent;
import org.patternfly.core.Attributes;
import org.patternfly.core.Logger;
import org.patternfly.layout.Classes;

import elemental2.dom.HTMLElement;

import static org.jboss.elemento.Elements.div;
import static org.patternfly.layout.Classes.component;
import static org.patternfly.layout.Classes.group;

public class FormGroup extends SubComponent<HTMLElement, FormGroup> implements
        ComponentReference<Form> {

    // ------------------------------------------------------ factory

    public static FormGroup formGroup() {
        return new FormGroup();
    }

    // ------------------------------------------------------ instance

    String fieldId;
    boolean required;
    FormGroupRole role;
    private Form form;
    private FormGroupLabel label;
    private FormGroupControl control;

    FormGroup() {
        super(div().css(component(Classes.form, group)).element());
        this.fieldId = null;
        this.required = false;
        this.label = null;
        this.control = null;
    }

    @Override
    public void passComponent(Form form) {
        if ((role == FormGroupRole.radiogroup || role == FormGroupRole.group) && fieldId == null) {
            Logger.missing(form.componentType(), element(), "Missing field ID for form group with role '" + role.name() + "'.");
        }
        this.form = form;
        if (label != null) {
            label.passComponent(form);
            label.passSubComponent(this);
        }
        if (control != null) {
            control.passComponent(form);
            control.passSubComponent(this);
        }
    }

    @Override
    public Form mainComponent() {
        return form;
    }

    // ------------------------------------------------------ add

    public FormGroup addLabel(FormGroupLabel label) {
        return add(label);
    }

    // override to assure internal wiring
    public FormGroup add(FormGroupLabel label) {
        this.label = label;
        return add(label.element());
    }

    public FormGroup addControl(FormGroupControl control) {
        return add(control);
    }

    // override to assure internal wiring
    public FormGroup add(FormGroupControl control) {
        this.control = control;
        return add(control.element());
    }

    // ------------------------------------------------------ builder

    public FormGroup fieldId(String id) {
        this.fieldId = id;
        return this;
    }

    public FormGroup required() {
        this.required = true;
        return this;
    }

    /**
     * Sets the role of the form group. Pass in {@link FormGroupRole#radiogroup} when the form group contains multiple radio
     * inputs, or pass in {@link FormGroupRole#group} when the form group contains multiple of any other input type (e.g.
     * checkboxes).
     * <p>
     * Please note that if you set a role, the internal structure of the {@link FormGroupLabel} changes. Without a role the
     * label contains a {@code <label>} element. With a role the {@code <label>} element is replaced by a {@code <span>}
     * element. In this case it is expected that the {@link FormGroupControl} contains label elements.
     */
    public FormGroup role(FormGroupRole role) {
        this.role = role;
        attr(Attributes.role, role.name());
        return this;
    }

    @Override
    public FormGroup that() {
        return this;
    }
}
