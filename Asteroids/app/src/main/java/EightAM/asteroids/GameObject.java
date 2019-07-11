package EightAM.asteroids;

import android.graphics.RectF;

abstract class GameObject {
    float velX;
    float velY;
    float posX;
    float posY;
    float delX;
    float delY;

    private RectF hitbox;   // manipulate shape using set()

    /**
     * Sets the position of the object
     *
     * @param x - horizontal spawn position for object
     * @param y - vertical spawn position for object
     */
    protected void spawn(float x, float y) {
        this.posX = x;
        this.posY = y;
    }

    /**
     * Move an object according to their velocity
     *
     * If the object hits the edge of space (the screen)
     * wrap around
     *
     * @param spaceWidth - width of space (canvas)
     * @param spaceHeight - height of space (canvas)
     */
    protected void move(int spaceWidth, int spaceHeight){
        // Move the object according to its velocity
        this.posX += this.velX;
        this.posY += this.velY;

        // Wrap around screen
        if (this.posX < 0){
            this.posX += (float) spaceWidth;
        }
        else if (this.posX > (float) spaceWidth) {
            this.posX -= (float) spaceWidth;
        }

        if (this.posY < 0){
            this.posY += (float) spaceHeight;
        }
        else if (this.posY > (float) spaceHeight) {
            this.posY -= (float) spaceHeight;
        }
    }

    /**
     * Use canvas and paint to draw objects
     */
    abstract protected void draw();
    
    /**
     * Possibly take in 2 RectF objects
     * Check intersection of hitboxes
     */
    abstract protected void collision();
}