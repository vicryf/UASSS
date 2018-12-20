package com.vicryf1504373.uasmopro.uasss.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.vicryf1504373.uasmopro.uasss.R;
import com.vicryf1504373.uasmopro.uasss.adapter.MovementListAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String LAST_STATE = "last_state";
    RecyclerView recyclerViewMovement;
    ArrayList<String> savedList;
    MovementListAdapter movementListAdapter;
    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewMovement = findViewById(R.id.recycler_view_movement);

        savedList = new ArrayList<>();
        if (savedInstanceState != null) {
            savedList = savedInstanceState.getStringArrayList(LAST_STATE);
        }


        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("");

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(getString(R.string.readme));
        alertDialog.show();

        setRecyclerView();
    }

    private void setRecyclerView() {
        recyclerViewMovement.setLayoutManager(new LinearLayoutManager(this));
        movementListAdapter = new MovementListAdapter(this);
        recyclerViewMovement.setAdapter(movementListAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putStringArrayList(LAST_STATE, savedList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_clear:

                movementListAdapter.getListDirection().clear();
                movementListAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_save:
                //untuk menyimpan item
                //blm selesai
                savedList = movementListAdapter.getListDirection();
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_load:
                //untuk me-load item
                //blm selesai
                movementListAdapter.getListDirection().clear();
                movementListAdapter.getListDirection().addAll(savedList);
                movementListAdapter.notifyDataSetChanged();
                movementListAdapter.notifyItemInserted(0);
                Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
//metode ini digunakan untuk mendeteksi perubahan mendadak sumbu Z
            if (event.values[0] > -10 && 10 < event.values[0]) {
                // getak angkat-turun
                movementListAdapter.addToList("Pengereman Mendadak");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //
    }
}
