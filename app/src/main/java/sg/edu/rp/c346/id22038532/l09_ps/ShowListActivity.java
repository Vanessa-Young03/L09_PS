package sg.edu.rp.c346.id22038532.l09_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowListActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<Songs> aa;
    ArrayList<Songs> al;
    Songs data;

//    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list3);
        lv = findViewById(R.id.lv);

        al = new ArrayList<Songs>();
        aa = new ArrayAdapter<Songs>(this, android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);

        DBHelper dbh = new DBHelper(ShowListActivity.this);
        al.clear();
        al.addAll(dbh.getSong());
        aa.notifyDataSetChanged();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Songs data = al.get(i);
                Intent intent = new Intent(ShowListActivity.this, Update_Delete.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });
    }
}