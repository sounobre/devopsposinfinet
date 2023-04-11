package com.devopsinfinet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devopsinfinet.model.Pessoa;
import com.devopsinfinet.service.PessoaService;

@RestController
@RequestMapping

public class PessoaController {
	@Autowired
	private PessoaService pessoaService;

	@GetMapping("/pessoas")
	public List<Pessoa> listarPessoas() {
	    return pessoaService.listarPessoas();
	}

	@GetMapping("/pessoas/{id}")
	public ResponseEntity<Pessoa> mostrarPessoa(@PathVariable Long id) {
	    Pessoa pessoa = pessoaService.mostrarPessoa(id);
	    return ResponseEntity.ok(pessoa);
	}

	@PostMapping("/pessoas")
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody Pessoa pessoa) {
	    Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa);
	    return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}

	@PutMapping("/pessoas/{id}")
	public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
	    Pessoa pessoaAtualizada = pessoaService.atualizarPessoa(id, pessoa);
	    return ResponseEntity.ok(pessoaAtualizada);
	}

	@DeleteMapping("/pessoas/{id}")
	public ResponseEntity<Void> excluirPessoa(@PathVariable Long id) {
	    pessoaService.excluirPessoa(id);
	    return ResponseEntity.noContent().build();
	}
}
