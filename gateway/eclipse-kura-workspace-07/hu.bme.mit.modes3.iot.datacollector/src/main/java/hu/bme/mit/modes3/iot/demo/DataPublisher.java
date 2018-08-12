package hu.bme.mit.modes3.iot.demo;
 
/*
 * Ez az osztály felel az adatok közvetlen továbbküldéséért a Kapua-nak
 */

import java.util.Date;
 
import org.eclipse.kura.KuraException;
import org.eclipse.kura.cloud.CloudClient;
import org.eclipse.kura.cloud.CloudService;
import org.eclipse.kura.message.KuraPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class DataPublisher {
    
	//publikálás beállításai
    public String topic="SensorData";
    public int qos=2;// quality of service 0-no check; 1-simple check; 2-complex check with 4 handshake
    public boolean retain =false;
    public final String appID="DataCollector";
    
    //adatok publikálásáért felelõs objektumok
    public final String watchmanID="WatchmanCollector";
    public final String solarID="SolarCollector";
    public final String accID="AccCollector";
    public final String trainID="TrainCollector";
    public final String cameraID="CameraCollector";
    public final String onboardID="OnboardCollector";
 
    private CloudClient cloudClient;
    private CloudClient watchmanClient;
    private CloudClient solarClient;
    private CloudClient accClient;
    private CloudClient trainClient;
    private CloudClient cameraClient;
    private CloudClient onboardClient;
    

    
    TCPCommandManager manager;
    
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    
    //konstruktorban megkapja a cloudservice-t ami segítségével a cloudclienteket létre tudjuk hozni
    public DataPublisher(CloudService cloudService) throws KuraException {
    
        logger.info("creating publisher...");
        cloudClient=cloudService.newCloudClient(appID);
        accClient=cloudService.newCloudClient(accID);
        solarClient=cloudService.newCloudClient(solarID);
        watchmanClient=cloudService.newCloudClient(watchmanID);
        trainClient=cloudService.newCloudClient(trainID);
        cameraClient=cloudService.newCloudClient(cameraID);
        onboardClient=cloudService.newCloudClient(onboardID);
        logger.info("creating publisher...Done");
        
        //elindítom a parancs manager-t ugyanis az adatoktól függõen változtatom az állapotokat
		logger.info("creating Manager...");
        manager=new TCPCommandManager();
        manager.start();
		logger.info("creating Manager...Done");
    
    }    
    
    /*
     * Ebben a szekcióban a különbözõ típusú adatok publikációja található
     */
    
public void publishData(SensorData data) throws Exception{
        
        logger.info("--------start to publishing normal data...");
        KuraPayload payload=new KuraPayload();
 
        logger.info("--------add data metric data...");
 
        payload.addMetric("RedLight", (data.RedLight));
        payload.addMetric("GreenLight", (data.GreenLight));
        payload.addMetric("RedLight", (data.BlueLight));
 
        payload.addMetric("AccX", (data.AccX));
        payload.addMetric("AccY", (data.AccY));
        payload.addMetric("AccZ", (data.AccZ));
 
        payload.addMetric("Light", data.Light);
 
        payload.addMetric("Infra1", data.Infra1);
        payload.addMetric("Infra2", data.Infra2);
        
        payload.addMetric("SensorID", data.SensorID);
        payload.addMetric("MicroTimeStamp", data.MicroTimeStamp);
 
        logger.info("--------publishing add date metric data...");
        payload.setTimestamp(new Date());
 
        logger.info("--------publishing data...");
        this.cloudClient.publish(topic,payload, qos, retain);
        logger.info("success!");
        
        
    }

public void publishData(CameraData data) throws Exception{
        
        logger.info("--------start to publishing normal data...");
        KuraPayload payload=new KuraPayload();
 
        logger.info("--------add data metric data...");

        payload.addMetric("LabelText", data.LabelText);
        payload.addMetric("BoxLeft", data.BoxLeft);
        payload.addMetric("BoxRight", data.BoxRight);
        payload.addMetric("BoxTop", data.BoxTop);
        payload.addMetric("BoxBottom", data.BoxBottom);
        payload.addMetric("UpperBound", data.UpperBound);
        payload.addMetric("LowerBound", data.LowerBound);
        
        payload.addMetric("SensorID", data.SensorID);
        payload.addMetric("MicroTimeStamp", data.MicroTimeStamp);
 
        logger.info("--------publishing add date metric data...");
        payload.setTimestamp(new Date());
 
        logger.info("--------publishing data...");
        this.cameraClient.publish(topic,payload, qos, retain);
        logger.info("success!");
        
        
    }
 
    public void publishData(WatchmanData data) throws Exception{
        
        logger.info("--------start to publishing WatchmanData...");
        KuraPayload payload=new KuraPayload();
        
        payload.addMetric("SensorID", data.SensorID);
        payload.addMetric("MicroTimeStamp", data.MicroTimeStamp);
        payload.addMetric("Type","Watchman");
 
        logger.info("--------publishing add date metric data...");
        payload.setTimestamp(new Date());
        
 
        logger.info("--------add data metric data...");
 
        payload.addMetric("RedLight", (data.RedLight));
        payload.addMetric("GreenLight", (data.GreenLight));
        payload.addMetric("RedLight", (data.BlueLight));
 
        payload.addMetric("AccX", (data.AccX));
        payload.addMetric("AccY", (data.AccY));
        payload.addMetric("AccZ", (data.AccZ));
 
        payload.addMetric("Light", data.Light);
 
        payload.addMetric("Infra1", data.Infra1);
        payload.addMetric("Infra2", data.Infra2);
 
        logger.info("--------publishing data...");
        //this.cloudClient.publish(topic,payload, qos, retain);
        this.watchmanClient.publish(topic,payload, qos, retain);
        logger.info("success!");
        
        //publishing into traincollector topic
        
        logger.info("train is coaught");
        KuraPayload trainpl=new KuraPayload();
        trainpl.addMetric("SensorID", data.SensorID);
        trainpl.addMetric("Type", "WatchmanSensor");
        trainpl.setTimestamp(new Date());
        trainpl.addMetric("Presense", data.Infra2);
        logger.info("--------publishing train...");
        //this.cloudClient.publish(topic,payload, qos, retain);
        this.trainClient.publish(topic,trainpl, qos, retain);
        logger.info("success!");
        
        //itt megvizsgálom a közelségérzékelõ értékét és ez alapján állítom be a commandmanager-ben az aktuátor állapotát
        if(data.Infra2==0) {
        	manager.setLampState(LampState.STOP);
        }else {
        	manager.setLampState(LampState.START );
        }
        
        
    }
    
    public void publishData(SolarData data) throws Exception{
        
        logger.info("--------start to publishing SolarData...");
        KuraPayload payload=new KuraPayload();
        
        payload.addMetric("SensorID", data.SensorID);
        payload.addMetric("MicroTimeStamp", data.MicroTimeStamp);
        
        payload.addMetric("Volt", data.Volt);
 
        logger.info("--------publishing add date metric data...");
        payload.setTimestamp(new Date());
 
        logger.info("--------publishing data...");
        //this.cloudClient.publish(topic,payload, qos, retain);
        this.solarClient.publish(topic,payload, qos, retain);
        logger.info("success!");
        
    }

    public void publishData(AccData data) throws Exception{
        
        logger.info("--------start to publishing AccData...");
        KuraPayload payload=new KuraPayload();
        
        payload.addMetric("SensorID", data.SensorID);
        payload.addMetric("MicroTimeStamp", data.MicroTimeStamp);
 
        payload.addMetric("AccX", data.AccX);
        payload.addMetric("AccY", data.AccY);
        payload.addMetric("AccZ", data.AccZ);
 
        logger.info("--------publishing add date metric data...");
        payload.setTimestamp(new Date());
 
        logger.info("--------publishing data...");
 
        this.accClient.publish(topic,payload, qos, retain);
        //this.cloudClient.publish(topic,payload, qos, retain);
        logger.info("success!");
        
    }
    
    public void publishData(OnboardData data) throws Exception{
        
        logger.info("--------start to publishing AccData...");
        KuraPayload payload=new KuraPayload();
        
        payload.addMetric("SensorID", data.SensorID);
        payload.addMetric("MicroTimeStamp", data.MicroTimeStamp);
 
        payload.addMetric("AccX", data.AccX);
        payload.addMetric("AccY", data.AccY);
        payload.addMetric("AccZ", data.AccZ);
 
        payload.addMetric("GyrX", data.GyrX);
        payload.addMetric("GyrY", data.GyrY);
        payload.addMetric("GyrZ", data.GyrZ);
 
        payload.addMetric("MagX", data.MagX);
        payload.addMetric("MagY", data.MagY);
        payload.addMetric("MagZ", data.MagZ);
 
        logger.info("--------publishing add date metric data...");
        payload.setTimestamp(new Date());
 
        logger.info("--------publishing data...");
 
        this.accClient.publish(topic,payload, qos, retain);
        //this.cloudClient.publish(topic,payload, qos, retain);
        logger.info("success!");
        
    }

    public void publishData(DHTData data) throws Exception{
        
        logger.info("--------start to publishing DHTData...");
        KuraPayload payload=new KuraPayload();
        
        payload.addMetric("SensorID", data.SensorID);
        payload.addMetric("MicroTimeStamp", data.MicroTimeStamp);
 
        payload.addMetric("Humidity", data.Humidity);
        payload.addMetric("Temperature", data.Temperature);
 
        logger.info("--------publishing add date metric data...");
        payload.setTimestamp(new Date());
 
        logger.info("--------publishing data...");
        this.cloudClient.publish(topic,payload, qos, retain);
        logger.info("success!");
        
    }
    
    public void open() throws Exception {
    }
    
    public void close() {
        this.cloudClient.release();
    }
 
}


