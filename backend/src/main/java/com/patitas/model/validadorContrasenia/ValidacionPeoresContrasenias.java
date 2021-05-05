package com.patitas.model.validadorContrasenia;

import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ValidacionPeoresContrasenias extends ValidacionContrasenia{
    public Boolean validarContrasenia(String contrasenia) {
        Boolean contraseniaValida = true;

        try
        {
            // Version vieja
            // File archivoPeoresContrasenias = new File("src/main/resources/contrasenias/peores-contrasenias.txt");

            // Esta version funciona pero no cuando esta metido en un JAR
            // File archivoPeoresContrasenias = ResourceUtils.getFile("classpath:contrasenias/peores-contrasenias.txt");

            // Esta version funciona en todas las instancias
            // Recordar que para leer un archivo mejor leerlo como un Stream
            InputStream streamPeoresContrasenias = new ClassPathResource("contrasenias/peores-contrasenias.txt").getInputStream();
            Scanner peoresContrasenias = new Scanner(streamPeoresContrasenias);
            while (peoresContrasenias.hasNextLine() && contraseniaValida)
            {
                String contraseniaMala = peoresContrasenias.nextLine();
                if(contrasenia.equals(contraseniaMala))
                {
                    contraseniaValida = false;
                }
            }
            peoresContrasenias.close();
            streamPeoresContrasenias.close();
        }
        catch (IOException e)
        {
            System.out.println("Ocurrio un error al leer el archivo de peores-contrasenias");
        }

        return contraseniaValida;
    }
}
