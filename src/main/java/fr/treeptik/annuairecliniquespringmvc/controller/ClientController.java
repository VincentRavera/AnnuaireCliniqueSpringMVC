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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.annuairecliniquespringmvc.exception.DuplicateClientException;
import fr.treeptik.annuairecliniquespringmvc.model.Client;
import fr.treeptik.annuairecliniquespringmvc.model.Stat;
import fr.treeptik.annuairecliniquespringmvc.service.AnimalService;
import fr.treeptik.annuairecliniquespringmvc.service.ClientService;
import fr.treeptik.annuairecliniquespringmvc.validator.ClientValidator;

@Controller
@RequestMapping("/clients")
@SessionAttributes("stat")
public class ClientController {
	
	private Logger logger = LoggerFactory.getLogger(ClientController.class);

	private ClientService clientService;
	
	private AnimalService animalService;
	
	private ClientValidator clientValidator;
	
	// bof car appelé pour les variables de sessions et stat n'est pas un client donc ça plante
	// on utilise le clientvalidator quand on en a besoin directement dans les méthodes
//	@InitBinder
//	public void init(WebDataBinder binder) {
//		
//		binder.setValidator(clientValidator);
//	}
	
	
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView index() {
		
		logger.info("Dans index de ClientController");
		
		ModelAndView modelAndView = new ModelAndView("index");
		
		Stat stat = Stat.getInstance();
		stat.setNbClient(clientService.countAll());
		stat.setNbAnimal(animalService.countAll());
		
		modelAndView.addObject("stat", stat);
		
//		Long nbClient = clientService.countAll();
//		modelAndView.addObject("nbClient", nbClient);
//		
//		Long nbAnimal = animalService.countAll();
//		modelAndView.addObject("nbAnimal", nbAnimal);
		// donc la il va chercher WEB-INF/pages/index.jsp avec notre conf
		return modelAndView;
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView initListClient() {
		
		logger.info("Dans initListClient de ClientController");
		
		// donc la il va chercher WEB-INF/pages/list-clients.jsp avec notre conf
		ModelAndView modelAndView = new ModelAndView("list-clients");
		
		List<Client> clients = clientService.findAllOrderByName();
		modelAndView.addObject("clients", clients);
		
		Stat stat = Stat.getInstance();
		stat.setNbClient(clientService.countAll());
		stat.setNbAnimal(animalService.countAll());
		
		modelAndView.addObject("stat", stat);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView initFormClient(@RequestParam(value = "id", required = false) Integer id) {
		
		logger.info("Dans initFormClient de ClientController");
		
		ModelAndView modelAndView = new ModelAndView("client");
		
		if (id != null) {
			modelAndView.addObject("client", clientService.findById(id));
		} else {
			modelAndView.addObject("client", new Client());
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/delete.html", method = RequestMethod.POST)
	public ModelAndView deleteClient(@RequestParam(value = "id") Integer id) {
		
		logger.info("Dans deleteClient de ClientController");
		
		ModelAndView modelAndView = new ModelAndView("redirect:list.html");
		
		clientService.delete(id);
		return modelAndView;
	}
	
	@RequestMapping(value = "/add.html", method = RequestMethod.POST)
	public ModelAndView saveClient(@ModelAttribute("client") Client client, BindingResult result) {
		/*@Validated normalemnt après modelattribute pour déclenché le validator mais on le fait directement dans la méthodes*/
		
		logger.info("Dans saveClient de ClientController");
		
		clientValidator.validate(client, result);
		
		if (result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("client");
			modelAndView.addObject("client", client);
			return modelAndView;
		}
		
		
		try {
			if (client.getId() == null) {
				clientService.save(client);
			} else {
				clientService.update(client);
			}
		} catch (DuplicateClientException e) {
			logger.info("Le client existe déjà (nouveau ou modification)");
			ModelAndView modelAndView = new ModelAndView("client");
			
			// on utilise un champ de client pour l'affichage
			result.rejectValue("nom", "client.duplicate");
			
			return modelAndView;
		}
		
		// permet de rediriger sur list.html après l'ajout
		return new ModelAndView("redirect:list.html");
	}
	
	
	
	
	public ClientService getClientService() {
		return clientService;
	}

	@Autowired
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ClientValidator getClientValidator() {
		return clientValidator;
	}

	@Autowired
	public void setClientValidator(ClientValidator clientValidator) {
		this.clientValidator = clientValidator;
	}

	public AnimalService getAnimalService() {
		return animalService;
	}

	@Autowired
	public void setAnimalService(AnimalService animalService) {
		this.animalService = animalService;
	}
	
	
}
