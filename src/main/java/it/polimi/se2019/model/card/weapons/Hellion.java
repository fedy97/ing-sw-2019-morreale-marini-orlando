package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.card.weapons.effect.Effect;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Hellion extends WeaponAlternativeFire {

    public Hellion(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);


        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Player target = game.getPlayer(chosenTargets.get(0));

                Map<Player, Integer> damages = new HashMap<>();
                damages.put(target, 1);
                playerManager.addDamage(damages);

                for (Character character : target.getCurrentPlatform().getPlayersOnThePlatform()) {
                    playerManager.mark(game.getPlayer(character), 1);
                }
            }

            @Override
            public void setupTargets() {
                List<Character> targets = new ArrayList<>(game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform()));
                targets.removeAll(playerManager.getCurrentPlayer().getCurrentPlatform().getPlayersOnThePlatform());
                this.setPossibleTargets(targets);
            }
        };

        Effect eff2 = new Effect(new AmmoCube[]{AmmoCube.RED}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Player target = game.getPlayer(chosenTargets.get(0));

                Map<Player, Integer> damages = new HashMap<>();
                damages.put(target, 1);
                playerManager.addDamage(damages);

                for (Character character : target.getCurrentPlatform().getPlayersOnThePlatform()) {
                    playerManager.mark(game.getPlayer(character), 2);
                }
            }

            @Override
            public void setupTargets() {
                List<Character> targets = new ArrayList<>(game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform()));
                targets.removeAll(playerManager.getCurrentPlayer().getCurrentPlatform().getPlayersOnThePlatform());
                this.setPossibleTargets(targets);
            }
        };

        eff1.setMaxTargets(1);
        eff2.setMaxTargets(1);

        getEffects().add(eff1);
        getEffects().add(eff2);
    }
}