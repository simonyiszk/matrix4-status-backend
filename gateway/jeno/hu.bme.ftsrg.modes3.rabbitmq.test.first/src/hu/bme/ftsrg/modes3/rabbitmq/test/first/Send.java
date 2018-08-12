package hu.bme.ftsrg.modes3.rabbitmq.test.first;
import com.rabbitmq.client.Channel;

import com.rabbitmq.client.Connection;

import com.rabbitmq.client.ConnectionFactory;



public class Send {



  static String QUEUE_NAME = "hello5";



  public static void main(String[] argv) throws Exception {
	 System.out.println("start");
    /*ConnectionFactory factory = new ConnectionFactory();

    factory.setHost("192.168.1.3");
    factory.setUsername("mqtt-test");
    factory.setPassword("mqtt-test");*/
    ConnectionFactory factory = new ConnectionFactory();
	 // "guest"/"guest" by default, limited to localhost connections
	 factory.setUsername("mqtt-test");
	 factory.setPassword("mqtt-test");
	// factory.setVirtualHost("/");
	 factory.setHost("192.168.1.230");
	 factory.setPort(5672);

	 System.out.println("properties has been set");
    Connection connection = factory.newConnection();

    Channel channel = connection.createChannel();

	 System.out.println("init end");



	System.out.println("start");
    channel.queueDeclare(QUEUE_NAME, true, false, false, null);

	System.out.println("start");
    String message = "Hello World2!";
    
    //channel.exchangeDeclare("hello", "direct", true);
    //channel.queueBind(QUEUE_NAME, "hello", null);
    
    channel.basicPublish("hello2", QUEUE_NAME, null, message.getBytes("UTF-8"));

	System.out.println("sent");
    System.out.println(" [x] Sent '" + message + "'");



    channel.close();

    connection.close();

  }

}