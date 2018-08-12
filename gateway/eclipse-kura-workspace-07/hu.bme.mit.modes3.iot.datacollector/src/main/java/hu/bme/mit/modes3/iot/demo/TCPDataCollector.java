package hu.bme.mit.modes3.iot.demo;


import java.net.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.kura.KuraException;
import org.eclipse.kura.cloud.CloudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import java.io.*;


public class TCPDataCollector extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    public  int serverPort=5502;
    protected ServerSocket serverSocket;
    private static final int jsonLimit=1024;
    //private ScheduledExecutorService m_worker;
    
    
    private DataPublisher dataPublisher;
    private ErrorPublisher errorPublisher;
    
    private void notifylog() {
    	logger.info("******************notify******************");
    	logger.info("******************notify******************");
    	logger.info("******************notify******************");
    	logger.info("******************notify******************");
    	logger.info("******************notify******************");
    	logger.info("******************notify******************");
    }
    
    
    
    public TCPDataCollector(CloudService cloudService, ScheduledExecutorService worker) {
    	//m_worker=worker;
    	try {
    		logger.info("creating Publishers...");
    		dataPublisher=new DataPublisher(cloudService);
    		errorPublisher=new ErrorPublisher(cloudService);
    		logger.info("creating Publishers...Done");
    	}catch(Exception e) {
    		notifylog();
    		logger.info(e.toString());
    	}
    	
    	
    	logger.info("data collector server...");
    	initServer();
    	logger.info("data collector server...Done");
    	

    	
    }
    
    
    private void initServer() {

    	logger.info("initalising TCP Data Collector...");
    	try {
    		serverSocket=new ServerSocket(serverPort);
    		logger.info("Server socket is created. :");
    		logger.info("Port is:"+Integer.toString(serverSocket.getLocalPort()));
    	}catch(IOException e) {
    		notifylog();
    		logger.info(e.toString());
    	}
    	
    	
    }
    
    public void setPort(int port) {
    	logger.info("set server port...");
    	serverPort=port;
    	initServer();
    	logger.info("set server port...Done");
    }
    
    
    public void run() {
        //Thread.currentThread().setName(getClass().getSimpleName());
    		
    	while(true) {
    		try {
    			Socket connection=serverSocket.accept();
    			ClientHandler client=new ClientHandler(connection);
    			//m_worker.schedule(client, 0, TimeUnit.MILLISECONDS);
    			client.start();
    		}catch(IOException e) {
    			notifylog();
    			logger.info(e.toString());
    		}
    	}
    	
    }
    
    
    
    
    
	private class ClientHandler extends Thread{
		Socket m_connection;
		StringBuffer inBuffer=new StringBuffer("");

		public static final char startSign='{';
		public static final char stopSign='}';
		
		
		public ClientHandler(Socket connection) {
			
			logger.info("new client has connected...");
			logger.info(connection.getInetAddress().toString());
			
			m_connection=connection;
			
		}
		
		
		public void run() {
            Thread.currentThread().setName(getClass().getSimpleName());
			try {
				//OutputStream os=m_connection.getOutputStream();
				//m_connection.close();
				InputStream is=m_connection.getInputStream();
				int counter=0;
				while(true) {
					while((char)(is.read())!=startSign);
					logger.info("incoming json is caught-----------------------------");
					inBuffer.append('{');
					char in;
					do {
						in=(char) is.read();
						inBuffer.append(in);
					}while(in!=stopSign && counter<jsonLimit);
					logger.info("reading incoming json is successful-----------------");
					logger.info(inBuffer.toString());
					
					m_connection.close();
					
					publishData(inBuffer.toString());
					
				}
			}catch(IOException e) {
				logger.info(e.toString());
			}
		}
		
		
		private void publishData(String inData) {
			Gson gson=new Gson();
			
			try {
				
				Data indat=gson.fromJson(inData, Data.class);
				
				if(indat.Type.compareTo("WatchmanData")==0) {
					logger.info("type has recognised");
					WatchmanData data=gson.fromJson(inData, WatchmanData.class);
					dataPublisher.publishData(data);
					errorPublisher.checkData(data);
				}
				
				if(indat.Type.compareTo("SolarData")==0) {
					logger.info("type has recognised");
					SolarData data=gson.fromJson(inData, SolarData.class);
					dataPublisher.publishData(data);
					errorPublisher.checkData(data);
				}

				if(indat.Type.compareTo("AccData")==0) {
					logger.info("type has recognised");
					AccData data=gson.fromJson(inData,AccData.class);
					dataPublisher.publishData(data);
					errorPublisher.checkData(data);
				}
				
				if(indat.Type.compareTo("CameraData")==0) {
					logger.info("type has recognised");
					CameraData data=gson.fromJson(inData,CameraData.class);
					dataPublisher.publishData(data);
					errorPublisher.checkData(data);
				}
				
				if(indat.Type.compareTo("DHTData")==0) {
					logger.info("type has recognised");
					DHTData data=gson.fromJson(inData,DHTData.class);
					dataPublisher.publishData(data);
					errorPublisher.checkData(data);
				}
				
			} catch (Exception e) {
				logger.info(e.toString());
				//BufferedWriter file;
				/*try {
					file = new BufferedWriter(new FileWriter("/data/data.txt"));
					file.write(inData);
					file.newLine();
				} catch (IOException e1) {
					logger.info(e.toString());
				}*/
			}
			
		}

		/*public void onConnection() {
			try {
				BufferedReader bfr=new BufferedReader(new FileReader("/data/data.txt"));
				
				publishData(bfr.readLine());
				
			}catch(Exception e) {
				logger.info(e.toString());
				
			}
		}*/
		
		
	}
	
}
