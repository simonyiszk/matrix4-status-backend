package matrix.command.tester;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Commander {
	
	private byte commandByte;
	private final int sendport=3000;
	private final int recport=3001;

	private DatagramSocket socket=null;
	
	public void closeSocket() {
		socket.close();
	}
	
	public Commander(String tipus) {

		try {
			socket=new DatagramSocket(recport);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(tipus.compareTo("12V-off-left")==0)
			commandByte=0;
		if(tipus.compareTo("12V-off-right")==0)
			commandByte=1;
		if(tipus.compareTo("reset-left-panel")==0)
			commandByte=2;
		if(tipus.compareTo("reset-right-panel")==0)
			commandByte=3;
		if(tipus.compareTo("reboot")==0)
			commandByte=4;
		if(tipus.compareTo("get-status")==0)
			commandByte=5;
		if(tipus.compareTo("use-internal-animation")==0)
			commandByte=10;
		if(tipus.compareTo("use-external-animation")==0)
			commandByte=20;
		if(tipus.compareTo("blank")==0)
			commandByte=30;
		if(tipus.compareTo("delete-anim-network-buffer")==0)
			commandByte=6;
		if(tipus.compareTo("ping")==0)
			commandByte=0x40;
	}
	
	byte[] toBytes() {
		byte[] valasz= {'S','E','M',commandByte};
		return valasz;
	}
	
	@SuppressWarnings("resource")
	byte[] send(String cel) throws Exception{
		byte valasz[]=null;
		DatagramPacket datagram=null;
		byte[] buff=new byte[256];
		
		InetAddress cim;
		//cim=InetAddress.getByName(cel); 
		cim=InetAddress.getByName("localhost"); //debuggolashoz
		datagram=new DatagramPacket(toBytes(),4,cim,sendport);
			
		
		
		socket.send(datagram);
		
		DatagramPacket packet=new DatagramPacket(buff,buff.length);
		socket.setSoTimeout(1000);
		socket.receive(packet);
		valasz=datagram.getData();
		return valasz;
	}

}
