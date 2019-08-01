package EightAM.asteroids;

import android.graphics.Point;
import android.graphics.RectF;

class Boundaries {
    /**
     * @return a Point within the spawnBoundaries but now inside boundaries
     */
    static Point getRandomPosition(RectF boundaries, RectF spawnBoundaries) {
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
}
