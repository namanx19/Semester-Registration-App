package com.crs.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crs.entity.Notification;
import com.crs.entity.NotificationStudentMapping;
import com.crs.exception.UserNotFoundException;
import com.crs.repository.*;

/**
 * Notification Service Implementation
 */
@Service
public class NotificationOperationImpl implements NotificationOperation {

	@Autowired
	public NotificationStudentMappingRepository notificationStudentMappingRepository;

	@Autowired
	public NotificationRepository notificationRepository;

	@Autowired
	public StudentRepository studentRepository;

	/**
	 * To get notifications of a specific user
	 * 
	 * @param studentId
	 * @return list of notifications
	 */
	@Override
	public List<Notification> getNotificationMessage(int studentId) throws UserNotFoundException {
		if (studentRepository.findById(studentId).isEmpty() == true) {
			throw new UserNotFoundException(studentId);
		}
		List<NotificationStudentMapping> notificationMappings = notificationStudentMappingRepository
				.findByStudent(studentRepository.findById(studentId).get());
		List<Notification> notifications = notificationMappings.stream()
				.map(notifs -> new Notification(notifs.getNotification())).collect(Collectors.toList());
		return notifications;
	}

	/**
	 * Send notification to user
	 * @param userId
	 * @param notificationMessage
	 */
	@Override
	public void sendNotification(int userId,String notificationMessage) {
		Notification notification = notificationRepository.findByNotificationMessage(notificationMessage);
		
		notificationStudentMappingRepository.save(new NotificationStudentMapping(
				studentRepository.findById(userId).get(), notification));
	}

}
