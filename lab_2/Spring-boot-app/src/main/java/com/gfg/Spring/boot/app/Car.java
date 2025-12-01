package com.gfg.Spring.boot.app;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("myCar")
@Scope("singleton") // по умолчанию, но явно укажем
public class Car {

	private String model;

	public Car() {
		System.out.println("Car: constructor called");
	}

	@PostConstruct
	public void init() {
		System.out.println("Car: init() method called");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Car: destroy() method called");
	}

	// Геттер
	public String getModel() {
		return model;
	}

	// Сеттер с @Value (можно и в конструкторе, но покажем сеттер)
	@Value("${car.model:NISSAN TERRANO}")
	public void setModel(String model) {
		System.out.println("Car: setModel('" + model + "') called");
		this.model = model;
	}
}
