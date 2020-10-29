package com.github.arthurmatosbsb.DAO;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import com.github.arthurmatosbsb.entity.Cliente;

@RequestScoped
public class ClienteDAO {

	@Transactional
	public void inserir(Cliente cliente) {
		Cliente.persist(cliente);
	}

	public Cliente listarPorId(Integer id) {
		return Cliente.findById(id);
	}
	@Transactional
	public void remove(Integer id) {
		Cliente.deleteById(id);
	}

	public List<Cliente> listarTudo() {
		return Cliente.listAll();
	}
	@Transactional
	public void atualizar(Integer id, Cliente cliente) {
		Cliente clientes = Cliente.findById(id);
		clientes.setNome(cliente.getNome());
	}
	

}
