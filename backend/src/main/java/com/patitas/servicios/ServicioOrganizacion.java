package com.patitas.servicios;

import com.patitas.daos.DaoOrganizacion;
import com.patitas.daos.DaoPregunta;
import com.patitas.daos.DaoTipoCaracteristica;
import com.patitas.dto.CrearOrganizacionDTO;
import com.patitas.modelo.*;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioOrganizacion {
    @Autowired
    private DaoOrganizacion daoOrganizacion;
    @Autowired
    private DaoTipoCaracteristica daoTipoCaracteristica;
    @Autowired
    private DaoPregunta daoPregunta;

    private ModelMapper modelMapper = new ModelMapper();

    public List<Organizacion> listadoOrganizaciones() {
        return daoOrganizacion.findAll();
    }

    public Organizacion crearOrganizacion(CrearOrganizacionDTO crearOrganizacionDTO) {
        Organizacion nuevaOrganizacion = modelMapper.map(crearOrganizacionDTO, Organizacion.class);
        return daoOrganizacion.save(nuevaOrganizacion);
    }

    public Organizacion getOrganizacionEstandar(Long idOrganizacion) throws NotFoundException {
        Organizacion organizacion = daoOrganizacion.findById(idOrganizacion).orElseThrow(
                () -> new NotFoundException("No existe organizacion con id "+idOrganizacion)
        );

        List<Pregunta> preguntas = organizacion.getPreguntasAdopcion();
        organizacion.setPreguntasAdopcion(preguntas.stream().filter(Pregunta::getActivo).collect(Collectors.toList()));

        List<TipoCaracteristica> caracteristicas = organizacion.getCaracteristicasDisponibles();
        organizacion.setCaracteristicasDisponibles(caracteristicas.stream().filter(TipoCaracteristica::getDisponible).collect(Collectors.toList()));

        return organizacion;
    }

    public Organizacion getOrganizacion(Long idOrganizacion) throws NotFoundException {
        return daoOrganizacion.findById(idOrganizacion).orElseThrow(
                () -> new NotFoundException("No existe organizacion con id "+idOrganizacion)
        );
    }

    public TipoCaracteristica crearTipoCaracteristica(Long idOrganizacion, String nombre, boolean disponibilidad) throws NotFoundException {
        Organizacion organizacion = getOrganizacion(idOrganizacion);

        TipoCaracteristica nuevoTipoCaracteristica = new TipoCaracteristica();

        nuevoTipoCaracteristica.setNombre(nombre);
        nuevoTipoCaracteristica.setDisponible(disponibilidad);
        nuevoTipoCaracteristica = daoTipoCaracteristica.save(nuevoTipoCaracteristica);

        organizacion.getCaracteristicasDisponibles().add(nuevoTipoCaracteristica);

        daoOrganizacion.save(organizacion);

        return nuevoTipoCaracteristica;
    }

    public TipoCaracteristica guardarTipoCaracteristica(Long idTipoCaracteristica, String nombre, boolean disponibilidad) throws NotFoundException {
        TipoCaracteristica tipoCaracteristica = daoTipoCaracteristica.findById(idTipoCaracteristica).orElseThrow(
                () -> new NotFoundException("No existe tipo caracteristica con id "+idTipoCaracteristica)
        );
        tipoCaracteristica.setNombre(nombre);
        tipoCaracteristica.setDisponible(disponibilidad);
        return daoTipoCaracteristica.save(tipoCaracteristica);
    }

    public Pregunta crearPreguntaDisponible(Long idOrganizacion, String texto, boolean activo, boolean obligatoria) throws NotFoundException {
        Organizacion organizacion = getOrganizacion(idOrganizacion);

        Pregunta nuevaPregunta = new Pregunta();

        nuevaPregunta.setTexto(texto);
        nuevaPregunta.setActivo(activo);
        nuevaPregunta.setObligatoria(obligatoria);
        nuevaPregunta = daoPregunta.save(nuevaPregunta);

        organizacion.getPreguntasAdopcion().add(nuevaPregunta);

        daoOrganizacion.save(organizacion);

        return nuevaPregunta;
    }

    public Pregunta guardarPregunta(Long idPregunta, String texto, boolean activo, boolean obligatoria) throws NotFoundException {
        Pregunta pregunta = daoPregunta.findById(idPregunta).orElseThrow(
                () -> new NotFoundException("No existe pregunta con id "+idPregunta)
        );
        pregunta.setTexto(texto);
        pregunta.setActivo(activo);
        pregunta.setObligatoria(obligatoria);
        return daoPregunta.save(pregunta);
    }
}
