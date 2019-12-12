package kr.go.seoul.apiair.activity;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import kr.go.seoul.apiair.R;

public class SupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        this.setTitle("데이 데이트");

        Button btn1, btn2;
        ImageView Image1, Image2, Image3, Image4, Image5;
        final ViewFlipper vFlipper;

        btn1 = (Button) findViewById(R.id.btn1); // 이전
        btn2 = (Button) findViewById(R.id.btn2); // 다음

        Image1 = (ImageView) findViewById(R.id.image1);
        Image2 = (ImageView) findViewById(R.id.image2);
        Image3 = (ImageView) findViewById(R.id.image3);
        Image4 = (ImageView) findViewById(R.id.image4);
        Image5 = (ImageView) findViewById(R.id.image5);

        vFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vFlipper.showPrevious();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vFlipper.showNext();
            }
        });

    }
}
