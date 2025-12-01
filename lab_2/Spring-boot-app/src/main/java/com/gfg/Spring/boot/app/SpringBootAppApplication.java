package com.gfg.Spring.boot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Чтобы использовать ТОЛЬКО конфигурационный класс, отключаем сканирование
// Или: закомментируйте @Component в классах и уберите excludeFilters
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

