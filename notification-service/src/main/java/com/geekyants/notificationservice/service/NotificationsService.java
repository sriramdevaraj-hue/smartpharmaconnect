package com.geekyants.notificationservice.service;

import java.util.List;

import com.geekyants.notificationservice.entity.Notifications;

public interface NotificationsService {

	List<Notifications> listNotifications();

	List<Notifications> listAllNotifications();

}
