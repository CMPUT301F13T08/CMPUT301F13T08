/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  �  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
Free Software Foundation, Inc., Marky Mark  License GPLv3+: GNU
GPL version 3 or later <http://gnu.org/licenses/gpl.html>.
This program is free software: you can redistribute it and/or modify it under the terms of 
the GNU General Public License as published by the Free Software Foundation, either 
version 3 of the License, or (at your option) any later version. This program is distributed 
in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public 
License for more details. You should have received a copy of the GNU General Public License 
along with this program.  If not, see <http://www.gnu.org/licenses/>.
              
3rd Party Libraries
=============
Retrieved Oct. 27, 2013 - https://github.com/rayzhangcl/ESDemo
-This demo was used to help with JSON and ESHelper

Retrieved Oct. 29, 2013  - http://hc.apache.org/downloads.cgi
-This is for the fluent library which is licenced under apache V2

Retrieved Oct. 29, 2013 
- https://code.google.com/p/google-gson/downloads/detail?name=google-gson-2.2.4-release.zip&can=2&q=
-This is for JSON which is licenced under apache V2
 */

package com.team08storyapp;

import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;


public class PhotoController extends Activity {

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int SELECT_PHOTO = 200;
	Uri imageFileUri;
	
	
	public void takePhoto(){
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	public void chooseFromGallery() {
	    Intent pickIntent = new Intent();
	    pickIntent.setType("image/*");
	    pickIntent.setAction(Intent.ACTION_GET_CONTENT);
	    startActivityForResult(
			Intent.createChooser(pickIntent, "Select Picture"), 1);
	}
	
	
	/*
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
	    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	            // Image captured and saved to fileUri specified in the Intent
	            Toast.makeText(this, "Image saved to:\n" +
	                     data.getData(), Toast.LENGTH_LONG).show();
	        } else if (resultCode == RESULT_CANCELED) {
	            // User cancelled the image capture
	        } else {
	            // Image capture failed, advise user
	        }
	    }
	    if (requestCode == SELECT_PHOTO){
	            Uri selectedImage = data.getData();
	            String[] filePathColumn = {MediaStore.Images.Media.DATA};

	            Cursor cursor = getContentResolver().query(
	                               selectedImage, filePathColumn, null, null, null);
	            cursor.moveToFirst();

	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            String filePath = cursor.getString(columnIndex);
	            cursor.close();


	            @SuppressWarnings("unused")
				Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
	            }
	        
	    
	}
	
	/*public void takePhoto(){
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{  
	    //Check that request code matches ours:
	    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) 
	    {
	        //Get our saved file into a bitmap object:
	       File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
	       Bitmap bitmap = decodeSampledBitmapFromFile(file.getAbsolutePath(), 1000, 700);
	    }
	}
	
	public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) 
	{    
	    //First decodes with inJustDecodeBounds to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(path, options);
	 
	    //Calculate inSampleSize, Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    options.inPreferredConfig = Bitmap.Config.RGB_565;
	    int inSampleSize = 1;
	 
	    if (height > reqHeight) 
	    {
	        inSampleSize = Math.round((float)height / (float)reqHeight);
	    }
	    int expectedWidth = width / inSampleSize;
	 
	    if (expectedWidth > reqWidth) 
	    {
	        inSampleSize = Math.round((float)width / (float)reqWidth);
	    }
	 
	    options.inSampleSize = inSampleSize;
	 
	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	 
	    return BitmapFactory.decodeFile(path, options);
	}*/

}

