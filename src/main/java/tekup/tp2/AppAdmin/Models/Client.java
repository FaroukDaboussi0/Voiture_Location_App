package tekup.tp2.AppAdmin.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomprenom;
    private String numero;
    private Sex sex;

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomprenom() {
        return nomprenom;
    }

    public void setNomprenom(String nomprenom) {
        this.nomprenom = nomprenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }






    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
    public enum Sex {
        MALE,
        FEMALE
    }


    @OneToMany(mappedBy = "client")
    private List<Location> locations;
}
