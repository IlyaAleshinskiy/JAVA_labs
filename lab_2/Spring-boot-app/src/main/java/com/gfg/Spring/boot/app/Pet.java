package com.gfg.Spring.boot.app;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("myPet")
@Scope("singleton")
public class Pet {

	private String name;

	public Pet() {
		System.out.println("Pet: constructor called");
	}

	@PostConstruct
	public void init() {
		System.out.println("Pet: init() method called");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Pet: destroy() method called");
	}

	public String getName() {
		return name;
	}

	@Value("${pet.name:Chihuahua}")
	public void setName(String name) {
		System.out.println("Pet: setName('" + name + "') called");
		this.name = name;
	}
}
