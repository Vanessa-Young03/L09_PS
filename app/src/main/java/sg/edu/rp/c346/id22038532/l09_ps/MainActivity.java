package sg.edu.rp.c346.id22038532.l09_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText Title,Singers,Years;
    Button Insert,ShowList;

    RadioGroup RatingGroup;
    RadioButton rb1,rb2,rb3,rb4,rb5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        DBHelper db = new DBHelper(MainActivity.this);

        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                db.insertSong(Title.getText().toString(), Singers.getText().toString(),Years.getText().toString(), String.valueOf(stars));

                Toast.makeText(MainActivity.this, "Song added", Toast.LENGTH_SHORT).show();
            }
        });

        ShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowListActivity.class);
                startActivity(intent);

            }
        });

    }
}