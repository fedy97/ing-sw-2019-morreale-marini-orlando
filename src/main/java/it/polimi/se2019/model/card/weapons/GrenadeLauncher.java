package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.network.message.toclient.SendBinaryOption;
import it.polimi.se2019.utils.CustomLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GrenadeLauncher extends WeaponOneAddingEffect {

    public GrenadeLauncher(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);

        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Player target = game.getPlayer(chosenTargets.get(0));

                Map<Player, Integer> damages = new HashMap<>();
                damages.put(target, 1);
                playerManager.addDamage(damages);

                try {
                    c.callView(new SendBinaryOption("Do you want to move the target 1 square away?"), playerManager.getCurrentPlayer().getName());
                    if (c.getChosenBinaryOption().take()) {
                        List<Platform> destinations = game.getGameField().getAvailablePlatforms(target.getCurrentPlatform(), 1);
                        c.sendMessage("Where do you want to move your target?", playerManager.getCurrentPlayer().getName());
                        c.askFor(destinations, "position");
                        target.setCurrentPlatform(c.getChosenDestination().take());
                        game.notifyChanges();
                        c.broadcastMessage(playerManager.getCurrentPlayer().getName() + " moved " + target.getName());
                    }
                } catch (Exception e) {
                    CustomLogger.logException(this.getClass().getName(), e);
                }

                usableEffects[0] = false;
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform()));
            }
        };

        Effect eff2 = new Effect(new AmmoCube[]{AmmoCube.RED}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();

                List<Platform> destinations = game.getGameField().getVisiblePlatforms(playerManager.getCurrentPlayer().getCurrentPlatform());

                c.sendMessage("What platform do you want to burn?", playerManager.getCurrentPlayer().getName());
                c.askFor(destinations, "position");

                try {
                    Platform target = c.getChosenDestination().take();
                    List<Character> characters = new ArrayList<>(target.getPlayersOnThePlatform());
                    characters.remove(playerManager.getCurrentPlayer().getCharacter());
                    for (Character character : characters)
                        damages.put(game.getPlayer(character), 1);
                } catch (Exception e) {
                    CustomLogger.logException(this.getClass().getName(), e);
                }

                playerManager.addDamage(damages);

                usableEffects[1] = false;
                playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().removeAmmos(this.getCost());
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(null);
            }
        };

        usableEffects[0] = true;
        usableEffects[1] = true;

        eff1.setMaxTargets(1);

        getEffects().add(eff1);
        getEffects().add(eff2);
    }

    @Override
    public void reload() {
        super.reload();
        usableEffects[0] = true;
        usableEffects[1] = true;
    }
}