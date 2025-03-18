package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Adicional;
import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.AdicionalService;
import com.example.demo.service.ProductoServiceImp;

@Controller
@RequestMapping("/producto")
public class ProductoTablaController {
    @Autowired
    ProductoRepository productoRepository;


    @Autowired
    ProductoServiceImp productoService;

    @Autowired
    AdicionalService adicionalService;

    // Mostrar productos en la tabla
    @GetMapping("/pr")
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", productoService.getAllProductos());
        return "menu_Tabla";
    }

    // Mostrar formulario para agregar un nuevo producto
    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("producto", new Producto());
        return "agregar_Producto";
    }

    // Guardar nuevo producto
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/producto/pr";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Producto producto = productoService.getProductoWithAdicionales(id);
        model.addAttribute("producto", producto);
        model.addAttribute("adicionales", adicionalService.getAllAdicionales());
        return "editar_Producto";
    }

    // Actualizar producto
    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute Producto producto, 
                                    @RequestParam(name = "adicionales", required = false) List<Long> adicionalesIn,
                                    RedirectAttributes redirectAttributes) {
        List<Adicional> adicionales = adicionalesIn != null ? adicionalService.getAdicionalesByIds(adicionalesIn) : new ArrayList<>();
        
        productoService.actualizarProducto(producto, adicionales);

        return "redirect:/producto/pr";
    }


    // Eliminar producto
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/producto/pr";
    }
}
