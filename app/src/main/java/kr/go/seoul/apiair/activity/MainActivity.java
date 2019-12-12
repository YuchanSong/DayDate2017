package kr.go.seoul.apiair.activity;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import kr.go.seoul.apiair.R;

public class MainActivity extends Activity implements View.OnClickListener {

    private String key = "4b4d6443477379633231686d535077";          //인증키
    private String SERVICE = "ListAirQualityByDistrictService";    //오픈API이름
    private String TYPE = "xml";
    private final long FINSH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    Document doc = null;
    String[] Gu = new String[25];
    String renew = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView btn_start = (ImageView) findViewById(R.id.btn_start);
        btn_start.setImageResource(R.drawable.pre);

        GetXMLTask task = new GetXMLTask();
        task.execute("http://openapi.seoul.go.kr:8088/4b4d6443477379633231686d535077/xml/ListAirQualityByDistrictService/1/30/");

        findViewById(R.id.btn_start).setOnClickListener(this);

    }

    //    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this)
//                .setIcon(R.drawable.close_icon)
//                .setTitle("데이데이트 종료")
//                .setMessage("\n\t\t다음에 또 이용해 주실꺼죠?")
//                .setPositiveButton("종료", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                })
//                .setNegativeButton("돌아가기", null)
//                .show();
//    }
    @Override
    public void onBackPressed() {
        //Back키 두번 클릭으로 어플 종료
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;
        if (0 <= intervalTime && FINSH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast toast = Toast.makeText(getApplicationContext(), "뒤로 버튼을 한 번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            toast.show();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                Intent intent = new Intent(this, FirstActivity.class);
                Intent intent2 = getIntent();
                intent.putExtra("db", Gu[9]);
                intent.putExtra("ddm", Gu[5]);
                intent.putExtra("dj", Gu[19]);
                intent.putExtra("ep", Gu[11]);
                intent.putExtra("gb", Gu[8]);
                intent.putExtra("gc", Gu[17]);
                intent.putExtra("gd", Gu[24]);
                intent.putExtra("gj", Gu[4]);
                intent.putExtra("gn", Gu[22]);
                intent.putExtra("gr", Gu[16]);
                intent.putExtra("gs", Gu[15]);
                intent.putExtra("gw", Gu[20]);
                intent.putExtra("jg", Gu[1]);
                intent.putExtra("jor", Gu[0]);
                intent.putExtra("jr", Gu[6]);
                intent.putExtra("mp", Gu[13]);
                intent.putExtra("nw", Gu[10]);
                intent.putExtra("sb", Gu[7]);
                intent.putExtra("sc", Gu[21]);
                intent.putExtra("sd", Gu[3]);
                intent.putExtra("sdm", Gu[12]);
                intent.putExtra("sp", Gu[23]);
                intent.putExtra("yc", Gu[14]);
                intent.putExtra("ydp", Gu[18]);
                intent.putExtra("ys", Gu[2]);
                intent.putExtra("time", renew);

                String userID = intent2.getStringExtra("userID");
                intent.putExtra("userID", userID);
                String userPassword = intent2.getStringExtra("userPassword");
                intent.putExtra("userPassword", userPassword);
                String userName = intent2.getStringExtra("userName");
                intent.putExtra("userName", userName);
                String userAge = intent2.getStringExtra("userAge");
                intent.putExtra("userAge", userAge);
                startActivity(intent);
                break;
        }
    }

    //private inner class extending AsyncTask
    private class GetXMLTask extends AsyncTask<String, Void, Document> {

        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder(); //XML문서 빌더 객체를 생성
                doc = db.parse(new InputSource(url.openStream())); //XML문서를 파싱한다.
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }

            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {
            String[] s = null;
            s = new String[25];
            //row태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
            NodeList nodeList = doc.getElementsByTagName("row");
            //row 태그를 가지는 노드를 찾음, 계층적인 노드 구조를 반환

            for (int i = 0; i < nodeList.getLength(); i++) {
                //날씨 데이터를 추출
                Node node = nodeList.item(i); //row엘리먼트 노드
                Element fstElmnt = (Element) node;
                NodeList msrstenameList = fstElmnt.getElementsByTagName("MSRSTENAME");
                Element nameElement = (Element) msrstenameList.item(0);
                msrstenameList = nameElement.getChildNodes();

                NodeList msrdateList = fstElmnt.getElementsByTagName("MSRDATE");
                renew = msrdateList.item(0).getChildNodes().item(0).getNodeValue();

                //지역
                s[i] += ";" + ((Node) msrstenameList.item(0)).getNodeValue() + ";";

                //정도
                NodeList gradeList = fstElmnt.getElementsByTagName("GRADE");
                s[i] += gradeList.item(0).getChildNodes().item(0).getNodeValue() + ";";

                //NITROGEN 이산화질소
                NodeList nitrogenList = fstElmnt.getElementsByTagName("NITROGEN");
                s[i] += nitrogenList.item(0).getChildNodes().item(0).getNodeValue() + ";";

                //OZONE 오존
                NodeList ozoneList = fstElmnt.getElementsByTagName("OZONE");
                s[i] += ozoneList.item(0).getChildNodes().item(0).getNodeValue() + ";";

                //CARBON 일산화탄소
                NodeList carbonList = fstElmnt.getElementsByTagName("CARBON");
                s[i] += carbonList.item(0).getChildNodes().item(0).getNodeValue() + ";";

                //SULFUROUS	아황산가스
                NodeList sulfurousList = fstElmnt.getElementsByTagName("SULFUROUS");
                s[i] += sulfurousList.item(0).getChildNodes().item(0).getNodeValue() + ";";

                //PM10 미세먼지
                NodeList pm10List = fstElmnt.getElementsByTagName("PM10");
                s[i] += pm10List.item(0).getChildNodes().item(0).getNodeValue() + ";";

                //PM25 초미세먼지
                NodeList pm25List = fstElmnt.getElementsByTagName("PM25");
                s[i] += pm25List.item(0).getChildNodes().item(0).getNodeValue() + ";";

            }
            System.arraycopy(s, 0, Gu, 0, s.length);
        }
    }
}