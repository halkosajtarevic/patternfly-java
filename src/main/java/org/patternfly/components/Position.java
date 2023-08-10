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

import static org.patternfly.layout.Classes.modifier;
import static org.patternfly.layout.Classes.bottom;
import static org.patternfly.layout.Classes.left;
import static org.patternfly.layout.Classes.right;
import static org.patternfly.layout.Classes.top;

/** Position used by {@link Popover} and {@link Tooltip}. */
enum Position {
    AUTO(""), TOP(modifier(top)), RIGHT(modifier(right)), BOTTOM(modifier(bottom)), LEFT(modifier(left));

    final String modifier;

    Position(String modifier) {
        this.modifier = modifier;
    }
}
