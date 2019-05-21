package it.polimi.se2019.view.client.cli;

public class CliMap3 {
    public CliPlatform plat1;
    public CliPlatform plat2;
    public CliPlatform plat3;
    public CliPlatform plat4;
    public CliPlatform plat5;
    public CliPlatform plat6;
    public CliPlatform plat7;
    public CliPlatform plat8;
    public CliPlatform plat9;
    public CliPlatform plat10;

    public CliMap3() {
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

    public void stamp() {
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
