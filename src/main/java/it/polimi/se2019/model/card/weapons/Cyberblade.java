package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.utils.CustomLogger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Cyberblade extends WeaponAlternativeFire {

    public Cyberblade(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);
        Controller c = Controller.getInstance();
        PlayerManager playerManager = c.getPlayerManager();
        Game game = c.getGame();

        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();

                //"Deal 2 damage to 1 target on your square"
                damages.put(game.getPlayer(chosenTargets.get(0)), 2);
                playerManager.addDamage(damages);
                usableEffects[0] = false;
                usableEffects[2] = true;

                //setting targets for the next additional effect
                List<Character> targets = playerManager.getCurrentPlayer().getCurrentPlatform().getPlayersOnThePlatform();
                //targets.remove(playerManager.getCurrentPlayer().getCharacter());
                targets.remove(chosenTargets.get(0));
                getEffects().get(2).getLastEffectTargets().addAll(targets);
            }


            @Override
            public void setupTargets() {
                this.setPossibleTargets(playerManager.getCurrentPlayer().getCurrentPlatform().getPlayersOnThePlatform());
            }
        };

        Effect eff2 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                //"Move 1 square before or after the basic effect"
                c.sendMessage("Where do you want to move?", playerManager.getCurrentPlayer().getName());
                c.askFor(game.getGameField().getAvailablePlatforms(playerManager.getCurrentPlayer().getCurrentPlatform(), 1), "position");

                try {
                    Platform destination = c.getChosenDestination().take();
                    playerManager.move(destination);
                    c.broadcastMessage(playerManager.getCurrentPlayer().getCharacter().name() + " moved  to " + destination.toString());
                } catch (Exception e) {
                    CustomLogger.logException(getClass().getName(), e);
                }

                usableEffects[1] = false;
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(null);
            }
        };

        Effect eff3 = new Effect(new AmmoCube[]{AmmoCube.YELLOW}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();

                //"Deal 2 damage to a different target on your square"
                damages.put(game.getPlayer(targets.get(0)), 2);
                playerManager.addDamage(damages);
                usableEffects[3] = false;

                playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().removeAmmos(this.getCost());
            }

            @Override
            public void setupTargets() {
                List<Character> targets;

                if (usableEffects[1]) {
                    targets = getLastEffectTargets();
                } else {
                    targets = playerManager.getCurrentPlayer().getCurrentPlatform().getPlayersOnThePlatform();
                    //commentata per testare effetto 3
                    //targets.remove(playerManager.getCurrentPlayer().getCharacter());
                }

                this.setPossibleTargets(targets);
            }
        };

        availableEffects = new boolean[]{true, true, true};
        usableEffects = new boolean[]{true, true, false};

        eff1.setMaxTargets(1);
        eff3.setMaxTargets(1);

        getEffects().add(eff1);
        getEffects().add(eff2);
        getEffects().add(eff3);
    }

    @Override
    public void reload() {
        cleanCache();
        usableEffects = new boolean[]{true, true, false};
        loaded = true;
    }
}