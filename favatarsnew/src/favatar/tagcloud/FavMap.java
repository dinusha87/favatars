package favatar.tagcloud;

import java.util.ArrayList;

public class FavMap<X, Y>{
	ArrayList<X> listX = new ArrayList<X>();
	ArrayList<Y> listY = new ArrayList<Y>();
	
	public FavMap(){ }
	
	public void put(X FirstVal, Y SecondVal){
		listX.add(FirstVal);
		listY.add(SecondVal);
	}
	
	public ArrayList<Y> getFromFirst(X value){
		ArrayList<Y> temp = new ArrayList<Y>();
		
		for (int i = 0; i < listX.size(); ++i)
			if (listX.get(i) == value)
				temp.add(listY.get(i));
		
		return temp;
	}
	public ArrayList<X> getFromSecond(Y value){
		ArrayList<X> temp = new ArrayList<X>();
		
		for (int i = 0; i < listY.size(); ++i)
			if (listY.get(i) == value)
				temp.add(listX.get(i));
		
		return temp;
	}
}

