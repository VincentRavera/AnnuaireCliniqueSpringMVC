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
import fr.treeptik.annuairecliniquespringmvc.model.Animal;
import fr.treeptik.annuairecliniquespringmvc.service.AnimalService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
@ActiveProfiles("test")
public class AnimalServiceTest {

	private Logger logger = LoggerFactory.getLogger(AnimalServiceTest.class);

	@Autowired
	private AnimalService animalService;

	@Test
	@Transactional
	public void shouldSaveAnimal() {

		try {

			Animal animal = new Animal();
			animal.setNom("Dupont");
			animal.setAge(4);
			animal.setRace("race");
			animal.setType("typ");
			animal = animalService.save(animal);

			Assert.assertNotNull(animal.getId());
			Assert.assertEquals("Dupont", animal.getNom());
			Assert.assertTrue(4 == animal.getAge());
			Assert.assertEquals("race", animal.getRace());
			Assert.assertEquals("typ", animal.getType());

		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}

	@Test
	@Sql(scripts = "classpath:/init-test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shoudFindAnimal() {
		try {
			Animal animal = animalService.findById(9);
			Assert.assertEquals("medor6", animal.getNom());
			Assert.assertTrue(12 == animal.getAge());
			Assert.assertEquals("pitbull", animal.getRace());
			Assert.assertEquals("chien", animal.getType());
		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	@Sql(scripts = "classpath:/init-test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shoudUpdateAnimal() {
		
		try {

			Animal animal = new Animal();
			animal.setId(3);
			animal.setNom("nom");
			animal.setAge(5);
			animal.setRace("race");
			animal.setType("typ");

			animal = animalService.update(animal);
			
			Assert.assertTrue(3 == animal.getId());
			Assert.assertEquals("nom", animal.getNom());
			Assert.assertTrue(5 == animal.getAge());
			Assert.assertEquals("race", animal.getRace());
			Assert.assertEquals("typ", animal.getType());

		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	@Sql(scripts = "classpath:/init-test.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shoudDeleteAnimal() {
		
		try {

			Animal animal = new Animal();
			animal.setId(6);
			animal.setNom("medor1");
			animal.setAge(3);
			animal.setRace("york");
			animal.setType("chien");
			
			animalService.delete(animal);
			
			animal = animalService.findById(6);
			
			Assert.assertNull(animal);

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
			List<Animal> animals = animalService.findByNom("titi");
			
			Assert.assertTrue(!animals.isEmpty());
			
			for (Animal animal : animals) {
				Assert.assertEquals("titi", animal.getNom());
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
			List<Animal> animals = animalService.findAllOrderByNom();
			
			Assert.assertTrue(!animals.isEmpty());
			
			Animal animalTmp = new Animal();
			
			for (Animal animal : animals) {
				
				if (animalTmp.getNom() == null) {
					continue;
				}
				
				Assert.assertTrue(animal.getNom().compareTo(animalTmp.getNom()) >= 0);
				animalTmp = animal;
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
			Long nb =  animalService.countAll();
			
			Assert.assertTrue(nb != null);
			
			Assert.assertTrue(nb == 7);
			
		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}

}