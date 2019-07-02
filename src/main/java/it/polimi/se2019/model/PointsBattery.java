package it.polimi.se2019.model;

public final class PointsBattery {
    private static int[] normalPointsValue = new int[]{8, 6, 4, 2, 1};

    private static int[] oneKillPointsValue = new int[]{6, 4, 2, 1};

    private static int[] twoKillsPointsValue = new int[]{4, 2, 1, 1};

    private static int[] threeKillsPointsValue = new int[]{2, 1, 1, 1};

    private static int[] maknyKillsPointsValue = new int[]{1, 1, 1, 1};

    private PointsBattery() {
    }

    public static int[] getPointsValue(int killNum) {
        int[] res = null;

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
                res = maknyKillsPointsValue;
        }

        return res;
    }

    public static int[] getFinalPointValue() {
        return normalPointsValue;
    }
}
