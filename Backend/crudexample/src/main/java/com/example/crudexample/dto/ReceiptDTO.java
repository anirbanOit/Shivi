package com.example.crudexample.dto;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReceiptDTO {
 @NotNull(message = "GENERIC_BUSINESS~MANDATORY_PARAMETER~RECEIPTTYPE")
 @NotEmpty(message = "GENERIC_BUSINESS~MANDATORY_PARAMETER~RECEIPTTYPE")
 @JsonProperty("Receipt Type")
 private String receiptType;
 
 @NotNull(message = "GENERIC_BUSINESS~MANDATORY_PARAMETER~RECEIPTDATE")
 @NotEmpty(message = "GENERIC_BUSINESS~MANDATORY_PARAMETER~RECEIPTDATE")
 @JsonProperty("Receipt Date")
    private String receiptDate; 
 
 @NotNull(message = "GENERIC_BUSINESS~MANDATORY_PARAMETER~RECEIPTAMOUNT")
 @NotEmpty(message = "GENERIC_BUSINESS~MANDATORY_PARAMETER~RECEIPTAMOUNT")
 @JsonProperty("Receipt Amount")
    private String receiptAmount;

 public ReceiptDTO() {
 }
 /**
  * @return the receiptType
  */
 public String getReceiptType() {
  return receiptType;
 }

 /**
  * @param receiptType the receiptType to set
  */
 public void setReceiptType(String receiptType) {
  this.receiptType = receiptType;
 }

 /**
  * @return the receiptDate
  */
 public String getReceiptDate() {
  return receiptDate;
 }

 /**
  * @param receiptDate the receiptDate to set
  */
 public void setReceiptDate(String receiptDate) {
  this.receiptDate = receiptDate;
 }

 /**
  * @return the receiptAmount
  */
 public String getReceiptAmount() {
  return receiptAmount;
 }

 /**
  * @param receiptAmount the receiptAmount to set
  */
 public void setReceiptAmount(String receiptAmount) {
  this.receiptAmount = receiptAmount;
 }
 
 
}