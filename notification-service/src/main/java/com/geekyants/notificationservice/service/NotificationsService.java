package com.geekyants.notificationservice.service;

import java.util.List;
import java.util.UUID;

import com.geekyants.notificationservice.entity.Notifications;

public interface NotificationsService {

	List<Notifications> listNotifications();

	List<Notifications> listAllNotifications();

	void createNotificationForUser(UUID userId, String string, String string2);

	void createBroadcastNotification(UUID id, String string2);

}
