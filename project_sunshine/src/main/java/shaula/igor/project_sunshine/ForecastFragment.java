package shaula.igor.project_sunshine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by igor shaula
 */
public class ForecastFragment extends Fragment {

    public ForecastFragment() {
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

        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7&APPID=625d8eee94f91c38eb09955b40b7de73";
/*
        try {
            String resultFromAsyncTask = new FetchWeatherTask().execute(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
*/

        return rootView;
    }

    private class FetchWeatherTask extends AsyncTask<URL, Integer, String> {

        protected String doInBackground(URL... urls) {

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                URL url = urls[0];
//                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line).append("\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e("ForecastFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("ForecastFragment", "Error closing stream", e);
                    }
                }
            }
            return forecastJsonStr;
        }

        protected void onProgressUpdate(Integer... progress) {
//            setProgressPercent(progress[0]);
        }

        protected void onPostExecute(String result) {
//            showDialog("Downloaded " + result + " bytes");
        }
    }
}