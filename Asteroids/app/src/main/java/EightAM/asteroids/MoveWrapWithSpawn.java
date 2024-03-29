package EightAM.asteroids;

import android.graphics.RectF;

import EightAM.asteroids.interfaces.MoveStrategy;


/**
 * Behaves like MoveWrap with outerBoundaries, but when object moves inside boundaries, changes to
 * MoveWrap behavior. This is to ensure objects smoothly transition from the outside of the screen
 * to the inside.
 */
class MoveWrapWithSpawn implements MoveStrategy {
    RectF boundaries;
    RectF outerBoundaries;
    float boundaryShrinkRate;

    public MoveWrapWithSpawn(RectF boundaries, RectF outerBoundaries, float boundaryShrinkRate) {
        this.boundaries = boundaries;
        this.outerBoundaries = outerBoundaries;
        this.boundaryShrinkRate = boundaryShrinkRate;
    }

    @Override
    public void move(GameObject o, long deltaTime) {
        float dx = o.vel.x * deltaTime;
        float dy = o.vel.y * deltaTime;
        o.hitbox.offset(dx, dy);
        float cx = o.hitbox.centerX();
        float cy = o.hitbox.centerY();
        // if the center passes the boundary, wrap around the hitbox
        if (cx > outerBoundaries.right) {
            o.hitbox.offset(-outerBoundaries.width(), 0);
        } else if (cx < outerBoundaries.left) {
            o.hitbox.offset(outerBoundaries.width(), 0);
        }
        if (cy > outerBoundaries.bottom) {
            o.hitbox.offset(0, -outerBoundaries.height());
        } else if (cy < 0) {
            o.hitbox.offset(0, outerBoundaries.height());
        }
        //Shrinks the boundaries until objects are within the play area
        if (outerBoundaries.contains(boundaries)) {
            outerBoundaries.inset(outerBoundaries.width() * (boundaryShrinkRate * deltaTime),
                    outerBoundaries.height() * (boundaryShrinkRate * deltaTime));
        }
        if (boundaries.contains(o.hitbox)) {
            o.setMoveStrategy(new MoveWrap(boundaries));
        }
    }
}
