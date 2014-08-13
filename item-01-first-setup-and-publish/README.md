# Android Wear - Item 01 - First setup and publish #

Here's the most basic walkthrough that you'll need to quickly get started with Android Wear. This assumes that you are using Android Studio.

The source code in this directory is the exact same that you would get from going through this walkthrough using the Android Studio new project wizard.

Steps:
1.0. Download the Wear SDK
2.0. Create the Wear emulator
3.0. Connect and run Google's Android Wear demo app
4.0. Create and run the "Hello, World" app
5.0. Understand publishing


## 1.0. Download the Wear SDK ##

1.1. In Android Studio, open `Tools->Android->SDK Manager`
1.2. Install each of the following packages:
  - Android SDK Tools
  - Android SDK Platform-tools
  - Android SDK Build-tools (Rev. 20+)
  - All of Android 4.4W (API 20)
  - And possibly more if you want to have a lower minimum API level


## 2.0. Create the Wear emulator ##

2.1. In Android Studio, open `Tools->Android->AVD Manager->Create`
2.2. Create the Android Virtual Device with the following values, and leave the rest as the default:
  - AVD Name: "ExampleWearDevice"
  - Device: Choose either "Android Wear Round" or "Android Wear Square"
  - Target: Android 4.4W - API Level 20
  - CPU/ABI: Android Wear ARM (armeabi-v7a)
  - Keyboard: Choose "Hardware keyboard present"
  - Skin: Choose either "AndroidWearRound" or "AndroidWearSquare"
  - Snapshop: Not selected
  - Use Host GPU: Selected
2.3. Click "OK"
2.4. To start the Wear emulator: select the virtual device, click "Start", then "Launch". (It may take a few minutes for the emulator to first load and show the basic usage tutorial)


## 3.0. Connect and run Google's Android Wear demo app ##

3.1. Connect handheld device to computer and make sure that the computer recognizes it. (Install any drivers that you have to in order to get the device recognized)
3.2. Connect wearable emulator to handheld device via `adb -d forward tcp:5601 tcp:5601`.
3.3. Install Google's (Android Wear)[https://play.google.com/store/apps/details?id=com.google.android.wearable.app] on your handheld device and start the app.
3.4. Go through the app until you are on the "Choose a device" screen. In the top-right, click the gear, then click "Pair with emulator".
3.5. On the new page, the top-left should say "Emulator connected". On the top right, click the overflow menu, then "Demo cards". On this "Demo cards" page, you can select the different options to have notifications sent to the wearable device.
3.6. Play around until you get a good feel for how Android Wear works and what are some of the possibilities for it.


## 4.0. Run the "Hello, World" app on the wearable ##
The default Wear project from the Android Studio is enough to display "Hello, World". So, that's what this walkthrough is going to be about.

4.1. Click "New Project".
4.2. Choose an application name and click "Next".
4.3. Select BOTH "Phone and Tablet" and "Wear" form factors and click "Next".
4.4. For the next few screens, select "Blank Activity" and the default Activity name for the handheld, then the wearable. Then, click "Finish".
4.5. You should now have two modules: "mobile" and "wear". On the top menu bar, select "wear" for the run configurations, then click the play button. After the project compiles, the Wear app will be pushed to Wear emulator. If that doesn't happen, then please make sure steps 2 and 3 have already been completed.

Note: This default app works for both round and square layouts, and has a different layout for each of them, referenced by a View Stub in /wear/src/main/res/layout/activity_my.xml


## 5.0. Understand publishing ##

- For debug configurations, the Wear app can be pushed directly to the wearable device/emulator.
- For publish/release configurations, the Wear app can only get to the wearable via the handheld app that holds on to the wear app until the wearable is synced with the handheld.
- Google Play does not support standalone wearable APKs. They must be packaged inside of a traditional APK. (Android Studio will do this automatically by just adding the Wear module as a dependency in the handheld module's build.gradle)

    dependencies {
        ...
        wearApp project(':wear')
    }


## Further Resources ##
- https://developer.android.com/training/building-wearables.html
