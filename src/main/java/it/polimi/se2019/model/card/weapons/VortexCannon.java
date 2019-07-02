package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.utils.CustomLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class VortexCannon extends WeaponOneAddingEffect {
    private Platform vortex;

    public VortexCannon(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);
        usableEffects = new boolean[3];


        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                List<Platform> possibleVortexs = new ArrayList<>(game.getGameField().getVisiblePlatforms(playerManager.getCurrentPlayer().getCurrentPlatform()));
                possibleVortexs.remove(playerManager.getCurrentPlayer().getCurrentPlatform());

                c.sendMessage("Where do you want to open the vortex?", playerManager.getCurrentPlayer().getName());
                c.askFor(possibleVortexs, "position");

                List<Character> targets = new ArrayList<>();
                Character target = null;

                try {
                    vortex = c.getChosenDestination().take();
                    targets.addAll(vortex.getPlayersOnThePlatform());
                    for (Platform p : game.getGameField().getAvailablePlatforms(vortex, 1)) {
                        for (Character c : p.getPlayersOnThePlatform())
                            if (c != playerManager.getCurrentPlayer().getCharacter())
                                targets.add(c);
                    }

                    if (!targets.isEmpty()) {
                        c.askFor(targets, "targets");

                        target = c.getCurrentTargets().take();
                        c.getCurrentTargets().clear();

                        if (game.getPlayer(target).getCurrentPlatform() != vortex)
                            game.getPlayer(target).setCurrentPlatform(vortex);
                        Map<Player, Integer> damage = new HashMap<>();
                        damage.put(game.getPlayer(target), 2);
                        playerManager.addDamage(damage);
                        targets.remove(target);
                    }
                } catch (Exception e) {
                    CustomLogger.logException(this.getClass().getName(), e);
                }
                //setting targets for the adding effect
                getEffects().get(1).getLastEffectTargets().addAll(targets);

                usableEffects[0] = false;
                usableEffects[1] = true;
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(null);
            }
        };


        Effect eff2 = new Effect(new AmmoCube[]{AmmoCube.RED}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Map<Player, Integer> damage = new HashMap<>();
                for (Character target : chosenTargets) {
                    if (game.getPlayer(target).getCurrentPlatform() != vortex)
                        game.getPlayer(target).setCurrentPlatform(vortex);
                    damage.put(game.getPlayer(target), 1);
                }
                playerManager.addDamage(damage);


                playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().removeAmmos(this.getCost());
                usableEffects[1] = false;
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(new ArrayList<>(getLastEffectTargets()));
            }
        };

        usableEffects[0] = true;
        usableEffects[1] = false;

        eff1.setMaxTargets(1);
        eff2.setMaxTargets(2);
        getEffects().add(eff1);
        getEffects().add(eff2);
    }

}