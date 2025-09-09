package com.gym.fit_power.service;

import com.gym.fit_power.dto.CustomerCreatedEventDTO;
import com.gym.fit_power.dto.CustomerUpdatedEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerNotificationService {

    private final MailSenderService mailSenderService;

    @KafkaListener(topics = "customer-events", groupId = "notification-group")
    public void handleCustomerEvent(CustomerCreatedEventDTO event){
        if (event != null){
            log.info("Received customer creation event: {}", event);
            // enviar email
            mailSenderService.send(
                    event.getEmail(),
                    "Bienvenido a FitPower!!",
                    "Se ha recibido la solicitud de creación para un nuevo cliente en la aplicación FitPower.\n" +
                            "Si desconoce esta solicitud por favor comuníquese con el servicio técnico."
            );
        }
    }

    @KafkaListener(topics = "customer-events", groupId = "notification-group")
    public void handleCustomerEvent(CustomerUpdatedEventDTO event){
        if (event != null){
            log.info("Received customer creation event: {}", event);
            // enviar email
            mailSenderService.send(
                    event.getEmail(),
                    "Actualización de usuario FitPower!!",
                    "Se ha recibido la solicitud de actualización para el cliente "+ event.getCuit() + " en la aplicación FitPower\n" +
                            "Si desconoce esta solicitud por favor comuníquese con el servicio técnico."
            );
        }
    }
}
