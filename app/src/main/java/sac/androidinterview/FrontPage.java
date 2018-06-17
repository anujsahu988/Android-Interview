package sac.androidinterview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class FrontPage extends AppCompatActivity implements View.OnClickListener{

    Button bsimple, btough,bexit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_page);

        //codes to add Action Bar

        LinearLayout front_ll=(LinearLayout)findViewById(R.id.front_page_titlebar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.frontpage_title_bar);

        bsimple=(Button)findViewById(R.id.bsq);
        btough=(Button)findViewById(R.id.btq);
        bexit=(Button)findViewById(R.id.bexit);


        bsimple.setOnClickListener(this);
        btough.setOnClickListener(this);
        bexit.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.bsq:

                Intent i=new Intent(FrontPage.this,Simple_question.class);
                startActivity(i);
                break;
            case R.id.btq:

                Intent j=new Intent(FrontPage.this,Tough_question.class);
                startActivity(j);
                break;

            case R.id.bexit:

                finish();
                System.exit(0);

        }
    }
}
