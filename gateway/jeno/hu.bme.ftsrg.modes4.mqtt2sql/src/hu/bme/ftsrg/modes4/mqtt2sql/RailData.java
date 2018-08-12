package hu.bme.ftsrg.modes4.mqtt2sql;

import com.google.gson.JsonObject;

public class RailData extends AccData {
	
	private int Infra1;
	private int Infra2;
	
	public RailData(String sensorID, String type, String microTimeStamp, double accX, double accY, double accZ,int infra1, int infra2) {
		super( sensorID,  type,  microTimeStamp,  accX,  accY,  accZ);
		Infra1=infra1;
		Infra2=infra2;
	}
	
	
	public RailData(JsonObject json) {
		super(json);
		Infra1= Integer.valueOf(json.get("Infra1").toString().substring(1,json.get("Infra1").toString().length()-1 ));
		Infra2= Integer.valueOf(json.get("Infra2").toString().substring(1,json.get("Infra2").toString().length()-1 ));
	}
	

    @Override

    public String toClassID() {
        return "rail";
    }
    
    public int getInfra1() {
    	return Infra1;
    }
    
    public void setInfra1(int infra1) {
    	Infra1=infra1;
    }
    
    public int getInfra2() {
    	return Infra2;
    }
    
    public void setInfra2(int infra2) {
    	Infra1=infra2;
    }
    
    


}
