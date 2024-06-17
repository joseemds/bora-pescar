package org.pescaria.service;

import org.pescaria.dao.PescariaDAO;
import org.pescaria.dao.PeixeDAO;
import org.pescaria.entity.Pescaria;
import org.pescaria.entity.Peixe;
import org.pescaria.exception.DAOException;

import java.util.List;

public class PescariaService {
	private PescariaDAO pescariaDAO = new PescariaDAO();
	private PeixeDAO peixeDAO = new PeixeDAO();

	public void cadastrarPescaria(Pescaria pescaria) throws DAOException {
		pescariaDAO.salvarPescaria(pescaria);
	}

	public List<Pescaria> listarPescaria() throws DAOException {
		return pescariaDAO.listarTodas();
	}

	public List<Pescaria> listarPescariaUsuario() throws DAOException {
		return pescariaDAO.listarTodas().stream().filter(pescaria -> pescaria.getUsuario() == AuthService.getAutenticado()).toList();
	}

	// TODO
	// public List<Peixe> listarPeixesAcimaDePeso(double pesoMinimo) throws
	// DAOException {
	// List<Peixe> peixes = peixeDAO.listarTodos();
	// return peixes.stream()
	// .filter(peixe -> peixe.getPeso() > pesoMinimo)
	// .toList();
	// }

	public Pescaria obterPescariaPorId(int id) throws DAOException {
		return pescariaDAO.obterPorId(id);
	}

	public List<Peixe> obterListaPeixes() throws DAOException {
		return peixeDAO.listarTodos();
	}
}
