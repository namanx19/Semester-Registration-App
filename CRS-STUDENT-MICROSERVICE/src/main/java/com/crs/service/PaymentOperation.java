package com.crs.service;

import org.springframework.stereotype.Service;

import com.crs.exception.StudentAlreadyRegisteredException;

/**
 * Payment Service Interface
 */
@Service
public interface PaymentOperation {

	/**
	 * To get the amount to be paid
	 * 
	 * @param studentId
	 * @return The amount to be paid or has already been paid.
	 */
	public int getAmount(long studentId);

	/**
	 * To get the payment status of a student
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	public boolean getPaymentStatus(long studentId);

	/**
	 * For paying the fee offline
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	public boolean offline(long studentId);

	/**
	 * For paying the fees via UPI
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	public boolean UPI(long studentId);

	/**
	 * For paying the fees using debit/credit card
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	public boolean cards(long studentId);

	/**
	 * For paying the fees using online wallet.
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	public boolean wallet(long studentId);

	/**
	 * 
	 * @param studentId
	 * @param paymentStatus
	 * @return the Payment Bean object containing payment record of that particular
	 *         student.
	 */
	public String recordPayment(int studentId,String paymentMethod, boolean paymentStatus) throws StudentAlreadyRegisteredException;

}
