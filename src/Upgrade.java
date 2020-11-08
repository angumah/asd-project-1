public class Upgrade {
    private static int cost;
    private static double pointIncrease;

    public Upgrade(){
        this.cost = 1000;
        this.pointIncrease = 0.0;
    }

    public static int getCost(){
        return cost;
    }

    public static int getPercentIncrease(){
        int percentIncrease = (int)(pointIncrease*100);
        return percentIncrease;
    }
    public static void setPointIncrease(){
        pointIncrease +=0.10;
    }

    public static void setCost(){
        cost *= 2;
    }

    public static int newPointTotal(int points){
        points = (int)((1+pointIncrease) * points);
        return points;
    }
}
