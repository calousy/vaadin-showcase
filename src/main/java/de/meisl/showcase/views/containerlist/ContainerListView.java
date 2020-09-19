package de.meisl.showcase.views.containerlist;

import ch.qos.logback.core.helpers.CyclicBuffer;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.meisl.showcase.views.main.MainView;

import java.util.ArrayList;
import java.util.List;

@Route(value = "containers", layout = MainView.class)
@PageTitle("Container List")
public class ContainerListView extends HorizontalLayout {

    private ContainerList containerList;

    private VerticalLayout detailView = new VerticalLayout();

    public ContainerListView() {
        containerList = new ContainerList();
        add(containerList, detailView);
        containerList.setWidth("300px");
        containerList.addSelectionChangeListener(x -> {
            detailView.removeAll();
           detailView.add(new Text(x.getSource().getContainer().getId()));
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
