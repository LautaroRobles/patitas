package com.patitas;

import com.patitas.daos.*;
import com.patitas.dto.CrearOrganizacionDTO;
import com.patitas.modelo.*;
import com.patitas.seguridad.Rol;
import com.patitas.seguridad.Usuario;
import com.patitas.servicios.ServicioOrganizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PrecargarBD implements CommandLineRunner {
    @Autowired
    private DaoUsuario usuarioRepositorio;
    @Autowired
    private DaoPublicacion daoPublicacion;
    @Autowired
    private DaoCaracteristica daoCaracteristica;
    @Autowired
    private DaoTipoCaracteristica daoTipoCaracteristica;
    @Autowired
    private DaoOrganizacion daoOrganizacion;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ServicioOrganizacion servicioOrganizacion;

    @Override
    public void run(String...args) throws Exception {
        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setRol(Rol.ADMINISTRADOR);
        usuarioRepositorio.save(admin);

        Usuario estandar = new Usuario();
        estandar.setUsername("estandar");
        estandar.setPassword(passwordEncoder.encode("123"));
        estandar.setRol(Rol.ESTANDAR);
        usuarioRepositorio.save(estandar);

        Usuario voluntario = new Usuario();
        voluntario.setUsername("voluntario");
        voluntario.setPassword(passwordEncoder.encode("123"));
        voluntario.setRol(Rol.VOLUNTARIO);
        usuarioRepositorio.save(estandar);

        Organizacion organizacion = new Organizacion();
        organizacion.setNombre("Patitas 1");
        organizacion.setEmail("email@example.com");

        PosicionGeografica ubicacion = new PosicionGeografica();
        ubicacion.setLatitud(0);
        ubicacion.setLongitud(0);

        TipoCaracteristica tipoCaracteristica = new TipoCaracteristica();
        tipoCaracteristica.setNombre("Color");
        tipoCaracteristica.setDisponible(true);
        Pregunta pregunta = new Pregunta();

        organizacion.setCaracteristicasDisponibles(Arrays.asList(tipoCaracteristica));
        organizacion.setPreguntasAdopcion(Arrays.asList(pregunta));

        daoOrganizacion.save(organizacion);
    }
}
