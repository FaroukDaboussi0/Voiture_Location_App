package tekup.tp2.AppAdmin.Controllers;

import org.springframework.web.bind.annotation.*;
import tekup.tp2.AppAdmin.Appfasade;
import tekup.tp2.AppAdmin.Models.Client;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientRestController {
    private final Appfasade appfasade ;
    public ClientRestController(Appfasade ap){
        appfasade = ap ;
    }
    @PostMapping("/save")
    public void saveclient(@RequestBody Client client ){
        appfasade.saveclient(client);
    }
   @GetMapping("/all")
    public List<Client> getallclient (){
        return appfasade.getAllclients();
   }
   @GetMapping("/get/{id}")
    public Client getclientbyid(@PathVariable("id") long ip){

        return appfasade.getclientById(ip);
   }
   @GetMapping("/count")
    public long countclient(){
        return appfasade.countClients();
   }
   @DeleteMapping("/delete/{id}")
    public void deletebyid(@PathVariable("id") long ip){
        appfasade.deleteclient(ip);
   }
   @PutMapping("/update/{id}")
    void updateclientbyid(@RequestBody Client updatedClient){
       Client existingClient = appfasade.getclientById(updatedClient.getId());


       existingClient.setNomprenom(updatedClient.getNomprenom());
       existingClient.setNumero(updatedClient.getNumero());
       existingClient.setSex(updatedClient.getSex());

       appfasade.saveclient(existingClient);
   }



}
