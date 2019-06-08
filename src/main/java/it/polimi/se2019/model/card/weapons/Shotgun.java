package it.polimi.se2019.model.card.weapons;


import it.polimi.se2019.model.enumeration.AmmoCube;

public final class Shotgun extends WeaponCard {

    public Shotgun(AmmoCube paidCost, AmmoCube[] extraCost) {
      /*  super(paidCost, extraCost);
        Effect eff1 = new Effect(new AmmoCube[]{});
        Effect eff2 = new Effect(new AmmoCube[]{AmmoCube.RED, AmmoCube.BLUE});
        PlayerManager playerManager = Controller.getInstance().getPlayerManager();
        Game game = Controller.getInstance().getGame();
        List<Character> targets = playerManager.getCurrentPlayer().getCurrentPlatform().getPlayersOnThePlatform();
        Map<Player, Integer> damages = new HashMap<>();

        Action be1 = c -> {
            Platform currentPlatform = c.getPlayerManager().getCurrentPlayer().getCurrentPlatform();
            c.askFor(currentPlatform.getPlayersOnThePlatform(), "targets");
            Map<Player, Integer> damageMap = new HashMap<>();

            try {
                damageMap.put(c.getCurrentTargets().take(), 3);
            }catch(Exception e){
                HandyFunctions.LOGGER.log(Level.WARNING, e.getMessage());
            }
            c.getPlayerManager().addDamage(damageMap);
        };

        eff1.addBasicEffect(be1);

        addEffect(eff1);*/
        //TODO
    }

}