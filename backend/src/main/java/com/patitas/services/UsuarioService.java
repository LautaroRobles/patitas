package com.patitas.services;

import com.patitas.model.Usuario;
import com.patitas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Una clase servicio es una que expone funcionalidad de nuestra base de datos, como por ejemplo buscar todos, borrar,
// agregar, etc.
@Service
public class UsuarioService {
    // Autowired significa que Spring le va a meter el repositorio correcto
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Encontrar todos los usuarios de la base de datos
    public List<Usuario> findAllUsuarios()
    {
        return usuarioRepository.findAll();
    }
    // Encontrar un usuario por id, si no lo encuentra tira un ResourceNotFoundException (la excepcion personalizada)
    public Usuario findUsuarioById(Long id) throws ResourceNotFoundException
    {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
    }
    // Crea un usuario en la base de datos
    public Usuario createUsuario(Usuario usuario)
    {
        // Hashear la contraseña correcta, etc
        return usuarioRepository.save(usuario);
    }
    // Borrar un usuario en la base de datos por id, si no existe el usuario tira un ResourceNotFoundException
    public void deleteUsuario(Long id) throws ResourceNotFoundException
    {
        Boolean usuarioExiste = usuarioRepository.existsById(id);

        if(usuarioExiste)
        {
            usuarioRepository.deleteById(id);
        }
        else
        {
            throw new ResourceNotFoundException();
        }
    }
    // Actualizar los datos de un usuario en la base de datos, si no existe el usuario tira un ResourceNotFoundException
    public Usuario updateUsuario(Usuario usuario, Long id) throws ResourceNotFoundException
    {
        Boolean usuarioExiste = usuarioRepository.existsById(id);
        usuario.setId(id); // Setear la id a la que se pasa como parametro para que el usuario no cambie su id

        if(usuarioExiste)
        {
            return usuarioRepository.save(usuario);
        }
        else
        {
            throw new ResourceNotFoundException();
        }
    }
}
