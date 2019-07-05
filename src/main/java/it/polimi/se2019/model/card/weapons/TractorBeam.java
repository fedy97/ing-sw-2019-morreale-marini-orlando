package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.card.weapons.effect.Effect;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.utils.CustomLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TractorBeam extends WeaponAlternativeFire {

    public TractorBeam(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);

        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Player target = game.getPlayer(chosenTargets.get(0));
                List<Platform> destinations = new ArrayList<>(game.getGameField().getAvailablePlatforms(target.getCurrentPlatform(),
                        2));
                List<Platform> notVisible = new ArrayList<>();
                for (Platform platform : destinations) {
                    if (!game.getGameField().getVisiblePlatforms(playerManager.getCurrentPlayer().getCurrentPlatform()).contains(platform))
                        notVisible.add(platform);
                }

                destinations.removeAll(notVisible);

                c.sendMessage("Where do you want to move your target?", playerManager.getCurrentPlayer().getName());
                c.askFor(destinations, "position");

                try {
                    target.setCurrentPlatform(c.getChosenDestination().take());
                    c.broadcastMessage(playerManager.getCurrentPlayer().getName() + " moved " + target.getName());
                } catch (Exception e) {
                    CustomLogger.logException(this.getClass().getName(), e);
                }

                Map<Player, Integer> damages = new HashMap<>();
                damages.put(target, 1);

                playerManager.addDamage(damages);
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(new ArrayList<>(game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform())));
            }
        };

        Effect eff2 = new Effect(new AmmoCube[]{AmmoCube.RED, AmmoCube.YELLOW}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();
                Player target = game.getPlayer(targets.get(0));

                target.setCurrentPlatform(playerManager.getCurrentPlayer().getCurrentPlatform());
                c.broadcastMessage(playerManager.getCurrentPlayer().getName() + " moved " + target.getName() + " to his platform!");

                damages.put(target, 3);
                playerManager.addDamage(damages);
            }

            @Override
            public void setupTargets() {
                List<Character> possibleTargets = new ArrayList<>();
                for (Platform p : game.getGameField().getAvailablePlatforms(playerManager.getCurrentPlayer().getCurrentPlatform(), 2))
                    possibleTargets.addAll(p.getPlayersOnThePlatform());
                this.setPossibleTargets(possibleTargets);
            }
        };

        eff1.setMaxTargets(1);
        eff2.setMaxTargets(1);

        getEffects().add(eff1);
        getEffects().add(eff2);
    }
}