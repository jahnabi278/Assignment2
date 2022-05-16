package com.example.demo;

public interface EmailService {
	String sendSimpleMail(EmailDetails details);

	String sendMailWithAttachment(EmailDetails details);
}
