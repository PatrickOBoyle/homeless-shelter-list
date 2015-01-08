package com.patrickoboyle.computersandsociety;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    // Information about the selected shelter, used by the Maps Activity
    public static double selectedLng = 0.0;
    public static double selectedLat = 0.0;
    public static String selectedShelter = "";
    public static String selectedAddress = "";
    public static double selectedWalkingTime = 0.0;

    // Location information about the specific terminal, with more, an array with each terminal having an index ID would be more appropriate
    public static double localLat = 53.343215;
    public static double localLng = -6.257169;
    public static String localStation = "YOUR LOCATION";

    // Create list
    private List<HomelessShelters> homelessSheltersList = new ArrayList<HomelessShelters>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createHomelessShelters();
        createListView();
        listViewInteraction();

    }

    // Creates each Homeless Shelter that is available. Not all data is accurate / real.
    private void createHomelessShelters(){

        homelessSheltersList.add(new HomelessShelters("Focus Ireland", "15 Eustace Street", 1, 6, 53.3450981,-6.2640227));
        homelessSheltersList.add(new HomelessShelters("Homeless Persons Unit", "41 Castle Street", 0, 7, 53.3432967,-6.2686355));
        homelessSheltersList.add(new HomelessShelters("Simon Community Dublin", "Grantham Street", 2, 9, 53.3383021, -6.2625744));
        homelessSheltersList.add(new HomelessShelters("VDP Back Lane Hostel", "18 Nicholas Street", 5, 16, 53.3423598, -6.2725654));
    }

    // Creates the list item view
    private void createListView(){
        ArrayAdapter<HomelessShelters> adapter = new MyListAdapter();
        // Creates a list view from the list in activity_main.xml
        ListView list = (ListView) findViewById(android.R.id.list);
        // Sets the adapter for the list view
        list.setAdapter(adapter);

    }

    private void listViewInteraction(){
        ListView list = (ListView) findViewById(android.R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomelessShelters homelessShelterPressed = homelessSheltersList.get(position);
                selectedLat = homelessShelterPressed.getShelterLat();
                selectedLng = homelessShelterPressed.getShelterLng();
                selectedShelter = homelessShelterPressed.getShelterName();
                selectedAddress = homelessShelterPressed.getShelterAddress();
                selectedWalkingTime = homelessShelterPressed.getWalkingTime();

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<HomelessShelters> {
        public MyListAdapter(){
            super(MainActivity.this, R.layout.row_layout, homelessSheltersList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Ensure that the view != null
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.row_layout, parent, false);
            }

            // Get index of the current shelter
            HomelessShelters currentShelter = homelessSheltersList.get(position);

            // Adds the name of the shelter to the list item
            TextView nameText = (TextView) itemView.findViewById(R.id.txtName);
            nameText.setText(currentShelter.getShelterName() +",");

            // Adds the shelter address to the list item
            TextView addressText = (TextView) itemView.findViewById(R.id.txtAddress);
            addressText.setText(currentShelter.getShelterAddress());

            // ... shelter vacancies ...
            TextView vacanciesText = (TextView) itemView.findViewById(R.id.txtVacancies);
            vacanciesText.setText("" + currentShelter.getVacancies() + " vacancies remaining");

            // ... walking time to shelter ...
            TextView walkTimeText = (TextView) itemView.findViewById(R.id.txtWalkTime);
            walkTimeText.setText("" + currentShelter.getWalkingTime() + " minute walk");

            return itemView;

        }

    }

}
