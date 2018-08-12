package hu.bme.ftsrg.modes4.mqtt2sql;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

import com.google.gson.Gson;

public class RailData2SQL extends Data2SQL{
	
	private final static String topic="sensor/rail";
	private final static String table="raildata";

	public RailData2SQL() {
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
		RailData data=gson.fromJson(json, RailData.class);
		String sql = "insert into "+table+"(Infra1,Infra2,AccX,AccY,AccZ,MicroTimestamp,SensorName) values("+Integer.toString(data.getInfra1())+","+Integer.toString(data.getInfra2())+","+Double.toString(data.getAccX())+","+Double.toString(data.getAccY())+","+Double.toString(data.getAccZ())+","+(data.getMicroTimeStamp())+",'railtest')";
		return sql;
	}
	

}
