package app.Online966.com;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;


// bar code:https://www.youtube.com/watch?v=7fUVwkRDF0g
//https://developers.google.com/identity/sign-in/android/start-integrating
public class MainActivity extends AppCompatActivity {

    WebView myWebView;
    Boolean check=false;
    ImageView   imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void toolweb(){
        if (checkNetwork()){
            setContentView(R.layout.webview);

            myWebView = (WebView)findViewById(R.id.webView);
            loadWebViewLoad(myWebView);


        }


    }
    @Override
    public void onBackPressed() {

        alartExit();
    }

    private void loadWebViewLoad(WebView x) {
        x.getSettings().setJavaScriptEnabled(true);
        x.getSettings().setUseWideViewPort(true);
        x.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        x.getSettings().setSupportMultipleWindows(true);
        x.getSettings().setLoadWithOverviewMode(true);
        x.getSettings().setBuiltInZoomControls(true);
        x.setWebViewClient(new WebViewClient());
        //x.loadUrl("http://alosboiya.com.sa/");
       // x.loadUrl("https://demos.telerik.com/aspnet-ajax/asyncupload/examples/multiplefileselection/defaultcs.aspx");
        x.loadUrl("http://www.966online.com/index.php?route=common/home");
        myWebView=x;

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



}
