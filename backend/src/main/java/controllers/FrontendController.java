package controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Para que el frontend (Vue) pueda acceder correctamente a las rutas se necesita que todas cuando no hay una ruta
// definida en algun controller que defaulteen a index.html
@Controller
public class FrontendController implements ErrorController {
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "forward:/";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
