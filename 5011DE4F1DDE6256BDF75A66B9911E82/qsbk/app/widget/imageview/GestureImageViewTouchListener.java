package qsbk.app.widget.imageview;

import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class GestureImageViewTouchListener implements OnTouchListener {
    private float boundaryBottom;
    private float boundaryLeft;
    private float boundaryRight;
    private float boundaryTop;
    private boolean canDragX;
    private boolean canDragY;
    private int canvasHeight;
    private int canvasWidth;
    private float centerX;
    private float centerY;
    private final PointF current;
    private float currentScale;
    private int displayHeight;
    private int displayWidth;
    private float fitScaleHorizontal;
    private float fitScaleVertical;
    private FlingAnimation flingAnimation;
    private GestureDetector flingDetector;
    private FlingListener flingListener;
    private GestureImageView image;
    private int imageHeight;
    private GestureImageViewListener imageListener;
    private int imageWidth;
    private boolean inZoom;
    private float initialDistance;
    private final PointF last;
    private float lastScale;
    private float maxScale;
    private final PointF midpoint;
    private float minScale;
    private MoveAnimation moveAnimation;
    private boolean multiTouch;
    private final PointF next;
    private OnClickListener onClickListener;
    private final VectorF pinchVector;
    private final VectorF scaleVector;
    private float startingScale;
    private GestureDetector tapDetector;
    private boolean touched;
    private ZoomAnimation zoomAnimation;

    public GestureImageViewTouchListener(GestureImageView r6_GestureImageView, int r7i, int r8i) {
        this.current = new PointF();
        this.last = new PointF();
        this.next = new PointF();
        this.midpoint = new PointF();
        this.scaleVector = new VectorF();
        this.pinchVector = new VectorF();
        this.touched = false;
        this.inZoom = false;
        this.lastScale = 1.0f;
        this.currentScale = 1.0f;
        this.boundaryLeft = 0.0f;
        this.boundaryTop = 0.0f;
        this.boundaryRight = 0.0f;
        this.boundaryBottom = 0.0f;
        this.maxScale = 5.0f;
        this.minScale = 0.25f;
        this.fitScaleHorizontal = 1.0f;
        this.fitScaleVertical = 1.0f;
        this.canvasWidth = 0;
        this.canvasHeight = 0;
        this.centerX = 0.0f;
        this.centerY = 0.0f;
        this.startingScale = 0.0f;
        this.canDragX = false;
        this.canDragY = false;
        this.multiTouch = false;
        this.image = r6_GestureImageView;
        this.displayWidth = r7i;
        this.displayHeight = r8i;
        this.centerX = ((float) r7i) / 2.0f;
        this.centerY = ((float) r8i) / 2.0f;
        this.imageWidth = r6_GestureImageView.getImageWidth();
        this.imageHeight = r6_GestureImageView.getImageHeight();
        this.startingScale = r6_GestureImageView.getScale();
        this.currentScale = this.startingScale;
        this.lastScale = this.startingScale;
        this.boundaryRight = (float) r7i;
        this.boundaryBottom = (float) r8i;
        this.boundaryLeft = 0.0f;
        this.boundaryTop = 0.0f;
        this.next.x = r6_GestureImageView.getImageX();
        this.next.y = r6_GestureImageView.getImageY();
        this.flingListener = new FlingListener();
        this.flingAnimation = new FlingAnimation();
        this.zoomAnimation = new ZoomAnimation();
        this.moveAnimation = new MoveAnimation();
        this.flingAnimation.setListener(new b(this));
        this.zoomAnimation.setZoom(1.0f);
        this.zoomAnimation.setZoomAnimationListener(new c(this));
        this.moveAnimation.setMoveAnimationListener(new d(this, r6_GestureImageView));
        this.tapDetector = new GestureDetector(r6_GestureImageView.getContext(), new e(this, r6_GestureImageView));
        this.flingDetector = new GestureDetector(r6_GestureImageView.getContext(), this.flingListener);
        this.imageListener = r6_GestureImageView.getGestureImageViewListener();
        calculateBoundaries();
    }

    private void startFling() {
        this.flingAnimation.setVelocityX(this.flingListener.getVelocityX());
        this.flingAnimation.setVelocityY(this.flingListener.getVelocityY());
        this.image.animationStart(this.flingAnimation);
    }

    private void startZoom(MotionEvent r4_MotionEvent) {
        float r0f;
        float r2f = 1.5f;
        this.inZoom = true;
        this.zoomAnimation.reset();
        int r0i;
        if (this.image.isLandscape()) {
            if (this.image.getDeviceOrientation() == 1) {
                if (this.image.getScaledHeight() < this.canvasHeight) {
                    r0f = this.fitScaleVertical / this.currentScale;
                    this.zoomAnimation.setTouchX(r4_MotionEvent.getX());
                    this.zoomAnimation.setTouchY(this.image.getCenterY());
                } else {
                    r0f = this.fitScaleHorizontal / this.currentScale;
                    this.zoomAnimation.setTouchX(this.image.getCenterX());
                    this.zoomAnimation.setTouchY(this.image.getCenterY());
                }
            } else {
                r0i = this.image.getScaledWidth();
                if (r0i == this.canvasWidth) {
                    r0f = this.currentScale * r2f;
                    this.zoomAnimation.setTouchX(r4_MotionEvent.getX());
                    this.zoomAnimation.setTouchY(r4_MotionEvent.getY());
                } else if (r0i < this.canvasWidth) {
                    r0f = this.fitScaleHorizontal / this.currentScale;
                    this.zoomAnimation.setTouchX(this.image.getCenterX());
                    this.zoomAnimation.setTouchY(r4_MotionEvent.getY());
                } else {
                    r0f = this.fitScaleHorizontal / this.currentScale;
                    this.zoomAnimation.setTouchX(this.image.getCenterX());
                    this.zoomAnimation.setTouchY(this.image.getCenterY());
                }
            }
        } else if (this.image.getDeviceOrientation() == 1) {
            r0i = this.image.getScaledHeight();
            if (r0i == this.canvasHeight) {
                r0f = this.currentScale * r2f;
                this.zoomAnimation.setTouchX(r4_MotionEvent.getX());
                this.zoomAnimation.setTouchY(r4_MotionEvent.getY());
            } else if (r0i < this.canvasHeight) {
                r0f = this.fitScaleVertical / this.currentScale;
                this.zoomAnimation.setTouchX(r4_MotionEvent.getX());
                this.zoomAnimation.setTouchY(this.image.getCenterY());
            } else {
                r0f = this.fitScaleVertical / this.currentScale;
                this.zoomAnimation.setTouchX(this.image.getCenterX());
                this.zoomAnimation.setTouchY(this.image.getCenterY());
            }
        } else if (this.image.getScaledWidth() < this.canvasWidth) {
            r0f = this.fitScaleHorizontal / this.currentScale;
            this.zoomAnimation.setTouchX(this.image.getCenterX());
            this.zoomAnimation.setTouchY(r4_MotionEvent.getY());
        } else {
            r0f = this.fitScaleVertical / this.currentScale;
            this.zoomAnimation.setTouchX(this.image.getCenterX());
            this.zoomAnimation.setTouchY(this.image.getCenterY());
        }
        this.zoomAnimation.setZoom(r0f);
        this.image.animationStart(this.zoomAnimation);
    }

    private void stopAnimations() {
        this.image.animationStop();
    }

    protected void boundCoordinates() {
        if (this.next.x < this.boundaryLeft) {
            this.next.x = this.boundaryLeft;
        } else if (this.next.x > this.boundaryRight) {
            this.next.x = this.boundaryRight;
        }
        if (this.next.y < this.boundaryTop) {
            this.next.y = this.boundaryTop;
        } else {
            if (this.next.y > this.boundaryBottom) {
                this.next.y = this.boundaryBottom;
            }
        }
    }

    protected void calculateBoundaries() {
        boolean r1z = true;
        int r3i = Math.round(((float) this.imageWidth) * this.currentScale);
        int r4i = Math.round(((float) this.imageHeight) * this.currentScale);
        this.canDragX = r3i > this.displayWidth;
        float r0f;
        if (r4i > this.displayHeight) {
            this.canDragY = r1z;
            if (!this.canDragX) {
                r0f = ((float) (r3i - this.displayWidth)) / 2.0f;
                this.boundaryLeft = this.centerX - r0f;
                this.boundaryRight = r0f + this.centerX;
            }
            if (this.canDragY) {
                r0f = ((float) (r4i - this.displayHeight)) / 2.0f;
                this.boundaryTop = this.centerY - r0f;
                this.boundaryBottom = r0f + this.centerY;
            }
        } else {
            r1z = false;
            this.canDragY = r1z;
            if (this.canDragX) {
                if (this.canDragY) {
                } else {
                    r0f = ((float) (r4i - this.displayHeight)) / 2.0f;
                    this.boundaryTop = this.centerY - r0f;
                    this.boundaryBottom = r0f + this.centerY;
                }
            } else {
                r0f = ((float) (r3i - this.displayWidth)) / 2.0f;
                this.boundaryLeft = this.centerX - r0f;
                this.boundaryRight = r0f + this.centerX;
                if (this.canDragY) {
                    r0f = ((float) (r4i - this.displayHeight)) / 2.0f;
                    this.boundaryTop = this.centerY - r0f;
                    this.boundaryBottom = r0f + this.centerY;
                }
            }
        }
    }

    public float getMaxScale() {
        return this.maxScale;
    }

    public float getMinScale() {
        return this.minScale;
    }

    protected boolean handleDrag(float r5f, float r6f) {
        this.current.x = r5f;
        this.current.y = r6f;
        float r0f = this.current.x - this.last.x;
        float r1f = this.current.y - this.last.y;
        if (r0f == 0.0f && r1f == 0.0f) {
            return false;
        }
        if (this.canDragX) {
            PointF r2_PointF = this.next;
            r2_PointF.x = r0f + r2_PointF.x;
        }
        if (this.canDragY) {
            PointF r0_PointF = this.next;
            r0_PointF.y = r1f + r0_PointF.y;
        }
        boundCoordinates();
        this.last.x = this.current.x;
        this.last.y = this.current.y;
        if (!(this.canDragX) && !(this.canDragY)) {
            return false;
        }
        this.image.setPosition(this.next.x, this.next.y);
        if (this.imageListener != null) {
            this.imageListener.onPosition(this.next.x, this.next.y);
        }
        return true;
    }

    protected void handleScale(float r4f, float r5f, float r6f) {
        this.currentScale = r4f;
        if (this.currentScale > this.maxScale) {
            this.currentScale = this.maxScale;
        } else if (this.currentScale < this.minScale) {
            this.currentScale = this.minScale;
        } else {
            this.next.x = r5f;
            this.next.y = r6f;
        }
        calculateBoundaries();
        this.image.setScale(this.currentScale);
        this.image.setPosition(this.next.x, this.next.y);
        if (this.imageListener != null) {
            this.imageListener.onScale(this.currentScale);
            this.imageListener.onPosition(this.next.x, this.next.y);
        }
        this.image.redraw();
    }

    protected void handleUp() {
        this.multiTouch = false;
        this.initialDistance = 0.0f;
        this.lastScale = this.currentScale;
        if (!this.canDragX) {
            this.next.x = this.centerX;
        }
        if (!this.canDragY) {
            this.next.y = this.centerY;
        }
        boundCoordinates();
        if (this.canDragX || this.canDragY) {
            this.image.setScale(this.currentScale);
            this.image.setPosition(this.next.x, this.next.y);
            if (this.imageListener == null) {
                this.imageListener.onScale(this.currentScale);
                this.imageListener.onPosition(this.next.x, this.next.y);
            }
            this.image.redraw();
        } else if (this.image.isLandscape()) {
            this.currentScale = this.fitScaleHorizontal;
            this.lastScale = this.fitScaleHorizontal;
            this.image.setScale(this.currentScale);
            this.image.setPosition(this.next.x, this.next.y);
            if (this.imageListener == null) {
                this.image.redraw();
            } else {
                this.imageListener.onScale(this.currentScale);
                this.imageListener.onPosition(this.next.x, this.next.y);
                this.image.redraw();
            }
        } else if (this.fitScaleHorizontal > this.fitScaleVertical) {
            this.currentScale = this.fitScaleVertical;
            this.lastScale = this.fitScaleVertical;
            this.image.setScale(this.currentScale);
            this.image.setPosition(this.next.x, this.next.y);
            if (this.imageListener == null) {
                this.imageListener.onScale(this.currentScale);
                this.imageListener.onPosition(this.next.x, this.next.y);
            }
            this.image.redraw();
        } else {
            this.currentScale = this.fitScaleHorizontal;
            this.lastScale = this.fitScaleHorizontal;
            this.image.setScale(this.currentScale);
            this.image.setPosition(this.next.x, this.next.y);
            if (this.imageListener == null) {
                this.image.redraw();
            } else {
                this.imageListener.onScale(this.currentScale);
                this.imageListener.onPosition(this.next.x, this.next.y);
                this.image.redraw();
            }
        }
    }

    public boolean onTouch(View r5_View, MotionEvent r6_MotionEvent) {
        if (this.inZoom || this.tapDetector.onTouchEvent(r6_MotionEvent)) {
            return true;
        }
        float r0f;
        if (r6_MotionEvent.getPointerCount() == 1 && this.flingDetector.onTouchEvent(r6_MotionEvent)) {
            startFling();
            if (r6_MotionEvent.getAction() != 1) {
                if (r6_MotionEvent.getAction() != 0) {
                    if (r6_MotionEvent.getAction() != 2) {
                        return true;
                    }
                    if (r6_MotionEvent.getPointerCount() <= 1) {
                        if (this.touched) {
                            if (this.multiTouch || (!handleDrag(r6_MotionEvent.getX(), r6_MotionEvent.getY()))) {
                                return true;
                            }
                            this.image.redraw();
                            return true;
                        } else {
                            this.touched = true;
                            this.last.x = r6_MotionEvent.getX();
                            this.last.y = r6_MotionEvent.getY();
                            this.next.x = this.image.getImageX();
                            this.next.y = this.image.getImageY();
                            return true;
                        }
                    } else {
                        this.multiTouch = true;
                        if (this.initialDistance <= 0.0f) {
                            this.initialDistance = MathUtils.distance(r6_MotionEvent);
                            MathUtils.midpoint(r6_MotionEvent, this.midpoint);
                            this.scaleVector.setStart(this.midpoint);
                            this.scaleVector.setEnd(this.next);
                            this.scaleVector.calculateLength();
                            this.scaleVector.calculateAngle();
                            r0_VectorF = this.scaleVector;
                            r0_VectorF.length /= this.lastScale;
                            return true;
                        } else {
                            this.pinchVector.set(r6_MotionEvent);
                            this.pinchVector.calculateLength();
                            r0f = this.pinchVector.length;
                            if (this.initialDistance == r0f) {
                                return true;
                            }
                            r0f = (r0f / this.initialDistance) * this.lastScale;
                            if (r0f > this.maxScale) {
                                return true;
                            }
                            r1_VectorF = this.scaleVector;
                            r1_VectorF.length *= r0f;
                            this.scaleVector.calculateEndPoint();
                            r1_VectorF = this.scaleVector;
                            r1_VectorF.length /= r0f;
                            handleScale(r0f, this.scaleVector.end.x, this.scaleVector.end.y);
                            return true;
                        }
                    }
                } else {
                    stopAnimations();
                    this.last.x = r6_MotionEvent.getX();
                    this.last.y = r6_MotionEvent.getY();
                    if (this.imageListener == null) {
                        this.touched = true;
                        return true;
                    } else {
                        this.imageListener.onTouch(this.last.x, this.last.y);
                        this.touched = true;
                        return true;
                    }
                }
            } else {
                handleUp();
                return true;
            }
        } else if (r6_MotionEvent.getAction() != 1) {
            handleUp();
            return true;
        } else if (r6_MotionEvent.getAction() != 0) {
            stopAnimations();
            this.last.x = r6_MotionEvent.getX();
            this.last.y = r6_MotionEvent.getY();
            if (this.imageListener == null) {
                this.imageListener.onTouch(this.last.x, this.last.y);
            }
            this.touched = true;
            return true;
        } else {
            if (r6_MotionEvent.getAction() != 2) {
                if (r6_MotionEvent.getPointerCount() <= 1) {
                    this.multiTouch = true;
                    if (this.initialDistance <= 0.0f) {
                        this.pinchVector.set(r6_MotionEvent);
                        this.pinchVector.calculateLength();
                        r0f = this.pinchVector.length;
                        if (this.initialDistance == r0f) {
                            r0f = (r0f / this.initialDistance) * this.lastScale;
                            if (r0f > this.maxScale) {
                                r1_VectorF = this.scaleVector;
                                r1_VectorF.length *= r0f;
                                this.scaleVector.calculateEndPoint();
                                r1_VectorF = this.scaleVector;
                                r1_VectorF.length /= r0f;
                                handleScale(r0f, this.scaleVector.end.x, this.scaleVector.end.y);
                            }
                        }
                    } else {
                        this.initialDistance = MathUtils.distance(r6_MotionEvent);
                        MathUtils.midpoint(r6_MotionEvent, this.midpoint);
                        this.scaleVector.setStart(this.midpoint);
                        this.scaleVector.setEnd(this.next);
                        this.scaleVector.calculateLength();
                        this.scaleVector.calculateAngle();
                        r0_VectorF = this.scaleVector;
                        r0_VectorF.length /= this.lastScale;
                    }
                } else if (this.touched) {
                    this.touched = true;
                    this.last.x = r6_MotionEvent.getX();
                    this.last.y = r6_MotionEvent.getY();
                    this.next.x = this.image.getImageX();
                    this.next.y = this.image.getImageY();
                } else {
                    if (this.multiTouch || handleDrag(r6_MotionEvent.getX(), r6_MotionEvent.getY())) {
                        return true;
                    }
                    this.image.redraw();
                }
            }
            return true;
        }
    }

    public void reset() {
        this.currentScale = this.startingScale;
        this.next.x = this.centerX;
        this.next.y = this.centerY;
        calculateBoundaries();
        this.image.setScale(this.currentScale);
        this.image.setPosition(this.next.x, this.next.y);
        this.image.redraw();
    }

    protected void setCanvasHeight(int r1i) {
        this.canvasHeight = r1i;
    }

    protected void setCanvasWidth(int r1i) {
        this.canvasWidth = r1i;
    }

    protected void setFitScaleHorizontal(float r1f) {
        this.fitScaleHorizontal = r1f;
    }

    protected void setFitScaleVertical(float r1f) {
        this.fitScaleVertical = r1f;
    }

    public void setMaxScale(float r1f) {
        this.maxScale = r1f;
    }

    public void setMinScale(float r1f) {
        this.minScale = r1f;
    }

    public void setOnClickListener(OnClickListener r1_OnClickListener) {
        this.onClickListener = r1_OnClickListener;
    }
}