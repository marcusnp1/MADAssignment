package np.edu.sg.madassignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class main2 extends AppCompatActivity
{
    CountDownTimer cdt;
    TextView txt;
    TextView title;
    TextView description;
    ImageView imgexercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2); //set page to main2 OnStart
        txt = findViewById(R.id.text_timer);
        title = findViewById(R.id.textView3);
        description = findViewById(R.id.textView4);
        imgexercise = findViewById(R.id.imageView2);
        Intent intent = getIntent();
        String grpName = ((Intent) intent).getStringExtra("ID");
        switch(grpName) {
            case ("Aerobic Exercise"):
                imgexercise.setImageResource(R.drawable.march);
                title.setText("Marching");
                description.setText("Marching is a good exercise for your heart and lungs\n" +
                        "Bend your elbows and swing your arms as you lift your knees.\n" +
                        "Your Knee should be lifted up to stomach level");
            case ("Strength Training"):
                imgexercise.setImageResource(R.drawable.squat);
                title.setText("Squating");
                description.setText("Squating is a good exercise for your Muscles\n" +
                        "Bend your hips and knees. Swinging arms forwards helps in balance\n" +
                        "Your back should be kept straight");
            case ("Stretching"):
                imgexercise.setImageResource(R.drawable.rotate);
                title.setText("Single Knee Rotation");
                description.setText("Single Knee Rotation is a good exercise for your flexibility\n" +
                        "Bend your knee to the opposite direction while putting your foot on the thigh.\n" +
                        "Pull your knee towards the opposite direction and hold for 10-30 seconds.");
            case ("Balance Exercise"):
                imgexercise.setImageResource(R.drawable.kneelift);
                title.setText("Standard Knee Lifting");
                description.setText("Standard Knee Lifting is a good exercise for your stability\n" +
                        "Lift your knee towards the ceiling as high as possible then lower it after you feel the stretch\n" +
                        "Repeat this 3-5 times with both legs.");
        }
    }







    public void onClick(View v) {
        Button b = (Button) v;
        String pressed = b.getText().toString();


        TextView tv = findViewById(R.id.text_timer);
        tv.setText(pressed);


        switch (pressed) {
            case "1 MIN":
                tv.setText("01:00");
                startTimer(1);
                break;
            case "2 MIN":
                tv.setText("02:00");
                startTimer(2);
                break;
            case "3 MIN":
                tv.setText("03:00");
                startTimer(3);
                break;


        }
    }
    private void startTimer(int duration)
    {
        cdt = new CountDownTimer(duration*60000,1000) {
            @Override
            public void onTick(long time){
                txt.setText("" + (int)(time/60000) + ":"
                        + String.format("%02d", (time%60000/1000)));

            }

            @Override
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


}