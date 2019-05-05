package it.polimi.se2019.view.client.cli;

/**
 * @author Simone Orlando
 */
public enum CliColor {
    TEXTBLACK("\u001B[30m"),
    TEXTRED("\u001B[31m"),
    TEXTGREEN("\u001B[32m"),
    TEXTYELLOW("\u001B[33m"),
    TEXTBLUE("\u001B[34m"),
    TEXTPURPLE("\u001B[35m"),
    TEXTCYAN("\u001B[36m"),
    TEXTWHITE("\u001B[37m"),

    BACKBLACK("\u001B[40m"),
    BACKRED("\u001B[41m"),
    BACKGREEN("\u001B[42m"),
    BACKYELLOW("\u001B[43m"),
    BACKBLUE("\u001B[44m"),
    BACKPURPLE("\u001B[45m"),
    BACKCYAN("\u001B[46m"),
    BACKWHITE("\u001B[47m");


    private String code;

    private CliColor(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }
}