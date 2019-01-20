package app.Online966.com;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import im.delight.android.webview.AdvancedWebView;


// bar code:https://www.youtube.com/watch?v=7fUVwkRDF0g
//https://developers.google.com/identity/sign-in/android/start-integrating
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , AdvancedWebView.Listener{
    Toolbar         toolbar ;
    DrawerLayout    drawer ;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
     WebView myWebView;
    Boolean check=false;
    ImageView   imageView;
    JavaScriptInterface JSInterface;
    AdvancedWebView     advancedwebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        advancedwebview = new AdvancedWebView(this);

        if (checkNetwork()){
            toolweb();

        }
        else {
            setContentView(R.layout.no_connection);
            alart();
            imageView = (ImageView)findViewById(R.id.connection);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkNetwork()){

                        toolweb();}
                    else{
                    alart();
                }
                }
            });

        }
    }

    public void toolweb() {
        if (checkNetwork()) {
            setContentView(R.layout.toolbar_connect_admin1);
            toolbar = (Toolbar) findViewById(R.id.toolbar_one);
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.rgb(255,255,255));
            toolbar.setTitleMargin(260,0,0,0);

        /*    drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            // navigationView.setItemIconTintList(null);

           */ advancedwebview = (AdvancedWebView) findViewById(R.id.webView);

            advancedwebview.loadUrl("http://www.966online.com/index.php?route=common/home");
            advancedwebview.getSettings().setLoadWithOverviewMode(true);
            advancedwebview.zoomOut();
            advancedwebview.getSettings().setUseWideViewPort(true);


        }
    }

    @Override
    public void onBackPressed() {

        alartExit();
    }


    public void alart() {
        AlertDialog al = new AlertDialog.Builder(this).create();
        al.setMessage("No Internet connection. Would you like to enable Internet connection ?");
        al.setButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        al.setButton2("MOBILEDATA", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.startActivity(new Intent("android.settings.DATA_ROAMING_SETTINGS"));
            }
        });
        al.setButton3("WIFI", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
            }
        });
        al.show();
    }

    private Boolean checkNetwork(){

        ConnectivityManager manager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            check=true;
        }else check=false;
        return check;
    }
    public void alartExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MainActivity.this.finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.Electronics:
                this.advancedwebview.loadUrl("http://www.966online.com/index.php?route=product/category&path=20");
                break;
            case R.id.Beauty:
                this.advancedwebview.loadUrl("http://www.966online.com/index.php?route=product/category&path=60");
                break;
            case R.id.Fashion:
                this.advancedwebview.loadUrl("http://www.966online.com/index.php?route=product/category&path=61");
                break;
            case R.id.Office:
                this.advancedwebview.loadUrl("http://www.966online.com/index.php?route=product/category&path=67");
                break;
            case R.id.Automotive:
                this.advancedwebview.loadUrl("http://www.966online.com/index.php?route=product/category&path=66");
                break;
            case R.id.Home:
                this.advancedwebview.loadUrl("http://www.966online.com/index.php?route=product/category&path=62");
                break;
            default:
               break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }
}
