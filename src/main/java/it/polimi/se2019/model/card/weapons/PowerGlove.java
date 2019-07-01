package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.toclient.SendBinaryOption;
import it.polimi.se2019.utils.CustomLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PowerGlove extends WeaponAlternativeFire {

    public PowerGlove(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);

        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Player target = game.getPlayer(chosenTargets.get(0));

                Map<Player, Integer> damages = new HashMap<>();
                damages.put(target, 1);
                playerManager.addDamage(damages);
                playerManager.mark(game.getPlayer(chosenTargets.get(0)), 2);

                playerManager.getCurrentPlayer().setCurrentPlatform(target.getCurrentPlatform());
                game.notifyChanges();

                usableEffects[0] = false;
                usableEffects[1] = false;
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

        Effect eff2 = new Effect(new AmmoCube[]{AmmoCube.BLUE}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                Player target = game.getPlayer(targets.get(0));
                Map<Player, Integer> damages = new HashMap<>();

                damages.put(game.getPlayer(targets.get(0)), 2);
                playerManager.addDamage(damages);
                Platform oldPlat = playerManager.getCurrentPlayer().getCurrentPlatform();
                playerManager.getCurrentPlayer().setCurrentPlatform(target.getCurrentPlatform());
                game.notifyChanges();
                Orientation rightOr = Orientation.RIGHT;
                for (Orientation or : Orientation.values()) {
                    if (oldPlat.getAdjacentPlatform(or) != null && oldPlat.getAdjacentPlatform(or).equals(playerManager.getCurrentPlayer().getCurrentPlatform()))
                        rightOr = or;
                }
                Platform toCheck = playerManager.getCurrentPlayer().getCurrentPlatform().getAdjacentPlatform(rightOr);
                if (toCheck != null && !toCheck.getPlayersOnThePlatform().isEmpty()) {
                    try {
                        c.callView(new SendBinaryOption("Do you want to move the same direction to inflict other 2 damages to another player?"), playerManager.getCurrentPlayer().getName());
                        if (c.getChosenBinaryOption().take()) {
                            List<Character> targets2 = new ArrayList<>();
                            targets2.addAll(toCheck.getPlayersOnThePlatform());

                            c.askFor(targets2, "targets");
                            damages.clear();
                            damages.put(game.getPlayer(c.getCurrentTargets().take()), 2);
                            playerManager.addDamage(damages);
                            playerManager.getCurrentPlayer().setCurrentPlatform(toCheck);

                            game.notifyChanges();
                        }
                    } catch (Exception e) {
                        CustomLogger.logException(this.getClass().getName(), e);
                    }
                }

                usableEffects[0] = false;
                usableEffects[1] = false;
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