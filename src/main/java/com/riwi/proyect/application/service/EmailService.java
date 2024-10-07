package com.riwi.proyect.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    /*Usa SimpleMailMessage si solo necesitas enviar correos electrónicos de texto simples,
    ya que es más fácil y rápido de implementar.

    Usa MimeMessage si necesitas características avanzadas como HTML, imágenes o archivos adjuntos.*/

    public void sendTaskNotificationEmail(String toEmail, String projectName, String projectDescription){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Nuevo proyecto asignado: " + projectName);
        message.setText("Has sido asignado a la siguiente tarea: " + projectDescription);
        mailSender.send(message);
    }
}
