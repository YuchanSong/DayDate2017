package kr.go.seoul.apiair.mail;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kr.go.seoul.apiair.R;

public class MailActivity extends AppCompatActivity
{
    private GMailSender m;

    EditText et_content;
    EditText et_title;
    EditText send;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        Button btn_send = (Button) this.findViewById(R.id.btn_send);
        et_content = (EditText) findViewById(R.id.et_content);
        et_title = (EditText) findViewById(R.id.et_title);
        send = (EditText) findViewById(R.id.send);

        send.setFocusable(false);

        btn_send.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {

                GMailSender sender = new GMailSender("tpals24@gmail.com", "rcbawzrxjigzwyvr"); // SUBSTITUTE

                if (android.os.Build.VERSION.SDK_INT > 9)
                {

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();

                    StrictMode.setThreadPolicy(policy);

                }
                try
                {
                    sender.sendMail(et_title.getText().toString(), // subject.getText().toString(),
                            et_content.getText().toString(), // body.getText().toString(),
                            "tpals24@gmail.com", // from.getText().toString(),
                            "tpals24@gmail.com" // to.getText().toString()
                    );
                    toast();
                } catch (Exception e)
                {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }
        });
    }

    public void toast()
    {
        Toast.makeText(this, "전송되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }
}