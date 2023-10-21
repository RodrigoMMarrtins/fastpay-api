package com.example.fastpayapi.fastpayapi.service;

import com.example.fastpayapi.fastpayapi.domain.User.User;
import com.example.fastpayapi.fastpayapi.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);
       ResponseEntity<String> response = restTemplate.postForEntity(
               "http://o4d9z.mocklab.io/notify",
                notificationRequest,
                String.class);
       if(!(response.getStatusCode() == HttpStatus.OK)) {
           System.out.println("Could not send the notification.");
           throw new Exception("Notification service is not working!");
       }
    }
}
