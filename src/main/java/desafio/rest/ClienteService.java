package desafio.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import desafio.dao.ClienteDAO;
import desafio.entidade.Cliente;

@Path("/clientes")
public class ClienteService {
	
	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private ClienteDAO clienteDAO;
	
	@PostConstruct
	private void init() {
		clienteDAO = new ClienteDAO();
	}
	
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> listarClientes() {
		List<Cliente> clientes = null;
		
		try {
			clientes = clienteDAO.listarClientes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clientes;
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente buscarPorId(@PathParam("id") int idCliente) {
		Cliente cliente = null;
		try {
			cliente = clienteDAO.buscarPorId(idCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cliente;
	}
	
	@GET
	@Path("/tipoTributacao/{tipo}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> buscarPorTipoTributacao(@PathParam("tipo") String tipoBuscado) {
		List<Cliente> clientes = null;
		
		try {
			clientes = clienteDAO.buscarPorTipoTributacao(tipoBuscado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clientes;
	}
	
	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String criarCliente(Cliente cliente) {
		String msg = "";
		
		try {
			int idGerado = clienteDAO.addCliente(cliente);
			
			msg = String.valueOf(idGerado);
		} catch (Exception e) {
			msg = "Erro ao criar cliente";
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerCliente(@PathParam("id") int idCliente) {
		String msg = "";
		
		try {
			clienteDAO.removerCliente(idCliente);
			
			msg = "Cliente removido!";
		} catch (Exception e) {
			msg = "Erro ao remover cliente";
			e.printStackTrace();
		}
		
		return msg;
	}	
	
	
}
