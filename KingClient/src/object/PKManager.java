package object;

import java.util.ArrayList;
import java.util.HashMap;

public class PKManager {

	private static PKManager myself;
	private  HashMap<Long, PK> pkMap;
	private  ArrayList<PK> pkList;

	private PKManager() {
		pkMap = new HashMap<Long, PK>();
		pkList = new ArrayList<PK>();
	}

	public static PKManager getInstance() {
		if (myself == null) {
			myself = new PKManager();
		}
		return myself;
	}

	public void clear() {
		pkMap.clear();
		pkList.clear();
	}

	public  void add(PK pk) {
		pkMap.put(pk.sql_id, pk);
		pkList.add(pk);
	}

	/**
	 * 获取挑战条目数量
	 * 
	 * @return
	 */
	public int getPKNum() {
		return pkList.size();
	}

	/**
	 * 获取挑战条目
	 * 
	 * @return
	 */
	public  PK getPKByIndex(int index) {
		return pkList.get(index);
	}
	/**
	 * 获取挑战条目
	 * 
	 * @return
	 */
	public  PK getPKBySQLID(long sql_id) {
		return pkMap.get(sql_id);
	}
	public static void main(String[] args) {
	
		PK pk=new PK(null, null, null, null, null, null, 0, 0, 0);
		PKManager.getInstance().add(pk);
		PK pk1=PKManager.getInstance().getPKBySQLID(0);
		pk1.faqiSeatCount=10;
		pk1.yingzhanSeatCount=10;
		PK pk2=PKManager.getInstance().getPKByIndex(0);
		System.out.println(pk2.faqiSeatCount);
	}
}
