package de.meisl.showcase.views.containerlist;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import de.meisl.showcase.views.main.MainView;

import java.util.ArrayList;
import java.util.List;

@Route(value = "containers", layout = MainView.class)
@PageTitle("Container List")
public class ContainerListView extends HorizontalLayout {

    private final ContainerList containerList;

    private final VerticalLayout detailView = new VerticalLayout();
    private Container currentSelection;

    public ContainerListView() {
        containerList = new ContainerList();
        add(containerList, detailView);
        containerList.setWidth("300px");
        containerList.addSelectionChangeListener(listener -> {
            detailView.removeAll();
            Container newSelection = listener.getSource().getContainer();
            if (newSelection != currentSelection) {
                detailView.add(new Text(newSelection.getId()));
                currentSelection = newSelection;
            } else {
                currentSelection = null;
            }

        });

        detailView.add(new Text("Nothing selected"));
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {

        List<Container> list = new ArrayList<>();
        list.add(new Container("LotA", "RecipeA"));
        list.add(new Container("LotB", "RecipeA"));
        list.add(new Container("LotC", "RecipeA"));

        containerList.setItems(list);
    }

}
