package tekup.tp2.AppAdmin.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity


public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serie;
    private String model;
    private int price;
    private Etat etat;
    private boolean disponibility = true; // Default value: true
    private LocalDateTime dateMiseEn = LocalDateTime.now(); // Default value: LocalDateTime.now()
    private double TotalRevenue = 0 ;

    public double getTotalRevenue() {
        return TotalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        TotalRevenue = totalRevenue;
    }

    public Voiture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
// Constructor


    // Getters and Setters
    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public boolean isDisponibility() {
        return disponibility;
    }

    public void setDisponibility(boolean disponibility) {
        this.disponibility = disponibility;
    }

    public LocalDateTime getDateMiseEn() {
        return dateMiseEn;
    }

    public void setDateMiseEn(LocalDateTime dateMiseEn) {
        this.dateMiseEn = dateMiseEn;
    }
    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }


    @OneToMany(mappedBy = "voiture" )
    @JsonIgnore
    private List<Location> locations;
   @ManyToOne
   @JsonIgnore
   private Models models ;}
