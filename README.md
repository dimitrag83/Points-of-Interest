# Points-of-Interest
Web application showing nearby POIs


Java application (JFrame) that acts as a customer / consumer of the RESTful Google Places web service client.

The basic service returns points of interest (POIs) close to a specific location, based on some limitations.

The web service returns information formatted in JSON format. 

The app's graphical interface will display fields in which the user will fill in 
  the latitude and longitude of a location, 
  the desired search radius, 
  and a list of POIs categories (no multiple selection on the list).

When user presses the 'Retrieve POIs' button and, if the entry is valid, he will see a list of points of interest in the right list, 
sorted in ascending order from the indicated location (see picture below). 
Selecting a POI in this list will show below POI items: distance from location, location ('neighborhood') and user rating ('rating').

When a user selects a POI (on the right list), a POI image will also be displayed. 
In order for this feature to be implemented, the place-complementary to Google Places is used.
