package qsbk.app.widget.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class GestureImageView extends ImageView {
    public static final String GLOBAL_NS = "http://schemas.android.com/apk/res/android";
    public static final String LOCAL_NS = "http://schemas.qsbk.app/android";
    private int alpha;
    private Animator animator;
    private float centerX;
    private float centerY;
    private ColorFilter colorFilter;
    private OnTouchListener customOnTouchListener;
    private int deviceOrientation;
    private int displayHeight;
    private int displayWidth;
    private final Semaphore drawLock;
    private Drawable drawable;
    private float fitScaleHorizontal;
    private float fitScaleVertical;
    private GestureImageViewListener gestureImageViewListener;
    private GestureImageViewTouchListener gestureImageViewTouchListener;
    private int hHeight;
    private int hWidth;
    private int imageOrientation;
    private boolean layout;
    private float maxScale;
    private float minScale;
    private OnClickListener onClickListener;
    private boolean recycle;
    private int resId;
    private float rotation;
    private float scale;
    private float scaleAdjust;
    private Float startX;
    private Float startY;
    private float startingScale;
    private boolean strict;
    private float x;
    private float y;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[ScaleType.values().length];
            try {
                a[ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e_2) {
            }
            a[ScaleType.CENTER_INSIDE.ordinal()] = 3;
        }
    }

    public GestureImageView(Context r6_Context) {
        super(r6_Context);
        this.drawLock = new Semaphore(0);
        this.x = 0.0f;
        this.y = 0.0f;
        this.layout = false;
        this.scaleAdjust = 1.0f;
        this.startingScale = -1.0f;
        this.scale = 1.0f;
        this.maxScale = 5.0f;
        this.minScale = 0.75f;
        this.fitScaleHorizontal = 1.0f;
        this.fitScaleVertical = 1.0f;
        this.rotation = 0.0f;
        this.resId = -1;
        this.recycle = false;
        this.strict = false;
        this.alpha = 255;
        this.deviceOrientation = -1;
        setScaleType(ScaleType.CENTER_INSIDE);
        initImage();
    }

    public GestureImageView(Context r6_Context, AttributeSet r7_AttributeSet) {
        super(r6_Context, r7_AttributeSet);
        this.drawLock = new Semaphore(0);
        this.x = 0.0f;
        this.y = 0.0f;
        this.layout = false;
        this.scaleAdjust = 1.0f;
        this.startingScale = -1.0f;
        this.scale = 1.0f;
        this.maxScale = 5.0f;
        this.minScale = 0.75f;
        this.fitScaleHorizontal = 1.0f;
        this.fitScaleVertical = 1.0f;
        this.rotation = 0.0f;
        this.resId = -1;
        this.recycle = false;
        this.strict = false;
        this.alpha = 255;
        this.deviceOrientation = -1;
        String r0_String = r7_AttributeSet.getAttributeValue(GLOBAL_NS, "scaleType");
        String r1_String;
        if (r0_String == null || r0_String.trim().length() == 0) {
            setScaleType(ScaleType.CENTER_INSIDE);
            r0_String = r7_AttributeSet.getAttributeValue(LOCAL_NS, "start-x");
            r1_String = r7_AttributeSet.getAttributeValue(LOCAL_NS, "start-y");
            if (r0_String == null || r0_String.trim().length() <= 0) {
                if (r1_String == null || r1_String.trim().length() <= 0) {
                    setStartingScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "start-scale", this.startingScale));
                    setMinScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "min-scale", this.minScale));
                    setMaxScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "max-scale", this.maxScale));
                    setStrict(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "strict", this.strict));
                    setRecycle(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "recycle", this.recycle));
                    initImage();
                } else {
                    this.startY = Float.valueOf(Float.parseFloat(r1_String));
                    setStartingScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "start-scale", this.startingScale));
                    setMinScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "min-scale", this.minScale));
                    setMaxScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "max-scale", this.maxScale));
                    setStrict(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "strict", this.strict));
                    setRecycle(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "recycle", this.recycle));
                    initImage();
                }
            } else {
                this.startX = Float.valueOf(Float.parseFloat(r0_String));
                if (r1_String == null || r1_String.trim().length() <= 0) {
                    setStartingScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "start-scale", this.startingScale));
                    setMinScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "min-scale", this.minScale));
                    setMaxScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "max-scale", this.maxScale));
                    setStrict(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "strict", this.strict));
                    setRecycle(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "recycle", this.recycle));
                    initImage();
                } else {
                    this.startY = Float.valueOf(Float.parseFloat(r1_String));
                    setStartingScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "start-scale", this.startingScale));
                    setMinScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "min-scale", this.minScale));
                    setMaxScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "max-scale", this.maxScale));
                    setStrict(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "strict", this.strict));
                    setRecycle(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "recycle", this.recycle));
                    initImage();
                }
            }
        } else {
            r0_String = r7_AttributeSet.getAttributeValue(LOCAL_NS, "start-x");
            r1_String = r7_AttributeSet.getAttributeValue(LOCAL_NS, "start-y");
            if (r0_String == null || r0_String.trim().length() <= 0) {
                if (r1_String == null || r1_String.trim().length() <= 0) {
                    setStartingScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "start-scale", this.startingScale));
                    setMinScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "min-scale", this.minScale));
                    setMaxScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "max-scale", this.maxScale));
                    setStrict(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "strict", this.strict));
                    setRecycle(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "recycle", this.recycle));
                    initImage();
                } else {
                    this.startY = Float.valueOf(Float.parseFloat(r1_String));
                    setStartingScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "start-scale", this.startingScale));
                    setMinScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "min-scale", this.minScale));
                    setMaxScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "max-scale", this.maxScale));
                    setStrict(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "strict", this.strict));
                    setRecycle(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "recycle", this.recycle));
                    initImage();
                }
            } else {
                this.startX = Float.valueOf(Float.parseFloat(r0_String));
                if (r1_String == null || r1_String.trim().length() <= 0) {
                    setStartingScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "start-scale", this.startingScale));
                    setMinScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "min-scale", this.minScale));
                    setMaxScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "max-scale", this.maxScale));
                    setStrict(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "strict", this.strict));
                    setRecycle(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "recycle", this.recycle));
                    initImage();
                } else {
                    this.startY = Float.valueOf(Float.parseFloat(r1_String));
                    setStartingScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "start-scale", this.startingScale));
                    setMinScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "min-scale", this.minScale));
                    setMaxScale(r7_AttributeSet.getAttributeFloatValue(LOCAL_NS, "max-scale", this.maxScale));
                    setStrict(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "strict", this.strict));
                    setRecycle(r7_AttributeSet.getAttributeBooleanValue(LOCAL_NS, "recycle", this.recycle));
                    initImage();
                }
            }
        }
    }

    public GestureImageView(Context r1_Context, AttributeSet r2_AttributeSet, int r3i) {
        this(r1_Context, r2_AttributeSet);
    }

    public void animationStart(Animation r2_Animation) {
        if (this.animator != null) {
            this.animator.play(r2_Animation);
        }
    }

    public void animationStop() {
        if (this.animator != null) {
            this.animator.cancel();
        }
    }

    protected void computeCropScale(int r3i, int r4i, int r5i, int r6i) {
        this.fitScaleHorizontal = ((float) r5i) / ((float) r3i);
        this.fitScaleVertical = ((float) r6i) / ((float) r4i);
    }

    protected void computeStartingScale(int r4i, int r5i, int r6i, int r7i) {
        switch (AnonymousClass_1.a[getScaleType().ordinal()]) {
            case XListViewHeader.STATE_READY:
                this.startingScale = 1.0f;
                break;
            case XListViewHeader.STATE_REFRESHING:
                this.startingScale = Math.max(((float) r7i) / ((float) r5i), ((float) r6i) / ((float) r4i));
                break;
            case XListViewFooter.STATE_NOMORE:
                if (isLandscape()) {
                    this.startingScale = this.fitScaleHorizontal;
                } else if (this.fitScaleHorizontal > this.fitScaleVertical) {
                    this.startingScale = this.fitScaleVertical;
                } else {
                    this.startingScale = this.fitScaleHorizontal;
                }
                break;
        }
    }

    public float getCenterX() {
        return this.centerX;
    }

    public float getCenterY() {
        return this.centerY;
    }

    public int getDeviceOrientation() {
        return this.deviceOrientation;
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public GestureImageViewListener getGestureImageViewListener() {
        return this.gestureImageViewListener;
    }

    public int getImageHeight() {
        return this.drawable != null ? this.drawable.getIntrinsicHeight() : 0;
    }

    public Matrix getImageMatrix() {
        if (!(this.strict)) {
            return super.getImageMatrix();
        }
        throw new UnsupportedOperationException("Not supported");
    }

    public int getImageWidth() {
        return this.drawable != null ? this.drawable.getIntrinsicWidth() : 0;
    }

    public float getImageX() {
        return this.x;
    }

    public float getImageY() {
        return this.y;
    }

    public float getScale() {
        return this.scaleAdjust;
    }

    public int getScaledHeight() {
        return Math.round(((float) getImageHeight()) * getScale());
    }

    public int getScaledWidth() {
        return Math.round(((float) getImageWidth()) * getScale());
    }

    protected void initImage() {
        if (this.drawable != null) {
            this.drawable.setAlpha(this.alpha);
            this.drawable.setFilterBitmap(true);
            if (this.colorFilter != null) {
                this.drawable.setColorFilter(this.colorFilter);
            }
        }
        if (!this.layout) {
            requestLayout();
            redraw();
        }
    }

    public void invalidateDrawable(Drawable r3_Drawable) {
        if (this.strict) {
            throw new UnsupportedOperationException("Not supported");
        } else {
            super.invalidateDrawable(r3_Drawable);
        }
    }

    public boolean isLandscape() {
        return getImageWidth() >= getImageHeight();
    }

    public boolean isOrientationAligned() {
        if (this.deviceOrientation == 2) {
            return isLandscape();
        }
        if (this.deviceOrientation == 1) {
            return isPortrait();
        }
        return true;
    }

    public boolean isPortrait() {
        return getImageWidth() <= getImageHeight();
    }

    public boolean isRecycle() {
        return this.recycle;
    }

    protected boolean isRecycled() {
        if (this.drawable == null || (!this.drawable instanceof BitmapDrawable)) {
            return false;
        }
        Bitmap r0_Bitmap = ((BitmapDrawable) this.drawable).getBitmap();
        if (r0_Bitmap != null) {
            return r0_Bitmap.isRecycled();
        }
        return false;
    }

    public boolean isStrict() {
        return this.strict;
    }

    public void moveBy(float r2f, float r3f) {
        this.x += r2f;
        this.y += r3f;
    }

    protected void onAttachedToWindow() {
        this.animator = new Animator(this, "GestureImageViewAnimator");
        this.animator.start();
        if (this.resId < 0 || this.drawable != null) {
            super.onAttachedToWindow();
        } else {
            setImageResource(this.resId);
            super.onAttachedToWindow();
        }
    }

    public int[] onCreateDrawableState(int r3i) {
        if (!(this.strict)) {
            return super.onCreateDrawableState(r3i);
        }
        throw new UnsupportedOperationException("Not supported");
    }

    protected void onDetachedFromWindow() {
        if (this.animator != null) {
            this.animator.finish();
        }
        if ((!this.recycle) || this.drawable == null || isRecycled()) {
            super.onDetachedFromWindow();
        } else {
            recycle();
            this.drawable = null;
            super.onDetachedFromWindow();
        }
    }

    protected void onDraw(Canvas r4_Canvas) {
        if (this.layout) {
            if (this.drawable == null || isRecycled()) {
                if (this.drawLock.availablePermits() > 0) {
                    this.drawLock.release();
                }
            } else {
                r4_Canvas.save();
                float r0f = this.scale * this.scaleAdjust;
                r4_Canvas.translate(this.x, this.y);
                if (this.rotation != 0.0f) {
                    r4_Canvas.rotate(this.rotation);
                }
                if (r0f != 1.0f) {
                    r4_Canvas.scale(r0f, r0f);
                }
                this.drawable.draw(r4_Canvas);
                r4_Canvas.restore();
                if (this.drawLock.availablePermits() > 0) {
                } else {
                    this.drawLock.release();
                }
            }
        }
    }

    protected void onLayout(boolean r4z, int r5i, int r6i, int r7i, int r8i) {
        super.onLayout(r4z, r5i, r6i, r7i, r8i);
        if (r4z || (!this.layout)) {
            setupCanvas(this.displayWidth, this.displayHeight, getResources().getConfiguration().orientation);
        }
    }

    protected void onMeasure(int r4i, int r5i) {
        if (this.drawable != null) {
            if (getResources().getConfiguration().orientation == 2) {
                this.displayHeight = MeasureSpec.getSize(r5i);
                if (getLayoutParams().width == -2) {
                    this.displayWidth = Math.round((((float) getImageWidth()) / ((float) getImageHeight())) * ((float) this.displayHeight));
                } else {
                    this.displayWidth = MeasureSpec.getSize(r4i);
                }
            } else {
                this.displayWidth = MeasureSpec.getSize(r4i);
                if (getLayoutParams().height == -2) {
                    this.displayHeight = Math.round((((float) getImageHeight()) / ((float) getImageWidth())) * ((float) this.displayWidth));
                } else {
                    this.displayHeight = MeasureSpec.getSize(r5i);
                }
            }
        } else {
            this.displayHeight = MeasureSpec.getSize(r5i);
            this.displayWidth = MeasureSpec.getSize(r4i);
        }
        setMeasuredDimension(this.displayWidth, this.displayHeight);
    }

    protected void recycle() {
        if (this.recycle && this.drawable != null && this.drawable instanceof BitmapDrawable) {
            Bitmap r0_Bitmap = ((BitmapDrawable) this.drawable).getBitmap();
            if (r0_Bitmap != null) {
                r0_Bitmap.recycle();
            }
        }
    }

    public void redraw() {
        postInvalidate();
    }

    public void reset() {
        this.x = this.centerX;
        this.y = this.centerY;
        this.scaleAdjust = this.startingScale;
        redraw();
    }

    public void setAdjustViewBounds(boolean r3z) {
        if (this.strict) {
            throw new UnsupportedOperationException("Not supported");
        } else {
            super.setAdjustViewBounds(r3z);
        }
    }

    public void setAlpha(int r2i) {
        this.alpha = r2i;
        if (this.drawable != null) {
            this.drawable.setAlpha(r2i);
        }
    }

    public void setColorFilter(ColorFilter r2_ColorFilter) {
        this.colorFilter = r2_ColorFilter;
        if (this.drawable != null) {
            this.drawable.setColorFilter(r2_ColorFilter);
        }
    }

    public void setGestureImageViewListener(GestureImageViewListener r1_GestureImageViewListener) {
        this.gestureImageViewListener = r1_GestureImageViewListener;
    }

    public void setImageBitmap(Bitmap r3_Bitmap) {
        this.drawable = new BitmapDrawable(getResources(), r3_Bitmap);
        initImage();
    }

    public void setImageDrawable(Drawable r1_Drawable) {
        this.drawable = r1_Drawable;
        initImage();
    }

    public void setImageLevel(int r3i) {
        if (this.strict) {
            throw new UnsupportedOperationException("Not supported");
        } else {
            super.setImageLevel(r3i);
        }
    }

    public void setImageMatrix(Matrix r3_Matrix) {
        if (this.strict) {
            throw new UnsupportedOperationException("Not supported");
        }
    }

    public void setImageResource(int r2i) {
        if (this.drawable != null) {
            recycle();
        }
        if (r2i >= 0) {
            this.resId = r2i;
            setImageDrawable(getContext().getResources().getDrawable(r2i));
        }
    }

    public void setImageState(int[] r3_intA, boolean r4z) {
        if (this.strict) {
            throw new UnsupportedOperationException("Not supported");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setImageURI(Uri r10_Uri) {
        /*
        r9_this = this;
        r6 = 0;
        r0 = "content";
        r1 = r10.getScheme();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x00d2;
    L_0x000d:
        r0 = 1;
        r2 = new java.lang.String[r0];	 //Catch:{ Exception -> 0x00b8 }
        r0 = 0;
        r1 = "orientation";
        r2[r0] = r1;	 //Catch:{ Exception -> 0x00b8 }
        r0 = r9.getContext();	 //Catch:{ Exception -> 0x00b8 }
        r0 = r0.getContentResolver();	 //Catch:{ Exception -> 0x00b8 }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r1 = r10;
        r8 = r0.query(r1, r2, r3, r4, r5);	 //Catch:{ Exception -> 0x00b8 }
        if (r8 == 0) goto L_0x003a;
    L_0x0027:
        r0 = r8.moveToFirst();	 //Catch:{ Exception -> 0x00b8 }
        if (r0 == 0) goto L_0x003a;
    L_0x002d:
        r0 = 0;
        r0 = r2[r0];	 //Catch:{ Exception -> 0x00b8 }
        r0 = r8.getColumnIndex(r0);	 //Catch:{ Exception -> 0x00b8 }
        r0 = r8.getInt(r0);	 //Catch:{ Exception -> 0x00b8 }
        r9.imageOrientation = r0;	 //Catch:{ Exception -> 0x00b8 }
    L_0x003a:
        r0 = r9.getContext();	 //Catch:{ all -> 0x00de }
        r0 = r0.getContentResolver();	 //Catch:{ all -> 0x00de }
        r7 = r0.openInputStream(r10);	 //Catch:{ all -> 0x00de }
        r0 = android.graphics.BitmapFactory.decodeStream(r7);	 //Catch:{ all -> 0x00ab }
        r1 = r9.imageOrientation;	 //Catch:{ all -> 0x00ab }
        if (r1 == 0) goto L_0x009e;
    L_0x004e:
        r5 = new android.graphics.Matrix;	 //Catch:{ all -> 0x00ab }
        r5.<init>();	 //Catch:{ all -> 0x00ab }
        r1 = r9.imageOrientation;	 //Catch:{ all -> 0x00ab }
        r1 = (float) r1;	 //Catch:{ all -> 0x00ab }
        r5.postRotate(r1);	 //Catch:{ all -> 0x00ab }
        r1 = 0;
        r2 = 0;
        r3 = r0.getWidth();	 //Catch:{ all -> 0x00ab }
        r4 = r0.getHeight();	 //Catch:{ all -> 0x00ab }
        r6 = 1;
        r1 = android.graphics.Bitmap.createBitmap(r0, r1, r2, r3, r4, r5, r6);	 //Catch:{ all -> 0x00ab }
        r0.recycle();	 //Catch:{ all -> 0x00ab }
        r0 = new android.graphics.drawable.BitmapDrawable;	 //Catch:{ all -> 0x00ab }
        r2 = r9.getResources();	 //Catch:{ all -> 0x00ab }
        r0.<init>(r2, r1);	 //Catch:{ all -> 0x00ab }
        r9.setImageDrawable(r0);	 //Catch:{ all -> 0x00ab }
    L_0x0077:
        if (r7 == 0) goto L_0x007c;
    L_0x0079:
        r7.close();	 //Catch:{ Exception -> 0x00b8 }
    L_0x007c:
        if (r8 == 0) goto L_0x0081;
    L_0x007e:
        r8.close();	 //Catch:{ Exception -> 0x00b8 }
    L_0x0081:
        r0 = r9.drawable;
        if (r0 != 0) goto L_0x009d;
    L_0x0085:
        r0 = "GestureImageView";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "resolveUri failed on bad bitmap uri: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.e(r0, r1);
    L_0x009d:
        return;
    L_0x009e:
        r1 = new android.graphics.drawable.BitmapDrawable;	 //Catch:{ all -> 0x00ab }
        r2 = r9.getResources();	 //Catch:{ all -> 0x00ab }
        r1.<init>(r2, r0);	 //Catch:{ all -> 0x00ab }
        r9.setImageDrawable(r1);	 //Catch:{ all -> 0x00ab }
        goto L_0x0077;
    L_0x00ab:
        r0 = move-exception;
        r1 = r7;
    L_0x00ad:
        if (r1 == 0) goto L_0x00b2;
    L_0x00af:
        r1.close();	 //Catch:{ Exception -> 0x00b8 }
    L_0x00b2:
        if (r8 == 0) goto L_0x00b7;
    L_0x00b4:
        r8.close();	 //Catch:{ Exception -> 0x00b8 }
    L_0x00b7:
        throw r0;	 //Catch:{ Exception -> 0x00b8 }
    L_0x00b8:
        r0 = move-exception;
        r1 = "GestureImageView";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Unable to open content: ";
        r2 = r2.append(r3);
        r2 = r2.append(r10);
        r2 = r2.toString();
        android.util.Log.w(r1, r2, r0);
        goto L_0x0081;
    L_0x00d2:
        r0 = r10.toString();
        r0 = android.graphics.drawable.Drawable.createFromPath(r0);
        r9.setImageDrawable(r0);
        goto L_0x0081;
    L_0x00de:
        r0 = move-exception;
        r1 = r6;
        goto L_0x00ad;
        */

    }

    public void setMaxScale(float r3f) {
        this.maxScale = r3f;
        if (this.gestureImageViewTouchListener != null) {
            this.gestureImageViewTouchListener.setMaxScale(this.startingScale * r3f);
        }
    }

    public void setMinScale(float r3f) {
        this.minScale = r3f;
        if (this.gestureImageViewTouchListener != null) {
            this.gestureImageViewTouchListener.setMinScale(this.fitScaleHorizontal * r3f);
        }
    }

    public void setOnClickListener(OnClickListener r2_OnClickListener) {
        this.onClickListener = r2_OnClickListener;
        if (this.gestureImageViewTouchListener != null) {
            this.gestureImageViewTouchListener.setOnClickListener(r2_OnClickListener);
        }
    }

    public void setOnTouchListener(OnTouchListener r1_OnTouchListener) {
        this.customOnTouchListener = r1_OnTouchListener;
    }

    public void setPosition(float r1f, float r2f) {
        this.x = r1f;
        this.y = r2f;
    }

    public void setRecycle(boolean r1z) {
        this.recycle = r1z;
    }

    public void setRotation(float r1f) {
        this.rotation = r1f;
    }

    public void setScale(float r1f) {
        this.scaleAdjust = r1f;
    }

    public void setScaleType(ScaleType r3_ScaleType) {
        if (r3_ScaleType == ScaleType.CENTER || r3_ScaleType == ScaleType.CENTER_CROP || r3_ScaleType == ScaleType.CENTER_INSIDE) {
            super.setScaleType(r3_ScaleType);
        } else if (this.strict) {
            throw new UnsupportedOperationException("Not supported");
        }
    }

    public void setSelected(boolean r3z) {
        if (this.strict) {
            throw new UnsupportedOperationException("Not supported");
        } else {
            super.setSelected(r3z);
        }
    }

    public void setStartingPosition(float r2f, float r3f) {
        this.startX = Float.valueOf(r2f);
        this.startY = Float.valueOf(r3f);
    }

    public void setStartingScale(float r1f) {
        this.startingScale = r1f;
    }

    public void setStrict(boolean r1z) {
        this.strict = r1z;
    }

    protected void setupCanvas(int r8i, int r9i, int r10i) {
        if (this.deviceOrientation != r10i) {
            this.layout = false;
            this.deviceOrientation = r10i;
        }
        if (this.drawable == null || this.layout) {
        } else {
            int r0i = getImageWidth();
            int r1i = getImageHeight();
            this.hWidth = Math.round(((float) r0i) / 2.0f);
            this.hHeight = Math.round(((float) r1i) / 2.0f);
            int r2i = r8i - getPaddingLeft() + getPaddingRight();
            int r3i = r9i - getPaddingTop() + getPaddingBottom();
            computeCropScale(r0i, r1i, r2i, r3i);
            if (this.startingScale <= 0.0f) {
                computeStartingScale(r0i, r1i, r2i, r3i);
            }
            this.scaleAdjust = this.startingScale;
            this.centerX = ((float) r2i) / 2.0f;
            this.centerY = ((float) r3i) / 2.0f;
            if (this.startX == null) {
                this.x = this.centerX;
            } else {
                this.x = this.startX.floatValue();
            }
            if (this.startY == null) {
                this.y = this.centerY;
            } else {
                this.y = this.startY.floatValue();
            }
            this.gestureImageViewTouchListener = new GestureImageViewTouchListener(this, r2i, r3i);
            if (isLandscape()) {
                this.gestureImageViewTouchListener.setMinScale(this.minScale * this.fitScaleHorizontal);
            } else {
                this.gestureImageViewTouchListener.setMinScale(this.minScale * this.fitScaleVertical);
            }
            this.gestureImageViewTouchListener.setMaxScale(this.maxScale * this.startingScale);
            this.gestureImageViewTouchListener.setFitScaleHorizontal(this.fitScaleHorizontal);
            this.gestureImageViewTouchListener.setFitScaleVertical(this.fitScaleVertical);
            this.gestureImageViewTouchListener.setCanvasWidth(r2i);
            this.gestureImageViewTouchListener.setCanvasHeight(r3i);
            this.gestureImageViewTouchListener.setOnClickListener(this.onClickListener);
            this.drawable.setBounds(-this.hWidth, -this.hHeight, this.hWidth, this.hHeight);
            super.setOnTouchListener(new a(this));
            this.layout = true;
        }
    }

    public boolean waitForDraw(long r3j) throws InterruptedException {
        return this.drawLock.tryAcquire(r3j, TimeUnit.MILLISECONDS);
    }
}