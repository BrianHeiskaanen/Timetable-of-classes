package com.example.timetableofclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private static final Object TAG = MainActivity.class.getSimpleName();
    private Toolbar toolbar;
    private ChipNavigationBar chipNavigationBar;
    private FragmentManager fragmentManager;
    private GestureDetector gestureDetector;

    private static final String TAGS = "Swipe Position";
    private float x1, x2, y1, y2;
    private static int MIN_DISTANCE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar initialization
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Swipe initialization
        this.gestureDetector = new GestureDetector(MainActivity.this, this);

        //Bottom menu initialization
        chipNavigationBar = findViewById(R.id.chipNavigationBar);


        //Setting the initial selection bottom menu
        if(savedInstanceState == null) {
            chipNavigationBar.setItemSelected(R.id.learners, true);
            fragmentManager = getSupportFragmentManager();
            LearnersFragment learnersFragment = new LearnersFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, learnersFragment)
                    .commit();
        }

        //Switch between items bottom menu
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;

                if(id == R.id.learners) {
                    fragment = new LearnersFragment();
                }
                else if(id == R.id.teachers) {
                    fragment = new TeachersFragment();
                }

                if(fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }
                else {
                    Log.e((String) TAG, "Error in creating fragment");
                }
            }
        });

    }

    //Swipe handling
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                float valueX = x2 - x1;
                float valueY = y2 - y1;

                if(Math.abs(valueX) > MIN_DISTANCE) {
                    if(x2 > x1) {
                        chipNavigationBar.setItemSelected(R.id.learners, true);
                        Log.d(TAGS, "Right Swipe");
                    }
                    else {
                        chipNavigationBar.setItemSelected(R.id.teachers, true);
                        Log.d(TAGS, "Left Swipe");
                    }
                }

                //Swipe top and bottom
                /*else if (Math.abs(valueY) > MIN_DISTANCE) {
                    if(y2 > y1) {
                        Toast.makeText(this, "Bottom is swiped", Toast.LENGTH_SHORT).show();
                        Log.d(TAGS, "Bottom Swipe");
                    }
                    else {
                        Toast.makeText(this, "Top is swiped", Toast.LENGTH_SHORT).show();
                        Log.d(TAGS, "Top Swipe");
                    }
                }
                */
                break;
        }

        return super.onTouchEvent(event);
    }

    //Drop-down menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    //Dropdown menu handling
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.auto_update) {
            Toast.makeText(getApplicationContext(), "Автообновление",
                    Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.site_with_a_schedule) {
            Toast.makeText(getApplicationContext(), "Открыть сайт с расписанием",
                    Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.about_the_program) {
            Toast.makeText(getApplicationContext(), "О программе",
                    Toast.LENGTH_SHORT).show();
        }
        return true;
    }


    //Swipe functions
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}