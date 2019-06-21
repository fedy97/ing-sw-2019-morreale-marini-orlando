package it.polimi.se2019.model.rep;

import it.polimi.se2019.model.card.Card;

import java.io.Serializable;

public final class CardRep implements Serializable {
    private int id;
    private String title;
    private String description;
    private String path;
    private boolean loaded;

    public CardRep(Card card) {
        this.id = System.identityHashCode(card);
        this.title = card.getName();
        this.description = card.getDescription();
        this.path = card.getImgPath();
        loaded = false;
    }

    public CardRep(int id, String title, String description, String path) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.path = path;
        loaded = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPath() {
        return path;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
