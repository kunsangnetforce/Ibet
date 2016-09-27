package com.netforceinfotech.ibet.profilesetting;

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
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.profilesetting.tutorial.DefaultIntro;
import com.netforceinfotech.ibet.profilesetting.selectteam.SelectTeamActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ProfileSettingActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private Intent intent;
    private MaterialDialog dialog;
    private static final int TAKE_PHOTO_CODE = 108;
    private static final String IMAGE_DIRECTORY_NAME = "ray";
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int PICK_IMAGE = 109;
    ImageView imageViewDP;
    private Uri fileUri;
    private String filePath;
    UserSessionManager userSessionManager;
    public static ArrayList<String> arrayListTeamids = new ArrayList<>();
    LinearLayout linearLayoutProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);
        context = this;
        userSessionManager = new UserSessionManager(context);
        if (!userSessionManager.getIsFirstTime()) {
            Intent intent = new Intent(this, Dashboard.class);
            startActivity(intent);
            finish();
        }
        getPermission();

        imageViewDP = (ImageView) findViewById(R.id.imageViewDP);

        findViewById(R.id.buttonSkip).setOnClickListener(this);
        findViewById(R.id.buttonFavTeam).setOnClickListener(this);
        findViewById(R.id.buttonDone).setOnClickListener(this);
        findViewById(R.id.imageViewDP).setOnClickListener(this);
        findViewById(R.id.rippleText).setOnClickListener(this);
        linearLayoutProgress = (LinearLayout) findViewById(R.id.linearLayoutProgress);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSkip:
              /*  userSessionManager.setIsFirstTime(false);
                intent = new Intent(context, Dashboard.class);
                startActivity(intent);
                finish();*/
                intent = new Intent(context, DefaultIntro.class);
                startActivity(intent);
                break;
            case R.id.buttonFavTeam:
                intent = new Intent(context, SelectTeamActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonDone:
                linearLayoutProgress.setVisibility(View.VISIBLE);
                String id = userSessionManager.getCustomerId();
                uploadImage1(id);
                userSessionManager.setIsFirstTime(false);
                break;
            case R.id.rippleProPic:
                showEditPicPopup();
                break;
            case R.id.imageViewDP:
                showEditPicPopup();
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

    private void uploadImage1(String id) {
        String url = getResources().getString(R.string.url);
        String uploadurl = "/services.php?opt=update_profile&customer_id=" + id;
        String teams = TextUtils.join(",", arrayListTeamids);
        url = url + uploadurl;
        Log.i("result_url", url);
        Log.i("result_url", filePath + "   " + teams);
        RequestQueue queue = Volley.newRequestQueue(context);
        SimpleMultiPartRequest request = new SimpleMultiPartRequest(
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                linearLayoutProgress.setVisibility(View.GONE);
                Log.i("result_multi", response);
                JsonParser jsonParser = new JsonParser();
                try {
                    JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
                    if (jsonObject.get("status").getAsString().equalsIgnoreCase("success")) {
                        showMessage("Successfully uploaded");
                        Intent intent = new Intent(context, DefaultIntro.class);
                        startActivity(intent);
                    } else {
                        showMessage("failed to upload");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showMessage("Something went wrong");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("result_multi", "error");
                error.printStackTrace();
                showMessage("something went wrong");

            }
        });
        if (filePath != null) {
            request.addFile("image", filePath);
        } else {
            request.addFile("image", "");
        }
        request.addMultipartParam("team", "text/plain", teams);
        queue.add(request);

    }

    private void uploadImage(String id) {
        //https://netforcesales.com/ibet_admin/api/services.php?opt=update_profile&customer_id=46
        String url = getResources().getString(R.string.url);
        String uploadurl = "/services.php?opt=update_profile&customer_id=" + id;
        String teams = TextUtils.join(",", arrayListTeamids);
        url = url + uploadurl;
        Log.i("result_url", url);
        Log.i("result_url", filePath + "   " + teams);
        File file = null;
        setHeader();
        if (filePath == null || filePath.length() == 0) {
            filePath = "";
            Ion.with(context)
                    .load(url)
                    .setMultipartParameter("team", teams)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            if (result == null) {
                                showMessage("nothing is happening");
                            } else {
                                Log.i("result_kunsang", result.toString());
                                String status = result.get("status").getAsString();
                                if (status.equalsIgnoreCase("success")) {
                                    showMessage("Successfully uploaded");
                                    Intent intent = new Intent(context, Dashboard.class);
                                    startActivity(intent);
                                } else {
                                    showMessage("Failed to upload data");
                                }
                            }
                        }
                    });
        } else {
            file = new File(filePath);
            Ion.with(context)
                    .load(url)
                    .setMultipartFile("image", "image/*", file)
                    .setMultipartParameter("team", teams)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            if (result == null) {
                                showMessage("nothing is happening");
                            } else {
                                Log.i("result_kunsang", result.toString());
                                String status = result.get("status").getAsString();
                                if (status.equalsIgnoreCase("success")) {
                                    showMessage("Successfully uploaded");
                                    Intent intent = new Intent(context, Dashboard.class);
                                    startActivity(intent);
                                } else {
                                    showMessage("Failed to upload data");
                                }
                            }
                        }
                    });
        }


    }

    private void showMessage(String s) {
        Toast.makeText(ProfileSettingActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    private void pickPictureIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    private void takePictureIntent() {
        UserSessionManager userSessionManager = new UserSessionManager(getApplicationContext());
        String name = userSessionManager.getName();
        Intent cameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        filePath = fileUri.getPath();

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
    }

    private void showEditPicPopup() {
        boolean wrapInScrollView = true;
        dialog = new MaterialDialog.Builder(this)
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
        dialog.findViewById(R.id.linearLayoutGalary).setOnClickListener(ProfileSettingActivity.this);
        dialog.findViewById(R.id.linearLayoutPicture).setOnClickListener(ProfileSettingActivity.this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO_CODE:
                if (resultCode == RESULT_OK) {
                    Log.i("result picture", "clicked");
                    imageViewDP.setImageURI(fileUri);
                }
                break;
            case PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    filePath = getPath(uri);
                    try {
                        // imageViewDP.setImageBitmap(decodeUri(uri));
                        Bitmap myBitmap = BitmapFactory.decodeFile(filePath);
                        imageViewDP.setImageBitmap(myBitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.i("result picture", "picked");
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
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
        Cursor cursor = managedQuery(uri, projection, null, null, null);
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
                getContentResolver().openInputStream(selectedImage), null, o);

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
                getContentResolver().openInputStream(selectedImage), null, o2);
    }

    private void getPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            String[] permission = {
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE

            };

            ActivityCompat.requestPermissions(this,
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


    private void setHeader() {
        final String appkey = getResources().getString(R.string.appkey);
        Ion.getDefault(context).getHttpClient().insertMiddleware(new AsyncHttpClientMiddleware() {
            @Override
            public void onRequest(OnRequestData data) {
                data.request.setHeader("APPKEY", appkey);
            }

            @Override
            public Cancellable getSocket(GetSocketData data) {
                return null;
            }

            @Override
            public boolean exchangeHeaders(OnExchangeHeaderData data) {
                return false;
            }

            @Override
            public void onRequestSent(OnRequestSentData data) {

            }

            @Override
            public void onHeadersReceived(OnHeadersReceivedDataOnRequestSentData data) {

            }

            @Override
            public void onBodyDecoder(OnBodyDataOnRequestSentData data) {

            }

            @Override
            public void onResponseComplete(OnResponseCompleteDataOnRequestSentData data) {

            }
        });
    }
}
