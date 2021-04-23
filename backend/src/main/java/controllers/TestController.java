package controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// Ejemplo de un REST Controller, se mapea un GET y un POST
@RestController
public class TestController {
    @GetMapping("/hola")
    public String Hola() {
        return "como le va";
    }
    @PostMapping("/hola")
    public String HolaNo() {
        return "xd";
    }
}
