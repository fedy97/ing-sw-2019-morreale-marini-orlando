package it.polimi.se2019.model.card.powerups;


import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.exceptions.InvalidCubeException;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.player.AmmoBox;
import it.polimi.se2019.model.player.Player;

import java.util.List;

public final class TargettingScope extends PowerUpCard {


    public TargettingScope(AmmoCube ammoCube, String name, String description, String img) throws InvalidNameException, InvalidCubeException {
        super(ammoCube, name, description, img);
/*
        Action eff2 = c -> c.askFor(c.getPlayerManager().getCurrentPlayer().getPlayerBoard().getAmmoBox().getCubes(), "tribute");
        Action eff1 = c -> c.askFor(c.getValidator().getValidTargets(this), "targets");
        Action eff3 = c -> {
            Map<Player, Integer> damageMap = new HashMap<>();
            damageMap.put(c.getPlayerManager().getCurrentPlayer(), 1);
            c.getPlayerManager().addDamage(damageMap);
        };

        effects.add(eff1);
        effects.add(eff2);
        effects.add(eff3);
        stages.add(0);
        stages.add(1);
        stages.add(2);*/
    }


    public void useEffect() {
        /**
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