package it.polimi.se2019.view.client.cli;

/**
 * Class that contains the escape sequences to color the writing and the background on the terminal
 * @author Simone Orlando
 */
public enum CliColor {
    TEXTRED("\u001B[31m"),
    TEXTGREEN("\u001B[32m"),
    TEXTYELLOW("\u001B[33m"),
    TEXTBLUE("\u001B[34m"),
    TEXTPURPLE("\u001B[35m"),
    TEXTCYAN("\u001B[36m"),
    TEXTWHITE("\u001B[37m"),

    BACKWHITE("\u001B[47m");


    private String code;

    /**
     * Initialize the color
     * @param code of the color chosen
     */
    CliColor(String code)
    {
        this.code = code;
    }

    /**
      * @return the code of the color chosen
     */
    public String getCode()
    {
        return code;
    }
}