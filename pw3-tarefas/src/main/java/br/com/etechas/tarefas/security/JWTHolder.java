package br.com.etechas.tarefas.security;

import lombok.Value;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class JWTHolder {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.Expiration}")
    private Duration duration;
}
