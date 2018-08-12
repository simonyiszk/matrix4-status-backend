package hu.bme.ftsrg.modes4.mqtt2sql;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

import com.google.gson.Gson;

public class CameraData2SQL extends Data2SQL{
	
	private final static String topic="raw/cameradata";
	private final static String table="cameradata";

	public CameraData2SQL() {
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
		CameraData data=gson.fromJson(json, CameraData.class);
		String sql = "insert into "+table+"(textlabel,boxtop,boxbottom,boxleft,boxright,upperbound,lowerbound,MicroTimestamp,SensorName) values("+data.getLabelText()+","+Double.toString(data.getBoxTop())+","+Double.toString(data.getBoxBottom())+","+Double.toString(data.getBoxLeft())+","+Double.toString(data.getBoxRight())+","+(data.getMicroTimeStamp())+",'train_test_1')";
		return sql;
	}
	

}
