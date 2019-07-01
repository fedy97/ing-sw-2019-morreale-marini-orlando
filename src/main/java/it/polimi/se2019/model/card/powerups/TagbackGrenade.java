package it.polimi.se2019.model.card.powerups;


import it.polimi.se2019.exceptions.InvalidCubeException;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;

import java.util.List;

public final class TagbackGrenade extends PowerUpCard {
    private String userTarget;


    public TagbackGrenade(AmmoCube ammoCube, String name, String description, String img) throws InvalidNameException, InvalidCubeException {
        super(ammoCube, name, description, img);
    }

    /**
     * @return a collection of players that can be the target
     */
    @Override
    public List<Character> getPossibleTargets() {
        return null;
    }

    @Override
    public void activate(Character target) {
        c.getPlayerManager().getCurrentPlayer().getPlayerBoard().addRevengeMark(game.getPlayer(userTarget).getCharacter(), 1);
    }

    public String getUserTarget() {
        return userTarget;
    }

    public void setUserTarget(String target) {
        userTarget = target;
    }
}