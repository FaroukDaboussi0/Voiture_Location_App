package tekup.tp2.AppAdmin.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Models {
    @Id
    @GeneratedValue()
    public long id;
    public String name ;

    public List<Voiture> getVoitures() {
        return voitures;
    }

    public void setVoitures(List<Voiture> voitures) {
        this.voitures = voitures;
    }

    @OneToMany(mappedBy = "models", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<Voiture> voitures ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
