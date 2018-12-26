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
import android.widget.Toast;


// bar code:https://www.youtube.com/watch?v=7fUVwkRDF0g
//https://developers.google.com/identity/sign-in/android/start-integrating
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    WebView myWebView;
    Boolean check=false;
    Toolbar toolbar ;
    DrawerLayout drawer ;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

   // private ValueCallback<Uri> mUploadMessage;
    /** File upload callback for platform versions prior to Android 5.0 */
    protected ValueCallback<Uri> mFileUploadCallbackFirst;
    /** File upload callback for Android 5.0+ */
    protected ValueCallback<Uri[]> mFileUploadCallbackSecond;
    private static final int FILE_CHOOSER_RESULT_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (checkNetwork()){

            toolweb();
            // Configure sign-in to request the user's ID, email address, and basic
            // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

          /*  myWebView.setWebChromeClient(new WebChromeClient() {

                public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                    mFileUploadCallbackFirst = uploadMsg;
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("image/*");
                   // startActivityForResult(i, 0);
                    startActivityForResult(Intent.createChooser(i, "File Chooser"), FILE_CHOOSER_RESULT_CODE);

                }
                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);
                    //  Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
                }
            });*/

        }
        else {
            setContentView(R.layout.drawer_no_connect);
            alart();

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.rgb(22,107,182));

            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.getItemIconTintList();
            navigationView.setItemIconTintList(null);

        }
    }

    public void toolweb(){
        if (checkNetwork()){
            setContentView(R.layout.drawer_connect);
            toolbar = (Toolbar) findViewById(R.id.toolbar_one);
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.rgb(22,107,182));


            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
           navigationView.setItemIconTintList(null);

            myWebView = (WebView)findViewById(R.id.webView);
            loadWebViewLoad(myWebView);


        }


    }
    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            alartExit();
        }
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
        x.setWebViewClient(new YourWebClient() );
        x.setWebChromeClient(new openchooserfile());
        //x.loadUrl("http://alosboiya.com.sa/");
        x.loadUrl("https://demos.telerik.com/aspnet-ajax/asyncupload/examples/multiplefileselection/defaultcs.aspx");

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


    //https://github.com/pjuu/droidotter/blob/master/app/src/main/java/com/pjuu/otterdroid/MainActivity.java
    public class openchooserfile extends WebChromeClient {

        // file upload callback (Android 2.2 (API level 8) -- Android 2.3 (API level 10)) (hidden method)
        @SuppressWarnings("unused")
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            openFileChooser(uploadMsg, null);
        }

        // file upload callback (Android 3.0 (API level 11) -- Android 4.0 (API level 15)) (hidden method)
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
            openFileChooser(uploadMsg, acceptType, null);
        }

        // file upload callback (Android 4.1 (API level 16) -- Android 4.3 (API level 18)) (hidden method)
        @SuppressWarnings("unused")
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            openFileInput(uploadMsg, null);
        }
        // file upload callback (Android 5.0 (API level 21) -- current) (public method)
        @SuppressWarnings("all")
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            openFileInput(null, filePathCallback);
            return true;
        }
    }


    @SuppressLint("NewApi")
    protected void openFileInput(final ValueCallback<Uri> fileUploadCallbackFirst,
                                 final ValueCallback<Uri[]> fileUploadCallbackSecond) {
        if (mFileUploadCallbackFirst != null) {
            mFileUploadCallbackFirst.onReceiveValue(null);
        }
        mFileUploadCallbackFirst = fileUploadCallbackFirst;
        if (mFileUploadCallbackSecond != null) {
            mFileUploadCallbackSecond.onReceiveValue(null);
        }
        mFileUploadCallbackSecond = fileUploadCallbackSecond;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*, video/*");
        i.putExtra(Intent.EXTRA_MIME_TYPES, new String[] {"image/*", "video/*"});
        startActivityForResult(Intent.createChooser(i, "Choose a file"),
                FILE_CHOOSER_RESULT_CODE);
    }
    public class YourWebClient extends WebViewClient {



        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (url.endsWith(".pdf")) {
                String googleDocs = "https://docs.google.com/viewer?url=";
                myWebView.loadUrl(googleDocs + url);
               // view.loadUrl(url);
                return true;
            } else {
                // Load all other urls normally.
                view.loadUrl(url);
            }

            return true;

        }
    }

    @Override
  /*  public boolean onNa
  vigationItemSelected(@NonNull MenuItem item) {
        return false;
    }*/


      @SuppressWarnings("StatementWithEmptyBody")

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        if (item.getItemId() == R.id.exit) {
            alartExit();
        } else {
            checkNetwork();
            toolweb();
            if (check) {
                switch (item.getItemId()) {
                    case R.id.home :
                        this.myWebView.loadUrl("http://alosboiya.com.sa/");
                        break;
                  /*  case R.id.about :
                        this.myWebView.loadUrl("http://alosboiya.com.sa/#about-us");
                        break;*/
                    case R.id.service:
                        this.myWebView.loadUrl("http://alosboiya.com.sa/about_services.aspx");
                        break;
                   /* case R.id.Ask_adv:
                        this.myWebView.loadUrl("http://alosboiya.com.sa/#portfolio");
                        break;*/
                    case R.id.Advertisement:
                        this.myWebView.loadUrl("http://alosboiya.com.sa/add_ads.aspx");
                        break;
                    case R.id.parts:
                        this.myWebView.loadUrl("http://alosboiya.com.sa/department.aspx");
                        break;
                    case R.id.archive:
                        this.myWebView.loadUrl("http://alosboiya.com.sa/archives.aspx");
                        break;
                    case R.id.call_us:
                        this.myWebView.loadUrl("http://alosboiya.com.sa/index.aspx");
                        break;
                    case R.id.sign_up:
                        this.myWebView.loadUrl("http://alosboiya.com.sa/registration.aspx");
                        break;
                    case R.id.sign_in:
                        this.myWebView.loadUrl("http://alosboiya.com.sa/login.aspx");
                        break;
                    case R.id.exit :
                        alartExit();
                        break;
                    case R.id.share :
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.setType("text/plain");
                        intent.putExtra("android.intent.extra.SUBJECT", "share app");
                        intent.putExtra("android.intent.extra.TEXT", "Download our application from: http://alosboiya.com.sa/");
                        startActivity(Intent.createChooser(intent, "Sharing Meppro Application using: "));
                        break;

                    default:
                        break;
                }
            }else
          alart();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);

        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (intent != null) {
                    if (mFileUploadCallbackFirst != null) {
                        mFileUploadCallbackFirst.onReceiveValue(intent.getData());
                        mFileUploadCallbackFirst = null;
                    }
                    else if (mFileUploadCallbackSecond != null) {
                        Uri[] dataUris;
                        try {
                            dataUris = new Uri[] { Uri.parse(intent.getDataString()) };
                        }
                        catch (Exception e) {
                            dataUris = null;
                        }
                        mFileUploadCallbackSecond.onReceiveValue(dataUris);
                        mFileUploadCallbackSecond = null;
                    }
                }
            }
            else {
                if (mFileUploadCallbackFirst != null) {
                    mFileUploadCallbackFirst.onReceiveValue(null);
                    mFileUploadCallbackFirst = null;
                } else if (mFileUploadCallbackSecond != null) {
                    mFileUploadCallbackSecond.onReceiveValue(null);
                    mFileUploadCallbackSecond = null;
                }
            }}
    }

}
