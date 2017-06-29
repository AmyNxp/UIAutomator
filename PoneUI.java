package com.ponetest;

import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.lang.Thread.sleep;

/**
 * Created by nxf31430 on 6/27/2017.
 */
@RunWith(AndroidJUnit4.class)
public class PoneUI {
        private UiDevice mDevice;
        private static final String TAG1 = "Camera";
        private static final String TAG2 = "Gallery";
        private static final String TAG3 = "Music";
        private static final String TAG4 = "Sound Recorder";
        private static final String TAG5 = "Settings";

        @Before
        public void setUp() throws RemoteException, InterruptedException {
            mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());  //获得device对象

            if (!mDevice.isScreenOn()) {  //唤醒屏幕
                mDevice.wakeUp();
                mDevice.swipe(600, 600, 600, 200, 15);
            }
        }
        @Test
        public void BATCase() throws Exception {
             Log.i(TAG1, "test begin !!!!!!!!!!!");
             CameraTest();
             Log.i(TAG2, "test begin !!!!!!!!!!!");
             GalleryTest();
             Log.i(TAG3, "test begin !!!!!!!!!!!");
             MusicTest();
             Log.i(TAG4, "test begin !!!!!!!!!!!");
             RecorderTest();
            //GUITest();
             Log.i(TAG5, "test begin !!!!!!!!!!!");
             SettingTest();
        }
        public void CameraTest() throws Exception{
            OpenAPP(TAG1);
            mDevice.swipe(0,360,512,360,50);     //video record
            sleep(2000);
            mDevice.findObject(By.text("Camera")).click();
            sleep(4000);
            mDevice.findObject(By.res("com.android.camera2:id/mode_options_toggle")).click();
            sleep(1000);
            mDevice.findObject(By.res("com.android.camera2:id/camera_toggle_button")).click();
            sleep(3000);
            mDevice.findObject(By.res("com.android.camera2:id/shutter_button")).click();
            sleep(3000);
            if(!mDevice.hasObject(By.res("com.android.camera2:id/rounded_thumbnail_view"))){
                Log.i(TAG1, "take photo fail!!");
            }
            //take pictures with different qualities
            String PictureQaulity[] = {"(4:3) 5.0 megapixels","(4:3) 0.8 megapixels","(4:3) 0.3 megapixels",
                    "(16:9) 2.1 megapixels","(16:9) 0.9 megapixels"};
            for(int i=0;i<5;i++){
                takePicture(PictureQaulity[i]);
            }

            mDevice.swipe(0,360,512,360,50);     //video record
            sleep(2000);
            mDevice.findObject(By.text("Video")).click();
            sleep(3000);
            mDevice.findObject(By.res("com.android.camera2:id/shutter_button")).click();
            sleep(10000);
            mDevice.click(940,360);
            sleep(3000);
            if(!mDevice.hasObject(By.res("com.android.camera2:id/rounded_thumbnail_view"))){
                Log.i(TAG1, "video record fail!!");
            }
            //record videoes with different qualities
            String VideoQaulity[] = {"HD 1080p","HD 720p","SD 480p"};
            for(int i=0;i<3;i++){
                recordVideo(VideoQaulity[i]);
            }
            //Touch the screen during recording to take pictures
            mDevice.findObject(By.res("com.android.camera2:id/shutter_button")).click();
            sleep(4000);
            mDevice.click(512,360);
            sleep(3000);
            mDevice.click(940,360);
            sleep(2000);

            //press back while recording
            mDevice.findObject(By.res("com.android.camera2:id/shutter_button")).click();
            sleep(4000);
            mDevice.pressBack();
            sleep(2000);

            //press home while recording
            mDevice.findObject(By.res("com.android.camera2:id/shutter_button")).click();
            sleep(4000);
            mDevice.pressHome();
            OpenAPP(TAG1);

            CloseAPP(TAG1);
        }

        //copy 10 media files to /sdcard/picture and /sdcard/movie
        public void GalleryTest() throws Exception{
            OpenAPP(TAG2);
            //video
            mDevice.click(500,300);
            sleep(1000);
            mDevice.click(500,300);
            sleep(1000);
            mDevice.click(512,360);
            sleep(2000);
            if(mDevice.hasObject(By.text("Video player"))) {
                mDevice.findObject(By.text("Video player")).click();
            }else{
                mDevice.findObject(By.text("Open with Video player")).click();
            }
            sleep(1000);
            mDevice.findObject(By.text("JUST ONCE")).click();
            sleep(1000);
            if(mDevice.hasObject(By.text("Start over"))){
                mDevice.findObject(By.text("Start over")).click();
            }
            sleep(4000);
            mDevice.click(512,360);
            sleep(500);
            mDevice.click(512,360);         //pause video
            sleep(5000);
            mDevice.click(512,360);         //resume video
            sleep(15*1000);
            mDevice.pressHome();
            sleep(5000);
            mDevice.pressRecentApps();
            sleep(2000);
            mDevice.findObject(By.text(TAG2)).click();
            sleep(15*1000);
            mDevice.pressBack();
            mDevice.pressBack();
            mDevice.pressBack();
            sleep(1000);

            //picture
            mDevice.click(512,510);
            sleep(1000);
            mDevice.click(1000,30);
            sleep(1000);
            mDevice.findObject(By.text("Slideshow")).click();
            sleep(15*1000);
            mDevice.click(512,360);
            sleep(1000);
            longClick(520,400,50);
            sleep(1000);
            mDevice.findObject(By.res("com.android.gallery3d:id/action_delete")).click();
            sleep(1000);
            mDevice.findObject(By.text("OK")).click();
            sleep(1000);
            mDevice.click(520,280);
            sleep(1000);
            mDevice.click(520,360);  //双击放大图片
            mDevice.click(520,360);
            sleep(1000);
            mDevice.click(520,360);  //双击缩小图片
            mDevice.click(520,360);
            sleep(500);
            if(mDevice.hasObject(By.desc("More options"))){
                mDevice.click(990,25);
            }else{
                mDevice.click(512,360);
                sleep(500);
                mDevice.click(990,25);
            }
            sleep(1000);
            mDevice.findObject(By.text("Delete")).click();
            sleep(1000);
            mDevice.findObject(By.text("OK")).click();
            sleep(1000);
            CloseAPP(TAG2);
        }


        public void MusicTest() throws Exception{
            OpenAPP(TAG3);
            mDevice.findObject(By.text("Songs")).click();
            sleep(1000);
            mDevice.findObject(By.res("com.android.music:id/line1")).click();
            sleep(10*1000);
            mDevice.click(512,635);     //pause the music
            sleep(10*1000);
            mDevice.click(512,635);     //resume the music
            sleep(10*1000);
            mDevice.pressHome();
            sleep(5*1000);
            mDevice.swipe(600, 0, 600, 600, 60);
            sleep(2000);
            mDevice.findObject(By.res("com.android.music:id/artistalbum")).click();
            sleep(10*1000);
            mDevice.click(870,690);
            sleep(10*1000);
            mDevice.click(450,60);         //repeat the music
            sleep(10*1000);
            for(int i=0;i<4;i++) {
                mDevice.click(570, 670);        //next one
                sleep(20 * 1000);
                mDevice.click(450,60);
                sleep(10*1000);
            }
            mDevice.click(330,60);          //shuffle
            sleep(10*1000);
            for(int j=0;j<4;j++) {
                mDevice.click(570, 670);        //next one
                sleep(20*1000);
                mDevice.click(330,60);
                sleep(10*1000);
            }
            CloseAPP(TAG3);
        }


        public void RecorderTest() throws Exception{
            OpenAPP(TAG3);
            sleep(1000);
            mDevice.findObject(By.text("Playlists")).click();
            sleep(1000);
            if(mDevice.hasObject(By.text("My recordings"))){
                longClick(300,180,50);
                sleep(1000);
                mDevice.findObject(By.text("Delete")).click();
                sleep(1000);
            }else{
                mDevice.pressHome();
                sleep(1000);
            }
            OpenAPP(TAG4);
            mDevice.findObject(By.res("com.android.soundrecorder:id/recordButton")).click();
            sleep(15*1000);
            mDevice.findObject(By.res("com.android.soundrecorder:id/stopButton")).click();
            sleep(1000);
            mDevice.findObject(By.res("com.android.soundrecorder:id/playButton")).click();
            sleep(15*1000);
            mDevice.findObject(By.res("com.android.soundrecorder:id/discardButton")).click();
            sleep(1000);

            OpenAPP(TAG4);
            mDevice.findObject(By.res("com.android.soundrecorder:id/recordButton")).click();
            sleep(15*1000);
            mDevice.findObject(By.res("com.android.soundrecorder:id/stopButton")).click();
            sleep(1000);
            mDevice.findObject(By.res("com.android.soundrecorder:id/acceptButton")).click();
            sleep(1000);

            OpenAPP(TAG3);
            sleep(1000);
            mDevice.findObject(By.text("Playlists")).click();
            if(mDevice.hasObject(By.text("My recordings"))){
                mDevice.findObject(By.text("My recordings")).click();
                sleep(1000);
                mDevice.findObject(By.res("com.android.music:id/line1")).click();
                sleep(10*1000);
            }else{
                Log.i(TAG4, "recording file saving failed !!!");
            }
            mDevice.pressHome();
            CloseAPP(TAG3);
            CloseAPP(TAG4);
        }


        public void SettingTest() throws Exception{
            OpenAPP(TAG5);
            String TimeDSC[] = {"15 seconds","30 seconds","1 minute","2 minutes","5 minutes"};
            long time[] = {15,30,60,120,300};
            mDevice.findObject(By.text("Display")).click();
            sleep(1000);

            for(int i=0;i<5;i++) {
                mDevice.findObject(By.text("Sleep")).click();
                sleep(1000);
                mDevice.findObject(By.text(TimeDSC[i])).click();
                sleep((time[i]+1) * 1000);
                if (!mDevice.isScreenOn()) {
                    mDevice.wakeUp();
                  //  mDevice.swipe(600, 600, 600, 200, 15);
                    Log.i(TAG5, "sleep  " +TimeDSC[i]+"  pass");
                }else{
                    Log.i(TAG5, "sleep  " +TimeDSC[i]+"  fail!!!!!!!");
                }
            }
            CloseAPP(TAG5);
        }

        public void OpenAPP(String APPName) throws Exception{
            mDevice.pressHome();  //按home键
            sleep(1000);
            UiObject2 APPIcon = mDevice.findObject(By.desc("Apps"));
            APPIcon.click();
            sleep(2000);
            mDevice.findObject(By.text(APPName)).click();
            sleep(3000);
            if(mDevice.hasObject(By.res("com.android.packageinstaller:id/permission_allow_button"))){
                mDevice.findObject(By.res("com.android.packageinstaller:id/permission_allow_button")).click();
                sleep(1000);
                if(mDevice.hasObject(By.res("com.android.packageinstaller:id/permission_allow_button"))){
                    mDevice.findObject(By.res("com.android.packageinstaller:id/permission_allow_button")).click();
                    sleep(2000);
                }else if(mDevice.hasObject(By.res("com.android.camera2:id/confirm_button"))){
                    mDevice.findObject(By.res("com.android.camera2:id/confirm_button")).click();
                    sleep(3000);
                }else{
                    return;
                }
            }
        }

        public void CloseAPP(String APPName) throws Exception{
            mDevice.pressRecentApps();
            sleep(2000);
            UiObject2 app = mDevice.findObject(By.text(APPName));
            app.fling(Direction.RIGHT,5000);
            sleep(1000);
            mDevice.pressHome();
        }

        public void takePicture(String quality) throws Exception{
            mDevice.swipe(0,360,512,360,50);
            sleep(1000);
            mDevice.findObject(By.res("com.android.camera2:id/settings_button")).click();
            sleep(1000);
            mDevice.findObject(By.text("Resolution & quality")).click();
            sleep(1000);
            mDevice.findObject(By.text("Back camera photo")).click();
            sleep(1000);
            if(mDevice.hasObject(By.text(quality))) {
                mDevice.findObject(By.text(quality)).click();
            }else{
                mDevice.pressBack();
            }
            sleep(1000);
            mDevice.findObject(By.text("Front camera photo")).click();
            sleep(1000);
            if(mDevice.hasObject(By.text(quality))) {
                mDevice.findObject(By.text(quality)).click();
            }else{
                mDevice.pressBack();
            }
            sleep(1000);
            mDevice.pressBack();
            mDevice.pressBack();
            mDevice.findObject(By.res("com.android.camera2:id/shutter_button")).click();
            sleep(3000);
            mDevice.findObject(By.res("com.android.camera2:id/mode_options_toggle")).click();
            sleep(1000);
            mDevice.findObject(By.res("com.android.camera2:id/camera_toggle_button")).click();
            sleep(3000);
            mDevice.findObject(By.res("com.android.camera2:id/shutter_button")).click();
            sleep(3000);
        }

        public void recordVideo(String quality) throws Exception{
            mDevice.swipe(0,360,512,360,10);
            sleep(3000);
            if(!mDevice.hasObject(By.res("com.android.camera2:id/settings_button"))){
                mDevice.click(940,360);//stop recording
                sleep(3000);
            }
            mDevice.findObject(By.res("com.android.camera2:id/settings_button")).click();
            sleep(1000);
            mDevice.findObject(By.text("Resolution & quality")).click();
            sleep(1000);
            mDevice.findObject(By.text("Back camera video")).click();
            sleep(1000);
            if(mDevice.hasObject(By.text(quality))) {
                mDevice.findObject(By.text(quality)).click();
            }else{
                mDevice.pressBack();
            }
            sleep(1000);
            mDevice.findObject(By.text("Front camera video")).click();
            sleep(1000);
            if(mDevice.hasObject(By.text(quality))) {
                mDevice.findObject(By.text(quality)).click();
            }else{
                mDevice.pressBack();
            }
            sleep(1000);
            mDevice.pressBack();
            mDevice.pressBack();
            mDevice.findObject(By.res("com.android.camera2:id/shutter_button")).click();//start recording
            sleep(10*1000);
            mDevice.click(940,360);//stop recording
            sleep(3000);
            if(!mDevice.hasObject(By.res("com.android.camera2:id/mode_options_toggle"))) {
                mDevice.click(940,360);//stop recording
                sleep(3000);
            }
            mDevice.findObject(By.res("com.android.camera2:id/mode_options_toggle")).click();
            sleep(1000);
            mDevice.findObject(By.res("com.android.camera2:id/camera_toggle_button")).click();
            sleep(3000);
            mDevice.findObject(By.res("com.android.camera2:id/shutter_button")).click();
            sleep(10*1000);
            mDevice.click(940,360);//stop recording
            sleep(3000);
        }

        public void longClick(int x,int y,int step) throws Exception {
            mDevice.swipe(x,y,x,y,step);
        }

}
