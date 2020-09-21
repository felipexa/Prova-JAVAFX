package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoHSQLDB {
	
	private String usuario = "SA";
	private String senha = "";
	private String PathBase = "C:\\Users\\felii\\OneDrive - Complexo de Ensino Superior do Brasil LTDA\\4º SEMESTRE\\Programação Orientada a Objetos\\PROVA1BIMATUALIZADO\\PROVA1BIM\\Dados\\Cliente";
	private String URL = "jdbc:hsqldb:file:" + PathBase + ";";
	
	public Connection conectar() {		
		try {
			return DriverManager.getConnection(URL, usuario, senha);
		} catch (SQLException e) {
			throw new Error("SQLException");			
		}
	}
}
