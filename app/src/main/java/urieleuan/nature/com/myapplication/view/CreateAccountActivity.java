package urieleuan.nature.com.myapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import urieleuan.nature.com.myapplication.R;

/**
 * Created by Uriel on 22/05/17.
 */

public class CreateAccountActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activity);

        showToolbar(getResources().getString(R.string.toolbar_tittle_createaccount), true);
    }
    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
