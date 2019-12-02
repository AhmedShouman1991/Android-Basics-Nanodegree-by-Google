package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adapter extends ArrayAdapter<Earthquake> {
    public Adapter(@NonNull Context context, int resource, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake current = getItem(position);

        double mag = current.getMagn();
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String stringMag = decimalFormat.format(mag);

        TextView textView = listItem.findViewById(R.id.magnitude);
        textView.setText(stringMag);

        GradientDrawable magnitudeCircle = (GradientDrawable) textView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(current.getMagn());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String place = current.getPlace();

        String offset;
        String primary;

        if (place.contains(" of ")) {
            offset = place.substring(0, place.indexOf(" of ") + 3);
            primary = place.substring(place.indexOf(" of ") + 4, place.length());
        } else {
            offset = "Near to";
            primary = place;
        }

        textView = listItem.findViewById(R.id.location_offset);
        textView.setText(offset);

        textView = listItem.findViewById(R.id.primary_location);
        textView.setText(primary);

        textView = listItem.findViewById(R.id.time);
        Date timeAndDateObject = new Date(current.getTime());
        String time = SimpleDateFormat.getTimeInstance().format(timeAndDateObject);
        textView.setText(time);

        textView = listItem.findViewById(R.id.date);
        String date = SimpleDateFormat.getDateInstance().format(timeAndDateObject);
        textView.setText(date);

        return listItem;
    }

    private int getMagnitudeColor(double mag) {
        int intMag = (int) mag;
        int magColor = 0;
        switch (intMag) {
            case 0:
            case 1:
                magColor = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2:
                magColor = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3:
                magColor = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4:
                magColor = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5:
                magColor = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6:
                magColor = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7:
                magColor = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8:
                magColor = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9:
                magColor = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default:
                magColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;
        }
        return magColor;
    }
}
