package com.cursoMc.SpringFast.services;

import org.springframework.mail.SimpleMailMessage;

import com.cursoMc.SpringFast.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
