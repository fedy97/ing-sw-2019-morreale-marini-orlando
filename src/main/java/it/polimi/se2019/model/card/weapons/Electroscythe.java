package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Electroscythe extends WeaponAlternativeFire {

    public Electroscythe(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);
        PlayerManager playerManager = Controller.getInstance().getPlayerManager();
        Game game = Controller.getInstance().getGame();


        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();
                for (Character character : playerManager.getCurrentPlayer().getCurrentPlatform().getPlayersOnThePlatform())
                    damages.put(game.getPlayer(character), 1);
                playerManager.addDamage(damages);

                usableEffects = new boolean[]{false, true, true};
                playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().removeAmmos(this.getCost());
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(null);
            }
        };

        Effect eff2 = new Effect(new AmmoCube[]{AmmoCube.RED, AmmoCube.BLUE}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();
                for (Character character : playerManager.getCurrentPlayer().getCurrentPlatform().getPlayersOnThePlatform())
                    damages.put(game.getPlayer(character), 2);
                playerManager.addDamage(damages);

                usableEffects = new boolean[]{false, false, true};
                playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().removeAmmos(this.getCost());
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(null);
            }
        };

        availableEffects = new boolean[]{true, true, false};
        usableEffects = new boolean[]{true, true, true};

        getEffects().add(eff1);
        getEffects().add(eff2);
    }

    @Override
    public void reload() {
        cleanCache();
        usableEffects = new boolean[]{true, true, true};
        loaded = true;
    }
}