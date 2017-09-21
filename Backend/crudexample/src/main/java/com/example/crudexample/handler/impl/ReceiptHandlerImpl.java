package com.example.crudexample.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudexample.domain.EmployeeDomain;
import com.example.crudexample.domain.ReceiptDomain;
import com.example.crudexample.domain.entity.Employee;
import com.example.crudexample.domain.entity.Receipt;
import com.example.crudexample.dto.EmployeeDTO;
import com.example.crudexample.dto.ReceiptDTO;
import com.example.crudexample.exception.NotFoundException;
import com.example.crudexample.exception.helper.ErrorCause;
import com.example.crudexample.exception.helper.ExceptionDetail;
import com.example.crudexample.exception.helper.ResponseCode;
import com.example.crudexample.handler.EmployeeHandler;
import com.example.crudexample.handler.ReceiptHandler;

@Service("receiptHandler")
public class ReceiptHandlerImpl implements ReceiptHandler{
	
	@Autowired(required = true)
	private ReceiptDomain receiptDomain;
	
	@Override
	public List<ReceiptDTO> performGetAllReceiptInfo(final String errorCodePrefix) {

		List<Receipt> receiptList = receiptDomain.getAllReceiptInfo();
		List<EmployeeDTO> employeeDtos = new ArrayList<>();
		
		if (receiptList == null || receiptList.isEmpty()) {
			ExceptionDetail exceptionDetail = new ExceptionDetail(0, "", 0, errorCodePrefix, ResponseCode.NOT_FOUND,
					ErrorCause.NO_DATA_FOUND);

			throw new NotFoundException(exceptionDetail);
		}
		
		for (Receipt receipt : receiptList) {
			ReceiptDTO receiptDTO = new ReceiptDTO();
			ReceiptDTO.setRecieptId(receipt.getReceiptId());
			ReceiptDTO.setRecptDate(receipt.getReceiptDate());
			ReceiptDTO.setRecieptAmount(receipt.getRecieptAmount());
			ReceiptDTO.setRecieptType(receipt.getRecieptType());
			employeeDtos.add(receiptDTO);
		}
		return employeeDtos;
	}