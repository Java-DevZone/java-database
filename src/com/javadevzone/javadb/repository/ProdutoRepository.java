package com.javadevzone.javadb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
			
			LocalDateTime dataCadastro = produto.getDataCadastro();
			statement.setTimestamp(5, Timestamp.valueOf(dataCadastro));
			
			statement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
