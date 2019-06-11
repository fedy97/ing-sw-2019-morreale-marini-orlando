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

public final class MachineGun extends WeaponAlternativeFire {

    public MachineGun(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);
        PlayerManager playerManager = Controller.getInstance().getPlayerManager();
        Game game = Controller.getInstance().getGame();

        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();

                //Chose one or two targets you can see and deal 1 damage to each
                for (Character character : chosenTargets)
                    damages.put(game.getPlayer(character), 1);
                playerManager.addDamage(damages);

                usableEffects = new boolean[]{false, true, true};

                //setting targets for the next additional effect
                getEffects().get(1).setPossibleTargets(chosenTargets);
            }


            @Override
            public void setupTargets() {
                this.setPossibleTargets(game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform()));
            }
        };

        Effect eff2 = new Effect(new AmmoCube[]{AmmoCube.YELLOW}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();

                //Deal 1 additional damage to one of those targets
                damages.put(game.getPlayer(targets.get(0)), 1);
                playerManager.addDamage(damages);

                usableEffects[1] = false;
                playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().removeAmmos(this.getCost());

                //setting targets for the next additional effect
                List<Character> targetsForEff3 = new ArrayList<>(game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform()));
                targetsForEff3.remove(targets.get(0));

                getEffects().get(2).setPossibleTargets(targetsForEff3);
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform()));
            }
        };

        Effect eff3 = new Effect(new AmmoCube[]{AmmoCube.BLUE}) {
            @Override
            public void activateEffect(List<Character> targets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();
                damages.put(game.getPlayer(targets.get(0)), 1);
                playerManager.addDamage(damages);

                usableEffects[2] = false;
                playerManager.getCurrentPlayer().getPlayerBoard().getAmmoBox().removeAmmos(this.getCost());
            }

            @Override
            public void setupTargets() {
                this.setPossibleTargets(game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform()));
            }
        };

        availableEffects = new boolean[]{true, true, true};
        usableEffects = new boolean[]{true, false, false};

        eff1.setMaxTargets(2);
        eff2.setMaxTargets(1);
        eff3.setMaxTargets(1);

        getEffects().add(eff1);
        getEffects().add(eff2);
        getEffects().add(eff3);
    }

    @Override
    public void reload() {
        cleanCache();
        usableEffects = new boolean[]{true, false, false};
        loaded = true;
    }
}