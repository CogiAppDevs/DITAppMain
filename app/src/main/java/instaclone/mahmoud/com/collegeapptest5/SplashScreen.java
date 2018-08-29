package instaclone.mahmoud.com.collegeapptest5;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                // do something
                Intent i = new Intent(SplashScreen.this,LoginActivity.class);
                startActivity(i);
            }
        }, 3000);
    }
}
