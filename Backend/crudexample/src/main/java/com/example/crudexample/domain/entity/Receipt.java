package com.example.crudexample.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "reciept")
public class Receipt {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reciept_id", updatable = false)
    private long recieptId;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "emp_id", updatable = true)
	private long empId;
	
	@NotNull
	@Min(value = 1)
	@Column(name = "reciept_type", updatable = true)
    private String recieptType;	
	
	@NotNull
	@Size(max = 255)
	@Column(name = "reciept_amount", updatable = true)
    private int recieptAmount;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "receipt_date", updatable = true)
	private String receiptDate;

	/**
	 * @return the recieptId
	 */
	public long getRecieptId() {
		return recieptId;
	}

	/**
	 * @param recieptId the recieptId to set
	 */
	public void setRecieptId(long recieptId) {
		this.recieptId = recieptId;
	}

	/**
	 * @return the empId
	 */
	public long getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(long empId) {
		this.empId = empId;
	}

	/**
	 * @return the recieptType
	 */
	public String getRecieptType() {
		return recieptType;
	}

	/**
	 * @param recieptType the recieptType to set
	 */
	public void setRecieptType(String recieptType) {
		this.recieptType = recieptType;
	}

	/**
	 * @return the recieptAmount
	 */
	public int getRecieptAmount() {
		return recieptAmount;
	}

	/**
	 * @param recieptAmount the recieptAmount to set
	 */
	public void setRecieptAmount(int recieptAmount) {
		this.recieptAmount = recieptAmount;
	}

	/**
	 * @return the receiptDate
	 */
	public String getReceiptDate() {
		return receiptDate;
	}

	/**
	 * @param receiptdate the receiptDate to set
	 */
	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

}