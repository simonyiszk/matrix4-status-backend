/*******************************************************************************
 * Copyright (c) 2011, 2016 Eurotech and/or its affiliates
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech
 *******************************************************************************/
package org.eclipse.kura.demo.heater;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.kura.cloud.CloudClient;
import org.eclipse.kura.cloud.CloudClientListener;
import org.eclipse.kura.cloud.CloudService;
import org.eclipse.kura.configuration.ConfigurableComponent;
import org.eclipse.kura.message.KuraPayload;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.json.JSONObject;
//import org.json.simple.JSONObject;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.ComponentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Heater implements ConfigurableComponent, CloudClientListener {

    private static final Logger s_logger = LoggerFactory.getLogger(Heater.class);
    private MqttClient client;
    // Cloud Application identifier
    private static final String APP_ID = "heater";

    // Publishing Property Names
    private static final String MODE_PROP_NAME = "mode";
    private static final String MODE_PROP_PROGRAM = "Program";
    private static final String MODE_PROP_MANUAL = "Manual";
    private static final String MODE_PROP_VACATION = "Vacation";

    private static final String PROGRAM_SETPOINT_NAME = "program.setPoint";
    private static final String MANUAL_SETPOINT_NAME = "manual.setPoint";

    private static final String TEMP_INITIAL_PROP_NAME = "temperature.initial";
    private static final String TEMP_INCREMENT_PROP_NAME = "temperature.increment";

    private static final String PUBLISH_RATE_PROP_NAME = "publish.rate";
    private static final String PUBLISH_TOPIC_PROP_NAME = "publish.semanticTopic";
    private static final String PUBLISH_QOS_PROP_NAME = "publish.qos";
    private static final String PUBLISH_RETAIN_PROP_NAME = "publish.retain";
    
    

    // ----------------------------------------------------------------
    //
    // Global variables and objects
    //
    // ----------------------------------------------------------------

    private CloudService m_cloudService;
    private CloudClient m_cloudClient;

    private final ScheduledExecutorService m_worker;
    private ScheduledFuture<?> m_handle;

    private float m_temperature;
    private Map<String, Object> m_properties;
    //private final Random m_random;
   
    
    State serverState=null;
    
    
    ServerSocket sensorHandler=null;
    Socket sensorConnection=null;
    
    SensorServer sensorServer=null;
    
    protected int serverPort=1947;
    
    protected String identificationKey=new String("there is a problem!");
    
    //

    // ----------------------------------------------------------------
    //
    // Dependencies
    //
    // ----------------------------------------------------------------

    public Heater() {
        super();
        //this.m_random = new Random();
        this.m_worker = Executors.newSingleThreadScheduledExecutor();
    }

    public void setCloudService(CloudService cloudService) {
        this.m_cloudService = cloudService;
    }

    public void unsetCloudService(CloudService cloudService) {
        this.m_cloudService = null;
    }
    
    // ----------------------------------------------------------------
    //
    // Custom functions
    //
    // ----------------------------------------------------------------
    
    protected void notifyLog() {
    	for(int i=0;i<10;i++)
    		s_logger.info("*******************notify********************");
    }
    
    
    // ----------------------------------------------------------------
    //
    // Custom classes structures and enums 
    //
    // ----------------------------------------------------------------
    
    public enum State {
        ON, OFF, WARNING, AUTO
    }
    
    protected class SensorServer extends Thread {
    	
    	public SensorServer() {
    		try {
    			sensorHandler=new ServerSocket(1947);
    		}catch(IOException e) {
    			notifyLog();
    			s_logger.info(e.toString());
    		}
    	}

		@Override
		public void run() {
			while(true) {
				try {
					
					Socket sensorlConnection=sensorHandler.accept();
					SensorClient sc=new SensorClient(sensorlConnection);
					sc.start();
				}catch(IOException e) {
	    			notifyLog();
	    			s_logger.info(e.toString());
	    		}
			}
			
		}
    
    }
    
    protected class Data{
    	public String test;
    }
    
    protected class SensorClient extends Thread {
    	
    	Socket localConnection=null;
    	
    	String output="No output";
    	
    	public SensorClient(Socket s) {
    		localConnection=s;
    		notifyLog();
    		s_logger.info("new client is connected...");
    	}
    	
		@Override
		public void run() {

			try {
				//JsonObjectBuilder commandJson;
			    
				s_logger.info("sending content");
				OutputStream os=localConnection.getOutputStream();
				
    			switch(serverState) {
    			case ON:
    				output=new String("N");
    			break;    				
    			case OFF:
    				output=new String("F");
    			break;    				
    			case WARNING:
    				output=new String("W");
    			break;    				
    			case AUTO:
    				output=new String("A");
    			break;   
    			default:
    				output=new String("There is some error, sorry");
    			break;
    			}
    			
    			
				os.write("K".getBytes());
    			s_logger.info("content is: "+output);
				os.write(output.getBytes());
				os.write("C".getBytes());
				os.close();
				s_logger.info("content was sent successfully");
				
			}catch(IOException e) {
    			notifyLog();
    			s_logger.info(e.toString());
    		}catch(Exception e) {
    			notifyLog();
    			s_logger.info(e.toString());
    		}
			
		}
    	
    }
    
    // ----------------------------------------------------------------
    //
    // Activation APIs
    //
    // ----------------------------------------------------------------

    protected void activate(ComponentContext componentContext, Map<String, Object> properties) {
        s_logger.info("Activating Heater...");
        /*try {
        	sensorServer=new SensorServer();
        	sensorServer.start();
        }catch(Exception e) {
			notifyLog();
			s_logger.info(e.toString());
		}*/
        this.m_properties = properties;
        for (String s : properties.keySet()) {
            s_logger.info("Activate - " + s + ": " + properties.get(s));
        }

        // get the mqtt client for this application
        try {

            // Acquire a Cloud Application Client for this Application
            s_logger.info("Getting CloudClient for {}...", APP_ID);
            this.m_cloudClient = this.m_cloudService.newCloudClient(APP_ID);
            this.m_cloudClient.addCloudClientListener(this);

            // Don't subscribe because these are handled by the default
            // subscriptions and we don't want to get messages twice
            doUpdate(false);
        } catch (Exception e) {
            s_logger.error("Error during component activation", e);
            throw new ComponentException(e);
        }
        s_logger.info("Activating Heater... Done.");
        
        TCPDataCollector collector=new TCPDataCollector();
        collector.start();
        
        s_logger.info("data collector is activated");
        notifyLog();
        /*try {
            client=new MqttClient("tcp://localhost:1883", "en a_robot");
            client.connect(new MqttConnectOptions());
            s_logger.info("MQTT connected------------------------------------------------");
            client.publish("test", new MqttMessage("hello from kura".getBytes()));
            s_logger.info("data was sent-------------------------------------------------");
            client.close();
        }catch(Exception e){
        	s_logger.info(e.toString());
        }*/

		JsonObject input=new JsonObject();
		input.addProperty("test", "test");
		
		s_logger.info(input.toString());
		
		Gson gson = new Gson();
		
		Data data=gson.fromJson(input, Data.class);
		
		s_logger.info(data.test);
		

    }

    protected void deactivate(ComponentContext componentContext) {
        s_logger.debug("Deactivating Heater...");

        // shutting down the worker and cleaning up the properties
        this.m_worker.shutdown();

        // Releasing the CloudApplicationClient
        s_logger.info("Releasing CloudApplicationClient for {}...", APP_ID);
        this.m_cloudClient.release();

        s_logger.debug("Deactivating Heater... Done.");
    }

    public void updated(Map<String, Object> properties) {
    	s_logger.info("updateing properties");
        this.m_properties = properties;
        s_logger.info("reading out properties...");
        if(properties != null && !properties.isEmpty()) {
            Iterator<Entry<String, Object>> it = properties.entrySet().iterator();
            while (it.hasNext()) {
                Entry<String, Object> entry = it.next();
                String key=entry.getKey();
                String value=entry.getValue().toString();
                s_logger.info("New property - " + key + " = " +
                entry.getValue() + " of type " + value);

                if(key.compareTo("server.port")==0) {
                	s_logger.info("port is revealed");
                	try {	
                		serverPort=Integer.parseInt(value);
                	}catch(Exception e) {
                		notifyLog();
                		s_logger.info(e.toString());
                	}
                }
                
                if(key.compareTo("server.lamp.state")==0) {
                	s_logger.info("lamp state is revealed");
                	if(value.compareTo("On")==0) {
                		this.serverState= State.ON;
                	}
                	if(value.compareTo("Off")==0) {
                		this.serverState= State.OFF;
                	}
                	if(value.compareTo("Warning")==0) {
                		this.serverState= State.WARNING;
                		
                	}
                	if(value.compareTo("Auto")==0) {
                		this.serverState= State.AUTO;
                		
                	}
                	
                }

                if(key.compareTo("server.key")==0) {
                	s_logger.info("key is revealed");
                }
                
            }
        }
    }

    // ----------------------------------------------------------------
    //
    // Cloud Application Callback Methods
    //
    // ----------------------------------------------------------------

    //@Override
    public void onControlMessageArrived(String deviceId, String appTopic, KuraPayload msg, int qos, boolean retain) {
        // TODO Auto-generated method stub

    }

    //@Override
    public void onMessageArrived(String deviceId, String appTopic, KuraPayload msg, int qos, boolean retain) {
        // TODO Auto-generated method stub

    }

    //@Override
    public void onConnectionLost() {
        // TODO Auto-generated method stub

    }

    //@Override
    public void onConnectionEstablished() {
        // TODO Auto-generated method stub

    }

    //@Override
    public void onMessageConfirmed(int messageId, String appTopic) {
        // TODO Auto-generated method stub

    }

    //@Override
    public void onMessagePublished(int messageId, String appTopic) {
        // TODO Auto-generated method stub

    }

    // ----------------------------------------------------------------
    //
    // Private Methods
    //
    // ----------------------------------------------------------------

    /**
     * Called after a new set of properties has been configured on the service
     */
    private void doUpdate(boolean onUpdate) {
        // cancel a current worker handle if one if active
        if (this.m_handle != null) {
            this.m_handle.cancel(true);
        }

        if (!this.m_properties.containsKey(TEMP_INITIAL_PROP_NAME)
                || !this.m_properties.containsKey(PUBLISH_RATE_PROP_NAME)) {
            s_logger.info(
                    "Update Heater - Ignore as properties do not contain TEMP_INITIAL_PROP_NAME and PUBLISH_RATE_PROP_NAME.");
            return;
        }

        // reset the temperature to the initial value
        if (!onUpdate) {
            this.m_temperature = (Float) this.m_properties.get(TEMP_INITIAL_PROP_NAME);
        }

        // schedule a new worker based on the properties of the service
        int pubrate = (Integer) this.m_properties.get(PUBLISH_RATE_PROP_NAME);
        this.m_handle = this.m_worker.scheduleAtFixedRate(new Runnable() {

            //@Override
            public void run() {
                Thread.currentThread().setName(getClass().getSimpleName());
                doPublish();
            }
        }, 0, pubrate, TimeUnit.SECONDS);
    }

    /**
     * Called at the configured rate to publish the next temperature measurement.
     */
    private void doPublish() {
        /*// fetch the publishing configuration from the publishing properties
        String topic = (String) this.m_properties.get(PUBLISH_TOPIC_PROP_NAME);
        Integer qos = (Integer) this.m_properties.get(PUBLISH_QOS_PROP_NAME);
        Boolean retain = (Boolean) this.m_properties.get(PUBLISH_RETAIN_PROP_NAME);
        String mode = (String) this.m_properties.get(MODE_PROP_NAME);

        // Increment the simulated temperature value
        float setPoint = 0;
        float tempIncr = (Float) this.m_properties.get(TEMP_INCREMENT_PROP_NAME);
        if (MODE_PROP_PROGRAM.equals(mode)) {
            setPoint = (Float) this.m_properties.get(PROGRAM_SETPOINT_NAME);
        } else if (MODE_PROP_MANUAL.equals(mode)) {
            setPoint = (Float) this.m_properties.get(MANUAL_SETPOINT_NAME);
        } else if (MODE_PROP_VACATION.equals(mode)) {
            setPoint = 6.0F;
        }
        if (this.m_temperature + tempIncr < setPoint) {
            this.m_temperature += tempIncr;
        } else {
            this.m_temperature -= 4 * tempIncr;
        }

        // Allocate a new payload
        KuraPayload payload = new KuraPayload();

        // Timestamp the message
        payload.setTimestamp(new Date());

        // Add the temperature as a metric to the payload
        payload.addMetric("temperatureInternal", this.m_temperature);
        payload.addMetric("temperatureExternal", 5.0F);
        payload.addMetric("temperatureExhaust", 30.0F);

        int code = this.m_random.nextInt();
        if (this.m_random.nextInt() % 5 == 0) {
            payload.addMetric("errorCode", code);
        } else {
            payload.addMetric("errorCode", 0);
        }

        // Publish the message
        try {
            this.m_cloudClient.publish(topic, payload, qos, retain);
            s_logger.info("Published to {} message: {}", topic, payload);
        } catch (Exception e) {
            s_logger.error("Cannot publish topic: " + topic, e);
        }*/
    }
}
