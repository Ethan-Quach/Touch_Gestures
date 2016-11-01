package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    // Defining a GestureDetector to listen to events on the ScrollView
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);

        // We're hooking up the GestureDetector to the ScrollView for its context.
        // The listener is this activity, since this activity implements OnGestureListener
        // as well as OnDoubleTapListener.

        gestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);

        // So far, we have four out of five gestures. We're just missing double tap,
        // so we're implementing it here.

        gestureDetector.setOnDoubleTapListener(this);

        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        singleTapTextView = (TextView) findViewById(R.id.singleTapTextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubleTapTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollTextView);
        flingTextView = (TextView) findViewById(R.id.flingTextView);
    }

    public void clearAll (View view) {

        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;

        gesturesLogTextView.setText("");
        singleTapTextView.setText("0");
        doubleTapTextView.setText("0");
        longPressTextView.setText("0");
        scrollTextView.setText("0");
        flingTextView.setText("0");
    }

    // Any touch event is now dispatched from the Activity to the ScrollView
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        singleTaps++;
        // Appending the single tap event to the gestures log
        gesturesLogTextView.append("\nOnSingleTapConfirmed touch event");
        singleTapTextView.setText(singleTaps);
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        doubleTaps++;
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDoubleTapUp touch event");
        doubleTapTextView.setText(doubleTaps);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown touch event");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonShowPress touch event");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonSingleTapUp touch event");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        scrolls++;
        scrollTextView.setText(scrolls);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        longPresses++;
        gesturesLogTextView.append("\nonLongPress touch event");
        longPressTextView.setText(longPresses);
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        flings++;
        flingTextView.setText(flings);
        return true;
    }
}
