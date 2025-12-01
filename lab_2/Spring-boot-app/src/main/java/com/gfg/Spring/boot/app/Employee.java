package com.gfg.Spring.boot.app;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Employee {

	private Car car;
	private Pet pet;
	private String name;
	private int age;

	public Employee() {
		System.out.println("Employee: constructor called");
	}

	@PostConstruct
	public void init() {
		System.out.println("Employee: init() method called");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Employee: destroy() method called");
	}

	// DI через сеттеры с @Autowired и @Qualifier
	@Autowired
	@Qualifier("myCar")
	public void setCar(Car car) {
		System.out.println("Employee: setCar() called");
		this.car = car;
	}

	@Autowired
	@Qualifier("myPet")
	public void setPet(Pet pet) {
		System.out.println("Employee: setPet() called");
		this.pet = pet;
	}

	@Value("${employee.name:Ilya Aleshinskiy}")
	public void setName(String name) {
		System.out.println("Employee: setName('" + name + "') called");
		this.name = name;
	}

	@Value("${employee.age:22}")
	public void setAge(int age) {
		System.out.println("Employee: setAge(" + age + ") called");
		this.age = age;
	}

	public String getInfo() {
		return String.format(
				"Employee: %s, %d years old. Car: %s. Pet: %s.",
				name, age, car.getModel(), pet.getName()
		);
	}
}
