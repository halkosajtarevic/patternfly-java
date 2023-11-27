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
package org.patternfly.component.code;

import org.patternfly.component.ComponentReference;
import org.patternfly.component.SubComponent;
import org.patternfly.component.button.Button;
import org.patternfly.component.icon.InlineIcon;
import org.patternfly.core.Aria;
import org.patternfly.core.WithIcon;
import org.patternfly.handler.ComponentHandler;
import org.patternfly.layout.Classes;
import org.patternfly.layout.PredefinedIcon;

import elemental2.dom.HTMLDivElement;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.EventType.click;
import static org.patternfly.component.button.Button.button;
import static org.patternfly.component.icon.InlineIcon.inlineIcon;
import static org.patternfly.dom.DomGlobal.navigator;
import static org.patternfly.layout.Classes.actions;
import static org.patternfly.layout.Classes.component;
import static org.patternfly.layout.Classes.item;
import static org.patternfly.layout.PredefinedIcon.copy;

public class CodeBlockAction extends SubComponent<HTMLDivElement, CodeBlockAction> implements
        WithIcon<HTMLDivElement, CodeBlockAction>, ComponentReference<CodeBlock> {

    // ------------------------------------------------------ factory

    public static CodeBlockAction codeBlockAction(String iconClass) {
        return new CodeBlockAction(inlineIcon(iconClass));
    }

    public static CodeBlockAction codeBlockAction(PredefinedIcon predefinedIcon) {
        return new CodeBlockAction(inlineIcon(predefinedIcon));
    }

    public static CodeBlockAction codeBlockAction(InlineIcon icon) {
        return new CodeBlockAction(icon);
    }

    public static CodeBlockAction codeBlockCopyToClipboardAction() {
        return new CodeBlockAction(inlineIcon(copy))
                .ariaLabel("Copy to clipboard")
                .onClick((event, action) -> navigator.clipboard.writeText(action.mainComponent().code()));
    }

    // ------------------------------------------------------ instance

    private final Button button;
    private ComponentHandler<CodeBlockAction> handler;
    private CodeBlock codeBlock;

    CodeBlockAction(InlineIcon icon) {
        super(div().css(component(Classes.codeBlock, actions, item)).element());
        add(button = button().plain().icon(icon));
    }

    @Override
    public void passComponent(CodeBlock codeBlock) {
        this.codeBlock = codeBlock;
        if (handler != null) {
            button.on(click, e -> handler.handle(e, this));
        }
    }

    @Override
    public CodeBlock mainComponent() {
        return codeBlock;
    }

    // ------------------------------------------------------ builder

    @Override
    public CodeBlockAction icon(InlineIcon icon) {
        button.icon(icon);
        return this;
    }

    @Override
    public CodeBlockAction that() {
        return this;
    }

    // ------------------------------------------------------ aria

    public CodeBlockAction ariaLabel(String label) {
        button.aria(Aria.label, label);
        return this;
    }

    // ------------------------------------------------------ events

    public CodeBlockAction onClick(ComponentHandler<CodeBlockAction> handler) {
        this.handler = handler;
        return this;
    }
}
