package com.example.picsone;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

public class Menu extends Activity{

	protected static final int LOAD_IMAGE_RESULT = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		ImageView image=(ImageView)findViewById(R.id.imageglry);
		Button addmore;
		addmore=(Button)findViewById(R.id.add2);
		addmore.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent,LOAD_IMAGE_RESULT);
			}
		});
	if(getIntent().hasExtra("byteArray")){
		Bitmap b=BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);
		Bitmap resized=ThumbnailUtils.extractThumbnail(b,200,200);
		image.setImageBitmap(resized);
	}
		
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==LOAD_IMAGE_RESULT&&resultCode==RESULT_OK&&data!=null){
		Uri uri=data.getData();
		InputStream image_stream;
		try {
			image_stream=getContentResolver().openInputStream(uri);
			HorizontalScrollView scroll;
			LinearLayout lin_layout;
			lin_layout=new LinearLayout(this);
			scroll=new HorizontalScrollView(this);
			Bitmap b=BitmapFactory.decodeStream(image_stream);
			Bitmap size=ThumbnailUtils.extractThumbnail(b, 200,200);
			ImageView image2;
			image2=new ImageView(this);
			image2.setImageBitmap(size);
			lin_layout.addView(image2,new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			scroll.addView(lin_layout,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			addContentView(scroll,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}
	
}
