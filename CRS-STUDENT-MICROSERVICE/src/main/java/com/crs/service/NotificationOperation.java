package com.crs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crs.entity.Notification;
import com.crs.exception.UserNotFoundException;

/**
 * Notification Service interface
 */
@Service
public interface NotificationOperation {

	/**
	 * To get notifications of a specific user
	 * 
	 * @param studentId
	 * @return list of notifications
	 */
	public List<Notification> getNotificationMessage(int studentId) throws UserNotFoundException;

	/**
	 * Send notification to user
	 * @param userId
	 * @param notificationId
	 */
	public void sendNotification(int userId,String notificationMessage);

}
