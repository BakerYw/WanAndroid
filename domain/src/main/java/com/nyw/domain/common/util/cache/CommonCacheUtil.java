package com.nyw.domain.common.util.cache;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.Utils;

import java.io.File;
import java.text.DecimalFormat;

/**
 * @author BakerJ
 * @date 2018/3/29
 */
public class CommonCacheUtil {
    public static final String CACHE_DIR = "MamaCache";
    private static float MB = 1024 * 1024 * 1.00f;

    public static String getCacheSize() {
        long size = FileUtils.getDirLength(Utils.getApp().getCacheDir()) + getWebCacheSize();
        float sizeFloat = size * 1.00f / MB;
        if (sizeFloat < 0.01) {
            return "0M";
        }
        return new DecimalFormat("0.00").format(sizeFloat) + "M";
    }

    private static long getWebCacheSize() {
        long size = 0;
        File fileApp = Utils.getApp().getCacheDir().getParentFile();
        File[] files = fileApp.listFiles();
        for (File file : files) {
            if (file.getName().contains("web")) {
                size += file.isDirectory() ? FileUtils.getDirLength(file) : file.length();
            }
        }
        File database = new File(fileApp, "databases");
        if (database.exists()) {
            File[] databaseFiles = database.listFiles();
            for (File file : databaseFiles) {
                if (file.getName().contains("sonic")) {
                    size += file.isDirectory() ? FileUtils.getDirLength(file) : file.length();
                }
            }
        }
        return size;
    }

    public static void clearCache() {
        File[] files = Utils.getApp().getCacheDir().listFiles();
        if (files == null || files.length <= 0) return;
        for (File file : files) {
            if (file.isDirectory()) {
                FileUtils.deleteDir(file);
            } else {
                FileUtils.deleteFile(file);
            }
        }
        clearWebCache();
    }

    public static void clearWebCache() {
        File fileApp = Utils.getApp().getCacheDir().getParentFile();
        File[] files = fileApp.listFiles();
        for (File file : files) {
            if (file.getName().contains("web")) {
                if (file.isDirectory()) {
                    FileUtils.deleteDir(file);
                } else {
                    FileUtils.deleteFile(file);
                }
            }
        }
        File database = new File(fileApp, "databases");
        if (database.exists()) {
            File[] databaseFiles = database.listFiles();
            for (File file : databaseFiles) {
                if (file.getName().contains("sonic")) {
                    if (file.isDirectory()) {
                        FileUtils.deleteDir(file);
                    } else {
                        FileUtils.deleteFile(file);
                    }
                }
            }
        }
    }

    public static String getCachePath() {
        File file = new File(Utils.getApp().getCacheDir(), CACHE_DIR);
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getPath();
    }
}
