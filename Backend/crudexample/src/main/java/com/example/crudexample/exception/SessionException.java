package com.example.crudexample.exception;

import com.example.crudexample.exception.base.AppBusinessException;
import com.example.crudexample.exception.helper.ExceptionDetail;
import com.example.crudexample.utils.constant.AppConstant;

public class SessionException extends AppBusinessException {
	private static final long serialVersionUID = 5951927527132322535L;

	/**
	 * 
	 * @param exceptionDetail
	 */
	public SessionException(final ExceptionDetail exceptionDetail) {
		super(exceptionDetail, AppConstant.HTTP_STATUS_UNAUTHORIZED);
	}
}
