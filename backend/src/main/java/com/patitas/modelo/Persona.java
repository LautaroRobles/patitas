package com.patitas.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaDeNacimiento;
    private String tipoDeDocumento;
    private String nroDocumento;
    private String direccion;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona")
    private List<Contacto> contactos;

    @OneToMany(mappedBy = "duenio",
            orphanRemoval = true,
            fetch = FetchType.LAZY,     // Solo obtengo las mascotas de la base de datos cuando las necesito
            cascade = CascadeType.ALL)  // Si borro la persona se borran todas sus mascotas de la base de datos
    @JsonIgnore
    private List<Mascota> mascota;
}
