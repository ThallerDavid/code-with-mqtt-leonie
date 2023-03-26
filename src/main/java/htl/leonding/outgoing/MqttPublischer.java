package htl.leonding.outgoing;

import io.smallrye.reactive.messaging.mqtt.MqttMessage;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

public class MqttPublischer {

    @Inject @Channel("outgoing-channel")
    Emitter<String> emitter;


    //payload = Nutzlast
    public void send(String payload) {
        String topic = "itp/leonie/message";

        MqttMessage message = MqttMessage.of(topic, payload);
        emitter.send(message);
        sendToRasa(payload);
    }

    public String sendToRasa(String payload) {
        String topic = "itp/rasa/message";

        MqttMessage message = MqttMessage.of(topic, payload);
        emitter.send(message);
        return String.valueOf(message);
    }
}
