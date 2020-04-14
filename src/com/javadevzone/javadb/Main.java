package com.javadevzone.javadb;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.javadevzone.javadb.modelo.Produto;
import com.javadevzone.javadb.repository.ConnectionFactory;
import com.javadevzone.javadb.repository.ProdutoRepository;

public class Main {

	static Connection mysqlConnection = ConnectionFactory.getConnection();
	
	public static void main(String[] args) throws Exception {
//		inserirProduto();
		
		listarProdutos();
	}
	
	static void listarProdutos() {
		ProdutoRepository repository = new ProdutoRepository(mysqlConnection);
		List<Produto> produtos = repository.buscarTodos();
		produtos.forEach(System.out::println);
	}
	
	static void inserirProduto() {
		Produto produto = new Produto();
//		produto.setNome("Curso Java to Backend Developers");
//		produto.setPreco(800.50);
//		produto.setDescricao("Aprenda a ser um Beckend Developer em Java");
//		produto.setQuantidade(1);
//		produto.setDataCadastro(
//			LocalDateTime
//				.now()
//				.atZone(ZoneId.of("America/Sao_Paulo"))
//				.toLocalDateTime()
//		);
		
		ProdutoRepository repository = new ProdutoRepository(mysqlConnection);
		repository.adicionaProduto(produto);
		
		System.out.println("Produto inserido com sucesso!");
	}
	
}
