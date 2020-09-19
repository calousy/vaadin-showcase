package de.meisl.showcase.views.containerlist;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

@CssImport("./styles/views/containerlist/item.css")
public class ContainerListItem extends FlexLayout {

    private Container container;

    public ContainerListItem(Container container) {
//        setFlexDirection(FlexDirection.COLUMN);
        this.container = container;

        setClassName("container-item");

        Icon button = VaadinIcon.CLOSE_CIRCLE_O.create();
        button.setClassName("btn-delete");

        Span x = new Span();
        x.setClassName("status-icon");

        Div spanLotId = new Div(new Text(container.getId()));
        spanLotId.setClassName("lotid");
        Div lotId = new Div(spanLotId, new Text(container.getRecipe()));
        lotId.getStyle().set("flex-grow", "1");

        add(x, lotId, button);
    }

    public Container getContainer() {
        return container;
    }
}
