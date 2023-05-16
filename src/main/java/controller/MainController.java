package controller;

import controller.serviceLayer.UserNotFoundExcpetion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {
    private PatientService service;
    private Object listPatients;

    @GetMapping("/patients")
    public String ShowPatientList(Model model) {

        List<Patient> listPatients= service.listAll();

        model.addAttribute("listPatients", listPatients);
        return "patients";
    }
    @GetMapping("/patients/new")
    public String ShowPatientForm( Model model){

        model.addAttribute("patient", new Patient());
        return "patient_form";
    }
    @PostMapping("/patient/new")
    public String savePatient(@ModelAttribute Patient patient, RedirectAttributes ra) {
        service.save(patient);
        ra.addAttribute("message", "Data has been saved successfully");
        return "redirect:/patients";
    }
    @GetMapping("/patients/edit/{id}")
    public  String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){

        Patient patient= service.get(id);
        model.addAttribute("patient", patient);
        model.addAttribute("pageTitle", "Edit Patient (ID "+ id +")");
        return "patient_form";

    }
}
