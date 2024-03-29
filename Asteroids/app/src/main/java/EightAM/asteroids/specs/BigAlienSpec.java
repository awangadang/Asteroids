package EightAM.asteroids.specs;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Pair;

import EightAM.asteroids.R;
import EightAM.asteroids.Rotation;
import EightAM.asteroids.Velocity;

public class BigAlienSpec extends BaseAlienSpec implements AudioSpec {
    public static int pointValue = 50;
    public static int hitPoints = 1;
    public static float maxSpeed = 0.20f;
    public static float initialAngle = (float) Math.PI * 0.25f;
    public static String tag = "alien_big";
    public static Point dimensions = new Point(65, 65);
    public static Point initialPosition = new Point(0, 0);
    public static Velocity initialVelocity = new Velocity(0, 0, maxSpeed);
    public static Rotation initialRotation = new Rotation(initialAngle, 0);

    public static float accuracy = 50;

    public static int paintColor = Color.BLUE;
    public static Paint.Style paintStyle = Paint.Style.FILL;

    public static int resID = R.drawable.ic_big_alien;
    public static float dbmRatio = 2f;

    public static Pair<Integer, Integer> _turnDelayRange = new Pair<>(2000, 3500);
    public static Pair<Integer, Integer> _shotDelayRange = new Pair<>(1000, 2000);

    // sound resIDs
    public static int explosion = R.raw.alien_explosion;

    public static BaseWeaponSpec weaponSpec = new BigAlienWeaponSpec();

    public Pair<Integer, Integer> turnDelayRange;
    public Pair<Integer, Integer> shotDelayRange;

    public BigAlienSpec(String tag, Point dimensions, Point initialPosition,
            Velocity initialVelocity, Rotation initialRotation, int bitMapResourceID,
            float dimensionBitMapRatio,
            int pointValue, int hitPoints, BaseWeaponSpec weaponSpec,
            Pair<Integer, Integer> turnDelayRange,
            Pair<Integer, Integer> shotDelayRange) {
        super(tag, dimensions, initialPosition, initialVelocity, initialRotation, bitMapResourceID,
                dimensionBitMapRatio, pointValue, hitPoints, weaponSpec);
        this.turnDelayRange = turnDelayRange;
        this.shotDelayRange = shotDelayRange;
    }

    public BigAlienSpec() {
        this(tag, dimensions, initialPosition, initialVelocity, initialRotation, resID, dbmRatio,
                pointValue, hitPoints, weaponSpec, _turnDelayRange, _shotDelayRange);
        setLootOnDeath(new RandomLootSpec());
        setExplosionID(explosion);
    }

    @Override
    public String getTag() {
        return tag;
    }
}
