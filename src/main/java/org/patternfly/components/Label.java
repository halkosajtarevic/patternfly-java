package org.patternfly.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.patternfly.resources.Constants;

import static org.jboss.gwt.elemento.core.Elements.span;
import static org.patternfly.resources.CSS.component;
import static org.patternfly.resources.CSS.modifier;
import static org.patternfly.resources.Constants.label;

/**
 * PatternFly label component.
 *
 * @see <a href= "https://www.patternfly.org/v4/documentation/react/components/label/">https://www.patternfly.org/v4/documentation/react/components/label</a>
 */
public class Label extends BaseComponent<HTMLElement, Label>
        implements HtmlContent<HTMLElement, Label> {

    // ------------------------------------------------------ factory methods

    public static Label label(String text) {
        return new Label(text);
    }

    public static Label label(String text, boolean compact) {
        return new Label(text, compact);
    }

    // ------------------------------------------------------ instance

    Label(String text) {
        this(text, false);
    }

    Label(String text, boolean compact) {
        super(span().css(component(label)).textContent(text).element(), "Label");
        if (compact) {
            element.classList.add(modifier(Constants.compact));
        }
    }

    @Override
    public Label that() {
        return this;
    }
}
