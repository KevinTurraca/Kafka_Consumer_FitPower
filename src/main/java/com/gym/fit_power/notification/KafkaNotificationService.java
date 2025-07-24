package com.gym.fit_power.notification;

import com.gym.fit_power.model.KafkaNutritionNotification;
import com.gym.fit_power.model.KafkaTrainingNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaNotificationService {

    private final MailSenderService mailSender;

    public KafkaNotificationService(MailSenderService mailSender){
        this.mailSender = mailSender;
    }

    @KafkaListener(topics = "${topic.client.name:nutrition}",
            containerFactory = "kafkaListenerContainerFactory", groupId = "group1")
    public void nutritionNotificationsConsumer(KafkaNutritionNotification nutritionNotification) {
            log.info("Received nutrition notification {} for client with Cuit={}",
                    nutritionNotification.getId(), nutritionNotification.getClient().getCuit());
            mailSender.send(nutritionNotification.getClient().getEmail(),
                    nutritionNotification.getReason(), nutritionNotification.getMessage()
                            + "\n\n\tenviado por " + nutritionNotification.getSender().getLastname()
                            + " " + nutritionNotification.getSender().getName());
        }

    @KafkaListener(topics = "${topic.client.name:train}",
            containerFactory = "kafkaListenerContainerFactory", groupId = "group1")
    public void trainingNotificationsConsumer(KafkaTrainingNotification trainingNotification) {
            log.info("Received training notification {} for client with Cuit={}",
                    trainingNotification.getId(), trainingNotification.getClient().getCuit());
            mailSender.send(trainingNotification.getClient().getEmail(),
                    trainingNotification.getReason(), trainingNotification.getMessage());
        }

}
