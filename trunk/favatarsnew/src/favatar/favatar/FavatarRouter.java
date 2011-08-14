package favatar.favatar;

import java.util.*;
import favatar.tagcloud.*;

public class FavatarRouter {
	public void applyFoodItems(ArrayList<String> foods, ArrayList<Integer> calorieValues) {
		int totalCalories = 0;
		Iterator<Integer> cal = calorieValues.iterator();
		for ( Iterator<String> i = foods.iterator(); i.hasNext();) {
			String foodName = i.next();
			int calories = cal.next();
			totalCalories += calories;
		}
		//Favatar.change(totalCalories);
	}
}
