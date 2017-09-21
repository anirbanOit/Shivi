package com.example.crudexample.feature.receipt.bean.impl;

import com.example.crudexample.api.message.response.AbstractResponse;

public interface ReceiptBean {
	
	/**
	 * 
	 * @param accessorId
	 * @param sessionToken
	 * @return
	 */
	AbstractResponse processGetAllReceiptInfo(String accessorId , String sessionToken);

}
