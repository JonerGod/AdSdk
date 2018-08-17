# Android Guidelines | Getting Started with Android SDK Integration

## You can get started with the following:

   - [Adding the SDK to your Project](#Adding the SDK to your Project)
   - [Changing the Manifest File](#Changing the Manifest File)
   - [Initializing the SDK](#Initializing the SDK)



### <span  id="Adding the SDK to your Project">Adding the SDK to your Project:</span>

 **Method 1**: Pulling the latest SDK via jCenter

   If you are using Gradle to build your Android applications, you can pull the latest version of the SDK from jCenter.
    To do so:
    
   a.Include this in your top-level build.gradle file:
        
         allprojects {
            repositories {
                jcenter()
            }
        }
    		
   b.   Add the following line to the dependencies element in your application module’s build.gradle.
        
        compile 'com.idouzi.adsdk:idouzi-ads:1.X.X'
    		
   c.  Sync your Gradle project to ensure that the dependency is downloaded by the build system.
   
**Method 2**: Adding the SDK library to your application project

Alternately, you can download the latest version of ad’s SDK here and copy the library to your application module’s libs/ directory.

To add the library to your project’s dependencies, add this line to the dependencies element in your module’s build.gradle.

    compile fileTree(dir: 'libs', include: ['*.jar'])

###<span id="Changing the Manifest File">Changing the Manifest File<span/>
**Granting Permissions**

You must include the following mandatory permissions in your application manifest.

Note: Failure to include these permissions will affect your ability to monetize your application on the ad network.

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

In addition to the mandatory permissions above, it is highly recommended that you include either the ACCESS_COARSE_LOCATION or the ACCESS_FINE_LOCATION to enable better ad targeting. This is not a mandatory permission; however, including it will enable accurate ad targeting.

    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

For further improved targeting, you can add the ACCESS_WIFI_STATE and the CHANGE_WIFI_STATE permissions to the manifest.

    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />

If you are requesting for rich-media ads, you can add the following permissions.
   
    <uses-permission android:name="com.google.android.gms.permission.ACTIVITY_RECOGNITION" />
    <uses-permission android:name="android.permission.READ_CALENDAR" />
    <uses-permission android:name="android.permission.WRITE_CALENDAR" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

**Proguard Configuration**

Proguarding helps to remove unused symbols and reduce the final application footprint. The following proguard directives should be added to your application’s proguard configuration file.

        # skip the ads library classes
        -keep class com.idouzi.adsdk.** {*;}
        -dontwarn com.idouzi.adsdk.**

###<span id="Initializing the SDK">Initializing the SDK<span/>
1.Initialize the SDK, using one of the two init APIs provided below in your launcher, by passing in the Activity context and your adsdk account ID.
  
      FlowBankSdkManager.getInstance(this).init("appid","appkey");
        
2.You can supply the  SDK with the gender and age:  
       
       FlowBankSdkManager.setGender(FlowBankSdkManager.Gender.MALE); // or AD Sdk.Gender.FEMALE
       FlowBankSdkManager.setAge(age); 
        
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
   Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```


