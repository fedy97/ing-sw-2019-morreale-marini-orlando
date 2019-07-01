package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class THOR extends WeaponTwoAddingEffect {

    public THOR(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);

        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();

                //Deal 2 damage to 1 target you can see
                damages.put(game.getPlayer(chosenTargets.get(0)), 2);
                playerManager.addDamage(damages);

                usableEffects[0] = false;
                usableEffects[1] = true;

                //setting targets for the next additional effect
                getEffects().get(1).getLastEffectTargets().add(chosenTargets.get(0));
            }


            @Override
            public void setupTargets() {
                this.setPossibleTargets(game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform()));
            }
        };

        Effect eff2 = new Effect(new AmmoCube[]{AmmoCube.BLUE}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();

                //Deal 1 damage to a second target that your first target can see
                damages.put(game.getPlayer(targets.get(0)), 1);
                playerManager.addDamage(damages);

                usableEffects[1] = false;
                playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().removeAmmos(this.getCost());
                getEffects().get(2).getLastEffectTargets().add(targets.get(0));
                game.notifyChanges();
            }

            @Override
            public void setupTargets() {
                List<Character> targets = new ArrayList<>();
                targets.addAll(game.getGameField().getVisiblePlayers(game.getPlayer(this.getLastEffectTargets().get(0)).getCurrentPlatform()));
                targets.remove(getLastEffectTargets().get(0));
                this.setPossibleTargets(targets);
            }
        };

        Effect eff3 = new Effect(new AmmoCube[]{AmmoCube.BLUE}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();
                damages.put(game.getPlayer(targets.get(0)), 2);
                playerManager.addDamage(damages);

                usableEffects[2] = false;
                playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().removeAmmos(this.getCost());
                game.notifyChanges();
            }

            @Override
            public void setupTargets() {
                List<Character> targets = new ArrayList<>();
                targets.addAll(game.getGameField().getVisiblePlayers(game.getPlayer(this.getLastEffectTargets().get(1)).getCurrentPlatform()));
                targets.remove(getLastEffectTargets().get(0));
                targets.remove(getLastEffectTargets().get(1));
                this.setPossibleTargets(targets);
            }
        };

        usableEffects[0] = true;
        usableEffects[1] = false;
        usableEffects[2] = false;

        eff1.setMaxTargets(1);
        eff2.setMaxTargets(1);
        eff3.setMaxTargets(1);

        getEffects().add(eff1);
        getEffects().add(eff2);
        getEffects().add(eff3);
    }

    @Override
    public void reload() {
        super.reload();
        usableEffects[0] = true;
        usableEffects[1] = false;
        usableEffects[2] = false;
    }

}