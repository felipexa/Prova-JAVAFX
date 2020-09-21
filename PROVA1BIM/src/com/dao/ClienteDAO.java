package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.db.ConexaoHSQLDB;
import com.entity.Cliente;

public class ClienteDAO extends ConexaoHSQLDB {
	final String SQL_INSERT_Cliente = "INSERT INTO Cliente(NOME, CPF, NASCIMENTO, SALARIO) VALUES ( ?, ?, ?, ?)";
	final String SQL_SELECT_Cliente = "SELECT * FROM Cliente";
	final String SQL_SELECT_Cliente_ID = "SELECT * FROM Cliente WHERE ID = ?";
	final String SQL_ALTERA_Cliente = "UPDATE Cliente SET NOME=?, CPF=?, NASCIMENTO=?, SALARIO=? WHERE ID = ?";
	final String SQL_DELETA_Cliente = "DELETE FROM Cliente WHERE ID = ?";
	
	public int inserir(Cliente Cliente) {
		int quantidade = 0;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_INSERT_Cliente);) {
			pst.setString(1, Cliente.getNome());
			pst.setString(2, Cliente.getCpf());
			pst.setDate(3, Cliente.getNascimento());
			pst.setFloat(4, Cliente.getSalario());
			quantidade = pst.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantidade;
	}
	
	public int alterar(Cliente Cliente) {
		int quantidade = 0;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_Cliente);) {
			pst.setString(1, Cliente.getNome());
			pst.setString(2, Cliente.getCpf());
			pst.setDate(3, Cliente.getNascimento());
			pst.setFloat(4, Cliente.getSalario());			
			pst.setInt(5, Cliente.getId());
			quantidade = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantidade;
	}
	
	public int deletar(int id) {
        int quantidade = 0;
        try (Connection connection = this.conectar();
                PreparedStatement pst = connection.prepareStatement(SQL_DELETA_Cliente);) {
            pst.setInt(1, id);
            quantidade = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return quantidade;
    }
	
	
	public List<Cliente> listar(){
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_Cliente);){

			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Cliente Cliente = new Cliente();
				
				Cliente.setId(rs.getInt("ID"));
				Cliente.setNome(rs.getString("NOME"));
				Cliente.setCpf(rs.getString("CPF"));
				Cliente.setNascimento(rs.getDate("NASCIMENTO"));
				Cliente.setSalario(rs.getFloat("SALARIO"));		
				
				listaClientes.add(Cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return listaClientes;
	}



	public Cliente buscar(int id) {
		Cliente Cliente = null;
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_Cliente_ID);){

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Cliente = new Cliente();
				
				Cliente.setId(rs.getInt("ID"));
				Cliente.setNome(rs.getString("NOME"));
				Cliente.setCpf(rs.getString("CPF"));
				Cliente.setNascimento(rs.getDate("NASCIMENTO"));
				Cliente.setSalario(rs.getFloat("SALARIO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return Cliente;
	}



	

	

}
