package com.cauly.nativead;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fsn.cauly.CaulyAdInfo;
import com.fsn.cauly.CaulyNativeAdHelper;
import com.fsn.cauly.CaulyNativeAdInfoBuilder;
import com.fsn.cauly.CaulyNativeAdView;
import com.fsn.cauly.CaulyNativeAdViewListener;

public class NativeListActivity extends Activity implements CaulyNativeAdViewListener  {

	String APP_CODE="cZCXKCsY";//"gatester";  // your app code which you are assigned.
	String[] TITLE = {"빈폴 2014 S/S시즌오프 UP TO 30%+10%...","화제의 텀블러 리버스 보틀/전용파우치","제이에스티나 외 쥬얼리& 시계 여름아이템 ~ 50% OFF",
					"MACMOC 2014 Molling & Cushy Series","애플 힙, 힙업을위한다면!","꼭 한번 읽어봐야 할 책!","네스카페 돌체구스토 피콜로&캡슐"};
	String[] SUBTITLE={"신세계몰 해피바이러스","요즘 핫한 텀블러! 리버스보틀","제이에스티나/스톤헨지/마크제이콥스/TISSOT 쥬얼리&시계 특가상품 + 추가쿠폰...","신세계몰 해피바이러스 여성샌들/슬리퍼 ",
					"휴먼팩토리 애플힙",	"책/일반도서/일반책/눈물편지/3분 통찰력/지혜의 한 줄/당신에게없는 한가지...",	"캡슐커피 1위 네스카페! 돌체구스토 커피머신"};
	String[] DESCRIPTION={"0","19,500","79,800","80,100","169,000","3,500","69,000"};
	String[] TAG={"신세계몰 해피 바이러스","CJ오클락 ","CJ오클락 ","신세계몰 ","티몬","위메프","위메프"};
	
	ArrayList<Item> mList ;
	ListView listview; 
	ListAdapter mAdapter;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mAdapter = new ListAdapter();
		mList = new ArrayList<Item>();
		for(int i=0; i<80; i++)
		{
			mList.add(new Item(R.drawable.a1 + i%7,TITLE[i%7],SUBTITLE[i%7],DESCRIPTION[i%7],TAG[i%7]));
		}
		listview = (ListView) findViewById(R.id.native_area);
		listview.setAdapter(mAdapter);
		showNative();
    }
	
	
	public void showNative()
	{
		CaulyAdInfo adInfo = new CaulyNativeAdInfoBuilder(APP_CODE)
		.layoutID(R.layout.activity_native_iconlist)
		.iconImageID(R.id.icon)
		.titleID(R.id.title)
		.subtitleID(R.id.subtitle)
		.build();
		CaulyNativeAdView nativeAd = new CaulyNativeAdView(this);
		nativeAd.setAdInfo(adInfo);
		nativeAd.setAdViewListener(this);
		nativeAd.request();

	}
	public void showNativeCustom()
	{
		CaulyAdInfo adInfo = new CaulyNativeAdInfoBuilder(APP_CODE)
		.layoutID(R.layout.activity_native_iconlist)
		.iconImageID(R.id.icon)
		.titleID(R.id.title)
		.subtitleID(R.id.subtitle)
		.landingLayoutID(R.layout.activity_native_detail)
		.landingMainImageID(R.id.native_ad_main_image)
		.landingIconImageID(R.id.native_ad_icon_image)
		.landingTextID(R.id.native_ad_text)
		.landingTitleID(R.id.native_ad_title)
		.landingSubtitleID(R.id.native_ad_subtitle)
		.landingCloseID(R.id.native_close_btn)
		.build();
		CaulyNativeAdView nativeAd = new CaulyNativeAdView(this);
		nativeAd.setAdInfo(adInfo);
		nativeAd.setAdViewListener(this);
		nativeAd.request();
	}
	
	class ListAdapter extends BaseAdapter 
	{
		private static final int YOUR_ITEM_TYPE = 0;
		private static final int YOUR_ITEM_COUNT = 1;
		
		public ListAdapter()
		{
		}
		public int getCount() {
			return mList.size();
		}

		public Item getItem(int position) {
			return mList.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		@Override
		public int getItemViewType(int position) {
			if(CaulyNativeAdHelper.getInstance().isAdPosition(listview,position))
				return YOUR_ITEM_TYPE+1;
			else 
				return YOUR_ITEM_TYPE;
		}
		@Override
		public int getViewTypeCount() {
			return YOUR_ITEM_COUNT+1;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			if(CaulyNativeAdHelper.getInstance().isAdPosition(listview, position) )
			{
				return CaulyNativeAdHelper.getInstance().getView(listview,position, convertView);
			}
			
			if(convertView==null)
			{
				View view=  View.inflate(NativeListActivity.this, R.layout.activity_listview, null);
				TextView title = (TextView) view.findViewById(R.id.title);
				TextView subtitle = (TextView) view.findViewById(R.id.subtitle);
				TextView description = (TextView) view.findViewById(R.id.description);
				TextView tag = (TextView) view.findViewById(R.id.tag);
				ImageView icon = (ImageView)view.findViewById(R.id.icon);
				icon.setBackgroundResource(getItem(position).img);
				title.setText(""+getItem(position).title);
				subtitle.setText(""+getItem(position).subTitle);
				tag.setText(""+getItem(position).tag);
				description.setText(""+getItem(position).description);
				return view;
			}
			else
			{
				TextView title = (TextView) convertView.findViewById(R.id.title);
				TextView subtitle = (TextView) convertView.findViewById(R.id.subtitle);
				ImageView icon = (ImageView)convertView.findViewById(R.id.icon);
				TextView description = (TextView) convertView.findViewById(R.id.description);
				TextView tag = (TextView) convertView.findViewById(R.id.tag);
				title.setText(""+getItem(position).title);
				subtitle.setText(""+getItem(position).subTitle);
				icon.setBackgroundResource(getItem(position).img);
				tag.setText(""+getItem(position).tag);
				description.setText(""+getItem(position).description);
			}
			return convertView;
		}
	}
	
	public void onFailedToReceiveNativeAd(CaulyNativeAdView adView,	int errorCode, String errorMsg) {
		
	}
	
	int r=8;
	public void onReceiveNativeAd(CaulyNativeAdView adView, boolean isChargeableAd) {
		
		mList.add(r,null);
		CaulyNativeAdHelper.getInstance().add(this,listview,r,adView);
		r = r+4;
		mAdapter.notifyDataSetChanged();
	}
}
