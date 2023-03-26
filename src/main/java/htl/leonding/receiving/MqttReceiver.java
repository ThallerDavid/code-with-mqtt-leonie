package htl.leonding.receiving;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;

@ApplicationScoped
public class MqttReceiver {

    @Inject
    Logger LOG;

    String message;
    @GET
    @Incoming("incoming-channel")
    public void consume(byte[] raw){
        LOG.info(new String(raw));
        message = new String(raw);
    }
}
