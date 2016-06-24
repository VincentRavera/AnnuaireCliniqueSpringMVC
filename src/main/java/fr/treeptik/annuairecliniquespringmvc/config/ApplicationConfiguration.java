package fr.treeptik.annuairecliniquespringmvc.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import fr.treeptik.annuairecliniquespringmvc.dao.AnimalDAO;
import fr.treeptik.annuairecliniquespringmvc.dao.ClientDAO;
import fr.treeptik.annuairecliniquespringmvc.service.AnimalService;
import fr.treeptik.annuairecliniquespringmvc.service.ClientService;
import fr.treeptik.annuairecliniquespringmvc.service.impl.AnimalServiceImpl;
import fr.treeptik.annuairecliniquespringmvc.service.impl.ClientServiceImpl;

@Configuration
@ComponentScan(basePackages = { "fr.treeptik.annuairecliniquespringmvc.service" })
@Import(value = { JPAConfiguration.class, SecurityConfig.class })
public class ApplicationConfiguration {

	@PersistenceContext
	EntityManager em;

	@Bean
	public ClientService clientService(ClientDAO clientDAO) {
		
		ClientServiceImpl clientService = new ClientServiceImpl();
		clientService.setClientDAO(clientDAO);
		return clientService;
	}
	
	@Bean
	public AnimalService animalService(AnimalDAO animalDAO) {
		
		AnimalServiceImpl animalService = new AnimalServiceImpl();
		animalService.setAnimalDAO(animalDAO);
		return animalService;
	}

//	@Bean
//	public PersonneDAO personneJPADAO() {
//
//		PersonneJPADAO personneJPADAO = new PersonneJPADAO();
//		personneJPADAO.setEm(em);
//		return personneJPADAO;
//	}
}
