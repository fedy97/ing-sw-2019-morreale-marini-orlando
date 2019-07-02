package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;
import it.polimi.se2019.utils.CustomLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PlasmaGun extends WeaponTwoAddingEffect {

    public PlasmaGun(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);

        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();

                //Deal 2 damage to 1 target you can see

                damages.put(game.getPlayer(chosenTargets.get(0)), 2);
                playerManager.addDamage(damages);
                usableEffects[0] = false;
                usableEffects[2] = true;

                //setting targets for the next additional effect
                getEffects().get(2).getLastEffectTargets().add(chosenTargets.get(0));
            }


            @Override
            public void setupTargets() {
                this.setPossibleTargets(new ArrayList<>(game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform())));
            }
        };

        Effect eff2 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                c.sendMessage("Where do you want to move?", playerManager.getCurrentPlayer().getName());
                c.askFor(game.getGameField().getAvailablePlatforms(playerManager.getCurrentPlayer().getCurrentPlatform(), 2), "position");

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

        Effect eff3 = new Effect(new AmmoCube[]{AmmoCube.BLUE}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();

                //Deal 1 additional damage to your target
                damages.put(game.getPlayer(getLastEffectTargets().get(0)), 1);
                playerManager.addDamage(damages);
                usableEffects[2] = false;

                playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().removeAmmos(this.getCost());
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(null);
            }
        };

        usableEffects[0] = true;
        usableEffects[1] = true;
        usableEffects[2] = false;

        eff1.setMaxTargets(1);
        eff2.setMaxTargets(1);
        eff3.setMaxTargets(1);

        getEffects().add(eff1);
        getEffects().add(eff2);
        getEffects().add(eff3);
    }

    @Override
    public void reload() {
        super.reload();
        usableEffects[0] = true;
        usableEffects[1] = true;
        usableEffects[2] = false;
    }
}