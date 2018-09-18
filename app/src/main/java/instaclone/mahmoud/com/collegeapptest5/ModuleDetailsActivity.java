package instaclone.mahmoud.com.collegeapptest5;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ModuleDetailsActivity extends AppCompatActivity {

    Button buttonContact;
    TextView tvRoom;
    TextView tvProfessor;
    TextView tvModule;
    ImageView backArrow;

    //Shared Preference
    SharedPreferences.Editor editor;
    SharedPreferences cacheData;

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
