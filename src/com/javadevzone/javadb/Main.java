package com.javadevzone.javadb;

import java.sql.Connection;

import com.javadevzone.javadb.modelo.Produto;
import com.javadevzone.javadb.repository.ConnectionFactory;
import com.javadevzone.javadb.repository.ProdutoRepository;

public class Main {

	public static void main(String[] args) throws Exception {
		Produto produto = new Produto();
		produto.setNome("Redbull Summer");
		produto.setPreco(8.50);
		produto.setDescricao("Te dá asas");
		produto.setQuantidade(200);
		produto.setDataCadastro(new java.util.Date());
		
		Connection mysqlConnection = ConnectionFactory.getConnection();
		
		ProdutoRepository repository = new ProdutoRepository(mysqlConnection);
		repository.adicionaProduto(produto);
	}
	
}
