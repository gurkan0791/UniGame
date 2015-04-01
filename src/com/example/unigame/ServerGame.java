package com.example.unigame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import android.os.Looper;
import android.util.Log;

public class ServerGame implements Runnable {

	private static Socket client;
	private static ServerSocket SERVER_SOCKET;
	private static final int PORT_N =10456;
	private Scanner input;
	BufferedReader in;
	PrintWriter out;
	private String gelenMesaj;
	static Ball b;
	static BouncingBall bouncingBall;
	
	static int clientScore = 0;
	static int serverScore = 0;
	
	boolean oyunDurum = true;
	public void setClientScore(int clientScore1){
		clientScore = clientScore1;
	}
	public void setServerScore(int serverScore1){
		serverScore = serverScore1;
	}
	Thread t;
	public ServerGame(){
		
		//android:hardwareAccelerated="true"
		try {
			if (SERVER_SOCKET==null) {
				
			
			SERVER_SOCKET =new ServerSocket(PORT_N);
			//SERVER_SOCKET.setReceiveBufferSize(4096);
		//	SERVER_SOCKET.bind(new InetSocketAddress(PORT_N)) ;
		
			Log.e("asd", "Server Çalýþýyor");
			client = SERVER_SOCKET.accept();
			//input = new Scanner(client.getInputStream());
			out = new PrintWriter(client.getOutputStream(),true);
		    in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				System.out.println("Client Baðlandý"+client.getInetAddress()+" "+client.getPort());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b = new Ball(0, 0);
		bouncingBall = new BouncingBall(0, 0, 0);
	}
	
	public void setBallXY(Ball b1){
		b = b1;
	}
	public void setBouncingBallXY(BouncingBall b2){
		bouncingBall = b2;
	}
	
	@Override
	 public synchronized void run() {
		// TODO Auto-generated method stub
		try {
			
			
			
			gelenMesaj = null;
			 while(oyunDurum){
				 //  gelenMesaj = input.nextLine();
				 if (Looper.myLooper() == null) {
				        Looper.prepare();
				    }
				 		//client oyuncu
				 		 gelenMesaj = in.readLine();	
				 		 float x = Float.parseFloat(gelenMesaj.trim());
				 		 gelenMesaj = in.readLine();
				 		 float y = Float.parseFloat(gelenMesaj.trim());
				 		 
				 		 //puan
				 		 out.println(String.valueOf(serverScore));
				 		 out.println(String.valueOf(clientScore));
				 		 
				 		 //server oyuncu
				 		 out.println(String.valueOf(b.getX()));
				 		 out.println(String.valueOf(b.getY()));
				 		 
				 		 //hareketli top
				 		 out.println(String.valueOf(bouncingBall.getBallX()));
				 		 out.println(String.valueOf(bouncingBall.getBallY()));
				 		 out.println(String.valueOf(bouncingBall.getBallSpeedX()));
				 		 //yeni
			             	Log.e("Server Run in içindeyim ",String.valueOf(b.getX()));
							Game g = new Game();
							g.setX(x);
							g.setY(y);
				 
				
			//	Thread.sleep(30);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				oyunDurum = false;
				input.close();
				out.close();
				client.close();
				SERVER_SOCKET.close();
				Log.e("Sistem", "Sonlandýrýldý");
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		

	}

}
