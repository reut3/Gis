package DataBase;

import java.util.ArrayList;

public class MacSignal {

	private int signal;
	private String mac;
	
	
	public MacSignal(){	
	}
	
	public MacSignal(String signal, String mac) {
		this.signal = (int)Double.parseDouble(signal);
		this.mac = mac;
	}

	@Override
	public String toString() {
		return "MacSignal [signal=" + signal + ", mac=" + mac + "]";
	}

	public int getSignal() {
		return signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
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
