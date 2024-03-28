package br.com.driver.springbootmssqljpahibernatedriver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.driver.springbootmssqljpahibernatedriver.exception.ResourceNotFoundException;
import br.com.driver.springbootmssqljpahibernatedriver.model.User;
import br.com.driver.springbootmssqljpahibernatedriver.repository.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/usuarios")
	public List<User> getAll()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<User> get(@PathVariable(value = "id") String userId)
			throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/usuarios")
	public User createEmployee(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
}
