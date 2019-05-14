package it.polimi.se2019.model;

import java.io.Serializable;

public final class CardRep implements Serializable {
    private int id;
    private String title;
    private String description;
    private String path;

    public CardRep(int id, String title, String description, String path){
        this.id = id;
        this.title = title;
        this.description = description;
        this.path = path;
    }
}
