package org.patternfly.components;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLFormElement;
import org.elemento.ElementBuilder;
import org.elemento.HtmlContent;

import static org.elemento.Elements.div;
import static org.patternfly.resources.CSS.component;
import static org.patternfly.resources.Constants.form;
import static org.patternfly.resources.Constants.group;

/**
 * PatternFly form component.
 *
 * @see <a href= "https://www.patternfly.org/v4/documentation/core/components/form">https://www.patternfly.org/v4/documentation/core/components/form</a>
 */
public class Form extends BaseComponent<HTMLFormElement, Form>
        implements HtmlContent<HTMLFormElement, Form> {

    // ------------------------------------------------------ factory methods

    public static Form form() {
        return new Form();
    }

    public static Group group() {
        return new Group();
    }

    // ------------------------------------------------------ instance

    Form() {
        super(form().css(component(form)).element(), "Form");
    }

    @Override
    public Form that() {
        return this;
    }

    // ------------------------------------------------------ inner classes

    public static class Group extends ElementBuilder<HTMLDivElement, Group>
            implements HtmlContent<HTMLDivElement, Group> {

        private Group() {
            super(div().css(component(form, group)).element());
        }

        @Override
        public Group that() {
            return this;
        }
    }
}
