package com.gfg.Spring.boot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Основной класс приложения
@SpringBootApplication
public class SpringBootAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppApplication.class, args);
	}
}

// Контроллер с тремя маршрутами
@RestController
class WebController {

	private final Employee employee;
	public WebController(Employee employee) {
		this.employee = employee;
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(required = false) String name) {
		if (name != null && !name.isEmpty()) {
			return "Hello " + name + "!";
		}
		return "Hello world!";
	}

	@GetMapping("/about")
	public String about() {
		return "about us";
	}

	@GetMapping("/options")
	public String options(@RequestParam(required = false) String option) {
		if (option != null) {
			return "not an option";
		}
		return "options";
	}

	@GetMapping("/employee")
	public String getEmployeeInfo() {
		return employee.getInfo();
	}
}

// Компоненты для DI

@Component
class Car {
	public String getModel() {
		return "NISSAN TERRANO";
	}
}

@Component
class Pet {
	public String getName() {
		return "Chihuahua";
	}
}

@Component
class Employee {
	private final Car car;
	private final Pet pet;
	private final String name;
	private final int age;

	public Employee(Car car, Pet pet) {
		this.car = car;
		this.pet = pet;
		this.name = "Ilya Aleshinskiy";
		this.age = 22;
	}

	public String getInfo() {
		return String.format(
				"Employee: %s, %d years old. Car: %s. Pet: %s.",
				name, age, car.getModel(), pet.getName()
		);
	}
}