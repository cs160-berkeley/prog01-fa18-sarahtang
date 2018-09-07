package com.example.sarahtang.electric_time;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    long distance;
    int transport;
    int range; //miles
    String[] travel = new String[]{
            "Walking",
            "Boosted Mini S Board",
            "Evolve Skateboard",
            "Segway i2 SE",
            "Hovertrax Hoverboard",
            "One Wheel",
            "Mototec Skateboard",
            "Segway Ninebot One S1",
            "Razor Scooter",
            "GeoBlade 500"
    };

    TextView travelTime;
    TextView inRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, travel);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(MainActivity.this);
        spinner.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDistance();
            }
        });

        travelTime = (TextView) findViewById(R.id.test);
        inRange = (TextView) findViewById(R.id.inRange);
        travelTime.setText("");
        inRange.setText("");
    }

    private ArrayList<TravelResults> GetTravelResults(){
        ArrayList<TravelResults> results = new ArrayList<TravelResults>();

        TravelResults tr = new TravelResults();
        tr.setType("Walking");
        tr.setTime(populate(0));
        results.add(tr);

        tr = new TravelResults();
        tr.setType("Boosted Mini S Board");
        tr.setTime(populate(1));
        results.add(tr);

        tr = new TravelResults();
        tr.setType("Evolve Skateboard");
        tr.setTime(populate(2));
        results.add(tr);

        tr = new TravelResults();
        tr.setType("Segway i2 SE");
        tr.setTime(populate(3));
        results.add(tr);

        tr = new TravelResults();
        tr.setType("Hovertrax Hoverboard");
        tr.setTime(populate(4));
        results.add(tr);

        tr = new TravelResults();
        tr.setType("One Wheel");
        tr.setTime(populate(5));
        results.add(tr);

        tr = new TravelResults();
        tr.setType("Mototec Skateboard");
        tr.setTime(populate(6));
        results.add(tr);

        tr = new TravelResults();
        tr.setType("Segway Ninebot One S1");
        tr.setTime(populate(7));
        results.add(tr);

        tr = new TravelResults();
        tr.setType("Razor Scooter");
        tr.setTime(populate(8));
        results.add(tr);

        tr = new TravelResults();
        tr.setType("GeoBlade 500");
        tr.setTime(populate(9));
        results.add(tr);

        return results;
    }

    public void calculateDistance() {
        //Enter amount of travel by miles
        EditText dist = (EditText) findViewById(R.id.editText);
        if (dist.equals("")) {
            distance = 0;
        } else {
            distance = Integer.parseInt(dist.getText().toString());
        }

        //Calculation based on selection
        double speed;
        switch (transport) {
            case 0:
                //Walking
                speed = 3.1;
                range = 30;
                break;
            case 1:
                //Boosted Mini S Board
                speed = 18;
                range = 7;
                break;
            case 2:
                //Evolve Skateboard
                speed = 26;
                range = 31;
                break;
            case 3:
                //Segway i2 SE
                speed = 12.5;
                range = 24;
                break;
            case 4:
                //Hovertrax Hoverboard
                speed = 8;
                range = 8;
                break;
            case 5:
                //One Wheel
                speed = 19;
                range = 7;
                break;
            case 6:
                //Mototec Skateboard
                speed = 22;
                range = 10;
                break;
            case 7:
                //Segway Ninebot One S1
                speed = 12.5;
                range = 15;
                break;
            case 8:
                //Razor Scooter
                speed = 10;
                range = 7;
                break;
            case 9:
                //GeoBlade 500
                speed = 15;
                range = 8;
                break;
            default:
                speed = 0;
                range = 0;
                break;
        }

        ArrayList<TravelResults> travelResults = GetTravelResults();
        final ListView lv = (ListView) findViewById(R.id.conversionsList);
        lv.setAdapter(new CustomAdapter(this, travelResults));
        lv.setBackgroundColor(Color.parseColor("#E0E0E0"));

        TextView skrt = (TextView) findViewById(R.id.textView4);
        skrt.setText("Or skrt over there another way!");

        //Display result
        long result = (Math.round((distance / speed) * 60));
        String display = display(result);
        travelTime.setText(display);

        //Display whether or not in range
        if (distance > range) {
            inRange.setText("Not in range");
        } else {
            inRange.setText("");
        }
    }

    public String display(long l) {
        if (l > 60) {
            long hours = l / 60;
            long minutes = l % 60;
            if (hours > 1) {
                return (hours + " hours " + minutes + " minutes");
            } else {
                return (hours + " hour " + minutes + " minutes");
            }

        } else {
            return (l + " minutes");
        }
    }

    public String displayTwo(double d) {
        String timeStr = new DecimalFormat("#").format(d);

        if (d > 60) {
            double hours = d / 60;
            double minutes = d % 60;
            String timeHours = new DecimalFormat("#").format(hours);
            String timeMinutes = new DecimalFormat("#").format(minutes);

            if (hours > 1) {
                return (timeHours + " hours " + timeMinutes + " minutes");
            } else {
                return (timeHours + " hour " + timeMinutes + " minutes");
            }

        } else {
            return (timeStr + " minutes");
        }
    }

    public String populate(int number) {

        double speed;
        switch (number) {
            case 0:
                //Walking
                speed = 3.1;
                break;
            case 1:
                //Boosted Mini S Board
                speed = 18;
                break;
            case 2:
                //Evolve Skateboard
                speed = 26;
                break;
            case 3:
                //Segway i2 SE
                speed = 12.5;
                break;
            case 4:
                //Hovertrax Hoverboard
                speed = 8;
                break;
            case 5:
                //One Wheel
                speed = 19;
                break;
            case 6:
                //Mototec Skateboard
                speed = 22;
                break;
            case 7:
                //Segway Ninebot One S1
                speed = 12.5;
                break;
            case 8:
                //Razor Scooter
                speed = 10;
                break;
            case 9:
                //GeoBlade 500
                speed = 15;
                break;
            default:
                speed = 0;
                break;
        }
        long result = (Math.round((distance / speed) * 60));
        String displayList = display(result);
        return displayList;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        switch (pos) {
            case 0:
                transport = 0;
                break;
            case 1:
                transport = 1;
                break;
            case 2:
                transport = 2;
                break;
            case 3:
                transport = 3;
                break;
            case 4:
                transport = 4;
                break;
            case 5:
                transport = 5;
                break;
            case 6:
                transport = 6;
                break;
            case 7:
                transport = 7;
                break;
            case 8:
                transport = 8;
                break;
            case 9:
                transport = 9;
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        transport = 0;
    }

}
