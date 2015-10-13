package io.github.countdooku.fbi_prank_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;


public class MenuActivity extends AppCompatActivity {

    private final static int SELECT_PHOTO = 12345;

    //Shared Preferences specific variables to set the selected : imageView4
    public static final String MyPREFERENCES = "MyPre";
    public static final String  key = "nameKey";
    SharedPreferences sharedpreferences ;
    Bitmap btmap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set layout to PORTRAIT mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //set app icon atop the ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        //Shared Preferences, initialize for the selected : imageView4
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(key)) {

            String u = sharedpreferences.getString(key, "");
            btmap = decodeBase64(u);

            //resize the bitmap value "btmap" && set it to imageView4 upon onCreate()
            //Why is Runnable necessary?
            // - imageView4 must be called in Runnable, using .post (or else compiler will assert height/width of 0 value for pre-selected image)
            // - this is so, because the layout wherein which the ImageView is contained MUST load first
            // - if the layout doesn't load first, our ImageView width/height has a 0 value, and app will crash with:
            // - "IllegalArgumentException: width and height must be > 0 while loading Bitmap from View"
            final ImageView iv4 = (ImageView)findViewById(R.id.imageView4);
            iv4.post(new Runnable() {
                @Override
                public void run() {

                    //get bitmap width && height
                    int bmWidth = btmap.getWidth();
                    int bmHeight = btmap.getHeight();
                    //get imageView4 width && height
                    int ivWidth = iv4.getWidth();
                    int ivHeight = iv4.getHeight();
                    //reset values
                    int new_width = ivWidth;
                    int new_height = ivHeight;

                    //scale selected image from Image Gallery to fit predefined width/height for the imageView
                    new_height = (int) Math.floor((double) bmHeight *((double) new_height / (double) bmHeight));
                    new_width = (int) Math.floor((double) bmWidth *((double) new_width / (double) bmWidth));

                    Bitmap newbitMap = Bitmap.createScaledBitmap(btmap, new_width, new_height, true);

                    //set bitmap to imageView4
                    iv4.setImageBitmap(newbitMap);

                }
            });

        }


        //set ImageView[x] values
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setOnClickListener(onClickListener);
        ImageView imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView2.setOnClickListener(onClickListener);
        ImageView imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView3.setOnClickListener(onClickListener);

        //TARGET is #4
        ImageView imageView4 = (ImageView)findViewById(R.id.imageView4);
        imageView4.setOnClickListener(onClickListener);
        //set onLongClick for Target imageView : send to gallery/photo selection aspect
        imageView4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                return true;
            }
        });

        ImageView imageView5 = (ImageView)findViewById(R.id.imageView5);
        imageView5.setOnClickListener(onClickListener);
        ImageView imageView6 = (ImageView)findViewById(R.id.imageView6);
        imageView6.setOnClickListener(onClickListener);
        ImageView imageView7 = (ImageView)findViewById(R.id.imageView7);
        imageView7.setOnClickListener(onClickListener);
        ImageView imageView8 = (ImageView)findViewById(R.id.imageView8);
        imageView8.setOnClickListener(onClickListener);
        ImageView imageView9 = (ImageView)findViewById(R.id.imageView9);
        imageView9.setOnClickListener(onClickListener);
        ImageView imageView10 = (ImageView)findViewById(R.id.imageView10);
        imageView10.setOnClickListener(onClickListener);


        //set TextView[x] values
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setOnClickListener(onClickListener);
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setOnClickListener(onClickListener);
        TextView textView3 = (TextView)findViewById(R.id.textView3);
        textView3.setOnClickListener(onClickListener);

        //TARGET is #4
        TextView textView4 = (TextView)findViewById(R.id.textView4);
        textView4.setOnClickListener(onClickListener);
        //set onLongClick for Target textView : go to configure Timer XML
        textView4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //RECEIVER : from MenuActivity to TimerActivity
                Intent GotoB = new Intent(MenuActivity.this, TimerActivity.class);
                GotoB.putExtra("finisher", new ResultReceiver(null) {
                    @Override
                    protected void onReceiveResult(int resultCode, Bundle resultData) {
                        MenuActivity.this.finish();
                    }
                });
                startActivityForResult(GotoB, 1);
                return true;

            }
        });

        TextView textView5 = (TextView)findViewById(R.id.textView5);
        textView5.setOnClickListener(onClickListener);
        TextView textView6 = (TextView)findViewById(R.id.textView6);
        textView6.setOnClickListener(onClickListener);
        TextView textView7 = (TextView)findViewById(R.id.textView7);
        textView7.setOnClickListener(onClickListener);
        TextView textView8 = (TextView)findViewById(R.id.textView8);
        textView8.setOnClickListener(onClickListener);
        TextView textView9 = (TextView)findViewById(R.id.textView9);
        textView9.setOnClickListener(onClickListener);
        TextView textView10 = (TextView)findViewById(R.id.textView10);
        textView10.setOnClickListener(onClickListener);

    }

    //for all imageView and textView elements
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.imageView:
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("https://www.fbi.gov/wanted/topten/jason-derek-brown"));
                    startActivity(intent);
                    break;
                case R.id.textView:
                    Intent intentT1 = new Intent();
                    intentT1.setAction(Intent.ACTION_VIEW);
                    intentT1.addCategory(Intent.CATEGORY_BROWSABLE);
                    intentT1.setData(Uri.parse("https://www.fbi.gov/wanted/topten/jason-derek-brown"));
                    startActivity(intentT1);
                    break;
                case R.id.imageView2:
                    Intent intent2 = new Intent();
                    intent2.setAction(Intent.ACTION_VIEW);
                    intent2.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent2.setData(Uri.parse("https://www.fbi.gov/wanted/topten/glen-stewart-godwin"));
                    startActivity(intent2);
                    break;
                case R.id.textView2:
                    Intent intentT2 = new Intent();
                    intentT2.setAction(Intent.ACTION_VIEW);
                    intentT2.addCategory(Intent.CATEGORY_BROWSABLE);
                    intentT2.setData(Uri.parse("https://www.fbi.gov/wanted/topten/glen-stewart-godwin"));
                    startActivity(intentT2);
                    break;
                case R.id.imageView3:
                    Intent intent3 = new Intent();
                    intent3.setAction(Intent.ACTION_VIEW);
                    intent3.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent3.setData(Uri.parse("https://www.fbi.gov/wanted/topten/yaser-abdel-said"));
                    startActivity(intent3);
                    break;
                case R.id.textView3:
                    Intent intentT3 = new Intent();
                    intentT3.setAction(Intent.ACTION_VIEW);
                    intentT3.addCategory(Intent.CATEGORY_BROWSABLE);
                    intentT3.setData(Uri.parse("https://www.fbi.gov/wanted/topten/yaser-abdel-said"));
                    startActivity(intentT3);
                    break;


                //TARGET is #4 : set imageView4 && textView4 to MovieActivity.class controller
                case R.id.imageView4:
                    //send to custom-selected video by prankster
                    Intent imageVideo = new Intent(MenuActivity.this, MovieActivity.class);
                    startActivity(imageVideo);
                    break;
                case R.id.textView4:
                    //send to custom-selected video by prankster
                    Intent textVideo = new Intent(MenuActivity.this, MovieActivity.class);
                    startActivity(textVideo);
                    break;


                case R.id.imageView5:
                    Intent intent5 = new Intent();
                    intent5.setAction(Intent.ACTION_VIEW);
                    intent5.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent5.setData(Uri.parse("https://www.fbi.gov/wanted/topten/fidel-urbina"));
                    startActivity(intent5);
                    break;
                case R.id.textView5:
                    Intent intentT5 = new Intent();
                    intentT5.setAction(Intent.ACTION_VIEW);
                    intentT5.addCategory(Intent.CATEGORY_BROWSABLE);
                    intentT5.setData(Uri.parse("https://www.fbi.gov/wanted/topten/fidel-urbina"));
                    startActivity(intentT5);
                    break;
                case R.id.imageView6:
                    Intent intent6 = new Intent();
                    intent6.setAction(Intent.ACTION_VIEW);
                    intent6.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent6.setData(Uri.parse("https://www.fbi.gov/wanted/topten/eduardo-ravelo"));
                    startActivity(intent6);
                    break;
                case R.id.textView6:
                    Intent intentT6 = new Intent();
                    intentT6.setAction(Intent.ACTION_VIEW);
                    intentT6.addCategory(Intent.CATEGORY_BROWSABLE);
                    intentT6.setData(Uri.parse("https://www.fbi.gov/wanted/topten/eduardo-ravelo"));
                    startActivity(intentT6);
                    break;
                case R.id.imageView7:
                    Intent intent7 = new Intent();
                    intent7.setAction(Intent.ACTION_VIEW);
                    intent7.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent7.setData(Uri.parse("https://www.fbi.gov/wanted/topten/william-bradford-bishop-jr"));
                    startActivity(intent7);
                    break;
                case R.id.textView7:
                    Intent intentT7 = new Intent();
                    intentT7.setAction(Intent.ACTION_VIEW);
                    intentT7.addCategory(Intent.CATEGORY_BROWSABLE);
                    intentT7.setData(Uri.parse("https://www.fbi.gov/wanted/topten/william-bradford-bishop-jr"));
                    startActivity(intentT7);
                    break;
                case R.id.imageView8:
                    Intent intent8 = new Intent();
                    intent8.setAction(Intent.ACTION_VIEW);
                    intent8.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent8.setData(Uri.parse("https://www.fbi.gov/wanted/topten/robert-william-fisher"));
                    startActivity(intent8);
                    break;
                case R.id.textView8:
                    Intent intentT8 = new Intent();
                    intentT8.setAction(Intent.ACTION_VIEW);
                    intentT8.addCategory(Intent.CATEGORY_BROWSABLE);
                    intentT8.setData(Uri.parse("https://www.fbi.gov/wanted/topten/robert-william-fisher"));
                    startActivity(intentT8);
                    break;
                case R.id.imageView9:
                    Intent intent9 = new Intent();
                    intent9.setAction(Intent.ACTION_VIEW);
                    intent9.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent9.setData(Uri.parse("https://www.fbi.gov/wanted/topten/alexis-flores"));
                    startActivity(intent9);
                    break;
                case R.id.textView9:
                    Intent intentT9 = new Intent();
                    intentT9.setAction(Intent.ACTION_VIEW);
                    intentT9.addCategory(Intent.CATEGORY_BROWSABLE);
                    intentT9.setData(Uri.parse("https://www.fbi.gov/wanted/topten/alexis-flores"));
                    startActivity(intentT9);
                    break;
                case R.id.imageView10:
                    Intent intent10 = new Intent();
                    intent10.setAction(Intent.ACTION_VIEW);
                    intent10.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent10.setData(Uri.parse("https://www.fbi.gov/wanted/topten/victor-manuel-gerena"));
                    startActivity(intent10);
                    break;
                case R.id.textView10:
                    Intent intentT10 = new Intent();
                    intentT10.setAction(Intent.ACTION_VIEW);
                    intentT10.addCategory(Intent.CATEGORY_BROWSABLE);
                    intentT10.setData(Uri.parse("https://www.fbi.gov/wanted/topten/victor-manuel-gerena"));
                    startActivity(intentT10);
                    break;
            }

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //resize selected image for imageView4 and set it into place on the UI
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null) {
            //read selected image's Uri
            Uri pickedImage = data.getData();
            //read and set path of selected image
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));


            //a Shared Preferences specific code-bit to set values for the selected : imageView4
            int columnIndex = cursor.getColumnIndex(filePath[0]);
            String picturePath = cursor.getString(columnIndex);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

            //instantiate the path of the desired imageView
            ImageView imageView4 = (ImageView)findViewById(R.id.imageView4);

            //get bitmap width && height
            int bmWidth = bitmap.getWidth();
            int bmHeight = bitmap.getHeight();
            //get imageView4 width && height
            int ivWidth = imageView4.getWidth();
            int ivHeight = imageView4.getHeight();
            //reset values
            int new_width = ivWidth;
            int new_height = ivHeight;

            //scale selected image from Image Gallery to fit predefined width/height for the imageView
            new_height = (int) Math.floor((double) bmHeight *((double) new_height / (double) bmHeight));
            new_width = (int) Math.floor((double) bmWidth *((double) new_width / (double) bmWidth));

            Bitmap newbitMap = Bitmap.createScaledBitmap(bitmap, new_width, new_height, true);

            //reset value
            bitmap = newbitMap;

            //set bitmap to desired imageView on UI
            imageView4.setImageBitmap(bitmap);

            //a Shared Preferences specific code-bit to commit data for the selected : imageView4
            btmap = BitmapFactory.decodeFile(picturePath); //decode method called
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(key, encodeTobase64(btmap));
            editor.commit();

            //mandatory
            cursor.close();

        }
    }


    //a Shared Preferences specific method encode for imageView4
    public static String encodeTobase64(Bitmap image) {

        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;

    }

    //a Shared Preferences specific method decode for imageView4
    public static Bitmap decodeBase64(String input) {

        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);

    }

}