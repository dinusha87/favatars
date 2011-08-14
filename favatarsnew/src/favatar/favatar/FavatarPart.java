package favatar.favatar;
/**
 * @deprecated 
 */
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;


public class FavatarPart {
	public int id;
	public String name;
	public ImageView view;
	public final String IMAGEURL = "http://rdeshapriya.com/favatar/";
	
	public FavatarPart(ImageView view) {
		//this.id = id;
		this.view = view;
	}
	public void change(int resid) {
		this.view.setImageResource(resid);
		/*downloadFile(IMAGEURL+imagename+".png");
		Log.i("Downloaded URL ",IMAGEURL+imagename+".png");*/
	}
	void downloadFile(String fileUrl){
		URL myFileUrl =null;
		Bitmap bmImg;
		try {
			myFileUrl= new URL(fileUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();

			bmImg = BitmapFactory.decodeStream(is);
			this.view.setImageBitmap(bmImg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
