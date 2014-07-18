package com.cauly.nativead;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.fsn.cauly.CaulyAdInfo;
import com.fsn.cauly.CaulyNativeAdInfoBuilder;
import com.fsn.cauly.CaulyNativeAdView;
import com.fsn.cauly.CaulyNativeAdViewListener;

public class NativeViewActivity extends Activity implements CaulyNativeAdViewListener  {

	String APP_CODE="cZCXKCsY";//"gatester";  // your app code which you are assigned.
	ViewGroup native_container;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        native_container = (ViewGroup) findViewById(R.id.native_container);
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
	
	public void onFailedToReceiveNativeAd(CaulyNativeAdView adView,	int errorCode, String errorMsg) {
		
	}
	
	public void onReceiveNativeAd(CaulyNativeAdView adView, boolean isChargeableAd) {
		adView.attachToView(native_container);
	}
}
