package tekup.tp2.AppAdmin.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tekup.tp2.AppAdmin.Appfasade;
import tekup.tp2.AppAdmin.Models.Voiture;
import tekup.tp2.Metier.Voiture.Service.VoitureService;

@Controller
@RequestMapping("/")
public class VoitureController {
    @Autowired
    private Appfasade appfasade;

    @GetMapping("/voitures")
    public String listVoitures(Model model) {
        model.addAttribute("voitures", appfasade.getAllVoitures());
        model.addAttribute("voitures5", appfasade.getTop5VoituresByRevenue());
        model.addAttribute("voitureCount", appfasade.countVoitures());
        model.addAttribute("voitureAV", appfasade.getAvailableVoitures());
        return "voiture";
    }


    @GetMapping("/voiture/{id}")
    public String viewVoiture(@PathVariable Long id, Model model) {
        Voiture voiture = appfasade.getVoitureById(id);
        model.addAttribute("voiture", voiture);
        return "view-voiture";
    }

    @GetMapping("/add-voiture")
    public String showAddVoitureForm(Model model) {
        model.addAttribute("voiture", appfasade.newVoitue());
        return "add-voiture";
    }

    @PostMapping("/add-voiture")
    public String saveVoiture(@ModelAttribute Voiture voiture) {
        appfasade.saveVoiture(voiture);
        return "redirect:/voiturePN";
    }

    @GetMapping("/edit-voiture/{id}")
    public String showEditVoitureForm(@PathVariable Long id, Model model) {
        Voiture voiture = appfasade.getVoitureById(id);
        model.addAttribute("voiture", voiture);
        return "edit-voiture";
    }

    @PostMapping("/update-voiture")
    public String updateVoiture(@ModelAttribute Voiture voiture) {
        appfasade.saveVoiture(voiture);
        return "redirect:/voiturePN";
    }

    @GetMapping("/delete-voiture/{id}")
    public String deleteVoiture(@PathVariable Long id) {
        appfasade.deleteVoiture(id);
        return "redirect:/voiturePN";
    }

}
