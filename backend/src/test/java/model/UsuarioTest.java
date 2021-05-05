package model;

import com.patitas.model.Usuario;
import org.junit.Assert;
import org.junit.Test;
import com.patitas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsuarioTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void usuarioPersistence()
    {
        // TODO agregar al POM el Spring Test
        // List<Usuario> usuarios = usuarioRepository.findAll();
        // Assert.assertEquals((long)usuarios.get(0).getId(), 0);
    }
}
