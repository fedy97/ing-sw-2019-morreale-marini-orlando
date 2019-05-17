package it.polimi.se2019.model.card.powerups;


import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.exceptions.InvalidCubeException;
import it.polimi.se2019.exceptions.InvalidImageException;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.card.weapons.BasicEffect;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.AmmoBox;
import it.polimi.se2019.model.player.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public final class TargettingScope extends PowerUpCard {


    public TargettingScope(AmmoCube ammoCube, String name, String description, String img) throws InvalidNameException, InvalidCubeException {
        super(ammoCube, name, description, img);

        BasicEffect eff1 = c -> c.askFor(c.getValidator().getValidTargets(this), "targets");
        BasicEffect eff2 = c -> c.askFor(c.getGame().getGameField().getPlatformDir(c.getCurrentTargets().get(0).getCurrentPlatform()), "tribute");

        effects.add(eff1);
        effects.add(eff2);
        stages.add(0);
        stages.add(1);
    }


    public void useEffect() {
        /** TODO
        Player currPlayer = c.getPlayerManager().getCurrentPlayer();
        Player target = c.askForTargets(c.getValidator().getValidTargets(this)).get(0);
        AmmoCube cost = c.askForTribute();
        HashMap<Player, Integer> targetMap = new HashMap<>();
        targetMap.put(target, 1);
        c.getPlayerManager().addDamage(targetMap);
        currPlayer.getPlayerBoard().getAmmoBox().removeAmmos(cost, 1);*/
    }

    /**
     * Return if the player in his current state can use the power up or not
     */
    public boolean isUsable(Player player) {
        AmmoBox ammoBox = player.getPlayerBoard().getAmmoBox();
        return player.isAttacking() && !ammoBox.isEmpty();
    }

    /**
     * @return a collection of players that can be the target
     */
    public List<Player> getPossibleTargets(Controller c) {
        return c.getPlayerManager().getCurrentPlayer().getCurrentTargets();
    }

}