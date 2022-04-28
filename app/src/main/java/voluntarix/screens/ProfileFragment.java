package voluntarix.screens;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private TextView name, last_name, tags, location, description;
    private Button edit_profile, calendar;
    private User data;

    public ProfileFragment(){
        // require a empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            this.data = ((MainActivity) context).preferences.getLoggedInUser();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = (TextView) view.findViewById(R.id.name);
        last_name = (TextView) view.findViewById(R.id.lastname);
        location = (TextView) view.findViewById(R.id.location);
        tags = (TextView) view.findViewById(R.id.tags);
        description = (TextView) view.findViewById(R.id.description);

        name.setText("Name: " + data.getName());
        last_name.setText("Last name: " + data.getLastName());
        location.setText("Location: " + data.getLocation());
        description.setText("Description: " + data.getDescription());

        edit_profile = (Button) view.findViewById(R.id.edit_button);
        calendar = (Button) view.findViewById(R.id.calendar_button);

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Intent intent = new Intent(view.getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Intent intent = new Intent(view.getContext(), CalendarActivity.class);
                startActivity(intent);
            }
        });
    }

}