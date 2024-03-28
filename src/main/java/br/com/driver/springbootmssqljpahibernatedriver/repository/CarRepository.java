package br.com.driver.springbootmssqljpahibernatedriver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.driver.springbootmssqljpahibernatedriver.model.Car;

public interface CarRepository extends JpaRepository<Car, String>{

}