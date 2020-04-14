package com.javadevzone.javadb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.hibernate.FindRepository;
import com.javadevzone.javadb.modelo.Produto;

public class ProdutoRepository {

	private final Connection conn;
	private final FindRepository<Produto> findRepository;
	
	// Inversion of Control // Dependency Injection
	public ProdutoRepository(Connection conn) {
		this.conn = conn;
		this.findRepository = new FindRepository<>(conn){};
	}
	
	public void adicionaProduto(Produto produto) {		
		String sql = "insert into produto "
				+ "(nome, preco, descricao, quantidade, data_cadastro) "
				+ "values (?, ?, ?, ?, ?)";
		
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
//			statement.setString(1, produto.getNome());
//			statement.setDouble(2, produto.getPreco());
//			statement.setString(3, produto.getDescricao());
//			statement.setInt(4, produto.getQuantidade());
//			
//			LocalDateTime dataCadastro = produto.getDataCadastro();
//			statement.setTimestamp(5, Timestamp.valueOf(dataCadastro));
			
			statement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Produto> buscarTodos() {
		String sql = "select * from produto";
		
		return findRepository.listar(sql);
	}
	

}






