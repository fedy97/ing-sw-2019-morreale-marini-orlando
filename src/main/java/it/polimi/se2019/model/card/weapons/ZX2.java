package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ZX2 extends WeaponAlternativeFire {

    public ZX2(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);
        Controller c = Controller.getInstance();
        PlayerManager playerManager = c.getPlayerManager();
        Game game = Controller.getInstance().getGame();


        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Player target = game.getPlayer(chosenTargets.get(0));

                Map<Player, Integer> damages = new HashMap<>();
                damages.put(target, 11);
                playerManager.addDamage(damages);
                playerManager.mark(target, 2);
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(new ArrayList<>(game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform())));
            }
        };

        Effect eff2 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                for (Character character : chosenTargets) {
                    playerManager.mark(game.getPlayer(character), 1);
                }
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(new ArrayList<>(game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform())));
            }
        };

        eff1.setMaxTargets(1);
        eff2.setMaxTargets(3);

        getEffects().add(eff1);
        getEffects().add(eff2);
    }
}