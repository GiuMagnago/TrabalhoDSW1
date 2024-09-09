package br.ufscar.dc.dsw.SistemaVagas.service.spec;

import jakarta.mail.internet.InternetAddress;

public interface IEmailService {
    void send(InternetAddress from, InternetAddress to, String subject, String body);   
}
