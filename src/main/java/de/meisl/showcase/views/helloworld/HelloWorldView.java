package de.meisl.showcase.views.helloworld;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import de.meisl.showcase.services.BackgroundService;
import de.meisl.showcase.views.main.MainView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

@Route(value = "hello", layout = MainView.class)
@PageTitle("Hello World")
@CssImport("./styles/views/helloworld/hello-world-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class HelloWorldView extends HorizontalLayout {

    private final BackgroundService backgroundService;
    private final Log log = LogFactory.getLog(HelloWorldView.class);

    private final TextField name;
    private final Button sayHello;

    public HelloWorldView(@Autowired BackgroundService backgroundService) {
        this.backgroundService = backgroundService;

        setId("hello-world-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> Notification.show("Hello " + name.getValue()));

        Text text = new Text("");
        ProgressBar progressBar = new ProgressBar(0, 10);
        Button runTasks = new Button("Run tasks", x -> {
            progressBar.setValue(0);
            for (int i = 0; i < 10; i++) {
                log.info("Starting taks " + i);
                CompletableFuture<String> stringCompletableFuture = backgroundService.longRunningTask("Task@" + i);
                stringCompletableFuture.thenAccept(c -> {
                    log.info("Accept taks " + c);
                    text.getUI().ifPresent(ui -> ui.access(() -> {
                        text.setText(c);
                        progressBar.setValue(progressBar.getValue()+1);
                    }));
                });
            }
        });
        add(runTasks, text, progressBar);

    }

}
