package com.transaction;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/transaction")
public class TransactionController {
	
	@Autowired
	TransactionService tservice;
	
	@RequestMapping(value="/save")
	public ResponseEntity save(@RequestBody Map params){
		tservice.save(params);
		
		return ResponseEntity.ok(HttpStatus.OK);
		
	}
	@RequestMapping(value="/get")
	public ResponseEntity get(){
		
		Iterable trans=tservice.getAllTrans();
		return ResponseEntity.ok(trans);
		
	}
	

}
