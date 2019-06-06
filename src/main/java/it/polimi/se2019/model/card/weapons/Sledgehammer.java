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

public final class Sledgehammer extends WeaponAlternativeFire {


    public Sledgehammer(String name, String descr, String img, AmmoCube paidCost, AmmoCube[] extraCost) throws InvalidNameException {
        super(name, descr, img, paidCost, extraCost);
        Effect eff1 = new Effect(new AmmoCube[]{});
        Effect eff2 = new Effect(new AmmoCube[]{AmmoCube.RED, AmmoCube.BLUE});
        PlayerManager playerManager = Controller.getInstance().getPlayerManager();
        Game game = Controller.getInstance().getGame();

        Map<Player, Integer> damages = new HashMap<>();
        BasicEffect be1 = payload -> {
            List<Character> targets = (List<Character>) payload;
            for(Character target: targets)
                damages.put(game.getPlayer(target), 1);
            playerManager.addDamage(damages);
        };
        eff1.addBasicEffect(be1);



        BasicEffect be2 = payload -> {
            List<Character> targets = (List<Character>) payload;
            for(Character target: targets)
                damages.put(game.getPlayer(target), 1);
            playerManager.addDamage(damages);
        };
        eff2.addBasicEffect(be2);

        setBasicEffect(eff1);
        setAlternativeEffect(eff2);
    }

}