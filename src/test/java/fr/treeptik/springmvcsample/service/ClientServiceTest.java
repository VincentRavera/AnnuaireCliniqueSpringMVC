package fr.treeptik.springmvcsample.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.annuairecliniquespringmvc.config.ApplicationConfiguration;
import fr.treeptik.annuairecliniquespringmvc.exception.ServiceException;
import fr.treeptik.annuairecliniquespringmvc.model.Client;
import fr.treeptik.annuairecliniquespringmvc.service.ClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
@ActiveProfiles("test")
public class ClientServiceTest {

	private Logger logger = LoggerFactory.getLogger(ClientServiceTest.class);

	@Autowired
	private ClientService clientService;

	@Test
	@Transactional
	public void shouldSaveClient() {

		try {

			Client client = new Client();
			client.setNom("Dupont");
			client.setPrenom("Pierre");
			client.setNumero("03");
			client = clientService.save(client);

			Assert.assertNotNull(client.getId());
			Assert.assertEquals("Dupont", client.getNom());
			Assert.assertEquals("Pierre", client.getPrenom());
			Assert.assertEquals("03", client.getNumero());

		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Test(expected = ServiceException.class)
	@Transactional
	@Sql(scripts = "classpath:/init-test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shouldNotSaveNewClient() {

			Client client = new Client();
			client.setNom("Dupont");
			client.setPrenom("Paul");
			client.setNumero("02");
			
			client = clientService.save(client);
			
//			Assert.assertNotNull(client.getId());
//			Assert.assertTrue(client.getId() == 3);
//			Assert.assertEquals("Dupont", client.getNom());
//			Assert.assertEquals("Pierre", client.getPrenom());
//			Assert.assertEquals("03", client.getNumero());
			
	}

	@Test
	@Sql(scripts = "classpath:/init-test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shoudFindClient() {
		try {
			Client client = clientService.findById(2);
			Assert.assertEquals("Dupuis", client.getNom());
			Assert.assertEquals("Paul", client.getPrenom());
			Assert.assertEquals("01", client.getNumero());
		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	@Sql(scripts = "classpath:/init-test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shoudFindClientByNomPrenomNumero() {
		try {
			Client client = new Client();
			client.setNom("Dupont");
			client.setPrenom("Paul");
			client.setNumero("02");
			
			client = clientService.find(client).get(0);
			
			Assert.assertNotNull(client.getId());
			Assert.assertTrue(client.getId() == 3);
			Assert.assertEquals("Dupont", client.getNom());
			Assert.assertEquals("Paul", client.getPrenom());
			Assert.assertEquals("02", client.getNumero());
		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	@Transactional
	@Sql(scripts = "classpath:/init-test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shoudUpdateClient() {
		
		try {

			Client client = new Client();
			client.setId(3);
			client.setNom("nom");
			client.setPrenom("prenom");
			client.setNumero("0");

			client = clientService.update(client);
			
			Assert.assertTrue(3 == client.getId());
			Assert.assertEquals("nom", client.getNom());
			Assert.assertEquals("prenom", client.getPrenom());
			Assert.assertEquals("0", client.getNumero());

		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	@Transactional
	@Sql(scripts = "classpath:/init-test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shoudDeleteClient() {
		
		try {

			Client client = new Client();
			client.setId(3);
			client.setNom("nom");
			client.setPrenom("prenom");
			client.setNumero("0");

			clientService.delete(client);
			
			client = clientService.findById(3);
			
			Assert.assertNull(client);

		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	// avec ma requete
	@Test
	@Sql(scripts = "classpath:/init-test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shouldFindByName() {
		
		try {
			List<Client> clients = clientService.findByName("dupont");
			
			Assert.assertTrue(!clients.isEmpty());
			
			//java 8 sans boucle for, pas vraiment
			//personnes.stream().forEach(p -> Assert.assertEquals("dupont", p.getNom()));
			
			for (Client client : clients) {
				Assert.assertEquals("Dupont", client.getNom());
			}
		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	// avec la requete générée par hibernate
	@Test
	@Sql(scripts = "classpath:/init-test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shouldFindByNom() {
		
		try {
			List<Client> clients = clientService.findByNom("dupont");
			
			Assert.assertTrue(!clients.isEmpty());
			
			for (Client client : clients) {
				Assert.assertEquals("Dupont", client.getNom());
			}
		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	@Sql(scripts = "classpath:/init-test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shouldFindAllOrderByName() {
		
		try {
			List<Client> clients = clientService.findAllOrderByName();
			
			Assert.assertTrue(!clients.isEmpty());
			
			Client clientTmp = new Client();
			
			for (Client client : clients) {
				
				if (clientTmp.getNom() == null) {
					continue;
				}
				
				Assert.assertTrue(client.getNom().compareTo(clientTmp.getNom()) >= 0);
				clientTmp = client;
			}
		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	
	@Test
	@Sql(scripts = "classpath:/init-test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shouldFindAllOrderByNom() {
		
		try {
			List<Client> clients = clientService.findAllOrderByNom();
			
			Assert.assertTrue(!clients.isEmpty());
			
			Client clientTmp = new Client();
			
			for (Client client : clients) {
				
				if (clientTmp.getNom() == null) {
					continue;
				}
				
				Assert.assertTrue(client.getNom().compareTo(clientTmp.getNom()) >= 0);
				clientTmp = client;
			}
		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	
	@Test
	@Sql(scripts = "classpath:/init-test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shouldCount7() {
		
		try {
			Long nb = clientService.countAll();
			
			Assert.assertTrue(nb != null);
			
			Assert.assertTrue(nb == 7);
			
		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}

}