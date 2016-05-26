Simple Counter Widget
================

Simple counter widget for Android. (API 15+)

![Default Timer](images/default_timer.png)

========
### Example

If you want an example on how to use it, you can find an example application in this repo.

### Code usage

Simple put this XML code to your layout to add an timer.
```
<com.android.widget.Counter
        android:id="@+id/defaultTimer"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        android:orientation="vertical" />
```

In activity logic get widget timer by id.
```
// Bind example with butterknife
@Bind(R.id.defaultTimer)
Counter defaultTimer;

// Classic bind example
Counter defaultTimer = (Counter) findViewById(R.id.defaultTimer);

// Example to start timer.
defaultTimer.runTimer();

// Example to stop timer.
defaultTimer.stopTimer();
```

### Import widget to project

Build .aar file from project with
```
   gradle build
```

and import created .aar file in an Android project.
```
   /widget/build/outputs/aar/widget-release.aar
```

### License

```
Copyright 2016 Andreas Sekulski

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
