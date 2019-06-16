package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LockRifle extends WeaponOneAddingEffect {

    public LockRifle(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);

        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();
                Character chosenTarget = chosenTargets.get(0);
                damages.put(game.getPlayer(chosenTarget), 2);
                playerManager.addDamage(damages);
                playerManager.mark(game.getPlayer(chosenTarget), 1);

                usableEffects[0] = false;
                usableEffects[1] = true;
                getEffects().get(1).getLastEffectTargets().add(chosenTarget);
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform()));
            }
        };

        Effect eff2 = new Effect(new AmmoCube[]{AmmoCube.RED}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                playerManager.mark(game.getPlayer(targets.get(0)), 1);

                usableEffects[1] = false;
                playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().removeAmmos(this.getCost());
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform()));
                this.getPossibleTargets().remove(this.getLastEffectTargets().get(0));
            }
        };

        usableEffects[0] = true;
        usableEffects[1] = false;

        eff1.setMaxTargets(1);
        eff2.setMaxTargets(1);

        getEffects().add(eff1);
        getEffects().add(eff2);
    }

    @Override
    public void reload() {
        super.reload();
        usableEffects[0] = true;
        usableEffects[1] = false;
    }
}