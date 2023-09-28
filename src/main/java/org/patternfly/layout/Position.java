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
package org.patternfly.layout;

import org.patternfly.components.Popover;
import org.patternfly.components.Tooltip;

import static org.patternfly.layout.Classes.*;

/** Position used by {@link Popover} and {@link Tooltip}. */
public enum Position {

    auto(""),

    top(modifier(Classes.top)),

    right(modifier(Classes.right)),

    bottom(modifier(Classes.bottom)),

    left(modifier(Classes.left));

    public final String modifier;

    Position(String modifier) {
        this.modifier = modifier;
    }
}