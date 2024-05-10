package MusicAppSpringGradle.repositories;

import MusicAppSpringGradle.models.entities.ArtistsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistsEntity, Long> {
}
