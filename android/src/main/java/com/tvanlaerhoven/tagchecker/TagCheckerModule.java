package com.tvanlaerhoven.tagchecker;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.UIImplementation;
import com.facebook.react.uimanager.UIManagerModule;

public class TagCheckerModule extends ReactContextBaseJavaModule {

    public static final String TAGCHECKER_MODULE = "RNTagCheckerModule";

    private UIManagerModule mUIManager = null;
    private UIImplementation mUIImplementation = null;
    private final ReactApplicationContext mContext;

    public TagCheckerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Override
    public String getName() {
        return TAGCHECKER_MODULE;
    }

    /**
     * Invoked by React to create a new node with a given tag has its properties changed.
     */
    @ReactMethod
    public void updateView(int tag, String className, ReadableMap props) {
        try {
            if (mUIManager == null) {
                mUIManager = mContext.getNativeModule(UIManagerModule.class);
                mUIImplementation = mUIManager.getUIImplementation();
            }
            mUIImplementation.updateView(tag, className, props);
        } catch (IllegalViewOperationException ignore) {
            Log.w(TAGCHECKER_MODULE, "Ignoring invalid view tag " + tag);
        }
    }
}
