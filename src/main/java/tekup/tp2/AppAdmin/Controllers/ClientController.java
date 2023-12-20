package tekup.tp2.AppAdmin.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tekup.tp2.AppAdmin.Appfasade;
import tekup.tp2.AppAdmin.Models.Client;
import tekup.tp2.AppAdmin.Models.Voiture;
import tekup.tp2.Metier.Client.Service.ClientService;

@Controller
@RequestMapping("/")
public class ClientController {
    private final Appfasade appfasade;

    public ClientController(Appfasade appfasade) {
        this.appfasade = appfasade;
    }

    @GetMapping("/clients")
    public String listClients(Model model) {
        model.addAttribute("clients", appfasade.getAllclients());
        model.addAttribute("client5", appfasade.getTop5ClientsByRevenue());
        model.addAttribute("clientCount", appfasade.countClients());
        return "client";
    }

    @GetMapping("/client/{id}")
    public String viewClient(@PathVariable Long id, Model model) {
        Client Client = appfasade.getclientById(id);
        model.addAttribute("client", Client);
        return "view-client";
    }

    @GetMapping("/add-client")
    public String showAddClientForm(Model model) {
        model.addAttribute("client", appfasade.newClient());
        return "add-client";
    }

    @PostMapping("/add-client")
    public String saveClient(@ModelAttribute Client Client) {
        appfasade.saveclient(Client);
        return "redirect:/clientPN";
    }

    @GetMapping("/edit-client/{id}")
    public String showEditClientForm(@PathVariable Long id, Model model) {
        Client Client = appfasade.getclientById(id);
        model.addAttribute("client", Client);
        return "edit-client";
    }

    @PostMapping("/update-client")
    public String updateClient(@ModelAttribute Client updatedClient) {
        Client existingClient = appfasade.getclientById(updatedClient.getId());

        // Update the existing voiture with the new values
        existingClient.setNomprenom(updatedClient.getNomprenom());
        existingClient.setNumero(updatedClient.getNumero());
        existingClient.setSex(updatedClient.getSex());

        appfasade.saveclient(existingClient);
        return "redirect:/clientPN";
    }

    @GetMapping("/delete-Client/{id}")
    public String deleteClient(@PathVariable Long id) {
        appfasade.deleteclient(id);
        return "redirect:/clientPN";
    }

}
