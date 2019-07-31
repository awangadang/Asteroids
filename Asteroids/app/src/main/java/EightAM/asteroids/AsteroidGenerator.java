package EightAM.asteroids;

import android.graphics.Point;
import android.graphics.RectF;

import androidx.collection.ArraySet;

import java.util.Collection;

import EightAM.asteroids.specs.LargeAsteroidSpec;

public class AsteroidGenerator extends CollidableObjectGenerator {
    private AsteroidGenerator() {
    }

    /**
     * Helper Function to init a asteroid position away from the player
     * Such that an asteroid does not unfairly spawn on top of a player
     *
     * @return a Valid random point
     */
    private static Point getRandomPosition(RectF boundaries, RectF spawnBoundaries) {
        assert spawnBoundaries.contains(boundaries);
        float leftDiff = Math.abs(spawnBoundaries.left - boundaries.left);
        float rightDiff = Math.abs(spawnBoundaries.right - boundaries.right);
        float topDiff = Math.abs(spawnBoundaries.top - boundaries.top);
        float botDiff = Math.abs(spawnBoundaries.bottom - boundaries.bottom);
        Point ret = new Point();
        if (Math.random() < leftDiff / (leftDiff + rightDiff)) {
            // left side
            ret.x = (int) (boundaries.left - Math.random() * leftDiff);
        } else {
            ret.x = (int) (boundaries.right + Math.random() * rightDiff);
        }
        if (Math.random() < topDiff / (topDiff + botDiff)) {
            // top side
            ret.y = (int) (boundaries.top - Math.random() * topDiff);
        } else {
            ret.y = (int) (boundaries.bottom + Math.random() * botDiff);
        }
        return ret;
    }

    /**
     * Creates an asteroid belt and setting a random position for individual asteroids
     *
     * @return a collection of asteroids
     */
    public static Collection<GameObject> createBelt(RectF boundaries, RectF spawnBoundaries,
            int numOfAsteroids) {
        Point randPoint;
        Collection<GameObject> asteroidBelt = new ArraySet<>();
        for (int i = 0; i < numOfAsteroids; i++) {
            GameObject asteroid = BaseFactory.getInstance().create(new LargeAsteroidSpec());
            randPoint = getRandomPosition(boundaries, spawnBoundaries);
            asteroid.hitbox.offsetTo(randPoint.x, randPoint.y);
            asteroidBelt.add(asteroid);
        }
        return asteroidBelt;
    }

    /**
     * Spawns asteroids in the place of its parent, i.e. upon breaking up
     *
     * @param parentAsteroid - reference to parent asteroid to retrieve position
     * @return a collection of baby asteroids
     */
    public static Collection<GameObject> breakUpAsteroid(Asteroid parentAsteroid) {
        Collection<GameObject> babyAsteroids = new ArraySet<>();
        if (parentAsteroid.breaksInto != null) {
            for (int i = 0; i < parentAsteroid.breakCount; i++) {
                GameObject asteroid = BaseFactory.getInstance().create(parentAsteroid.breaksInto);
                asteroid.hitbox.offsetTo(parentAsteroid.getObjPos().x,
                        parentAsteroid.getObjPos().y);
                babyAsteroids.add(asteroid);
            }
        }
        return babyAsteroids;
    }
}
