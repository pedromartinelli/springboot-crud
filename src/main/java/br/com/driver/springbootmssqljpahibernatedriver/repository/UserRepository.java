package br.com.driver.springbootmssqljpahibernatedriver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.driver.springbootmssqljpahibernatedriver.model.User;

public interface UserRepository extends JpaRepository<User, String>{

}