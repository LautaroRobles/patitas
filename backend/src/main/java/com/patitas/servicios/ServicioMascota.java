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

    public Mensaje encontreATuMascota(Long idMascota, Long idRescatista) throws NotFoundException {
        Persona rescatista = daoPersona.findById(idRescatista).orElseThrow(
                () -> new NotFoundException("No existe rescatista con id "+idRescatista)
        );

        Mascota mascota = daoMascota.findById(idMascota).orElseThrow(
                () -> new NotFoundException("No existe mascota con id "+idMascota)
        );

        Persona duenio = mascota.getDuenio();

        String titulo = "";
        StringBuilder cuerpo = new StringBuilder("Hola " + duenio.getNombre() + ", encontre tu mascota '" + mascota.getApodo() + "'.\nTe paso mis datos de contacto:\n");

        for(int i = 0; i < duenio.getContactos().size(); i++) {
            Contacto contacto = duenio.getContactos().get(i);

            if(!contacto.getTelefono().equals(""))
                cuerpo.append("Telefono: ").append(contacto.getTelefono()).append("\n");

            if(!contacto.getEmail().equals(""))
                cuerpo.append("Email: ").append(contacto.getEmail()).append("\n");
        };

        cuerpo.append("Saludos, ").append(rescatista.getNombre());

        return servicioPersona.notificarPersona(duenio.getId(), titulo, cuerpo.toString());
    }
}
