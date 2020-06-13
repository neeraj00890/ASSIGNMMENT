package com.springdata.conf;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
        "com.transaction.dao"
})
@EnableWebMvc()
@ComponentScan(basePackages= {
		"com.transaction",
})


public class ConfgurationFile {

	
	@Bean
	public BasicDataSource  getBasicDataSource() throws IOException
	{
		System.out.println("Basic data source is conf");
		File file =new ClassPathResource("db.properties").getFile();
		FileReader reader= new FileReader(file);
		Properties props= new Properties();
		props.load(reader);
	
		BasicDataSource basicDataSource= new BasicDataSource();
		basicDataSource.setDriverClassName(props.get("db.driver").toString());
		basicDataSource.setUsername(props.get("db.username").toString());
		basicDataSource.setPassword(props.get("db.password").toString());
		basicDataSource.setUrl(props.get("db.url").toString());
		
		return basicDataSource;	
	}
	
	@Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException {
		File file =new ClassPathResource("db.properties").getFile();
		FileReader reader= new FileReader(file);
		Properties props= new Properties();
		props.load(reader);
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(getBasicDataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.transaction.dao");
 
        Properties jpaProperties = new Properties();
     
        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect",props.get("hibernate.dialect"));
 
        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", 
        		props.get("hibernate.hbm2ddl.auto")
        );
 
        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements
        jpaProperties.put("hibernate.ejb.naming_strategy", 
        		props.get("hibernate.ejb.naming_strategy")
        );
 
        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql", 
        		props.get("hibernate.show_sql")
        );
 
        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        jpaProperties.put("hibernate.format_sql", 
        		props.get("hibernate.format_sql")
        );
 
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
 
        return entityManagerFactoryBean;
    }
	
	@Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
