package com.portfolio.seed;

import com.portfolio.entity.User;
import com.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${custom.app.seed.users.enabled}")
    private Boolean enabled;

    @Value("${custom.app.username}")
    private String username;

    @Value("${custom.app.password}")
    private String password;


    @Override
    public void run(String... args) throws Exception {
        if (!enabled) return;
        if (userRepository.count() == 0) {
            userRepository.save(User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .build());
        }
    }
}
