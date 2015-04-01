package com.example.unigame;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {

	Thread serverThread = null;
	Button button1;
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		 StrictMode.setThreadPolicy(policy); 
		init();
		
	}
	private void init() {
		
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("Selam", "Oyuncu Bekleniyor");
			    serverThread = new Thread(new ServerGame());
			    serverThread.start();
				//new ServerClass().execute();
				Log.e("Selam", "Baðlantý Gerçekleþti");
			Intent i = new Intent(v.getContext(), Game.class);
				startActivityForResult(i, 0);
			}
		});
		
	}

	/*
	class ServerClass extends AsyncTask<String, String, String> {

		private  Socket client;
		private  ServerSocket SERVER_SOCKET;
		private static final int PORT_N =9000;
		private Scanner input;
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			try {
				
				SERVER_SOCKET =new ServerSocket(PORT_N);
				
				Log.e("asd", "Server Çalýþýyor");
				client = SERVER_SOCKET.accept();
				input = new Scanner(client.getInputStream());
				 do{
			
					System.out.println("Client Baðlandý"+client.getInetAddress()+" "+ input.nextLine()+client.getPort());
					try {
						runOnUiThread(new Runnable() {
		                    @Override
		                    public void run() {
		                    	
		                    	int x = Integer.parseInt(input.nextLine().trim());
		                    	System.out.print("Run in içindeyim "+ x);
								Game g = new Game();
								g.setX(x); 
							
		                    }
		                 });
				 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}while (!input.nextLine().trim().equals("son"));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		
	}
	
	**/
	
	@Override
	public void onDestroy(){
		if (serverThread != null) {
			serverThread.interrupt();
		}
		System.exit(0);
	}
}

