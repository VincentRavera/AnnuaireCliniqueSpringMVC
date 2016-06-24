package fr.treeptik.annuairecliniquespringmvc.service;

import java.util.List;

import fr.treeptik.annuairecliniquespringmvc.exception.ServiceException;
import fr.treeptik.annuairecliniquespringmvc.model.Client;

public interface ClientService {

	Client update(Client client) throws ServiceException;
	void delete(Client client) throws ServiceException;
	void delete(Integer id) throws ServiceException;
	//Client add(Client Client) throws ServiceException;
	Client save(Client client) throws ServiceException;
	Client findById(Integer id) throws ServiceException;
	List<Client> find(Client client) throws ServiceException;
	//List<Client> find(Client Client) throws ServiceException;
	List<Client> findByName(String name) throws ServiceException;
	List<Client> findByNom(String nom) throws ServiceException;
	List<Client> findAllOrderByName() throws ServiceException;
	List<Client> findAllOrderByNom() throws ServiceException;
	
	Long countAll() throws ServiceException;
	
}
