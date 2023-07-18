package sg.edu.rp.c346.id22038532.l09_ps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Songs> songList;

    public CustomAdapter(Context context, int resource, ArrayList<Songs> objects)
    {
        super(context,resource,objects);

        parent_context = context;
        layout_id = resource;
        songList = objects; // VersionList refer to ArrayList
    }

    @Override

    //Gernerate row View
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //"Inflate" the View for each row
        View rowView = inflater.inflate(layout_id,parent,false); // Represent row.xml

        //Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        TextView tvStars = rowView.findViewById(R.id.textViewStars);
        TextView tvSinger = rowView.findViewById(R.id.textViewSinger);

        //Obtain the Android Version information based on the position
        Songs currentVersion = songList.get(position);

        //Set values to the TextView to display the corresponding information
        tvTitle.setText(currentVersion.getTitle());
        tvYear.setText(currentVersion.getYear());
        tvStars.setText(currentVersion.getStars());
        tvSinger.setText(currentVersion.getSingers());

        return rowView;
    }
}
