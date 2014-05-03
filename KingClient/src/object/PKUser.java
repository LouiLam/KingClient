package object;

public class PKUser {
	//客户端是否是房主
	public static long sql_id=-1;
	public static int type=-1;
	public static int uid=-1;
public String name;
public int camp;//阵营 1发起 2应战
public int seatID;//座位号0~4
public PKUser(String name, int camp, int seatID) {
	super();
	this.name = name;
	this.camp = camp;
	this.seatID = seatID;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return "name:"+name+",camp:"+camp+",seatID"+seatID;
}
}
