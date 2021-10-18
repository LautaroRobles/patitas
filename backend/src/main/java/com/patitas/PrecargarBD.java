package com.patitas;

import com.patitas.daos.DaoCaracteristica;
import com.patitas.daos.DaoPublicacion;
import com.patitas.daos.DaoTipoCaracteristica;
import com.patitas.daos.DaoUsuario;
import com.patitas.modelo.*;
import com.patitas.seguridad.Rol;
import com.patitas.seguridad.Usuario;
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
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String...args) throws Exception {
        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setRol(Rol.ADMINISTRADOR);
        usuarioRepositorio.save(admin);
    }
}
