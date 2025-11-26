package com.geekyants.notificationservice.service;

import java.util.List;
import java.util.UUID;

import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekyants.notificationservice.entity.Notifications;
import com.geekyants.notificationservice.repository.NotificationsRepository;

@Service
public class NotificationsServiceImpl implements NotificationsService {

	@Autowired
	private NotificationsRepository notifyRepo;

	public List<Notifications> listNotifications() {
		return notifyRepo.findAll();
	}

	public List<Notifications> listAllNotifications() {
		return notifyRepo.findAll();
	}
	
	
	
	 public void createNotificationForUser(UUID userId, String title, String message) {
	        Notifications n = new Notifications();
	        n.setId(UUID.randomUUID());
	        n.setRefId(userId);
	        n.setMessage(message);
	        notifyRepo.save(n);
	    }

	    public void createBroadcastNotification(UUID id, String message) {
	        Notifications n = new Notifications();
	        n.setId(UUID.randomUUID());
	        n.setMessage(message);
	        notifyRepo.save(n);
	    }


}
