package np.edu.sg.madassignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class main2 extends AppCompatActivity
{
    CountDownTimer cdt;
    TextView txt;
    TextView title;
    TextView description;
    Button commentbutton;
    TextView commenttext;
    ImageView imgexercise;
    ListView comments;
    long timer;
    MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main2); //set page to main2 OnStart
        txt = findViewById(R.id.text_timer);
        title = findViewById(R.id.textView3);
        description = findViewById(R.id.textView4);
        imgexercise = findViewById(R.id.imageView2);
        commentbutton = findViewById(R.id.btnComment);
        commenttext = findViewById(R.id.txtComment);
        comments = findViewById(R.id.lv_comments);
        Intent intent = getIntent();
        String grpName = ((Intent) intent).getStringExtra("ID");
        //Populating individual pages with different routines, also to show the correct comments for respective exercises.
        switch(grpName) {
            case "Aerobic Exercise":
                imgexercise.setImageResource(R.drawable.march);
                title.setText("Marching");
                description.setText("Marching is a good exercise\nfor your heart and lungs\n" +
                        "Bend your elbows and\nswing your arms as you lift your knees.\n" +
                        "Your Knee should be lifted up to stomach level");
                ArrayList<String> List1 = new ArrayList<>(dbHandler.loadHandler(1));
                ArrayAdapter adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,List1);
                comments.setAdapter(adapter1);
                break;
            case "Strength Training":
                imgexercise.setImageResource(R.drawable.squat);
                title.setText("Squating");
                description.setText("Squating is a good exercise for your Muscles\n" +
                        "Bend your hips and knees.\nSwinging arms forwards helps in balance\n" +
                        "Your back should be kept straight");
                ArrayList<String> List2 = new ArrayList<>(dbHandler.loadHandler(2));
                ArrayAdapter adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,List2);
                comments.setAdapter(adapter2);
                break;
            case "Stretching":
                imgexercise.setImageResource(R.drawable.rotate);
                title.setText("Single Knee Rotation");
                description.setText("Single Knee Rotation is a good exercise for your flexibility\n" +
                        "Bend your knee to the opposite direction\nwhile putting your foot on the thigh.\n" +
                        "Pull your knee towards the\nopposite direction and hold for 10-30 seconds.");
                ArrayList<String> List3 = new ArrayList<>(dbHandler.loadHandler(3));
                ArrayAdapter adapter3 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,List3);
                comments.setAdapter(adapter3);
                break;
            case "Balance Exercise":
                imgexercise.setImageResource(R.drawable.kneelift);

                title.setText("Standard Knee Lifting");
                description.setText("Standard Knee Lifting is a good exercise for your stability\n" +
                        "Lift your knee towards the ceiling\nas high as possible then lower it\nafter you feel the stretch\n" +
                        "Repeat this 3-5 times with both legs.");
                ArrayList<String> List4 = new ArrayList<>(dbHandler.loadHandler(4));
                ArrayAdapter adapter4 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,List4);
                comments.setAdapter(adapter4);
        }
    }






    //Implementing the Onclick method for Timer countdown
    public void onClick(View v) {
        Button b = (Button) v;
        String pressed = b.getText().toString();


        TextView tv = findViewById(R.id.text_timer);
        tv.setText(pressed);


        switch (pressed) {
            case "5 MIN":
                if(timer == 0) {
                    tv.setText("05:00");
                    startTimer(5);
                    break;
                }
                else{

                }

            case "10 MIN":
                if(timer == 0) {
                    tv.setText("10:00");
                    startTimer(10);
                    break;
                }
                else {

                }
            case "15 MIN":
                if(timer == 0) {
                    tv.setText("15:00");
                    startTimer(15);
                    break;
                }
                else{

                }


        }
    }
    // Method for starting the countdown Timer
    private void startTimer(int duration)
    {
        cdt = new CountDownTimer(duration*60000,1000) {
            @Override
            public void onTick(long time){
                timer = time/1000;
                long minute = timer / 60;
                long seconds = timer % 60;
                txt.setText(minute + ":" + seconds);
                /*txt.setText("" + (int)(time/60000) + ":"
                        + String.format("%02d", (time%60000/1000)));*/

            }

            @Override
            //Toast message will pop out when the Timer runs out
            public void onFinish() {
                txt.setText("0:00");


                Toast tt = Toast.makeText(main2.this,
                        "Times up!", Toast.LENGTH_LONG);
                tt.show();
                new AlertDialog.Builder(main2.this)
                        .setTitle("Times up")
                        .setMessage("Times up")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                })
                        .show();
            }

        }.start();

    }
    //Method for adding comments
    public void addComment(View view) {
        int id = 0;
        String name = commenttext.getText().toString();
        Comments comment = new Comments(id, name);
        Intent intent = getIntent();
        String grpName = ((Intent) intent).getStringExtra("ID");
        switch(grpName) {       //Checks for which exercise the user is in to add the comments into the corrrect table.
            case "Aerobic Exercise":
                dbHandler.addHandler(comment,1);
                commenttext.setText("");
                ArrayList<String> List1 = dbHandler.loadHandler(1);
                ArrayAdapter adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, List1);
                comments.setAdapter(adapter1);
                break;
            case "Strength Training":
                dbHandler.addHandler(comment,2);
                commenttext.setText("");
                ArrayList<String> List2 = dbHandler.loadHandler(2);
                ArrayAdapter adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, List2);
                comments.setAdapter(adapter2);
                break;
            case "Stretching":
                dbHandler.addHandler(comment,3);
                commenttext.setText("");
                ArrayList<String> List3 = dbHandler.loadHandler(3);
                ArrayAdapter adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, List3);
                comments.setAdapter(adapter3);
                break;
            case "Balance Exercise":
                dbHandler.addHandler(comment,4);
                commenttext.setText("");
                ArrayList<String> List4 = dbHandler.loadHandler(4);
                ArrayAdapter adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, List4);
                comments.setAdapter(adapter4);
                break;
        }
    }

}