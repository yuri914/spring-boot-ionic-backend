package com.nelioalves.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.nelioalves.cursomc.domain.Pedido;

public interface EmailService {

    void sendOrderConfirmateEmail(Pedido obj);
    void sendEmail(SimpleMailMessage msg);
    void sendOrderConfirmateHtmlEmail(Pedido obj);
    void sendHtmlEmail(MimeMessage msg);
}
