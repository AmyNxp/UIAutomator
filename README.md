Launching PoneUI
$ adb push C:\AndroidThings\AutoTest\uitest\PoneTest\app\build\outputs\apk\app-debug.apk /data/local/tmp/com.ponetest
$ adb shell pm install -r "/data/local/tmp/com.ponetest"
Success


$ adb push C:\AndroidThings\AutoTest\uitest\PoneTest\app\build\outputs\apk\app-debug-androidTest.apk /data/local/tmp/com.ponetest.test
$ adb shell pm install -r "/data/local/tmp/com.ponetest.test"
Success


Running tests

$ adb shell am instrument -w -r   -e debug false -e class com.ponetest.PoneUI com.ponetest.test/android.support.test.runner.AndroidJUnitRunner

function:
1.camera auto test, including taking pictures and recording video with different quality,
                              switch between front camera and back camera,
                              switch between camera and camorder,
                              press back while recording,
                              press home while recording
2.gallery auto test,including play/pause a video in gallery,
                    press home while video playing and opem gallery again to resume
                    slideshow picture
                    delete a file
3.music auto test,including play/pause a music
                  press home while playing,open music app again in notification
                    repeat/shuffle playback, and next song
4.Sound recorder auto test, including start/stop recording
                            replay the recorded sound
                            find and play recorded file in music/playlist/My recordings if save done
