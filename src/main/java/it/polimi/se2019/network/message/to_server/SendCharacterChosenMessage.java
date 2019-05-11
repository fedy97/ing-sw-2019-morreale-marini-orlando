package it.polimi.se2019.network.message.to_server;

import it.polimi.se2019.model.Game;

public class SendCharacterChosenMessage extends ToServerMessage {

    public SendCharacterChosenMessage(Object payload) {
        super(payload);
    }

    @Override
    public void performAction() {
        try {
            Game.getInstance().setCharacterChosen(this.sender, (String) payload);
        } catch (Exception ex) {
        }
    }
}
