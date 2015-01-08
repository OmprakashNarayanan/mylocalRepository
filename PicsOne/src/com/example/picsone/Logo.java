package com.example.picsone;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class Logo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		Thread logoTimer=new Thread(){
			public void run(){
				try{
					sleep(1000);
					Intent mainactivity=new Intent("com.example.picsone.MAIN");
					startActivity(mainactivity);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					finish();
				}
			}
		};
		logoTimer.start();
	}

}
