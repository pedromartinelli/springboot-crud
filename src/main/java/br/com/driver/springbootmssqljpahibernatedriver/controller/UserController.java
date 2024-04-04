package br.com.driver.springbootmssqljpahibernatedriver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.driver.springbootmssqljpahibernatedriver.dto.UserDto;
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
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<User> get(@PathVariable(value = "id") String userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/usuarios")
	public Map<String, Boolean> create(@Valid @RequestBody UserDto userDto) {
		User user = new User();
		user.setNome(userDto.getNome());
		user.setCpf(userDto.getCpf());
		user.setIdade(Integer.parseInt(userDto.getIdade()));
		userRepository.save(user);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("created", Boolean.TRUE);
		return response;
	}

	@PutMapping("/usuarios/{id}")
	public Map<String, Boolean> update(@PathVariable(value = "id") String userId, @Valid @RequestBody UserDto userDto)
			throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
		
		user.setNome(userDto.getNome());
		user.setCpf(userDto.getCpf());
		user.setIdade(Integer.parseInt(userDto.getIdade()));
		userRepository.save(user);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("updated", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/usuarios/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") String userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
