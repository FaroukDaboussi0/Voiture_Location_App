package tekup.tp2.AppAdmin.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity

public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date_debut;
    private LocalDateTime Date_retour;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDateTime date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDateTime getDate_retour() {
        return Date_retour;
    }

    public void setDate_retour(LocalDateTime date_retour) {
        Date_retour = date_retour;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    @ManyToOne
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Voiture voiture ;

}
