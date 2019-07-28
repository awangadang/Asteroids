package EightAM.asteroids;

import java.util.Map;
import android.graphics.Point;
import EightAM.asteroids.specs.BaseParticleSpec;
import static EightAM.asteroids.Constants.EFFECT_NUM;
import static EightAM.asteroids.Constants.EFFECT_RADIUS;

public class ParticleGenerator {
    static ParticleGenerator instance;
    int numOfParticles;

    private ParticleGenerator() {
        this.numOfParticles = EFFECT_NUM;
    }

    static void init() {
        if (instance == null) instance = new ParticleGenerator();
    }

    static ParticleGenerator getInstance() {
        if (instance == null) init();
        return instance;
    }

    public void createParticles(Map<ObjectID, GameObject> objectMap, Point spaceSize, Point objectPos) {
        Point randPoint;
        for (int i = 0; i < numOfParticles; i++) {
            GameObject particle = BaseFactory.getInstance().create(new BaseParticleSpec());
            randPoint = getRandomPosition(spaceSize, objectPos);
            particle.hitbox.offset(randPoint.x, randPoint.y);
            objectMap.put(particle.getID(), particle);
        }
    }

    private Point getRandomPosition(Point size, Point pos) {
        double angle = Math.random() * Math.PI * 2;
        int offSet = size.x - pos.x;
        int x = (int)Math.cos(angle) * EFFECT_RADIUS + offSet;
        int y = (int)Math.sin(angle) * EFFECT_RADIUS + offSet;
        return new Point(x, y);
    }
}