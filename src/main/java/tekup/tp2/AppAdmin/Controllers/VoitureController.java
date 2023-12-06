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
        System.out.println("must be voiture.html"); // Printing a success message
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
        return "addV";
    }

    @PostMapping("/add-voiture")
    public String saveVoiture(@ModelAttribute Voiture voiture) {
        if (appfasade.isVoitureUnique(voiture)) {
            // If unique, save the Voiture
            appfasade.saveVoiture(voiture);
            System.out.println("Voiture saved successfully: " + voiture.getId());
            return "voiture"; // Redirect or return to a specific view after successful save
        } else {
            // If not unique, do nothing or add a message to indicate the duplicate entry
            return ""; // Returning an empty string or null here
        }
    }


    @GetMapping("/edit-voiture/{id}")
    public String showEditVoitureForm(@PathVariable Long id, Model model) {
        Voiture voiture = appfasade.getVoitureById(id);
        model.addAttribute("voiture", voiture);
        return "addV";
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
