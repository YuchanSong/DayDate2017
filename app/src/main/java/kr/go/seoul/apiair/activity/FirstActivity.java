package kr.go.seoul.apiair.activity;

import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;

import kr.go.seoul.apiair.R;
import kr.go.seoul.apiair.location.DoBong;
import kr.go.seoul.apiair.location.DongDae;
import kr.go.seoul.apiair.location.DongJak;
import kr.go.seoul.apiair.location.EunPyeong;
import kr.go.seoul.apiair.location.GangBook;
import kr.go.seoul.apiair.location.GangDong;
import kr.go.seoul.apiair.location.GangNam;
import kr.go.seoul.apiair.location.GangSeo;
import kr.go.seoul.apiair.location.GummCheon;
import kr.go.seoul.apiair.location.Guro;
import kr.go.seoul.apiair.location.GwanAk;
import kr.go.seoul.apiair.location.GwangJin;
import kr.go.seoul.apiair.location.JongRo;
import kr.go.seoul.apiair.location.JoongGu;
import kr.go.seoul.apiair.location.JoongRang;
import kr.go.seoul.apiair.location.Mapo;
import kr.go.seoul.apiair.location.NoWon;
import kr.go.seoul.apiair.location.SeoCho;
import kr.go.seoul.apiair.location.SeoDae;
import kr.go.seoul.apiair.location.SeongBook;
import kr.go.seoul.apiair.location.SeongDong;
import kr.go.seoul.apiair.location.SongPa;
import kr.go.seoul.apiair.location.YangCheon;
import kr.go.seoul.apiair.location.YeongDeung;
import kr.go.seoul.apiair.location.YongSan;
import kr.go.seoul.apiair.login.LoginActivity;
import kr.go.seoul.apiair.login.LoginmainActivity;
import kr.go.seoul.apiair.mail.MailActivity;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    String[] Gu = new String[25];
    String S1 = "좋음";   //blue
    String S2 = "보통";   //green
    String S3 = "나쁨";   //red
    String userID = null;
    String userPassword = null;
    String userName = null;
    String userAge = null;
    private AlertDialog dialog;
    private final long FINSH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        getSupportActionBar().setElevation(30); // 그림자(높낮이효과)
        getSupportActionBar().setTitle("데이 데이트(in seoul)"); // 타이틀
        getSupportActionBar().setSubtitle("서울시 대기정보와 문화정보"); // 서브타이틀
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_list_black_24dp);

//      햄버거버튼클릭으로 drawer열기
//        Button btn_menu = (Button) findViewById(R.id.btn_menu);
//        btn_menu.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
//                if (!drawer.isDrawerOpen(Gravity.LEFT)) {
//                    drawer.openDrawer(Gravity.LEFT);
//                }
//            }
//        });

        TextView idText = (TextView) findViewById(R.id.idText);
        TextView txt_logout = (TextView) findViewById(R.id.txt_logout);
        Button btn_login = (Button) findViewById(R.id.btn_login);

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        userPassword = intent.getStringExtra("userPassword");
        userName = intent.getStringExtra("userName");
        userAge = intent.getStringExtra("userAge");

        if (userID == null) {
            btn_login.setVisibility(View.VISIBLE);
            idText.setVisibility(View.GONE);
            txt_logout.setVisibility(View.GONE);

        } else {
            idText.setText(userID);
            btn_login.setVisibility(View.GONE);
            idText.setVisibility(View.VISIBLE);
            idText.setText("환영합니다, " + userID + "(" + userName + ")" + "님!");
        }

        Gu[0] = intent.getExtras().getString("jor");
        String JorScale = Gu[0].split(";")[2];
        if (!JorScale.equals(S1)) {
            if (!JorScale.equals(S2)) {
                if (!JorScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_jor)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_jor)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_jor)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_jor)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[1] = intent.getExtras().getString("jg");
        String JgScale = Gu[1].split(";")[2];
        if (!JgScale.equals(S1)) {
            if (!JgScale.equals(S2)) {
                if (!JgScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_jg)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_jg)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_jg)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_jg)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[2] = intent.getExtras().getString("ys");
        String YsScale = Gu[2].split(";")[2];
        if (!YsScale.equals(S1)) {
            if (!YsScale.equals(S2)) {
                if (!YsScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_ys)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_ys)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_ys)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_ys)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[3] = intent.getExtras().getString("sd");
        String SdScale = Gu[3].split(";")[2];
        if (!SdScale.equals(S1)) {
            if (!SdScale.equals(S2)) {
                if (!SdScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_sd)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_sd)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_sd)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_sd)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[4] = intent.getExtras().getString("gj");
        String GjScale = Gu[4].split(";")[2];
        if (!GjScale.equals(S1)) {
            if (!GjScale.equals(S2)) {
                if (!GjScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_gj)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_gj)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_gj)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_gj)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[5] = intent.getExtras().getString("ddm");
        String DdmScale = Gu[5].split(";")[2];
        if (!DdmScale.equals(S1)) {
            if (!DdmScale.equals(S2)) {
                if (!DdmScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_ddm)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_ddm)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_ddm)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_ddm)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[6] = intent.getExtras().getString("jr");
        String JrScale = Gu[6].split(";")[2];
        if (!JrScale.equals(S1)) {
            if (!JrScale.equals(S2)) {
                if (!JrScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_jr)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_jr)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_jr)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_jr)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[7] = intent.getExtras().getString("sb");
        String SbScale = Gu[7].split(";")[2];
        if (!SbScale.equals(S1)) {
            if (!SbScale.equals(S2)) {
                if (!SbScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_sb)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_sb)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_sb)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_sb)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[8] = intent.getExtras().getString("gb");
        String GbScale = Gu[8].split(";")[2];
        if (!GbScale.equals(S1)) {
            if (!GbScale.equals(S2)) {
                if (!GbScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_gb)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_gb)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_gb)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_gb)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[9] = intent.getExtras().getString("db");
        String DbScale = Gu[9].split(";")[2];
        if (!DbScale.equals(S1)) {
            if (!DbScale.equals(S2)) {
                if (!DbScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_db)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_db)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_db)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_db)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[10] = intent.getExtras().getString("nw");
        String NwScale = Gu[10].split(";")[2];
        if (!NwScale.equals(S1)) {
            if (!NwScale.equals(S2)) {
                if (!NwScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_nw)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_nw)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_nw)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_nw)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[11] = intent.getExtras().getString("ep");
        String EpScale = Gu[11].split(";")[2];
        if (!EpScale.equals(S1)) {
            if (!EpScale.equals(S2)) {
                if (!EpScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_ep)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_ep)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_ep)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_ep)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[12] = intent.getExtras().getString("sdm");
        String SdmScale = Gu[12].split(";")[2];
        if (!SdmScale.equals(S1)) {
            if (!SdmScale.equals(S2)) {
                if (!SdmScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_sdm)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_sdm)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_sdm)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_sdm)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[13] = intent.getExtras().getString("mp");
        String MpScale = Gu[13].split(";")[2];
        if (!MpScale.equals(S1)) {
            if (!MpScale.equals(S2)) {
                if (!MpScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_mp)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_mp)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_mp)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_mp)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[14] = intent.getExtras().getString("yc");
        String YcScale = Gu[14].split(";")[2];
        if (!YcScale.equals(S1)) {
            if (!YcScale.equals(S2)) {
                if (!YcScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_yc)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_yc)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_yc)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_yc)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[15] = intent.getExtras().getString("gs");
        String GsScale = Gu[15].split(";")[2];
        if (!GsScale.equals(S1)) {
            if (!GsScale.equals(S2)) {
                if (!GsScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_gs)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_gs)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_gs)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_gs)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[16] = intent.getExtras().getString("gr");
        String GrScale = Gu[16].split(";")[2];
        if (!GrScale.equals(S1)) {
            if (!GrScale.equals(S2)) {
                if (!GrScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_gr)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_gr)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_gr)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_gr)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[17] = intent.getExtras().getString("gc");
        String GcScale = Gu[17].split(";")[2];
        if (!GcScale.equals(S1)) {
            if (!GcScale.equals(S2)) {
                if (!GcScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_gc)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_gc)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_gc)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_gc)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[18] = intent.getExtras().getString("ydp");
        String YdpScale = Gu[18].split(";")[2];
        if (!YdpScale.equals(S1)) {
            if (!YdpScale.equals(S2)) {
                if (!YdpScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_ydp)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_ydp)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_ydp)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_ydp)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[19] = intent.getExtras().getString("dj");
        String DjScale = Gu[19].split(";")[2];
        if (!DjScale.equals(S1)) {
            if (!DjScale.equals(S2)) {
                if (!DjScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_dj)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_dj)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_dj)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_dj)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[20] = intent.getExtras().getString("gw");
        String GwScale = Gu[20].split(";")[2];
        if (!GwScale.equals(S1)) {
            if (!GwScale.equals(S2)) {
                if (!GwScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_gw)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_gw)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_gw)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_gw)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[21] = intent.getExtras().getString("sc");
        String ScScale = Gu[21].split(";")[2];
        if (!ScScale.equals(S1)) {
            if (!ScScale.equals(S2)) {
                if (!ScScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_sc)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_sc)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_sc)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_sc)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[22] = intent.getExtras().getString("gn");
        String GnScale = Gu[22].split(";")[2];
        if (!GnScale.equals(S1)) {
            if (!GnScale.equals(S2)) {
                if (!GnScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_gn)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_gn)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_gn)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_gn)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[23] = intent.getExtras().getString("sp");
        String SpScale = Gu[23].split(";")[2];
        if (!SpScale.equals(S1)) {
            if (!SpScale.equals(S2)) {
                if (!SpScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_sp)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_sp)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_sp)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_sp)).setTextColor(Color.parseColor("#32CD32"));
        }

        Gu[24] = intent.getExtras().getString("gd");
        String GdScale = Gu[24].split(";")[2];
        if (!GdScale.equals(S1)) {
            if (!GdScale.equals(S2)) {
                if (!GdScale.equals(S3)) {
                    ((TextView) findViewById(R.id.txt_gd)).setTextColor(Color.parseColor("#000000"));
                } else {
                    ((TextView) findViewById(R.id.txt_gd)).setTextColor(Color.parseColor("#FF0000"));
                }
            } else {
                ((TextView) findViewById(R.id.txt_gd)).setTextColor(Color.parseColor("#FFA500"));
            }
        } else {
            ((TextView) findViewById(R.id.txt_gd)).setTextColor(Color.parseColor("#32CD32"));
        }

        TextView textView1 = (TextView) findViewById(R.id.textView);
        textView1.setText("DayDate");
        TextView textView2 = (TextView) findViewById(R.id.sub_inform1);
        textView2.setText("최근 갱신 시간 : ");
        TextView textView3 = (TextView) findViewById(R.id.renew);
        String renew_time = intent.getExtras().getString("time");
        textView3.setText(renew_time.substring(0, 4) + "년 " + renew_time.substring(4, 6) + "월 " + renew_time.substring(6, 8) + "일  " + renew_time.substring(8, 10) + "시");

        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.txt_mail).setOnClickListener(this);
        findViewById(R.id.txt_management).setOnClickListener(this);
        findViewById(R.id.txt_support).setOnClickListener(this);
        findViewById(R.id.txt_logout).setOnClickListener(this);
        findViewById(R.id.txt_db).setOnClickListener(this);
        findViewById(R.id.txt_ddm).setOnClickListener(this);
        findViewById(R.id.txt_dj).setOnClickListener(this);
        findViewById(R.id.txt_ep).setOnClickListener(this);
        findViewById(R.id.txt_gb).setOnClickListener(this);
        findViewById(R.id.txt_gc).setOnClickListener(this);
        findViewById(R.id.txt_gd).setOnClickListener(this);
        findViewById(R.id.txt_gj).setOnClickListener(this);
        findViewById(R.id.txt_gn).setOnClickListener(this);
        findViewById(R.id.txt_gr).setOnClickListener(this);
        findViewById(R.id.txt_gs).setOnClickListener(this);
        findViewById(R.id.txt_gw).setOnClickListener(this);
        findViewById(R.id.txt_jg).setOnClickListener(this);
        findViewById(R.id.txt_jor).setOnClickListener(this);
        findViewById(R.id.txt_jr).setOnClickListener(this);
        findViewById(R.id.txt_mp).setOnClickListener(this);
        findViewById(R.id.txt_nw).setOnClickListener(this);
        findViewById(R.id.txt_sb).setOnClickListener(this);
        findViewById(R.id.txt_sc).setOnClickListener(this);
        findViewById(R.id.txt_sd).setOnClickListener(this);
        findViewById(R.id.txt_sdm).setOnClickListener(this);
        findViewById(R.id.txt_sp).setOnClickListener(this);
        findViewById(R.id.txt_yc).setOnClickListener(this);
        findViewById(R.id.txt_ydp).setOnClickListener(this);
        findViewById(R.id.txt_ys).setOnClickListener(this);
    }

    public void shareKakao() {
        //카카오 공유기능
        try {
            final KakaoLink kakaoLink = KakaoLink.getKakaoLink(this);
            final KakaoTalkLinkMessageBuilder kakaoBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

        /*메시지 추가*/
            kakaoBuilder.addText("서울시의 대기정보와 문화 정보를 한눈에 알고 싶다면?");

        /*이미지 가로/세로 사이즈는 80px 보다 커야하며, 이미지 용량은 500kb 이하로 제한된다.*/
            String url = "http://imageshack.com/a/img923/7427/nPChaK.jpg";
            kakaoBuilder.addImage(url, 160, 160);

        /*앱 실행버튼 추가*/
            kakaoBuilder.addAppButton("지금 시작하기");

        /*메시지 발송*/
            kakaoLink.sendMessage(kakaoBuilder, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflater함수를 이용해서 menu 리소스를 menu로 변환.
        // 액션바 호출
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //ActionBar 메뉴 클릭에 대한 이벤트 처리
        String txt = null;
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                //서랍이 닫혀있을 때 왼쪽으로 열기
                if (!drawer.isDrawerOpen(Gravity.LEFT))
                    drawer.openDrawer(Gravity.LEFT);
                // 서랍이 열려있을 때 왼쪽으로 닫기
                if (drawer.isDrawerOpen(Gravity.LEFT))
                    drawer.closeDrawer(Gravity.LEFT);
                break;

            case R.id.login:
                if (userID == null) {
                    startActivity(new Intent(this, LoginActivity.class));
                } else {
                    startActivity(new Intent(this, LoginmainActivity.class).putExtra("userID", userID).putExtra("userPassword", userPassword).putExtra("userName", userName).putExtra("userAge", userAge));
                }
                break;

            case R.id.action_share:
                shareKakao();
                break;

            case R.id.action_inform:
                startActivity(new Intent(this, InformActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onBackPressed() {
//        //어플종료 다이얼로그
//        new android.app.AlertDialog.Builder(this)
//                .setIcon(R.drawable.close_icon)
//                .setTitle("데이데이트 종료")
//                .setMessage("\n\t\t다음에 또 이용해 주실꺼죠?")
//                .setPositiveButton("종료", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        moveTaskToBack(true);
//                        finish();
//                        android.os.Process.killProcess(android.os.Process.myPid());
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
            moveTaskToBack(true);
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
            case R.id.btn_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.txt_mail:
                startActivity(new Intent(this, MailActivity.class));
                break;
            case R.id.txt_management:
                if (userID == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FirstActivity.this);
                    dialog = builder.setMessage("로그인하시면 이용하실 수 있습니다.")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                } else {
                    startActivity(new Intent(this, LoginmainActivity.class).putExtra("userID", userID).putExtra("userPassword", userPassword).putExtra("userName", userName).putExtra("userAge", userAge));
                    break;
                }
            case R.id.txt_support:
                startActivity(new Intent(this, kr.go.seoul.apiair.activity.SupportActivity.class));
                break;
            case R.id.txt_logout:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.txt_db:
                startActivity(new Intent(this, DoBong.class).putExtra("Extra", Gu[9]).putExtra("userID", userID));
                break;
            case R.id.txt_ddm:
                startActivity(new Intent(this, DongDae.class).putExtra("Extra", Gu[5]).putExtra("userID", userID));
                break;
            case R.id.txt_dj:
                startActivity(new Intent(this, DongJak.class).putExtra("Extra", Gu[19]).putExtra("userID", userID));
                break;
            case R.id.txt_ep:
                startActivity(new Intent(this, EunPyeong.class).putExtra("Extra", Gu[11]).putExtra("userID", userID));
                break;
            case R.id.txt_gb:
                startActivity(new Intent(this, GangBook.class).putExtra("Extra", Gu[8]).putExtra("userID", userID));
                break;
            case R.id.txt_gc:
                startActivity(new Intent(this, GummCheon.class).putExtra("Extra", Gu[17]).putExtra("userID", userID));
                break;
            case R.id.txt_gd:
                startActivity(new Intent(this, GangDong.class).putExtra("Extra", Gu[24]).putExtra("userID", userID));
                break;
            case R.id.txt_gj:
                startActivity(new Intent(this, GwangJin.class).putExtra("Extra", Gu[4]).putExtra("userID", userID));
                break;
            case R.id.txt_gn:
                startActivity(new Intent(this, GangNam.class).putExtra("Extra", Gu[22]).putExtra("userID", userID));
                break;
            case R.id.txt_gr:
                startActivity(new Intent(this, Guro.class).putExtra("Extra", Gu[16]).putExtra("userID", userID));
                break;
            case R.id.txt_gs:
                startActivity(new Intent(this, GangSeo.class).putExtra("Extra", Gu[15]).putExtra("userID", userID));
                break;
            case R.id.txt_gw:
                startActivity(new Intent(this, GwanAk.class).putExtra("Extra", Gu[20]).putExtra("userID", userID));
                break;
            case R.id.txt_jg:
                startActivity(new Intent(this, JoongGu.class).putExtra("Extra", Gu[1]).putExtra("userID", userID));
                break;
            case R.id.txt_jor:
                startActivity(new Intent(this, JongRo.class).putExtra("Extra", Gu[0]).putExtra("userID", userID));
                break;
            case R.id.txt_jr:
                startActivity(new Intent(this, JoongRang.class).putExtra("Extra", Gu[6]).putExtra("userID", userID));
                break;
            case R.id.txt_mp:
                startActivity(new Intent(this, Mapo.class).putExtra("Extra", Gu[13]).putExtra("userID", userID));
                break;
            case R.id.txt_nw:
                startActivity(new Intent(this, NoWon.class).putExtra("Extra", Gu[10]).putExtra("userID", userID));
                break;
            case R.id.txt_sb:
                startActivity(new Intent(this, SeongBook.class).putExtra("Extra", Gu[7]).putExtra("userID", userID));
                break;
            case R.id.txt_sc:
                startActivity(new Intent(this, SeoCho.class).putExtra("Extra", Gu[21]).putExtra("userID", userID));
                break;
            case R.id.txt_sd:
                startActivity(new Intent(this, SeongDong.class).putExtra("Extra", Gu[3]).putExtra("userID", userID));
                break;
            case R.id.txt_sdm:
                startActivity(new Intent(this, SeoDae.class).putExtra("Extra", Gu[12]).putExtra("userID", userID));
                break;
            case R.id.txt_sp:
                startActivity(new Intent(this, SongPa.class).putExtra("Extra", Gu[23]).putExtra("userID", userID));
                break;
            case R.id.txt_yc:
                startActivity(new Intent(this, YangCheon.class).putExtra("Extra", Gu[14]).putExtra("userID", userID));
                break;
            case R.id.txt_ydp:
                startActivity(new Intent(this, YeongDeung.class).putExtra("Extra", Gu[18]).putExtra("userID", userID));
                break;
            case R.id.txt_ys:
                startActivity(new Intent(this, YongSan.class).putExtra("Extra", Gu[2]).putExtra("userID", userID));
                break;
        }
    }
}
