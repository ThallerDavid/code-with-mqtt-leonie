package htl.leonding.outgoing;

import io.smallrye.reactive.messaging.mqtt.MqttMessage;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;

public class MqttPublischer {

    @Inject @Channel("outgoing-channel")
    Emitter<String> emitter;

    public void send(String payload) {
        String topic = "itp/leonie/thaller";

        MqttMessage message = MqttMessage.of(topic, payload);
        emitter.send(message);
    }
}