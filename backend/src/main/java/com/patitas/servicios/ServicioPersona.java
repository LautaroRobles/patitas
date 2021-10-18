package com.patitas.servicios;

import com.patitas.daos.DaoPersona;
import com.patitas.dto.RegistrarPersonaDTO;
import com.patitas.modelo.Persona;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPersona {

    @Autowired
    private DaoPersona daoPersona;

    private ModelMapper modelMapper = new ModelMapper();

    public Persona registrarPersona(RegistrarPersonaDTO registrarPersonaDTO) {
        Persona nuevaPersona = modelMapper.map(registrarPersonaDTO, Persona.class);
        return daoPersona.save(nuevaPersona);
    }
}
