package com.nyw.libwidgets.photopicker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;

import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.nyw.libwidgets.R;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yalantis.ucrop.UCrop;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.SelectionCreator;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;

import static android.app.Activity.RESULT_OK;

/**
 * @author BakerJ
 * @date 2018/4/17
 */
public class CBPhotoPicker {
    public static final int REQUEST_CODE_PHOTO_WITH_CROP = 1000, REQUEST_CODE_CROP = 1001,
            REQUEST_CODE_PHOTO = 1002;

    @SuppressLint("CheckResult")
    public static void pickPhoto(RxPermissions rxPermissions, Activity activity, int
            maxSelectable) {
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission
                .READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        callMatisse(Matisse.from(activity), maxSelectable, REQUEST_CODE_PHOTO);
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void pickPhotoWithCrop(RxPermissions rxPermissions, Activity activity) {
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission
                .READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        callMatisse(Matisse.from(activity), 1, REQUEST_CODE_PHOTO_WITH_CROP);
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void pickPhotoWithCrop(RxPermissions rxPermissions, Fragment fragment) {
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission
                .READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        callMatisse(Matisse.from(fragment), 1, REQUEST_CODE_PHOTO_WITH_CROP);
                    }
                });
    }

    private static void callMatisse(Matisse matisse, int maxSelectable, int request) {
        SelectionCreator selectionCreator = matisse.choose(MimeType.of(MimeType.JPEG, MimeType.PNG))
                .countable(false)
                .capture(true)
                .captureStrategy(new CaptureStrategy(true, AppUtils.getAppPackageName() + "" +
                        ".fileProvider"))
                .theme(R.style.PhotoPicker)
                .maxSelectable(maxSelectable)
                .imageEngine(new MatisseImageEngine());
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            selectionCreator.restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        selectionCreator.forResult(request);
    }

    @SuppressLint("CheckResult")
    public static void onActivityResult(int requestCode, int resultCode, Intent data, Activity
            activity, OnPhotoPickListener listener) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_PHOTO) {
                List<Uri> selected = Matisse.obtainResult(data);
                List<String> paths = new ArrayList<>();
                for (Uri uri : selected) {
                    paths.add(com.nyw.libwidgets.utils.file.FileUtils.getRealPathFromUri(activity,
                            uri));
                }
                Flowable.fromIterable(paths)
                        .doOnNext(s -> FileUtils.createOrExistsDir(activity.getCacheDir() +
                                "/pics"))
                        .map(imgs -> Luban.with(activity).load(imgs)
                                .ignoreBy(100)
                                .setTargetDir(activity.getCacheDir() + "/pics").get())
                        .flatMap(Flowable::fromIterable)
                        .map(File::getPath).toList().toFlowable()
                        .map(pathList -> {
                            String[] pathArray = new String[pathList.size()];
                            pathList.toArray(pathArray);
                            return pathArray;
                        })
                        .onErrorReturn(throwable -> new String[0])
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(listener::getPhoto);
            }
        }
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data, Activity
            activity, OnCropListener listener) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_PHOTO_WITH_CROP) {
                List<Uri> selected = Matisse.obtainResult(data);
                Uri destinationUri = Uri.fromFile(new File(activity.getCacheDir(), "/headCrop" +
                        System.currentTimeMillis() / 1000 + ".jpg"));
                UCrop.Options options = new UCrop.Options();
                options.setHideBottomControls(true);
                options.setToolbarColor(Color.WHITE);
                options.setLogoColor(Color.BLACK);
                options.setToolbarWidgetColor(Color.BLACK);
                UCrop.of(selected.get(0), destinationUri)
                        .withOptions(options)
                        .withAspectRatio(1, 1)
                        .withMaxResultSize(300, 300)
                        .start(activity);
            } else if (requestCode == UCrop.REQUEST_CROP) {
                Uri resultUri = UCrop.getOutput(data);
                if (resultUri != null) {
                    listener.getPhoto(resultUri.getPath());
                } else {
                    listener.getPhotoFailed();
                }
            }
        } else {
            listener.getPhotoFailed();
        }
    }

    public interface OnCropListener {
        void getPhoto(String photoPath);

        void getPhotoFailed();
    }

    public interface OnPhotoPickListener {
        void getPhoto(String[] photoPaths);
    }
}
