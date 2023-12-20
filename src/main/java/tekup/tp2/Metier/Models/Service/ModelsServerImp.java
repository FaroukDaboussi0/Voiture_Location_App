package tekup.tp2.Metier.Models.Service;

import org.springframework.stereotype.Service;
import tekup.tp2.AppAdmin.Models.Models;
import tekup.tp2.Metier.Repository.ModelsRepository;

import java.util.List;
import java.util.Optional;
@Service
public class ModelsServerImp implements ModelsServer{

    private final ModelsRepository modelsRepository;
    public ModelsServerImp(ModelsRepository mr){
        modelsRepository = mr;
    }
  public  void saveModel(Models models) {
        modelsRepository.save(models);

  };
    public List<Models> listModel (){
        return modelsRepository.findAll();
    };
    public Optional<Models> getmodelbyid(Long id){
        return modelsRepository.findById(id);
    };
   public  void updateMo (Models newmodels){
       Optional<Models> oldmodels=modelsRepository.findById(newmodels.id);
       newmodels.name=oldmodels.get().name;
       modelsRepository.save(newmodels);

   };

   public void deleteMo(Long id){};

}
