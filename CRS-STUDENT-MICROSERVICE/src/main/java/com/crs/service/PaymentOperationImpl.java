package com.crs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crs.entity.Payment;
import com.crs.entity.PaymentStudentMapper;
import com.crs.entity.Student;
import com.crs.exception.StudentAlreadyRegisteredException;
import com.crs.repository.*;

/**
 * Payment Service Implementation
 */
@Service
public class PaymentOperationImpl implements PaymentOperation {
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PaymentStudentMapperRepository paymentStudentMapperRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	Payment paymentBean;

	public PaymentOperationImpl() {
		paymentBean = new Payment();
	}

	/**
	 * To get the amount to be paid
	 * 
	 * @param studentId
	 * @return The amount to be paid or has already been paid.
	 */
	@Override
	public int getAmount(long studentId) {
		// TODO Auto-generated method stub
		return paymentBean.getAmount();
	}

	/**
	 * To get the payment status of a student
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	@Override
	public boolean getPaymentStatus(long studentId) {

		if (paymentBean.getPaymentStatus() == 1)
			return true;
		else
			return false;
	}

	/**
	 * For paying the fee offline
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	@Override
	@Transactional
	public boolean offline(long studentId) {

		paymentBean.setPaymentStatus(1);
		return true;
	}

	/**
	 * For paying the fees via UPI
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	@Override
	public boolean UPI(long studentId) {

		this.paymentBean.setPaymentStatus(1);
		return true;
	}

	/**
	 * For paying the fees using debit/credit card
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	@Override
	public boolean cards(long studentId) {

		this.paymentBean.setPaymentStatus(0);
		return false;
	}

	/**
	 * For paying the fees using online wallet.
	 * 
	 * @param studentId
	 * @return True if payment is successful else returns False.
	 */
	@Override
	public boolean wallet(long studentId) {
		this.paymentBean.setPaymentStatus(0);
		return false;
	}

	/**
	 * 
	 * @param studentId
	 * @param paymentStatus
	 * @return the Payment Bean object containing payment record of that particular
	 *         student.
	 * @throws StudentAlreadyRegisteredException 
	 */
	@Override
	@Transactional
	public String recordPayment(int studentId, String paymentmethod,boolean paymentStatus) throws StudentAlreadyRegisteredException {
		
		Student student = studentRepository.findById(studentId).get();
		Payment payment = new Payment();
		PaymentStudentMapper paymentStudentMapper=new PaymentStudentMapper();
		
		if(student.getCourseRegistrationStatus()==1)
		{
			throw new StudentAlreadyRegisteredException();
		}
		payment.setPaymentType(paymentmethod);		
		if (paymentStatus)
		{
			payment.setPaymentStatus(1);
			student.setCourseRegistrationStatus(1);
		}
		else
		{
			this.paymentBean.setPaymentStatus(0);
		}
		paymentStudentMapper.setPayment(payment);
		paymentStudentMapper.setStudent(student);
	
		//this.paymentBean.setUserId(studentId);
		//this.paymentBean.setTransactionId(1000000 + studentId);
		System.out.println("transaction"+payment.getTransactionId());
		studentRepository.save(student);
		paymentRepository.save(payment);
		paymentStudentMapperRepository.save(paymentStudentMapper);
		return payment.getTransactionId().toString();
	
	}

}
