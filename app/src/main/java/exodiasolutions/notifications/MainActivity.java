package exodiasolutions.notifications;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {

    String TAG = "FIREBASE";
    String SENDER_ID = "424821188089";
    String username = "sunny";
    TextView lol;
    EditText title1,content1,image1;
    String title= "HAHA";
    String content = "this is content";
    String imageurl = "https://scontent.fbho1-1.fna.fbcdn.net/v/t1.0-1/p160x160/54798745_960577107666622_8278082277972377600_n.jpg?_nc_cat=103&_nc_ht=scontent.fbho1-1.fna&oh=4f854ed5f59a6a0720736777ba5f4c89&oe=5D3912FD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lol = findViewById(R.id.lol);
        title1 = findViewById(R.id.title);
        content1 = findViewById(R.id.content);
        image1 = findViewById(R.id.image);
        FirebaseMessaging.getInstance().subscribeToTopic(username)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "NOT Subscribed";
                        }
                        Log.d(TAG, msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });



    }

    public void submit(View v){
        title = title1.getText().toString();
        content = content1.getText().toString();
        imageurl = image1.getText().toString();
        final MyHttpClient myHttpClient = new MyHttpClient(this,"https://vintagevow-sunnynarang.legacy.cs50.io/curl.php",new String[]{"title",title,"content",content,"imageurl",imageurl});
        myHttpClient.execute();

        myHttpClient.callback = new MyCallback() {
            @Override
            public void callbackCall() {
                //Toast.makeText(MainActivity.this, myHttpClient.result, Toast.LENGTH_SHORT).show();
                lol.setText(myHttpClient.result);
            }
        };
    }


}
