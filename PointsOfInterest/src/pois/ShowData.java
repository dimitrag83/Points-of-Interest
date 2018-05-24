/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pois;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author dimitragoudela
 */
public class ShowData{
   double lat,lng,rating,distance;
   String photoRef;
   String vicinity;
    
   //Αποθηκε�?ει τα στοιχεία εν�?ς google place στις κατάλληλες μεταβλητές
    public void placeToShow(JSONArray jsonArrayResults, int index) {
         JSONObject placeToShowObj;
         try {
                        placeToShowObj= (JSONObject) jsonArrayResults.get(index);
                        if(!placeToShowObj.getString("vicinity").isEmpty())
                            vicinity = (String) placeToShowObj.get("vicinity");
                        else vicinity = "Δεν υπά�?χει διε�?θυνση";
                          
                        if(!placeToShowObj.isNull("rating"))
                            rating = placeToShowObj.getDouble("rating");
                        else rating = 0.0;
                        
                        lat=placeToShowObj.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                        lng=placeToShowObj.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                        
                        if(!placeToShowObj.isNull("photos"))
                        {
                            JSONArray jsonArrayPhotos = placeToShowObj.getJSONArray("photos"); 
                            JSONObject photosObj = (JSONObject) jsonArrayPhotos.get(0);
                            photoRef=photosObj.getString("photo_reference");
                        }  else  photoRef= null;

         } catch (JSONException ex) {
                        Logger.getLogger(PointsOfInterest.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    //Διαβάζει μία εικ�?να απο URL
    public BufferedImage getImage(String path) throws IOException{
               
        URL url=null;
        BufferedImage image = null;
                    try {
                        url = new URL(path);
                        image = ImageIO.read(url);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(PointsOfInterest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   return image;
    }
    //Υπολογισμ�?ς απ�?στασης με δεδομένα τις συντεταγμένες
     public double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        
        double R = 6372.8*1000; // Earth radius in metres
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
 
        double dLatsin2 = Math.sin(dLat/2) * Math.sin(dLat/2);
        double dLonsin2 = Math.sin(dLon/2) * Math.sin(dLon/2);
        double a = dLatsin2+Math.cos(lat1)*Math.cos(lat2)*dLonsin2;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }
    
}
