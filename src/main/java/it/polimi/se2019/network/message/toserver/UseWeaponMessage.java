package it.polimi.se2019.network.message.toserver;

public class UseWeaponMessage extends ToServerMessage {
    public UseWeaponMessage(Object payload) {
        super(payload);
    }

    @Override
    /**
     * Use the selected weapon on the chosen targets
     */
    public void performAction() {
        //TODO
    }
}
