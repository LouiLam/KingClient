package object;


import java.util.ArrayList;
import java.util.List;

public class PKManager {

	

	private static PKManager myself;
	private ArrayList<PK> pkList;
	
	private PKManager(){
		pkList=new ArrayList<>();
	}
	
	public static PKManager getInstance(){
		if(myself == null){
			myself = new PKManager();
		}
		return myself;
	}
	
	
	public void add(PK pk)
	{
		pkList.add(pk);
	}
	/**
	 * 获取挑战条目数量 
	 * @return
	 */
	public int getPKNum(){
		return pkList.size();
	}

	/**
	 * 获取挑战条目
	 * @return
	 */
	public PK getPKByIndex(int index) {
		return pkList.get(index);
	}
}
