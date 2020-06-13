package com.transaction.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface TransactionRepo extends CrudRepository<Transaction,Long> {

	
	
}
