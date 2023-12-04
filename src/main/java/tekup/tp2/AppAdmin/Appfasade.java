package tekup.tp2.AppAdmin;

import tekup.tp2.AppAdmin.Models.Client;
import tekup.tp2.AppAdmin.Models.Voiture;

import java.util.List;

public interface Appfasade {

    //Client methods
       //-Edit methods
       void saveclient(Client client);
       void deleteclient(Long id);

       //-Get methods

       List<Client> getAllclients();
       Client getclientById(Long id);
       List<Client> getTop5ClientsByRevenue();
       List<Client> getTopClientsByRevenue();
       List<Client> getClientsCurrentlyRentingCars();

       //-other methods
       long countClients();
       Client newClient();

    //Voiture methods
       //-Edit methods
      void deleteVoiture(Long id);
      void saveVoiture(Voiture voiture);
       //-Get methods
       List<Voiture> getAllVoitures();
       Voiture getVoitureById(Long id);
       List<Voiture> getTop5VoituresByRevenue();
       List<Voiture> getTopVoituresByRevenue();
       List<Voiture> getAvailableVoitures();

       //-other methods
       Voiture newVoitue();
       long countVoitures();




}
