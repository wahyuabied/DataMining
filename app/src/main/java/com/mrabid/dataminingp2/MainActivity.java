package com.mrabid.dataminingp2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Bitmap[] band= new Bitmap[100];
    ArrayList<ArrayList<Integer>> greys = new ArrayList<>();
    Button load;
    RecyclerView rcyImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();


        band[0] = BitmapFactory.decodeResource(getResources(), R.drawable.gb1);
        band[1] = BitmapFactory.decodeResource(getResources(), R.drawable.gb2);
        band[2] = BitmapFactory.decodeResource(getResources(), R.drawable.gb3);
        band[3] = BitmapFactory.decodeResource(getResources(), R.drawable.gb4);
        band[4] = BitmapFactory.decodeResource(getResources(), R.drawable.gb5);
        band[5] = BitmapFactory.decodeResource(getResources(), R.drawable.gb7);

        for(int i=0;i<band.length;i++)
            if(band[i]!=null)
                greys.add(greyScale(band[i]));

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 5);
            }
        });

    }

    public ArrayList<Integer> greyScale(Bitmap bitmap){
        ArrayList <Integer> grey = new ArrayList<>();
        int red =0, blue = 0, green = 0, pixel=0;
        for (int y = 0; y < bitmap.getHeight(); y++)
        {
            for (int x = 0; x < bitmap.getWidth(); x++)
            {
                int c = bitmap.getPixel(x, y);
                pixel++;
                red = Color.red(c);
                green = Color.green(c);
                blue= Color.blue(c);
                grey.add((red+green+blue)/3);
            }
        }

        return grey;
    }

    public ArrayList<ArrayList<Integer>> clustering(ArrayList<ArrayList<Integer>> data){
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (requestCode == 5) {
                Toast.makeText(this, "Load berhasil", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void initialize(){
        load = findViewById(R.id.bt_loadImage);
        rcyImage = findViewById(R.id.rcy_image);
    }
}
