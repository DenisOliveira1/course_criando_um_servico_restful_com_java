package br.edu.denis.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.denis.dao.NotaDAO;
import br.edu.denis.entidade.Nota;

@Path("/notas") // annotation 1

//pedaco apos o url padrao definido no web.xml
//sempre que a url conter esse pedaço essa classe sera resposavel por atende-la

public class NotasService {

	// variaveis
	private NotaDAO notaDAO;
	private final String CHARSET = ";charset=utf-8";//final deixa a variavel constante. essa stirng nao pode ter espaços

	//contrutor
	@PostConstruct // annotation 2

	// essa classe nao precisa de um construtor
	// esse é o construtor disponibilizado pelo jersey
	// sempre que uma url for recebida sera encamnhada para essa casa e esse
	// construtor sera inicializado disponibilizando o DAO
	private void init() {
		notaDAO = new NotaDAO();
	}

	// metodos
	@GET//
	@Path("/list")// get notas/list
	@Produces(MediaType.APPLICATION_JSON) // o metodo abaixo produz um json

	// procudes define o que será produzido. Significa que o proximo metodo tera seu return convertido para json, assim como foi definido no web.xml
	// consumes nao foi usado pois o metodo nao recebe parametros, ou seja...nao consume nada
	
	public List<Nota> listarNotas() {
		List<Nota> lista = null;

		try {
			lista = notaDAO.listarNotas();// acessa o dao iniciado no construtor e pega todas as listas
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;// devolve as listas
	}
	
	@GET
	@Path("/search/{id}")// get notas/search/2
	@Produces(MediaType.APPLICATION_JSON) // o metodo abaixo produz um json
	
	// consumes nao foi usado pois o metodo nao recebe parametros, ou seja...nao consume nada. o id é retirado da propria url
	
	public Nota buscarNotaPorId(@PathParam("id") int idNota) {
		Nota nota = null;

		try {
			nota= notaDAO.buscarNotaPorId(idNota);// acessa o dao iniciado no construtor e pega todas as listas
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nota;// devolve a nota
	}
	
	@POST
	@Path("/insert")	// post notas/insert
	@Consumes(MediaType.APPLICATION_JSON + CHARSET) // o metodo abaixo recebe um json junto com a requisicao
	@Produces(MediaType.TEXT_PLAIN) // o metodo abaixo produz um texto

	public String adicionarNota(Nota nota) {
		String msg = "";

		System.out.println(nota.getTitulo());

		try {
			String idGerado = Integer.toString(notaDAO.adicionarNota(nota));//sempre o retorno par ao usuario devera se ruma string
			msg = "Nota inserida com sucesso! IdGerado: "+idGerado;
		} catch (Exception e) {
			msg = "Erro ao inserir a nota!";
			e.printStackTrace();
		}
		return msg;
	}

	@PUT
	@Path("/edit/{id}")	// put notas/edit/2
	@Consumes(MediaType.APPLICATION_JSON + CHARSET) // o metodo abaixo recebe um json junto com a requisicao
	@Produces(MediaType.TEXT_PLAIN) // o metodo abaixo produz um texto

	public String editarNota(Nota nota, @PathParam("id") int idNota) {
		String msg = "";

		System.out.println(nota.getTitulo());

		try {
			notaDAO.editarNota(nota, idNota);
			msg = "Nota editada com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao editar a nota!";
			e.printStackTrace();
		}
		return msg;
	}
	
	@DELETE
	@Path("/delete/{id}")	// delete notas/delete/2
	@Produces(MediaType.TEXT_PLAIN) // o metodo abaixo produz um texto

	public String editarNota(@PathParam("id") int idNota) {
		String msg = "";
		
		try {
			notaDAO.deletarNota(idNota);
			msg = "Nota removida com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao remover a nota!";
			e.printStackTrace();
		}
		return msg;
	}
}


