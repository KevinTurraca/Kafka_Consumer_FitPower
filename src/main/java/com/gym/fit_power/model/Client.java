package com.gym.fit_power.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

	private Long id;
	private String cuit;
	private Object assignedGym;
	private List<Object> routines;
	private List<Object> plans;
	private String name;
	private String lastname;
	private String email;
	private String phone;
	private LocalDate birthDate;
	private Boolean enabled;
}