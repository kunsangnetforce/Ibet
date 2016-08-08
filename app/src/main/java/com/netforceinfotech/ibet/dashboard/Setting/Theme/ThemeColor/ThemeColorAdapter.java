package com.netforceinfotech.ibet.dashboard.Setting.Theme.ThemeColor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * Created by John on 7/29/2016.
 */
public class ThemeColorAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    SettingHolder viewHolder;
    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    Activity context;
    ArrayList<Integer> setting_icon = new ArrayList<>();

    UserSessionManager userSessionManager;



    public ThemeColorAdapter(Activity context,ArrayList<Integer> imagelist)
    {
        this.context = context;
        this.setting_icon = imagelist;
        inflater = LayoutInflater.from(context);
        userSessionManager = new UserSessionManager(context);
    }

    /*  @Override
      public int getItemViewType(int position) {
          if (itemList.get(position).image.isEmpty()) {
              return SIMPLE_TYPE;
          } else {
              return IMAGE_TYPE;
          }
      }
    */


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {


        View view = inflater.inflate(R.layout.row_theme, parent, false);

        viewHolder = new SettingHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {

        Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(),setting_icon.get(position));
        viewHolder.image_icon.setImageBitmap(bitmap);
       // new_decode();

    }

    private void showMessage(String s)
    {

        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();


    }


    @Override
    public int getItemCount()
    {
        if (setting_icon == null)
            return 0;

        return setting_icon.size();

    }


    public class SettingHolder  extends RecyclerView.ViewHolder implements  View.OnClickListener
    {

        ImageView image_icon;
        MaterialRippleLayout materialRippleLayout;
        View view;
        FrameLayout delete_layout ;

        public SettingHolder(View itemView)
        {
            super(itemView);
            //implementing onClickListener

            view = itemView;

            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);

            image_icon = (ImageView)  itemView.findViewById(R.id.setting_list_icon);

            image_icon.setOnClickListener(this);


        }

        @Override
        public void onClick(View view)
        {

            if(getAdapterPosition() == 0)
            {

                userSessionManager.setTheme(0);
            }
            else  if(getAdapterPosition() == 1)
            {
                userSessionManager.setTheme(1);
            }
            else  if(getAdapterPosition() == 2)
            {
                userSessionManager.setTheme(2);
            }
            else  if(getAdapterPosition() == 3)
            {
                userSessionManager.setTheme(3);
            }
            else  if(getAdapterPosition() == 4)
            {
                userSessionManager.setTheme(4);
            }



            context.finish();
            Intent intent  = new Intent(context, Dashboard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);


        }
    }

    public static Bitmap new_decode(File f) {

        // decode image size

        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        o.inDither = false; // Disable Dithering mode

        o.inPurgeable = true; // Tell to gc that whether it needs free memory,
        // the Bitmap can be cleared

        o.inInputShareable = true; // Which kind of reference will be used to
        // recover the Bitmap data after being
        // clear, when it will be used in the future
        try {
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Find the correct scale value. It should be the power of 2.
        final int REQUIRED_SIZE = 300;
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 1.5 < REQUIRED_SIZE && height_tmp / 1.5 < REQUIRED_SIZE)
                break;
            width_tmp /= 1.5;
            height_tmp /= 1.5;
            scale *= 1.5;
        }

        // decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        // o2.inSampleSize=scale;
        o.inDither = false; // Disable Dithering mode

        o.inPurgeable = true; // Tell to gc that whether it needs free memory,
        // the Bitmap can be cleared

        o.inInputShareable = true; // Which kind of reference will be used to
        // recover the Bitmap data after being
        // clear, when it will be used in the future
        // return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        try {

//          return BitmapFactory.decodeStream(new FileInputStream(f), null,
//                  null);
            Bitmap bitmap= BitmapFactory.decodeStream(new FileInputStream(f), null, null);
            System.out.println(" IW " + width_tmp);
            System.out.println("IHH " + height_tmp);
            int iW = width_tmp;
            int iH = height_tmp;

            return Bitmap.createScaledBitmap(bitmap, iW, iH, true);

        } catch (OutOfMemoryError e) {
            // TODO: handle exception
            e.printStackTrace();
            // clearCache();

            // System.out.println("bitmap creating success");
            System.gc();
            return null;
            // System.runFinalization();
            // Runtime.getRuntime().gc();
            // System.gc();
            // decodeFile(f);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }





}

