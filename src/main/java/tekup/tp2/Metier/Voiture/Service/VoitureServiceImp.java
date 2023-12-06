package tekup.tp2.Metier.Voiture.Service;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tekup.tp2.AppAdmin.Models.Voiture;
import tekup.tp2.Metier.Repository.VoitureRepository;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VoitureServiceImp implements VoitureService {
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    private final VoitureRepository voitureRepository;

    public VoitureServiceImp(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
    }

    public List<Voiture> getAllVoitures() {
        return voitureRepository.findAll();
    }

    public Voiture getVoitureById(Long id) {
        return voitureRepository.findById(id).orElse(null);
    }

    public void saveVoiture(Voiture voiture) {
        voitureRepository.save(voiture);
    }

    public void deleteVoiture(Long id) {
        voitureRepository.deleteById(id);
    }
    public long countVoitures() {

        return voitureRepository.count();

    }
    public double calculateTotalRevenueForVoiture(Voiture voiture) {
        int totalDaysRented = voiture.getLocations().stream()
                .mapToInt(location -> (int) ChronoUnit.DAYS.between(
                        location.getDate_debut().toLocalDate(),
                        location.getDate_retour().toLocalDate()))
                .sum();

        return totalDaysRented * voiture.getPrice();
    }

    public List<Voiture> getTop5VoituresByRevenue() {

        List<Voiture> top5Voitures = getTopVoituresByRevenue();
        return top5Voitures.subList(0, Math.min(5, top5Voitures.size()));
    }

    public List<Voiture> getTopVoituresByRevenue() {
        entityManagerFactory.getCache().evictAll();
        List<Voiture> allVoitures = voitureRepository.findAll(); // Fetch all Voiture objects


            // Filter out cars with no rentals
            List<Voiture> carsWithRentals = allVoitures.stream()
                    .filter(voiture -> !voiture.getLocations().isEmpty())
                    .collect(Collectors.toList());

            // Calculate revenue for each Voiture
            carsWithRentals.forEach(voiture -> {
                // Check if the car has any rentals

                // Calculate the total number of days rented
                int totalDaysRented = voiture.getLocations().stream()
                        .mapToInt(location -> (int) ChronoUnit.DAYS.between(
                                location.getDate_debut().toLocalDate(),
                                location.getDate_retour().toLocalDate()))
                        .sum();

                // Calculate the total revenue
                double totalRevenue = totalDaysRented * voiture.getPrice();

                // Set the total revenue for the car
                voiture.setTotalRevenue(totalRevenue);
            });

                // Sort Voiture objects based on revenue in descending order
                carsWithRentals.sort(Comparator.comparingDouble(Voiture::getTotalRevenue).reversed());


                return carsWithRentals; // Return only cars with rentals
            }







    public List<Voiture> getAvailableVoitures() {
        List<Voiture> allVoitures = voitureRepository.findAll(); // Fetch all Voiture objects

        // Filter available Voiture objects
        return allVoitures.stream()
                .filter(Voiture::isDisponibility) // Assuming isDisponibility() returns the availability status
                .collect(Collectors.toList());
    }


    public Voiture newVoitue() {
        return new Voiture();
    }

    @Override
    public boolean isVoitureUnique(Voiture voiture) {
        List<Voiture> existingVoitures = getAllVoitures();

        for (Voiture existingVoiture : existingVoitures) {
            if (existingVoiture.getSerie().equals(voiture.getSerie()) &&
                    existingVoiture.getModel().equals(voiture.getModel())) {
                return false; // Not unique - a similar Voiture already exists
            }
        }

        return true; // Unique - no matching Voiture found
    }


}

