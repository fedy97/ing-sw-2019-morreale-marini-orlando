package it.polimi.se2019.network.message.to_server;

public class BuyWithPowerupsMessage extends ToServerMessage {

    public BuyWithPowerupsMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        String hashPowerup = (String) payload;
        //TODO
    }
}
