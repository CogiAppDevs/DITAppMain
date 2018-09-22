package instaclone.mahmoud.com.collegeapptest5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModuleDetailsActivity extends AppCompatActivity {

    Button buttonContact;
    TextView tvRoom;
    TextView tvProfessor;
    TextView tvModule;
    TextView tvGradeTitle;
    TextView tvGrade;
    ImageView backArrow;
    ListView list;

    ArrayList<AssignmentListObject> arrayList;
    UsersAdapter adapter;

    Intent intent;
    String moduleExtraString;
    String moduleName;
    String moduleProf;
    String moduleRoom;
    String grade;
    String gradeTitle;

    //Shared Preference
    SharedPreferences.Editor editor;
    SharedPreferences cacheData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_details);

        list = findViewById(R.id.list);
        cacheData = getSharedPreferences(getString(R.string.sharedPrefModulesDetails), ModuleDetailsActivity.MODE_PRIVATE);
        editor = cacheData.edit();

        arrayList = new ArrayList<AssignmentListObject>();
        adapter = new UsersAdapter(this, arrayList);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        tvProfessor = findViewById(R.id.tvProfessor);
        tvModule = findViewById(R.id.tvModule);
        tvRoom = findViewById(R.id.room);
        backArrow = (ImageView) findViewById(R.id.moduleDetailsBackArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getIncomingIntent();

        loadTimetable(moduleExtraString);

    }

    private void createObject(AssignmentListObject object, final String grade, final String title)
    {
        object.gradeTitle = title;
        object.grade = grade;
        arrayList.add(object);
        adapter.notifyDataSetChanged();
    }

    private void getIncomingIntent()
    {
        intent = getIntent();

        if (intent.hasExtra("module1"))
        {
            moduleExtraString = intent.getStringExtra("module1");

        }else if(intent.hasExtra("module2")){

            moduleExtraString = intent.getStringExtra("module2");

        }else if(intent.hasExtra("module3")){

            moduleExtraString = intent.getStringExtra("module3");

        }else if(intent.hasExtra("module4")){

            moduleExtraString = intent.getStringExtra("module4");

        }else if(intent.hasExtra("module5")){

            moduleExtraString = intent.getStringExtra("module5");
        }
    }

    public class UsersAdapter extends ArrayAdapter<AssignmentListObject> {

        public UsersAdapter(Context context, ArrayList<AssignmentListObject> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            AssignmentListObject user = getItem(position);

            if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.grades_row, parent, false);

            }

            tvGradeTitle = convertView.findViewById(R.id.tvGradeTitle);
            tvGrade = convertView.findViewById(R.id.tvGrade);

            tvGradeTitle.setText(user.gradeTitle);
            tvGrade.setText(user.grade);

            return convertView;

        }

    }

    private void loadTimetable(final String incomingModule)
    {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child(getString(R.string.modules));

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                moduleName = dataSnapshot.child(incomingModule).child(getString(R.string.ModuleName)).getValue(String.class);
                moduleProf = dataSnapshot.child(incomingModule).child(getString(R.string.professor)).getValue(String.class);
                moduleRoom = dataSnapshot.child(incomingModule).child("room").getValue(String.class);

                tvModule.setText(String.valueOf(moduleName.charAt(0)).toUpperCase() + moduleName.substring(1));
                tvProfessor.setText(String.valueOf(moduleProf.charAt(0)).toUpperCase() + moduleProf.substring(1));
                tvRoom.setText(getString(R.string.Room) + " " + moduleRoom);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);

        DatabaseReference gradesRef = rootRef.child("grades").child(incomingModule);
        ValueEventListener valueEventListenerGrades = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()) {

                    if(ds.getKey().equals("test1")) {

                        grade = dataSnapshot.child("test1").child("grade").getValue(String.class);
                        gradeTitle = dataSnapshot.child("test1").child("title").getValue(String.class);

                        AssignmentListObject object = new AssignmentListObject();
                        createObject(object, grade, gradeTitle);

                    }else if (ds.getKey().equals("test2")){

                        grade = dataSnapshot.child("test2").child("grade").getValue(String.class);
                        gradeTitle = dataSnapshot.child("test2").child("title").getValue(String.class);

                        AssignmentListObject object = new AssignmentListObject();
                        createObject(object, grade, gradeTitle);
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        gradesRef.addListenerForSingleValueEvent(valueEventListenerGrades);
    }
}
































