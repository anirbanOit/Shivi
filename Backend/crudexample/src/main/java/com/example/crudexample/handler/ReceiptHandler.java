package com.example.crudexample.handler;

import java.util.List;

import com.example.crudexample.dto.EmployeeDTO;
import com.example.crudexample.dto.ReceiptDTO;

public interface ReceiptHandler {
	/**
	 * 
	 * @param errorCodePrefix
	 * @return
	 */
	List<ReceiptDTO> performGetAllReceiptInfo(String errorCodePrefix);

}
