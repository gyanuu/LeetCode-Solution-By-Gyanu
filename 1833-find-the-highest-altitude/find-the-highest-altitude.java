class Solution {
    /**
     * Finds the highest altitude reached during a trip.
     * Starting from altitude 0, each element in gain represents the altitude change.
     * 
     * @param gain Array where gain[i] is the net altitude change between points i and i+1
     * @return The highest altitude reached during the trip
     */
    public int largestAltitude(int[] gain) {
        // Track the maximum altitude reached
        int maxAltitude = 0;
      
        // Track the current altitude as we traverse
        int currentAltitude = 0;
      
        // Iterate through each altitude change
        for (int altitudeChange : gain) {
            // Update current altitude by adding the change
            currentAltitude += altitudeChange;
          
            // Update maximum altitude if current is higher
            maxAltitude = Math.max(maxAltitude, currentAltitude);
        }
      
        return maxAltitude;
    }
}
