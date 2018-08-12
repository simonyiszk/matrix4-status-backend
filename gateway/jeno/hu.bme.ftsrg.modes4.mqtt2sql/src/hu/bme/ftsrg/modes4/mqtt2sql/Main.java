package hu.bme.ftsrg.modes4.mqtt2sql;

import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {
	static AccData2SQL mukodj1=null;
	static DHTData2SQL mukodj2=null;
	static RailData2SQL mukodj3=null;

	public static void main(String[] args) {
		mukodj1=new AccData2SQL();
		mukodj2=new DHTData2SQL();
		mukodj3=new RailData2SQL();

		try {
			System.out.println("initalising database connection...");
			mukodj1.connect2Database();
			mukodj2.connect2Database();
			mukodj3.connect2Database();
			System.out.println("database connection established :)");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("initalising broker connection...");
			mukodj1.connect2Broker();
			mukodj2.connect2Broker();
			mukodj3.connect2Broker();
			System.out.println("broker connection established :)");
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
