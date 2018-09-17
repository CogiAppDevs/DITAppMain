package instaclone.mahmoud.com.collegeapptest5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MondayFragment extends Fragment {
    
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    //Widgets
    TextView tvModuleName;
    TextView tvModuleRoom;
    TextView tvModuleIcon;
    private Button editTxt;
    private ListView list;
    String value;
    private UsersAdapter adapter;
    private ArrayList<AssignmentListObject> arrayList;

    String module1;
    String module2;
    String module3;
    String module4;
    String module5;

    String name;
    String time;
    char icon;

    SharedPreferences.Editor editorMonday;
    SharedPreferences cacheDataMonday;

    public static MondayFragment newInstance(int position) {
        MondayFragment fragment = new MondayFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_monday, container, false);

        tvModuleIcon = (TextView) view.findViewById(R.id.timetable_row_icon);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        cacheDataMonday = getActivity().getSharedPreferences("TimetabeleValuesMonday", getContext().MODE_PRIVATE);
        editorMonday = cacheDataMonday.edit();

        if (!cacheDataMonday.getBoolean("firstTime", false)) {
            loadTimetable();

            editorMonday.putBoolean("firstTime", true);
            editorMonday.commit();
        }else {
            icon = cacheDataMonday.getString("module1Name", "").charAt(0);
        }


        arrayList = new ArrayList<AssignmentListObject>();
        adapter = new UsersAdapter(getActivity(), arrayList);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        AssignmentListObject object2 = new AssignmentListObject();
        object2.name = cacheDataMonday.getString("module1Name", "");
        object2.room = cacheDataMonday.getString("module1Time", "");
        arrayList.add(object2);
        adapter.notifyDataSetChanged();

        String test = "Test";

        return view;
    }

    public class UsersAdapter extends ArrayAdapter<AssignmentListObject> {

        public UsersAdapter(Context context, ArrayList<AssignmentListObject> users) {

            super(context, 0, users);

        }



        @Override

        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position

            AssignmentListObject user = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view

            if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.timetable_row, parent, false);

            }

            // Lookup view for data population

            tvModuleName = (TextView) convertView.findViewById(R.id.module_name_TV);
            tvModuleRoom = (TextView) convertView.findViewById(R.id.module_room_TV);
            tvModuleIcon = (TextView) convertView.findViewById(R.id.timetable_row_icon);

            // Populate the data into the template view using the data object

            tvModuleName.setText(user.name);
            tvModuleRoom.setText("Room: " + user.room);
            tvModuleIcon.setText(String.valueOf(icon).toUpperCase());

            // Return the completed view to render on screen

            return convertView;

        }

    }

    private void loadTimetable()
    {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("timetable").child("monday");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()) {

                    if(ds.getKey().equals("module1"))
                    {
                        editorMonday.putString("module1Name", dataSnapshot.child("module1").child("module_name").getValue(String.class));
                        editorMonday.putString("module1Time", dataSnapshot.child("module1").child("room").getValue(String.class));
                        editorMonday.apply();
                        tvModuleName.setText(cacheDataMonday.getString("module1Name", ""));
                        tvModuleRoom.setText("Room: " + cacheDataMonday.getString("module1Time", ""));
                        tvModuleIcon.setText(cacheDataMonday.getString("module1Name", "").substring(0, 1).toUpperCase());
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);
    }

    public void onBackPressed() {

    }

}

















