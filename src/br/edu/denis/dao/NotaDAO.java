package br.edu.denis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.denis.config.BDConfig;
import br.edu.denis.entidade.Nota;

public class NotaDAO {
	/**
	 * execute - nao retorna resultset executeQuery - retorna resultset
	 */

//metodos
	// listar tudo
	public List<Nota> listarNotas() throws ClassNotFoundException, SQLException {
		List<Nota> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();// cria conexao

		String sql = "SELECT * FROM TB_NOTA";// comando

		PreparedStatement statement = conexao.prepareStatement(sql);// prepara a execuçao
		ResultSet rs = statement.executeQuery();// executa a execucao e recebe a resposta

		while (rs.next()) {// enquanto ainda houver itens converte resultset para objetos java
			Nota nota = new Nota();
			nota.setId(rs.getInt("ID_NOTE"));
			nota.setTitulo(rs.getString("TITULO"));
			nota.setDescricao(rs.getString("DESCRICAO"));
			lista.add(nota);
		}
		return lista;
	}

	// buscar por id
	public Nota buscarNotaPorId(int idNota) throws ClassNotFoundException, SQLException {
		Nota nota = null;

		Connection conexao = BDConfig.getConnection();// cria conexao

		String sql = "SELECT * FROM TB_NOTA WHERE ID_NOTe = ?";// comando
		PreparedStatement statement = conexao.prepareStatement(sql);// prepara a execuçao
		statement.setInt(1, idNota);
		ResultSet rs = statement.executeQuery();// executa a execucao e recebe a resposta

		if (rs.next()) {// se houver um item com esse id converte resultset para objetos java
			nota = new Nota();
			nota.setId(rs.getInt("ID_NOTE"));
			nota.setTitulo(rs.getString("TITULO"));
			nota.setDescricao(rs.getString("DESCRICAO"));
		}
		return nota;
	}

	// adicionar
	public int adicionarNota(Nota nota) throws ClassNotFoundException, SQLException {

		int idGerado = 0;
		Connection conexao = BDConfig.getConnection();// cria conexao

		String sql = "INSERT INTO TB_NOTA(TITULO,DESCRICAO) VALUES(?,?)";// comando..nao poe id pois ele é auto incremento
		PreparedStatement statement = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);// prepara a execuçao
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.execute();// executa a execucao sem receber uma resposta, afinal nao requisitou nada
		
		ResultSet rs = statement.getGeneratedKeys();//retorna uma coluna com o id
		if(rs.next()) {
			idGerado = rs.getInt(1);//pega a informacao armazenada na unica ocluna do resultset
		}
		
		return idGerado;
		
	}

	// editar
	public void editarNota(Nota nota, int idNota) throws ClassNotFoundException, SQLException {

		Connection conexao = BDConfig.getConnection();// cria conexao

		String sql = "UPDATE TB_NOTA SET TITULO = ?, DESCRICAO = ? WHERE ID_NOTE = ?";// comando
		PreparedStatement statement = conexao.prepareStatement(sql);// prepara a execuçao
		statement.setString(1, nota.getTitulo());
		statement.setString(2, nota.getDescricao());
		statement.setInt(3, idNota);
		statement.execute();// executa a execucao sem receber uma resposta, afinal nao requisitou nada
	}

	// deletar
	public void deletarNota(int idNota) throws ClassNotFoundException, SQLException {

		Connection conexao = BDConfig.getConnection();// cria conexao

		String sql = "DELETE FROM TB_NOTA WHERE ID_NOTE = ?";// comando
		PreparedStatement statement = conexao.prepareStatement(sql);// prepara a execuçao
		statement.setInt(1, idNota);
		statement.execute();// executa a execucao sem receber uma resposta, afinal nao requisitou nada
	}

}
