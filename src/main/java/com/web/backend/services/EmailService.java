package com.web.backend.services;

import com.web.backend.model.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service @Slf4j @AllArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final String sender = "at2396433@gmail.com";
    private final String frontendUrl = "http://localhost:3000";

    public void sendAuthorizationEmail(AppUser appUser) {
        try {
            log.info("Creating simple email...");
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            String linkWithToken = String.format(
                    "%s/auth/register/authorize?email=%s&authorizationToken=%s",
                    frontendUrl, appUser.getEmail(), appUser.getAuthorizationToken());

            mailMessage.setFrom(sender);
            mailMessage.setTo(appUser.getEmail());

            mailMessage.setText("Thank you for creating an account with us. Please click the link provided to finalize your account creation process! " + linkWithToken);
            mailMessage.setSubject("Authorizing your Quickclean account");

            log.info("Sending simple email...");
            mailSender.send(mailMessage);
        } catch(Exception e) {
            throw new MailSendException("Couldn't create and send authentication email");
        }
    }

    public void resendAuthorizationEmail(AppUser appUser) {
        try {
            log.info("Creating simple email...");
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            String linkWithToken = String.format(
                    "%s/auth/register/authorize?email=%s&authorizationToken=%s",
                    frontendUrl, appUser.getEmail(), appUser.getAuthorizationToken());

            mailMessage.setFrom(sender);
            mailMessage.setTo(appUser.getEmail());

            mailMessage.setText("We noticed you tried to login just now. Please click the link provided to finalize your account creation process! " + linkWithToken);
            mailMessage.setSubject("Authorizing your Quickclean account");

            log.info("Sending simple email...");
            mailSender.send(mailMessage);
        } catch(Exception e) {
            throw new MailSendException("Couldn't create and send authentication email");
        }
    }

    public void sendResetPasswordEmail(AppUser appUser) {
        try {
            log.info("Creating simple email...");
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            String linkWithToken = String.format(
                    "%s/auth/resetpassword?email=%s&resetToken=%s",
                    frontendUrl, appUser.getEmail(), appUser.getResetToken());

            mailMessage.setFrom(sender);
            mailMessage.setTo(appUser.getEmail());

            mailMessage.setText(String.format("Hey %s, please click the link provided and follow the instructions to reset your password %s", appUser.getFirstName(), linkWithToken));
            mailMessage.setSubject("Forgot Quickclean account password");

            log.info("Sending simple email...");
            mailSender.send(mailMessage);
        } catch(Exception e) {
            throw new MailSendException("Couldn't create and send authentication email");
        }
    }
}
