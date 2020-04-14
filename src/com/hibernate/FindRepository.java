package com.hibernate;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindRepository<T> {

	private final Connection conn;
	
	public FindRepository(Connection conn) {
		this.conn = conn;
	}
	
	public List<T> listar(String sql) {
		try (Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery(sql);
			
			List<T> linhas = new ArrayList<>();
			
			Class<T> clazz = (Class<T>)
					((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
			
			Field[] declaredFields = clazz.getDeclaredFields();
			
			while (resultSet.next()) {
				T elemento = clazz.newInstance();
				
				for (Field field : declaredFields) {
					field.setAccessible(true);
					
					if (field.getType() == LocalDateTime.class) {
						Timestamp dateField = resultSet.getTimestamp(fieldToColumnName(field));
						field.set(elemento, dateField.toLocalDateTime());
					} else if (field.getType() == Double.class) {
						field.set(elemento, 
								resultSet.getDouble(fieldToColumnName(field))
						);
					} else {
						field.set(elemento, 
								resultSet.getObject(fieldToColumnName(field))
						);
					}					
				}
				
				linhas.add(elemento);
			}
			
			return linhas;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String fieldToColumnName(Field field) {
		String fieldName = field.getName();
		
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher matcher = pattern.matcher(fieldName);
		while (matcher.find()) {
			String caractere = matcher.group();
			fieldName = fieldName
					.replace(caractere, "_"+caractere.toLowerCase());
		}
		
		return fieldName;
	}
	
}














