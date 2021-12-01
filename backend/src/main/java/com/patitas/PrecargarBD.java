package com.patitas;

import com.github.javafaker.Faker;
import com.patitas.daos.*;
import com.patitas.modelo.*;
import com.patitas.modelo.enviadorNotificaciones.TipoNotificacion;
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
import java.util.Locale;

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
    private DaoCategoria daoCategoria;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ServicioOrganizacion servicioOrganizacion;

    private final Faker faker = new Faker(new Locale("es"));

    // Random pregunta
    private Pregunta RandPregunta() {
        Pregunta pregunta = new Pregunta();
        pregunta.setTexto(faker.options().option("¿Esta castrada?", "¿Tiene algun tipo de enfermedad?", "¿Es chica o grande?"));
        pregunta.setObligatoria(faker.bool().bool());
        pregunta.setActivo(true);
        return pregunta;
    }

    private TipoCaracteristica RandTipoCaract() {
        TipoCaracteristica tipoCaracteristica = new TipoCaracteristica();
        tipoCaracteristica.setNombre(faker.options().option("Color", "Peso", "Raza"));
        tipoCaracteristica.setDisponible(true);
        return tipoCaracteristica;
    }

    private Contacto RandContacto() {
        Contacto contacto = new Contacto();
        contacto.setNombre(faker.name().firstName());
        contacto.setApellido(faker.name().lastName());
        contacto.setTelefono(faker.phoneNumber().cellPhone());
        contacto.setEmail(faker.internet().emailAddress());
        contacto.setTipoNotificaciones(Arrays.asList(faker.options().option(TipoNotificacion.Email, TipoNotificacion.SMS, TipoNotificacion.Whatsapp)));

        return contacto;
    }

    private Persona RandPersona() {
        Persona persona = new Persona();
        persona.setNombre(faker.name().firstName());
        persona.setApellido(faker.name().lastName());
        persona.setContactos(Arrays.asList(RandContacto(), RandContacto()));
        persona.setDireccion(faker.address().fullAddress());
        persona.setFechaDeNacimiento(faker.date().birthday(23, 50));
        persona.setTipoDeDocumento("DNI");
        persona.setNroDocumento(faker.idNumber().valid());

        return persona;
    }

    private Usuario RandUsuario() {
        Usuario usuario = new Usuario();
        usuario.setUsername(faker.name().username());
        usuario.setPassword(passwordEncoder.encode(usuario.getUsername()));
        usuario.setRol(Rol.ESTANDAR);
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setPersona(RandPersona());

        return usuario;
    }

    private List<Categoria> CrearCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        Categoria categoria1 = new Categoria();
        categoria1.setNombre("Quiero adoptar");
        categorias.add(daoCategoria.save(categoria1));

        Categoria categoria2 = new Categoria();
        categoria2.setNombre("Mascota en adopcion");
        categorias.add(daoCategoria.save(categoria2));

        Categoria categoria3 = new Categoria();
        categoria3.setNombre("Mascota perdida");
        categorias.add(daoCategoria.save(categoria3));

        return categorias;
    }

    private Publicacion RandPublicacion(Usuario autor, Organizacion organizacion, List<Categoria> categorias) {
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(faker.book().title());
        publicacion.setCuerpo(faker.lorem().paragraph());
        publicacion.setEstado(faker.options().option(EstadoPublicacion.Aprobada, EstadoPublicacion.SinVerificar));
        publicacion.setAutor(autor.getPersona());
        publicacion.setCategoria(faker.options().nextElement(categorias));
        publicacion.setOrganizacion(organizacion);

        return publicacion;
    }

    private void CrearOrganizacionRandom(List<Categoria> categorias) {
        Organizacion organizacion = new Organizacion();
        organizacion.setNombre(faker.company().name());
        organizacion.setEmail(faker.internet().emailAddress());

        PosicionGeografica ubicacion = new PosicionGeografica();
        ubicacion.setLatitud(Float.parseFloat(faker.address().latitude().replace(",", ".")));
        ubicacion.setLongitud(Float.parseFloat(faker.address().longitude().replace(",", ".")));

        organizacion.setCaracteristicasDisponibles(Arrays.asList(RandTipoCaract(), RandTipoCaract()));
        organizacion.setPreguntasAdopcion(Arrays.asList(RandPregunta(), RandPregunta(), RandPregunta()));

        List<Usuario> usuarios = Arrays.asList(RandUsuario(), RandUsuario(), RandUsuario());
        List<Publicacion> publicaciones = Arrays.asList(
                RandPublicacion(usuarios.get(0), organizacion, categorias),
                RandPublicacion(usuarios.get(1), organizacion, categorias),
                RandPublicacion(usuarios.get(2), organizacion, categorias)
        );

        organizacion.setPublicaciones(publicaciones);

        daoOrganizacion.save(organizacion);
    }

    @Override
    public void run(String...args) throws Exception {
        if(true){
            return;
        }

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
        usuarioRepositorio.save(voluntario);

        List<Categoria> categorias = CrearCategorias();

        CrearOrganizacionRandom(categorias);
        CrearOrganizacionRandom(categorias);
    }
}
