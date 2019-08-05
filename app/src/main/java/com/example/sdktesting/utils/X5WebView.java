package com.example.sdktesting.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class X5WebView extends WebView {
	TextView title;
	private WebViewClient client = new WebViewClient() {
		/**
		 * 防止加载网页时调起系统浏览器
		 */
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	};

	@SuppressLint("SetJavaScriptEnabled")
	public X5WebView(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
		this.setWebViewClient(client);
		// this.setWebChromeClient(chromeClient);
		// WebStorage webStorage = WebStorage.getInstance();
		initWebViewSettings();
		this.getView().setClickable(true);
	}

	private void initWebViewSettings() {
		WebSettings webSetting = this.getSettings();
		webSetting.setJavaScriptEnabled(true);//允许js调用
		webSetting.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
		webSetting.setAllowFileAccess(true);//在File域下，能够执行任意的JavaScript代码，同源策略跨域访问能够对私有目录文件进行访问等
		webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);//控制页面的布局(使所有列的宽度不超过屏幕宽度)
		webSetting.setSupportZoom(true);//支持页面缩放
		webSetting.setBuiltInZoomControls(true);//进行控制缩放
		webSetting.setAllowContentAccess(true);//是否允许在WebView中访问内容URL（Content Url），默认允许
		webSetting.setUseWideViewPort(true);//设置缩放密度
		webSetting.setSupportMultipleWindows(false);//设置WebView是否支持多窗口,如果为true需要实现onCreateWindow(WebView, boolean, boolean, Message)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			//两者都可以
			webSetting.setMixedContentMode(webSetting.getMixedContentMode());//设置安全的来源
		}
		webSetting.setAppCacheEnabled(true);//设置应用缓存
		webSetting.setDomStorageEnabled(true);//DOM存储API是否可用
		webSetting.setGeolocationEnabled(true);//定位是否可用
		webSetting.setLoadWithOverviewMode(true);//是否允许WebView度超出以概览的方式载入页面，
		webSetting.setAppCacheMaxSize(Long.MAX_VALUE);//设置应用缓存内容的最大值
		webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);//设置是否支持插件
		webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);//重写使用缓存的方式
		webSetting.setAllowUniversalAccessFromFileURLs(true);//是否允许运行在一个file schema URL环境下的JavaScript访问来自其他任何来源的内容
		webSetting.setAllowFileAccessFromFileURLs(true);//是否允许运行在一个URL环境
	}

//	@Override
//	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
//		boolean ret = super.drawChild(canvas, child, drawingTime);
//		canvas.save();
//		Paint paint = new Paint();
//		paint.setColor(0x7fff0000);
//		paint.setTextSize(24.f);
//		paint.setAntiAlias(true);
//		if (getX5WebViewExtension() != null) {
//			canvas.drawText(this.getContext().getPackageName() + "-pid:"
//					+ android.os.Process.myPid(), 10, 50, paint);
//			canvas.drawText(
//					"X5  Core:" + QbSdk.getTbsVersion(this.getContext()), 10,
//					100, paint);
//		} else {
//			canvas.drawText(this.getContext().getPackageName() + "-pid:"
//					+ android.os.Process.myPid(), 10, 50, paint);
//			canvas.drawText("Sys Core", 10, 100, paint);
//		}
//		canvas.drawText(Build.MANUFACTURER, 10, 150, paint);
//		canvas.drawText(Build.MODEL, 10, 200, paint);
//		canvas.restore();
//		return ret;
//	}
//
//	public X5WebView(Context arg0) {
//		super(arg0);
//		setBackgroundColor(85621);
//	}

}
