package fr.treeptik.annuairecliniquespringmvc.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.annuairecliniquespringmvc.dao.ClientDAO;
import fr.treeptik.annuairecliniquespringmvc.exception.DuplicateClientException;
import fr.treeptik.annuairecliniquespringmvc.exception.ServiceException;
import fr.treeptik.annuairecliniquespringmvc.model.Client;
import fr.treeptik.annuairecliniquespringmvc.service.ClientService;

public class ClientServiceImpl implements ClientService {

	private ClientDAO clientDAO;
	
	
	public ClientServiceImpl() {
		// constructeur vide
	}

	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

//	@Override
//	@Transactional(propagation = Propagation.REQUIRED)
//	public Personne add(Personne personne) throws ServiceException {
//
//		try {
//			if (find(personne).isEmpty()) {
//				return this.personneDAO.save(personne);
//			}
//			throw new ServiceException("La personne existe déjà !");
//		} catch (DAOException e) {
//			throw new ServiceException("Erreur ClientService add ==> " + e.getMessage(), e);
//		}
//	}

	
	@Override
	public Client findById(Integer id) throws ServiceException {
		
		try {
			return this.clientDAO.findOne(id);
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur ClientService findById ==> " + e.getMessage(), e);
		}
	}

//	@Override
//	public List<Personne> find(Personne personne) throws ServiceException {
//
//		try {
//			return this.personneDAO.find(personne);
//		} catch (DAOException e) {
//			throw new ServiceException("Erreur ClientService find ==> " + e.getMessage(), e);
//		}
//	}

	@Override
	public List<Client> findByName(String name) throws ServiceException {
		
		try {
			return this.clientDAO.findByName(name);
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur ClientService findByName ==> " + e.getMessage(), e);
		}
	}

	@Override
	public List<Client> findByNom(String nom) throws ServiceException {
		
		try {
			return this.clientDAO.findByNom(nom);
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur ClientService findByNom ==> " + e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Client save(Client client) throws ServiceException {
		
		try {
			if (find(client).isEmpty()) {
				return this.clientDAO.save(client);
			}
			throw new DuplicateClientException("la personne existe déjà");
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur ClientService save ==> " + e.getMessage(), e);
		}
	}

	@Override
	public List<Client> findAllOrderByName() throws ServiceException {
		
		try {
			return this.clientDAO.findAllOrderByName();
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur ClientService findAllOrderByName ==> " + e.getMessage(), e);
		}
	}

	@Override
	public List<Client> findAllOrderByNom() throws ServiceException {
		
		try {
			return this.clientDAO.findAllByOrderByNom();
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur ClientService findAllOrderByNom ==> " + e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Client update(Client client) throws ServiceException {
		
		try {
			List<Client> clients = find(client);
			if (clients.isEmpty() || clients.get(0).getId() == client.getId()) {
				return this.clientDAO.save(client);
			}
			throw new DuplicateClientException("la nouvelle personne existe déjà");
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur ClientService update ==> " + e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void delete(Client client) throws ServiceException {
		
		try {
			this.clientDAO.delete(client);
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur ClientService delete ==> " + e.getMessage(), e);
		}
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		
		try {
			this.clientDAO.delete(id);
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur ClientService delete ==> " + e.getMessage(), e);
		}
		
	}

	@Override
	public Long countAll() throws ServiceException {
		
		try {
			return this.clientDAO.countAll();
		} catch (ServiceException e) {
			throw new ServiceException("Erreur ClientService countAll ==> " + e.getMessage(), e);
		}
	}

	@Override
	public List<Client> find(Client client) throws ServiceException {
		
		try {
			return this.clientDAO.findByNomAndPrenomAndNumero(client.getNom(), client.getPrenom(), client.getNumero());
		} catch (ServiceException e) {
			throw new ServiceException("Erreur ClientService find ==> " + e.getMessage(), e);
		}
	}

}
