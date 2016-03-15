package shaula.igor.project_sunshine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            String[] weatherArray = {
                    getResources().getStringArray(R.array.week)[0] + " - Cloudy, 4`C",
                    getResources().getStringArray(R.array.week)[1] + " - Cloudy, 5`C",
                    getResources().getStringArray(R.array.week)[2] + " - Cloudy, 6`C",
                    getResources().getStringArray(R.array.week)[3] + " - Cloudy, 7`C",
                    getResources().getStringArray(R.array.week)[4] + " - Cloudy, 8`C",
                    getResources().getStringArray(R.array.week)[5] + " - Sunny, 9`C",
                    getResources().getStringArray(R.array.week)[6] + " - Sunny, 0`C"
            };
            ArrayList<String> weatherList = new ArrayList<>(Arrays.asList(weatherArray));

            ArrayAdapter<String> weatherListAdapter = new ArrayAdapter<>(getContext(), R.layout.list_item_forecast_tv, R.id.tvSingle, weatherList);

            ListView lvForecast = (ListView) rootView.findViewById(R.id.lvForecast);
            lvForecast.setAdapter(weatherListAdapter);

            return rootView;
        }
    }
}