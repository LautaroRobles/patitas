package com.patitas.controllers;

import com.patitas.dto.UsuarioCreateDto;
import com.patitas.dto.UsuarioDto;
import com.patitas.model.Usuario;
import com.patitas.services.ResourceNotFoundException;
import com.patitas.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@ResponseStatus(HttpStatus.OK)
public class UsuarioController {
    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto createUsuario(@RequestBody UsuarioCreateDto usuarioCreateDto)
    {
        Usuario usuarioCreado = usuarioService.createUsuario(modelMapper.map(usuarioCreateDto, Usuario.class));
        return modelMapper.map(usuarioCreado, UsuarioDto.class);
    }

    @GetMapping()
    public List<UsuarioDto> findAllUsuarios()
    {
        List<Usuario> usuarios = usuarioService.findAllUsuarios();
        List<UsuarioDto> usuarioDtos = modelMapper.map(usuarios, new TypeToken<List<UsuarioDto>>(){}.getType());
        return usuarioDtos;
    }

    @GetMapping("/{id}")
    public UsuarioDto findUsuario(@PathVariable Long id)
    {
        try
        {
            Usuario usuario = usuarioService.findUsuarioById(id);
            UsuarioDto usuarioDto = modelMapper.map(usuario, UsuarioDto.class);
            return usuarioDto;
        }
        catch (ResourceNotFoundException ex)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario", ex);
        }
    }

    @PutMapping("/{id}")
    public UsuarioDto updateUsuario(@RequestBody UsuarioDto usuarioUpdateDto, @PathVariable Long id)
    {
        try
        {
            Usuario usuarioModificado = usuarioService.updateUsuario(modelMapper.map(usuarioUpdateDto, Usuario.class), id);
            return modelMapper.map(usuarioModificado, UsuarioDto.class);
        }
        catch (ResourceNotFoundException ex)
        {
            System.out.println("Upsie, no esta");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede actualizar el usuario porque no existe", ex);
        }
    }


    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id)
    {
        try
        {
            Usuario usuario = usuarioService.findUsuarioById(id);
            usuarioService.deleteUsuario(id);
        }
        catch (ResourceNotFoundException ex)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede eliminar el usuario porque no existe", ex);
        }
    }

}
