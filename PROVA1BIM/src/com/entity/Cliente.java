package com.entity;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Cliente {

	private int id;
	private String nome;
	private String cpf;
	private Date dataNascimento;
	private Float salario;
	
	
	public Cliente() {
		
	}
	public Cliente(String nome, String cpf, Date dataNascimento, Float salario) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
	}
	
	
	
	
	public Cliente(int id, String nome, String cpf, Date dataNascimento, Float salario) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public Date getNascimento() {
		return dataNascimento;
	}
	public void setNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Float getSalario() {
		return salario;
	}
	public void setSalario(Float salario) {
		this.salario = salario;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	@Override
	public String toString() {
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		return "- Cliente ID: " + id + " " + "\n" 
				+ "     • Nome Cliente: " + nome + "\n"  + "     • CPF: " + cpf + "\n" + "     • Data de Nascimento: " + dt.format(dataNascimento) + "\n" + "     • Salário: " + salario + "\n" ;
	}
	
}
