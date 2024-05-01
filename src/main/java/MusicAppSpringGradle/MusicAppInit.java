package MusicAppSpringGradle;

import MusicAppSpringGradle.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MusicAppInit implements CommandLineRunner {

    private final UserService userService;

    public MusicAppInit(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {

        userService.seedUsers();

    }
}
