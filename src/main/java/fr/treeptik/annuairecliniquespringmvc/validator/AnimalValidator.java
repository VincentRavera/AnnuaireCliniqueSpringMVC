package fr.treeptik.annuairecliniquespringmvc.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.treeptik.annuairecliniquespringmvc.model.Animal;

@Component
public class AnimalValidator implements Validator {

	private Logger logger = LoggerFactory.getLogger(AnimalValidator.class);
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Animal.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		logger.info("Dans validate de AnimalValidator");

		Animal animal = (Animal) target;

		if (animal.getNom() == null || animal.getNom().isEmpty()) {

			errors.rejectValue("nom", "animal.nom.empty");
		}

		if (animal.getAge() == null || animal.getAge() < 0) {

			errors.rejectValue("age", "animal.age.invalid");
		}

		if (animal.getRace() == null || animal.getRace().isEmpty()) {

			errors.rejectValue("race", "animal.race.empty");
		}

		if (animal.getType() == null || animal.getType().isEmpty()) {

			errors.rejectValue("type", "animal.type.empty");
		}
	}

}
