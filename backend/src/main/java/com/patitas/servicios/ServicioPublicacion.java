package com.patitas.servicios;

import com.patitas.daos.DaoOrganizacion;
import com.patitas.daos.DaoPersona;
import com.patitas.daos.DaoPublicacion;
import com.patitas.dto.CrearPublicacionDTO;
import com.patitas.modelo.*;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioPublicacion {
    @Autowired
    private DaoOrganizacion daoOrganizacion;
    @Autowired
    private DaoPersona daoPersona;
    @Autowired
    private DaoPublicacion daoPublicacion;

    private ModelMapper modelMapper = new ModelMapper();

    private Organizacion validarOrganizacion(CrearPublicacionDTO crearPublicacionDTO) throws NotFoundException {
        return daoOrganizacion.findById(crearPublicacionDTO.getId_organizacion()).orElseThrow(
                () -> new NotFoundException("No existe organizacion con id "+crearPublicacionDTO.getId_organizacion())
        );
    }

    private Persona validarAutor(CrearPublicacionDTO crearPublicacionDTO) throws NotFoundException {
        Persona autor = null;

        if(crearPublicacionDTO.getId_autor() != null) {
            autor = daoPersona.findById(crearPublicacionDTO.getId_autor()).orElseThrow(
                    () -> new NotFoundException("No existe persona con id "+crearPublicacionDTO.getId_autor())
            );
        }

        return autor;
    }

    public Publicacion crearPublicacion(CrearPublicacionDTO crearPublicacionDTO) throws NotFoundException {
        Organizacion organizacion = validarOrganizacion(crearPublicacionDTO);
        Persona autor = validarAutor(crearPublicacionDTO);

        Publicacion nuevaPublicacion = modelMapper.map(crearPublicacionDTO, Publicacion.class);

        nuevaPublicacion.setOrganizacion(organizacion);
        nuevaPublicacion.setAutor(autor);
        nuevaPublicacion.setAprobada(false);

        nuevaPublicacion = daoPublicacion.save(nuevaPublicacion);

        organizacion.getPublicaciones().add(nuevaPublicacion);

        daoOrganizacion.save(organizacion);

        return nuevaPublicacion;
    }

    public MascotaEnAdopcion crearMascotaEnAdopcion(CrearPublicacionDTO crearPublicacionDTO) throws NotFoundException {
        Organizacion organizacion = validarOrganizacion(crearPublicacionDTO);
        Persona autor = validarAutor(crearPublicacionDTO);

        MascotaEnAdopcion nuevaPublicacion = modelMapper.map(crearPublicacionDTO, MascotaEnAdopcion.class);

        nuevaPublicacion.setOrganizacion(organizacion);
        nuevaPublicacion.setAutor(autor);
        nuevaPublicacion.setAprobada(false);

        nuevaPublicacion = daoPublicacion.save(nuevaPublicacion);

        organizacion.getPublicaciones().add(nuevaPublicacion);

        daoOrganizacion.save(organizacion);

        return nuevaPublicacion;
    }

    public QuieroAdoptar crearQuieroAdoptar(CrearPublicacionDTO crearPublicacionDTO) throws NotFoundException {
        Organizacion organizacion = validarOrganizacion(crearPublicacionDTO);
        Persona autor = validarAutor(crearPublicacionDTO);

        QuieroAdoptar nuevaPublicacion = modelMapper.map(crearPublicacionDTO, QuieroAdoptar.class);

        nuevaPublicacion.setOrganizacion(organizacion);
        nuevaPublicacion.setAutor(autor);
        nuevaPublicacion.setAprobada(false);

        nuevaPublicacion = daoPublicacion.save(nuevaPublicacion);

        organizacion.getPublicaciones().add(nuevaPublicacion);

        daoOrganizacion.save(organizacion);

        return nuevaPublicacion;
    }

    public List<Publicacion> listadoPublicaciones(){return daoPublicacion.findAll(); }
    public List<QuieroAdoptar> listadoQuieroAdoptar() {return daoPublicacion.findAllQuieroAdoptar(); }
    public List<MascotaEnAdopcion> listadoMascotaEnAdopcion() {return daoPublicacion.findAllMascotaEnAdopcion(); }

    public Publicacion findPublicacion(Long idPublicacion) throws NotFoundException {
        return daoPublicacion.findById(idPublicacion).orElseThrow(
                () -> new NotFoundException("No existe publicacion con id "+idPublicacion)
        );
    }

    public Publicacion aprobarPublicacion(Long idPublicacion) throws NotFoundException {
        Publicacion publicacion = findPublicacion(idPublicacion);
        publicacion.setAprobada(true);
        return daoPublicacion.save(publicacion);
    }

}
