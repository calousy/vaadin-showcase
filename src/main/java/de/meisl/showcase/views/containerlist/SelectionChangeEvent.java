package de.meisl.showcase.views.containerlist;

import com.vaadin.flow.component.ComponentEvent;

public class SelectionChangeEvent extends ComponentEvent<ContainerListItem> {
    public SelectionChangeEvent(ContainerListItem source, boolean fromClient) {
        super(source, fromClient);
    }

    public Container getContainer()
    {
        return getSource().getContainer();
    }
}
