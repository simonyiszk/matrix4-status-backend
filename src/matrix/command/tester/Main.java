package matrix.command.tester;

public class Main {

	public static void main(String[] args) {
		MuebArray cont=new MuebArray();
		System.out.println(cont.toJsonString());
		System.out.println("test started");
		Commander comm=new Commander("get-status");
		System.out.println("initialisation is successful :)");
		for(int i=0;i<256;i++) {
			System.out.println("sending to ip address: 192.168.1."+Integer.toString(i));
			System.out.println(comm.send("192.168.1."+Integer.toString(i)));
		}

	}

}
