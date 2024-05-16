package MusicAppSpringGradle.repositories;

import MusicAppSpringGradle.models.entities.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface AlbumRepository extends JpaRepository<AlbumEntity,Long> {
}
