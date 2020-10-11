package com.pluralsight.candycoded;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //load image
        Uri uri = Uri.parse(getString(R.string.value_uri_image) + R.drawable.store_front);
        ImageView candyStoreImageView = (ImageView)findViewById(R.id.image_view_candy_store);
        Picasso.with(this).
                load(uri).
                into(candyStoreImageView);

    }

    /**
     * to create and lanch map
     * @param view android.view.View
     */
    public void createMapIntent(View view){
        // Create a Uri from the address and passing in the String with the geo location of our store.
        Uri mapUri = Uri.parse("geo:0,0?q=618 E South St Orlando, FL 32801");

        // Create an Intent and pass the action: Intent.ACTION_VIEW and the Uri we just created.
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);

        // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

        //check that the result is not equal to null
        if(mapIntent.resolveActivity(getPackageManager()) != null){
            // Attempt to start an activity that can handle the Intent
            startActivity(mapIntent);
        }
    }


    // ***
    // TODO - Task 3 - Launch the Phone Activity
    // ***

}
