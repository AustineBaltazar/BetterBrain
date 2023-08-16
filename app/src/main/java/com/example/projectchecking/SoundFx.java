//package com.example.projectchecking;
//import android.app.Service;
//import android.content.Intent;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.os.IBinder;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//public class SoundFx extends Service {
//    MediaPlayer SfxPlayer;
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        SfxPlayer = MediaPlayer.create(this, R.raw.btn_sound);
//        SfxPlayer.setVolume(1, 1);
//    }
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        SfxPlayer.start();
//        return startId;
//    }
//    public void onStart(Intent intent, int startId) {
//    }
//    @Override
//    public void onDestroy() {
//        SfxPlayer.stop();
//        SfxPlayer.release();
//    }
//    @Override
//    public void onLowMemory() {
//    }
//
//
//}
