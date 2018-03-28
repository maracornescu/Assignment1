package dataAccess.repository;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccess.ConnectionFactory;


public class GenericRepository<T>{
	
	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public GenericRepository() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	private String createSelectQuery(String field) {
		return "SELECT * FROM `" + type.getSimpleName().replace("Dto", "") + "` WHERE " + type.getSimpleName().replace("Dto", "") + field + " = ?"; 
	}
	
	private String createFindAllQuery() {	
		return "SELECT * FROM `" + type.getSimpleName().replace("Dto", "") + "`"; 
	}
	
	private String createDeleteQuery(String field) {	
		return "DELETE FROM `" + type.getSimpleName().replace("Dto", "") + "` WHERE " + type.getSimpleName().replace("Dto", "") + field + " =?"; 
	}
	
	private String createUpdateQuery(String field, T t) {
		String query =  new String();
		
		query = "UPDATE `" + type.getSimpleName().replace("Dto", "") + "` SET ";
		
		for (Field fields : t.getClass().getDeclaredFields()) {
			fields.setAccessible(true); 
			Object value;
			try {
				value = fields.get(t);
				query += fields.getName() + "='" + value;
				query += "', ";

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		String finalQuery = query.substring(0, query.length() - 2);
		finalQuery += " WHERE " + type.getSimpleName().replace("Dto", "") + field + "=?";
		
		return finalQuery;
		
	}
	
	private String createInsertQuery(T t) {
		String query =  new String();
		
		query = "INSERT INTO `" + type.getSimpleName().replace("Dto", "") + "` (";
		
		for (Field fields : t.getClass().getDeclaredFields()) {
			fields.setAccessible(true); 
			try {
				query += fields.getName();
				query += ", ";

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		
		String nextQuery = query.substring(0, query.length() - 2);
		nextQuery += ") VALUES (";
		
		for (Field fields : t.getClass().getDeclaredFields()) {
			fields.setAccessible(true); 
			Object value;
			try {
				value = fields.get(t);
				nextQuery +="'" + value;
				nextQuery += "', ";

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		String finalQuery = nextQuery.substring(0, nextQuery.length() - 2);
		finalQuery += ")";
		
		System.out.println(finalQuery);
		
		return finalQuery;
		
	}


	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("Id");
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	public List<T> findAll() {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createFindAllQuery();
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
		
			return createObjects(resultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	
	public void deleteById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createDeleteQuery("ID");
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	
	}

	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();

		try {
			while (resultSet.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					
					Object value = resultSet.getObject(field.getName());
					//System.out.println(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					
					try {
						method.invoke(instance, value);
					} catch (Exception e) {
						value = resultSet.getByte(field.getName());
						method.invoke(instance, value);
					}
				}
				list.add(instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	public void insert(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createInsertQuery(t);
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		};
	}

	public void update(int id, T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createUpdateQuery("ID" , t);
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		};
	}
}

