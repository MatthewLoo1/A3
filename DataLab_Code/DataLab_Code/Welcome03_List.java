/*
 * Arrays of objects
 */

import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Welcome03_List {
   public static void main(String[] args) {
      DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/index.xml").load();
      ArrayList<WeatherStation> allstns = ds.fetchList("WeatherStation", "station/station_name", 
             "station/station_id", "station/state",
             "station/latitude", "station/longitude");
      System.out.println("Total stations: " + allstns.size());
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter a state abbreviation: ");
      String state = sc.next();
      System.out.println("Stations in " + state);
      for (WeatherStation ws : allstns) {
         if (ws.isLocatedInState(state)) {
            System.out.println("  " + ws.getId() + ": " + ws.getName());
         }
         
      }
      WeatherStation southernmostStation = findSouthernState(allstns);
      System.out.println("Southernmost Station: " + southernmostStation.getName() + ", Latitude: " + southernmostStation.getLatitude());
   }
   public static WeatherStation findSouthernState(ArrayList<WeatherStation> stations) {
      WeatherStation mostSouthernState = stations.get(0);
      for (int i = 0; i < stations.size(); i++) {
         if (stations.get(i).getLatitude() < mostSouthernState.getLatitude()) {
            mostSouthernState = stations.get(i);
         }
      }
      
      return mostSouthernState;

   }
}