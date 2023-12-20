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
        List<Client> top5Clients =  getTopClientsByRevenue();



        // Return top 5 Client objects
        return top5Clients.subList(0, Math.min(5, top5Clients.size()));
    }

    public List<Client> getTopClientsByRevenue() {
        List<Client> allClients = clientRepository.findAll();

        // Filter clients with rentals
        List<Client> clientsWithRentals = allClients.stream()
                .filter(client -> !client.getLocations().isEmpty())
                .collect(Collectors.toList());

        // Calculate revenue for each client
        clientsWithRentals.forEach(client -> {
            List<Location> clientLocations = client.getLocations();

            // Check if the client has any active rentals
            boolean isActive = clientLocations.stream()
                    .anyMatch(location -> location.getDate_retour().isAfter(LocalDateTime.now()));

            // Set the client's active status
            client.setActive(isActive);

            // Calculate total revenue for the client based on their rentals
            double totalRevenue = clientLocations.stream()
                    .mapToDouble(location -> {
                        int totalDaysRented = (int) ChronoUnit.DAYS.between(
                                location.getDate_debut().toLocalDate(),
                                location.getDate_retour().toLocalDate());
                        return totalDaysRented * location.getVoiture().getPrice();
                    })
                    .sum();

            // Set the total revenue for the client
            client.setTotalRevenue(totalRevenue);
        });


        // Sort clients based on total revenue in descending order
        clientsWithRentals.sort(Comparator.comparingDouble(Client::getTotalRevenue).reversed());

        return clientsWithRentals;
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


