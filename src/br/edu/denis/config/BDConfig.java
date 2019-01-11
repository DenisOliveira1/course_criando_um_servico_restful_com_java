package br.edu.denis.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class BDConfig {
	
	//metodos
	public static Connection getConnection() throws SQLException, ClassNotFoundException{//esse metodo retorna a conexao 	
		String user = "root";
		String pass = "root";	
		String tabela = "notes_db";
		String url = "jdbc:mysql://localhost:3306/";
		String fix = "?useTimezone=true&serverTimezone=UTC&useSSL=false";
		
		Class.forName("com.mysql.cj.jdbc.Driver");//o driver trabalha com qualquer banco de dados.Essa linha é uma garantia de que o drivermanager reconhecera que o mysql que sera usado
		return DriverManager.getConnection(url+tabela+fix,user,pass);//cria uma conexao e retorna ela
	}
}
