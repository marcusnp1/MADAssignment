package np.edu.sg.madassignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    ArrayList<Exercise> data;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Aerobic Exercise");
        arrayList.add("Strength Training");
        arrayList.add("Stretching");
        arrayList.add("Balance Exercise");
        data = new ArrayList<>();
        for(int i=0; i<4;i++) {
            Exercise s = new Exercise();
            String title = arrayList.get(i);
            s.setTitle(title);
            data.add(s);
        }


        ExerciseAdapter adapter = new ExerciseAdapter(this,R.layout.wolayout,data);
        ListView list = findViewById(R.id.listview);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, main2.class);
                Exercise selectedItem = (Exercise) parent.getItemAtPosition(position);
                intent.putExtra("ID", selectedItem.getTitle());
                startActivity(intent);

            }
        });
    }
    public void onClick()
    {
        Intent intentnew = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intentnew);
    }

}