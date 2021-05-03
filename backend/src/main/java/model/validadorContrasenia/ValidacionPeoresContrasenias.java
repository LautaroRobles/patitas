package model.validadorContrasenia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ValidacionPeoresContrasenias extends ValidacionContrasenia{
    public Boolean validarContrasenia(String contrasenia) {
        Boolean contraseniaValida = true;

        try
        {
            File archivoPeoresContrasenias = new File("src/main/resources/contrasenias/peores-contrasenias.txt");
            Scanner peoresContrasenias = new Scanner(archivoPeoresContrasenias);
            while (peoresContrasenias.hasNextLine() && contraseniaValida)
            {
                String contraseniaMala = peoresContrasenias.nextLine();
                if(contrasenia.equals(contraseniaMala))
                {
                    contraseniaValida = false;
                }
            }
            peoresContrasenias.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("No se encuentra el archivo de las peores contrasenias");
        }

        return contraseniaValida;
    }
}
