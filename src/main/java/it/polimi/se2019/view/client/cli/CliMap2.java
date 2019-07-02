package it.polimi.se2019.view.client.cli;

/**
 * Class that contains all the information on map 2 and that allows you to print it on a terminal
 * @author Simone Orlando
 */
class CliMap2 {
    CliPlatform plat1;
    CliPlatform plat2;
    CliPlatform plat3;
    CliPlatform plat4;
    CliPlatform plat5;
    CliPlatform plat6;
    CliPlatform plat7;
    CliPlatform plat8;
    CliPlatform plat9;
    CliPlatform plat10;
    CliPlatform plat11;

    /**
     * Initialize all the platforms so as to build the map 2
     */
    CliMap2() {
        plat1 = new CliPlatform(CliColor.TEXTCYAN, false, true, false, true);
        plat2 = new CliPlatform(CliColor.TEXTCYAN, false, false, true, true);
        plat3 = new CliPlatform(CliColor.TEXTCYAN, false, true, true, true);
        plat4 = new CliPlatform(CliColor.TEXTGREEN, false, true, true, false);
        plat5 = new CliPlatform(CliColor.TEXTRED, true, false, false, true);
        plat6 = new CliPlatform(CliColor.TEXTRED, false, true, true, false);
        plat7 = new CliPlatform(CliColor.TEXTYELLOW,true, true, false, true);
        plat8 = new CliPlatform(CliColor.TEXTYELLOW, true, true, true, false);
        plat9 = new CliPlatform(CliColor.TEXTWHITE, true, false, false, true);
        plat10 = new CliPlatform(CliColor.TEXTYELLOW, true, false, true, true);
        plat11 = new CliPlatform(CliColor.TEXTYELLOW, true, false, true, false);
    }

    /**
     * Print the map 2 on the terminal
     */
    void stamp() {
        plat1.print();
        plat2.print();
        plat3.print();
        plat4.print();
        CliSetUp.cursorDown(7);
        CliSetUp.cursorLeft(68);
        plat5.print();
        plat6.print();
        plat7.print();
        plat8.print();
        CliSetUp.cursorDown(7);
        CliSetUp.cursorLeft(51);
        plat9.print();
        plat10.print();
        plat11.print();
    }
}
