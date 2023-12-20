package tekup.tp2.Metier.Models.Service;

import tekup.tp2.AppAdmin.Models.Models;

import java.util.List;
import java.util.Optional;

public interface ModelsServer {
    void saveModel(Models models) ;
    public List<Models> listModel ();
    public Optional<Models> getmodelbyid(Long id);
    public  void updateMo (Models newmodels);
    void deleteMo(Long id);
}
