package np.edu.sg.madassignment;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class main2 extends AppCompatActivity
{
    CountDownTimer cdt;
    TextView txt;
    ImageView imgexercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2); //set page to main2 OnStart
        txt = findViewById(R.id.text_timer);
        imgexercise = findViewById(R.id.imageView2);
        imgexercise.setImageResource(R.drawable.squat);
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