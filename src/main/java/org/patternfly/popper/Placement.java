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
package org.patternfly.popper;

public enum Placement {

    auto("auto"),

    top("top"),

    topStart("top-start"),

    topEnd("top-end"),

    bottom("bottom"),

    bottomStart("bottom-start"),

    bottomEnd("bottom-end"),

    left("left"),

    leftStart("left-start"),

    leftEnd("left-end"),

    right("right"),

    rightStart("right-start"),

    rightEnd("right-end");

    public final String value;

    Placement(String value) {
        this.value = value;
    }
}
