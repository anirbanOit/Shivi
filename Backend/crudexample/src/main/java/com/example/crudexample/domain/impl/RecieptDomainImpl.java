package com.example.crudexample.domain.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.crudexample.api.message.response.AbstractResponse;
import com.example.crudexample.domain.ReceiptDomain;
import com.example.crudexample.domain.entity.Receipt;
import com.example.crudexample.domain.repo.RecieptRepo;

public class RecieptDomainImpl implements ReceiptDomain {
	
	@Autowired(required = true)
	private RecieptRepo recieptRepo;
	
	@Override
	public List<Receipt> getAllUsersProfile() {
		return recieptRepo.findAll();
	}

	@Override
	public AbstractResponse processGetAllRecieptInfo(String accessorIdStr, String sessionToken) {
		// TODO Auto-generated method stub
		return null;
	}

}
