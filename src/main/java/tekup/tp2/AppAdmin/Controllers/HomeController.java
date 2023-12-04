package tekup.tp2.AppAdmin.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import tekup.tp2.Metier.Client.Service.ClientServiceImp;
import tekup.tp2.Metier.Voiture.Service.VoitureServiceImp;

@Controller

public class HomeController {
    @Autowired
    private VoitureServiceImp voitureService;
    @Autowired
    private ClientServiceImp clientService;
    @GetMapping("/")
    public String home(Model model) {
        long clientCount = clientService.countClients();
        model.addAttribute("clientCount", clientCount);
        long voitureCount = voitureService.countVoitures();
        model.addAttribute("voitureCount", voitureCount);
        return "indexPN"; // This corresponds to the HTML file named "index.html"

    }}






