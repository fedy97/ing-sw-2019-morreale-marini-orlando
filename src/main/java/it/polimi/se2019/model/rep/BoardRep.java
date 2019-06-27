package it.polimi.se2019.model.rep;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public final class BoardRep implements Serializable {
    private List<String> damages;
    private List<String> marks;
    private Map<String, Integer> colorQtyAmmos;
    private int points;
    private int actionType;

    public BoardRep(List<String> damages, List<String> marks, Map<String, Integer> colorQtyAmmos, int points, int actionType) {
        this.damages = damages;
        this.marks = marks;
        this.colorQtyAmmos = colorQtyAmmos;
        this.points = points;
        this.actionType = actionType;
    }

    public List<String> getDamages() {
        return damages;
    }

    public List<String> getMarks() {
        return marks;
    }

    public Map<String, Integer> getColorQtyAmmos() {
        return colorQtyAmmos;
    }

    public int getPoints() {
        return points;
    }

    public int getActionType() {
        return actionType;
    }
}
