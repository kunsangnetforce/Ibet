package com.netforceinfotech.ibet.dashboard.profile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.Debugger.Debugger;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.dashboard.profile.selectteam.SelectTeamActivity;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private static final int TAKE_PHOTO_CODE = 108;
    private static final String IMAGE_DIRECTORY_NAME = "ray";
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int PICK_IMAGE = 109;

    CoordinatorLayout coordinatorLayout;
    int theme;
    CircleImageView circleImageViewDp;
    TextView textViewName, textViewLevel, textViewWin, textViewLose, textViewLevelNumber;
    Context context;
    private UserSessionManager userSessionManager;
    private MaterialDialog dialog;
    private Uri fileUri;
    private String filePath;
    Button buttonSetTeam, buttonDone;
    private View view1;
    private MaterialDialog progressDialog;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();
        context = getActivity();
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        Dashboard.title.setText(getString(R.string.profile));
        setupLayout(view);
        getPermission();
        setupTheme();
        setupBackground();
        getProfile();
        return view;
    }

    private void getProfile() {
        progressDialog.show();
        //https://netforcesales.com/ibet_admin/api/services.php?opt=get_home_by_userid&custid=137
        String baseUrl = getString(R.string.url);
        final String profileUrl = "/services.php?opt=get_home_by_userid&custid=" + userSessionManager.getCustomerId();
        String url = baseUrl + profileUrl;
        Debugger.i("kunsangprofile", url);
        setupSelfSSLCert();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        progressDialog.dismiss();
                        if (result == null) {
                            showMessage("Something is wrong");
                        } else {
                            Log.i("kunsangresult", result.toString());
                            if (result.get("status").getAsString().equalsIgnoreCase("success")) {
                                setupProfile(result);
                            } else {
                                showMessage("Could not set team. Try again");
                            }
                        }
                    }
                });


    }

    private void setupProfile(JsonObject result) {
        JsonArray data = result.getAsJsonArray("data");
        JsonObject jsonObject = data.get(0).getAsJsonObject();
        String name, profile_image, level, levelNumber, wins, losses;
        name = jsonObject.get("name").getAsString();
        profile_image = jsonObject.get("profile_image").getAsString();
        wins = jsonObject.get("wincount").getAsString();
        losses = jsonObject.get("losscount").getAsString();
        levelNumber = jsonObject.get("cust_level").getAsString();
        level = getString(R.string.beginner);
        try {
            Glide.with(context).load(profile_image).error(R.drawable.ic_error).into(circleImageViewDp);
        } catch (Exception ex) {
            Glide.with(context).load(R.drawable.ic_error).into(circleImageViewDp);
        }
        textViewName.setText(name);
        textViewLevel.setText(level);
        textViewLevelNumber.setText(levelNumber);
        textViewLose.setText(losses);
        textViewWin.setText(wins);

    }


    private void setupLayout(View view) {
        progressDialog = new MaterialDialog.Builder(context)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0).build();
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);
        view1 = view.findViewById(R.id.view);
        textViewLevel = (TextView) view.findViewById(R.id.textViewLevel);
        textViewLevelNumber = (TextView) view.findViewById(R.id.textViewLevelNumber);
        textViewName = (TextView) view.findViewById(R.id.textViewName);
        textViewWin = (TextView) view.findViewById(R.id.textViewWins);
        textViewLose = (TextView) view.findViewById(R.id.textViewLosses);
        circleImageViewDp = (CircleImageView) view.findViewById(R.id.circleImageViewDp);
        buttonSetTeam = (Button) view.findViewById(R.id.buttonSetTeam);
        buttonDone = (Button) view.findViewById(R.id.buttonDone);
        circleImageViewDp.setOnClickListener(this);
        buttonDone.setOnClickListener(ProfileFragment.this);
        buttonSetTeam.setOnClickListener(ProfileFragment.this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.circleImageViewDp:
                showEditPicPopup();
                break;
            case R.id.buttonSetTeam:
                Intent intent = new Intent(getActivity(), SelectTeamActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonDone:
                if (filePath == null) {
                    showMessage("Image not picked");
                } else {
                    uploadImage1();
                }
                break;
            case R.id.linearLayoutPicture:
                takePictureIntent();
                dialog.dismiss();
                break;
            case R.id.linearLayoutGalary:
                pickPictureIntent();
                dialog.dismiss();
                break;

        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    public void setupSelfSSLCert() {
        final Trust trust = new Trust();
        final TrustManager[] trustmanagers = new TrustManager[]{trust};
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustmanagers, new SecureRandom());
            Ion.getInstance(context, "rest").getHttpClient().getSSLSocketMiddleware().setTrustManagers(trustmanagers);
            Ion.getInstance(context, "rest").getHttpClient().getSSLSocketMiddleware().setSSLContext(sslContext);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (final KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private static class Trust implements X509TrustManager {

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

    }

    private void showEditPicPopup() {
        boolean wrapInScrollView = true;
        dialog = new MaterialDialog.Builder(context)
                .title(R.string.editpic)
                .customView(R.layout.editpic, wrapInScrollView)
                .negativeText(R.string.cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
        dialog.findViewById(R.id.linearLayoutGalary).setOnClickListener(this);
        dialog.findViewById(R.id.linearLayoutPicture).setOnClickListener(this);

    }

    public String getPath(Uri uri) {
        // just some safety built in
        if (uri == null) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }

    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(
                context.getContentResolver().openInputStream(selectedImage), null, o);

        final int REQUIRED_SIZE = 100;

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(
                context.getContentResolver().openInputStream(selectedImage), null, o2);
    }

    private void getPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            String[] permission = {
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE

            };

            ActivityCompat.requestPermissions(getActivity(),
                    permission, 1);


        }
    }

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            } else {
                Log.d(IMAGE_DIRECTORY_NAME, mediaStorageDir.getAbsolutePath());
            }

        } else {
            Log.d(IMAGE_DIRECTORY_NAME, mediaStorageDir.getAbsolutePath());
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile = null;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
            Log.i("result imagepath", mediaFile.getAbsolutePath());
        } else {
        }

        return mediaFile;
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private void pickPictureIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    private void takePictureIntent() {
        UserSessionManager userSessionManager = new UserSessionManager(context);
        String name = userSessionManager.getName();
        Intent cameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        filePath = fileUri.getPath();

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO_CODE:
                if (resultCode == getActivity().RESULT_OK) {
                    Log.i("result picture", "clicked");
                    Glide.with(context).load(fileUri).into(circleImageViewDp);
                }
                break;
            case PICK_IMAGE:
                if (resultCode == getActivity().RESULT_OK) {

                    if (data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = context.getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);

                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String filePath = cursor.getString(columnIndex);
                        String path = new File(filePath).getAbsolutePath();
//                        circleImageViewDp.setImageBitmap(BitmapFactory.decodeFile(path));
                        File f = new File(path);
                        this.filePath = f.getPath();
                        Glide.with(context).load(f).into(circleImageViewDp);
                        Log.i("kunsangfilepath", filePath);
                        cursor.close();
                    } else {
                        Toast.makeText(getActivity(), "Try Again!!", Toast.LENGTH_SHORT)
                                .show();
                    }
                    Log.i("result picture", "picked");
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadImage1() {
        progressDialog.show();
        String url = getResources().getString(R.string.url);
        String uploadurl = "/services.php?opt=update_profile&customer_id=" + userSessionManager.getCustomerId();
        url = url + uploadurl;
        Log.i("result_url", url);
        File file = null;
        if (filePath == null) {
            showMessage("No Image Selected");
        } else {
            file = savebitmap(filePath);
            if (file == null) {
                showMessage("Image not valid");
                return;
            }
            Ion.with(context)
                    .load(url)
                    .setHeader("ENCTYPE", "multipart/form-data")
                    .setTimeout(60 * 60 * 1000)
                    .setMultipartFile("upload", "image/*", file)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            progressDialog.dismiss();
                            if (result == null) {
                                showMessage("nothing is happening");
                            } else {
                                Log.i("result_kunsang", result.toString());
                                String status = result.get("status").getAsString();
                                if (status.equalsIgnoreCase("success")) {
                                    showMessage("Profile edited successfully");
                                }
                            }
                        }
                    });
        }

    }

    private File savebitmap(String filePath) {
        File file = new File(filePath);
        String extension = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, bmOptions);
        OutputStream outStream = null;
        try {
            // make a new bitmap from your file
            outStream = new FileOutputStream(file);
            if (extension.equalsIgnoreCase("png")) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, outStream);
            } else if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg")) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, outStream);
            } else {
                return null;
            }
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;


    }

    private void setupTheme() {
        int theme = userSessionManager.getTheme();
        switch (theme) {
            case 0:
                //  setupDefaultTheme();
                break;
            case 1:
                setupBrownTheme();
                break;
            case 2:
                setupPurlpleTheme();
                break;
            case 3:
                setupGreenTheme();
                break;
            case 4:
                setupMarronTheme();
                break;
            case 5:
                setupLightBlueTheme();
                break;
        }
    }

    private void setupBrownTheme() {
        textViewWin.setBackgroundResource(R.drawable.circular_bg_brown);
        textViewLose.setBackgroundResource(R.drawable.circular_bg_brown);
        textViewLevelNumber.setBackgroundResource(R.drawable.circular_bg_brown);
        textViewLevel.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        buttonSetTeam.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
    }

    private void setupPurlpleTheme() {
        textViewWin.setBackgroundResource(R.drawable.circular_bg_purlple);
        textViewLose.setBackgroundResource(R.drawable.circular_bg_purlple);
        textViewLevelNumber.setBackgroundResource(R.drawable.circular_bg_purlple);
        textViewLevel.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        buttonSetTeam.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
    }

    private void setupGreenTheme() {
        textViewWin.setBackgroundResource(R.drawable.circular_bg_green);
        textViewLose.setBackgroundResource(R.drawable.circular_bg_green);
        textViewLevelNumber.setBackgroundResource(R.drawable.circular_bg_green);
        textViewLevel.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        buttonSetTeam.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
    }

    private void setupMarronTheme() {
        textViewWin.setBackgroundResource(R.drawable.circular_bg_marron);
        textViewLose.setBackgroundResource(R.drawable.circular_bg_marron);
        textViewLevelNumber.setBackgroundResource(R.drawable.circular_bg_marron);
        textViewLevel.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        buttonSetTeam.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
    }

    private void setupLightBlueTheme() {
        textViewWin.setBackgroundResource(R.drawable.circular_bg_lightblue);
        textViewLose.setBackgroundResource(R.drawable.circular_bg_lightblue);
        textViewLevelNumber.setBackgroundResource(R.drawable.circular_bg_lightblue);
        textViewLevel.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        buttonSetTeam.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
    }

    private void setupDefaultTheme() {
        textViewWin.setBackgroundResource(R.drawable.circular_bg);
        textViewLose.setBackgroundResource(R.drawable.circular_bg);
        textViewLevelNumber.setBackgroundResource(R.drawable.circular_bg);
        textViewLevel.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        buttonSetTeam.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
    }

    private void setupBackground() {

        switch (userSessionManager.getBackground()) {
            case 0:
                coordinatorLayout.setBackgroundResource(R.drawable.blue240);
                break;
            case 1:
                coordinatorLayout.setBackgroundResource(R.drawable.france240);
                break;
            case 2:
                coordinatorLayout.setBackgroundResource(R.drawable.soccer240);
                break;
            case 3:
                coordinatorLayout.setBackgroundResource(R.drawable.spain240);
                break;
            case 4:
                coordinatorLayout.setBackgroundResource(R.drawable.uk240);
                break;
            case 5:
                view1.setVisibility(View.GONE);
                break;
        }
    }
}
