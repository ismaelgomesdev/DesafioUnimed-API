package desafio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import desafio.config.BDConfig;
import desafio.entidade.Cliente;

public class ClienteDAO {
	
	public List<Cliente> listarClientes() throws Exception {
		List<Cliente> clientes = new ArrayList<>();
		
		Connection conexao = BDConfig.getConnection();
		
		String sql = "SELECT * FROM cliente";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		
		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setId(rs.getInt("id"));
			cliente.setCnpj(rs.getString("cnpj"));
			cliente.setEmail(rs.getString("email"));
			cliente.setRazaoSocial(rs.getString("razao_social"));
			cliente.setTipoTributacao(rs.getString("tipo_tributacao"));
			
			clientes.add(cliente);
		}
		
		return clientes;
	}
	
	public Cliente buscarPorId(int idCliente) throws Exception {
		Cliente cliente = null;
		
		Connection conexao = BDConfig.getConnection();
		
		String sql = "SELECT * FROM cliente WHERE id = ?";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, idCliente);
		ResultSet rs = statement.executeQuery();
		
		if (rs.next()) {
			cliente = new Cliente();
			cliente.setId(rs.getInt("id"));
			cliente.setCnpj(rs.getString("cnpj"));
			cliente.setEmail(rs.getString("email"));
			cliente.setRazaoSocial(rs.getString("razao_social"));
			cliente.setTipoTributacao(rs.getString("tipo_tributacao"));
		}
		
		return cliente;
	}
	
	public List<Cliente> buscarPorTipoTributacao(String tipoBuscado) throws Exception {
		List<Cliente> clientes = new ArrayList<>();
		
		Connection conexao = BDConfig.getConnection();
		
		String sql = "SELECT * FROM cliente WHERE tipo_tributacao = ?";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, tipoBuscado);
		ResultSet rs = statement.executeQuery();
		
		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setId(rs.getInt("id"));
			cliente.setCnpj(rs.getString("cnpj"));
			cliente.setEmail(rs.getString("email"));
			cliente.setRazaoSocial(rs.getString("razao_social"));
			cliente.setTipoTributacao(rs.getString("tipo_tributacao"));
			
			clientes.add(cliente);
		}
		
		return clientes;
	}
	
	public int addCliente(Cliente cliente) throws Exception {
		int idGerado = 0;
		
		Connection conexao = BDConfig.getConnection();
		
		String sql = "INSERT INTO cliente(cnpj, razao_social, email, tipo_tributacao) VALUES (?, ?, ?, ?)";
		
		PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		statement.setString(1, cliente.getCnpj());
		statement.setString(2, cliente.getRazaoSocial());
		statement.setString(3, cliente.getEmail());
		statement.setString(4, cliente.getTipoTributacao());
		
		statement.execute();
		
		ResultSet rs = statement.getGeneratedKeys();
		
		if(rs.next()) {
			idGerado = rs.getInt(1);
		}
		
		return idGerado;
	}
	
	public void removerCliente(int idCliente) throws Exception {
		Connection conexao = BDConfig.getConnection();
		
		String sql = "DELETE FROM cliente WHERE id = ?";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		statement.setInt(1, idCliente);
		
		statement.execute();
	}
	
	
	
}
