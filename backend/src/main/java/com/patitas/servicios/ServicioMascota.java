package com.patitas.servicios;

import com.patitas.daos.DaoMascota;
import com.patitas.daos.DaoOrganizacion;
import com.patitas.daos.DaoPersona;
import com.patitas.dto.MascotaDTO;
import com.patitas.modelo.Mascota;
import com.patitas.modelo.Organizacion;
import com.patitas.modelo.Persona;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioMascota {

    @Autowired
    private DaoOrganizacion daoOrganizacion;
    @Autowired
    private DaoPersona daoPersona;
    @Autowired
    private DaoMascota daoMascota;

    private ModelMapper modelMapper = new ModelMapper();

    public MascotaDTO registrarMascota(MascotaDTO mascotaDTO) throws NotFoundException {
        Organizacion organizacion = daoOrganizacion.findById(mascotaDTO.getOrganizacion()).orElseThrow(
                () -> new NotFoundException("No existe organizacion con id "+mascotaDTO.getOrganizacion())
        );
        Persona duenio = daoPersona.findById(mascotaDTO.getDuenio()).orElseThrow(
                () -> new NotFoundException("No existe persona con id "+mascotaDTO.getDuenio())
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
}
