package br.com.driver.springbootmssqljpahibernatedriver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import br.com.driver.springbootmssqljpahibernatedriver.dto.CarDto;
import br.com.driver.springbootmssqljpahibernatedriver.exception.ResourceNotFoundException;
import br.com.driver.springbootmssqljpahibernatedriver.model.Car;
import br.com.driver.springbootmssqljpahibernatedriver.model.User;
import br.com.driver.springbootmssqljpahibernatedriver.repository.CarRepository;
import br.com.driver.springbootmssqljpahibernatedriver.repository.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CarController {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/carros")
	public List<Car> getAll() {
		return carRepository.findAll();
	}

	@GetMapping("/carros/{id}")
	public ResponseEntity<Car> get(@PathVariable(value = "id") String carId) throws ResourceNotFoundException {
		Car car = carRepository.findById(carId)
				.orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado!"));
		return ResponseEntity.ok().body(car);
	}

	@PostMapping("/carros")
	public Map<String, Boolean> create(@Valid @RequestBody CarDto carDto) throws ResourceNotFoundException {
		Car car = new Car();
		var userId = carDto.getUsuarioId();

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

		car.setUser(user);
		car.setMarca(carDto.getMarca());
		car.setModelo(carDto.getModelo());
		car.setCor(carDto.getCor());
		carRepository.save(car);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("created", Boolean.TRUE);
		return response;
	}

	@PutMapping("/carros/{id}")
	public Map<String, Boolean> update(@PathVariable(value = "id") String carId, @Valid @RequestBody CarDto carDto)
			throws ResourceNotFoundException {
		Car car = carRepository.findById(carId)
				.orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado!"));

		var userId = carDto.getUsuarioId();

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

		car.setUser(user);
		car.setMarca(carDto.getMarca());
		car.setModelo(carDto.getModelo());
		car.setCor(carDto.getCor());
		carRepository.save(car);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("updated", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/carros/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") String carId) throws ResourceNotFoundException {
		Car car = carRepository.findById(carId)
				.orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado!"));

		carRepository.delete(car);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
