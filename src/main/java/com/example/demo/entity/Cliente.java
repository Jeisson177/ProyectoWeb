package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Data
public class Cliente {

    @NotNull
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String Nombre;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(nullable = false)   
    private String Apellido;
    @NotNull
    @Email
    @Column(nullable = false, unique = true,name = "correo")
    private String correo;

    @NotNull
    @Size(min = 8)
    @Column(nullable = false)
    private String contrasena;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false)
    private String Direccion;

    @Pattern(regexp = "\\d{10}", message = "El teléfono debe tener 10 dígitos")
    @Column(nullable = false)
    private String Telefono;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidos;

    @Id
    @GeneratedValue
    private Long id;

    public Cliente(Long id, String Nombre, String Apellido, String correo, String contrasena, String Direccion, String Telefono) {
        this.id = id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
    }

    public Cliente( String Nombre, String Apellido, String correo, String contrasena, String Direccion, String Telefono) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
    }

    public Cliente() {}

   


}