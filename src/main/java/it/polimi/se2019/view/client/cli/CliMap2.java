package it.polimi.se2019.view.client.cli;

public class CliMap2 {
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
    public CliPlatform plat11;

    public CliMap2() {
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

    public void stamp() {
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
