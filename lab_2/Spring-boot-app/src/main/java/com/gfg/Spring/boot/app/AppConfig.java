package com.gfg.Spring.boot.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("singleton")
    public Car myCar(@Value("${car.model}") String model) {
        System.out.println("AppConfig: creating Car bean");
        Car car = new Car();
        car.setModel(model); // вручную вызываем сеттер
        return car;
    }

    @Bean
    @Scope("singleton")
    public Pet myPet(@Value("${pet.name}") String name) {
        System.out.println("AppConfig: creating Pet bean");
        Pet pet = new Pet();
        pet.setName(name);
        return pet;
    }

    @Bean
    @Scope("singleton")
    public Employee employee(Car car, Pet pet,
                             @Value("${employee.name}") String name,
                             @Value("${employee.age}") int age) {
        System.out.println("AppConfig: creating Employee bean");
        Employee emp = new Employee();
        emp.setCar(car);
        emp.setPet(pet);
        emp.setName(name);
        emp.setAge(age);
        return emp;
    }
}