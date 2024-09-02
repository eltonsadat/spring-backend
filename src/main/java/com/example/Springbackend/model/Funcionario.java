package com.example.Springbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Funcionario {

	private long id;
        private long cpf;
	private String nome;
	private String endereco;
	private String sexo;
	
	public Funcionario() {
		
	}
	
	public Funcionario(long cpf, String nome, String endereco, String sexo) {
		this.cpf = cpf;
                this.nome = nome;
		this.endereco = endereco;
		this.sexo = sexo;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "cpf_number", nullable = false)
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
        
        @Column(name = "full_name", nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "address", nullable = false)
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Column(name = "sex", nullable = false)
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Funcionariov2 [v2id=" + id +"CPF=" + cpf +", nome=" + nome + ", endereço=" + endereco + ", sexo=" + sexo
				+ "]";
	}
	
}
