package com.patitas.controladores;

import com.patitas.dto.ContactoDTO;
import com.patitas.modelo.Contacto;
import com.patitas.repositorios.RepositorioContacto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TestContacto {

    @Autowired
    private RepositorioContacto repositorioContacto;
    private static final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/contacto")
    public List<ContactoDTO> getContacto() {
        List<Contacto> contactos = repositorioContacto.findAll();
        List<ContactoDTO> contactoDTOS = contactos.stream().map(
                contacto -> modelMapper.map(contacto, ContactoDTO.class)
        ).collect(Collectors.toList());
        return contactoDTOS;
    }

    @PostMapping("/contacto")
    public ResponseEntity<ContactoDTO> insertContacto(@RequestBody Contacto contacto) {
        Contacto contactoGuardado = repositorioContacto.save(contacto);
        ContactoDTO contactoDTO = modelMapper.map(contactoGuardado, ContactoDTO.class);
        return new ResponseEntity<>(contactoDTO, HttpStatus.CREATED);
    }
}
