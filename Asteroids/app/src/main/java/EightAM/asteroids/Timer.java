package EightAM.asteroids;

public class Timer {
    long target;
    long start;
    long curr;
    boolean reachedTarget = false;
    private Direction direction;

    Timer(long target, long start) {
        resetTimer(target, start);
    }

    Timer(long target) {
        this(target, 0);
    }

    Timer(Timer timer) {
        this.target = timer.target;
        this.start = timer.start;
        this.curr = timer.curr;
        this.reachedTarget = timer.reachedTarget;
        this.direction = timer.direction;
    }

    long remaining() {
        if (reachedTarget) return 0;
        return Math.abs(target - curr);
    }

    long total() {
        return Math.abs(target - start);
    }

    void reset() {
        curr = start;
        reachedTarget = false;
    }

    void resetTimer(long target) {
        resetTimer(target, 0);
    }

    void resetTimer(long target, long start) {
        this.target = target;
        this.start = start;
        this.curr = start;
        if (target >= start) {
            direction = Direction.Up;
        } else {
            direction = Direction.Down;
        }
        reachedTarget = target == start;
    }

    boolean update(long updateTicks) {
        this.curr += updateTicks;
        if (direction == Direction.Up) {
            if (curr >= target) {
                reachedTarget = true;
                return true;
            } else {
                reachedTarget = false;
            }
        } else {
            if (curr <= target) {
                reachedTarget = true;
                return true;
            } else {
                reachedTarget = false;
            }
        }
        return false;
    }

    private enum Direction {
        Up, Down
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " target: " + target + ", curr: " + curr + ", start: "
                + start + ", reachedTarget: " + reachedTarget + ", direction: " + direction;
    }
}
