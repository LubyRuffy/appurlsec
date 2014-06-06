package org.apache.cordova;

import android.media.AudioManager;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.cordova.AudioPlayer.STATE;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import qsbk.app.widget.listview.XListViewHeader;

public class AudioHandler extends CordovaPlugin {
    public static String TAG;
    ArrayList<AudioPlayer> pausedForPhone;
    HashMap<String, AudioPlayer> players;

    static {
        TAG = "AudioHandler";
    }

    public AudioHandler() {
        this.players = new HashMap();
        this.pausedForPhone = new ArrayList();
    }

    private boolean release(String r3_String) {
        if (!this.players.containsKey(r3_String)) {
            return false;
        }
        this.players.remove(r3_String);
        ((AudioPlayer) this.players.get(r3_String)).destroy();
        return true;
    }

    public boolean execute(String r7_String, JSONArray r8_JSONArray, CallbackContext r9_CallbackContext) throws JSONException {
        int r1i = 0;
        Status r2_Status = Status.OK;
        String r3_String = RContactStorage.PRIMARY_KEY;
        if (r7_String.equals("startRecordingAudio")) {
            startRecordingAudio(r8_JSONArray.getString(r1i), FileUtils.stripFileProtocol(r8_JSONArray.getString(1)));
        } else if (r7_String.equals("stopRecordingAudio")) {
            stopRecordingAudio(r8_JSONArray.getString(r1i));
        } else if (r7_String.equals("startPlayingAudio")) {
            startPlayingAudio(r8_JSONArray.getString(r1i), FileUtils.stripFileProtocol(r8_JSONArray.getString(1)));
        } else if (r7_String.equals("seekToAudio")) {
            seekToAudio(r8_JSONArray.getString(r1i), r8_JSONArray.getInt(1));
        } else if (r7_String.equals("pausePlayingAudio")) {
            pausePlayingAudio(r8_JSONArray.getString(r1i));
        } else if (r7_String.equals("stopPlayingAudio")) {
            stopPlayingAudio(r8_JSONArray.getString(r1i));
        } else if (r7_String.equals("setVolume")) {
            try {
                setVolume(r8_JSONArray.getString(0), Float.parseFloat(r8_JSONArray.getString(1)));
            } catch (NumberFormatException e) {
            }
        } else if (r7_String.equals("getCurrentPositionAudio")) {
            r9_CallbackContext.sendPluginResult(new PluginResult(r2_Status, getCurrentPositionAudio(r8_JSONArray.getString(r1i))));
            return true;
        } else if (r7_String.equals("getDurationAudio")) {
            r9_CallbackContext.sendPluginResult(new PluginResult(r2_Status, getDurationAudio(r8_JSONArray.getString(r1i), r8_JSONArray.getString(1))));
            return true;
        } else if (r7_String.equals("create")) {
            String r1_String = r8_JSONArray.getString(r1i);
            this.players.put(r1_String, new AudioPlayer(this, r1_String, FileUtils.stripFileProtocol(r8_JSONArray.getString(1))));
        } else {
            if (!r7_String.equals("release")) {
                return false;
            }
            r9_CallbackContext.sendPluginResult(new PluginResult(r2_Status, release(r8_JSONArray.getString(r1i))));
            return true;
        }
        r9_CallbackContext.sendPluginResult(new PluginResult(r2_Status, r3_String));
        return true;
    }

    public int getAudioOutputDevice() {
        AudioManager r0_AudioManager = (AudioManager) this.cordova.getActivity().getSystemService("audio");
        if (r0_AudioManager.getRouting(0) == 1) {
            return 1;
        }
        if (r0_AudioManager.getRouting(0) == 2) {
            return 2;
        }
        return -1;
    }

    public float getCurrentPositionAudio(String r3_String) {
        AudioPlayer r0_AudioPlayer = (AudioPlayer) this.players.get(r3_String);
        return r0_AudioPlayer != null ? ((float) r0_AudioPlayer.getCurrentPosition()) / 1000.0f : -1.0f;
    }

    public float getDurationAudio(String r3_String, String r4_String) {
        AudioPlayer r0_AudioPlayer = (AudioPlayer) this.players.get(r3_String);
        if (r0_AudioPlayer != null) {
            return r0_AudioPlayer.getDuration(r4_String);
        }
        r0_AudioPlayer = new AudioPlayer(this, r3_String, r4_String);
        this.players.put(r3_String, r0_AudioPlayer);
        return r0_AudioPlayer.getDuration(r4_String);
    }

    public void onDestroy() {
        Iterator r1_Iterator = this.players.values().iterator();
        while (r1_Iterator.hasNext()) {
            ((AudioPlayer) r1_Iterator.next()).destroy();
        }
        this.players.clear();
    }

    public Object onMessage(String r6_String, Object r7_Object) {
        if (r6_String.equals("telephone")) {
            Iterator r1_Iterator;
            if ("ringing".equals(r7_Object) || "offhook".equals(r7_Object)) {
                r1_Iterator = this.players.values().iterator();
                while (r1_Iterator.hasNext()) {
                    AudioPlayer r0_AudioPlayer = (AudioPlayer) r1_Iterator.next();
                    if (r0_AudioPlayer.getState() == STATE.MEDIA_RUNNING.ordinal()) {
                        this.pausedForPhone.add(r0_AudioPlayer);
                        r0_AudioPlayer.pausePlaying();
                    }
                }
            } else if ("idle".equals(r7_Object)) {
                r1_Iterator = this.pausedForPhone.iterator();
                while (r1_Iterator.hasNext()) {
                    ((AudioPlayer) r1_Iterator.next()).startPlaying(null);
                }
                this.pausedForPhone.clear();
            }
        }
        return null;
    }

    public void onReset() {
        onDestroy();
    }

    public void pausePlayingAudio(String r2_String) {
        AudioPlayer r0_AudioPlayer = (AudioPlayer) this.players.get(r2_String);
        if (r0_AudioPlayer != null) {
            r0_AudioPlayer.pausePlaying();
        }
    }

    public void seekToAudio(String r2_String, int r3i) {
        AudioPlayer r0_AudioPlayer = (AudioPlayer) this.players.get(r2_String);
        if (r0_AudioPlayer != null) {
            r0_AudioPlayer.seekToPlaying(r3i);
        }
    }

    public void setAudioOutputDevice(int r7i) {
        AudioManager r0_AudioManager = (AudioManager) this.cordova.getActivity().getSystemService("audio");
        if (r7i == 2) {
            r0_AudioManager.setRouting(0, XListViewHeader.STATE_REFRESHING, -1);
        } else if (r7i == 1) {
            r0_AudioManager.setRouting(0, 1, -1);
        } else {
            System.out.println("AudioHandler.setAudioOutputDevice() Error: Unknown output device.");
        }
    }

    public void setVolume(String r4_String, float r5f) {
        AudioPlayer r0_AudioPlayer = (AudioPlayer) this.players.get(r4_String);
        if (r0_AudioPlayer != null) {
            r0_AudioPlayer.setVolume(r5f);
        } else {
            System.out.println("AudioHandler.setVolume() Error: Unknown Audio Player " + r4_String);
        }
    }

    public void startPlayingAudio(String r3_String, String r4_String) {
        AudioPlayer r0_AudioPlayer = (AudioPlayer) this.players.get(r3_String);
        if (r0_AudioPlayer == null) {
            r0_AudioPlayer = new AudioPlayer(this, r3_String, r4_String);
            this.players.put(r3_String, r0_AudioPlayer);
        }
        r0_AudioPlayer.startPlaying(r4_String);
    }

    public void startRecordingAudio(String r3_String, String r4_String) {
        AudioPlayer r0_AudioPlayer = (AudioPlayer) this.players.get(r3_String);
        if (r0_AudioPlayer == null) {
            r0_AudioPlayer = new AudioPlayer(this, r3_String, r4_String);
            this.players.put(r3_String, r0_AudioPlayer);
        }
        r0_AudioPlayer.startRecording(r4_String);
    }

    public void stopPlayingAudio(String r2_String) {
        AudioPlayer r0_AudioPlayer = (AudioPlayer) this.players.get(r2_String);
        if (r0_AudioPlayer != null) {
            r0_AudioPlayer.stopPlaying();
        }
    }

    public void stopRecordingAudio(String r2_String) {
        AudioPlayer r0_AudioPlayer = (AudioPlayer) this.players.get(r2_String);
        if (r0_AudioPlayer != null) {
            r0_AudioPlayer.stopRecording();
        }
    }
}