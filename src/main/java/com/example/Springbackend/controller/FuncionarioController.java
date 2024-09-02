package com.example.Springbackend.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Springbackend.exception.ResourceNotFoundException;
import com.example.Springbackend.model.Funcionario;
import com.example.Springbackend.repository.FuncionarioRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class FuncionarioController {
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@GetMapping("/funcionarios")
	public List<Funcionario> getAllFuncionarios() {
		return funcionarioRepository.findAll();
	}

	@GetMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable(value = "id") Long funcionarioId)
			throws ResourceNotFoundException {
		Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Funcionario com id nao existente::: " + funcionarioId));
		return ResponseEntity.ok().body(funcionario);
	}

	@PostMapping("/funcionarios")
	public Funcionario createFuncionario(@Valid @RequestBody Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	@PutMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> updateFuncionario(@PathVariable(value = "id") Long funcionarioId,
			@Valid @RequestBody Funcionario funcionarioDetails) throws ResourceNotFoundException {
		Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Funcionario com id nao existente::: " + funcionarioId));

		funcionario.setCpf(funcionarioDetails.getCpf());
                funcionario.setSexo(funcionarioDetails.getSexo());
		funcionario.setEndereco(funcionarioDetails.getEndereco());
		funcionario.setNome(funcionarioDetails.getNome());
		final Funcionario updateFuncionario = funcionarioRepository.save(funcionario);
		return ResponseEntity.ok(updateFuncionario);
	}

	@DeleteMapping("/funcionarios/{id}")
	public Map<String, Boolean> deleteFuncionario(@PathVariable(value = "id") Long funcionarioId)
			throws ResourceNotFoundException {
		Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Funcionario com id nao existente::: " + funcionarioId));

		funcionarioRepository.delete(funcionario);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
