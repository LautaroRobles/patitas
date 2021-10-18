package com.patitas.servicios;

import com.patitas.daos.DaoUsuario;
import com.patitas.excepciones.InvalidPasswordException;
import com.patitas.excepciones.UsernameAlreadyTakenException;
import com.patitas.seguridad.Rol;
import com.patitas.seguridad.Usuario;
import com.patitas.seguridad.jwt.TokenProvider;
import com.patitas.seguridad.validadorContrasenia.ValidacionLongitud;
import com.patitas.seguridad.validadorContrasenia.ValidacionMismoNombre;
import com.patitas.seguridad.validadorContrasenia.ValidacionPeoresContrasenias;
import com.patitas.seguridad.validadorContrasenia.ValidadorContrasenia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class ServicioUsuario implements UserDetailsService {

    @Autowired
    private DaoUsuario daoUsuario;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private ValidadorContrasenia validadorContrasenia;

    public ServicioUsuario() {
        validadorContrasenia = new ValidadorContrasenia();
        validadorContrasenia.setValidaciones(Arrays.asList(new ValidacionLongitud(), new ValidacionMismoNombre(), new ValidacionPeoresContrasenias()));
    }

    // TODO hacer refresh

    public String login(String username, String password) throws UsernameNotFoundException {
        Usuario usuario = loadUserByUsername(username);

        // Check if password is correct
        if(!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new UsernameNotFoundException("Usuario o contraseña incorrecta");
        }

        return TokenProvider.generateToken(usuario);
    }

    public Usuario registrar(String username, String password, String email, Rol rol) throws InvalidPasswordException, UsernameAlreadyTakenException {
        if(!validadorContrasenia.validarContrasenia(username, password, email)) {
            throw new InvalidPasswordException("Contraseña no valida");
        }

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

    @Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = daoUsuario.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Usuario o contraseña incorrecta")
        );

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ROL_" + usuario.getRol().toString()));

        usuario.setAuthorities(authorities);
        return usuario;
    }
}
