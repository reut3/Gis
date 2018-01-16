package Main;


/** 
 * This is a very simple example representing how to work with MySQL 
 * using java JDBC interface;
 * The example mainly present how to read a table representing a set of WiFi_Scans
 * Note: for simplicity only two properties are stored (in the DB) for each AP:
 * the MAC address (mac) and the signal strength (rssi), the other properties (ssid and channel)
 * are omitted as the algorithms do not use the additional data.
 * 
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import DataBase.DataBase;
import DataBase.Sample;
import DataBase.wifi;

import java.sql.Statement;

public class SQL_DB {

	String _ip;
	String _url;
	String _user;
	String _password;
	String tableName;
	String DBname;
	private static Connection _con = null;

	public SQL_DB(String _ip, String _url, String _user, String _password, String DBname, String tableName) {
		super();
		this._ip = _ip;
		this._url = _url;
		this._user = _user;
		this._password = _password;
		this.tableName= tableName;
		this.DBname= DBname;
	}





	@SuppressWarnings("resource")
	public void insertDB(DataBase database) {
		Statement st = null;
		ResultSet rs = null;
//		int max_id = -1;

		try {     
			_con = DriverManager.getConnection(this._url, this._user, this._password);
			st = _con.createStatement();
			rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = '"+DBname+"' AND TABLE_NAME = '"+tableName+"'");
			if (rs.next()) {
				System.out.println("**** Update: "+rs.getString(1));
			}

			PreparedStatement pst = _con.prepareStatement("SELECT * FROM "+tableName);
			rs = pst.executeQuery();
			int ind=0;
			ArrayList<Sample> list= new ArrayList<Sample>();
			while (rs.next()) {
				int size = rs.getInt(7);
				int len = 7+2*size;
				if(ind==1){
					System.out.println("print"+rs.getString(1));

				}
				if(ind%100==0) {
					for(int i=1;i<=len;i++){
						//						System.out.print(ind+") "+rs.getString(i)+",");
					}
					//					System.out.println();
				}
				String id= rs.getString(3);
				String wifiAmount= rs.getString(7);
				String time= rs.getString(2);
				String lat= rs.getString(4);
				String lon= rs.getString(5);
				String alt= rs.getString(6);

				ArrayList<wifi> listOfWifi= new ArrayList<wifi>();

				for(int i=8; i<len;){
					String sSID= "0";
					String mAC= rs.getString(i);
					String rSSI= rs.getString(i+1);
					String channel1= "0";
					wifi tempWifi= new wifi(sSID, mAC, rSSI, channel1);
					listOfWifi.add(tempWifi);
					i+=4;
				}
				Sample temp= new Sample(time, id, lat, lon, alt, wifiAmount, listOfWifi);
				list.add(temp);
				ind++;
			}
//			System.out.println(list);
			Set<Sample> samples= new HashSet<>();
			samples.addAll(list);

			database.add(samples);

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQL_DB.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (st != null) { st.close(); }
				if (_con != null) { _con.close();  }
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(SQL_DB.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
//		return max_id;
	}










}
