package com.patitas.servicios;

import com.patitas.daos.*;
import com.patitas.dto.CrearPublicacionDTO;
import com.patitas.modelo.*;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPublicacion {
    @Autowired
    private DaoOrganizacion daoOrganizacion;
    @Autowired
    private DaoPersona daoPersona;
    @Autowired
    private DaoPublicacion daoPublicacion;
    @Autowired
    private DaoPregunta daoPregunta;
    @Autowired
    private DaoCategoria daoCategoria;
    @Autowired
    private DaoTipoCaracteristica daoTipoCaracteristica;

    private ModelMapper modelMapper = new ModelMapper();

    private Organizacion validarOrganizacion(CrearPublicacionDTO crearPublicacionDTO) throws NotFoundException {
        return daoOrganizacion.findById(crearPublicacionDTO.getOrganizacion_id()).orElseThrow(
                () -> new NotFoundException("No existe organizacion con id "+crearPublicacionDTO.getOrganizacion_id())
        );
    }

    private Persona validarAutor(CrearPublicacionDTO crearPublicacionDTO) throws NotFoundException {
        Persona autor = null;

        if(crearPublicacionDTO.getAutor_id() != null) {
            autor = daoPersona.findById(crearPublicacionDTO.getAutor_id()).orElseThrow(
                    () -> new NotFoundException("No existe autor con id "+crearPublicacionDTO.getAutor_id())
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

        for(int i = 0; i < nuevaPublicacion.getRespuestas().size(); i++) {
            Respuesta respuesta = nuevaPublicacion.getRespuestas().get(i);
            Long pregunta_id = respuesta.getPregunta().getId();

            if(pregunta_id == null) {
                throw new NotFoundException("Se requiere un id de pregunta ");
            }

            respuesta.setPregunta(daoPregunta.findById(pregunta_id).orElseThrow(
                    () -> new NotFoundException("No existe pregunta con id "+pregunta_id)
            ));
        }

        for(int i = 0; i < nuevaPublicacion.getCaracteristicas().size(); i++) {
            Caracteristica caracteristica = nuevaPublicacion.getCaracteristicas().get(i);
            Long tipo_caracteristica_id = caracteristica.getTipoCaracteristica().getId();

            if(tipo_caracteristica_id == null) {
                throw new NotFoundException("Se requiere un id de tipo caracteristica ");
            }

            caracteristica.setTipoCaracteristica(daoTipoCaracteristica.findById(tipo_caracteristica_id).orElseThrow(
                    () -> new NotFoundException("No existe tipo caracteristica con id "+tipo_caracteristica_id)
            ));
        }

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

        for(int i = 0; i < nuevaPublicacion.getPreferencias().size(); i++) {
            Caracteristica caracteristica = nuevaPublicacion.getPreferencias().get(i);
            Long tipo_caracteristica_id = caracteristica.getTipoCaracteristica().getId();

            if(tipo_caracteristica_id == null) {
                throw new NotFoundException("Se requiere un id de tipo caracteristica ");
            }

            caracteristica.setTipoCaracteristica(daoTipoCaracteristica.findById(tipo_caracteristica_id).orElseThrow(
                    () -> new NotFoundException("No existe tipo caracteristica con id "+tipo_caracteristica_id)
            ));
        }

        nuevaPublicacion = daoPublicacion.save(nuevaPublicacion);

        organizacion.getPublicaciones().add(nuevaPublicacion);

        daoOrganizacion.save(organizacion);

        return nuevaPublicacion;
    }

    public List<Categoria> listadoCategorias() {return daoCategoria.findAll();}

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
