package sg.edu.rp.c346.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movies> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<Movies> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        versionList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView Title = rowView.findViewById(R.id.textViewTitle);
        TextView Genre = rowView.findViewById(R.id.textViewGenre);
        TextView Year = rowView.findViewById(R.id.textViewYear);
        ImageView ivRated = rowView.findViewById(R.id.ivRated);



        Movies currentVersion = versionList.get(position);
        String rating = currentVersion.getRating();

        // Set values to the TextView to display the corresponding information

        Title.setText("Title: " + currentVersion.getTitle());
        Genre.setText("GENRE: " + currentVersion.getGenre());
        Year.setText("Year: " + String.valueOf(currentVersion.getYear()));
        if (rating.equalsIgnoreCase("g")) {

            ivRated.setImageResource(R.drawable.rating_g);
        }
        else if (rating.equalsIgnoreCase("pg")) {

            ivRated.setImageResource(R.drawable.rating_pg);
        }
        else if (rating.equalsIgnoreCase("pg13")) {

            ivRated.setImageResource(R.drawable.rating_pg13);
        }
        else if (rating.equalsIgnoreCase("nc16")) {

            ivRated.setImageResource(R.drawable.rating_nc16);
        }
        else if (rating.equalsIgnoreCase("m18")) {

            ivRated.setImageResource(R.drawable.rating_m18);
        }
        else if (rating.equalsIgnoreCase("r21")) {

            ivRated.setImageResource(R.drawable.rating_r21);
        }
        return rowView;
    }
}
