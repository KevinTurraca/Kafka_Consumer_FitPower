package com.gym.fit_power.service;

import com.gym.fit_power.dto.event.CustomerCreatedEventDTO;
import com.gym.fit_power.dto.event.CustomerUpdatedEventDTO;
import com.gym.fit_power.dto.event.TrainerCreatedEventDTO;
import com.gym.fit_power.dto.event.TrainerUpdatedEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@KafkaListener(topics = {"customer-events", "trainer-events"}, groupId = "notification-group")
public class ConsumerNotificationService {

    private final MailSenderService mailSenderService;

    private static final String WELCOME_SUBJECT = "Bienvenido a FitPower!!";
    private static final String UPDATE_SUBJECT = "Actualización de perfil en FitPower!!";
    private static final String FOOTER_TEXT = "\nSi desconoce esta solicitud por favor comuníquese con el servicio técnico.";

    @KafkaHandler
    public void handleCustomerEvent(CustomerCreatedEventDTO event){
        log.info("Received customer creation event: {}", event);
        // enviar email
        mailSenderService.send(
                event.getEmail(),
                WELCOME_SUBJECT,
                "Se ha recibido la solicitud de creación para un nuevo cliente en la aplicación FitPower." + FOOTER_TEXT
        );
    }

    @KafkaHandler
    public void handleCustomerEvent(CustomerUpdatedEventDTO event){
        log.info("Received customer update event: {}", event);
        // enviar email
        mailSenderService.send(
                event.getEmail(),
                UPDATE_SUBJECT,
                "Se ha recibido la solicitud de actualización para el cliente " + event.getCuit() + " en la aplicación FitPower." + FOOTER_TEXT
        );
    }

    @KafkaHandler
    public void handleTrainerEvent(TrainerCreatedEventDTO event){
        log.info("Received trainer creation event: {}", event);
        // enviar email
        mailSenderService.send(
                event.getEmail(),
                "Bienvenido a FitPower, Entrenador!!", // Asunto personalizado
                "Se ha recibido la solicitud de creación para un nuevo entrenador en la aplicación FitPower." + FOOTER_TEXT
        );
    }

    @KafkaHandler
    public void handleTrainerEvent(TrainerUpdatedEventDTO event){
        log.info("Received trainer update event: {}", event);
        // enviar email
        mailSenderService.send(
                event.getEmail(),
                UPDATE_SUBJECT,
                "Se ha recibido la solicitud de actualización para el entrenador " + event.getCuit() + " en la aplicación FitPower." + FOOTER_TEXT
        );
    }
}
