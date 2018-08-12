package hu.bme.mit.modes3.iot.gatewaysimualtor;


import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.JsonObject;
/*
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
*/
public class GatewaySimulator extends Thread /*implements MqttCallback*/{
	

    
    //PipedInputStream 
	
	/*
	 * TCP variables
	 */

    public  int serverPort=5503;
    protected ServerSocket serverSocket;
    
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
    	System.out.println("******************notify******************");
    	System.out.println("******************notify******************");
    	System.out.println("******************notify******************");
    	System.out.println("******************notify******************");
    	System.out.println("******************notify******************");
    	System.out.println("******************notify******************");
    }
    
    public GatewaySimulator(/*ScheduledExecutorService worker*/) {
    	
    	/*m_worker=worker;*/
    	System.out.println("creating simulator server...");
    	initServer();
    	System.out.println("creating simulator server...Done");
    	//initMQTT();
    	
    	System.out.println("simulator has successfully initalised :)");
    	
    }
    
    
    private void initServer() {

    	System.out.println("initalising server...");
    	try {
    		serverSocket=new ServerSocket(serverPort);
    		System.out.println("simulator socket is created. :");
    		System.out.println("Port is:"+Integer.toString(serverSocket.getLocalPort()));
    	}catch(IOException e) {
    		notifylog();
    		System.out.println(e.toString());
    	}
    	
    	
    }
    
   
    public void setPort(int port) {
    	System.out.println("set server port...");
    	serverPort=port;
    	initServer();
    	System.out.println("set server port...Done");
    }
    
    
    public void run() {

    		
    	while(true) {
    		try {
    			Socket connection=serverSocket.accept();
    			//Socket masterConnection=masterSocket.accept();
    			ClientHandler client=new ClientHandler(connection);
    			client.start();
    		}catch(IOException e) {
    			notifylog();
    			System.out.println(e.toString());
    		}
    	}
    	
    }
    
    
    
    
    
    private class ClientHandler extends Thread{
		Socket m_connection;

		
		
		public ClientHandler(Socket connection) {
			
			System.out.println("new client has connected...");
			System.out.println(connection.getInetAddress().toString());
			
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
				System.out.println(e.toString());
			}
		}
    }
		





}
