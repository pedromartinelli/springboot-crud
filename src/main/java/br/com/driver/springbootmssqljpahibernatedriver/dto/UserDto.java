package br.com.driver.springbootmssqljpahibernatedriver.dto;

import lombok.Data;

@Data
public class UserDto {
	private String nome;
	private String cpf;
	private String idade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}
}
