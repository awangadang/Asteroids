package EightAM.asteroids;

import android.graphics.RectF;
import java.util.Random;

class Rocks extends GameObject{

    /*
    Goals:
    - Spawn on space outskirts
    - rocks generates rocks in a collision
    - rocks float in space
    - rocks collide with everting other object except itself
    - speed: constant and dependent on size

    Interact with:
    Space
    GameObject
     */

    /**
     * Enum to denote 3 size types of asteroid rock
     */
    enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }

    private Size rockSize;
    private int MAXSPEED; //TODO: Decide what MAXSPEED should be and set to static final

    /**
     * This constructor constructs the asteroid rocks when there are no
     * asteroids in space, i.e. when there's a new game or when all
     * asteroids in the field are destroyed.
     *
     * Its velocity and position are random.
     *
     * These asteroids only spawn when the ship/player is a certain
     * distance away from the spawn point.
     *
     * @param xTotalPix - total horizontal pixels
     * @param yTotalPix - total vertical pixels
     */

    protected Rocks(int xTotalPix, int yTotalPix) {
        rockSize = Size.LARGE;
        //TODO: Set RecF to a "Large size" dependent on size of screen
        Random rand = new Random();
        float xRand, yRand;
        /*
         * We only want to spawn asteroids we are a certain distance away from the ship
         * NOTE: May be inefficient
         *
         * Alternative:
         * Only spawn asteroids close to the borders of the screen
         * Alternative Implementation:
         * new Random().nextInt((max-min+1))+min to set bounds
         *
         */
        //TODO: Find a way to get "a certain distance away from the ship"
        //do {
            xRand = (float) rand.nextInt(xTotalPix);
            yRand = (float) rand.nextInt(yTotalPix);
        //} while(a certain distance away from the ship);
        spawn(xRand, yRand);

        this.velX = (float) rand.nextInt(MAXSPEED);
        this.velY = (float) rand.nextInt(MAXSPEED);
    }

    /**
     * This constructor constructs the asteroid rocks when there an
     * asteroid of a Size of LARGE or MEDIUM gets destroyed.
     *
     * These asteroids spawn in the wake of the destruction of its parent
     * i.e. the parent's position.
     *
     * Its velocity are randomized
     *
     * @param currentX - current horizontal position of the parent asteroid
     * @param currentY - current vertical position of the parent asteroid
     * @param parentSize - Size of parent
     */
    protected Rocks(int currentX, int currentY, Size parentSize) {
        if (parentSize == Size.LARGE) {
            rockSize = Size.MEDIUM;
            //TODO: Set RecF to a "Medium size" dependent on size of screen
        }
        else {
            rockSize = Size.SMALL;
            //TODO: Set RecF to a "Small size" dependent on size of screen
        }
        spawn(currentX, currentY);

        Random rand = new Random();

        this.velX = (float) rand.nextInt(MAXSPEED);
        this.velY = (float) rand.nextInt(MAXSPEED);
    }

    protected void draw(){
        //TODO: Draw on canvas dependent on rockSize
    }

    protected void move(/*velocity*/) {

    }

    protected void collision() {

    }
}