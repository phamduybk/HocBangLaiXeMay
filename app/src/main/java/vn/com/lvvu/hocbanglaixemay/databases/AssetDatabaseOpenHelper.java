package vn.com.lvvu.hocbanglaixemay.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import vn.com.lvvu.hocbanglaixemay.MyApplication;

/**
 * Created by levan on 7/17/2019.
 */

public class AssetDatabaseOpenHelper {

    private static final String DB_NAME = "thibanglaixemay.db";

    private Context context;

    private static AssetDatabaseOpenHelper assetDatabaseOpenHelper;

    public static AssetDatabaseOpenHelper getInstance() {
        if (assetDatabaseOpenHelper == null) {
            assetDatabaseOpenHelper = new AssetDatabaseOpenHelper(MyApplication.getAppContext());
        }
        return assetDatabaseOpenHelper;
    }

    private AssetDatabaseOpenHelper(Context context) {
        this.context = context;
    }

    public SQLiteDatabase openDatabaseForRead() {
        File dbFile = context.getDatabasePath(DB_NAME);

        if (!dbFile.exists()) {
            try {
                String DB_PATH;
                if (android.os.Build.VERSION.SDK_INT >= 17)
                    DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
                else
                    DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
                copyDatabase(DB_PATH);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);
    }

    private void copyDatabase(String DB_PATH) throws IOException {
        InputStream mInput = context.getAssets().open(DB_NAME);
        File file = new File(DB_PATH + DB_NAME);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        OutputStream mOutput = new FileOutputStream(file);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

}
