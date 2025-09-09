package com.gym.fit_power.dto.event;

import lombok.Data;

@Data
public class CustomerUpdatedEventDTO {
    private String name;
    private String lastname;
    private String cuit;
    private String email;
}
