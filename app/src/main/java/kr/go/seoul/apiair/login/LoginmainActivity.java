package kr.go.seoul.apiair.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import kr.go.seoul.apiair.R;
import kr.go.seoul.apiair.activity.FirstActivity;
import kr.go.seoul.apiair.activity.MainActivity;

public class LoginmainActivity extends AppCompatActivity {
    private AlertDialog dialog;
    String userID = null;
    String userPassword = null;
    String userName = null;
    String userAge = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmain);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText ageText = (EditText) findViewById(R.id.ageText);
        final TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);

        Button managementButton = (Button) findViewById(R.id.loginButton);
        Button modifiedButton = (Button) findViewById(R.id.modifiedButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        userPassword = intent.getStringExtra("userPassword");
        userName = intent.getStringExtra("userName");
        userAge = intent.getStringExtra("userAge");
        String message = "회원정보 수정";

        idText.setText(userID);
        passwordText.setText(null);
        nameText.setText(userName);
        ageText.setText(userAge);
        welcomeMessage.setText(message);

        idText.setFocusable(false);

        if (!userID.equals("admin")) {
            managementButton.setVisibility(View.GONE);
        }

        modifiedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String userName = nameText.getText().toString();
//                Integer userAge = Integer.parseInt(ageText.getText().toString());
                String userAge = ageText.getText().toString();

                if (userID.equals("") || userPassword.equals("") || userName.equals("") || userAge.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginmainActivity.this);
                    dialog = builder.setMessage("빈 칸 없이 입력해주세요.")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }

                if (Integer.parseInt(userAge) < 1 || Integer.parseInt(userAge) > 120) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginmainActivity.this);
                    dialog = builder.setMessage("나이는 1세부터 120세까지 입력가능합니다.")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginmainActivity.this);
                                dialog = builder.setMessage("회원 수정에 성공했습니다.")
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                finish();
                                            }
                                        })
                                        .create();
                                dialog.show();
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginmainActivity.this);
                                dialog = builder.setMessage("회원 수정에 실패했습니다.")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                UpdateRequest updateRequest = new UpdateRequest(userPassword, userName, Integer.parseInt(userAge), userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginmainActivity.this);
                queue.add(updateRequest);

                Intent intent2 = new Intent(LoginmainActivity.this, MainActivity.class);
                intent2.putExtra("userID", userID);
                intent2.putExtra("userPassword", userPassword);
                intent2.putExtra("userName", userName);
                intent2.putExtra("userAge", userAge);

                startActivity(intent2);
           }
        });

        managementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BackgroundTask().execute();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();
            }
        });
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target = "http://uchanism24.cafe24.com/List.php";

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result) {
            Intent intent = new Intent(LoginmainActivity.this, ManagementActivity.class);
            intent.putExtra("userList", result);
            LoginmainActivity.this.startActivity(intent);
        }
    }
}
