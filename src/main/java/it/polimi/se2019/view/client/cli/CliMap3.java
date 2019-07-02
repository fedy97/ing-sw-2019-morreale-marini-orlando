package it.polimi.se2019.view.client.cli;

/**
 * Class that contains all the information on map 3 and that allows you to print it on a terminal
 * @author Simone Orlando
 */
class CliMap3 {
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

    /**
     * Initialize all the platforms so as to build the map 3
     */
    CliMap3() {
        plat1 = new CliPlatform(CliColor.TEXTCYAN, false, true,false, true);
        plat2 = new CliPlatform(CliColor.TEXTCYAN, false, false, true, true);
        plat3 = new CliPlatform(CliColor.TEXTCYAN, false, true, true, false);
        plat4 = new CliPlatform(CliColor.TEXTRED, true, false, false, true);
        plat5 = new CliPlatform(CliColor.TEXTRED, false, true, true, true);
        plat6 = new CliPlatform(CliColor.TEXTPURPLE, true, false, true, true);
        plat7 = new CliPlatform(CliColor.TEXTYELLOW, false, true, true, false);
        plat8 = new CliPlatform(CliColor.TEXTWHITE, true, false, false,true);
        plat9 = new CliPlatform(CliColor.TEXTWHITE, false,false,true,true);
        plat10 = new CliPlatform(CliColor.TEXTYELLOW, true, false,true,false);
    }

    /**
     * Print the map 3 on the terminal
     */
    void stamp() {
        plat1.print();
        plat2.print();
        plat3.print();
        CliSetUp.cursorDown(7);
        CliSetUp.cursorLeft(51);
        plat4.print();
        plat5.print();
        plat6.print();
        plat7.print();
        CliSetUp.cursorDown(7);
        CliSetUp.cursorLeft(51);
        plat8.print();
        plat9.print();
        plat10.print();
    }
}
