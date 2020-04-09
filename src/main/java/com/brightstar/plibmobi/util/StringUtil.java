package com.brightstar.plibmobi.util;

import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public final class StringUtil extends StringUtils {

    public static final String VIRGULA_ESPACO = ", ";
    public static final String VIRGULA = ",";
    public static final String DOIS_PONTOS_ESPACO = ": ";

    public static final String STRING_ESPACO = " ";
    public static final String QUEBRA_DE_LINHA = "\n";

    private static final String DEFAULT_CHARSET = "UTF8";

    /**
     *
     */
    private StringUtil(){
    }

    /**
     *
     * @param text
     * @return
     */
    public static String textNotNull(final String text) {
        return isNotBlank(text) ? text : EMPTY;
    }

    /**
     *
     * @param text
     * @return
     */
    public static String textNotNullZero(final String text) {
        return ("0".equals(textNotNull(text))) ? EMPTY : textNotNull(text);
    }

    /**
     *
     * @param number
     * @return
     */
    public static String integerNotNullAndZero(final Integer number) {
        return ("0".equals(integerNotNull(number))) ? EMPTY : integerNotNull(number);
    }

    /**
     *
     * @param number
     * @return
     */
    public static String integerNotNull(final Integer number) {
        if (number == null) {
            return EMPTY;
        }
        return String.valueOf(number);
    }

    /**
     *
     * @param value
     * @return
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     *
     * @param fileName
     * @return
     */
    public static String getExtensionFileName(String fileName) {
        for(int i = fileName.length(); i > 0 ; i--) {
            if(String.valueOf(fileName.charAt(i-1)).equals(".")) {
                return fileName.substring(i, fileName.length());
            }
        }
        return null;
    }

    /**
     *
     * @param number
     * @return
     */
    public static String longNotNull(final Long number) {
        if (number == null) {
            return EMPTY;
        }
        return String.valueOf(number);
    }

    /**
     *
     * @param number
     * @return
     */
    public static String doubleNotNull(final Double number) {
        if (number == null) {
            return EMPTY;
        }
        return String.valueOf(number);
    }

    /**
     *
     * @param date
     * @param sdf
     * @return
     */
    public static String dateNotNull(final Date date, final SimpleDateFormat sdf) {
        return (date != null) ? sdf.format(date) : EMPTY;
    }

    /**
     * Remove, caso houver, acentuacao dos caracteres da string passada como
     * parametro.
     */
    public static String removeAccentuation(final String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    /**
     * Remove caracteres que nao sejam numeros inteiros de uma string.
     * "(19)4432-4313" : "1944324313"
     */
    public static String removeNoNumbersChars(final String text) {

        StringBuilder stringBuilder = new StringBuilder(EMPTY);
        int textLength = text.length();

        for (int i = 0; i < textLength; i++) {
            if (Character.isDigit(text.charAt(i))) {
                stringBuilder.append(String.valueOf(text.charAt(i)));
            }
        }

        return stringBuilder.toString();
    }





    /**
     * Remove caracteres que nao sejam letras de uma string.
     * "Ex3mp10" : "Exmp"
     */
    public static String removeNoLettersChars(final String text) {

        StringBuilder stringBuilder = new StringBuilder(EMPTY);
        int textLength = text.length();

        for (int i = 0; i < textLength; i++) {
            if (Character.isLetter(text.charAt(i))) {
                stringBuilder.append(String.valueOf(text.charAt(i)));
            }
        }

        return stringBuilder.toString();
    }

    /**
     *
     * @param number
     * @return
     */
    public static String getOnlyNumber(String number) {
        if (isNotBlank(number)) {
            return number.replace("-", "").replace(".", "").replace("/", "").replace("\\", "").replace("(", "")
                    .replace(")", "").replace(" ", "").replace("null", "");
        }
        return number;
    }


    /**
     * Retorna true, false ou null de acordo com a similaridade da string passada como parametro.
     */
    public static Boolean stringToBoolean(final String text) {

        if(text == null){
            return null;
        }

        String textAux = trimToEmpty(text);

        if (textAux.equalsIgnoreCase("true")
                || textAux.equalsIgnoreCase("y")
                || textAux.equalsIgnoreCase("yes")
                || textAux.equalsIgnoreCase("si")
                || textAux.equalsIgnoreCase("s�")
                || textAux.equalsIgnoreCase("sim")
                || textAux.equalsIgnoreCase("t")
                || textAux.equalsIgnoreCase("1")
                || textAux.equalsIgnoreCase("s") ) {
            return true;
        }

        if (textAux.equalsIgnoreCase("false")
                || textAux.equalsIgnoreCase("n")
                || textAux.equalsIgnoreCase("no")
                || textAux.equalsIgnoreCase("nao")
                || textAux.equalsIgnoreCase("n�o")
                || textAux.equalsIgnoreCase("not")
                || textAux.equalsIgnoreCase("f")
                || textAux.equalsIgnoreCase("0")) {
            return false;
        }

        return null;
    }

    /**
     *
     * @param text
     * @return
     */
    public static Boolean stringToBooleanNotNull(final String text) {
        return stringToBoolean(text) != null ? stringToBoolean(text) : false;
    }

    /**
     * (true) -> 1 (false) -> 0
     *
     * @param bol
     * @return
     */
    public static String convertBooleanToN(final boolean bol) {
        return bol ? "1" : "0";
    }

    /**
     *
     * @param bol
     * @return
     */
    public static String convertBooleanToN(final Boolean bol) {
        if(bol == null){
            return "0";
        }

        return convertBooleanToN(bol.booleanValue());
    }

    /**
     *
     * @param value
     * @return
     */
    public static String booleanToString(Boolean value) {
        if (value != null) {
            return (Boolean.TRUE.equals(value) ? "true" : "false");
        } else {
            return EMPTY;
        }
    }

    /**
     *
     * @param valor
     * @param patern
     * @return
     */
    public static String convertByte(long valor, String patern){
        String[] powerOfByte= {"B","KB","MB","GB","TB"};
        int potencia=0;
        int proxima=potencia;
        boolean testaPotenciaActual;
        boolean testaPotenciaSeguinte;
        do {
            proxima=potencia+1;
            testaPotenciaActual= (Math.pow(2,potencia*10)<=valor);
            testaPotenciaSeguinte= (valor<Math.pow(2,proxima*10));
            potencia++;

        } while(!(testaPotenciaActual && testaPotenciaSeguinte));

        potencia--;
        DecimalFormat myFormatter = new DecimalFormat(patern);

        return myFormatter.format(valor/Math.pow(2,potencia*10))+" "+powerOfByte[potencia];
    }

    /**
     *
     * @param array
     * @param separator
     * @return
     */
    public static <T> String convertArrayToString(final Object[] array, final String separator) {

        int somatorio = 0;
        StringBuilder stringBuilder = new StringBuilder(StringUtils.EMPTY);

        if (array != null) {
            int listSize = array.length;
            for (Object o : array) {
                somatorio++;

                if (o.toString() != null) {
                    stringBuilder.append(o.toString());
                }

                if (listSize != somatorio) {
                    stringBuilder.append(separator);
                }
            }
        }

        return stringBuilder.toString();
    }

    /**
     *
     * @param text
     * @return
     */
    public static boolean hasIntegerContent(final String text) {

        if(!isNotBlank(text)){
            return false;
        }

        Pattern pattern = Pattern.compile("^(-)?\\d*\\d$");

        Matcher find = pattern.matcher(trim(text));

        return find.matches();

    }

    /**
     *
     * @param cnpj
     * @return
     */
    public static String formatCnpj(String cnpj) {

        if (cnpj != null && cnpj.length() >= 13) {
            cnpj = cnpj.substring(0, cnpj.length() - 2) + "-" + cnpj.substring(cnpj.length() - 2, cnpj.length());
            cnpj = cnpj.substring(0, cnpj.length() - 7) + "/" + cnpj.substring(cnpj.length() - 7, cnpj.length());
            cnpj = cnpj.substring(0, cnpj.length() - 11) + "." + cnpj.substring(cnpj.length() - 11, cnpj.length());
            cnpj = cnpj.substring(0, cnpj.length() - 15) + "." + cnpj.substring(cnpj.length() - 15, cnpj.length());
        }

        return cnpj;
    }

    /**
     *
     * @param cpf
     * @return
     */
    public static String formatCpf(String cpf) {

        if (cpf != null && cpf.length() >= 9) {
            cpf = cpf.substring(0, cpf.length() - 2) + "-" + cpf.substring(cpf.length() - 2, cpf.length());
            cpf = cpf.substring(0, cpf.length() - 6) + "." + cpf.substring(cpf.length() - 6, cpf.length());
            cpf = cpf.substring(0, cpf.length() - 10) + "." + cpf.substring(cpf.length() - 10, cpf.length());
        }

        return cpf;
    }

    /**
     *
     * @param str
     * @param charset
     * @return
     */
    private static byte[] getBytes(final String str, final Charset charset) {
        return trimToEmpty(str).getBytes(charset);
    }

    /**
     *
     * @param str
     * @return
     */
    public static byte[] getBytes(final String str) {
        return getBytes(str, Charset.forName(DEFAULT_CHARSET));
    }

    /**
     *
     * @param src
     * @return
     */
    public static String filterInvalidChars(String src) {
        if (src == null || (src.trim().equalsIgnoreCase("null"))){
            return EMPTY;
        }

        int srcLength = src.length();
        StringBuilder srcReturn = new StringBuilder(src);

        for (int i = 0; i < srcLength; i++) {
            if (!Character.isLetterOrDigit(src.charAt(i))
                    && !"\"!@#$%^&*()_+-=[]{}\\|;':,./<>?".contains(EMPTY + src.charAt(i))) {
                srcReturn.replace(i, i+1, " ");
            }
        }
        return srcReturn.toString();
    }

    /**
     *
     * @param lst
     * @return
     */
    public static String convertInLine(ArrayList<String> lst) {
        String r = "";

        for (int i = 0; i < lst.size(); i++) {
            if (i != 0) {
                r += ", ";
            }
            String v = lst.get(i);
            r += String.valueOf(v);
        }

        return r;
    }

    /**
     *
     * @param lst
     * @return
     */
    public static String convertInLine(ArrayList<String> lst, String limiter) {
        String r = "";

        for (int i = 0; i < lst.size(); i++) {
            if (i != 0) {
                r += limiter;
            }
            String v = lst.get(i);
            r += String.valueOf(v);
        }

        return r;
    }


    /**
     * Utility method to replaces all the string references from StringBuilder.
     * @param sb          the StringBuilder object.
     * @param toReplace   the String that should be replaced.
     * @param replacement the String that has to be replaced by.
     * Taken from http://stackoverflow.com/questions/3472663/replace-all-occurences-of-a-string-using-stringbuilder
     */
    public static void replaceString(StringBuilder sb,
            String toReplace,
            String replacement) {
        int index = -1;
        while ((index = sb.lastIndexOf(toReplace)) != -1) {
            sb.replace(index, index + toReplace.length(), replacement);
        }
    }


    /**
     * Replaces all latin special characters
     * @param sb  The text to be modified
     */
    public static void replaceCharactersForZPL(StringBuilder sb) {
        String[][] equivalences = {
                {"�","2_bf"},
                {"�","3_80"},
                {"�","3_81"},
                {"�","3_83"},
                {"�","3_87"},
                {"�","3_89"},
                {"�","3_8a"},
                {"�","3_8c"},
                {"�","3_8d"},
                {"�","3_91"},
                {"�","3_92"},
                {"�","3_93"},
                {"�","3_94"},
                {"�","3_95"},
                {"�","3_99"},
                {"�","3_9a"},
                {"�","3_9c"},
                {"�","3_a0"},
                {"�","3_a1"},
                {"�","3_a2"},
                {"�","3_a3"},
                {"�","3_a7"},
                {"�","3_a8"},
                {"�","3_a9"},
                {"�","3_aa"},
                {"�","3_ac"},
                {"�","3_ad"},
                {"�","3_b1"},
                {"�","3_b2"},
                {"�","3_b3"},
                {"�","3_b4"},
                {"�","3_b5"},
                {"�","3_b9"},
                {"�","3_ba"},
                {"�","3_bb"},
                {"�","3_bc"}
        };

        for(String[] replacement : equivalences) {
            replaceString(sb, replacement[0], "_c"+replacement[1]);
        }
    }

}