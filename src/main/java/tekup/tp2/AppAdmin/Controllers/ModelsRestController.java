package tekup.tp2.AppAdmin.Controllers;

import org.springframework.web.bind.annotation.*;
import tekup.tp2.AppAdmin.Models.Models;
import tekup.tp2.Metier.Models.Service.ModelsServer;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/model")
public class ModelsRestController {
    private final ModelsServer modelsServer;

    public ModelsRestController(ModelsServer md) {
        this.modelsServer = md;

    }

    @PostMapping("/save")
    public void saveModeles(@RequestBody Models models){

      modelsServer.saveModel(models);
 

    }

    @GetMapping("/all")
    public List<Models> getallModeles(){

        return modelsServer.listModel();
    }
    @GetMapping("/getone/{id}")
    public Optional<Models> getoneModeles(@PathVariable Long id) {

        return  modelsServer.getmodelbyid(id);
    }
    @PutMapping("/update/{id}")
    public Models updateModeles(@PathVariable Long id, @RequestBody Models models){
        Optional<Models> c1=modelsServer.getmodelbyid(id);
        if(c1!=null){
            models.setId(id);
            modelsServer.updateMo(models);
            return models;
        }
        else {
            throw  new RuntimeException("Failed!!!");
        }
    }
    @DeleteMapping("/delete/{id}")
    public HashMap<String,String> deleteModeles(@PathVariable Long id){
        HashMap<String,String> message=new HashMap<>();
        if (modelsServer.getmodelbyid(id).isEmpty()){
            message.put("etat","modele not found");
            return  message;
        }
        try {
            modelsServer.deleteMo(id);
            message.put("etat","modele delete");
            return message;
        }catch (Exception e){
            message.put("etat","modele not deleted");
            return message;
        }
    }



}

