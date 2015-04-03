package jp.ihoku;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	private static EditText mEditbox = null;
	private static Button mButton = null;
	private static WebView mWebView = null;
	private static ActionBarDrawerToggle mDrawerToggle;
	private static DrawerLayout mDrawer;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mWebView = (WebView)findViewById(R.id.webView1);
		mWebView.setWebViewClient(new WebViewClient());
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setLoadWithOverviewMode(true);
		mWebView.getSettings().setUseWideViewPort(true);
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,R.drawable.ic_drawer, R.string.string_about_app,R.string.string_app_info) {
			@Override
			public void onDrawerClosed(View drawerView) {
				//PdLog.i(TAG, "onDrawerClosed");
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				//PdLog.i(TAG, "onDrawerOpened");
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				// ActionBarDrawerToggleクラス内の同メソッドにてアイコンのアニメーションの処理をしている。
				// overrideするときは気を付けること。
				super.onDrawerSlide(drawerView, slideOffset);

				//PdLog.i(TAG, "onDrawerSlide : " + slideOffset);
			}

			@Override
			public void onDrawerStateChanged(int newState) {
				// 表示済み、閉じ済みの状態：0
				// ドラッグ中状態:1
				// ドラッグを放した後のアニメーション中：2
				//PdLog.i(TAG, "onDrawerStateChanged  new state : " + newState);
			}
		};

		mDrawer.setDrawerListener(mDrawerToggle);
		if( mButton != null ) mButton.setOnClickListener(this);
		mWebView.loadUrl("https://dl.dropboxusercontent.com/spa/85226c8ivht2m24/Exports/ad/ad.html");
    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
        SpannableStringBuilder url = (SpannableStringBuilder)mEditbox.getText();
        Log.e("E","url.toString():"+url.toString());
        mWebView.loadUrl(url.toString());
	}

}
