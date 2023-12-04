package tekup.tp2.Metier.Client.Service;

import tekup.tp2.AppAdmin.Models.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllclients();
    Client getclientById(Long id);
    void saveclient(Client client);
    void deleteclient(Long id);
    long countClients();
    double calculateTotalRevenueForClient(Client client);
    List<Client> getTop5ClientsByRevenue();
    List<Client> getTopClientsByRevenue();
    List<Client> getClientsCurrentlyRentingCars();
    Client newClient();
}
