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

public final class RailGun extends WeaponAlternativeFire {

    public RailGun(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);

        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Player target = game.getPlayer(chosenTargets.get(0));

                Map<Player, Integer> damages = new HashMap<>();
                damages.put(target, 3);
                playerManager.addDamage(damages);
                game.notifyChanges();

                usableEffects[0] = false;
                usableEffects[1] = false;
            }

            @Override
            public void setupTargets() {
                List<Character> possibleTargets = new ArrayList<>();
                int[] pos = playerManager.getCurrentPlayer().getCurrentPlatform().getPlatformPosition();
                int myX = pos[0];
                int myY = pos[1];
                for (Platform platform : game.getGameField().getPlatforms()) {
                    int currX = platform.getPlatformPosition()[0];
                    int currY = platform.getPlatformPosition()[1];
                    if (myX != currX && myY != currY) {
                    } else possibleTargets.addAll(platform.getPlayersOnThePlatform());
                }
                possibleTargets.remove(playerManager.getCurrentPlayer().getCharacter());
                this.setPossibleTargets(possibleTargets);

            }
        };

        Effect eff2 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {

                Map<Player, Integer> damages = new HashMap<>();
                for (Character character : targets)
                    damages.put(game.getPlayer(character), 2);
                playerManager.addDamage(damages);
                game.notifyChanges();

                usableEffects[0] = false;
                usableEffects[1] = false;
            }

            @Override
            public void setupTargets() {
                List<Character> possibleTargets = new ArrayList<>();

                int[] pos = playerManager.getCurrentPlayer().getCurrentPlatform().getPlatformPosition();
                int myX = pos[0];
                int myY = pos[1];
                for (Platform platform : game.getGameField().getPlatforms()) {
                    int currX = platform.getPlatformPosition()[0];
                    int currY = platform.getPlatformPosition()[1];
                    if (myX != currX && myY != currY) {
                    } else possibleTargets.addAll(platform.getPlayersOnThePlatform());
                }
                possibleTargets.remove(playerManager.getCurrentPlayer().getCharacter());
                this.setPossibleTargets(possibleTargets);
            }
        };

        eff1.setMaxTargets(1);
        eff2.setMaxTargets(2);

        getEffects().add(eff1);
        getEffects().add(eff2);
    }
}