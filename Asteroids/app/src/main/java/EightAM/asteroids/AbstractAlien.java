package EightAM.asteroids;

import android.graphics.Bitmap;
import android.graphics.Point;

import EightAM.asteroids.interfaces.AIModule;
import EightAM.asteroids.interfaces.AudioGenerator;
import EightAM.asteroids.interfaces.AudioListener;
import EightAM.asteroids.interfaces.Collision;
import EightAM.asteroids.interfaces.GameState;
import EightAM.asteroids.specs.BaseAlienSpec;

public abstract class AbstractAlien extends GameObject implements Collision, AIModule,
        AudioGenerator {
    int pointValue;
    int hitPoints;
    Bitmap bitmap;
    float dbmRatio;
    Point lastKnownPlayerPos;

    // sound IDs
    int explosionID;
    private AudioListener audioListener;

    AbstractAlien(BaseAlienSpec spec) {
        super(spec);
        this.id = ObjectID.getNewID(Faction.Alien);

        this.bitmap = BitmapStore.getInstance().getBitmap(spec.tag);
        this.dbmRatio = spec.dimensionBitMapRatio;

        // alien spec
        this.pointValue = spec.pointValue;
        this.hitPoints = spec.hitPoints;
        this.explosionID = spec.explosionID;

        lastKnownPlayerPos = new Point(0, 0);
    }

    AbstractAlien(AbstractAlien alien) {
        super(alien);
        this.id = ObjectID.getNewID(Faction.Alien);

        this.bitmap = alien.bitmap;
        this.dbmRatio = alien.dbmRatio;

        this.pointValue = alien.pointValue;
        this.hitPoints = alien.hitPoints;
        this.explosionID = alien.explosionID;

        lastKnownPlayerPos = new Point(0, 0);
    }

    // AIModule methods

    /**
     * Default AI that records the player's last position on the map
     */
    @Override
    public void processGameState(GameState state) {
        if(state.getPlayerShip() != null)lastKnownPlayerPos = state.getPlayerShip().getObjPos();
    }

    // Collision methods

    /**
     * Collision detection method takes in the hitbox of approaching object, using intersection
     * method to check of collision
     *
     * @param approachingObject the hitbox of approaching object,
     * @return true for collision, otherwise false
     */
    @Override
    public boolean detectCollisions(GameObject approachingObject) {
        return hitbox.intersect(approachingObject.hitbox);
    }

    @Override
    public void onCollide(GameObject approachingObject) {
        boolean destroyThis = false;
        if (approachingObject instanceof Bullet) {
            hitPoints -= ((Bullet) approachingObject).damage;
            if (hitPoints <= 0) {
                destroyThis = true;
            }
        } else {
            destroyThis = true;
        }
        if (destroyThis) {
            destruct(new DestroyedObject(pointValue, id, approachingObject.id, this));
        }
    }

    @Override
    public boolean canCollide() {
        return true;
    }

    @Override
    public void registerAudioListener(AudioListener listener) {
        this.audioListener = listener;
    }

    @Override
    public void destruct(DestroyedObject destroyedObject) {
        super.destruct(destroyedObject);
        audioListener.sendSoundCommand(explosionID);
    }
}
