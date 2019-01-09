package app.Online966.com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by egypt2 on 09-Jan-19.
 */
public class JavaScriptInterface extends Activity {
    Context mContext;

    /** Instantiate the interface and set the context */
    JavaScriptInterface(Context c) {
        mContext = c;
    }

    @android.webkit.JavascriptInterface
    public void changeActivity()
    {
        Intent i = new Intent(JavaScriptInterface.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}

