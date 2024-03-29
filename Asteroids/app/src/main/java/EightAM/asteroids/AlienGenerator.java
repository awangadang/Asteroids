package EightAM.asteroids;

import android.graphics.Point;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import EightAM.asteroids.specs.BaseAlienSpec;
import EightAM.asteroids.specs.BigAlienSpec;
import EightAM.asteroids.specs.KamikazeAlienSpec;
import EightAM.asteroids.specs.SmallAlienSpec;

public class AlienGenerator {
    /**
     * Makes an alien and puts it into model's objectMap.
     *
     * @param spaceSize the space size
     */
    public static Collection<GameObject> createAlien(Point spaceSize) {
        BaseAlienSpec spec = getAlienSpec();

        return Collections.singleton(prepareAlien(spec, spaceSize));
    }

    private static GameObject prepareAlien(BaseAlienSpec spec, Point spaceSize) {
        // change initial position in alien spec
        Point origin = getRandomPosition(spaceSize);
        spec.initialPosition = origin;

        GameObject alien = BaseFactory.getInstance().create(spec);
        alien.hitbox.offsetTo(origin.x, origin.y);
        alien.vel.resetVelocity(alien.vel.maxSpeed, 0, alien.vel.maxSpeed);
        if (origin.x == spaceSize.x) alien.vel.x *= -1;

        return alien;
    }

    /**
     * Generates what kind of Alien Spec to use based on probability.
     * Also determines spawn position.
     *
     * @return some Alien Spec
     */
    private static BaseAlienSpec getAlienSpec() {
        float f = new Random().nextFloat();
        float num = 0f;
        for (Aliens e : Aliens.values()) {
            if (e.probOfSpawning + num < f) {
                num += e.probOfSpawning;
            } else {
                switch (e) {
                    case Small:
                        return new SmallAlienSpec();
                    case Big:
                        return new BigAlienSpec();
                    case KamiKaze:
                        return new KamikazeAlienSpec(); // testing
                }
            }
        }
        return new BigAlienSpec(); // default alien to generate
    }

    /**
     * Generates a Point that has a random y position and an x that is either
     * on the left or on the right.
     *
     * @param spaceSize a Point representing the size of space/canvas
     * @return a Point
     */
    private static Point getRandomPosition(Point spaceSize) {
        Random rand = new Random();
        int randX, randY;

        randX = spaceSize.x * rand.nextInt(2);
        randY = rand.nextInt(((spaceSize.y - 1) + 1) + 1);

        return new Point(randX, randY);
    }


}
