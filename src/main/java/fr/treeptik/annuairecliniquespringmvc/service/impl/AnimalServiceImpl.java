package fr.treeptik.annuairecliniquespringmvc.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import fr.treeptik.annuairecliniquespringmvc.dao.AnimalDAO;
import fr.treeptik.annuairecliniquespringmvc.exception.ServiceException;
import fr.treeptik.annuairecliniquespringmvc.model.Animal;
import fr.treeptik.annuairecliniquespringmvc.service.AnimalService;

public class AnimalServiceImpl implements AnimalService {

	private AnimalDAO animalDAO;
	
	public AnimalServiceImpl() {
		//constructeur vide
	}

	public AnimalDAO getAnimalDAO() {
		return animalDAO;
	}

	public void setAnimalDAO(AnimalDAO animalDAO) {
		this.animalDAO = animalDAO;
	}
	
	
	

	@Override
	public Animal update(Animal animal) throws ServiceException {
		
		try {
			return this.animalDAO.save(animal);
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur AnimalService update ==> " + e.getMessage(), e);
		}
	}

	@Override
	public void delete(Animal animal) throws ServiceException {
		
		try {
			this.animalDAO.delete(animal);
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur AnimalService delete ==> " + e.getMessage(), e);
		}
		
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		
		try {
			this.animalDAO.delete(id);
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur AnimalService delete ==> " + e.getMessage(), e);
		}
		
	}

	@Override
	public Animal save(Animal animal) throws ServiceException {
		
		try {
			return this.animalDAO.save(animal);
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur AnimalService save ==> " + e.getMessage(), e);
		}
	}

	@Override
	public Animal findById(Integer id) throws ServiceException {
		
		try {
			return this.animalDAO.findOne(id);
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur AnimalService findById ==> " + e.getMessage(), e);
		}
	}

	@Override
	public List<Animal> findByNom(String nom) throws ServiceException {
		
		try {
			return this.animalDAO.findByNom(nom);
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur AnimalService findByNom ==> " + e.getMessage(), e);
		}
	}

	@Override
	public List<Animal> findAllOrderByNom() throws ServiceException {
		
		try {
			return this.animalDAO.findAllOrderByNom();
		} catch (DataAccessException e) {
			throw new ServiceException("Erreur AnimalService findAllOrderByNom ==> " + e.getMessage(), e);
		}
	}

	@Override
	public Long countAll() throws ServiceException {
		
		try {
			return this.animalDAO.countAll();
		} catch (ServiceException e) {
			throw new ServiceException("Erreur AnimalService countAll ==> " + e.getMessage(), e);
		}
	}

	@Override
	public List<Animal> findAnimalByClientId(Integer id) {
		
		try {
			return this.animalDAO.findAnimalByClientId(id);
		} catch (ServiceException e) {
			throw new ServiceException("Erreur AnimalService findAnimalByClientId ==> " + e.getMessage(), e);
		}
	}

}
