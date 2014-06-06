package qsbk.app.widget.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageViewTouchBaseView extends ImageView {
    protected static final float MAX_ZOOM = 2.0f;
    protected static final float SCALE_RATE = 1.5f;
    protected Matrix mBaseMatrix;
    protected Bitmap mBitmap;
    protected Matrix mDisplayMatrix;
    protected Handler mHandler;
    protected float[] mMatrixValues;
    protected float mMaxZoom;
    private Runnable mOnLayoutRunnable;
    private Recycler mRecycler;
    protected int mRotation;
    protected Matrix mSuppMatrix;
    protected int mThisHeight;
    protected int mThisWidth;

    public static interface Recycler {
        public void recycler(Bitmap r1_Bitmap);
    }

    public ImageViewTouchBaseView(Context r3_Context) {
        super(r3_Context);
        this.mBitmap = null;
        this.mOnLayoutRunnable = null;
        this.mHandler = new Handler();
        this.mBaseMatrix = new Matrix();
        this.mSuppMatrix = new Matrix();
        this.mDisplayMatrix = new Matrix();
        this.mMatrixValues = new float[9];
        this.mThisWidth = -1;
        this.mThisHeight = -1;
        init();
    }

    public ImageViewTouchBaseView(Context r3_Context, AttributeSet r4_AttributeSet) {
        super(r3_Context, r4_AttributeSet);
        this.mBitmap = null;
        this.mOnLayoutRunnable = null;
        this.mHandler = new Handler();
        this.mBaseMatrix = new Matrix();
        this.mSuppMatrix = new Matrix();
        this.mDisplayMatrix = new Matrix();
        this.mMatrixValues = new float[9];
        this.mThisWidth = -1;
        this.mThisHeight = -1;
        init();
    }

    private int getBitmapHeight() {
        return isOrientationChanged() ? this.mBitmap.getWidth() : this.mBitmap.getHeight();
    }

    private int getBitmapWidth() {
        return isOrientationChanged() ? this.mBitmap.getHeight() : this.mBitmap.getWidth();
    }

    private Matrix getRotateMatrix() {
        Matrix r0_Matrix = new Matrix();
        if (this.mRotation == 0 || this.mBitmap == null) {
            return r0_Matrix;
        }
        int r1i = this.mBitmap.getWidth() / 2;
        int r2i = this.mBitmap.getHeight() / 2;
        r0_Matrix.preTranslate((float) (-r1i), (float) (-r2i));
        r0_Matrix.postRotate((float) this.mRotation);
        r0_Matrix.postTranslate((float) r1i, (float) r2i);
        return r0_Matrix;
    }

    private boolean isOrientationChanged() {
        return (this.mRotation / 90) % 2 != 0;
    }

    private void setImageBitmap(Bitmap r3_Bitmap, int r4i) {
        super.setImageBitmap(r3_Bitmap);
        Drawable r0_Drawable = getDrawable();
        if (r0_Drawable != null) {
            r0_Drawable.setDither(true);
        }
        Bitmap r0_Bitmap = this.mBitmap;
        this.mBitmap = r3_Bitmap;
        this.mRotation = r4i % 360;
        if (r0_Bitmap == null || r0_Bitmap == r3_Bitmap || this.mRecycler == null) {
        } else {
            this.mRecycler.recycler(r0_Bitmap);
        }
    }

    protected void center(boolean r8z, boolean r9z) {
        float r0f = 0.0f;
        if (this.mBitmap == null) {
        } else {
            int r4i;
            Matrix r1_Matrix = getImageViewMatrix();
            RectF r2_RectF = new RectF(0.0f, 0.0f, (float) this.mBitmap.getWidth(), (float) this.mBitmap.getHeight());
            r1_Matrix.mapRect(r2_RectF);
            float r1f = r2_RectF.height();
            float r3f = r2_RectF.width();
            if (r9z) {
                r4i = this.mThisHeight;
                if (r1f < ((float) r4i)) {
                    r1f = (((float) r4i) - r1f) / 2.0f - r2_RectF.top;
                } else if (r2_RectF.top > 0.0f) {
                    r1f = -r2_RectF.top;
                } else {
                    if (r2_RectF.bottom < ((float) r4i)) {
                        r1f = ((float) getHeight()) - r2_RectF.bottom;
                    }
                    r1f = 0.0f;
                }
            } else {
                r1f = 0.0f;
            }
            if (r8z) {
                r4i = this.mThisWidth;
                if (r3f < ((float) r4i)) {
                    r0f = (((float) r4i) - r3f) / 2.0f - r2_RectF.left;
                } else if (r2_RectF.left > 0.0f) {
                    r0f = -r2_RectF.left;
                } else if (r2_RectF.right < ((float) r4i)) {
                    r0f = ((float) r4i) - r2_RectF.right;
                }
            }
            postTranslate(r0f, r1f);
            setImageMatrix(getImageViewMatrix());
        }
    }

    public void clear() {
        setImageRotateBitmapReset(null, 0, true);
    }

    protected RectF getBitmapRect() {
        if (this.mBitmap == null) {
            return null;
        }
        Matrix r1_Matrix = getImageViewMatrix();
        RectF r0_RectF = new RectF(0.0f, 0.0f, (float) getBitmapWidth(), (float) getBitmapHeight());
        r1_Matrix.mapRect(r0_RectF);
        return r0_RectF;
    }

    protected RectF getCenter(boolean r8z, boolean r9z) {
        if (this.mBitmap == null) {
            return new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        }
        float r3f;
        RectF r2_RectF = getBitmapRect();
        float r0f = r2_RectF.height();
        float r4f = r2_RectF.width();
        if (r9z) {
            int r3i = getHeight();
            if (r0f < ((float) r3i)) {
                r3f = (((float) r3i) - r0f) / 2.0f - r2_RectF.top;
            } else if (r2_RectF.top > 0.0f) {
                r3f = -r2_RectF.top;
            } else {
                if (r2_RectF.bottom < ((float) r3i)) {
                    r3f = ((float) getHeight()) - r2_RectF.bottom;
                }
                r3f = 0.0f;
            }
        } else {
            r3f = 0.0f;
        }
        if (r8z) {
            int r0i = getWidth();
            if (r4f < ((float) r0i)) {
                r0f = (((float) r0i) - r4f) / 2.0f - r2_RectF.left;
            } else if (r2_RectF.left > 0.0f) {
                r0f = -r2_RectF.left;
            } else {
                if (r2_RectF.right < ((float) r0i)) {
                    r0f = ((float) r0i) - r2_RectF.right;
                }
                r0f = 0.0f;
            }
        } else {
            r0f = 0.0f;
        }
        return new RectF(r0f, r3f, 0.0f, 0.0f);
    }

    public Bitmap getDisplayBitmap() {
        return this.mBitmap;
    }

    protected Matrix getImageViewMatrix() {
        this.mDisplayMatrix.set(this.mBaseMatrix);
        this.mDisplayMatrix.postConcat(this.mSuppMatrix);
        return this.mDisplayMatrix;
    }

    public float getMaxZoom() {
        return this.mMaxZoom;
    }

    protected void getProperBaseMatrix(Matrix r9_Matrix) {
        float r2f = (float) getWidth();
        float r3f = (float) getHeight();
        float r4f = (float) getBitmapWidth();
        float r5f = (float) getBitmapHeight();
        r9_Matrix.reset();
        float r1f = r2f / r4f;
        float r0f = r3f / r5f;
        if (r1f > r0f) {
            r9_Matrix.postConcat(getRotateMatrix());
            r9_Matrix.postScale(r0f, r0f);
            r9_Matrix.postTranslate((r2f - (r4f * r0f)) / 2.0f, (r3f - (r0f * r5f)) / 2.0f);
        } else {
            r0f = r1f;
            r9_Matrix.postConcat(getRotateMatrix());
            r9_Matrix.postScale(r0f, r0f);
            r9_Matrix.postTranslate((r2f - (r4f * r0f)) / 2.0f, (r3f - (r0f * r5f)) / 2.0f);
        }
    }

    public float getRotation() {
        return (float) this.mRotation;
    }

    public float getScale() {
        return getScale(this.mSuppMatrix);
    }

    protected float getScale(Matrix r2_Matrix) {
        return getValue(r2_Matrix, 0);
    }

    protected float getValue(Matrix r2_Matrix, int r3i) {
        r2_Matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[r3i];
    }

    protected void init() {
        setScaleType(ScaleType.MATRIX);
    }

    protected float maxZoom() {
        float r0f = 1.0f;
        return this.mBitmap == null ? 1.0f : Math.max(Math.max(((float) this.mBitmap.getWidth()) / ((float) this.mThisWidth), ((float) this.mBitmap.getHeight()) / ((float) this.mThisHeight)), r0f) * 4.0f;
    }

    public boolean onKeyDown(int r3i, KeyEvent r4_KeyEvent) {
        if (r3i != 4 || getScale() <= 1.0f) {
            return super.onKeyDown(r3i, r4_KeyEvent);
        }
        zoomTo(1.0f);
        return true;
    }

    protected void onLayout(boolean r4z, int r5i, int r6i, int r7i, int r8i) {
        super.onLayout(r4z, r5i, r6i, r7i, r8i);
        this.mThisWidth = r7i - r5i;
        this.mThisHeight = r8i - r6i;
        Runnable r0_Runnable = this.mOnLayoutRunnable;
        if (r0_Runnable != null) {
            this.mOnLayoutRunnable = null;
            r0_Runnable.run();
        }
        if (this.mBitmap != null) {
            getProperBaseMatrix(this.mBaseMatrix);
            setImageMatrix(getImageViewMatrix());
            this.mMaxZoom = maxZoom();
            center(true, true);
        }
    }

    protected void onZoom(float r1f) {
    }

    protected void panBy(float r5f, float r6f) {
        RectF r0_RectF = getBitmapRect();
        if (r0_RectF == null) {
        } else {
            updateRect(r0_RectF, new RectF(r5f, r6f, 0.0f, 0.0f));
            postTranslate(r5f, r6f);
            center(true, true);
        }
    }

    protected void postScale(float r2f, float r3f, float r4f) {
        this.mSuppMatrix.postScale(r2f, r2f, r3f, r4f);
        setImageMatrix(getImageViewMatrix());
    }

    protected void postTranslate(float r2f, float r3f) {
        this.mSuppMatrix.postTranslate(r2f, r3f);
        setImageMatrix(getImageViewMatrix());
    }

    public void rotate(int r2i) {
        rotateTo(this.mRotation + r2i);
    }

    public void rotateTo(int r3i) {
        this.mRotation = r3i % 360;
        if (this.mBitmap == null) {
        } else {
            getProperBaseMatrix(this.mBaseMatrix);
            setImageMatrix(getImageViewMatrix());
            this.mMaxZoom = maxZoom();
            center(true, true);
        }
    }

    protected void scrollBy(float r1f, float r2f) {
        panBy(r1f, r2f);
    }

    protected void scrollBy(float r9f, float r10f, float r11f) {
        this.mHandler.post(new h(this, r11f, System.currentTimeMillis(), r9f, r10f));
    }

    public void setImageBitmap(Bitmap r2_Bitmap) {
        setImageBitmap(r2_Bitmap, this.mRotation);
    }

    public void setImageRotateBitmapReset(Bitmap r3_Bitmap, int r4i, boolean r5z) {
        if (getWidth() <= 0) {
            this.mOnLayoutRunnable = new f(this, r3_Bitmap, r5z);
        } else {
            if (r3_Bitmap != null) {
                setImageBitmap(r3_Bitmap, r4i);
                getProperBaseMatrix(this.mBaseMatrix);
            } else {
                this.mBaseMatrix.reset();
                setImageBitmap(null, 0);
            }
            if (r5z) {
                this.mSuppMatrix.reset();
            }
            setImageMatrix(getImageViewMatrix());
            this.mMaxZoom = maxZoom();
        }
    }

    public void setRecycler(Recycler r1_Recycler) {
        this.mRecycler = r1_Recycler;
    }

    protected void updateRect(RectF r6_RectF, RectF r7_RectF) {
        float r0f = (float) getWidth();
        float r1f = (float) getHeight();
        if (r6_RectF.top < 0.0f || r6_RectF.bottom > r1f) {
            if (r6_RectF.left < 0.0f || r6_RectF.right > r0f) {
                if (r6_RectF.top + r7_RectF.top < 0.0f || r6_RectF.bottom <= r1f) {
                    if (r6_RectF.bottom + r7_RectF.top > r1f - 0.0f || r6_RectF.top >= 0.0f) {
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                        }
                        if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                            r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                        }
                    } else {
                        r7_RectF.top = (float) ((int) (r1f - 0.0f - r6_RectF.bottom));
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                            } else {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        } else {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        }
                    }
                } else {
                    r7_RectF.top = (float) ((int) (0.0f - r6_RectF.top));
                    if (r6_RectF.bottom + r7_RectF.top > r1f - 0.0f || r6_RectF.top >= 0.0f) {
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                        }
                        if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                        } else {
                            r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                        }
                    } else {
                        r7_RectF.top = (float) ((int) (r1f - 0.0f - r6_RectF.bottom));
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        } else {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                            } else {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        }
                    }
                }
            } else {
                r7_RectF.left = 0.0f;
                if (r6_RectF.top + r7_RectF.top < 0.0f || r6_RectF.bottom <= r1f) {
                    if (r6_RectF.bottom + r7_RectF.top > r1f - 0.0f || r6_RectF.top >= 0.0f) {
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                        }
                        if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                            r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                        }
                    } else {
                        r7_RectF.top = (float) ((int) (r1f - 0.0f - r6_RectF.bottom));
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                            } else {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        } else {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        }
                    }
                } else {
                    r7_RectF.top = (float) ((int) (0.0f - r6_RectF.top));
                    if (r6_RectF.bottom + r7_RectF.top > r1f - 0.0f || r6_RectF.top >= 0.0f) {
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                        }
                        if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                        } else {
                            r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                        }
                    } else {
                        r7_RectF.top = (float) ((int) (r1f - 0.0f - r6_RectF.bottom));
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        } else {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                            } else {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        }
                    }
                }
            }
        } else {
            r7_RectF.top = 0.0f;
            if (r6_RectF.left < 0.0f || r6_RectF.right > r0f) {
                if (r6_RectF.top + r7_RectF.top < 0.0f || r6_RectF.bottom <= r1f) {
                    if (r6_RectF.bottom + r7_RectF.top > r1f - 0.0f || r6_RectF.top >= 0.0f) {
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                        }
                        if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                            r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                        }
                    } else {
                        r7_RectF.top = (float) ((int) (r1f - 0.0f - r6_RectF.bottom));
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                            } else {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        } else {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        }
                    }
                } else {
                    r7_RectF.top = (float) ((int) (0.0f - r6_RectF.top));
                    if (r6_RectF.bottom + r7_RectF.top > r1f - 0.0f || r6_RectF.top >= 0.0f) {
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                        }
                        if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                        } else {
                            r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                        }
                    } else {
                        r7_RectF.top = (float) ((int) (r1f - 0.0f - r6_RectF.bottom));
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        } else {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                            } else {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        }
                    }
                }
            } else {
                r7_RectF.left = 0.0f;
                if (r6_RectF.top + r7_RectF.top < 0.0f || r6_RectF.bottom <= r1f) {
                    if (r6_RectF.bottom + r7_RectF.top > r1f - 0.0f || r6_RectF.top >= 0.0f) {
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                        }
                        if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                            r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                        }
                    } else {
                        r7_RectF.top = (float) ((int) (r1f - 0.0f - r6_RectF.bottom));
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                            } else {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        } else {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        }
                    }
                } else {
                    r7_RectF.top = (float) ((int) (0.0f - r6_RectF.top));
                    if (r6_RectF.bottom + r7_RectF.top > r1f - 0.0f || r6_RectF.top >= 0.0f) {
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                        }
                        if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                        } else {
                            r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                        }
                    } else {
                        r7_RectF.top = (float) ((int) (r1f - 0.0f - r6_RectF.bottom));
                        if (r6_RectF.left + r7_RectF.left < 0.0f) {
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        } else {
                            r7_RectF.left = (float) ((int) (0.0f - r6_RectF.left));
                            if (r6_RectF.right + r7_RectF.left > r0f - 0.0f) {
                            } else {
                                r7_RectF.left = (float) ((int) (r0f - 0.0f - r6_RectF.right));
                            }
                        }
                    }
                }
            }
        }
    }

    public void zoomIn() {
        zoomIn(SCALE_RATE);
    }

    protected void zoomIn(float r4f) {
        float r2f = MAX_ZOOM;
        if (getScale() < this.mMaxZoom && this.mBitmap != null) {
            this.mSuppMatrix.postScale(r4f, r4f, ((float) getWidth()) / r2f, ((float) getHeight()) / r2f);
            setImageMatrix(getImageViewMatrix());
        }
    }

    public void zoomOut() {
        zoomOut(SCALE_RATE);
    }

    protected void zoomOut(float r8f) {
        float r2f = MAX_ZOOM;
        if (this.mBitmap == null) {
        } else {
            float r0f = ((float) getWidth()) / r2f;
            float r1f = ((float) getHeight()) / r2f;
            Matrix r2_Matrix = new Matrix(this.mSuppMatrix);
            r2_Matrix.postScale(1.0f / r8f, 1.0f / r8f, r0f, r1f);
            if (getScale(r2_Matrix) < 1.0f) {
                this.mSuppMatrix.setScale(1.0f, 1.0f, r0f, r1f);
            } else {
                this.mSuppMatrix.postScale(1.0f / r8f, 1.0f / r8f, r0f, r1f);
            }
            setImageMatrix(getImageViewMatrix());
            center(true, true);
        }
    }

    protected void zoomTo(float r4f) {
        zoomTo(r4f, ((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
    }

    public void zoomTo(float r4f, float r5f) {
        zoomTo(r4f, ((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, r5f);
    }

    protected void zoomTo(float r4f, float r5f, float r6f) {
        if (r4f > this.mMaxZoom) {
            r4f = this.mMaxZoom;
        }
        float r0f = r4f / getScale();
        this.mSuppMatrix.postScale(r0f, r0f, r5f, r6f);
        setImageMatrix(getImageViewMatrix());
        center(true, true);
    }

    protected void zoomTo(float r11f, float r12f, float r13f, float r14f) {
        this.mHandler.post(new g(this, r14f, System.currentTimeMillis(), getScale(), (r11f - getScale()) / r14f, r12f, r13f));
    }
}