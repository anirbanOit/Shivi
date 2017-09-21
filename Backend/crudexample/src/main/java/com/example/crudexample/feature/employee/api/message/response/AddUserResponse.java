package com.example.crudexample.feature.employee.api.message.response;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.example.crudexample.api.message.response.GenericSuccessResponse;
import com.example.crudexample.dto.EmployeeDTO;
import com.example.crudexample.dto.StatusDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddUserResponse extends GenericSuccessResponse {
	@NotNull
	@JsonProperty("result")
	private StatusDTO statusDTO;

	/**
	 * @return the statusDTO
	 */
	public StatusDTO getStatusDTO() {
		return statusDTO;
	}

	/**
	 * @param statusDTO
	 *            the statusDTO to set
	 */
	public void setStatusDTO(StatusDTO statusDTO) {
		this.statusDTO = statusDTO;
	}
}
