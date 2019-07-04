package it.polimi.se2019.model;

/**
 * Class used to retrieve the amount of points for each state of the
 *
 * @author Gabriel Raul Marinil
 */
public final class PointsBattery {
    private static int[] normalPointsValue = new int[]{8, 6, 4, 2, 1};

    private static int[] oneKillPointsValue = new int[]{6, 4, 2, 1};

    private static int[] twoKillsPointsValue = new int[]{4, 2, 1, 1};

    private static int[] threeKillsPointsValue = new int[]{2, 1, 1, 1};

    private static int[] manyKillsPointsValue = new int[]{1, 1, 1, 1};

    private PointsBattery() {
    }

    /**
     * @param killNum how many times the player was killed
     * @return the points array
     */
    public static int[] getPointsValue(int killNum) {
        int[] res;

        switch (killNum) {
            case 0:
                res = normalPointsValue;
                break;
            case 1:
                res = oneKillPointsValue;
                break;
            case 2:
                res = twoKillsPointsValue;
                break;
            case 3:
                res = threeKillsPointsValue;
                break;
            default:
                res = manyKillsPointsValue;
        }

        return res;
    }

    /**
     * @return the points array for the final counting
     */
    public static int[] getFinalPointValue() {
        return normalPointsValue;
    }
}
