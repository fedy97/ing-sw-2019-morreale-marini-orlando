package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.weapons.effect.Effect;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.toclient.SendBinaryOption;
import it.polimi.se2019.utils.CustomLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Shotgun extends WeaponAlternativeFire {

    public Shotgun(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);

        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Player target = game.getPlayer(chosenTargets.get(0));

                Map<Player, Integer> damages = new HashMap<>();
                damages.put(target, 3);
                playerManager.addDamage(damages);

                try {
                    c.callView(new SendBinaryOption("Do you want to move the target 1 square away?"), playerManager.getCurrentPlayer().getName());
                    if (c.getChosenBinaryOption().take()) {
                        List<Platform> destinations = new ArrayList<>(game.getGameField().getAvailablePlatforms(playerManager.getCurrentPlayer().getCurrentPlatform(), 1));
                        c.sendMessage("Where do you want to move your target?", playerManager.getCurrentPlayer().getName());
                        c.askFor(destinations, "position");
                        target.setCurrentPlatform(c.getChosenDestination().take());
                        game.notifyChanges();
                        c.broadcastMessage(playerManager.getCurrentPlayer().getName() + " moved " + target.getName());
                    }
                } catch (Exception e) {
                    CustomLogger.logException(this.getClass().getName(), e);
                }
            }

            @Override
            public void setupTargets() {
                List<Character> targets = new ArrayList<>();
                targets.addAll(playerManager.getCurrentPlayer().getCurrentPlatform().getPlayersOnThePlatform());
                targets.remove(playerManager.getCurrentPlayer().getCharacter());
                this.setPossibleTargets(playerManager.getCurrentPlayer().getCurrentPlatform().getPlayersOnThePlatform());
            }
        };

        Effect eff2 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();

                damages.put(game.getPlayer(targets.get(0)), 2);
                playerManager.addDamage(damages);
            }

            @Override
            public void setupTargets() {
                List<Character> possibleTargets = new ArrayList<>();

                for (Platform p : game.getGameField().getAvailablePlatforms(playerManager.getCurrentPlayer().getCurrentPlatform(), 1))
                    possibleTargets.addAll(p.getPlayersOnThePlatform());
                possibleTargets.removeAll(playerManager.getCurrentPlayer().getCurrentPlatform().getPlayersOnThePlatform());
                this.setPossibleTargets(possibleTargets);
            }
        };

        eff1.setMaxTargets(1);
        eff2.setMaxTargets(1);

        getEffects().add(eff1);
        getEffects().add(eff2);
    }
}