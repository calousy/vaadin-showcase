package de.meisl.showcase.views.about;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.webpush.WebPush;
import de.meisl.showcase.services.WebPushService;
import de.meisl.showcase.views.main.MainView;
import de.meisl.showcase.webpush.WebPushAction;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
@CssImport("./styles/views/about/about-view.css")
public class AboutView extends Div {

    private final Button subscribe;
    private final Button unsubscribe;
    private final WebPushService webPushService;

    public AboutView(@Autowired WebPushService webPushService) {
        this.webPushService = webPushService;
        setId("about-view");
        add(new Text("Content placeholder"));

        WebPush webpush = webPushService.getWebPush();

        subscribe = new Button("Subscribe");
        unsubscribe = new Button("UnSubscribe");

        subscribe.setEnabled(false);
        subscribe.addClickListener(e -> {
            webpush.subscribe(subscribe.getUI().get(), subscription -> {
                webPushService.store(subscription);
                subscribe.setEnabled(false);
                unsubscribe.setEnabled(true);
            });
        });

        unsubscribe.setEnabled(false);
        unsubscribe.addClickListener(e -> {
            webpush.unsubscribe(unsubscribe.getUI().get(), subscription -> {
                webPushService.remove(subscription);
                subscribe.setEnabled(true);
                unsubscribe.setEnabled(false);
            });
        });

        WebPushAction webPushAction = new WebPushAction(
                "hello",
                "Hello World"
        );

        TextField message = new TextField("Message");
        Button broadcast = new Button("Broadcast message", e -> {
            webPushService.sendNotification("Message from administration",
                    message.getValue(), webPushAction, "This is my data",
                    "https://upload.wikimedia.org/wikipedia/commons/0/0e/Message-icon-blue-symbol-double.png"
            );
            //webPushService.notifyAll("Message from administration", message.getValue());
        });

        add(subscribe, unsubscribe, message, broadcast);

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        UI ui = attachEvent.getUI();
        WebPush webpush = webPushService.getWebPush();
        webpush.subscriptionExists(ui, registered -> {
            subscribe.setEnabled(!registered);
            unsubscribe.setEnabled(registered);
            if (registered && webPushService.isEmpty()) {
                webpush.fetchExistingSubscription(ui, webPushService::store);
            }
        });
    }

}
