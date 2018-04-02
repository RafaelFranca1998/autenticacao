package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Usuario;

public class UsuarioDAO {
	private DataSource dataSource;
	public Connection connection;
	public PreparedStatement stmt;
//#################################################################################################
	/**
	 * Construtor da Classe
	 * 
	 * **/
	public UsuarioDAO(DataSource datasource) {
		this.dataSource = datasource;
	}
//#################################################################################################
	/**
	 * Checa se existe o usuário e Senha no Banco de dados.
	 * Retorna um booleano.
	 * * **/
	public Boolean checkLogin(String login, int senha) {
		Boolean check = false;	
		try {
			PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT * FROM tb_usuario WHERE login = ? AND senha = ?");
			ps.setString(1, login);
			ps.setInt(2, senha);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				check = true;
			}
		} catch (SQLException e) {
			System.err.println("Erro na Listagem " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Erro Geral " + e.getMessage());
		}
		return check;
	}
//#################################################################################################
	/**
	 * Checa se existe o usuário e Senha no Banco de dados e retorna o Tipo de Usuário.
	 * Retorna um Valor Inteiro.
	 * * **/
	public int checkTipo(String login, int senha) {
		int check = -1 ;
		Usuario usuario = new Usuario();
		try {
			PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT * FROM tb_usuario WHERE login = ? AND senha = ?");
			ps.setString(1, login);
			ps.setInt(2, senha);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				usuario.setTipo(rs.getInt("tipo"));
				check = usuario.getTipo();
			}
		} catch (SQLException e) {
			System.err.println("Erro na Listagem " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Erro Geral " + e.getMessage());
		}
		return check;
	}
//#################################################################################################
	/**
	 * Lista Todos os usuários cadastrados no Banco de dados.
	 * Retorna um ArrayList com a lista de Usuários.
	 * * **/
	public ArrayList<Usuario> ListarLogin() {
		try {
			String SQL = "select * from tb_usuario";
			PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			ArrayList<Usuario> Lista = new ArrayList<Usuario>();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getInt("senha"));
				usuario.setTipo(rs.getInt("tipo"));
				Lista.add(usuario);
			}
			ps.close();
			return Lista;
		} catch (SQLException e) {
			System.err.println("Erro na Listagem " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Erro Geral " + e.getMessage());
		}
		return null;
	}
	
	//#################################################################################################
	/**
	 * Insere Usuários no Banco de dados.
	 * * **/
	public void create(Usuario U) {
		Connection con = dataSource.getConnection();
		PreparedStatement stmp = null;
		try {
			stmp = con.prepareStatement("insert into tb_usuario(id,login,senha,tipo) values (?,?,?,?)");
			stmp.setInt(1, U.getId());
			stmp.setString(2, U.getLogin());
			stmp.setInt(3, U.getSenha());
			stmp.setInt(4, U.getTipo());
			stmp.executeUpdate();
			System.out.println("Sucesso!");
		} catch (SQLException u) {
			throw new RuntimeException(u);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
//#################################################################################################
	/**
	 * Deleta Usuários do Banco de dados Com base no ID informado.
	 * * **/
	public void delete(Usuario U) {
		Connection con = dataSource.getConnection();
		PreparedStatement stmp = null;
		try {
			stmp = con.prepareStatement("DELETE FROM tb_usuario WHERE id= ?");
			stmp.setInt(1, U.getId());
			stmp.executeUpdate();
			System.out.println("Sucesso!");
		} catch (SQLException u) {
			throw new RuntimeException(u);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void orderId(int id, int row) {
					PreparedStatement ps;
					try {
						ps = dataSource.getConnection().prepareStatement("UPDATE tb_usuario set id= ? WHERE id = ?");
						ps.setInt(1, row);
						ps.setInt(2, id);
						ps.executeUpdate();
					} catch (SQLException e) {
						System.err.println("Erro SQL em orderID"+ e);
					}
	}
}