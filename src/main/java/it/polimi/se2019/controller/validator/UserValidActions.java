package it.polimi.se2019.controller.validator;

public enum UserValidActions {

    // action 1 - action 2 - action 3 - reload - endturn - powerups - convert
    ALL(new boolean[]{true, true, true, true, true, true, true}),
    NONE(new boolean[]{false, false, false, false, false, false, false}),
    NO_BASIC(new boolean[]{false, false, false, true, true, true, true}),
    NO_SHOOT(new boolean[]{true, true, false, true, true, true, true}),
    FRENZY_2(new boolean[]{true, true, false, true, true, true, true}),
    ONLY_END(new boolean[]{false, false, false, false, true, true, false});

    private boolean[] actions;

    UserValidActions(boolean[] actions) {
        this.actions = actions;
    }

    public boolean[] getActions() {
        return actions;
    }
}
