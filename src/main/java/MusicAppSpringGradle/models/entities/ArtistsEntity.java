package MusicAppSpringGradle.models.entities;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity(name = "artists")
public class ArtistsEntity extends BaseEntity{
    @Expose
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,columnDefinition = "TEXT")
    @Expose
    private String careerInformation;

    public ArtistsEntity() {
    }

    public String getName() {
        return name;
    }

    public ArtistsEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public ArtistsEntity setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }

    @Override
    public String toString() {
        return "ArtistsEntity{" +
                "name='" + name + '\'' +
                ", careerInformation='" + careerInformation + '\'' +
                '}';
    }
}
