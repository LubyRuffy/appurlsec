package qsbk.app.widget.imageview;

public class Animator extends Thread {
    private boolean active;
    private Animation animation;
    private long lastTime;
    private boolean running;
    private GestureImageView view;

    public Animator(GestureImageView r3_GestureImageView, String r4_String) {
        super(r4_String);
        this.running = false;
        this.active = false;
        this.lastTime = -1;
        this.view = r3_GestureImageView;
    }

    public synchronized void activate() {
        this.lastTime = System.currentTimeMillis();
        this.active = true;
        notifyAll();
    }

    public void cancel() {
        this.active = false;
    }

    public synchronized void finish() {
        this.running = false;
        this.active = false;
        notifyAll();
    }

    public void play(Animation r2_Animation) {
        if (this.active) {
            cancel();
        }
        this.animation = r2_Animation;
        activate();
    }

    public void run() {
        this.running = true;
        while (this.running) {
            while (this.active && this.animation != null) {
                long r0j = System.currentTimeMillis();
                this.active = this.animation.update(this.view, r0j - this.lastTime);
                this.view.redraw();
                this.lastTime = r0j;
                while (this.active) {
                    try {
                        if (this.view.waitForDraw(32)) {
                            break;
                        }
                    } catch (InterruptedException e) {
                        this.active = false;
                    }
                }
            }
            synchronized (this) {
                if (this.running) {
                    try {
                        wait();
                    } catch (InterruptedException e_2) {
                    }
                }
            }
        }
    }
}