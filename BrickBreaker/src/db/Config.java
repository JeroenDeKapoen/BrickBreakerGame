package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class Config {
	private Properties props = new Properties();
	private String[] existingProps = new String[] {"Stonehit","Lifeloss","Challenger","TEST"};
	
	public Config() throws IOException {
		this.Load();
	}
	public String getConfig(String k) {
		return props.getProperty(k,"0");
	}
	
	public void setConfig(String k, String v) {
		if(!v.equals("") && !k.equals("") && Arrays.asList(existingProps).contains(k))
		{
			props.setProperty(k, v);
		}
	}
	public void delKey(String k){
		if(props.containsKey(k))
		{
			props.remove(k);
		}
	}
	public void Load() throws IOException
	{
		//Als de config file niet bestaat, maak hem dan aan indien mogelijk.
		File f = new File("appProperties");
		if(!f.exists()) {
		    f.createNewFile();
		} 
		FileInputStream in = new FileInputStream("appProperties");
		props.load(in);
		in.close();
	}
	public void Save() throws IOException
	{
		FileOutputStream out = new FileOutputStream("appProperties");
		props.store(out, "---No Comment---");
		out.close();
	}
}
