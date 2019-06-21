package it.polimi.se2019.model.rep;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public final class BoardRep implements Serializable {
    private List<String> damages;
    private List<String> marks;
    private Map<String,Integer> colorQtyAmmos;


    public BoardRep(List<String> damages, List<String> marks, Map<String,Integer> colorQtyAmmos) {
        this.damages = damages;
        this.marks = marks;
        this.colorQtyAmmos = colorQtyAmmos;
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
}
