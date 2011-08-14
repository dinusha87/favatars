package favatar.firstlogin;


import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import favatar.favatar.Favatar;
import favatar.json.JsonTest;



import android.widget.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class FavatarsnewActivity extends Activity {
	JsonTest JT=new JsonTest();
	public static String smallString = "50";
	public static String mediumString = "150";
	public static String largeString = "250";
	public static String favatarID="";
	public static String counter = "";
	public String diseaseName="";
	String imageID="";
	enum WindowMode{
		FavatarSelect,
		DataEnter,
		HomePage,
		Register,
		Conversasion
	}
	static WindowMode cWindowMode = WindowMode.FavatarSelect;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 
        super.onCreate(savedInstanceState);
        SharedPreferences favatar = getSharedPreferences("favatar_image", 0);
        Favatar.setActivity(this);
        common.fma = this;
        favatarID = favatar.getString("imageID", null);
        counter = favatar.getString("counter", null);
        
        
        if(favatar.getString("imageID", null) == null){
        	
        	if (cWindowMode == WindowMode.FavatarSelect)
        		//cWindowMode= WindowMode.HomePage;
        	setContentView(R.layout.main);
        	
        }
        else
        	cWindowMode = WindowMode.HomePage;
        
        if (cWindowMode == WindowMode.DataEnter){
    		setContentView(R.layout.infoenter);
    		ImageView favatarI=(ImageView)findViewById(R.id.imageView15);
    	
    		Spinner spinner = (Spinner) findViewById(R.id.spinner);
	   		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	   										this, R.array.planets_array, android.R.layout.simple_spinner_item);
       										adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       										spinner.setAdapter(adapter);
       										spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
 	   
    		if(favatarID.equals("f_000_000_00_000"))
		   		favatarI.setImageResource(R.drawable.f_000_000_00_000);
    		else if(favatarID.equals("f_001_001_01_001"))
		   		favatarI.setImageResource(R.drawable.f_001_001_01_001);
	   		else if(favatarID.equals("f_002_002_02_002"))
		   		favatarI.setImageResource(R.drawable.f_002_002_02_002);
	   		else if(favatarID.equals("f_100_100_00_100"))
		   		favatarI.setImageResource(R.drawable.f_100_100_00_100);
	   		else if(favatarID.equals("f_101_101_01_101"))
		   		favatarI.setImageResource(R.drawable.f_101_101_01_101);
	   		else if(favatarID.equals("f_102_102_02_102"))
		   		favatarI.setImageResource(R.drawable.f_102_102_02_102);
	   		else if(favatarID.equals("m_000_000_00_000"))
		   		favatarI.setImageResource(R.drawable.m_000_000_00_000);
	   		else if(favatarID.equals("m_001_001_01_001"))
		   		favatarI.setImageResource(R.drawable.m_001_001_01_001);
	   		else if(favatarID.equals("m_002_002_02_002"))
		   		favatarI.setImageResource(R.drawable.m_002_002_02_002);
	   		else if(favatarID.equals("m_100_100_00_100"))
		   		favatarI.setImageResource(R.drawable.m_100_100_00_100);
	   		else if(favatarID.equals("m_101_101_01_101"))
		   		favatarI.setImageResource(R.drawable.m_101_101_01_101);
	   		else if(favatarID.equals("m_102_102_02_102"))
		   		favatarI.setImageResource(R.drawable.m_102_102_02_102);
    	}
    	
        else if (cWindowMode == WindowMode.HomePage) {
    		setContentView(R.layout.home);
    		ImageView favatarI = (ImageView)findViewById(R.id.fav_image);
			 TextView counter = (TextView)findViewById(R.id.counter);
			 SharedPreferences prefs = getSharedPreferences("favatar_image",0);
			 SharedPreferences.Editor edit = prefs.edit();
			 String num = prefs.getString("counter", null);
			 //get the image id of favatar
			 if(favatarID.equals("f_000_000_00_000")) {
			   		favatarI.setImageResource(R.drawable.f_000_000_00_000);
			   		if ( num == null ) {
			   			edit.putString("counter", smallString);
			   		}
			   		else {
			   			counter.setText(num);
			   		}
			 }
			 else if(favatarID.equals("f_001_001_01_001")) {
				 favatarI.setImageResource(R.drawable.f_001_001_01_001);
				 if ( num == null ) {
					 edit.putString("counter", mediumString);
			   		 counter.setText(mediumString);
			   	 }
			   	 else {
			   		 counter.setText(num);
			   	 }
				 
			 }
			 else if(favatarID.equals("f_002_002_02_002")) {
				favatarI.setImageResource(R.drawable.f_002_002_02_002);
				if ( num == null ) {
					 edit.putString("counter", largeString);
			   		 counter.setText(largeString);
			   	 }
			   	 else {
			   		 counter.setText(num);
			   	 }
			 }
			 else if(favatarID.equals("f_100_100_00_100")) {
				favatarI.setImageResource(R.drawable.f_100_100_00_100);
				if ( num == null ) {
		   			edit.putString("counter", smallString);
		   		}
		   		else {
		   			counter.setText(num);
		   		}
			 }
			 else if(favatarID.equals("f_101_101_01_101")) {
				favatarI.setImageResource(R.drawable.f_101_101_01_101);
				if ( num == null ) {
					 edit.putString("counter", mediumString);
			   		 counter.setText(mediumString);
			   	 }
			   	 else {
			   		 counter.setText(num);
			   	 }
			 }
			 else if(favatarID.equals("f_102_102_02_102")) {
				favatarI.setImageResource(R.drawable.f_102_102_02_102);
				if ( num == null ) {
					 edit.putString("counter", largeString);
			   		 counter.setText(largeString);
			   	 }
			   	 else {
			   		 counter.setText(num);
			   	 }
			 }
			 else if(favatarID.equals("m_000_000_00_000")) {
				favatarI.setImageResource(R.drawable.m_000_000_00_000);
				if ( num == null ) {
		   			edit.putString("counter", smallString);
		   		}
		   		else {
		   			counter.setText(num);
		   		}
			 }
			 else if(favatarID.equals("m_001_001_01_001")) {
				favatarI.setImageResource(R.drawable.m_001_001_01_001);
				if ( num == null ) {
					 edit.putString("counter", mediumString);
			   		 counter.setText(mediumString);
			   	 }
			   	 else {
			   		 counter.setText(num);
			   	 }
			 }
			 else if(favatarID.equals("m_002_002_02_002")) {
				favatarI.setImageResource(R.drawable.m_002_002_02_002);
				if ( num == null ) {
					 edit.putString("counter", largeString);
			   		 counter.setText(largeString);
			   	 }
			   	 else {
			   		 counter.setText(num);
			   	 }
			 }
			 else if(favatarID.equals("m_100_100_00_100")) {
				favatarI.setImageResource(R.drawable.m_100_100_00_100);
				if ( num == null ) {
		   			edit.putString("counter", smallString);
		   		}
		   		else {
		   			counter.setText(num);
		   		}
			 }
			 else if(favatarID.equals("m_101_101_01_101")) {
				favatarI.setImageResource(R.drawable.m_101_101_01_101);
				if ( num == null ) {
					 edit.putString("counter", mediumString);
			   		 counter.setText(mediumString);
			   	 }
			   	 else {
			   		 counter.setText(num);
			   	 }
			 }
			 else if(favatarID.equals("m_102_102_02_102")) {
				favatarI.setImageResource(R.drawable.m_102_102_02_102);
				if ( num == null ) {
					 edit.putString("counter", largeString);
			   		 counter.setText(largeString);
			   	 }
			   	 else {
			   		 counter.setText(num);
			   	 }
			 }
			 edit.commit();
    	}
        else if(cWindowMode==WindowMode.Register){
        	setContentView(R.layout.register);
        }
        
        
       
    }
    public class MyOnItemSelectedListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
            View view, int pos, long id) {
          Toast.makeText(parent.getContext(), "The disease is " +
              parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
          diseaseName=parent.getItemAtPosition(pos).toString();
        }

        public void onNothingSelected(AdapterView parent) {
          // Do nothing.
        }
    }
    
   // selecting the favatar for the first time.
   // this method should be called after checking the value of SharedPref. favatar("imageID")
    
   public void selectFavatar(){   
	   
		  String favatar_id=favatarID;			   
	      SharedPreferences favatar = getSharedPreferences("favatar_image", 0);
	      SharedPreferences.Editor editor = favatar.edit();
	      editor.putString("imageID",favatar_id);

	      // Commit the edits!
	      editor.commit();
	      
	     setContentView(R.layout.infoenter);
	     cWindowMode = WindowMode.DataEnter;
	     
	     Spinner spinner = (Spinner) findViewById(R.id.spinner);
		   ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	       this, R.array.planets_array, android.R.layout.simple_spinner_item);
	       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	       spinner.setAdapter(adapter);
	       spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

       
      
	   ImageView favatarI=(ImageView)findViewById(R.id.imageView15);
	   
	   /*Context context = getApplicationContext();
	   String text = favatarID+"";
	   int duration = Toast.LENGTH_SHORT;

	   Toast toast = Toast.makeText(context, text, duration);
*/   
	   if(favatarID.equals("f_000_000_00_000"))
		   	favatarI.setImageResource(R.drawable.f_000_000_00_000);
	   else if(favatarID.equals("f_001_001_01_001"))
		   	favatarI.setImageResource(R.drawable.f_001_001_01_001);
	   else if(favatarID.equals("f_002_002_02_002"))
		   	favatarI.setImageResource(R.drawable.f_002_002_02_002);
	   else if(favatarID.equals("f_100_100_00_100"))
		   	favatarI.setImageResource(R.drawable.f_100_100_00_100);
	   else if(favatarID.equals("f_101_101_01_101"))
		   	favatarI.setImageResource(R.drawable.f_101_101_01_101);
	   else if(favatarID.equals("f_102_102_02_102"))
		   	favatarI.setImageResource(R.drawable.f_102_102_02_102);
	   else if(favatarID.equals("m_000_000_00_000"))
		   	favatarI.setImageResource(R.drawable.m_000_000_00_000);
	   else if(favatarID.equals("m_001_001_01_001"))
		   	favatarI.setImageResource(R.drawable.m_001_001_01_001);
	   else if(favatarID.equals("m_002_002_02_002"))
		   	favatarI.setImageResource(R.drawable.m_002_002_02_002);
	   else if(favatarID.equals("m_100_100_00_100"))
		   	favatarI.setImageResource(R.drawable.m_100_100_00_100);
	   else if(favatarID.equals("m_101_101_01_101"))
		   	favatarI.setImageResource(R.drawable.m_101_101_01_101);
	   else if(favatarID.equals("m_102_102_02_102"))
		   	favatarI.setImageResource(R.drawable.m_102_102_02_102);
	   
   }
   
   public void storeInApp(){
	   EditText editText1=(EditText)findViewById(R.id.editText1);
	   EditText editText2=(EditText)findViewById(R.id.editText2);
	   String height=editText1.getText().toString();
	   String weight=editText2.getText().toString();
	   float heightD=Float.parseFloat(height);
	   float weightD=Float.parseFloat(weight);
	   SharedPreferences favatar = getSharedPreferences("favatar_image", 0);
	   SharedPreferences.Editor editor = favatar.edit();
	   if(height.equals("") || weight.equals("")){
		   setContentView(R.layout.infoenter);
	   }
	   editor.putFloat("height", heightD);
	   editor.putFloat("weight", weightD);
	   editor.putString("disease", diseaseName);
   }
 //-----------------------------------------------------------------------------  
 public void image01(View v){
	 favatarID="f_000_000_00_000";
	 selectFavatar();
 }
 public void image02(View v){
	 favatarID="m_000_000_00_000";
	 selectFavatar();
 }
 public void image03(View v){
	 favatarID="f_001_001_01_001";
	 selectFavatar();
 }
 public void image04(View v){
	 favatarID="m_001_001_01_001";
	 selectFavatar();
 }
 public void image05(View v){
	 favatarID="f_002_002_02_002";
	 selectFavatar();
 }
 public void image06(View v){
	 favatarID="m_002_002_02_002";
	 selectFavatar();
 }
 public void image07(View v){
	 favatarID="f_100_100_00_100";
	 selectFavatar();
 }
 public void image08(View v){
	 favatarID="m_100_100_10_100";
	 selectFavatar();
 }
 public void image09(View v){
	 favatarID="f_101_101_01_101";
	 selectFavatar();
 }
 public void image10(View v){
	 favatarID="m_101_101_01_101";
	 selectFavatar();
 }
 public void image11(View v){
	 favatarID="f_102_102_02_102";
	 selectFavatar();
 }
 public void image12(View v){
	 favatarID="m_102_102_02_102";
	 selectFavatar();
 }
//-------------------------Function to open tag cloud (Feed Me button)-----------------------------
 public void openTagCloud(View v) {
 	try {
     	Intent tagCloud = new Intent(this, favatar.tagcloud.TagCloud.class);
     	startActivity(tagCloud);	
     	//this.startActivity(tagCloud);
		} catch (Exception e) {
			//String s=e.getMessage();
		}
 }
//--------------------Dinusha-------------------------
 
 public void contWoLogin(View v){
	 setContentView(R.layout.home);
	 cWindowMode = WindowMode.HomePage;
	 SharedPreferences prefs = getSharedPreferences("favatar_image", 0);
	 SharedPreferences.Editor editor = prefs.edit();
	 ImageView favatarI = (ImageView)findViewById(R.id.fav_image);
	 TextView counter = (TextView)findViewById(R.id.counter);
	 //get the image id of favatar
	 if(favatarID.equals("f_000_000_00_000")) {
	   		favatarI.setImageResource(R.drawable.f_000_000_00_000);
	 		editor.putString("counter", smallString);
	 		editor.commit();
	 }
	 else if(favatarID.equals("f_001_001_01_001")) {
		 favatarI.setImageResource(R.drawable.f_001_001_01_001);
		 counter.setText(mediumString);
		 editor.putString("counter", mediumString);
	 	 editor.commit();
	 }
	 else if(favatarID.equals("f_002_002_02_002")) {
		favatarI.setImageResource(R.drawable.f_002_002_02_002);
		counter.setText(largeString);
		editor.putString("counter", largeString);
 		editor.commit();
	 }
	 else if(favatarID.equals("f_100_100_00_100")) {
		favatarI.setImageResource(R.drawable.f_100_100_00_100);
		editor.putString("counter", smallString);
 		editor.commit();
	 }
	 else if(favatarID.equals("f_101_101_01_101")) {
		favatarI.setImageResource(R.drawable.f_101_101_01_101);
		counter.setText(mediumString);
		editor.putString("counter", mediumString);
 		editor.commit();
	 }
	 else if(favatarID.equals("f_102_102_02_102")) {
		favatarI.setImageResource(R.drawable.f_102_102_02_102);
		counter.setText(largeString);
		editor.putString("counter", largeString);
 		editor.commit();
	 }
	 else if(favatarID.equals("m_000_000_00_000")) {
		favatarI.setImageResource(R.drawable.m_000_000_00_000);
		editor.putString("counter", smallString);
 		editor.commit();
		
	 }
	 else if(favatarID.equals("m_001_001_01_001")) {
		favatarI.setImageResource(R.drawable.m_001_001_01_001);
		counter.setText(mediumString);
		editor.putString("counter", mediumString);
 		editor.commit();
	 }
	 else if(favatarID.equals("m_002_002_02_002")) {
		favatarI.setImageResource(R.drawable.m_002_002_02_002);
		counter.setText(largeString);
		editor.putString("counter", largeString);
 		editor.commit();
	 }
	 else if(favatarID.equals("m_100_100_00_100")) {
		favatarI.setImageResource(R.drawable.m_100_100_00_100);
		editor.putString("counter", smallString);
 		editor.commit();
	 }
	 else if(favatarID.equals("m_101_101_01_101")) {
		favatarI.setImageResource(R.drawable.m_101_101_01_101);
		counter.setText(mediumString);
		editor.putString("counter", mediumString);
 		editor.commit();
	 }
	 else if(favatarID.equals("m_102_102_02_102")) {
		favatarI.setImageResource(R.drawable.m_102_102_02_102);
		counter.setText(largeString);
		editor.putString("counter", largeString);
 		editor.commit();
	 }
	 //storeInApp();  save the initial data
	 
 }
 
 public void viewReg(View v){
	 cWindowMode = WindowMode.Register;
	 setContentView(R.layout.register);
 }
 
 public void backFromReg(View v){
	 cWindowMode = WindowMode.DataEnter;
	 setContentView(R.layout.infoenter);
	 
	 ImageView favatarI=(ImageView)findViewById(R.id.imageView15);
 	
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
										this, R.array.planets_array, android.R.layout.simple_spinner_item);
										adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
										spinner.setAdapter(adapter);
										spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
 
		if(favatarID.equals("f_000_000_00_000"))
	   		favatarI.setImageResource(R.drawable.f_000_000_00_000);
		else if(favatarID.equals("f_001_001_01_001"))
	   		favatarI.setImageResource(R.drawable.f_001_001_01_001);
		else if(favatarID.equals("f_002_002_02_002"))
	   		favatarI.setImageResource(R.drawable.f_002_002_02_002);
		else if(favatarID.equals("f_100_100_00_100"))
	   		favatarI.setImageResource(R.drawable.f_100_100_00_100);
		else if(favatarID.equals("f_101_101_01_101"))
	   		favatarI.setImageResource(R.drawable.f_101_101_01_101);
		else if(favatarID.equals("f_102_102_02_102"))
	   		favatarI.setImageResource(R.drawable.f_102_102_02_102);
		else if(favatarID.equals("m_000_000_00_000"))
	   		favatarI.setImageResource(R.drawable.m_000_000_00_000);
		else if(favatarID.equals("m_001_001_01_001"))
	   		favatarI.setImageResource(R.drawable.m_001_001_01_001);
		else if(favatarID.equals("m_002_002_02_002"))
	   		favatarI.setImageResource(R.drawable.m_002_002_02_002);
		else if(favatarID.equals("m_100_100_00_100"))
	   		favatarI.setImageResource(R.drawable.m_100_100_00_100);
		else if(favatarID.equals("m_101_101_01_101"))
	   		favatarI.setImageResource(R.drawable.m_101_101_01_101);
		else if(favatarID.equals("m_102_102_02_102"))
	   		favatarI.setImageResource(R.drawable.m_102_102_02_102);
 }
 
 
 
 //---------------------------------------------------Dinusha
 


 
 public void viewProfile()
 {
 	// display profile info
 }
  
 public void veiwInfo()
 {
 	// display info page
 }
 //------------------------------------------------------------------------------------



 
 //---------------------------------------------------------------------------------------
 public void load_blue_male(View v) {
 	ImageView favatarView = (ImageView)common.fma.findViewById(R.id.fav_image);
 	favatarView.setImageResource(R.drawable.m_100_100_00_100);
 }
 public void load_blue_female(View v) {
 	ImageView favatarView = (ImageView)common.fma.findViewById(R.id.fav_image);
 	favatarView.setImageResource(R.drawable.f_101_101_01_101);
 }
 // function for friend's images in the front
 //---------------------------------------------------------------------------------------------
 // Conversasion page codes



 public void loadProfile_m_green_med(View v){
	 if (cWindowMode != WindowMode.Conversasion){
		 setContentView(R.layout.conversation);
		 cWindowMode = WindowMode.Conversasion;
	 }
 	//ImageView favatarView = (ImageView)common.fma.findViewById(R.id.friendsimage);
 	//favatarView.setImageResource(R.drawable.m_001_001_01_001);
 	SetImage(R.id.linearLayout1, R.drawable.m_001_001_01_001, R.id.friendsimage);
 	SetImage(R.id.linearLayout1, GetImageID(favatarID), R.id.convermyimage);
 	//LoadComments("1", "2");
 	LoadComments("Dinusha : Hey, Malin whts up??\nMe : I had the worst lunch ever...\nDinusha : Ho..Wht happened??\nMe : having a stomatchache\nDinusha : Ha..Ha.. told u to not to go there\nMe : :) \nDinusha : Try good meal for dinner, :) it'll fix u up.");
 }

 public void loadProfile_m_green_sma(View v){
	 if (cWindowMode != WindowMode.Conversasion){
		 setContentView(R.layout.conversation);
		 cWindowMode = WindowMode.Conversasion;
	 }
 	//ImageView favatarView = (ImageView)common.fma.findViewById(R.id.friendsimage);
 	//favatarView.setImageResource(R.drawable.m_000_000_00_000);
	 	SetImage(R.id.linearLayout1, R.drawable.m_000_000_00_000, R.id.friendsimage);
	 	SetImage(R.id.linearLayout1, GetImageID(favatarID), R.id.convermyimage);
 	LoadComments("Ramindu : Do u have any work on tomorrow night.\nMe : Nothing special. Y?\nRamindu : We are having a party.\nMe : not sure about that..\nRamindu : np");
 }

 public void loadProfile_f_blue_sma(View v){
	 if (cWindowMode != WindowMode.Conversasion){
		 setContentView(R.layout.conversation);
		 cWindowMode = WindowMode.Conversasion;
	 }
 	//ImageView favatarView = (ImageView)common.fma.findViewById(R.id.friendsimage);
 	//favatarView.setImageResource(R.drawable.f_000_000_00_000);
 	SetImage(R.id.linearLayout1, R.drawable.f_000_000_00_000, R.id.friendsimage);
 	SetImage(R.id.linearLayout1, GetImageID(favatarID), R.id.convermyimage);
 	LoadComments("Me : MMmmmmm..Getting fat \nAnusha  : Eat something healthy..\nMe : Sure.");
 }
 
 public void loadProfile_f_pink_med(View v){
	 if (cWindowMode != WindowMode.Conversasion){
		 setContentView(R.layout.conversation);
		 cWindowMode = WindowMode.Conversasion;
	 }
 	//ImageView favatarView = (ImageView)common.fma.findViewById(R.id.friendsimage);
 	//favatarView.setImageResource(R.drawable.f_101_101_01_101);
 	SetImage(R.id.linearLayout1, R.drawable.f_101_101_01_101, R.id.friendsimage);
 	SetImage(R.id.linearLayout1, GetImageID(favatarID), R.id.convermyimage);
 	LoadComments("Anupama : Had the best meal...:)\nMe : wht did u have??\nAnupama : Just rice and curry\nMe : Eat healthy..\nAnupama : Yup.");
 }
 
 public void loadProfile_m_blue_sma(View v){
	 if (cWindowMode != WindowMode.Conversasion){
		 setContentView(R.layout.conversation);
		 cWindowMode = WindowMode.Conversasion;
	 }
 	//ImageView favatarView = (ImageView)common.fma.findViewById(R.id.friendsimage);
 	//favatarView.setImageResource(R.drawable.m_100_100_00_100);
 	SetImage(R.id.linearLayout1, R.drawable.m_100_100_00_100, R.id.friendsimage);
 	SetImage(R.id.linearLayout1, GetImageID(favatarID), R.id.convermyimage);
 	LoadComments("Sachira : Hi, malin r you comming to university today.\nMe : not sure going for a wedding..\nSachira : Hmmm.. eat carefully.\nMe : haa.. Sure..:)");
 }
 
 public void loadProfile_f_blue_lar(View v){
	 if (cWindowMode != WindowMode.Conversasion){
		 setContentView(R.layout.conversation);
		 cWindowMode = WindowMode.Conversasion;
	 }
 	//ImageView favatarView = (ImageView)common.fma.findViewById(R.id.friendsimage);
 	//favatarView.setImageResource(R.drawable.f_001_001_01_001);
 	SetImage(R.id.linearLayout1, R.drawable.f_001_001_01_001, R.id.friendsimage);
 	SetImage(R.id.linearLayout1, GetImageID(favatarID), R.id.convermyimage);
 	LoadComments("Kawshi : Hi malli you look really fat..:)\nMe : Yeah I've been eating a lot\nKawshi : Think u should control ur diet\nMe : Hmmmm....:)");
 }

 public void LoadComments(String uid, String cid){

		HashMap<Object, Object> friendInfo = new HashMap<Object, Object>();
		friendInfo.put("userID", uid);
		friendInfo.put("comID", cid);
		
		@SuppressWarnings("unchecked")
		HashMap<Object, Object> data = JT.postRequest("comments", JT.encodeData(friendInfo));

 	TextView conver =(TextView)findViewById(R.id.conversation);
 	String conversastion = "";
 	for (Object key : data.keySet()){
 		
 		String[] parts = data.get(key).toString().split("\\^");
 		if (parts[0] == "Error"){
 			conversastion = "Please check Internet connection of your mobile.";
 			break;
 		}
 		conversastion += parts[0] + " : " + parts[1] + "\n";
 	}
 	
 	conver.setText(conversastion);
 }
 
 public void LoadComments(String conversastion){
	 TextView conver =(TextView)findViewById(R.id.conversation);
	 conver.setText(conversastion);	 	
 }
 
 public void BackHome(View v){

	 setContentView(R.layout.home);
	 cWindowMode = WindowMode.HomePage;
	 ImageView image = (ImageView)common.fma.findViewById(R.id.fav_image);
	 image.setImageResource(GetImageID(favatarID));
	 TextView counter = (TextView)findViewById(R.id.counter);
	 SharedPreferences prefs = getSharedPreferences("favatar_image",0);
	 String num = prefs.getString("counter", null);
	 counter.setText(num);
 }

 public void SetImage(int layot, int timage, int imageview){
	//assuming your layout is in a LinearLayout as its root
	 LinearLayout layout = (LinearLayout)findViewById(layot);

	 ImageView image = (ImageView)common.fma.findViewById(imageview);
	 if (image != null){
		 image.setImageResource(timage);
	
		 int newWidth = getWindowManager().getDefaultDisplay().getWidth() / 2;
		 int orgWidth = image.getDrawable().getIntrinsicWidth();
		 int orgHeight = image.getDrawable().getIntrinsicHeight();
	
		 //double check my math, this should be right, though
		 int newHeight = (int) Math.floor((orgHeight * newWidth) / orgWidth);
	
		 //Use RelativeLayout.LayoutParams if your parent is a RelativeLayout
		 LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		     newWidth, newHeight);
		 //params.topMargin = 150;
		 //image.set
		 image.setLayoutParams(params);
		 image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		 //layout.setLayoutParams(params);
//		 layout.addView(image);
	 }
 }
 
 public int GetImageID(String name){

		if(name.equals("f_000_000_00_000"))
	   		return (R.drawable.f_000_000_00_000);
		else if(name.equals("f_001_001_01_001"))
			return (R.drawable.f_001_001_01_001);
		else if(name.equals("f_002_002_02_002"))
			return (R.drawable.f_002_002_02_002);
		else if(name.equals("f_100_100_00_100"))
			return (R.drawable.f_100_100_00_100);
		else if(name.equals("f_101_101_01_101"))
			return (R.drawable.f_101_101_01_101);
		else if(name.equals("f_102_102_02_102"))
			return (R.drawable.f_102_102_02_102);
		else if(name.equals("m_000_000_00_000"))
			return (R.drawable.m_000_000_00_000);
		else if(name.equals("m_001_001_01_001"))
			return (R.drawable.m_001_001_01_001);
		else if(name.equals("m_002_002_02_002"))
			return (R.drawable.m_002_002_02_002);
		else if(name.equals("m_100_100_00_100"))
			return (R.drawable.m_100_100_00_100);
		else if(name.equals("m_101_101_01_101"))
			return (R.drawable.m_101_101_01_101);
		else if(name.equals("m_102_102_02_102"))
			return (R.drawable.m_102_102_02_102);
		else
			return -1;
 }
 
 //---------------------------------Share Via Email------------------------------------------------
 public void inviteFriends(View v){
     Intent i = new Intent(Intent.ACTION_SEND);
     i.setType("text/plain");
     i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"Your friend's email"});
     i.putExtra(Intent.EXTRA_SUBJECT, "Invitation to Favatars");
     i.putExtra(Intent.EXTRA_TEXT   , "Hi, Im sending this mail to invite you to favatars which is a social network based on nutrition. Please visit http://www.favatars.net");
     try {
         startActivity(Intent.createChooser(i, "Send mail..."));
     } catch (android.content.ActivityNotFoundException ex) {
         Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
     }
 }
 //---------------------------------End Email share function-----------------------------------------
 //-------------------------Registration--------------------------------------------------
 public void registerCheck(View v){
 	EditText editText1 = (EditText)findViewById(R.id.fNameTXT);
 	String fname= editText1.getText().toString();
 	EditText editText2 = (EditText)findViewById(R.id.lNameTXT);
 	String lname = editText2.getText().toString();
 	EditText editText3 = (EditText)findViewById(R.id.uNameTXT);
 	String username= editText3.getText().toString();
 	EditText editText4 = (EditText)findViewById(R.id.eAddTXT);
 	String emailAdd = editText4.getText().toString();
 	EditText editText5 = (EditText)findViewById(R.id.pWordTXT);
 	String pword= editText5.getText().toString();
 	EditText editText6 = (EditText)findViewById(R.id.rpWordTXT);
 	String rpword = editText6.getText().toString();
 	if(fname.trim().equals("")||lname.trim().equals("")||username.trim().equals("")||emailAdd.trim().equals("")||pword.trim().equals("")||rpword.trim().equals("")){
 		//required fields are missing
 		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
         alertbox.setMessage("Please fill in all the fields.");
         alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface arg0, int arg1) {}});
         alertbox.show();
 	}else{
 		if(!pword.equals(rpword)){
 			//passwords do not match
 			AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
             alertbox.setMessage("Passwords does not match.");
             alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface arg0, int arg1) {}});
             alertbox.show();
 		}else{  
 			if(pword.length()<5){
 				 AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
	                alertbox.setMessage("Password must have at least 5 characters.");
	                alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {}});
	                alertbox.show();    				
 			}
 			else{
 			//check for email address validation
 			 String regEx =
 					 "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
 					+"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
 					+"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
 					+"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
 					+"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
 					+"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
 			 String strEmailAddress = emailAdd.trim();//email
 			 Matcher matcherObj = Pattern.compile(regEx).matcher(strEmailAddress);
 			 if(matcherObj.matches()){
 			 //The input email address is valid
 		     //insert to db
 				 if(isUsername(username)==true){
 					 
 				 
 					 String action="register";
     				 HashMap userReg=new HashMap();
     				 userReg.put("fname", fname);
     				 userReg.put("lname", lname);
     				 userReg.put("username", username);
     				 userReg.put("email", emailAdd);
     				 userReg.put("password", pword);
     				 HashMap result=JT.postRequest(action, JT.encodeData(userReg));
     				   if(result.get("message").toString().equals("UserInserted")){
     					    AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
         	                alertbox.setMessage("Your account has been created.");
         	                alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
         	                public void onClick(DialogInterface arg0, int arg1) {}});
         	                alertbox.show();
         	                cWindowMode=WindowMode.HomePage;
         	                setContentView(R.layout.home);
     				   }
     				   else{
     					   AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        	                   alertbox.setMessage("Sorry. There was an error in the registration, please try again");
        	                   alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
        	                   public void onClick(DialogInterface arg0, int arg1) {}});
        	                   alertbox.show();
     				   }
 				 }
 				 else{
 				    AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
  	                alertbox.setMessage("Username already Exists. Please try another username");
  	                alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
  	                public void onClick(DialogInterface arg0, int arg1) {}});
  	                alertbox.show();
 				 }
 				 
 				 
 			 }else{
 				 AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
 	                alertbox.setMessage("Invalid email address.");
 	                alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
 	                public void onClick(DialogInterface arg0, int arg1) {}});
 	                alertbox.show();
 			 }
 			}
 		}   		
 	}
 }
 
 public boolean isUsername(String uname){
 	String action="checkusername";
 	HashMap hm = new HashMap();
 	hm.put("username", uname);
 	HashMap result=JT.postRequest(action, JT.encodeData(hm));
 	if(result.get("message").toString().equals("True")){
 		return true;
 	}
 	else{
 		return false;
 	}
 }
 //-----------------------------------end registration-------------------------------------------------
}	  