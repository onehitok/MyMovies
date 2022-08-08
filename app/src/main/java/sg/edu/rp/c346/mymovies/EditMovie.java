package sg.edu.rp.c346.mymovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class EditMovie extends AppCompatActivity {

    Button btnUpdate, btnDelete, btnCancel;
    EditText etTitle, etGenre2, etYear;
    ArrayList<Movies> alMovies;
    Movies object;
    Spinner spinnerEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);


        //initialize the variables with UI here
        spinnerEdit = findViewById(R.id.ratingEdit);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        etTitle = findViewById(R.id.etTitle);
        etGenre2 = findViewById(R.id.etGenre2);
        etYear = findViewById(R.id.etYear);

        Intent i = getIntent();
        String data = (String) i.getSerializableExtra("AAA");

        DBHelper db = new DBHelper(this);
        alMovies = db.getAllMovie();

        for (int A = 0; A < alMovies.size(); A++) {
            if (alMovies.get(A).getTitle().equalsIgnoreCase(data)) {
                object = alMovies.get(A);
                break;
            }
        }

        etTitle.setText(object.getTitle());
        etGenre2.setText(object.getGenre());
        etYear.setText(String.valueOf(object.getYear()));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerItems, android.R.layout.simple_spinner_item);

        spinnerEdit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String value;
                switch (position) {
                    case 0:
                        value = "g";
                        break;
                    case 1:
                        value = "m18";
                        break;
                    case 2:
                        value = "nc16";
                        break;
                    case 3:
                        value = "pg";
                        break;
                    case 4:
                        value = "pg13";
                        break;
                    case 5:
                        value = "r21";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerEdit.setSelection(adapter.getPosition(object.getRating()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(EditMovie.this);
                object.setTitle(etTitle.getText().toString());
                object.setGenre(etGenre2.getText().toString());
                object.setYear(Integer.valueOf(etYear.getText().toString()));
                object.setRating(spinnerEdit.getSelectedItem().toString());
                dbh.updateMovie(object);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder ABuilder = new AlertDialog.Builder(EditMovie.this);

                ABuilder.setTitle("DELETE Movie");
                ABuilder.setMessage("Are you sure you want to delete the movie \n" + "''" + object.getTitle().toString() + "''");
                ABuilder.setCancelable(false);

                // Delete
                ABuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper dbh = new DBHelper(EditMovie.this);
                        dbh.deleteMovie(object.getId());
                        finish();
                    }
                });

                // Cancel
                ABuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                AlertDialog mydialog = ABuilder.create();
                mydialog.show();


            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}