/**
 * 
 */
package com.crs.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Class representing the payment
 */

@Entity
@Table(name = "payment")
public class Payment implements Serializable {

	@Id
	@Column
	@GeneratedValue(generator ="uuid2")
	@GenericGenerator(name="uuid2",strategy = "org.hibernate.id.UUIDGenerator")
	private UUID transactionId;
	
	@Column
	private int paymentStatus;

	@Column
	private int amount;
	
	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Column
	private String paymentType;

	public Payment() {
		amount = 1000000;
	}

	/**
	 * @return User ID
	 */

	/**
	 * @return Transaction Id
	 */
	public UUID getTransactionId() {
		return transactionId;
	}

	/**
	 * @param Get Transaction Id
	 */
	public void setTransactionId(UUID transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param set amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * 
	 * @return
	 */
	public int getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * 
	 * @param paymentStatus
	 */
	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}
