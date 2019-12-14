package com.example.okul.kaydirma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

public class MainActivity extends AppCompatActivity {
    private GestureDetector gestureDetector = null;
    private HorizontalScrollView hs;

    private int mWidth = 1160;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDetector = new GestureDetector(new MyGestureDetector());
        hs = (HorizontalScrollView) findViewById(R.id.hs);
        hs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (gestureDetector.onTouchEvent(motionEvent)) {
                    return true;
                }
                if (motionEvent.getAction() == 1) {
                    if (hs.getScrollX() > mWidth + mWidth / 2) {
                        hs.smoothScrollBy(mWidth * 2 - hs.getScrollX(), 0);
                    } else if (hs.getScrollX() > mWidth / 2) {
                        hs.smoothScrollBy(mWidth - hs.getScrollX(), 0);
                    } else {
                        hs.smoothScrollBy(-hs.getScrollX(), 0);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            if (e1.getX() < e2.getX()) {
                moveLeft();
            } else {
                moveRight();
            }
            return true;
        }
    }

    private void moveRight() {
        if (hs.getScrollX() > 0 && hs.getScrollX() < mWidth) {
            hs.smoothScrollBy(mWidth - hs.getScrollX(), 0);
        } else if (hs.getScrollX() > mWidth && hs.getScrollX() < mWidth * 2) {
            hs.smoothScrollBy(mWidth * 2 - hs.getScrollX(), 0);
        }

    }

    private void moveLeft() {
        if (hs.getScrollX() > 0 && hs.getScrollX() < mWidth) {
            hs.smoothScrollBy(-hs.getScrollX(), 0);
        } else if (hs.getScrollX() > mWidth && hs.getScrollX() < mWidth * 2) {
            hs.smoothScrollBy(mWidth - hs.getScrollX(), 0);
        }

    }

}
