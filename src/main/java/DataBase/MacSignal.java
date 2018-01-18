package DataBase;

import java.util.ArrayList;

/**
 * 
 * class MacSignal- the object contain two parameters: signal (int), mac (string)
 * contains the functions- getters and setters to the features, toString, parsing
 */
public class MacSignal {
	
	//MacSignal features:
	private int signal;
	private String mac;
	
	
	/**
	 * MacSignal's empty constructor
	 */
	public MacSignal(){	
	}
	
	
	/**
	 * MacSignal's Explicit constructor
	 * @param signal
	 * @param mac
	 */
	public MacSignal(String signal, String mac) {
		this.signal = (int)Double.parseDouble(signal);
		this.mac = mac;
	}

	
	/**
	 * information of the object
	 */
	@Override
	public String toString() {
		return "MacSignal [signal=" + signal + ", mac=" + mac + "]";
	}

	
	/**
	 * 
	 * @return MacSignal's signal
	 */
	public int getSignal() {
		return signal;
	}

	
	/**
	 * 
	 * @param sets MacSignal's signal
	 */
	public void setSignal(int signal) {
		this.signal = signal;
	}

	
	/**
	 * 
	 * @return MacSignal's mac
	 */
	public String getMac() {
		return mac;
	}

	
	/**
	 * sets MacSignal's mac
	 * @param mac
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	
	/**
	 * The function gets string contains pairs of mac and signals, analyzing it, store it in array list of MacSignal
	 * @param s- string contains pairs of mac and signals
	 * @return array list of MacSignal
	 */
	public static ArrayList<MacSignal> parsing(String s){
		ArrayList<MacSignal> MacSignalInput=new ArrayList<MacSignal>();
		String[] WhatShouldIdo=s.split(",");
		for(int i=0;i<WhatShouldIdo.length;i++)
		{
			MacSignalInput.add((new MacSignal(WhatShouldIdo[i+1],WhatShouldIdo[i])));
					i++;
		}
		return MacSignalInput;
	}



}
