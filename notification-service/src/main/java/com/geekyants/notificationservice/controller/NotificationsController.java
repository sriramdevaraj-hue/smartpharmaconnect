package com.geekyants.notificationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekyants.notificationservice.entity.Notifications;
import com.geekyants.notificationservice.service.NotificationsService;

@RestController
@RequestMapping("/api/v1")
public class NotificationsController {

	@Autowired
	private NotificationsService notifServ;

	@GetMapping("/notifications")
	public ResponseEntity<?> displayNotification() {
		try {
			List<Notifications> not = notifServ.listNotifications();
			return ResponseEntity.status(HttpStatus.FOUND).body(not);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping("/notifications/admin")
	public ResponseEntity<?> displayAllNotification() {
		try {
			List<Notifications> not = notifServ.listAllNotifications();
			return ResponseEntity.status(HttpStatus.FOUND).body(not);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
