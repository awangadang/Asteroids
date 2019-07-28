package EightAM.asteroids.specs;

import android.graphics.Point;
import android.util.Pair;

import EightAM.asteroids.R;

public class SmallAlienSpec extends BaseAlienSpec {
    public static String tag = "big_alien";
    public static String bitMapName = "big_alien";
    public static int resID = R.drawable.ic_small_alien;
    public static Point dimensions = new Point(20, 20);
    public static float dbmRatio = 2f;
    public static String paintName = "alien";
    public static int pointValue = 10;
    public static int hitPoints = 1;
    public static float reloadTime = 30;
    public static float maxSpeed = 3f;

    public Pair<Integer, Integer> turnDelayRange = new Pair<>(3000, 4000);
    public Pair<Integer, Integer> shotDelayRange = new Pair<>(2000, 3000);

    public SmallAlienSpec() {
        super(tag, bitMapName, resID, dimensions, dbmRatio, paintName);
        setHitPoints(hitPoints);
        setMaxSpeed(maxSpeed);
        setPointValue(pointValue);
        setReloadTime(reloadTime);
    }
}