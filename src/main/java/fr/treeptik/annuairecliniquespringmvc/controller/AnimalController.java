package fr.treeptik.annuairecliniquespringmvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.annuairecliniquespringmvc.model.Animal;
import fr.treeptik.annuairecliniquespringmvc.model.Stat;
import fr.treeptik.annuairecliniquespringmvc.service.AnimalService;
import fr.treeptik.annuairecliniquespringmvc.service.ClientService;
import fr.treeptik.annuairecliniquespringmvc.validator.AnimalValidator;

@Controller
@RequestMapping("/animaux")
public class AnimalController {
	
	private Logger logger = LoggerFactory.getLogger(AnimalController.class);
	
	private AnimalService animalService;
	
	private ClientService clientService;
	
	private AnimalValidator animalValidator;

	
	@RequestMapping(value = "/list-animals.html", method = RequestMethod.GET)
	public ModelAndView initListAnimal(@RequestParam(value = "id", required = false) Integer id) {
		
		logger.info("Dans initListAnimal de AnimalController");
		
		ModelAndView modelAndView = new ModelAndView("list-animals");
		
		List<Animal> animals;
		
		if (id == null) {
			animals = animalService.findAllOrderByNom();
		} else {
			
			modelAndView.addObject("client", clientService.findById(id));
			animals = animalService.findAnimalByClientId(id);
		}
		modelAndView.addObject("animals", animals);
		
		Stat stat = Stat.getInstance();
		stat.setNbClient(clientService.countAll());
		stat.setNbAnimal(animalService.countAll());
		
		modelAndView.addObject("stat", stat);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView initFormAnimal(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "idClient", required = false) Integer idClient) {
		
		logger.info("Dans initFormAnimal de AnimalController");
		
		ModelAndView modelAndView = new ModelAndView("animal-edit");
		if (id != null) {
			
				modelAndView.addObject("animal", animalService.findById(id));
		} else {
			
			if (idClient != null) {
				// TODO ajouter animal a client
				modelAndView.addObject("animal", animalService.findById(id));
			} else {
				modelAndView.addObject("animal", new Animal());
			}
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/add.html", method = RequestMethod.POST)
	public ModelAndView saveAnimal(@ModelAttribute("animal") Animal animal, BindingResult result) {
		/*@Validated normalemnt après modelattribute pour déclenché le validator mais on le fait directement dans la méthodes*/
		
		logger.info("Dans saveAnimal de AnimalController");
		
		animalValidator.validate(animal, result);
		
		if (result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("animal-edit");
			modelAndView.addObject("animal", animal);
			return modelAndView;
		}
		
		
		if (animal.getId() == null) {
			animalService.save(animal);
		} else {
			animalService.update(animal);
		}
			
		
		// permet de rediriger sur list.html après l'ajout
		return new ModelAndView("redirect:list-animals.html");
	}
	
	
	
	public AnimalService getAnimalService() {
		return animalService;
	}

	@Autowired
	public void setAnimalService(AnimalService animalService) {
		this.animalService = animalService;
	}

	public AnimalValidator getAnimalValidator() {
		return animalValidator;
	}

	@Autowired
	public void setAnimalValidator(AnimalValidator animalValidator) {
		this.animalValidator = animalValidator;
	}

	public ClientService getClientService() {
		return clientService;
	}

	@Autowired
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

}
