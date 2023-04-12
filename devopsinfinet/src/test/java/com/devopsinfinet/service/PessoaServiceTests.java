package com.devopsinfinet.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.devopsinfinet.model.Pessoa;
import com.devopsinfinet.repository.PessoaRepository;

@SpringBootTest
public class PessoaServiceTests {

	@Mock
	private PessoaRepository pessoaRepository;

	@InjectMocks
	private PessoaService pessoaService;

	private Pessoa pessoa1;
	private Pessoa pessoa2;

	@Before
	public void setUp() {
		pessoa1 = new Pessoa("João", "12345678901", "joao@gmail.com");
		pessoa1.setId(1L);
		pessoa2 = new Pessoa("Maria", "23456789012", "maria@gmail.com");
		pessoa2.setId(2L);
	}

	@Test
	public void listarPessoas_deveRetornarListaDePessoas() {
		List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2);
		when(pessoaRepository.findAll()).thenReturn(pessoas);
		assertThat(pessoaService.listarPessoas()).isEqualTo(pessoas);
	}

	@Test
	public void mostrarPessoa_deveRetornarPessoaComIdEspecifico() {
		when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa1));
		assertThat(pessoaService.mostrarPessoa(1L)).isEqualTo(pessoa1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void mostrarPessoa_quandoPessoaNaoEncontrada_deveLancarExcecao() {
		when(pessoaRepository.findById(anyLong())).thenReturn(Optional.empty());
		pessoaService.mostrarPessoa(1L);
	}

	@Test
	public void salvarPessoa_deveSalvarNovaPessoa() {
		when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa1);
		assertThat(pessoaService.salvarPessoa(pessoa1)).isEqualTo(pessoa1);
	}

	@Test
	public void atualizarPessoa_deveAtualizarPessoaExistente() {
		when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa1));
		when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa1);
		Pessoa pessoaAtualizada = new Pessoa("João Silva", "12345678901", "joao.silva@gmail.com");
		pessoaAtualizada.setId(1L);
		assertThat(pessoaService.atualizarPessoa(1L, pessoaAtualizada)).isEqualTo(pessoaAtualizada);
	}

	@Test(expected = IllegalArgumentException.class)
	public void atualizarPessoa_quandoPessoaNaoEncontrada_deveLancarExcecao() {
		when(pessoaRepository.findById(anyLong())).thenReturn(Optional.empty());
		pessoaService.atualizarPessoa(1L, pessoa1);
	}

	@Test
	public void excluirPessoa_deveExcluirPessoaExistente() {
		when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa1));
		pessoaService.excluirPessoa(1L);
		verify(pessoaRepository, times(1)).delete(pessoa1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void excluirPessoa_quandoPessoaNaoEncontrada_deveLancarExcecao() {
		when(pessoaRepository.findById(anyLong())).thenReturn(Optional.empty());
		pessoaService.excluirPessoa(1L);
	}
}
