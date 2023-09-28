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

import org.patternfly.core.PatternFly;

public interface Classes {

    String action = "action";
    String actionGroup = "action-group";
    String actions = "actions";
    String active = "active";
    String alert = "alert";
    String alertGroup = "alert-group";
    String alignRight = "align-right";
    String arrow = "arrow";
    String avatar = "avatar";
    String badge = "badge";
    String banner = "banner";
    String block = "block";
    String body = "body";
    String bottom = "bottom";
    String brand = "brand";
    String breadcrumb = "breadcrumb";
    String bulkSelect = "bulk-select";
    String button = "button";
    String buttonGroup = "button-group";
    String card = "card";
    String cell = "cell";
    String center = "center";
    String check = "check";
    String chip = "chip";
    String chipGroup = "chip-group";
    String clipper = "clipper";
    String col = "col";
    String collapsed = "collapsed";
    String compact = "compact";
    String content = "content";
    String contextSelector = "context-selector";
    String control = "control";
    String controls = "controls";
    String current = "current";
    String custom = "custom";
    String danger = "danger";
    String dataList = "data-list";
    String dataToolbar = "data-toolbar";
    String description = "description";
    String disabled = "disabled";
    String divider = "divider";
    String dropdown = "dropdown";
    String emptyState = "empty-state";
    String end = "end";
    String expandable = "expandable";
    String expandableContent = "expandable-content";
    String expandableRow = "expandable-row";
    String expanded = "expanded";
    String fieldset = "fieldset";
    String fill = "fill";
    String filterGroup = "filter-group";
    String flex = "flex";
    String flyout = "flyout";
    String focus = "focus";
    String footer = "footer";
    String form = "form";
    String formControl = "form-control";
    String gallery = "gallery";
    String global = "global";
    String globalNavigation = "Global Navigation";
    String grid = "grid";
    String group = "group";
    String gutter = "gutter";
    String hasPopup = "haspopup";
    String head = "head";
    String header = "header";
    String horizontal = "horizontal";
    String horizontalSubnav = "horizontal-subnav";
    String hoverable = "hoverable";
    String icon = "icon";
    String iconButtonGroup = "icon-button-group";
    String indicator = "indicator";
    String info = "info";
    String inline = "inline";
    String input = "input";
    String inputGroup = "input-group";
    String inset = "inset";
    String insetNone = "inset-none";
    String invalid = "invalid";
    String item = "item";
    String itemAction = "item-action";
    String itemContent = "item-content";
    String itemControl = "item-control";
    String itemRow = "item-row";
    String label = "label";
    String labelledBy = "labelledby";
    String leadBall = "lead-ball";
    String left = "left";
    String limitWidth = "limit-width";
    String link = "link";
    String list = "list";
    String listbox = "listbox";
    String main = "main";
    String mainSection = "main-section";
    String masthead = "masthead";
    String maxLines = "max-lines";
    String menu = "menu";
    String menuitem = "menuitem";
    String nav = "nav";
    String noBorderRows = "no-border-rows";
    String noFill = "no-fill";
    String none = "none";
    String noPadding = "no-padding";
    String option = "option";
    String optionsMenu = "options-menu";
    String overflow = "overflow";
    String overflowScroll = "overflow-scroll";
    String padding = "padding";
    String page = "page";
    String pageInsets = "page-insets";
    String pagination = "pagination";
    String path = "path";
    String plain = "plain";
    String popover = "popover";
    String presentation = "presentation";
    String primary = "primary";
    String progress = "progress";
    String progressbar = "progressbar";
    String read = "read";
    String readOnly = "read-only";
    String right = "right";
    String scope = "scope";
    String screenReader = "pf-" + PatternFly.VERSION + "-screen-reader";
    String scroll = "scroll";
    String scrollable = "scrollable";
    String scrollButton = "scroll-button";
    String search = "search";
    String secondary = "secondary";
    String section = "section";
    String select = "select";
    String selected = "selected";
    String selector = "selector";
    String separator = "separator";
    String shadowBottom = "shadow-bottom";
    String shadowTop = "shadow-top";
    String sidebar = "sidebar";
    String simple = "simple";
    String skipToContent = "skip-to-content";
    String sort = "sort";
    String spinner = "spinner";
    String splitButton = "split-button";
    String stack = "stack";
    String subnav = "subnav";
    String success = "success";
    String tab = "tab";
    String tabContent = "tab-content";
    String table = "table";
    String tabpanel = "tabpanel";
    String tabs = "tabs";
    String tailBall = "tail-ball";
    String tertiary = "tertiary";
    String text = "text";
    String textLeftAligned = "textLeftAligned";
    String title = "title";
    String toast = "toast";
    String toggle = "toggle";
    String toggleGroup = "toggle-group";
    String toggleGroupContainer = "toggle-group-container";
    String toolbar = "toolbar";
    String tools = "tools";
    String tooltip = "tooltip";
    String top = "top";
    String totalItems = "total-items";
    String truncate = "truncate";
    String type = "type";
    String typeahead = "typeahead";
    String unread = "unread";
    String valueText = "value-text";
    String warning = "warning";
    String wizard = "wizard";
    String wrap = "wrap";
    String wrapper = "wrapper";

    static String component(String component, String... elements) {
        StringBuilder builder = new StringBuilder();
        builder.append("pf-").append(PatternFly.VERSION).append("-c-").append(component);
        if (elements != null && elements.length != 0) {
            builder.append("__");
            for (int i = 0; i < elements.length; i++) {
                builder.append(elements[i]);
                if (i < elements.length - 1) {
                    builder.append("-");
                }
            }
        }
        return builder.toString();
    }

    static String layout(String layout) {
        return "pf-" + PatternFly.VERSION + "-l-" + layout;
    }

    static String modifier(String modifier) {
        return "pf-m-" + modifier;
    }

    static String modifier(String modifier, Breakpoint breakpoint) {
        return "pf-m-" + modifier + "-on-" + breakpoint.value;
    }

    static String util(String utility) {
        return "pf-" + PatternFly.VERSION + "-u-" + utility;
    }
}