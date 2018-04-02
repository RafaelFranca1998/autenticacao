package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	// ATRIBUTOS
	// -------------------------------------------------------------------------------------
	private String hostName;
	private int port;
	private String dataBase;
	private String userName;
	private String password;
	private Connection connection;

	// CONSTRUTOR E METODOS
	// -------------------------------------------------------------------------------------
	/**
	 * Define e estabelece Conexão com um banco de Dados
	 **/
	public DataSource() {
		hostName = "127.0.0.1";
		port = 3306;
		dataBase = "dbteste";
		userName = "root";
		password = "32612421";

		try {
			String url = "jdbc:mysql://" + hostName + ":" + port + "/" + dataBase;
			connection = DriverManager.getConnection(url, userName, password);
			System.out.println("Conexão Efetuada");
		} catch (SQLException e) {
			System.err.println("Erro ao Conectar no DataSource" + e);
		} catch (Exception e) {
			System.out.println("Erro Geral no DataSource " + e);
		}
	}

	public Connection getConnection() {
		return connection;
	}

	/**
	 * Encerra a Conexão com o Banco de dados.
	 **/
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Conexão encerrada!!");
		} catch (SQLException e) {
			System.err.println("Não fechou " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Erro diverso " + e.getMessage());
		}
	}
}