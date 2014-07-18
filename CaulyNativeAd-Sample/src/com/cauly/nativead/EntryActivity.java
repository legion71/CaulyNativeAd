package com.cauly.nativead;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EntryActivity extends Activity   {

	ListView listview; 
	static final String[] type = {"네이티브 리스트뷰 타입", "네이티브 뷰타입","네이티브 카드뷰 타입"};
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
		listview = (ListView) findViewById(R.id.native_area);
		ArrayAdapter<String>   myAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, type);  
		listview.setAdapter(myAdapter );
		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				if(position==0)
					startActivity(new Intent(EntryActivity.this, NativeListActivity.class));
				else if(position==1)
					startActivity(new Intent(EntryActivity.this, NativeViewActivity.class));
				else 
					startActivity(new Intent(EntryActivity.this, NativeCardActivity.class));
			}
		});
    }
	
}
