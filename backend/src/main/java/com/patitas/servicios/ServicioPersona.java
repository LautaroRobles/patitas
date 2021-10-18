package com.patitas.servicios;

import com.patitas.daos.DaoPersona;
import com.patitas.dto.RegistrarPersonaDTO;
import com.patitas.modelo.Contacto;
import com.patitas.modelo.Persona;
import com.patitas.modelo.enviadorNotificaciones.Mensaje;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPersona {

    @Autowired
    private DaoPersona daoPersona;

    private ModelMapper modelMapper = new ModelMapper();

    public Persona registrarPersona(RegistrarPersonaDTO registrarPersonaDTO) {
        Persona nuevaPersona = modelMapper.map(registrarPersonaDTO, Persona.class);
        return daoPersona.save(nuevaPersona);
    }

    public Persona getPersona(Long idPersona) throws NotFoundException {
        return daoPersona.findById(idPersona).orElseThrow(
                () -> new NotFoundException("No existe persona con id "+idPersona)
        );
    }

    public Mensaje notificarPersona(Long idPersona, String titulo, String cuerpo) throws NotFoundException {
        Persona persona = getPersona(idPersona);

        List<Contacto> contactos = persona.getContactos();
        Mensaje mensaje = new Mensaje();
        mensaje.setTitulo(titulo);
        mensaje.setCuerpo(cuerpo);

        contactos.forEach(contacto -> {
            contacto.enviarNotificaciones(mensaje);
        });

        return mensaje;
    }
}
