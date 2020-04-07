package com.javadevzone.javadb;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.javadevzone.javadb.modelo.Produto;
import com.javadevzone.javadb.repository.ConnectionFactory;
import com.javadevzone.javadb.repository.ProdutoRepository;

public class Main {

	public static void main(String[] args) throws Exception {
		Produto produto = new Produto();
		produto.setNome("Curso Java to Backend Developers");
		produto.setPreco(800.50);
		produto.setDescricao("Aprenda a ser um Beckend Developer em Java");
		produto.setQuantidade(1);
		produto.setDataCadastro(
			LocalDateTime
				.now()
				.atZone(ZoneId.of("America/Sao_Paulo"))
				.toLocalDateTime()
		);
		
		System.out.println(produto.getDataCadastro());
		
		Connection mysqlConnection = ConnectionFactory.getConnection();
		
		ProdutoRepository repository = new ProdutoRepository(mysqlConnection);
		repository.adicionaProduto(produto);
		System.out.println("Produto inserido com sucesso!");
	}
	
}
