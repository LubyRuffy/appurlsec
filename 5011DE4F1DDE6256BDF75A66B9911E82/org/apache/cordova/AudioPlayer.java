package org.apache.cordova;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.HttpUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class AudioPlayer implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private static final String LOG_TAG = "AudioPlayer";
    private static int MEDIA_DURATION;
    private static int MEDIA_ERROR;
    private static int MEDIA_ERR_ABORTED;
    private static int MEDIA_ERR_DECODE;
    private static int MEDIA_ERR_NETWORK;
    private static int MEDIA_ERR_NONE_ACTIVE;
    private static int MEDIA_ERR_NONE_SUPPORTED;
    private static int MEDIA_POSITION;
    private static int MEDIA_STATE;
    private String audioFile;
    private float duration;
    private AudioHandler handler;
    private String id;
    private MODE mode;
    private MediaPlayer player;
    private boolean prepareOnly;
    private MediaRecorder recorder;
    private int seekOnPrepared;
    private STATE state;
    private String tempFile;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$cordova$AudioPlayer$MODE;
        static final /* synthetic */ int[] $SwitchMap$org$apache$cordova$AudioPlayer$STATE;

        static {
            $SwitchMap$org$apache$cordova$AudioPlayer$STATE = new int[org.apache.cordova.AudioPlayer.STATE.values().length];
            try {
                $SwitchMap$org$apache$cordova$AudioPlayer$STATE[org.apache.cordova.AudioPlayer.STATE.MEDIA_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$apache$cordova$AudioPlayer$STATE[org.apache.cordova.AudioPlayer.STATE.MEDIA_LOADING.ordinal()] = 2;
            } catch (NoSuchFieldError e_2) {
            }
            try {
                $SwitchMap$org$apache$cordova$AudioPlayer$STATE[org.apache.cordova.AudioPlayer.STATE.MEDIA_STARTING.ordinal()] = 3;
            } catch (NoSuchFieldError e_3) {
            }
            try {
                $SwitchMap$org$apache$cordova$AudioPlayer$STATE[org.apache.cordova.AudioPlayer.STATE.MEDIA_RUNNING.ordinal()] = 4;
            } catch (NoSuchFieldError e_4) {
            }
            try {
                $SwitchMap$org$apache$cordova$AudioPlayer$STATE[org.apache.cordova.AudioPlayer.STATE.MEDIA_PAUSED.ordinal()] = 5;
            } catch (NoSuchFieldError e_5) {
            }
            try {
                $SwitchMap$org$apache$cordova$AudioPlayer$STATE[org.apache.cordova.AudioPlayer.STATE.MEDIA_STOPPED.ordinal()] = 6;
            } catch (NoSuchFieldError e_6) {
            }
            $SwitchMap$org$apache$cordova$AudioPlayer$MODE = new int[org.apache.cordova.AudioPlayer.MODE.values().length];
            try {
                $SwitchMap$org$apache$cordova$AudioPlayer$MODE[org.apache.cordova.AudioPlayer.MODE.PLAY.ordinal()] = 1;
            } catch (NoSuchFieldError e_7) {
            }
            try {
                $SwitchMap$org$apache$cordova$AudioPlayer$MODE[org.apache.cordova.AudioPlayer.MODE.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError e_8) {
            }
            $SwitchMap$org$apache$cordova$AudioPlayer$MODE[org.apache.cordova.AudioPlayer.MODE.RECORD.ordinal()] = 3;
        }
    }

    public static enum MODE {
        NONE,
        PLAY,
        RECORD;

    }

    public static enum STATE {
        MEDIA_NONE,
        MEDIA_STARTING,
        MEDIA_RUNNING,
        MEDIA_PAUSED,
        MEDIA_STOPPED,
        MEDIA_LOADING;

    }

    static {
        MEDIA_STATE = 1;
        MEDIA_DURATION = 2;
        MEDIA_POSITION = 3;
        MEDIA_ERROR = 9;
        MEDIA_ERR_NONE_ACTIVE = 0;
        MEDIA_ERR_ABORTED = 1;
        MEDIA_ERR_NETWORK = 2;
        MEDIA_ERR_DECODE = 3;
        MEDIA_ERR_NONE_SUPPORTED = 4;
    }

    public AudioPlayer(AudioHandler r3_AudioHandler, String r4_String, String r5_String) {
        this.mode = MODE.NONE;
        this.state = STATE.MEDIA_NONE;
        this.audioFile = null;
        this.duration = -1.0f;
        this.recorder = null;
        this.tempFile = null;
        this.player = null;
        this.prepareOnly = true;
        this.seekOnPrepared = 0;
        this.handler = r3_AudioHandler;
        this.id = r4_String;
        this.audioFile = r5_String;
        this.recorder = new MediaRecorder();
        if (Environment.getExternalStorageState().equals("mounted")) {
            this.tempFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmprecording.3gp";
        } else {
            this.tempFile = "/data/data/" + r3_AudioHandler.cordova.getActivity().getPackageName() + "/cache/tmprecording.3gp";
        }
    }

    private float getDurationInSeconds() {
        return ((float) this.player.getDuration()) / 1000.0f;
    }

    private void loadAudioFile(String r7_String) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException {
        if (isStreaming(r7_String)) {
            this.player.setDataSource(r7_String);
            this.player.setAudioStreamType(XListViewFooter.STATE_NOMORE);
            setMode(MODE.PLAY);
            setState(STATE.MEDIA_STARTING);
            this.player.setOnPreparedListener(this);
            this.player.prepareAsync();
        } else {
            if (r7_String.startsWith("/android_asset/")) {
                AssetFileDescriptor r4_AssetFileDescriptor = this.handler.cordova.getActivity().getAssets().openFd(r7_String.substring(NearbySelectView.TIME_15MIN));
                this.player.setDataSource(r4_AssetFileDescriptor.getFileDescriptor(), r4_AssetFileDescriptor.getStartOffset(), r4_AssetFileDescriptor.getLength());
            } else if (new File(r7_String).exists()) {
                this.player.setDataSource(new FileInputStream(r7_String).getFD());
            } else {
                this.player.setDataSource("/sdcard/" + r7_String);
            }
            setState(STATE.MEDIA_STARTING);
            this.player.setOnPreparedListener(this);
            this.player.prepare();
            this.duration = getDurationInSeconds();
        }
    }

    private boolean playMode() {
        switch (AnonymousClass_1.$SwitchMap$org$apache$cordova$AudioPlayer$MODE[this.mode.ordinal()]) {
            case XListViewHeader.STATE_REFRESHING:
                setMode(MODE.PLAY);
                break;
            case XListViewFooter.STATE_NOMORE:
                Log.d(LOG_TAG, "AudioPlayer Error: Can't play in record mode.");
                this.handler.webView.sendJavascript("cordova.require('cordova/plugin/Media').onStatus('" + this.id + "', " + MEDIA_ERROR + ", { \"code\":" + MEDIA_ERR_ABORTED + "});");
                return false;
        }
        return true;
    }

    private boolean readyPlayer(String r5_String) {
        boolean r0z = false;
        if (!playMode()) {
            return false;
        }
        switch (AnonymousClass_1.$SwitchMap$org$apache$cordova$AudioPlayer$STATE[this.state.ordinal()]) {
            case XListViewHeader.STATE_READY:
                if (this.player == null) {
                    this.player = new MediaPlayer();
                }
                try {
                    loadAudioFile(r5_String);
                    return false;
                } catch (Exception e) {
                    this.handler.webView.sendJavascript("cordova.require('cordova/plugin/Media').onStatus('" + this.id + "', " + MEDIA_ERROR + ", { \"code\":" + MEDIA_ERR_ABORTED + "});");
                    return false;
                }
            case XListViewHeader.STATE_REFRESHING:
                Log.d(LOG_TAG, "AudioPlayer Loading: startPlaying() called during media preparation: " + STATE.MEDIA_STARTING.ordinal());
                this.prepareOnly = false;
                return false;
            case XListViewFooter.STATE_NOMORE:
            case XListViewFooter.STATE_NODATA:
            case ShareUtils.SHARE_SMS:
                return true;
            case ShareUtils.SHARE_COPY:
                if (this.audioFile.compareTo(r5_String) == 0) {
                    this.player.seekTo(r0z);
                    this.player.pause();
                    return true;
                } else {
                    this.player.reset();
                    try {
                        loadAudioFile(r5_String);
                        return false;
                    } catch (Exception e_2) {
                        this.handler.webView.sendJavascript("cordova.require('cordova/plugin/Media').onStatus('" + this.id + "', " + MEDIA_ERROR + ", { \"code\":" + MEDIA_ERR_ABORTED + "});");
                        return false;
                    }
                }
        }
        Log.d(LOG_TAG, "AudioPlayer Error: startPlaying() called during invalid state: " + this.state);
        this.handler.webView.sendJavascript("cordova.require('cordova/plugin/Media').onStatus('" + this.id + "', " + MEDIA_ERROR + ", { \"code\":" + MEDIA_ERR_ABORTED + "});");
        return false;
    }

    private void setMode(MODE r2_MODE) {
        if (this.mode != r2_MODE) {
            this.mode = r2_MODE;
        } else {
            this.mode = r2_MODE;
        }
    }

    private void setState(STATE r4_STATE) {
        if (this.state != r4_STATE) {
            this.handler.webView.sendJavascript("cordova.require('cordova/plugin/Media').onStatus('" + this.id + "', " + MEDIA_STATE + ", " + r4_STATE.ordinal() + ");");
        }
        this.state = r4_STATE;
    }

    public void destroy() {
        if (this.player != null) {
            if (this.state == STATE.MEDIA_RUNNING || this.state == STATE.MEDIA_PAUSED) {
                this.player.stop();
                setState(STATE.MEDIA_STOPPED);
                this.player.release();
                this.player = null;
            } else {
                this.player.release();
                this.player = null;
            }
        }
        if (this.recorder != null) {
            stopRecording();
            this.recorder.release();
            this.recorder = null;
        }
    }

    public long getCurrentPosition() {
        if (this.state != STATE.MEDIA_RUNNING && this.state != STATE.MEDIA_PAUSED) {
            return -1;
        }
        int r0i = this.player.getCurrentPosition();
        this.handler.webView.sendJavascript("cordova.require('cordova/plugin/Media').onStatus('" + this.id + "', " + MEDIA_POSITION + ", " + (((float) r0i) / 1000.0f) + ");");
        return (long) r0i;
    }

    public float getDuration(String r2_String) {
        if (this.recorder != null) {
            return -2.0f;
        }
        if (this.player != null) {
            return this.duration;
        }
        this.prepareOnly = true;
        startPlaying(r2_String);
        return this.duration;
    }

    public int getState() {
        return this.state.ordinal();
    }

    public boolean isStreaming(String r2_String) {
        return r2_String.contains(HttpUtils.http) || r2_String.contains(HttpUtils.https);
    }

    public void moveFile(String r5_String) {
        File r0_File = new File(this.tempFile);
        if (Environment.getExternalStorageState().equals("mounted")) {
            r0_File.renameTo(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + r5_String));
        } else {
            r0_File.renameTo(new File("/data/data/" + this.handler.cordova.getActivity().getPackageName() + "/cache/" + r5_String));
        }
    }

    public void onCompletion(MediaPlayer r3_MediaPlayer) {
        Log.d(LOG_TAG, "on completion is calling stopped");
        setState(STATE.MEDIA_STOPPED);
    }

    public boolean onError(MediaPlayer r4_MediaPlayer, int r5i, int r6i) {
        Log.d(LOG_TAG, "AudioPlayer.onError(" + r5i + ", " + r6i + ")");
        this.player.stop();
        this.player.release();
        this.handler.webView.sendJavascript("cordova.require('cordova/plugin/Media').onStatus('" + this.id + "', { \"code\":" + r5i + "});");
        return false;
    }

    public void onPrepared(MediaPlayer r4_MediaPlayer) {
        this.player.setOnCompletionListener(this);
        seekToPlaying(this.seekOnPrepared);
        if (this.prepareOnly) {
            setState(STATE.MEDIA_STARTING);
        } else {
            this.player.start();
            setState(STATE.MEDIA_RUNNING);
            this.seekOnPrepared = 0;
        }
        this.duration = getDurationInSeconds();
        this.prepareOnly = true;
        this.handler.webView.sendJavascript("cordova.require('cordova/plugin/Media').onStatus('" + this.id + "', " + MEDIA_DURATION + "," + this.duration + ");");
    }

    public void pausePlaying() {
        if (this.state != STATE.MEDIA_RUNNING || this.player == null) {
            Log.d(LOG_TAG, "AudioPlayer Error: pausePlaying() called during invalid state: " + this.state.ordinal());
            this.handler.webView.sendJavascript("cordova.require('cordova/plugin/Media').onStatus('" + this.id + "', " + MEDIA_ERROR + ", { \"code\":" + MEDIA_ERR_NONE_ACTIVE + "});");
        } else {
            this.player.pause();
            setState(STATE.MEDIA_PAUSED);
        }
    }

    public void seekToPlaying(int r5i) {
        if (readyPlayer(this.audioFile)) {
            this.player.seekTo(r5i);
            Log.d(LOG_TAG, "Send a onStatus update for the new seek");
            this.handler.webView.sendJavascript("cordova.require('cordova/plugin/Media').onStatus('" + this.id + "', " + MEDIA_POSITION + ", " + (((float) r5i) / 1000.0f) + ");");
        } else {
            this.seekOnPrepared = r5i;
        }
    }

    public void setVolume(float r2f) {
        this.player.setVolume(r2f, r2f);
    }

    public void startPlaying(String r3_String) {
        if ((!readyPlayer(r3_String)) || this.player == null) {
            this.prepareOnly = false;
        } else {
            this.player.start();
            setState(STATE.MEDIA_RUNNING);
            this.seekOnPrepared = 0;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startRecording(String r4_String) {
        /*
        r3_this = this;
        r2 = 0;
        r0 = org.apache.cordova.AudioPlayer.AnonymousClass_1.$SwitchMap$org$apache$cordova$AudioPlayer$MODE;
        r1 = r3.mode;
        r1 = r1.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x000f;
            case 2: goto L_0x0051;
            case 3: goto L_0x00bf;
            default: goto L_0x000e;
        };
    L_0x000e:
        return;
    L_0x000f:
        r0 = "AudioPlayer";
        r1 = "AudioPlayer Error: Can't record in play mode.";
        android.util.Log.d(r0, r1);
        r0 = r3.handler;
        r0 = r0.webView;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "cordova.require('cordova/plugin/Media').onStatus('";
        r1 = r1.append(r2);
        r2 = r3.id;
        r1 = r1.append(r2);
        r2 = "', ";
        r1 = r1.append(r2);
        r2 = MEDIA_ERROR;
        r1 = r1.append(r2);
        r2 = ", { \"code\":";
        r1 = r1.append(r2);
        r2 = MEDIA_ERR_ABORTED;
        r1 = r1.append(r2);
        r2 = "});";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.sendJavascript(r1);
        goto L_0x000e;
    L_0x0051:
        r3.audioFile = r4;
        r0 = r3.recorder;
        r1 = 1;
        r0.setAudioSource(r1);
        r0 = r3.recorder;
        r0.setOutputFormat(r2);
        r0 = r3.recorder;
        r0.setAudioEncoder(r2);
        r0 = r3.recorder;
        r1 = r3.tempFile;
        r0.setOutputFile(r1);
        r0 = r3.recorder;	 //Catch:{ IllegalStateException -> 0x007a, IOException -> 0x00ba }
        r0.prepare();	 //Catch:{ IllegalStateException -> 0x007a, IOException -> 0x00ba }
        r0 = r3.recorder;	 //Catch:{ IllegalStateException -> 0x007a, IOException -> 0x00ba }
        r0.start();	 //Catch:{ IllegalStateException -> 0x007a, IOException -> 0x00ba }
        r0 = org.apache.cordova.AudioPlayer.STATE.MEDIA_RUNNING;	 //Catch:{ IllegalStateException -> 0x007a, IOException -> 0x00ba }
        r3.setState(r0);	 //Catch:{ IllegalStateException -> 0x007a, IOException -> 0x00ba }
        goto L_0x000e;
    L_0x007a:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x007e:
        r0 = r3.handler;
        r0 = r0.webView;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "cordova.require('cordova/plugin/Media').onStatus('";
        r1 = r1.append(r2);
        r2 = r3.id;
        r1 = r1.append(r2);
        r2 = "', ";
        r1 = r1.append(r2);
        r2 = MEDIA_ERROR;
        r1 = r1.append(r2);
        r2 = ", { \"code\":";
        r1 = r1.append(r2);
        r2 = MEDIA_ERR_ABORTED;
        r1 = r1.append(r2);
        r2 = "});";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.sendJavascript(r1);
        goto L_0x000e;
    L_0x00ba:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x007e;
    L_0x00bf:
        r0 = "AudioPlayer";
        r1 = "AudioPlayer Error: Already recording.";
        android.util.Log.d(r0, r1);
        r0 = r3.handler;
        r0 = r0.webView;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "cordova.require('cordova/plugin/Media').onStatus('";
        r1 = r1.append(r2);
        r2 = r3.id;
        r1 = r1.append(r2);
        r2 = "', ";
        r1 = r1.append(r2);
        r2 = MEDIA_ERROR;
        r1 = r1.append(r2);
        r2 = ", { \"code\":";
        r1 = r1.append(r2);
        r2 = MEDIA_ERR_ABORTED;
        r1 = r1.append(r2);
        r2 = "});";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.sendJavascript(r1);
        goto L_0x000e;
        */

    }

    public void stopPlaying() {
        if (this.state == STATE.MEDIA_RUNNING || this.state == STATE.MEDIA_PAUSED) {
            this.player.pause();
            this.player.seekTo(0);
            Log.d(LOG_TAG, "stopPlaying is calling stopped");
            setState(STATE.MEDIA_STOPPED);
        } else {
            Log.d(LOG_TAG, "AudioPlayer Error: stopPlaying() called during invalid state: " + this.state.ordinal());
            this.handler.webView.sendJavascript("cordova.require('cordova/plugin/Media').onStatus('" + this.id + "', " + MEDIA_ERROR + ", { \"code\":" + MEDIA_ERR_NONE_ACTIVE + "});");
        }
    }

    public void stopRecording() {
        if (this.recorder != null) {
            try {
                if (this.state == STATE.MEDIA_RUNNING) {
                    this.recorder.stop();
                    setState(STATE.MEDIA_STOPPED);
                }
                this.recorder.reset();
                moveFile(this.audioFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}