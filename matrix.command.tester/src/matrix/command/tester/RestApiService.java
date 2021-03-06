package matrix.command.tester;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RestApiService extends Thread {
	
	Socket conn;
	ServerSocket serverSocket;
	public final int port=31920;
	
	public volatile MuebArray muebArray=new MuebArray();
	
	public void setMuebArray(MuebArray ma) {
		muebArray=ma;
	}
	
	RestApiService() throws IOException{
		
		serverSocket=new ServerSocket(port);
		
	}
	
	public void run() {

		while(true) {
			System.out.println("ciklus");
			try {
				conn=serverSocket.accept();
				InputStream in=conn.getInputStream();
				OutputStream out=conn.getOutputStream();
				out.write(muebArray.toJsonString().getBytes());
				conn.close();
				
			}catch(Exception e){
				System.out.println(e.toString());
			}
		}
	}
	
	

}
