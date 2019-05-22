package it.polimi.se2019.view.client.cli;


public class CliPlatform {

    private CliColor platformColor;
    private boolean doorUp;
    private boolean doorDown;
    private boolean doorLeft;
    private boolean doorRight;
    private String banshee;
    private String sprog;
    private String dozer;
    private String violet;
    private String distructor;
    private String ammo;

    public CliPlatform(CliColor color, boolean doorUp, boolean doorDown, boolean doorLeft, boolean doorRight ) {
        platformColor = color;
        this.doorUp = doorUp;
        this.doorDown = doorDown;
        this.doorLeft = doorLeft;
        this.doorRight = doorRight;
        banshee = " ";
        sprog = " ";
        dozer = " ";
        violet = " ";
        distructor = " ";
        ammo = "   ";
    }

    public void setAmmo(String ammo) {
        if (ammo == null || ammo.equals("null"))
            ammo = "   ";
        this.ammo = ammo;
    }

    public void setBansheeInside() {
        banshee = "●";
    }

    public void setSprogInside() {
        sprog = "●";
    }

    public void setDozerInside() {
        dozer = "●";
    }

    public void setVioletInside() {
        violet = "●";
    }

    public void setDistructorInside() {
        distructor = "●";
    }

    public void noBansheeInside() {
        banshee = " ";
    }

    public void noSprogInside() {
        sprog = " ";
    }

    public void noDozerInside() {
        dozer = " ";
    }

    public void noVioletInside() {
        violet = " ";
    }

    public void noDistructorInside() {
        distructor = " ";
    }

    private void printAmmo(String ammo) {
        if (ammo == null) {
            ammo = "   ";
        }
        if (ammo.equals("   ")) {
            CliPrinter.stamp(ammo);
            return;
        }
        if(ammo.length() == 2) {
            CliPrinter.stamp("★",CliColor.TEXTWHITE);
            for(int i=0; i<2;i++) {
                if(ammo.charAt(i) == 'r') {
                    CliPrinter.stamp("■",CliColor.TEXTRED);
                }
                else if (ammo.charAt(i) == 'b') {
                    CliPrinter.stamp("■",CliColor.TEXTBLUE);
                }
                else {
                    CliPrinter.stamp("■",CliColor.TEXTYELLOW);
                }
            }
        }
        else {
            for(int i=0; i<3;i++) {
                if(ammo.charAt(i) == 'r') {
                    CliPrinter.stamp("■",CliColor.TEXTRED);
                }
                else if (ammo.charAt(i) == 'b') {
                    CliPrinter.stamp("■",CliColor.TEXTBLUE);
                }
                else {
                    CliPrinter.stamp("■",CliColor.TEXTYELLOW);
                }
            }
        }
    }

    public void print() {
        if (doorUp) {
            CliPrinter.stamp("┏━━━━━┛   ┗━━━━━┓", platformColor);
        }
        else {
            CliPrinter.stamp("┏━━━━━━━━━━━━━━━┓", platformColor);
        }

        if (!doorLeft) {
            CliSetUp.cursorLeft(17);
            CliSetUp.cursorDown(1);
            for (int i=0; i < 5; i++) {
                CliPrinter.stamp("┃", platformColor);
                CliSetUp.cursorLeft(1);
                CliSetUp.cursorDown(1);
            }
        }
        else {
            CliSetUp.cursorLeft(17);
            CliSetUp.cursorDown(1);
            for (int i=0; i < 1; i++) {
                CliPrinter.stamp("┃", platformColor);
                CliSetUp.cursorLeft(1);
                CliSetUp.cursorDown(1);
            }
            CliPrinter.stamp("┛", platformColor);
            CliSetUp.cursorLeft(1);
            CliSetUp.cursorDown(2);
            CliPrinter.stamp("┓", platformColor);
            CliSetUp.cursorLeft(1);
            CliSetUp.cursorDown(1);
            for (int i=0; i < 1; i++) {
                CliPrinter.stamp("┃", platformColor);
                CliSetUp.cursorLeft(1);
                CliSetUp.cursorDown(1);
            }
        }

        if (doorDown) {
            CliPrinter.stamp("┗━━━━━┓   ┏━━━━━┛", platformColor);
        }
        else {
            CliPrinter.stamp("┗━━━━━━━━━━━━━━━┛", platformColor);
        }

        if (doorRight) {
            CliSetUp.cursorLeft(1);
            CliSetUp.cursorUp(5);
            for (int i=0; i < 1; i++) {
                CliPrinter.stamp("┃", platformColor);
                CliSetUp.cursorLeft(1);
                CliSetUp.cursorDown(1);
            }
            CliPrinter.stamp("┗", platformColor);
            CliSetUp.cursorLeft(1);
            CliSetUp.cursorDown(2);
            CliPrinter.stamp("┏", platformColor);
            CliSetUp.cursorLeft(1);
            CliSetUp.cursorDown(1);
            for (int i=0; i < 1; i++) {
                CliPrinter.stamp("┃", platformColor);
                CliSetUp.cursorLeft(1);
                CliSetUp.cursorDown(1);
            }
        }
        else {
            CliSetUp.cursorLeft(1);
            CliSetUp.cursorUp(5);
            for (int i=0; i < 5; i++) {
                CliPrinter.stamp("┃", platformColor);
                CliSetUp.cursorLeft(1);
                CliSetUp.cursorDown(1);
            }
        }

        CliSetUp.cursorLeft(13);
        CliSetUp.cursorUp((5));
        CliPrinter.stamp(banshee, CliColor.TEXTBLUE);
        CliSetUp.cursorRight(5);
        CliPrinter.stamp(sprog, CliColor.TEXTGREEN);
        CliSetUp.cursorDown((4));
        CliSetUp.cursorLeft(3);
        CliPrinter.stamp(dozer, CliColor.TEXTWHITE);
        CliSetUp.cursorUp(2);
        CliSetUp.cursorRight(5);
        CliPrinter.stamp(violet, CliColor.TEXTPURPLE);
        CliSetUp.cursorLeft(11);
        CliPrinter.stamp(distructor, CliColor.TEXTYELLOW);
        CliSetUp.cursorRight(3);
        printAmmo(ammo);

        CliSetUp.cursorRight(7);
        CliSetUp.cursorUp(3);
    }
}
