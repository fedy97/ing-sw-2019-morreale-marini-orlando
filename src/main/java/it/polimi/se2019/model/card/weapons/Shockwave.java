package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Shockwave extends WeaponAlternativeFire {

    public Shockwave(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);

        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                boolean samePlatform = false;

                if (chosenTargets.size() > 1)
                    for (int i = 0; i < chosenTargets.size() - 1; i++) {
                        for (int j = i + 1; i < chosenTargets.size(); j++)
                            if (game.getPlayer(chosenTargets.get(i)).getCurrentPlatform() == game.getPlayer(chosenTargets.get(j)).getCurrentPlatform())
                                samePlatform = true;
                    }

                if (!samePlatform) {
                    Map<Player, Integer> damages = new HashMap<>();
                    for (Character character : chosenTargets)
                        damages.put(game.getPlayer(character), 1);
                    playerManager.addDamage(damages);
                } else {
                    c.sendMessage("You selection was wrong, two or more targets are on the same platform!", playerManager.getCurrentPlayer().getName());
                }
                usableEffects[0] = false;
                usableEffects[1] = false;
            }

            @Override
            public void setupTargets() {
                List<Character> targets = new ArrayList<>();
                List<Platform> destinations = game.getGameField().getAvailablePlatforms(playerManager.getCurrentPlayer().getCurrentPlatform(), 1);
                destinations.remove(playerManager.getCurrentPlayer().getCurrentPlatform());
                for (Platform p : destinations) {
                    for (Character character : p.getPlayersOnThePlatform()) {
                        targets.add(character);
                    }
                }
                this.setPossibleTargets(targets);
            }
        };

        Effect eff2 = new Effect(new AmmoCube[]{AmmoCube.YELLOW}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();

                List<Platform> destinations = game.getGameField().getAvailablePlatforms(playerManager.getCurrentPlayer().getCurrentPlatform(), 1);
                destinations.remove(playerManager.getCurrentPlayer().getCurrentPlatform());

                for (Platform p : destinations) {
                    for (Character character : p.getPlayersOnThePlatform()) {
                        damages.put(game.getPlayer(character), 1);
                    }
                }

                playerManager.addDamage(damages);
                playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().removeAmmos(this.getCost());
                usableEffects[0] = false;
                usableEffects[1] = false;
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(null);
            }
        };

        eff1.setMaxTargets(3);
        getEffects().add(eff1);
        getEffects().add(eff2);
    }

}