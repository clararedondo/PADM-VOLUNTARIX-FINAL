package voluntarix.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.util.List;

public class VoluntaryActivity extends AppCompatActivity {

    private List<VoluntaryEvent> vol_list;
    private int max_pages;
    private int page = 0;

    private TextView title;
    private TextView description;
    private TextView publisher;
    private TextView tags;
    private TextView addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voluntary);
        Intent intent = getIntent();
        int day = intent.getIntExtra("day", 1);
        int month = intent.getIntExtra("month", 1);
        int year = intent.getIntExtra("year",1);

        DataBaseAccess db = new DataBaseAccess(this);
        vol_list = db.getEventsOn(day, month, year);

        title = (TextView) findViewById(R.id.vol_title);
        description = (TextView) findViewById(R.id.vol_desc);
        publisher = (TextView) findViewById(R.id.vol_publisher);
        tags = (TextView) findViewById(R.id.vol_tags);
        addr = (TextView) findViewById(R.id.addr);

        Button nextButton = (Button) findViewById(R.id.next);
        Button previousButton = (Button) findViewById(R.id.previous);
        Button routeButton = (Button) findViewById(R.id.bgetRoute);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = (page + 1) % max_pages;
                updateTexts();
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = page - 1;
                if (page == -1)
                    page = max_pages - 1;
                updateTexts();
            }
        });

        routeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = vol_list.get(page).location;
                Uri navigation = Uri.parse("google.navigation:q=" + location.replace(" ", "+") + "&avoid=t");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigation);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        updateTexts();
    }

    private void updateTexts() {
        this.title.setText(vol_list.get(page).title);
        this.description.setText(vol_list.get(page).description);
        this.publisher.setText(vol_list.get(page).publisher);
        this.tags.setText(vol_list.get(page).tags.toString());
        this.addr.setText(vol_list.get(page).location.toString());
    }
}