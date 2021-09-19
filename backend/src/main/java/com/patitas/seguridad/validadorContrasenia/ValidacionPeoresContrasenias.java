package com.patitas.seguridad.validadorContrasenia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ValidacionPeoresContrasenias extends ValidacionContrasenia{
    public Boolean validarContrasenia(String password, String username, String email) {
        Boolean contraseniaValida = true;

        try
        {
            File archivoPeoresContrasenias = new File("src/main/resources/contrasenias/peores-contrasenias.txt");
            InputStream streamPeoresContrasenias = new FileInputStream(archivoPeoresContrasenias);
            Scanner peoresContrasenias = new Scanner(streamPeoresContrasenias);
            while (peoresContrasenias.hasNextLine() && contraseniaValida)
            {
                String contraseniaMala = peoresContrasenias.nextLine();
                if(password.equals(contraseniaMala))
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
