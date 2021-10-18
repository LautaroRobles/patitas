package com.patitas;

import com.patitas.daos.DaoUsuario;
import com.patitas.seguridad.Rol;
import com.patitas.seguridad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PrecargarBD implements CommandLineRunner {
    @Autowired
    private DaoUsuario usuarioRepositorio;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String...args) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("pepito");
        usuario.setPassword(passwordEncoder.encode("asd123"));
        usuario.setRol(Rol.ESTANDAR);
        usuarioRepositorio.save(usuario);
    }
}
