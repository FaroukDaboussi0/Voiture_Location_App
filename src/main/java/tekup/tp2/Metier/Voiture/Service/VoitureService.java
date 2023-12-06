package tekup.tp2.Metier.Voiture.Service;

import tekup.tp2.AppAdmin.Models.Voiture;

import java.util.List;

public interface VoitureService {
    List<Voiture> getAllVoitures();
    Voiture getVoitureById(Long id);
    void saveVoiture(Voiture voiture);
    void deleteVoiture(Long id);
    long countVoitures();
    List<Voiture> getTop5VoituresByRevenue();
    List<Voiture> getTopVoituresByRevenue();
    List<Voiture> getAvailableVoitures();
    Voiture newVoitue();

    boolean isVoitureUnique(Voiture voiture);
}
