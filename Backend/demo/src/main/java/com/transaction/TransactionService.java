package com.transaction;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.transaction.dao.Transaction;
import com.transaction.dao.TransactionRepo;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepo repo;
	
	@PersistenceContext
	private EntityManager entityManager;

	public void save(Map <String,String>params) {
		String amount= params.get("amount");
		String desc= params.get("desc");
		String type= params.get("type");
		javax.persistence.Query q=			entityManager.createQuery("select t from "+Transaction.class.getName()+" t order by t.id desc");
		
		q.setMaxResults(1);
		List transaction=q.getResultList();
		if(type.equalsIgnoreCase("credit")) {
			Transaction t= new Transaction();
					t.setCreditAmount(amount);
					t.setDebitAmount("");
		
					if(transaction.isEmpty()) {
						if(type.equalsIgnoreCase("credit")) {
							Transaction t1= new Transaction();
							t1.setCreditAmount(amount);
							t1.setDebitAmount("");
							t1.setDate(new Date());
							 t1.setDescription(desc);
							t1.setRunningBalance(amount);
							repo.save(t1);
						}
						
						
					}
					else {
						Transaction t2=(Transaction)transaction.get(0);
						Transaction saveTrnsaction= new Transaction();
						
							 String running=t2.getRunningBalance();
							 saveTrnsaction.setType(type);
							 saveTrnsaction.setDate(new Date());
							 saveTrnsaction.setCreditAmount(amount);
							 saveTrnsaction.setDescription(desc);
							 
							 
							 if(!running.equalsIgnoreCase("")) {
								 saveTrnsaction.setRunningBalance(String.valueOf(Integer.parseInt(running)+Integer.parseInt(amount)));
							 }else
							 {
								 saveTrnsaction.setRunningBalance(String.valueOf(0+Integer.parseInt(amount)));
							 }
							
							 repo.save(saveTrnsaction);
						 }
						}
		else {
			
			
			Transaction saveTrnsaction= new Transaction();
			saveTrnsaction.setDebitAmount(amount);
			Transaction t2=(Transaction)transaction.get(0);
			 saveTrnsaction.setDescription(desc);
			 saveTrnsaction.setDate(new Date());
			 String running=t2.getRunningBalance();
			 if(!running.equalsIgnoreCase("")) {
				 saveTrnsaction.setRunningBalance(String.valueOf(Integer.parseInt(running)-Integer.parseInt(amount)));
			 }
			 repo.save(saveTrnsaction);
			
			
		}
						
					}

	public Iterable<Transaction> getAllTrans() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
		
		
		
		
	}


