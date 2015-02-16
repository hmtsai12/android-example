package com.example.sockettcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class sockettcp extends Activity {

	private Button mBtnSend;
	private EditText mEditIP;
	private EditText mEditPort;
	private EditText mEditCommand;
	private TextView mTVResult;
	protected Thread mSocketThread;
	
	private final String LOG_TAG = "socket tcp";

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mBtnSend = (Button)findViewById(R.id.btnSend);
		mEditIP = (EditText)findViewById(R.id.editTextIP);
		mEditPort = (EditText)findViewById(R.id.editTextPort);
		mEditCommand = (EditText)findViewById(R.id.editTextCommand);
		mTVResult = (TextView)findViewById(R.id.tvResult);
		
		mBtnSend.setOnClickListener(new OnClickListener(){

			/* (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mTVResult.setText("Start TCP");
				mEditIP.setText("192.168.1.3");
				mEditPort.setText("11111");
				mEditCommand.setText("KVM,k1p1");
				mSocketThread = new Thread(){

					private InetAddress mRemoteAddress;
					private Integer mRemotePort;
					private Socket mTcpSocket;
					private OutputStream mOutputStream;
					private InputStream mInputStream;

					/* (non-Javadoc)
					 * @see java.lang.Thread#run()
					 */
					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						/*judge edit is null*/
						/*if("".equals(mEditIP.getText().toString().trim())){
							Log.w(LOG_TAG, "IP is null");
							return;
						}
						if("".equals(mEditPort.getText().toString().trim())){
							Log.w(LOG_TAG, "Port is null");
							return;
						}
						if("".equals(mEditCommand.getText().toString().trim())){
							Log.w(LOG_TAG, "Port is null");
							return;
						}*/
						
						try {
							mRemoteAddress = InetAddress.getByName(mEditIP.getText().toString());
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						mRemotePort = Integer.valueOf(mEditPort.getText().toString());
						
						try {
							mTcpSocket = new Socket(mRemoteAddress, mRemotePort);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							mOutputStream = mTcpSocket.getOutputStream();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							mInputStream = mTcpSocket.getInputStream();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							mOutputStream.write(mEditCommand.getText().toString().getBytes());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							mOutputStream.flush();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						byte[] mRecvBuf = new byte[1024];
						try {
							Message mMsg = new Message();
							Bundle mData = new Bundle();
							int mRecvBufLen = mInputStream.read(mRecvBuf, 0x00, 1023);
							String mRecvString = new String(mRecvBuf);
							//String mRecvString = mRecvBuf.toString();
							//String mRecvString = new String(mRecvBuf, 0x00, mRecvBuf.length);
							Log.i(LOG_TAG, "resultLen = " + mRecvBufLen);
							mData.putString("Socket", mRecvString);
							mMsg.setData(mData);
							mSocketHandler.sendMessage(mMsg);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    /*BufferedReader in = new BufferedReader(new InputStreamReader(mInputStream));
					    try {
							String mRecvString = in.readLine();
							Log.i(LOG_TAG, "result = " + mRecvString);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}*/
						try {
							mTcpSocket.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				};
				mSocketThread.start();
			}
			
		});
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
			mTVResult.setText(msg.getData().getString("Socket"));
		}
		
	};

}
