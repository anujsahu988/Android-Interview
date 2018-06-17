package sac.androidinterview;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Tough_question extends AppCompatActivity implements View.OnClickListener {


    TextView tvquestion,tvanswer,tvtotallength_yy,tvpresentindex_xx;

    Button bleft,bshow,bright;

    String[] tough_question,tough_answwers;

    int index;

    private static final String default_answer="Press \"A\" button for the answer";

    //Variables $ objects of TTS
    TextToSpeech ttsobject;
    int result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        //codes to add Action Bar

        LinearLayout questions_ll=(LinearLayout)findViewById(R.id.question_page_titlebar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.questions_title_bar);

        Button bspeak=(Button)findViewById(R.id.bspeak);
        Button bstop_mute=(Button)findViewById(R.id.bstop_mute);
        TextView tv_category=(TextView)findViewById(R.id.tv_questions_titlebar);
        tv_category.setText("Tough Questions");


        //Initialization of textview
        tvquestion=(TextView)findViewById(R.id.tvquestion);
        tvanswer=(TextView)findViewById(R.id.tvanswer);
        tvpresentindex_xx=(TextView)findViewById(R.id.tvxx);
        tvtotallength_yy=(TextView)findViewById(R.id.tvyy);

        //Initialization of button
        bleft=(Button)findViewById(R.id.bleft);
        bshow=(Button)findViewById(R.id.bshowanswer);
        bright=(Button)findViewById(R.id.bright);

        // Importing the string array from values folder
        tough_question=getResources().getStringArray(R.array.difficult_ques);
        tough_answwers=getResources().getStringArray(R.array.difficult_ans);

        //OnClickListener for 3 buttons and also speak and mute buttons
        bleft.setOnClickListener(this);
        bshow.setOnClickListener(this);
        bright.setOnClickListener(this);
        bspeak.setOnClickListener(this);
        bstop_mute.setOnClickListener(this);

        //setting values to our variables and 4 textviews
        index=0;
        tvquestion.setText(tough_question[index]);
        tvanswer.setText("Press \"A\" button for the answer");
        tvpresentindex_xx.setText(String.valueOf(index+1));
        tvtotallength_yy.setText("/"+String.valueOf(tough_question.length));

        //TTS object and listener initilization
        ttsobject=new TextToSpeech(Tough_question.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                if(i==TextToSpeech.SUCCESS)
                {
                    result=ttsobject.setLanguage(Locale.US);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Feature not Supported in Your Device",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.bleft:
                tvanswer.setText("Press \"A\" button for the answer");
                index--;

                //It makes our array to circular array
                if(index==-1){

                    index=tough_question.length-1;
                    tvquestion.setText(tough_question[index]);
                    tvpresentindex_xx.setText(String.valueOf(index+1));
                }
                else {
                    tvquestion.setText(tough_question[index]);
                    tvpresentindex_xx.setText(String.valueOf(index + 1));
                }

                break;

            case R.id.bshowanswer:
                tvanswer.setText(tough_answwers[index]);

                break;

            case R.id.bright:
                tvanswer.setText("Press \"A\" button for the answer");
                index++;

                if(index==tough_question.length)
                {
                    index=0;
                    tvquestion.setText(tough_question[index]);
                    tvpresentindex_xx.setText(String.valueOf(index+1));
                }
                else {
                    tvquestion.setText(tough_question[index]);
                    tvpresentindex_xx.setText(String.valueOf(index + 1));

                }

                break;

            case R.id.bspeak:
            {
                if(result==TextToSpeech.LANG_NOT_SUPPORTED||result==TextToSpeech.LANG_MISSING_DATA)
                {
                    Toast.makeText(getApplicationContext(),"Feature not Supported in Your Device",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(tvanswer.getText().toString().equals(default_answer)){

                    }else {
                        ttsobject.speak(tough_answwers[index],TextToSpeech.QUEUE_FLUSH,null);
                    }
                }}
            break;
            case R.id.bstop_mute:

                if(ttsobject!=null){
                    ttsobject.stop();
                }

                break;
                }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(ttsobject!=null)
        {
            ttsobject.stop();
            ttsobject.shutdown();
        }
    }
}
