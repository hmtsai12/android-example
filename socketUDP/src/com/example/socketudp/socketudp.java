package com.example.socketudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class socketudp extends Activity {

	EditText mEditIP;
	EditText mEditPort;
	Button mBtnSend;
	TextView mUDPReturn;
	Thread mSocketThread = null;
	
	private final String LOG_TAG = "socket udp";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mEditIP = (EditText) findViewById(R.id.editIP);
		mEditPort = (EditText)findViewById(R.id.editPort);
		mBtnSend = (Button)findViewById(R.id.button1);
		mUDPReturn = (TextView)findViewById(R.id.textShow);
		
		mBtnSend.setOnClickListener(new Button.OnClickListener(){

			/* (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mUDPReturn.setText("Text string");
				mSocketThread = new Thread(){

					/* (non-Javadoc)
					 * @see java.lang.Thread#run()
					 */
					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						Log.i(LOG_TAG, "start thread run");
						DatagramSocket mDatagramSocket = null;
						InetAddress mRemoteAddress = null;
						int mRemotePort;
						DatagramPacket mPacket;
						String mBuffer = "KVM,k1p0";
						
						try{
							mDatagramSocket = new DatagramSocket();
						}
						catch(SocketException e1){
							e1.printStackTrace();
						}
						/*judge edit is null*/
						if("".equals(mEditIP.getText().toString().trim())){
							Log.w(LOG_TAG, "IP is null");
							mDatagramSocket.close();
							return;
						}
						try {
							Log.i(LOG_TAG, "IP is " + mEditIP.getText().toString());
							mRemoteAddress = InetAddress.getByName(mEditIP.getText().toString());
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if("".equals(mEditPort.getText().toString().trim())){
							Log.w(LOG_TAG, "Port is null");
							mDatagramSocket.close();
							return;
						}
						
						mRemotePort = Integer.valueOf(mEditPort.getText().toString());
						Log.i(LOG_TAG, "Port is " + mRemotePort);
						
						mPacket = new DatagramPacket(mBuffer.getBytes(), mBuffer.length(),mRemoteAddress ,mRemotePort);
						
						try {
							mDatagramSocket.send(mPacket);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						byte[] mRecvBuf = new byte[1024];
						DatagramPacket mRecvPacket = new DatagramPacket(mRecvBuf, mRecvBuf.length);
						
						try {
							Message mMsg = new Message();
							Bundle mData = new Bundle();
							mDatagramSocket.receive(mRecvPacket);
							String mRecvString = new String(mRecvPacket.getData(), mRecvPacket.getOffset(), mRecvPacket.getLength());
							Log.i(LOG_TAG, "result = " + mRecvString);
							mData.putString("Socket", mRecvString);
							mMsg.setData(mData);
							mSocketHandler.sendMessage(mMsg);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						mDatagramSocket.close();
						
					}
				};
				
				mSocketThread.start();
			}
			
		}
		
		);
	}
	
	@SuppressLint("HandlerLeak")
	private Handler mSocketHandler = new Handler(){

		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Log.i(LOG_TAG, "receive handle");
			mUDPReturn.setText(msg.getData().getString("Socket"));
		}
		
	};

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(mSocketThread != null){
			if(!mSocketThread.isInterrupted()){
				mSocketThread.interrupt();
			}
		}
	}

}
