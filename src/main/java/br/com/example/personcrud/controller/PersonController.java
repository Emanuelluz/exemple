package br.com.example.personcrud.controller;

import br.com.example.personcrud.model.Person;
import br.com.example.personcrud.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("people", service.findAll());
        return "people/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("person", new Person());
        return "people/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "people/form";
        }
        service.save(person);
        return "redirect:/people";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        var p = service.findById(id).orElseThrow();
        model.addAttribute("person", p);
        return "people/form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/people";
    }
}
