package com.patitas.servicios;

import com.patitas.daos.DaoMascota;
import com.patitas.daos.DaoOrganizacion;
import com.patitas.daos.DaoPersona;
import com.patitas.dto.ListadoMascotasDTO;
import com.patitas.dto.MascotaDTO;
import com.patitas.modelo.Contacto;
import com.patitas.modelo.Mascota;
import com.patitas.modelo.Organizacion;
import com.patitas.modelo.Persona;
import com.patitas.modelo.enviadorNotificaciones.Mensaje;
import com.patitas.seguridad.Usuario;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioMascota {

    @Autowired
    private DaoOrganizacion daoOrganizacion;
    @Autowired
    private DaoPersona daoPersona;
    @Autowired
    private DaoMascota daoMascota;
    @Autowired
    private ServicioPersona servicioPersona;
    @Autowired
    private ServicioUsuario servicioUsuario;

    private ModelMapper modelMapper = new ModelMapper();

    public MascotaDTO registrarMascota(MascotaDTO mascotaDTO) throws NotFoundException {
        Organizacion organizacion = daoOrganizacion.findById(mascotaDTO.getOrganizacion_id()).orElseThrow(
                () -> new NotFoundException("No existe organizacion con id "+mascotaDTO.getOrganizacion_id())
        );
        Persona duenio = daoPersona.findById(mascotaDTO.getDuenio_id()).orElseThrow(
                () -> new NotFoundException("No existe persona con id "+mascotaDTO.getDuenio_id())
        );

        Mascota mascota = new Mascota();
        
        mascota.setDuenio(duenio);
        mascota.setOrganizacion(organizacion);
        mascota.setNombre(mascotaDTO.getNombre());
        mascota.setApodo(mascotaDTO.getApodo());
        mascota.setEdad(mascotaDTO.getEdad());
        mascota.setEspecie(mascotaDTO.getEspecie());
        mascota.setSexo(mascotaDTO.getSexo());
        mascota.setCaracteristicas(mascotaDTO.getCaracteristicas());
        mascota.setDescripcion(mascotaDTO.getDescripcion());

        return modelMapper.map(daoMascota.save(mascota), MascotaDTO.class);
    }

    public ListadoMascotasDTO registrarVariasMascotas(ListadoMascotasDTO listadoMascotasDTO) throws NotFoundException {
        List<MascotaDTO> mascotas = listadoMascotasDTO.getMascotas();
        List<MascotaDTO> mascotasGuardadas = new ArrayList<>();
        ListadoMascotasDTO listadoMascotasGuardads = new ListadoMascotasDTO();

        for(int i = 0; i < mascotas.size(); i++) {
            mascotasGuardadas.add(registrarMascota(mascotas.get(i)));
        }
        listadoMascotasGuardads.setMascotas(mascotasGuardadas);

        return listadoMascotasGuardads;
    }

    public MascotaDTO getMascota(Long idMascota) throws NotFoundException {
        Mascota mascota = daoMascota.findById(idMascota).orElseThrow(
                () -> new NotFoundException("No existe mascota con id "+idMascota)
        );

        MascotaDTO mascotaDTO = modelMapper.map(mascota, MascotaDTO.class);
        mascotaDTO.setOrganizacion_id(mascota.getOrganizacion().getId());
        mascotaDTO.setDuenio_id(mascota.getDuenio().getId());

        return mascotaDTO;
    }

    public List<MascotaDTO> listadoMascotas(String duenio) throws NotFoundException {
        Usuario usuario = servicioUsuario.getUsuario(duenio);

        Persona persona = usuario.getPersona();

        if(persona != null) {
            List<MascotaDTO> mascotasDTO = new ArrayList<>();

            for (Mascota mascota : persona.getMascota()) {
                mascotasDTO.add(modelMapper.map(mascota, MascotaDTO.class));
            }

            return mascotasDTO;
        }
        else {
            throw new NotFoundException("El usuario '"+usuario.getUsername()+"' no tiene datos de persona");
        }
    }

    public Mensaje encontreATuMascota(Long idMascota, Long idRescatista) throws NotFoundException {
        Persona rescatista = daoPersona.findById(idRescatista).orElseThrow(
                () -> new NotFoundException("No existe rescatista con id "+idRescatista)
        );

        Mascota mascota = daoMascota.findById(idMascota).orElseThrow(
                () -> new NotFoundException("No existe mascota con id "+idMascota)
        );

        Persona duenio = mascota.getDuenio();

        Mensaje ultimoMensaje = new Mensaje();

        for(int i = 0; i < duenio.getContactos().size(); i++) {
            Contacto contacto = duenio.getContactos().get(i);

            String titulo = "Notificacion de mascota encontrada";
            StringBuilder cuerpo = new StringBuilder("Hola " + contacto.getNombre() + ", encontre tu mascota '" + mascota.getApodo() + "'.\nTe paso mis datos de contactos:\n");

            for(int j = 0; j < rescatista.getContactos().size(); j++) {
                Contacto contactoRescatista = rescatista.getContactos().get(j);

                cuerpo.append("\n");

                cuerpo.append("Nombre: ").append(contactoRescatista.getNombre() + " " + contactoRescatista.getApellido() + "\n");

                if(!contactoRescatista.getTelefono().equals(""))
                    cuerpo.append("Telefono: ").append(contactoRescatista.getTelefono()).append("\n");

                if(!contactoRescatista.getEmail().equals(""))
                    cuerpo.append("Email: ").append(contactoRescatista.getEmail()).append("\n");

                cuerpo.append("\n");
            }

            cuerpo.append("Saludos, ").append(rescatista.getNombre());

            ultimoMensaje = servicioPersona.notificarContacto(contacto, titulo, cuerpo.toString());
        };


        return ultimoMensaje;
    }
}
