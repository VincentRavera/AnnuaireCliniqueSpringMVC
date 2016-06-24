package fr.treeptik.annuairecliniquespringmvc.service;

import java.util.List;

import fr.treeptik.annuairecliniquespringmvc.exception.ServiceException;
import fr.treeptik.annuairecliniquespringmvc.model.Animal;

public interface AnimalService {

	Animal update(Animal animal) throws ServiceException;
	
	void delete(Animal animal) throws ServiceException;
	void delete(Integer id) throws ServiceException;
	
	Animal save(Animal animal) throws ServiceException;
	
	Animal findById(Integer id) throws ServiceException;
	List<Animal> findByNom(String nom) throws ServiceException;
	
	List<Animal> findAllOrderByNom() throws ServiceException;
	
	Long countAll() throws ServiceException;

	List<Animal> findAnimalByClientId(Integer id);
}
