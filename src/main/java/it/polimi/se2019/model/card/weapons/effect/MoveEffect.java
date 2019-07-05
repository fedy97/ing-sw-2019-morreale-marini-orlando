package it.polimi.se2019.model.card.weapons.effect;

import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.weapons.WeaponCard;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.utils.CustomLogger;

import java.util.List;

public class MoveEffect extends Effect{

    public MoveEffect(AmmoCube[] cost){
        super(cost);
    }

    @Override
    public void activateEffect(List<Character> targets, WeaponCard card) {
        c.sendMessage("Where do you want to move?", c.getPlayerManager().getCurrentPlayer().getName());
        c.askFor(c.getGame().getGameField().getAvailablePlatforms(c.getPlayerManager().getCurrentPlayer().getCurrentPlatform(), 2), "position");

        try {
            Platform destination = c.getChosenDestination().take();
            c.getPlayerManager().move(destination);
            c.broadcastMessage(c.getPlayerManager().getCurrentPlayer().getCharacter().name() + " moved  to " + destination.toString());
        } catch (Exception e) {
            CustomLogger.logException(getClass().getName(), e);
        }
    }

    @Override
    public void setupTargets() {
        this.setPossibleTargets(null);
    }
}
