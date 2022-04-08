![](media/animation.gif)

# BubbleTabBar

BubbleTabBar is bottom navigation bar with customizable bubble like tabs

[![Android Weekly](https://img.shields.io/badge/Featured%20in%20androidweekly.net-Issue%20%23474-blue.svg?style=flat-square)](https://androidweekly.net/issues/issue-474)
[![Google Dev Library](https://img.shields.io/badge/Google%20Dev%20Library-BubbleTabBar-brightgreen.svg?style=flat-square)](https://devlibrary.withgoogle.com/products/android/repos/akshay2211-BubbleTabBar)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a3f9c05f456b45f1a1e332b3cf668de8)](https://app.codacy.com/gh/akshay2211/BubbleTabBar?utm_source=github.com&utm_medium=referral&utm_content=akshay2211/BubbleTabBar&utm_campaign=Badge_Grade)
[![BubbleTabBar](https://www.appbrain.com/stats/libraries/shield/bubbletabbar.svg)](https://www.appbrain.com/stats/libraries/details/bubbletabbar/bubbletabbar)
[![](https://img.shields.io/badge/Android%20Arsenal-BubbleTabBar-blue.svg?style=flat-square)](https://android-arsenal.com/details/1/7841)
[![](https://img.shields.io/badge/Awesome%20Android-BubbleTabBar-green.svg?style=flat-square)](https://android.libhunt.com/bubbletabbar-alternatives)
[![](https://img.shields.io/badge/API-16%2B-orange.svg?style=flat-square)](https://android-arsenal.com/api?level=21)
[![](https://img.shields.io/badge/Google%20Dev%20Libray-BubbleTabBar-red.svg?style=flat-square)](https://devlibrary.withgoogle.com/products/android/repos/akshay2211-BubbleTabBar)

![](media/media-600.gif)

## Usage
 
```xml
    <io.ak1.BubbleTabBar
               android:id="@+id/bubbleTabBar"
               android:layout_width="match_parent"
               android:layout_height="wrap_content"
               android:background="#FFF"
               android:elevation="16dp"
               android:padding="7dp"
               app:bubbletab_menuResource="@menu/list"
               app:bubbletab_custom_font="@font/opensans"
               app:bubbletab_disabled_icon_color="@color/colorPrimaryDark"
               app:bubbletab_horizontal_padding="20dp"
               app:bubbletab_icon_size="20dp"
               app:bubbletab_title_size="16sp"
               app:bubbletab_icon_padding="5sp"
               app:bubbletab_vertical_padding="10dp"
               app:bubbletab_tab_corner_radius="25dp">
       </io.ak1.BubbleTabBar>
```
or just use
```xml
    <io.ak1.BubbleTabBar
               android:id="@+id/bubbleTabBar"
               android:layout_width="match_parent"
               android:layout_height="wrap_content"
               android:padding="7dp"
               app:bubbletab_menuResource="@menu/list">
       </io.ak1.BubbleTabBar>
```
Inflate menu list
```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/home"
        android:icon="@drawable/ic_grid"
        android:title="Home"
        android:checked="true"
        android:color="@color/home"/>

    <item
        android:id="@+id/log"
        android:icon="@drawable/ic_clock"
        android:title="Logger"
        android:color="@color/logger"/>
</menu>
            
```

Add onclick listener 

```kotlin
   bubbleTabBar.addBubbLeListener(object : OnBubbleClickListener{
               override fun onBubbleClick(id: Int) {
                   
               }
           })
```
### Connect with components like [ViewPager](https://github.com/akshay2211/BubbleTabBar/blob/master/README.md#setup-viewpager-to-bubbletabbar), [ViewPager2](https://github.com/akshay2211/BubbleTabBar#setup-viewpager2-to-bubbletabbar) and [NavController](https://github.com/akshay2211/BubbleTabBar#setup-navcontroller-to-bubbletabbar)
#### Setup ViewPager to BubbleTabBar

```kotlin

    viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                bubbleTabBar.setSelected(position, false)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
   
```

#### Setup ViewPager2 to BubbleTabBar

```kotlin

    viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                               override fun onPageSelected(position: Int) {
                                   super.onPageSelected(position)
                                   bubbleTabBar.setSelected(position)
                               }
                           })

```

#### Setup NavController to BubbleTabBar

*onNavDestinationSelected* can be found in [here](https://github.com/akshay2211/BubbleTabBar/blob/cad8bdc3b634410c4d76c99853016e955f9fac70/app/src/main/java/com/fxn/bubbletabbarapp/utils/Helper.kt#L36)

```kotlin

    bubbleTabBar.addBubbleListener { id ->
                bubbleTabBar.onNavDestinationSelected(id, navController)
            }
    navController.addOnDestinationChangedListener { _, destination, _ ->
                setSelectedWithId(destination.id, false)
            }

```

### Java Implementation
for [Java-Implementation](https://github.com/akshay2211/BubbleTabBar/wiki/Java-Implementation)

## Credits
Thanks to [Ariana](https://github.com/akshay2211/Ariana) for transition in sample

## IOS

Similar library [BubbleTabBar](https://github.com/Cuberto/bubble-icon-tabbar) by [Cuberto](https://github.com/Cuberto)

## Download

[![Download](https://api.bintray.com/packages/fxn769/android_projects/BubbleTabBar/images/download.svg?version=1.0.3)](https://bintray.com/fxn769/android_projects/BubbleTabBar/1.0.3/link)

 include in app level build.gradle
 ```groovy
        repositories {
          mavenCentral()
        }
 ```
```groovy
        implementation  'io.ak1:bubbletabbar:1.0.8'
```
or Maven:
```xml
        <dependency>
            <groupId>io.ak1</groupId>
            <artifactId>bubbletabbar</artifactId>
            <version>1.0.8</version>
            <type>pom</type>
        </dependency>
```
or ivy:
```xml
        <dependency org='io.ak1' name='bubbletabbar' rev='1.0.8'>
            <artifact name='bubbletabbar' ext='pom' ></artifact>
        </dependency>
```

## License
Licensed under the Apache License, Version 2.0, [click here for the full license](/LICENSE).

## Author & support
This project was created by [Akshay Sharma](https://akshay2211.github.io/).

> If you appreciate my work, consider buying me a cup of :coffee: to keep me recharged :metal: by [PayPal](https://www.paypal.me/akshay2211)

> I love using my work and I'm available for contract work. Freelancing helps to maintain and keep [my open source projects](https://github.com/akshay2211/) up to date!
