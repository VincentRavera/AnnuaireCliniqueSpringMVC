package fr.treeptik.annuairecliniquespringmvc.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.treeptik.annuairecliniquespringmvc.model.Animal;

public interface AnimalDAO extends JpaRepository<Animal, Integer>{

	
	@Query("SELECT a FROM Animal a WHERE a.nom = :nom")
	List<Animal> findByName(@Param("nom") String nom) throws DataAccessException;
	
	// la requete est générer automatiquement grace à l'attribut nom de la personne
	List<Animal> findByNom(String nom) throws DataAccessException;
	
	@Query("SELECT a FROM Animal a ORDER BY a.nom DESC")
	List<Animal> findAllOrderByNom() throws DataAccessException;
	
	@Query("SELECT COUNT(a) FROM Animal a")
	Long countAll() throws DataAccessException;

	@Query("SELECT a FROM Animal a JOIN a.client c WHERE c.id = :id")
	List<Animal> findAnimalByClientId(@Param("id") Integer id);
}
