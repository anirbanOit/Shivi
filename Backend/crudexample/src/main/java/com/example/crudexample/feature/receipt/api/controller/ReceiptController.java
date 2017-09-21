package com.example.crudexample.feature.receipt.api.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudexample.api.message.response.AbstractResponse;
import com.example.crudexample.feature.employee.api.controller.EmployeeController;
import com.example.crudexample.feature.employee.bean.EmployeeBean;
import com.example.crudexample.feature.receipt.bean.impl.ReceiptBean;
import com.example.crudexample.utils.AppUtil;
import com.example.crudexample.utils.constant.AppConstant;
import com.example.crudexample.utils.log.AppLog;

	
	@RestController
	@RequestMapping("/receipt")
	public class ReceiptController {
		private final ReceiptBean receiptBean;
		private final AppLog appLog;

		@Inject
		public ReceiptController(final ReceiptBean receiptBean) {
			this.appLog = AppLog.getInstance(ReceiptController.class);
			this.receiptBean = receiptBean;
		}

		@CrossOrigin
		@RequestMapping(method = RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<String> getUser() {
			return ResponseEntity.status(HttpStatus.OK).body("User Information");
		}

		@CrossOrigin
		@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<AbstractResponse> getAllUser(@Valid @RequestParam("admin_id") final String accessorIdStr,
				@RequestHeader(AppConstant.REQUEST_HEADER_TOKEN) final String sessionToken) {
			String methodName = "getReceiptInfo";

			appLog.printLog(Level.DEBUG, methodName, "Role: " + accessorIdStr);

			AbstractResponse getAllUserResponse = receiptBean.processGetAllReceiptInfo(accessorIdStr, sessionToken);

			appLog.printLog(Level.DEBUG, methodName, "Response: " + AppUtil.convertObjectToJson(getAllUserResponse));

			return ResponseEntity.status(HttpStatus.OK).body(getAllUserResponse);
		}
}
