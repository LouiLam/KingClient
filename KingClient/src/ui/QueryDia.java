package ui;

import java.util.ArrayList;
import java.util.HashMap;

import object.JfaceWindowManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.jboss.netty.channel.Channel;
import org.json.JSONArray;
import org.json.JSONObject;

public class QueryDia extends Dialog {
	private Combo status;
	private Combo gamename;
	private Combo point;
	private Combo area;
	private Combo map;
	private Combo type;
	private Table table_1;
	private Label queryTime;
  HashMap<Integer, String>statusMap=new HashMap<>();
	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public QueryDia(Shell parentShell) {
		super(parentShell);
		setWindowManager(JfaceWindowManager.wm);
	}
	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		statusMap.put(0, "未开始");
		statusMap.put(1, "进行中");
		statusMap.put(2, "结束");
		statusMap.put(3, "有人未进游戏");
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 4;
		new Label(container, SWT.NONE);

		Label label1 = new Label(container, SWT.NONE);
		label1.setText("对局状态");
		new Label(container, SWT.NONE);

		status = new Combo(container, SWT.READ_ONLY);
		status.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		status.add("未开始");
		status.add("进行中");
		status.add("结束");
		status.add("有人未进游戏");
		new Label(container, SWT.NONE);

		Label label2 = new Label(container, SWT.NONE);
		label2.setText("对局开始时间");
		
		 queryTime = new Label(container, SWT.NONE);
		queryTime.setText(DateUtil.getCurDate());
		queryTime.setVisible(false);
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DateTimeDia dia=new DateTimeDia(QueryDia.this.getParentShell(),queryTime);
				dia.open();
              
            
			}
		});
		button.setText("日期选择器");
		new Label(container, SWT.NONE);

		Label label3 = new Label(container, SWT.NONE);
		label3.setText("游戏名称");
		new Label(container, SWT.NONE);

		gamename = new Combo(container, SWT.READ_ONLY);
		gamename.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		gamename.add("英雄联盟");
		gamename.select(0);
		new Label(container, SWT.NONE);

		Label label4 = new Label(container, SWT.NONE);
		label4.setText("游戏区服");
		new Label(container, SWT.NONE);

		area = new Combo(container, SWT.READ_ONLY);
		area.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		for (String str : 	KingLogin.AREA) {
			area.add(str);
		}
		
		new Label(container, SWT.NONE);

		Label label5 = new Label(container, SWT.NONE);
		label5.setText("挑战点数");
		new Label(container, SWT.NONE);

		point = new Combo(container, SWT.READ_ONLY);
		point.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		point.add("10");
		point.add("30");
		point.add("50");
		point.add("100");
		point.add("200");
		new Label(container, SWT.NONE);

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("游戏地图");
		new Label(container, SWT.NONE);

		map = new Combo(container, SWT.READ_ONLY);
		map.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		map.add("扭曲丛林");
		map.add("召唤师峡谷");
		map.add("水晶之痕");
		map.add("嚎哭深渊");
		new Label(container, SWT.NONE);
		
		Label label = new Label(container, SWT.NONE);
		label.setText("对战人数");
		new Label(container, SWT.NONE);
		
		type = new Combo(container, SWT.READ_ONLY);
		type.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		type.add("1v1");
		type.add("2v2");
		type.add("3v3");
		type.add("4v4");
		type.add("5v5");
		table_1 = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		TableColumn column = new TableColumn(table_1, SWT.NONE);
		column.setWidth(30);
		column.setText("id");
		TableColumn column1 = new TableColumn(table_1, SWT.NONE);
		column1.setWidth(50);
		column1.setText("状态");
		TableColumn column2 = new TableColumn(table_1, SWT.NONE);
		column2.setWidth(100);
		column2.setText("创建日期");
		TableColumn column3 = new TableColumn(table_1, SWT.NONE);
		column3.setWidth(60);
		column3.setText("游戏名称");
		TableColumn column4 = new TableColumn(table_1, SWT.NONE);
		column4.setWidth(100);
		column4.setText("区服");
		TableColumn column5 = new TableColumn(table_1, SWT.NONE);
		column5.setWidth(60);
		column5.setText("标题");
		TableColumn column6 = new TableColumn(table_1, SWT.NONE);
		column6.setWidth(100);
		column6.setText("地图");
		TableColumn column7 = new TableColumn(table_1, SWT.NONE);
		column7.setWidth(60);
		column7.setText("挑战点数");
		TableColumn column8 = new TableColumn(table_1, SWT.NONE);
		column8.setWidth(60);
		column8.setText("描述");
		TableColumn column9 = new TableColumn(table_1, SWT.NONE);
		column9.setWidth(60);
		column9.setText("类型");
		TableColumn column10 = new TableColumn(table_1, SWT.NONE);
		column10.setWidth(100);
		column10.setText("胜利次数");
		



		return container;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.FINISH_ID,
				IDialogConstants.OK_LABEL, true);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					httpGetFightCha(null);
					DateTimeDia.timeMillis=0;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setText("确定");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(814, 532);
	}
	ArrayList<JSONObject> list=new ArrayList<JSONObject>();
	ArrayList<Control> listControl=new ArrayList<Control>();

	public  void  httpGetFightCha(Channel channel) throws Exception {
		String url="http://121.127.253.207/yxlm/member/fight_cha.php?action=dopost";
		
		
		if(status.getText().length()!=0)
		{
			{url+="&status="+status.getSelectionIndex();}
		}
		if(DateTimeDia.timeMillis!=0)
		{
			url+="&date_s="+DateTimeDia.timeMillis/1000;
		}
		if(area.getText().length()!=0)
		{url+="&area="+area.getText();}
		if(point.getText().length()!=0)
		{url+="&price="+point.getText();}
		if(map.getText().length()!=0)
		{url+="&map="+map.getText();}
		if(type.getText().length()!=0)
		{url+="&map="+type.getSelectionIndex()+1;}
		
		  System.out.println(url);
		
//		String testEncode = URLEncoder.encode("2|3|4", "utf-8" ); 
//		String testEncode1 = URLEncoder.encode("12|13|14", "utf-8" ); 
//		String uu="action=stac&id="+sql_id+"&status=1&gt="+testEncode+"&yt="+testEncode1;	
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST either fully consume the response content  or abort request
            // execution by calling CloseableHttpResponse#close().

            try {
            	list.clear();
            	
            	String str=EntityUtils.toString(response1
 						.getEntity());
            	JSONArray array=new JSONArray(str);
            	for (int i = 0; i < array.length(); i++) {
            		JSONObject obj=	array.getJSONObject(i);
            		list.add(obj);
            	
        		}
                HttpEntity entity1 = response1.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                if(response1.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
                {
                
                	 System.out.println(str);
                	 RefreshTable();
                	//用户点击开始游戏按钮成功
                }
                EntityUtils.consume(entity1);
              
            } finally {
                response1.close();
            }

        } finally {
            httpclient.close();
        }
    
		
		
		
	

	}
	public void RefreshTable()
	{
		table_1.clearAll();
		table_1.removeAll();
		for (int i = 0; i < listControl.size(); i++) {
			listControl.get(i).dispose();
		}
		listControl.clear();
		System.out.println(table_1.getItems().length);
		for (int i = 0; i < list.size(); i++) {
    		new TableItem(table_1, SWT.NONE);
		}
		
//		{"id":"15",
//			"status":"1"
//			,"date_create":"1397296152",
//			"gamename":"\u82f1\u96c4\u8054\u76df",
//			"area":"\u827e\u6b27\u5c3c\u4e9a---\u7535\u4fe1",
//			"title":"1",
//			"price":"100",
//			"map":"\u626d\u66f2\u4e1b\u6797",
//			"description":"1",
//			"type":"1",
//			"win":"0"
//			},
		TableItem[] items = table_1.getItems();
		System.out.println(table_1.getItems().length);
		for (int i = 0; i < items.length; i++) {
			TableEditor editor = new TableEditor(table_1);
			Text text = new Text(table_1, SWT.NONE);
			text.setText(list.get(i).getString("id"));
			text.setEditable(false);
			text.setBackground(table_1.getBackground());
			editor.grabHorizontal = true;
			editor.setEditor(text, items[i], 0);
			listControl.add(text);
			
			editor = new TableEditor(table_1);
			text = new Text(table_1, SWT.NONE);
			int status=Integer.parseInt(list.get(i).getString("status"));
			text.setText(statusMap.get(status));
			text.setEditable(false);
			text.setBackground(table_1.getBackground());
			editor.grabHorizontal = true;
			editor.setEditor(text, items[i], 1);
			listControl.add(text);
			
			editor = new TableEditor(table_1);
			text = new Text(table_1, SWT.NONE);
			text.setText(DateUtil.getDateByTimestamp(Integer.parseInt(list.get(i).getString("date_create"))));
			text.setEditable(false);
			text.setBackground(table_1.getBackground());
			editor.grabHorizontal = true;
			editor.setEditor(text, items[i], 2);
			listControl.add(text);
			
			editor = new TableEditor(table_1);
			text = new Text(table_1, SWT.NONE);
			text.setText(list.get(i).getString("gamename"));
			text.setEditable(false);
			text.setBackground(table_1.getBackground());
			editor.grabHorizontal = true;
			editor.setEditor(text, items[i], 3);
			listControl.add(text);
			
			editor = new TableEditor(table_1);
			text = new Text(table_1, SWT.NONE);
			text.setText(list.get(i).getString("area"));
			text.setEditable(false);
			text.setBackground(table_1.getBackground());
			editor.grabHorizontal = true;
			editor.setEditor(text, items[i], 4);
			listControl.add(text);
			
			editor = new TableEditor(table_1);
			text = new Text(table_1, SWT.NONE);
			text.setText(list.get(i).getString("title"));
			text.setEditable(false);
			text.setBackground(table_1.getBackground());
			editor.grabHorizontal = true;
			editor.setEditor(text, items[i], 5);
			listControl.add(text);
			
			editor = new TableEditor(table_1);
			text = new Text(table_1, SWT.NONE);
			text.setText(list.get(i).getString("map"));
			text.setEditable(false);
			text.setBackground(table_1.getBackground());
			editor.grabHorizontal = true;
			editor.setEditor(text, items[i], 6);
			listControl.add(text);
			
			editor = new TableEditor(table_1);
			text = new Text(table_1, SWT.NONE);
			text.setText(list.get(i).getString("price"));
			text.setEditable(false);
			text.setBackground(table_1.getBackground());
			editor.grabHorizontal = true;
			editor.setEditor(text, items[i], 7);
			listControl.add(text);
			
			editor = new TableEditor(table_1);
			text = new Text(table_1, SWT.NONE);
			text.setText(list.get(i).getString("description"));
			text.setEditable(false);
			text.setBackground(table_1.getBackground());
			editor.grabHorizontal = true;
			editor.setEditor(text, items[i], 8);
			listControl.add(text);
			
			editor = new TableEditor(table_1);
			text = new Text(table_1, SWT.NONE);
			text.setText(list.get(i).getString("type"));
			text.setEditable(false);
			text.setBackground(table_1.getBackground());
			editor.grabHorizontal = true;
			editor.setEditor(text, items[i], 9);
			listControl.add(text);
			
			editor = new TableEditor(table_1);
			text = new Text(table_1, SWT.NONE);
			text.setText(list.get(i).getString("win"));
			text.setEditable(false);
			text.setBackground(table_1.getBackground());
			editor.grabHorizontal = true;
			editor.setEditor(text, items[i], 10);
			listControl.add(text);
		}
//		column.setText("id");
//		column.setText("状态");
//		column.setText("创建日期");
//		column.setText("游戏名称");
//		column.setText("区服");
//		column.setText("标题");
//		column.setText("地图");
//		column.setText("挑战点数");
//		column.setText("描述");
//		column.setText("类型");
//		column.setText("胜利次数");
	
	}
}
