package object;




public class PK {
	
	public String id;//发起挑战的房主ID
	public String roleName;//游戏角色名
	public String title;//挑战标题
	public String area;//挑战区
	public String map;//挑战地图
	public String des;//挑战描述
	public String password;//房间密码
	public int type;//游戏对战人数类型
	public int point;//挑战点数
	public long sql_id;//数据库ID
	public String curNum;
	public PKUser[]faqi=new PKUser[5];
	public PKUser[]yingzhan=new PKUser[5];
	public int faqiSeatCount = 0;
	public int yingzhanSeatCount = 0;
	public PK( String id,String roleName,String title, String area, String map,String des, int type,
			int point,long sql_id,String password) {
		super();
		this.id=id;
		this.roleName=roleName;
		this.title = title;
		this.area = area;
		this.map = map;
		this.des=des;
		this.type = type;
		this.point = point;
		this.sql_id=sql_id;
		this.password=password;
	}

	@Override
	public String toString() {
		return "房主id:"+id+"游戏角色名："+roleName+" 标题:"+title+" 挑战区:"+area+" 挑战地图:"+map+" 游戏对战人数类型"+type+" 挑战点数"+point;
	}


}
