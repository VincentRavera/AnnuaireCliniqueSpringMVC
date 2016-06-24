package fr.treeptik.annuairecliniquespringmvc.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.treeptik.annuairecliniquespringmvc.model.Client;

public interface ClientDAO extends JpaRepository<Client, Integer>{

//	Personne add(Personne personne) throws DAOException;
//	Personne findById(Integer id) throws DAOException;
	
//	@Query("SELECT c FROM Client c WHERE c.nom = :nom AND c.prenom = :prenom AND c.numero = :numero")
//	List<Client> find(Client client) throws DataAccessException;
	
	List<Client> findByNomAndPrenomAndNumero(String nom, String prenom, String numero) throws DataAccessException;
	
	
	@Query("SELECT c FROM Client c WHERE c.nom = :nom")
//	@Query(value = "SELECT p FROM Personne p WHERE p.nom = :nom", nativeQuery = true)
	List<Client> findByName(@Param("nom") String nom) throws DataAccessException;
	
	// la requete est générer automatiquement grace à l'attribut nom de la personne
	List<Client> findByNom(String nom) throws DataAccessException;
	
	@Query("SELECT c FROM Client c ORDER BY c.nom DESC")
	List<Client> findAllOrderByName() throws DataAccessException;

	List<Client> findAllByOrderByNom() throws DataAccessException;
	
	@Query("SELECT COUNT(c) FROM Client c")
	Long countAll() throws DataAccessException;
}
