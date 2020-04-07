package com.javadevzone.javadb.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import com.javadevzone.javadb.modelo.Produto;

public class ProdutoRepository {

	private final Connection conn;
	
	public ProdutoRepository(Connection conn) {
		this.conn = conn;
		// Inversion of Control // Dependency Injection
	}
	
	public void adicionaProduto(Produto produto) {		
		String sql = "insert into produto "
				+ "(nome, preco, descricao, quantidade, data_cadastro) "
				+ "values (?, ?, ?, ?, ?)";
		
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, produto.getNome());
			statement.setDouble(2, produto.getPreco());
			statement.setString(3, produto.getDescricao());
			statement.setInt(4, produto.getQuantidade());
			
			Date dataCadastro = new Date(produto.getDataCadastro().getTime());
			statement.setDate(5, dataCadastro);
			
			statement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
