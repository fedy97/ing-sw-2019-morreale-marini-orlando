package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.toclient.SendBinaryOption;
import it.polimi.se2019.utils.CustomLogger;
import it.polimi.se2019.utils.HandyFunctions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Sledgehammer extends WeaponAlternativeFire {

    public Sledgehammer(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);

        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();
                damages.put(game.getPlayer(chosenTargets.get(0)), 2);
                playerManager.addDamage(damages);
            }

            @Override
            public void setupTargets() {
                List<Character> possibleTargets = new ArrayList<>();
                possibleTargets.addAll(playerManager.getCurrentPlayer().getCurrentPlatform().getPlayersOnThePlatform());
                possibleTargets.remove(playerManager.getCurrentPlayer().getCharacter());
                this.setPossibleTargets(possibleTargets);
            }
        };

        Effect eff2 = new Effect(new AmmoCube[]{AmmoCube.RED}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                Player target = game.getPlayer(targets.get(0));
                Map<Player, Integer> damages = new HashMap<>();

                damages.put(target, 3);
                playerManager.addDamage(damages);

                try {
                    c.callView(new SendBinaryOption("Do you want to move the target away?"), playerManager.getCurrentPlayer().getName());
                    if (c.getChosenBinaryOption().take()) {
                        List<Platform> destinations = new ArrayList<>(game.getGameField().getAvailablePlatforms(playerManager.getCurrentPlayer().getCurrentPlatform(), 2));
                        int[] pos = playerManager.getCurrentPlayer().getCurrentPlatform().getPlatformPosition();
                        int myX = pos[0];
                        int myY = pos[1];
                        List<Platform> toDelete = new ArrayList<>();
                        for (Platform platform : destinations) {
                            int currX = platform.getPlatformPosition()[0];
                            int currY = platform.getPlatformPosition()[1];
                            if (myX != currX && myY != currY)
                                toDelete.add(platform);
                        }
                        destinations.removeAll(toDelete);
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
                List<Character> possibleTargets = new ArrayList<>(playerManager.getCurrentPlayer().getCurrentPlatform().getPlayersOnThePlatform());
                this.setPossibleTargets(possibleTargets);
            }
        };

        eff1.setMaxTargets(1);
        eff2.setMaxTargets(1);

        getEffects().add(eff1);
        getEffects().add(eff2);
    }
}