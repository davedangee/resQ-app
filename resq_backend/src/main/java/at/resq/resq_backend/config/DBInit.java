package at.resq.resq_backend.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DBInit {

    @PostConstruct
    public void initialize() {
        System.out.println("""
                                      ________    __________                __                      .___
                _______   ____   _____\\_____  \\   \\______   \\_____    ____ |  | __ ____   ____    __| _/
                \\_  __ \\_/ __ \\ /  ___//  / \\  \\   |    |  _/\\__  \\ _/ ___\\|  |/ // __ \\ /    \\  / __ |\s
                 |  | \\/\\  ___/ \\___ \\/   \\_/.  \\  |    |   \\ / __ \\\\  \\___|    <\\  ___/|   |  \\/ /_/ |\s
                 |__|    \\___  >____  >_____\\ \\_/  |______  /(____  /\\___  >__|_ \\\\___  >___|  /\\____ |\s
                             \\/     \\/       \\__>         \\/      \\/     \\/     \\/    \\/     \\/      \\/        \s""");
        System.out.println("DBInit.initialize");
    }
}
