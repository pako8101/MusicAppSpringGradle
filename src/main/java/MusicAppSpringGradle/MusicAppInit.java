package MusicAppSpringGradle;

import MusicAppSpringGradle.service.ArtistService;
import MusicAppSpringGradle.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MusicAppInit implements CommandLineRunner {

    private final UserService userService;
    private final ArtistService artistService;

    public MusicAppInit(UserService userService, ArtistService artistService) {
        this.userService = userService;
        this.artistService = artistService;
    }


    @Override
    public void run(String... args) throws Exception {
artistService.seedArtists();
        userService.seedUsers();

    }
}
