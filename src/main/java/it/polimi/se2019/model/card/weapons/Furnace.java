package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.enumeration.Orientation;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.utils.CustomLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Furnace extends WeaponAlternativeFire {

    public Furnace(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);
        Controller c = Controller.getInstance();
        PlayerManager playerManager = c.getPlayerManager();
        Game game = Controller.getInstance().getGame();


        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();

                List<Platform> destinations = new ArrayList<>();
                for (Orientation or : playerManager.getCurrentPlayer().getCurrentPlatform().getDoorLocation()) {
                    Platform platNewRoom = playerManager.getCurrentPlayer().getCurrentPlatform().getAdjacentPlatform(or);
                    destinations.add(platNewRoom);
                }

                c.sendMessage("What room do you want to burn?", playerManager.getCurrentPlayer().getName());
                c.askFor(destinations, "position");

                try {
                    Platform target = c.getChosenDestination().take();
                    for (Platform p : target.getPlatformRoom().getPlatformsInRoom()) {
                        for (Character character : p.getPlayersOnThePlatform())
                            damages.put(game.getPlayer(character), 1);
                    }
                } catch (Exception e) {
                    CustomLogger.logException(this.getClass().getName(), e);
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

        Effect eff2 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();

                List<Platform> destinations = game.getGameField().getAvailablePlatforms(playerManager.getCurrentPlayer().getCurrentPlatform(), 1);
                destinations.remove(playerManager.getCurrentPlayer().getCurrentPlatform());


                c.sendMessage("What platform do you want to burn?", playerManager.getCurrentPlayer().getName());
                c.askFor(destinations, "position");

                try {
                    Platform target = c.getChosenDestination().take();
                    for (Character character : target.getPlayersOnThePlatform()) {
                        damages.put(game.getPlayer(character), 1);
                        playerManager.mark(game.getPlayer(character), 1);
                    }
                } catch (Exception e) {
                    CustomLogger.logException(this.getClass().getName(), e);
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