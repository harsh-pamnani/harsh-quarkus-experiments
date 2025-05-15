package main.mailpit;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/send-email")
public class EmailResource {
    @Inject
    Mailer mailer;

    @POST
    @Blocking
    public void sendEmail() {
        mailer.send(Mail.withText(
                "quarkus@quarkus.io", "Ahoy from Quarkus", "A simple email sent from a Quarkus application."));
    }
}
