package fr.treeptik.annuairecliniquespringmvc.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
// on peut charger le bon fichier de properties suivant le profile chargé
// après les properties se nomment config-test et config-tomcat
// du coup besoin que d'une datasource
//@PropertySource(value = "classpath:/config-${ENV}.properties")
@PropertySource(value = "classpath:/mysql.properties")
//ATTENTION a surtout pas l'oublier pour la gestion des transcations
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"fr.treeptik.annuairecliniquespringmvc.dao"}, entityManagerFactoryRef = "entityManagerFactoryBean")
public class JPAConfiguration {

	private Logger logger = LoggerFactory.getLogger(JPAConfiguration.class);
	
	// environment : contient les propriétés systèmes, les variables
	// d'environnements et les properties déclarées en propertysource
	@Autowired
	private Environment environment;

	@Bean(name = "dataSource")
	@Profile("tomcat")
	public DataSource dataSourceTomCat() {

		logger.info("Chargement profile tomcat");
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("db.driverclassname"));
		dataSource.setUrl(environment.getProperty("db.url"));
		dataSource.setUsername(environment.getProperty("db.username"));
		dataSource.setPassword(environment.getProperty("db.password"));

		return dataSource;
	}
	
	@Bean(name = "dataSource")
	@Profile("test")
	public DataSource dataSourceTest() {
		
		logger.info("Chargement profile test");
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("dbtest.driverclassname"));
		dataSource.setUrl(environment.getProperty("dbtest.url"));
		dataSource.setUsername(environment.getProperty("dbtest.username"));
		dataSource.setPassword(environment.getProperty("dbtest.password"));
		
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {

		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		// on peut faire avec le persistence.xml mais on va le faire full spring
		// localContainerEntityManagerFactoryBean.setPersistenceUnitName(et ici
		// le persistence unit name);

		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		// génère les tables mais pas la base
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		// mont les requetes sql
		hibernateJpaVendorAdapter.setShowSql(true);
		// dialecte mysql
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);

		// on ratache au container
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);

		// on donne les paramètres de conncetions
		localContainerEntityManagerFactoryBean.setDataSource(dataSource);

		Properties properties = new Properties();
		properties.put("hibernate.generate_statistics", true);
		properties.put("hibernate.format_sql", true);
		properties.put("hibernate.hbm2ddl.auto", "create");

		localContainerEntityManagerFactoryBean.setJpaProperties(properties);
		// package a scanner ==> obligatoire
		localContainerEntityManagerFactoryBean.setPackagesToScan("fr.treeptik.annuairecliniquespringmvc.model");

		return localContainerEntityManagerFactoryBean;

	}

	// méthode classe !!! entityManagerFactory est automatiquement créer par la
	// méthode du dessus
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean(dataSource).getObject());

		return transactionManager;
	}
	
	
//	// méthode classe !!! entityManagerFactory est automatiquement créer par la
//	// méthode du dessus
//	@Bean
//	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//		
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(entityManagerFactory);
//		
//		return transactionManager;
//	}
}
