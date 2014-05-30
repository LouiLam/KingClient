package object;

public class PKUser {
	//客户端是否是房主
	public static long sql_id=-1;
	public static int type=-1;
	public static int uid=-1;
	// 阵营 1发起 2应战
	public static int myCamp=-1;
	public static int myPoint=-1;
public String id,roleName;
public int camp;//阵营 1发起 2应战
public int seatID;//座位号0~4
public PKUser(String id, String roleName,int camp, int seatID) {
	super();
	this.id = id;
	this.roleName=roleName;
	this.camp = camp;
	this.seatID = seatID;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return "id:"+id+"roleName:"+roleName+",camp:"+camp+",seatID"+seatID;
}
}
