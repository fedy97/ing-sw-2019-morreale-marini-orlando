package it.polimi.se2019.controller;

public enum UserValidActions {
    ALL(new boolean[]{true, true, true, true, true, true, true}),
    NONE(new boolean[]{false, false, false, false, false, false, false}),
    NO_BASIC(new boolean[]{false, false, false, true, true, true, true});

    private boolean[] actions;

    UserValidActions(boolean[] actions) {
        this.actions = actions;
    }

    public boolean[] getActions() {
        return actions;
    }
}
