package com.patitas.servicios;

import com.patitas.daos.DaoUsuario;
import com.patitas.seguridad.Usuario;
import com.patitas.seguridad.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ServicioUsuario implements UserDetailsService {

    @Autowired
    private DaoUsuario daoUsuario;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String login(String username, String password) throws UsernameNotFoundException {
        Usuario usuario = loadUserByUsername(username);

        // Check if password is correct
        if(!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new UsernameNotFoundException("Usuario o contraseña incorrecta");
        }

        return TokenProvider.generateToken(usuario);
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
