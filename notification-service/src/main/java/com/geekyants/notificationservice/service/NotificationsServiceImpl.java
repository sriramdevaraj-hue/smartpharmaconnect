package com.geekyants.notificationservice.service;

import java.util.List;

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

}
