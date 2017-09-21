package com.example.crudexample.feature.receipt.bean.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudexample.api.message.response.AbstractResponse;
import com.example.crudexample.domain.RecieptDomain;
import com.example.crudexample.domain.entity.Receipt;
import com.example.crudexample.dto.EmployeeDTO;
import com.example.crudexample.dto.ReceiptDTO;
import com.example.crudexample.feature.employee.api.message.response.AllUsersDetailResponse;
import com.example.crudexample.feature.employee.bean.EmployeeBean;
import com.example.crudexample.handler.EmployeeHandler;
import com.example.crudexample.handler.ReceiptHandler;
import com.example.crudexample.handler.RequestValidator;
import com.example.crudexample.utils.APIGroupCode;
import com.example.crudexample.utils.AppUtil;
import com.example.crudexample.utils.log.AppLog;
@Service("userBean")
public class ReceiptBeanImpl implements ReceiptBean {
	
@Autowired(required = true)
private EmployeeHandler employeeHandler;

@Autowired(required = true)
private RequestValidator requestValidator;

private final AppLog appLog;

@Inject
public ReceiptBeanImpl() {
	appLog = AppLog.getInstance(getClass());
}

@Override
public AbstractResponse processGetAllReceiptInfo(final String accessorIdStr, final String sessionToken) {
	final String errorCodePrefix = APIGroupCode.EMPLOYEE_GET_ALL_USERS.getGroupCode()
			+ APIGroupCode.EMPLOYEE_GET_ALL_USERS.getApiCode() + "000";

	String beanServiceName = "processGetAllReceiptInfo";

	appLog.printLog(Level.DEBUG, beanServiceName, " Accessor ID:" + accessorIdStr);

	// Validating Permissions & Session Token
	requestValidator.validateAuthToken(sessionToken, beanServiceName, errorCodePrefix);
	requestValidator.validateEMRId(accessorIdStr, errorCodePrefix);

	List<ReceiptDTO> employeeDTOs = ReceiptHandler.performGetAllReceiptInfo(errorCodePrefix);

	AllUsersDetailResponse getAllUsersDetailResponse = new AllUsersDetailResponse();
	getAllUsersDetailResponse.setEmployeeDTO(employeeDTOs);
	appLog.printLog(Level.DEBUG, beanServiceName, AppUtil.convertObjectToJson(getAllUsersDetailResponse));
	
	return getAllUsersDetailResponse;
}