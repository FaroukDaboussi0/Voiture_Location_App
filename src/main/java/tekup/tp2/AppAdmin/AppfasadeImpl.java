package tekup.tp2.AppAdmin;

import org.springframework.stereotype.Component;
import tekup.tp2.AppAdmin.Models.Client;
import tekup.tp2.AppAdmin.Models.Voiture;
import tekup.tp2.Metier.Client.Service.ClientService;
import tekup.tp2.Metier.Voiture.Service.VoitureService;

import java.util.List;
@Component()
public class AppfasadeImpl implements Appfasade {

    public final ClientService clientService ;
    public final VoitureService voitureService;
    public AppfasadeImpl(ClientService clientService, VoitureService voitureService){
        this.clientService = clientService;
        this.voitureService=voitureService ;}
    @Override
    public void saveclient(Client client) {
        clientService.saveclient(client);

    }

    @Override
    public void deleteclient(Long id) {
        clientService.deleteclient(id);

    }

    @Override
    public List<Client> getAllclients() {
        return clientService.getAllclients();
    }

    @Override
    public Client getclientById(Long id) {
        return clientService.getclientById(id);
    }

    @Override
    public List<Client> getTop5ClientsByRevenue() {
        return clientService.getTop5ClientsByRevenue();
    }

    @Override
    public List<Client> getTopClientsByRevenue() {
        return clientService.getTopClientsByRevenue();
    }

    @Override
    public List<Client> getClientsCurrentlyRentingCars() {
        return clientService.getClientsCurrentlyRentingCars();
    }

    @Override
    public long countClients() {
        return clientService.countClients();
    }

    @Override
    public Client newClient() {
        return clientService.newClient();
    }

    @Override
    public void deleteVoiture(Long id) {
        voitureService.deleteVoiture(id);

    }

    @Override
    public void saveVoiture(Voiture voiture) {
        voitureService.saveVoiture(voiture);

    }

    @Override
    public List<Voiture> getAllVoitures() {
        return voitureService.getAllVoitures();
    }

    @Override
    public Voiture getVoitureById(Long id) {
        return voitureService.getVoitureById(id);
    }

    @Override
    public List<Voiture> getTop5VoituresByRevenue() {
        return voitureService.getTop5VoituresByRevenue();
    }

    @Override
    public List<Voiture> getTopVoituresByRevenue() {
        return voitureService.getTopVoituresByRevenue();
    }

    @Override
    public List<Voiture> getAvailableVoitures() {
        return voitureService.getAvailableVoitures();
    }

    @Override
    public Voiture newVoitue() {
        return voitureService.newVoitue();
    }

    @Override
    public long countVoitures() {
        return voitureService.countVoitures();
    }
}
