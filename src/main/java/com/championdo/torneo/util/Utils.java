package com.championdo.torneo.util;

import com.championdo.torneo.model.UtilModel;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {

    public static String date2String (Date date) {
        if (date != null) {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            return df.format(date);
        } else {
            return null;
        }
    }

    public static String generateSecurePassword() {

        // create character rule for lower case
        CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase);
        // set number of lower case characters
        LCR.setNumberOfCharacters(4);

        // create character rule for upper case
        CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase);
        // set number of upper case characters
        UCR.setNumberOfCharacters(2);

        // create character rule for digit
        CharacterRule DR = new CharacterRule(EnglishCharacterData.Digit);
        // set number of digits
        DR.setNumberOfCharacters(2);

        /* create character rule for lower case
        CharacterRule SR = new CharacterRule(EnglishCharacterData.Special);
        // set number of special characters
        SR.setNumberOfCharacters(2);
        */

        // create instance of the PasswordGenerator class
        PasswordGenerator passGen = new PasswordGenerator();

        // call generatePassword() method of PasswordGenerator class to get Passay generated password
        // return Passay generated password to the main() method
        return passGen.generatePassword(8, LCR, UCR, DR);
    }

    public static long milisegEntreDosFechas(Date fechaMayor, Date fechaMenor) {
        return (fechaMayor.getTime() - fechaMenor.getTime());
    }

    public static String ofuscar(String entrada) {
        StringBuilder sb = new StringBuilder();
        if(entrada != null) {
            int tamanio = entrada.length();
            int visible = tamanio/3;
            if (tamanio > 3) {
                sb.append(entrada, 0, visible);
                for(int j=visible;j<(tamanio-visible);j++) {
                    sb.append("*");
                }
                sb.append(entrada, tamanio-visible, tamanio);
            } else {
                sb.append("***");
            }
        }
        return sb.toString();
    }

    public static List<UtilModel> cargarListaSiNo() {
        List<UtilModel> listaSiNo = new ArrayList<>();
        listaSiNo.add(new UtilModel("Si", "true"));
        listaSiNo.add(new UtilModel("No", "false"));
        return listaSiNo;
    }
}
