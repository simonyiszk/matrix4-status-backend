package matrix.command.tester;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class MuebArray {
	private Mueb container[][];
	public final int width=8;
	public final int  height=13;

	public void setMueb(int emelet,int szoba, Mueb mueb) {
		container[emelet-6][szoba-5]=mueb;
		System.out.println("mueb has been set");
	}

	public Mueb getMueb(int emelet,int szoba) {
		return container[emelet-6][szoba-5];
	}
	
	
	public MuebArray() {
		container=new Mueb[height][width];
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				container[i][j]=new Mueb();
			}
		}
	}
	
	public String toJsonString() {
		GsonBuilder builder=new GsonBuilder();
		Gson gson=new Gson();
		String json=gson.toJson(this);
		return json;
	}
	
}
