package instaclone.mahmoud.com.collegeapptest5;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity   {


    ConstraintLayout myLayout;
    AnimationDrawable animationDrawable;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Removes  the title bar on the screen
        setContentView(R.layout.login_page);

        // Set the layouts to myLayout and set the animation drawable to the different layouts of myLayout
        myLayout = findViewById(R.id.login);
        animationDrawable = (AnimationDrawable) myLayout.getBackground();

        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(1500);
        animationDrawable.start();

        loginBtn = findViewById(R.id.button);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}
