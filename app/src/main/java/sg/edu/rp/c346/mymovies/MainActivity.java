package sg.edu.rp.c346.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvRating;
    Button btnInsert, btnShowlist;
    EditText etMovieTitle, etGenre, etYear;

    Spinner Rating;

    ArrayList<Movies> ALMovies;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rating = findViewById(R.id.Rating);
        tvRating = findViewById(R.id.tvRating);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowlist = findViewById(R.id.btnShowList);
        etMovieTitle = findViewById(R.id.etMovieTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        ALMovies = new ArrayList<>();
        String strText1 = (etMovieTitle).getText().toString();
        String strText2 = (etGenre).getText().toString();
        String strText3 = (etYear).getText().toString();

        btnInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = etMovieTitle.getText().toString().trim();
                    String genre = etGenre.getText().toString().trim();
                    //  int year = Integer.valueOf(etYear.getText().toString());  function the same as parseint
                    int year = Integer.parseInt(etYear.getText().toString());
                    Rating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String value;
                            switch (position) {
                                case 0:
                                    tvRating.setText("Rated : G ");
                                    value = "G";
                                    break;
                                case 1:
                                    tvRating.setText("Rated : PG ");
                                    value = "PG";
                                    break;
                                case 2:
                                    tvRating.setText("Rated : PG13 ");
                                    value = "PG13";
                                    break;
                                case 3:
                                    tvRating.setText("Rated : NC16 ");
                                    value = "NC16";
                                    break;
                                case 4:
                                    tvRating.setText("Rated : M18 ");
                                    value = "M18";
                                    break;
                                case 5:
                                    tvRating.setText("Rated : R21 ");
                                    value = "R21";
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    String getSpnSelected = Rating.getSelectedItem().toString();

                    DBHelper db = new DBHelper(MainActivity.this);
                    long id = db.insertMovie(title, genre, year, getSpnSelected);
                    etMovieTitle.setText("");
                    etGenre.setText("");
                    etYear.setText("");

                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                    }
            });

            etYear.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Create the Listener to set the date
                    DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            etYear.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    };

                    // Create the Date Picker Dialog
                    Calendar now = Calendar.getInstance();
                    int year = now.get(Calendar.YEAR);
                    int month = now.get(Calendar.MONTH);
                    int day = now.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,
                            myDateListener, year, month, day);
                    myDateDialog.show();
                }
            }));

        btnShowlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListMovies.class);
                startActivity(i);
            }
        });

    }
}



