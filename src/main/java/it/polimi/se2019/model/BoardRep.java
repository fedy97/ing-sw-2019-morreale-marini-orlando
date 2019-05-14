package it.polimi.se2019.model;

import java.io.Serializable;
import java.util.List;

public class BoardRep implements Serializable {
    private List<String> damages;
    private List<String> marks;

    public List<String> getDamages() {
        return damages;
    }

    public void setDamages(List<String> damages) {
        this.damages = damages;
    }

    public List<String> getMarks() {
        return marks;
    }

    public void setMarks(List<String> marks) {
        this.marks = marks;
    }
}
