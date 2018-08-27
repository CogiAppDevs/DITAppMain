package instaclone.mahmoud.com.collegeapptest5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TimetableFragment extends Fragment {

    TextView timetableModule;
    TextView timetableRoom;
    TextView timetableTime;
    TextView date;
    ImageView nextDay;
    ImageView previousDay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }
}
