package instaclone.mahmoud.com.collegeapptest5;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class AssignmentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_assignment, container, false);

        List<String> list = new ArrayList<>();
        list.add("Mahmoud");

        Spinner menu = (Spinner) view.findViewById(R.id.modules_spinner);

        return view;

    }

}
