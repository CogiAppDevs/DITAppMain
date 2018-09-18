package instaclone.mahmoud.com.collegeapptest5;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class AssignmentFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Spinner assignmentSpinner;
    UsersAdapter usersAdapter;
    ListView listView;
    ArrayAdapter<CharSequence> adapter;
    AssignmentListObject object;
    ArrayList<AssignmentListObject> module1List;

    TextView tvAssignmentHeading;
    TextView tvAssignmenDescription;
    TextView tvAssignmentDueDate;

    String moduleName;
    String duedate;
    String descriptionInput;

    //Shared Preference
    SharedPreferences.Editor editor;
    SharedPreferences cacheData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_assignment, container, false);

        assignmentSpinner = view.findViewById(R.id.assignmentSpinner);
        listView = view.findViewById(R.id.list);
        cacheData = getActivity().getSharedPreferences("AssignmentValues", getContext().MODE_PRIVATE);
        editor = cacheData.edit();

        if (!cacheData.getBoolean("firstTime", false)) {
            loadTimetable();

            editor.putBoolean("firstTime", true);
            editor.commit();
        }


        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.modules, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignmentSpinner.setAdapter(adapter);
        assignmentSpinner.setOnItemSelectedListener(this);

        return view;

    }

    private void createObject(String moduleName, String dueDate, String description)
    {
        module1List = new ArrayList<AssignmentListObject>();
        usersAdapter = new UsersAdapter(getActivity(), module1List);
        listView.setAdapter(usersAdapter);
        usersAdapter.notifyDataSetChanged();

        object = new AssignmentListObject();
        object.name = moduleName;
        object.time = dueDate;
        object.room = description;

        module1List.add(object);
        usersAdapter.notifyDataSetChanged();
    }

    public class UsersAdapter extends ArrayAdapter<AssignmentListObject> {

        public UsersAdapter(Context context, ArrayList<AssignmentListObject> users) {
            super(context, 0, users);
        }



        @Override

        public View getView(int position, View convertView, ViewGroup parent) {

            AssignmentListObject user = getItem(position);

            if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.assignment_row, parent, false);

            }

            tvAssignmentHeading = convertView.findViewById(R.id.heading_tv);
            tvAssignmentDueDate = convertView.findViewById(R.id.due_date_tv);
            tvAssignmenDescription = convertView.findViewById(R.id.description_tv);

            tvAssignmentHeading.setText(user.name);
            tvAssignmentDueDate.setText("Due: " + user.time);
            tvAssignmenDescription.setText("Details: " + user.room);

            return convertView;

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String itemSelected = parent.getItemAtPosition(position).toString();

        if(itemSelected.equals(getString(R.string.module1)))
        {
            moduleName = cacheData.getString("module1Name", "");
            duedate = cacheData.getString("module1DueDate", "");
            descriptionInput = cacheData.getString("module1Description", "");
            createObject(moduleName, duedate, descriptionInput);

        }else if (itemSelected.equals(getString(R.string.module2))){

            moduleName = cacheData.getString("module2Name", "");
            duedate = cacheData.getString("module2DueDate", "");
            descriptionInput = cacheData.getString("module2Description", "");
            createObject(moduleName, duedate, descriptionInput);

        }else if (itemSelected.equals(getString(R.string.module3))){

            moduleName = cacheData.getString("module3Name", "");
            duedate = cacheData.getString("module3DueDate", "");
            descriptionInput = cacheData.getString("module3Description", "");
            createObject(moduleName, duedate, descriptionInput);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void loadTimetable()
    {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child(getString(R.string.assignments));

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()) {

                    if(ds.getKey().equals("module1")) {
                        editor.putString("module1Name", dataSnapshot.child(getString(R.string.module1)).child("module_name").getValue(String.class));
                        editor.putString("module1DueDate", dataSnapshot.child(getString(R.string.module1)).child("duedate").getValue(String.class));
                        editor.putString("module1Description", dataSnapshot.child(getString(R.string.module1)).child("description").getValue(String.class));
                        editor.apply();

                        tvAssignmentHeading.setText(cacheData.getString("module1Name", ""));
                        tvAssignmenDescription.setText(cacheData.getString("module1Description", ""));
                        tvAssignmentDueDate.setText("Due: " + cacheData.getString("module1DueDate", ""));

                    }else if (ds.getKey().equals("module2")){
                        editor.putString("module2Name", dataSnapshot.child(getString(R.string.module2)).child("module_name").getValue(String.class));
                        editor.putString("module2DueDate", dataSnapshot.child(getString(R.string.module2)).child("duedate").getValue(String.class));
                        editor.putString("module2Description", dataSnapshot.child(getString(R.string.module2)).child("description").getValue(String.class));
                        editor.apply();

                        tvAssignmentHeading.setText(cacheData.getString("module2Name", ""));
                        tvAssignmenDescription.setText(cacheData.getString("module2Description", ""));
                        tvAssignmentDueDate.setText("Due: " + cacheData.getString("module2DueDate", ""));

                    }else if (ds.getKey().equals("module3")){
                        editor.putString("module3Name", dataSnapshot.child(getString(R.string.module3)).child("module_name").getValue(String.class));
                        editor.putString("module3DueDate", dataSnapshot.child("module3").child("duedate").getValue(String.class));
                        editor.putString("module3Description", dataSnapshot.child("module3").child("description").getValue(String.class));
                        editor.apply();

                        tvAssignmentHeading.setText(cacheData.getString("module3Name", ""));
                        tvAssignmenDescription.setText(cacheData.getString("module3Description", ""));
                        tvAssignmentDueDate.setText(cacheData.getString("module3DueDate", ""));
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);
    }
}



















