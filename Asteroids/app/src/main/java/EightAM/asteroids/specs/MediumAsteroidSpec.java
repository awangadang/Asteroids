package EightAM.asteroids.specs;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Pair;

import EightAM.asteroids.R;
import EightAM.asteroids.Rotation;
import EightAM.asteroids.Velocity;

public class MediumAsteroidSpec extends BaseAsteroidSpec {
    public static Pair<Float, Float> speedRange = new Pair<>(0.02f, 0.2f);
    public static Pair<Float, Float> spinRange = new Pair<>(0f, 0.01f);
    public static BaseAsteroidSpec breaksInto = new SmallAsteroidSpec();
    public static int breakCount = 2;

    public static String tag = "asteroid_medium";
    public static Point dimensions = new Point(68, 68);
    public static Point initialPosition = new Point(0, 0);
    public static Velocity initialVelocity = new Velocity(0, 0, speedRange.second);
    public static Rotation initialRotation = new Rotation(0, 0);

    public static int paintColor = Color.BLUE;
    public static Paint.Style paintStyle = Paint.Style.FILL;

    public static int resID = R.drawable.asteroid_medium;
    public static float dbmRatio = 2f;

    public static int pointValue = 15;
    public static int hitPoints = 1;

    public MediumAsteroidSpec(String tag, Point dimensions, Point initialPosition,
            Velocity initialVelocity, Rotation initialRotation, int bitMapResourceID,
            float dimensionBitMapRatio,
            Pair<Float, Float> speedRange,
            Pair<Float, Float> spinSpeedRange, BaseAsteroidSpec breaksInto, int breakCount,
            int pointValue, int hitPoints) {
        super(tag, dimensions, initialPosition, initialVelocity, initialRotation, bitMapResourceID,
                dimensionBitMapRatio, speedRange, spinSpeedRange, breaksInto, breakCount,
                pointValue, hitPoints);
    }

    public MediumAsteroidSpec() {
        this(tag, dimensions, initialPosition, initialVelocity, initialRotation, resID, dbmRatio,
                speedRange, spinRange, breaksInto, breakCount, pointValue, hitPoints);
    }
}
