package EightAM.asteroids;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

class GameView extends SurfaceView implements Runnable {

    // ---------------Member variables-------------

    //for sounds
   // public AudioUtility audio;
    private Paint defaultPaint;
    private SurfaceHolder surfaceHolder;
    private boolean isRunning;
    private Thread thread;
    private GameModel model;
    private Canvas canvas;

    // ---------------Member methods---------------

    /**
     * Constructors
     */
    GameView(Context ctx) {
        this(ctx, null);
    }

    GameView(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        init();
    }

    GameView(Context ctx, AttributeSet attrs, int defStyleAttrs) {
        super(ctx, attrs, defStyleAttrs);
        init();
    }

    void init() {
        surfaceHolder = getHolder();
        int colorPrimary = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        int colorAccent = ContextCompat.getColor(getContext(), R.color.colorAccent);
        defaultPaint = new Paint();
        defaultPaint.setColor(colorPrimary);
        defaultPaint.setStyle(Paint.Style.FILL);
        defaultPaint.setAntiAlias(true);

        // For audio
        //audio = new AudioUtility(getContext());
    }

    @Override
    public void run() {
        while (isRunning) {

            if (surfaceHolder.getSurface().isValid()) {
                canvas = surfaceHolder.lockCanvas();
                if (canvas == null) return;
                canvas.drawColor(Color.BLACK);
                model.getLock().lock();
                try {
                    // Drawings
                    drawObjects(canvas);
                    Messages.draw(canvas);

                    // Drawing hub
                    model.stats.drawAttributes(canvas, defaultPaint, getContext());

                    // Sound effects
//                    this.audio.playInputPress(InputControl.playerInput.UP, InputControl.playerInput.DOWN, InputControl.playerInput.LEFT, InputControl.playerInput.RIGHT,
//                                              InputControl.playerInput.SHOOT);
                } finally {
                    model.getLock().unlock();
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    void drawObjects(Canvas canvas) {
        for (GameObject object : model.objectMap.values()) object.draw(canvas);
    }


    /**
     * onPause stop the thread which controls when the run method execute
     */
    void onPause() {
        if (thread == null) return;
        pause();
        thread = null;
    }

    /**
     * onResume stop the thread which controls when the run method execute
     */
    void onResume() {
        if (thread != null) onPause();
        resume();
    }

    void pause() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // debugging code
            Log.e("Exception", "onPause()" + e.getMessage());
        }
    }

    void resume() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    void setGameModel(GameModel gameModel) {
        model = gameModel;
    }
}
