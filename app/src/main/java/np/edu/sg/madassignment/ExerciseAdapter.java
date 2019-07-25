package np.edu.sg.madassignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseAdapter extends ArrayAdapter<Exercise> {
    Context c;
    int layout;
    ArrayList<Exercise> data;
    public ExerciseAdapter(Context c, int layout, ArrayList<Exercise> data){
        super(c,layout,data);

        this.c = c;
        this.layout = layout;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null)
        {
            v = LayoutInflater.from(c)
                    .inflate(layout, parent, false);
        }

        Exercise s = data.get(position);
        TextView title = v.findViewById(R.id.textView);
        TextView artist = v.findViewById(R.id.textView2);

        title.setText(s.getTitle());
        artist.setText(s.getDescription());
        return v;
    }
}
