package com.fishuman.facebookkey;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Base64;
import android.widget.TextView;

public class GenKeyHashActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     
        TextView khView = (TextView)findViewById(R.id.keyhash);
        khView.setText(genKeyHash());
    }
    
	private String genKeyHash() {
		PackageInfo pkgInfo;
		try {
			PackageManager localPackageManager = getPackageManager();
			String str = getApplicationInfo().packageName;
			pkgInfo = localPackageManager.getPackageInfo(str, 64);
			
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-1");
				byte[] arrayOfByte1 = pkgInfo.signatures[0].toByteArray();
				digest.update(arrayOfByte1);
				
				byte[] bHash = digest.digest();
				
				return Base64.encodeToString(bHash, Base64.DEFAULT);
				
			} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
				localNoSuchAlgorithmException.printStackTrace();
			}
		} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
			localNameNotFoundException.printStackTrace();
		}
		
		return null;
	}
}