package instaclone.mahmoud.com.collegeapptest5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ModuleDetailsActivity extends AppCompatActivity {

    Button buttonContact;
    TextView tvRoom;
    TextView tvProfessor;
    TextView tvModule;
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_details);

        backArrow = (ImageView) findViewById(R.id.moduleDetailsBackArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
