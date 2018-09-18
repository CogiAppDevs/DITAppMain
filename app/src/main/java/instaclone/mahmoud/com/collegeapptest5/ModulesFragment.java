package instaclone.mahmoud.com.collegeapptest5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ModulesFragment extends Fragment {

    //Firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    String name;

    //Shared Preference
    SharedPreferences.Editor editor;
    SharedPreferences cacheData;

    Button module1Btn;
    Button module2Btn;
    Button module3Btn;
    Button module4Btn;
    Button module5Btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_modules, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        cacheData = getActivity().getSharedPreferences(getString(R.string.sharedPrefModules), getContext().MODE_PRIVATE);
        editor = cacheData.edit();

        loadTimetable();

        module1Btn = (Button)view.findViewById(R.id.module1);
        module2Btn = (Button)view.findViewById(R.id.module2);
        module3Btn = (Button)view.findViewById(R.id.module3);
        module4Btn = (Button)view.findViewById(R.id.module4);
        module5Btn = (Button)view.findViewById(R.id.module5);

        setupWidgets();

        return view;
    }

    private void setupWidgets()
    {
       module1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ModuleDetailsActivity.class);
                startActivity(intent);
            }
        });

        module2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ModuleDetailsActivity.class);
                startActivity(intent);
            }
        });

        module3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ModuleDetailsActivity.class);
                startActivity(intent);
            }
        });

        module4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ModuleDetailsActivity.class);
                startActivity(intent);
            }
        });

        module5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ModuleDetailsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loadTimetable()
    {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child(getString(R.string.modules));

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()) {

                    if(ds.getKey().equals("module1"))
                    {
                        editor.putString("module1Name", dataSnapshot.child(getString(R.string.module1)).child(getString(R.string.ModuleName)).getValue(String.class));
                        editor.apply();
                        name = cacheData.getString("module1Name", "");
                        module1Btn.setText(name);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);
    }
}
