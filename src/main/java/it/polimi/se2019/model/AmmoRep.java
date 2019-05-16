package it.polimi.se2019.model;

import java.io.Serializable;

public final class AmmoRep implements Serializable {
    private int id;
    private String type;

    public AmmoRep(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
