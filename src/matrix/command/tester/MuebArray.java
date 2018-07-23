package matrix.command.tester;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class MuebArray {
	private Mueb container[][];
	public final int width=2;
	public final int  height=3;
	public void setMueb(int emelet,int szoba) {
		
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
