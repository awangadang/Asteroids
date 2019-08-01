package EightAM.asteroids.specs;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import EightAM.asteroids.R;
import EightAM.asteroids.Rotation;
import EightAM.asteroids.Velocity;

public class RandomLootSpec extends BaseLootSpec {
    public static float randomAcceleration = 0.0002f;
    public static long durationMS = 10000;
    public static float maxSpeed = 0.8f;

    public static String tag = "powerup_random";
    public static Point dimensions = new Point(50, 50);
    public static Point initialPosition = new Point(0, 0);
    public static Velocity initialVelocity = new Velocity(0, 0, maxSpeed);
    public static Rotation initialRotation = new Rotation((float) Math.PI * 3f / 2, 0);

    public static int resID = R.drawable.power_up;
    public static float dbmRatio = 2f;

    public static int paintColor = Color.WHITE;
    public static Paint.Style paintStyle = Paint.Style.FILL;

    public RandomLootSpec(String tag, Point dimensions,
            Point initialPosition, Velocity initialVelocity,
            Rotation initialRotation, int bitMapResourceID,
            float dimensionBitMapRatio, float randomAcceleration, long durationMS) {
        super(tag, dimensions, initialPosition, initialVelocity, initialRotation, bitMapResourceID,
                dimensionBitMapRatio, randomAcceleration, durationMS);
    }

    public RandomLootSpec() {
        this(tag, dimensions, initialPosition, initialVelocity, initialRotation, resID, dbmRatio,
                randomAcceleration, durationMS);
    }
}