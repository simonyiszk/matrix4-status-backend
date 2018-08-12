package hu.bme.mit.modes3.iot.demo;

/*
 * Ez az objektum felel a parancsok továbbításáért
 * A parancsok kiadása úgy zajlik, hogy miután TCP-vel kapcsolódik az aktuátor (kliens)
 * utána a mester elküld egy json-t ami tartalmazza kulcs-érték párokként, hogy kinek mit kell csinálni
 */


import java.net.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.kura.cloud.CloudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
//Ha ezeket bennthagyod akkor egyáltalán nem indul el a bundle a Kura-n
/*
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
*/
public class TCPCommandManager extends Thread /*implements MqttCallback*/{
	

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	
	/*
	 * TCP variables
	 */

    public  int serverPort=5503;
    public  int masterPort=5505;    //ez nem fontos
    protected ServerSocket serverSocket;
    protected ServerSocket masterSocket;
    //enumokban tárolom, hogy az egyes aktuátoroknak milyen állapotban kell lenniük
    LampState lampState=LampState.STOP;// a vasúti lámpa alapértelmezettként piros
    LCDState lcdState=LCDState.OKAY;
    LightState lightState=LightState.OFF;//a közvilágítás alapértelmezettként ki van kapcsolva
    //ScheduledExecutorService m_worker;
    
    /*
     * MQTT variables
     * most feleslegesek
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
    
    public TCPCommandManager(/*ScheduledExecutorService worker*/) {
    	
    	/*m_worker=worker;*/
    	//szerver inicializálása
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
    		//masterSocket=new ServerSocket(masterPort);
    		logger.info("Server socket is created. :");
    		logger.info("Port is:"+Integer.toString(serverSocket.getLocalPort()));
    	}catch(IOException e) {
    		notifylog();
    		logger.info(e.toString());
    	}
    	
    	
    }
    
   //port beállítása és szerver újrainicializálása
    public void setPort(int port) {
    	logger.info("set server port...");
    	serverPort=port;
    	initServer();
    	logger.info("set server port...Done");
    }
    
    
    public void run() {

        //Thread.currentThread().setName(getClass().getSimpleName());
    	//figyelik, hogy jön e új kliens és hozzákapcsol egy klienskezelõt ami összzeállítja neki a parancs json-t és elküldi neki	
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
				//a gateway azonosítója a kliensnek
				commands.addProperty("GatewayID", "5521");
				
				//lámpa állapotától függõen írja be a parancsot
				switch(lampState) {
				case START:
					commands.addProperty("LampState", "START");
				break;
				case STOP:
					commands.addProperty("LampState", "STOP");
				break;
				case WARNING:
					commands.addProperty("LampState", "WARNING");
				break;
				default:
					commands.addProperty("LampState", "STOP");
				break;
				}
				
				switch(lcdState) {
				case COLD:
					commands.addProperty("LCDState", "COLD");
				break;
				case HOT:
					commands.addProperty("LCDState", "HOT");
				break;
				case OKAY:
					commands.addProperty("LCDState", "OKAY");
				break;
				default:
					commands.addProperty("LCDState", "OKAY");
				break;
				
				}
				
				switch(lightState) {
				case ON:
					commands.addProperty("LightState", "ON");
				break;
				case OFF:
					commands.addProperty("LightState", "OFF");
				break;
		
				default:
					commands.addProperty("LightState", "OFF");
				break;
				
				}
				
				//elküdi a kliensnek az összeállított parancsot a kliensnek és zárja a kapcsolatot
				os.write(commands.toString().getBytes());
				os.write('$');
				
				m_connection.close();
				
			}catch(IOException e) {
				logger.info(e.toString());
			}
		}
    }
		//most még nincs használva
		private class MasterHandler extends Thread{
			Socket m_connection;
			StringBuffer inBuffer=new StringBuffer("");

			public static final char startSign='{';
			public static final char stopSign='}';
			int jsonLimit=1024;
			
			
			public MasterHandler(Socket connection) {
				
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

						Gson gson=new Gson();
						
						Command command=gson.fromJson(inBuffer.toString(), Command.class);

						if(command.Command.compareTo("ON")==0) {
							lightState=LightState.ON;
						}
						
						if(command.Command.compareTo("OFF")==0) {
							lightState=LightState.OFF;
						}
						
						
					}
				}catch(IOException e) {
					logger.info(e.toString());
				}
			}
		
		}
	





}
