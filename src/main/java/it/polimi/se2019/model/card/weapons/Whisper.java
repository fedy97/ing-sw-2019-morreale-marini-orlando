package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.exceptions.InvalidNameException;
import it.polimi.se2019.model.Game;
import it.polimi.se2019.model.board.Platform;
import it.polimi.se2019.model.enumeration.AmmoCube;
import it.polimi.se2019.model.enumeration.Character;
import it.polimi.se2019.model.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Whisper extends WeaponAlternativeFire {

    public Whisper(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);
        PlayerManager playerManager = Controller.getInstance().getPlayerManager();
        Game game = Controller.getInstance().getGame();

        Effect eff1 = new Effect(new AmmoCube[]{}) {
            @Override
            public void activateEffect(List<Character> chosenTargets, WeaponCard card) {
                Map<Player, Integer> damages = new HashMap<>();
                playerManager.mark(game.getPlayer(chosenTargets.get(0)), 1);
                damages.put(game.getPlayer(chosenTargets.get(0)), 3);
                playerManager.addDamage(damages);

                usableEffects[0] = false;
            }

            @Override
            public void setupTargets() {
                List<Character> targets = game.getGameField().getVisiblePlayers(playerManager.getCurrentPlayer().getCurrentPlatform());
                for (Platform p : game.getGameField().getAvailablePlatforms(playerManager.getCurrentPlayer().getCurrentPlatform(), 1))
                    targets.removeAll(p.getPlayersOnThePlatform());
                this.setPossibleTargets(targets);
            }
        };

        usableEffects = new boolean[]{true, false, false};

        eff1.setMaxTargets(1);
        getEffects().add(eff1);
    }

    @Override
    public void reload() {
        cleanCache();
        usableEffects = new boolean[]{true, false, false};
        loaded = true;
    }
}