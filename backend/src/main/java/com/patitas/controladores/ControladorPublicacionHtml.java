package com.patitas.controladores;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.patitas.modelo.Categoria;
import com.patitas.modelo.EstadoPublicacion;
import com.patitas.modelo.Publicacion;
import com.patitas.seguridad.Rol;
import com.patitas.seguridad.Usuario;
import com.patitas.seguridad.jwt.TokenProvider;
import com.patitas.servicios.ServicioPublicacion;
import com.patitas.servicios.ServicioUsuario;
import io.jsonwebtoken.ExpiredJwtException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ControladorPublicacionHtml {

    private Handlebars handlebars = new Handlebars();

    @Autowired
    private ServicioPublicacion servicioPublicacion;
    @Autowired
    private ServicioUsuario servicioUsuario;

    @GetMapping(value = "/publicacion", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> vistaPublicaciones(@RequestParam String token, @RequestParam String clientePesado, @RequestParam String filtro) throws IOException {
        Usuario usuario = null;

        try {
            String username = TokenProvider.getUserName(token);
            usuario = servicioUsuario.getUsuario(username);
        }
        catch (NotFoundException | IllegalArgumentException | ExpiredJwtException ignored) {}

        Template template = handlebars.compile("/templates/publicaciones");

        HashMap<String, Object> model = new HashMap<>();
        // Traer publicaciones de la base de datos
        List<Publicacion> publicaciones = servicioPublicacion.listadoPublicaciones();
        List<Categoria> categorias = servicioPublicacion.listadoCategorias();

        if(usuario == null || usuario.getRol() != Rol.VOLUNTARIO) {
            publicaciones = publicaciones
                    .stream()
                    .filter(publicacion -> publicacion.getEstado() == EstadoPublicacion.Aprobada)
                    .collect(Collectors.toList());
        }

        model.put("publicaciones", publicaciones);
        model.put("categorias", categorias);
        model.put("usuario", (usuario != null && usuario.getRol() == Rol.VOLUNTARIO) ? usuario : null);
        model.put("clientePesado", clientePesado);
        model.put("filtro", filtro);

        String html = template.apply(model);

        return new ResponseEntity<>(html, HttpStatus.OK);
    }

    @GetMapping(value = "/publicacion/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> vistaPublicacion(@PathVariable Long id, @RequestParam String token, @RequestParam String clientePesado) throws IOException, NotFoundException {
        Usuario usuario = null;

        try {
            String username = TokenProvider.getUserName(token);
            usuario = servicioUsuario.getUsuario(username);
        }
        catch (NotFoundException | IllegalArgumentException | ExpiredJwtException ignored) {}

        Template template = handlebars.compile("/templates/publicacion");

        HashMap<String, Object> model = new HashMap<>();
        // Traer publicaciones de la base de datos
        Publicacion publicacion = null;

        try {
            publicacion = servicioPublicacion.findPublicacion(id);
        }
        catch (NotFoundException ignored) {}

        if(publicacion != null && publicacion.getEstado() != EstadoPublicacion.Aprobada && (usuario == null || usuario.getRol() != Rol.VOLUNTARIO)){
            publicacion = null;
        }

        model.put("publicacion", publicacion);
        model.put("clientePesado", clientePesado);

        String html = template.apply(model);

        return new ResponseEntity<>(html, HttpStatus.OK);
    }
}
