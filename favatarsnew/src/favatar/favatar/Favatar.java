package favatar.favatar;

import java.util.*;

import android.content.SharedPreferences;
import android.widget.*;
import favatar.firstlogin.*;

public class Favatar {
	public static FavatarsnewActivity factivity;
	public ArrayList<FavatarPart> fparts;
	public static Favatar favatar = null;
	
	public static void setActivity(FavatarsnewActivity fa) {
		factivity = fa;
		
	}

	private static FavatarPart getPartById(int id) {
		return new FavatarPart((ImageView)factivity.findViewById(id));
		
	}
	/**
	 * 
	 * @param calories the amount of calories that will be added or subtracted
	 * @return Boolean whether the change was successful
	 * @see Choose FavatarPart to change based on amount of calories
	 */
	public static void change(int calories) {
		String favatarID = FavatarsnewActivity.favatarID;
		SharedPreferences favatar = factivity.getSharedPreferences("favatar_image", 0);
		SharedPreferences.Editor editor = favatar.edit();
		ImageView favatarI = (ImageView)factivity.findViewById(R.id.fav_image);
		ImageView arrowView = (ImageView)factivity.findViewById(R.id.arrow);
		TextView counter = (TextView)factivity.findViewById(R.id.counter);
		int score = new Integer((String)counter.getText());
		score += calories;
		String newValue = new Integer(score).toString();
		counter.setText(newValue);
		editor.putString("counter", newValue);
		editor.commit();
		
		if ( 100 < calories ) {
			arrowView.setImageResource(R.drawable.redarrow);
			if(favatarID.equals("f_000_000_00_000")) {
		   		favatarI.setImageResource(R.drawable.f_001_001_01_001);
		   		editor.putString("imageID", "f_001_001_01_001");
		   		editor.commit();
		   		FavatarsnewActivity.favatarID = "f_001_001_01_001";
		   		
			}
    		else if(favatarID.equals("f_001_001_01_001")) {
		   		favatarI.setImageResource(R.drawable.f_002_002_02_002);
		   		editor.putString("imageID", "f_002_002_02_002");
		   		editor.commit();
		   		FavatarsnewActivity.favatarID = "f_002_002_02_002";
    		}
	   		else if(favatarID.equals("f_002_002_02_002")) {
		   		favatarI.setImageResource(R.drawable.f_002_002_02_002);
	   		}
	   		else if(favatarID.equals("f_100_100_00_100")) {
		   		favatarI.setImageResource(R.drawable.f_101_101_01_101);
		   		editor.putString("imageID", "f_101_101_01_101");
		   		editor.commit();
		   		FavatarsnewActivity.favatarID = "f_101_101_01_101";
	   		}
	   		else if(favatarID.equals("f_101_101_01_101")) {
		   		favatarI.setImageResource(R.drawable.f_102_102_02_102);
		   		editor.putString("imageID", "f_102_102_02_102");
		   		editor.commit();
		   		FavatarsnewActivity.favatarID = "f_102_102_02_102";
	   		}
	   		else if(favatarID.equals("f_102_102_02_102")) {
		   		favatarI.setImageResource(R.drawable.f_102_102_02_102);
	   		}
	   		else if(favatarID.equals("m_000_000_00_000")) {
		   		favatarI.setImageResource(R.drawable.m_001_001_01_001);
		   		editor.putString("imageID", "m_001_001_01_001");
		   		editor.commit();
		   		FavatarsnewActivity.favatarID = "m_001_001_01_001";
	   		}
	   		else if(favatarID.equals("m_001_001_01_001")) {
		   		favatarI.setImageResource(R.drawable.m_002_002_02_002);
		   		editor.putString("imageID", "m_002_002_02_002");
		   		editor.commit();
		   		FavatarsnewActivity.favatarID = "m_002_002_02_002";
	   		}
	   		else if(favatarID.equals("m_002_002_02_002")) {
		   		favatarI.setImageResource(R.drawable.m_002_002_02_002);
	   		}
	   		else if(favatarID.equals("m_100_100_00_100")) {
		   		favatarI.setImageResource(R.drawable.m_101_101_01_101);
		   		editor.putString("imageID", "m_101_101_01_101");
		   		editor.commit();
		   		FavatarsnewActivity.favatarID = "m_101_101_01_101";
	   		}
	   		else if(favatarID.equals("m_101_101_01_101")) {
		   		favatarI.setImageResource(R.drawable.m_102_102_02_102);
		   		editor.putString("imageID", "m_102_102_02_102");
		   		editor.commit();
		   		FavatarsnewActivity.favatarID = "m_102_102_02_102";
	   		}
	   		else if(favatarID.equals("m_102_102_02_102")) {
		   		favatarI.setImageResource(R.drawable.m_102_102_02_102);
	   		}
			
		}
		else if ( 50 < calories ) {
			
			favatarI.setImageResource(R.drawable.m_001_001_01_001);
		}
		else if ( calories <= 50 ){
			return;
		}
		else if ( calories <= 0 ) {
			arrowView.setImageResource(R.drawable.greenarrow);
			favatarI.setImageResource(R.drawable.m_000_000_00_000);
		}
	}
}
/*
 * Deprecated code
 * 	FavatarPart head = getPartById(R.id.favatar_head);
		FavatarPart leftArm = getPartById(R.id.favatar_leftarm);
		FavatarPart rightArm = getPartById(R.id.favatar_rightarm);
		FavatarPart stomach = getPartById(R.id.favatar_stomach);
		FavatarPart leftLeg = getPartById(R.id.favatar_leftleg);
		FavatarPart rightLeg = getPartById(R.id.favatar_rightleg);	
		
		head.change(R.drawable.favatar1head);
			leftArm.change(R.drawable.favatar1leftarm);
			rightArm.change(R.drawable.favatar1rightarm);
			stomach.change(R.drawable.favatar1stomach);
			leftLeg.change(R.drawable.favatar1leftleg);
			rightLeg.change(R.drawable.favatar1rightleg);
 */
