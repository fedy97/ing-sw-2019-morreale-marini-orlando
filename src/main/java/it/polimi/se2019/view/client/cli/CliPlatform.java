package it.polimi.se2019.view.client.cli;

/**
 * Class used to print on the terminal a single platform of the playing field
 * @author Simone Orlando
 */
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

    /**
     * Class constructor that initializes the parameters of the platform
     * @param color of the platform
     * @param doorUp true if there is a door in the upper side
     * @param doorDown true if there is a door in the lower side
     * @param doorLeft true if there is a door in the left side
     * @param doorRight true if there is a door in the right side
     */
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

    /**
     * Set the ammunition present in the platform
     * @param ammo in the platform
     */
    public void setAmmo(String ammo) {
        if (ammo == null || ammo.equals("null"))
            ammo = "   ";
        this.ammo = ammo;
    }

    /**
     * Sets that Banshee is on this platform
     */
    public void setBansheeInside() {
        banshee = "●";
    }

    /**
     * Sets that Sprog is on this platform
     */
    public void setSprogInside() {
        sprog = "●";
    }

    /**
     * Sets that Dozer is on this platform
     */
    public void setDozerInside() {
        dozer = "●";
    }

    /**
     * Sets that Violet is on this platform
     */
    public void setVioletInside() {
        violet = "●";
    }

    /**
     * Sets that Distructor is on this platform
     */
    public void setDistructorInside() {
        distructor = "●";
    }

    /**
     * Sets that Banshee is not on this platform
     */
    public void noBansheeInside() {
        banshee = " ";
    }

    /**
     * Sets that Sprog is not on this platform
     */
    public void noSprogInside() {
        sprog = " ";
    }

    /**
     * Sets that Dozer is not on this platform
     */
    public void noDozerInside() {
        dozer = " ";
    }

    /**
     * Sets that Violet is not on this platform
     */
    public void noVioletInside() {
        violet = " ";
    }

    /**
     * Sets that Distructor is not on this platform
     */
    public void noDistructorInside() {
        distructor = " ";
    }

    /**
     * Print the ammunition set inside the platform
     * @param ammo in the platform
     */
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

    /**
     * Prints the complete platform on the terminal
     */
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
