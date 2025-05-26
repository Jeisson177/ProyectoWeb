package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.internet.MimeMessage;

@RestController
@RequestMapping("/contacto")
@CrossOrigin(origins = "http://localhost:4200") // permite solicitudes desde tu Angular
public class ContactoController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarCorreo(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("correo") String correo,
            @RequestParam("telefono") String telefono,
            @RequestParam("archivo") MultipartFile archivo) {

        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

            helper.setTo("pastapassione380@gmail.com");
            helper.setSubject("Nueva hoja de vida enviada");
            helper.setText("Nombre: " + nombre +
                    "\nApellidos: " + apellidos +
                    "\nCorreo: " + correo +
                    "\nTeléfono: " + telefono);

            helper.addAttachment(archivo.getOriginalFilename(), archivo);

            mailSender.send(mensaje);

            return ResponseEntity.ok("Correo enviado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar el correo.");
        }
    }
}
