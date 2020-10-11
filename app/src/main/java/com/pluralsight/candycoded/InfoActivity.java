package com.pluralsight.candycoded;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import java.net.URI;

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
     * to create intent and lanch map
     * @param view android.view.View, to attach to the Click Listener on the TextView
     */
    public void createMapIntent(View view){

        // Create a Uri from the address and passing in the String with the geo location of our store.
        Uri mapUri = Uri.parse("geo:0,0?q=618 E South St Orlando, FL 32801");

        // Create an Intent and pass the action: Intent.ACTION_VIEW and the Uri we just created.
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);

        // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

        // Check that the result is not equal to null
        if(mapIntent.resolveActivity(getPackageManager()) != null){
            // Attempt to start an activity that can handle the Intent
            startActivity(mapIntent);
        }
    }

    /**
     * to create intent and lanch phone
     * @param view android.view.View to attach, to the Click Listener on the TextView
     */
    public void createPhoneIntent(View view){

        // Create an Intent with action Intent.ACTION_DIAL
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);

        // Create a URI with the Uri.parse() method and pass the telephone number "tel:0123456789".
        Uri phoneUri = Uri.parse("tel:0123456789");

        // Use the Intent setData() method and pass the Uri we just created.
        phoneIntent.setData(phoneUri);

        // Start the Activity with the Intent.
        startActivity(phoneIntent);


    }

}
