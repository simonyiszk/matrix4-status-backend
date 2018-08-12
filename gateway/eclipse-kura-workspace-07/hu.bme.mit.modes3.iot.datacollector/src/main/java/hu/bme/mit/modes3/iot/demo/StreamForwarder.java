package hu.bme.mit.modes3.iot.demo;


import java.net.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.kura.cloud.CloudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
/*
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
*/
public class StreamForwarder extends Thread /*implements MqttCallback*/{
	

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	
	/*
	 * TCP variables
	 */

    public  int serverPort=5507;
    protected ServerSocket serverSocket;
    protected ServerSocket masterSocket;
    LampState lampState=LampState.STOP;
    LCDState lcdState=LCDState.OKAY;
    LightState lightState=LightState.OFF;
    //ScheduledExecutorService m_worker;
    
    /*
     * MQTT variables
     */
    

    private final String topic        = "LampCommand";
    private final String content      = "";
    private final int qos             = 2;
    private final String broker       = "tcp://localhost:1883";
    private final String clientId     = "CommandManager";
    //MemoryPersistence persistence = new MemoryPersistence();
    
    
    private void notifylog() {
    	logger.info("******************notify******************");
    	logger.info("******************notify******************");
    	logger.info("******************notify******************");
    	logger.info("******************notify******************");
    	logger.info("******************notify******************");
    	logger.info("******************notify******************");
    }
    
    public void setLampState(LampState ls) {
    	this.lampState=ls;
    }
    
    public StreamForwarder(/*ScheduledExecutorService worker*/) {
    	
    	/*m_worker=worker;*/
    	logger.info("creating webcontrol server...");
    	initServer();
    	logger.info("creating webcontrol server...Done");
    	//initMQTT();
    	
    	logger.info("TCP Command Manager has successfully initalised :)");
    	
    }
    
    
    private void initServer() {

    	logger.info("initalising TCP Data Manager...");
    	try {
    		serverSocket=new ServerSocket(serverPort);
    		logger.info("Forwarder socket is created. :");
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
    			//Socket masterConnection=masterSocket.accept();
    			ClientHandler client=new ClientHandler(connection);
    			//MasterHandler master=new MasterHandler(masterConnection);
    			
    			//m_worker.schedule(client, 1, TimeUnit.MILLISECONDS);
    			client.start();
    			//master.start();
    		}catch(IOException e) {
    			notifylog();
    			logger.info(e.toString());
    		}
    	}
    	
    }
    
    
    
    
    
    private class ClientHandler extends Thread{
		Socket m_connection;

		
		
		public ClientHandler(Socket connection) {
			
			logger.info("new client has connected...");
			logger.info(connection.getInetAddress().toString());
			
			m_connection=connection;
			
		}
		
		
		public void run() {
            //Thread.currentThread().setName(getClass().getSimpleName());
			try {
				OutputStream os=m_connection.getOutputStream();
				
				JsonObject commands=new JsonObject();
				
				commands.addProperty("GatewayID", "5521");
				
				
				os.write(commands.toString().getBytes());
				os.write('$');
				
				m_connection.close();
				
			}catch(IOException e) {
				logger.info(e.toString());
			}
		}
    }
		





}
