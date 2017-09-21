package com.example.crudexample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusDTO {

 @JsonProperty("Status")
 private String status;

 public StatusDTO() {
 }

 /**
  * @return the status
  */
 public String getStatus() {
  return status;
 }

 /**
  * @param status
  *            the status to set
  */
 public void setStatus(String status) {
  this.status = status;
 }
}
