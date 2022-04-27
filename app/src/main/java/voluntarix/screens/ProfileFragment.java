package voluntarix.screens;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private TextView name_text;
    private TextView surname_text;
    UserLocalStore userLocalStore;

    public ProfileFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name_text = (TextView) view.findViewById(R.id.name);
        surname_text = (TextView) view.findViewById(R.id.surname);

        userLocalStore = new UserLocalStore(requireContext());
        User user = userLocalStore.getLoggedInUser();

        name_text.setText(user.getName());
        surname_text.setText(user.getLastName());
    }

}