package com.pluralsight.candycoded;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.pluralsight.candycoded.DB.CandyContract;
import com.pluralsight.candycoded.DB.CandyContract.CandyEntry;
import com.pluralsight.candycoded.DB.CandyDbHelper;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public static final String SHARE_DESCRIPTION = "Look at this delicious candy from Candy Coded - ";
    public static final String HASHTAG_CANDYCODED = " #candycoded";
    String mCandyImageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = DetailActivity.this.getIntent();

        if (intent != null && intent.hasExtra(getString(R.string.text_position))) {
            // get position from intent
            int position = intent.getIntExtra(getString(R.string.text_position), 0);

            // fetch candy object in DB
            CandyDbHelper dbHelper = new CandyDbHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM candy", null);
            cursor.moveToPosition(position);


            String candyName = cursor.getString(cursor.getColumnIndexOrThrow(
                    CandyContract.CandyEntry.COLUMN_NAME_NAME));
            String candyPrice = cursor.getString(cursor.getColumnIndexOrThrow(
                    CandyEntry.COLUMN_NAME_PRICE));
            mCandyImageUrl = cursor.getString(cursor.getColumnIndexOrThrow(
                    CandyEntry.COLUMN_NAME_IMAGE));
            String candyDesc = cursor.getString(cursor.getColumnIndexOrThrow(
                    CandyEntry.COLUMN_NAME_DESC));

            // set candy name in textView
            TextView textView = (TextView) this.findViewById(R.id.text_view_name);
            textView.setText(candyName);

            // set candy price in textView
            TextView textViewPrice = (TextView) this.findViewById(R.id.text_view_price);
            textViewPrice.setText(candyPrice);

            // set candy description in textView
            TextView textViewDesc = (TextView) this.findViewById(R.id.text_view_desc);
            textViewDesc.setText(candyDesc);

            // set candy image in ImageView
            ImageView imageView = (ImageView) this.findViewById(
                    R.id.image_view_candy);
            Picasso.with(this).load(mCandyImageUrl).into(imageView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {

        this.createShareIntent();

        return super.onOptionsItemSelected(item);
    }

    /**
     * to create intent and share it
     */
    private void createShareIntent(){

        // Create an Intent with action Intent.ACTION_SEND
        Intent shareIntent = new Intent(Intent.ACTION_SEND);

        // Set the type
        shareIntent.setType("text/plain");

        // Create the String that we want to share
        String concatenated = SHARE_DESCRIPTION + mCandyImageUrl + HASHTAG_CANDYCODED;

        // Use the Intent's putExtra() method to add the text we want to share.
        shareIntent.putExtra(Intent.EXTRA_TEXT,concatenated);

        // to start an activity that can handle the Intent
        startActivity(shareIntent);
    }

}
