package hu.bme.ftsrg.modes4.mqtt2sql;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

import com.google.gson.Gson;

public class DHTData2SQL extends Data2SQL{
	
	private final static String topic="raw/dht";
	private final static String table="weatherdata";

	public DHTData2SQL() {
		super(topic);
	}

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getSQL(String json) {
		Gson gson=new Gson();
		DHTData data=gson.fromJson(json, DHTData.class);
		String sql = "insert into "+table+"(Temp,Hum,Pressure,MicroTimestamp,SensorName) values("+Double.toString(data.getTemperature())+","+Double.toString(data.getHumidity())+","+Double.toString(Double.NaN)+","+(data.getMicroTimeStamp())+",'train_test_1')";
		return sql;
	}
	

}
