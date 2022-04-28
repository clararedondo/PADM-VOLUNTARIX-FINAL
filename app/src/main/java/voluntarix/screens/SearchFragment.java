package voluntarix.screens;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private User data;
    private EditText title, desc, tags, day, month, year, location;
    private Button saveButton;

    public SearchFragment(){
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = (EditText) view.findViewById(R.id.etVTitle);
        desc = (EditText) view.findViewById(R.id.etVDesc);
        tags = (EditText) view.findViewById(R.id.etVTags);
        day = (EditText) view.findViewById(R.id.etVDay);
        month = (EditText) view.findViewById(R.id.etVMonth);
        year = (EditText) view.findViewById(R.id.etVYear);
        location = (EditText) view.findViewById(R.id.etVLocation);

        saveButton = (Button) view.findViewById(R.id.bVSave);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                int tday, tmonth, tyear;
                tday = Integer.parseInt(day.getText().toString());
                tmonth = Integer.parseInt(month.getText().toString());
                tyear = Integer.parseInt(year.getText().toString());
                if ((tday < 1 || tday > 31) || (tmonth < 1 || tmonth > 12)) {
                    Toast toast = Toast.makeText(view.getContext(), "Invalid date", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    List<String> tagList = new ArrayList<String>();
                    for (String str : tags.getText().toString().split(", "))
                        tagList.add(str);

                    DataBaseAccess db = new DataBaseAccess(view.getContext());
                    db.storeVoluntaryEvent(new VoluntaryEvent(title.getText().toString(), desc.getText().toString(), data.username, tagList, tday, tmonth, tyear, location.getText().toString()));
                }
            }
        });
    }
}