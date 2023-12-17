/**
 * 
 */
package com.crs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crs.entity.Notification;

/**
 * Notification repository related to Notification CRUD operations
 */
@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {

	public Notification findByNotificationMessage(String notificationMessage);

}
