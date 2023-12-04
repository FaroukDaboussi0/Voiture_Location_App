package tekup.tp2.Metier.Client.Service;


import org.springframework.stereotype.Service;
import tekup.tp2.AppAdmin.Models.Client;

import tekup.tp2.AppAdmin.Models.Location;
import tekup.tp2.Metier.Repository.ClientRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImp implements ClientService {
    public final ClientRepository clientRepository;

    public ClientServiceImp(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllclients() {
        return clientRepository.findAll();
    }

    public Client getclientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void saveclient(Client client) {
        clientRepository.save(client);
    }

    public void deleteclient(Long id) {
        clientRepository.deleteById(id);
    }

    public long countClients() {
        return clientRepository.count();
    }

    public double calculateTotalRevenueForClient(Client client) {
        List<Location> clientLocations = client.getLocations();

        return clientLocations.stream()
                .mapToDouble(location -> {
                    int totalDaysRented = (int) ChronoUnit.DAYS.between(location.getDate_debut().toLocalDate(), location.getDate_retour().toLocalDate());
                    return totalDaysRented * location.getVoiture().getPrice();
                })
                .sum();
    }

    public List<Client> getTop5ClientsByRevenue() {
        List<Client> allClients = clientRepository.findAll(); // Fetch all Client objects

        // Calculate revenue for each Client
        allClients.forEach(this::calculateTotalRevenueForClient);

        // Sort Client objects based on revenue in descending order
        allClients.sort(Comparator.comparingDouble(this::calculateTotalRevenueForClient).reversed());

        // Return top 5 Client objects
        return allClients.stream().limit(5).collect(Collectors.toList());
    }

    public List<Client> getTopClientsByRevenue() {
        List<Client> allClients = clientRepository.findAll(); // Fetch all Client objects

        // Calculate revenue for each Client
        allClients.forEach(this::calculateTotalRevenueForClient);

        // Sort Client objects based on revenue in descending order
        allClients.sort(Comparator.comparingDouble(this::calculateTotalRevenueForClient).reversed());


        return new ArrayList<>(allClients);
    }

    public List<Client> getClientsCurrentlyRentingCars() {
        List<Client> allClients = clientRepository.findAll(); // Fetch all Client objects

        // Filter clients with active car rentals

        return allClients.stream()
                .filter(client -> client.getLocations().stream()
                        .anyMatch(location -> location.getDate_retour().isAfter(LocalDateTime.now())))
                .collect(Collectors.toList());
    }


    public Client newClient() {
        return new Client();
    }
}


