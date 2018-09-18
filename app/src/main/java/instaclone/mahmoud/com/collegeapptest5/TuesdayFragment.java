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

public class TuesdayFragment extends Fragment {

    //Firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    //Widgets
    TextView tvModuleName;
    TextView tvModuleRoom;
    TextView tvModuleIcon;
    TextView tvModuleTime;
    ListView list;
    String value;
    UsersAdapter adapter;
    ArrayList<AssignmentListObject> arrayList;
    AssignmentListObject object;

    //Variables
    String module1;
    String module2;
    String module3;
    String module4;
    String module5;
    char icon;

    //Shared Preference
    SharedPreferences.Editor editor;
    SharedPreferences cacheData;

    public static TuesdayFragment newInstance(int position) {
        TuesdayFragment fragment = new TuesdayFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tuesday, container, false);

        list = (ListView)view.findViewById(R.id.list);
        tvModuleIcon = (TextView) view.findViewById(R.id.timetable_row_icon);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        cacheData = getActivity().getSharedPreferences(getString(R.string.sharedPrefTuesday), getContext().MODE_PRIVATE);
        editor = cacheData.edit();

        if (!cacheData.getBoolean("firstTime", false)) {

            loadTimetable();

            editor.putBoolean("firstTime", true);
            editor.commit();

        }else {
            icon = cacheData.getString("module1Name", "").charAt(0);
        }


        createObject();

        return view;

    }

    private void createObject()
    {
        arrayList = new ArrayList<AssignmentListObject>();
        adapter = new UsersAdapter(getActivity(), arrayList);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        object = new AssignmentListObject();
        object.name = cacheData.getString("module1Name", "");
        object.room = cacheData.getString("module1Room", "");
        object.time = cacheData.getString("module1Time", "");

        arrayList.add(object);
        adapter.notifyDataSetChanged();
    }

    public class UsersAdapter extends ArrayAdapter<AssignmentListObject> {

        public UsersAdapter(Context context, ArrayList<AssignmentListObject> users) {

            super(context, 0, users);

        }



        @Override

        public View getView(int position, View convertView, ViewGroup parent) {


            AssignmentListObject user = getItem(position);


            if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.timetable_row, parent, false);

            }

            tvModuleName = (TextView) convertView.findViewById(R.id.module_name_TV);
            tvModuleRoom = (TextView) convertView.findViewById(R.id.module_room_TV);
            tvModuleTime = (TextView) convertView.findViewById(R.id.module_time_TV);
            tvModuleIcon = (TextView) convertView.findViewById(R.id.timetable_row_icon);

            tvModuleName.setText(user.name);
            tvModuleTime.setText(user.time);
            tvModuleRoom.setText(user.room);
            tvModuleIcon.setText(String.valueOf(icon).toUpperCase());

            return convertView;

        }

    }

    private void loadTimetable()
    {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("timetable").child("tuesday");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()) {

                    if(ds.getKey().equals("module1"))
                    {
                        editor.putString("module1Name", dataSnapshot.child(getString(R.string.module1)).child(getString(R.string.ModuleName)).getValue(String.class));
                        editor.putString("module1Time", dataSnapshot.child(getString(R.string.module1)).child(getString(R.string.ModuleTime)).getValue(String.class));
                        editor.putString("module1Room", dataSnapshot.child(getString(R.string.module1)).child(getString(R.string.ModuleRoom)).getValue(String.class));
                        editor.apply();

                        tvModuleName.setText(cacheData.getString("module1Name", ""));
                        tvModuleTime.setText(cacheData.getString("module1Time", ""));
                        tvModuleRoom.setText(cacheData.getString("module1Room", ""));
                        tvModuleIcon.setText(cacheData.getString("module1Name", "").substring(0, 1).toUpperCase());
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);
    }

}
