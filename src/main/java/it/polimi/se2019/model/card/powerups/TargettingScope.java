package it.polimi.se2019.model.card.powerups;


import it.polimi.se2019.controller.ControllerState;
import it.polimi.se2019.exceptions.InvalidCubeException;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.toclient.ShowAmmoMessage;
import it.polimi.se2019.utils.CustomLogger;

import java.util.List;

public final class TargettingScope extends PowerUpCard {


    public TargettingScope(AmmoCube ammoCube, String name, String description, String img) throws InvalidNameException, InvalidCubeException {
        super(ammoCube, name, description, img);

    }

    /**
     * Return if the player in his current state can use the power up or not
     */
    public boolean isUsable(Player player) {
        return true;
    }

    /**
     * @return a collection of players that can be the target
     */
    @Override
    public List<Character> getPossibleTargets() {
        return c.getGame().getCharacters(c.getPlayerManager().getTempPlayers());
    }

    /**
     * @param target chosen by the current player
     */
    @Override
    public void activate(Character target) {
        Player currPlayer = c.getPlayerManager().getCurrentPlayer();
        Player playerTarget = c.getGame().getPlayer(target);

        c.callView(new ShowAmmoMessage(null), currPlayer.getName());

        AmmoCube cube = null;
        try {
            cube = AmmoCube.valueOf(c.getChosenAmmo().take());
            currPlayer.getPlayerBoard().getAmmoBox().removeAmmos(cube, 1);
            playerTarget.getPlayerBoard().addDamage(currPlayer.getCharacter(), 1);
        } catch (Exception e) {
            CustomLogger.logException(this.getClass().getName(), e);
        }

        if (c.isFrenzyModeOn())
            c.setState(ControllerState.PROCESSING_ACTION_1);
        else
            c.setState(ControllerState.PROCESSING_ACTION_3);
    }

}