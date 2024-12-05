package com.gym.fit_power.notification;

import com.gym.fit_power.notification.event.ClientCreatedEvent;
import com.gym.fit_power.notification.event.ClientUpdatedEvent;
import com.gym.fit_power.notification.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientEventsNotification {

    private final MailSenderService mailSender;

    public ClientEventsNotification(MailSenderService mailSender){
        this.mailSender = mailSender;
    }

    @KafkaListener(topics = "${topic.client.name:clients}",
            containerFactory = "kafkaListenerContainerFactory", groupId = "group1")
    public void clientEventConsumer(Event<?> event) {
        if (event.getClass().isAssignableFrom(ClientCreatedEvent.class)) {
            ClientCreatedEvent createdEvent = (ClientCreatedEvent) event;
            log.info("Received created event for client with Id={}, data={}",
                    createdEvent.getId(), createdEvent.getEntity().toString());
            mailSender.send(createdEvent.getEntity().getEmail(),
                    "Bienvenido a FitPower!!",
                    "Se ha recibido la solicitud de creación para un nuevo cliente en la aplicación FitPower\n" +
                            "Si desconoce esta solicitud por favor comuníquese con el servicio técnico.");
        } else if (event.getClass().isAssignableFrom(ClientUpdatedEvent.class)) {
            ClientUpdatedEvent updatedEvent = (ClientUpdatedEvent) event;
            log.info("Received updated event for client with Id={}, data={}",
                    updatedEvent.getId(), updatedEvent.getEntity().toString());
            mailSender.send(updatedEvent.getEntity().getEmail(),
                    "Actualización de Usuario FitPower",
                    "Se ha recibido la solicitud de actualización para el cliente "+ updatedEvent.getEntity().getCuit() +
                            "  en la aplicación FitPower\n" +
                            "Si desconoce esta solicitud por favor comuníquese con el servicio técnico.");
        }
    }

}
