package com.patitas.servicios;

import com.patitas.daos.DaoPersona;
import com.patitas.daos.DaoUsuario;
import com.patitas.dto.TokenDTO;
import com.patitas.excepciones.InvalidPasswordException;
import com.patitas.excepciones.UsernameAlreadyTakenException;
import com.patitas.modelo.Persona;
import com.patitas.seguridad.Rol;
import com.patitas.seguridad.Usuario;
import com.patitas.seguridad.jwt.TokenProvider;
import com.patitas.seguridad.validadorContrasenia.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.patitas.utils.Constants.LONGITUD_MINIMA_CONTRASENIAS;

@Service
public class ServicioUsuario {

    @Autowired
    private DaoUsuario daoUsuario;
    @Autowired
    private DaoPersona daoPersona;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private ValidadorContrasenia validadorContrasenia;

    public ServicioUsuario() {
        validadorContrasenia = new ValidadorContrasenia();

        ValidacionLongitud validacion1 = new ValidacionLongitud();
        validacion1.setLongitudMinima(LONGITUD_MINIMA_CONTRASENIAS);
        ValidacionMismoNombre validacion2 = new ValidacionMismoNombre();
        ValidacionPeoresContrasenias validacion3 = new ValidacionPeoresContrasenias();

        validadorContrasenia.setValidaciones(Arrays.asList(validacion1, validacion2, validacion3));
    }

    // TODO hacer refresh

    public TokenDTO login(String username, String password) throws UsernameNotFoundException {
        Usuario usuario = daoUsuario.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Usuario o contraseña incorrecta")
        );

        // Check if password is correct
        if(!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new UsernameNotFoundException("Usuario o contraseña incorrecta");
        }

        TokenDTO token = new TokenDTO();
        token.setToken(TokenProvider.generateToken(usuario));

        return token;
    }

    public Usuario registrar(String username, String password, String email, Rol rol) throws InvalidPasswordException, UsernameAlreadyTakenException {
        validadorContrasenia.validarContrasenia(password, username, email);

        Usuario usuarioExistente = daoUsuario.findByUsername(username).orElse(null);
        if(usuarioExistente != null) {
            throw new UsernameAlreadyTakenException("El usuario ya existe");
        }

        Usuario nuevoUsuario = new Usuario();

        nuevoUsuario.setUsername(username);
        nuevoUsuario.setPassword(passwordEncoder.encode(password));
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setRol(rol);

        return daoUsuario.save(nuevoUsuario);
    }

    public Persona asociarPersonaAUsuario(Long idPersona, String username) throws NotFoundException {
        Usuario usuario = daoUsuario.findByUsername(username).orElseThrow(
                () -> new NotFoundException("No se encuentra usuario con username "+ username)
        );
        Persona persona = daoPersona.findById(idPersona).orElseThrow(
                () -> new NotFoundException("No se encuentra persona con id "+idPersona)
        );

        usuario.setPersona(persona);

        daoUsuario.save(usuario);

        return persona;
    }

    public Usuario getUsuario(String username) throws NotFoundException {
        return daoUsuario.findByUsername(username).orElseThrow(
                () -> new NotFoundException("No se encuentra usuario con username "+ username)
        );
    }
}
