package object;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class PKManager {

	private static PKManager myself;
	private HashMap<Long, PK> pkMap;
	private LinkedList<PK> pkList;
	private ArrayList<PK> pkListFilter;
	private PKManager() {
		pkMap = new HashMap<Long, PK>();
		pkList = new LinkedList<PK>();
		pkListFilter= new ArrayList<PK>();
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
		pkListFilter.clear();
	}
	public void Filterclear() {
		pkListFilter.clear();
	}
	public void add(PK pk) {
		pkMap.put(pk.sql_id, pk);
		pkList.push(pk);
	}
	public void addFilter(PK pk) {
		pkListFilter.add(pk);
	}

	/**
	 * 获取挑战条目数量
	 * 
	 * @return
	 */
	public int getPKNum() {
		return pkList.size();
	}
	public int getFilterPKNum() {
		return pkListFilter.size();
	}
	/**
	 * 获取挑战条目
	 * 
	 * @return
	 */
	public PK getPKByIndex(int index) {
		return pkList.get(index);
	}
	/**
	 * 获取 过滤挑战条目
	 * 
	 * @return
	 */
	public PK getFilterPKByIndex(int index) {
		return pkListFilter.get(index);
	}
	/**
	 * 获取挑战条目
	 * 
	 * @return
	 */
	public PK getPKBySQLID(long sql_id) {
		return pkMap.get(sql_id);
	}

	public static void main(String[] args) {

		try {
			System.out.println(URLEncoder.encode("", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PK pk = new PK(null, null, null, null, null, null, 0, 0, 0, null);
		PKManager.getInstance().add(pk);
		PK pk1 = PKManager.getInstance().getPKBySQLID(0);
		pk1.faqiSeatCount = 10;
		pk1.yingzhanSeatCount = 10;
		PK pk2 = PKManager.getInstance().getPKByIndex(0);
		System.out.println(pk2.faqiSeatCount);
	}
}
