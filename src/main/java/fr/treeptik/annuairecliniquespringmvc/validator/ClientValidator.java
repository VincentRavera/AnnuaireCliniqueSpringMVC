package fr.treeptik.annuairecliniquespringmvc.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.treeptik.annuairecliniquespringmvc.model.Client;

@Component
public class ClientValidator implements Validator {

	private Logger logger = LoggerFactory.getLogger(ClientValidator.class);
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Client.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.info("Dans validate de ClientValidator");

		Client client = (Client) target;
		
		if (client.getNom() == null || client.getNom().isEmpty()) {
			
			errors.rejectValue("nom", "nom.empty");
		}
		
		if (client.getPrenom() == null || client.getPrenom().isEmpty()) {
			
			errors.rejectValue("prenom", "prenom.empty");
		}
	}

}
