package com.example.crudexample.domain;

import java.util.List;

import com.example.crudexample.api.message.response.AbstractResponse;
import com.example.crudexample.domain.entity.Receipt;


public interface ReceiptDomain {
	
	/**
	 * 
	 * @return List<Reciept>
	 */
	List<Receipt> getAllUsersProfile();

	AbstractResponse processGetAllRecieptInfo(String accessorIdStr, String sessionToken);

}