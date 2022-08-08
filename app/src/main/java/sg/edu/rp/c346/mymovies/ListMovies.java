package sg.edu.rp.c346.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListMovies extends AppCompatActivity {

    Button btnShowMovies, btnGoBack;
    ListView lv;
    ArrayList<Movies> alMovies;
    CustomAdapter caMovies;
    Movies filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movies);

        btnShowMovies = findViewById(R.id.btnShowMovies);
        btnGoBack = findViewById(R.id.btnGoBack);
        lv = findViewById(R.id.lv);
        alMovies = new ArrayList<>();

        DBHelper db = new DBHelper(ListMovies.this);
        alMovies = db.getAllMovie();

        //Initialize the CustomAdapter and connect it to the ListView.
        caMovies = new CustomAdapter(this, R.layout.row, alMovies);
        lv.setAdapter(caMovies);

        btnShowMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                for (int i = 0 ; i < alMovies.size() ; i++) {

                    if (alMovies.get(i).getRating().equalsIgnoreCase("pg13")) {
                        filter = alMovies.get(i);
                        alMovies.clear();
                        alMovies.add(filter);
                    }

                }
                lv.setAdapter(caMovies);
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                finish();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Intent i = new Intent(ListMovies.this,
                        EditMovie.class);
                Movies object = alMovies.get(position);
                i.putExtra("AAA", object.getTitle());
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper db = new DBHelper(ListMovies.this);
        alMovies =  db.getAllMovie();
        caMovies = new CustomAdapter(this,R.layout.row,alMovies);
        lv.setAdapter(caMovies);
    }
}