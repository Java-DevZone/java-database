package com.javadevzone.javadb.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Produto {

	private int id;
	private String nome;
	private Double preco;
	private String descricao;
	private int quantidade;
	private LocalDateTime dataCadastro;

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", descricao=" + descricao
				+ ", quantidade=" + quantidade + ", dataCadastro=" + dataCadastro + "]";
	}
	
}
