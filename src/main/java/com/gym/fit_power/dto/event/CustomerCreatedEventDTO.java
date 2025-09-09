package com.gym.fit_power.dto.event;

import lombok.Data;

@Data
public class CustomerCreatedEventDTO {
    private String name;
    private String lastname;
    private String cuit;
    private String email;
}
