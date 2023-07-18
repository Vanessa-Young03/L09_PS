package sg.edu.rp.c346.id22038532.l09_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Update_Delete extends AppCompatActivity {

    Button Update,Delete,Cancel;
    EditText ID,Title,Singers,Years;
    Button Insert,ShowList;

    RadioGroup RatingGroup;
    RadioButton rb1,rb2,rb3,rb4,rb5;

    Songs data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        ID = findViewById(R.id.editTextTextID);
        Title = findViewById(R.id.editTextTextSongTitle);
        Singers = findViewById(R.id.editTextTextSingers);
        Years = findViewById(R.id.editTextTextYear);
        Insert = findViewById(R.id.buttonUpdate);
        ShowList = findViewById(R.id.buttonDelete);
        RatingGroup = findViewById(R.id.RatingGroup);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);
        Update = findViewById(R.id.buttonUpdate);
        Delete = findViewById(R.id.buttonDelete);
        Cancel =findViewById(R.id.buttonCancel);

        DBHelper dbh = new DBHelper(Update_Delete.this);

        Intent intent = getIntent();
        data = (Songs) intent.getSerializableExtra("data");


        ID.setText(String.valueOf(data.getId()));
        Title.setText(String.valueOf(data.getTitle()));
        Singers.setText(String.valueOf(data.getSingers()));
        Years.setText(String.valueOf(data.getYear()));
        int NoOfStars = data.getStars();

        if(NoOfStars == 1)
        {
            rb1.setChecked(true);
        }
        else if (NoOfStars == 2)
        {
            rb2.setChecked(true);
        }
        else if (NoOfStars == 3)
        {
            rb3.setChecked(true);
        }
        else if (NoOfStars == 4)
        {
            rb4.setChecked(true);
        }
        else if (NoOfStars == 5)
        {
            rb5.setChecked(true);
        }

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setTitle(Title.getText().toString());
                data.setSingers(Singers.getText().toString());
                data.setYears(Integer.parseInt(Years.getText().toString()));

                int SelectedRating = RatingGroup.getCheckedRadioButtonId();
                int stars = 0;

                if (SelectedRating == R.id.radioButton)
                {
                    stars = 1;
                }
                else if (SelectedRating == R.id.radioButton2)
                {
                    stars = 2;
                }
                else if (SelectedRating == R.id.radioButton3)
                {
                    stars = 3;
                }
                else if (SelectedRating == R.id.radioButton4)
                {
                    stars = 4;
                }
                else if (SelectedRating == R.id.radioButton5)
                {
                    stars = 5;
                }
                data.setStars(stars);
                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbh.deleteSong(data.getId());
                finish();
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}