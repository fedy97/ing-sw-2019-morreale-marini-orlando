package it.polimi.se2019.model;

public final class PointsBattery {
    private static int[] normalPointsValue = new int[]{8, 6, 4, 2, 1};

    public static int[] getPointsValue(int killNum) {
        int [] res = null;

        switch(killNum) {
            case 0: res = normalPointsValue;
            break;
        }

        return res;
    }

    public static int[] getFinalPointValue() {
        return normalPointsValue;
    }
}
