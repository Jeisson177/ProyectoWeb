package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Adicional;
import com.example.demo.service.AdicionalServiceImp;


@Controller
@RequestMapping("/adicionales")
public class AdicionalesTablaController {

    @Autowired
    AdicionalServiceImp adicionalService;

    //Mostrar productos en la tabla 
    @GetMapping
    public String mostrarProductos(Model model) {
        model.addAttribute("adicionales", adicionalService.getAllAdicionales());
        return "adicionalesTabla";
    }

    //Mostrar formulario para agregar un nuevo producto
    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("adicional", new Adicional());
        return "agregarAdicional";
    }

    //Guardar nuevo producto
    @PostMapping("/guardar")
    public String guardarAdicional(@ModelAttribute Adicional adicional) {
        adicionalService.guardarAdicional(adicional);
        return "redirect:/adicionales";
    }

    //Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Adicional adicional = adicionalService.getAdicionalById(id);
        model.addAttribute("adicional", adicional);
        return "editarAdicional";
    }

    //Actualizar adicional
    @PostMapping("/actualizar")
    public String actualizarAdicional(@ModelAttribute Adicional adicional) {
        
        adicionalService.actualizarAdicional(adicional);
        
        return "redirect:/adicionales";
    }

    //Eliminar adicional
    @GetMapping("/eliminar/{id}")
    public String eliminarAdicional(@PathVariable Long id) {
        adicionalService.eliminarAdicional(id);
        return "redirect:/adicionales";
    }
    
}
