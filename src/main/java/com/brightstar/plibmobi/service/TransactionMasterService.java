package com.brightstar.plibmobi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightstar.plibmobi.model.TransactionMaster;
import com.brightstar.plibmobi.repository.TransactionMasterRepository;

@Service
public class TransactionMasterService {
	
	@Autowired
	private TransactionMasterRepository transactionMasterRepository;
	
	public Optional<TransactionMaster> findMasterBox(Integer id) {
		return transactionMasterRepository.findById(id);
	}

}
