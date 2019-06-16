package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Shockwave extends WeaponAlternativeFire {

    public Shockwave(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);

        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                //TODO


                usableEffects[0] = false;
                usableEffects[1] = false;
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(null);
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
                usableEffects[0] = false;
                usableEffects[1] = false;
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(null);
            }
        };

        getEffects().add(eff1);
        getEffects().add(eff2);
    }

}