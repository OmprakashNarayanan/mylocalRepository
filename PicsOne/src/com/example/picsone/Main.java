package com.example.picsone;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {

	protected static final int LOAD_RESULT_IMAGE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button add=(Button)findViewById(R.id.add);
		add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent("com.example.picsone.MENU");
				startActivity(i);
			}
		});
		Button gallery=(Button)findViewById(R.id.gallery);
		gallery.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);	
				startActivityForResult(i,LOAD_RESULT_IMAGE);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	if(requestCode==LOAD_RESULT_IMAGE&&resultCode==RESULT_OK&&data!=null){
		Intent i=new Intent(this,Menu.class);
		Uri uri=data.getData();
		Bitmap b;
		try {
			b = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
			ByteArrayOutputStream bs=new ByteArrayOutputStream();
			b.compress(Bitmap.CompressFormat.PNG,25, bs);
			i.putExtra("byteArray",bs.toByteArray());
			startActivity(i);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}

	
	

}
