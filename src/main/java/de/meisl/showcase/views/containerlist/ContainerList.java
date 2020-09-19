package de.meisl.showcase.views.containerlist;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.HasItems;
import com.vaadin.flow.shared.Registration;

import java.util.Collection;


public class ContainerList extends FlexLayout implements HasItems<Container> {

    private ContainerListItem selected;

    public ContainerList() {
        setFlexDirection(FlexDirection.COLUMN);
    }

    public Registration addSelectionChangeListener(ComponentEventListener<SelectionChangeEvent> listener) {
        return addListener(SelectionChangeEvent.class, listener);
    }

    @Override
    public void setItems(Collection<Container> collection) {
        removeAll();
        for (Container container : collection) {
            ContainerListItem item = new ContainerListItem(container);

            item.addClickListener(this::clickedItem);
            add(item);
        }
    }

    private void clickedItem(ClickEvent<FlexLayout> event) {
        ContainerListItem it = (ContainerListItem) event.getSource();
        if (selected != null) {
            selected.removeClassName("active");
        }
        if (selected == it)
        {
            it = null;
        }
        selected = it;
        if (selected != null) {
            fireEvent(new SelectionChangeEvent(selected, true));
            selected.addClassName("active");
        }
    }

}
