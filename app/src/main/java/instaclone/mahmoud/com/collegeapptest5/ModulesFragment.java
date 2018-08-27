package instaclone.mahmoud.com.collegeapptest5;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ModulesFragment extends Fragment {

    Button module1;
    Button module2;
    Button module3;
    Button module4;
    Button module5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_modules, container, false);

        module1 = (Button)view.findViewById(R.id.module1);
        module2 = (Button)view.findViewById(R.id.module2);
        module3 = (Button)view.findViewById(R.id.module3);
        module4 = (Button)view.findViewById(R.id.module4);
        module5 = (Button)view.findViewById(R.id.module5);

        setupWidgets();

        return view;
    }

    private void setupWidgets()
    {
        module1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ModuleDetailsActivity.class);
                startActivity(intent);
            }
        });

        module2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ModuleDetailsActivity.class);
                startActivity(intent);
            }
        });

        module3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ModuleDetailsActivity.class);
                startActivity(intent);
            }
        });

        module4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ModuleDetailsActivity.class);
                startActivity(intent);
            }
        });

        module5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ModuleDetailsActivity.class);
                startActivity(intent);
            }
        });

    }


}
