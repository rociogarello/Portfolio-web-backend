package com.portfolio.seed;

import com.portfolio.entity.About;
import com.portfolio.entity.Persona;
import com.portfolio.repository.AboutRepository;
import com.portfolio.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PersonaSeeder implements CommandLineRunner {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private AboutRepository aboutRepository;
    @Value("${custom.app.seed.enabled}")
    private Boolean enabled;

    @Override
    public void run(String... args) throws Exception {
        if (!enabled) return;
        if (personaRepository.count() == 0) {
            Persona persona = personaRepository.save(Persona.builder().firstName("Rocio").lastName("Garello").build());
            aboutRepository.save(About.builder()
                    .title("Fullstack Web Developer")
                    .about("About text")
                    .persona(personaRepository.getReferenceById(persona.getId()))
                    .build()
            );
        }
    }
}
