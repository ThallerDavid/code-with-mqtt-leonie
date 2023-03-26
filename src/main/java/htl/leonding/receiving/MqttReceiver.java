package htl.leonding.receiving;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MqttReceiver {

    @Inject
    Logger LOG;

    @Incoming("incoming-channel")
    public void consume(byte[] raw){
        LOG.info(new String(raw));
    }
}
