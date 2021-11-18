package com.patitas.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorPublicacionHtml {

    @GetMapping("/publicacion")
    public ResponseEntity<String> vistaPublicaciones(@RequestParam String token) {
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }
}
