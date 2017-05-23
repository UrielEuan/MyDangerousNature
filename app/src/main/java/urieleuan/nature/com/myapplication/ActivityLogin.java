package urieleuan.nature.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import urieleuan.nature.com.myapplication.view.ContainerActivity;
import urieleuan.nature.com.myapplication.view.CreateAccountActivity;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void goCreateAccount(View view){
        Intent intent=new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }
    public void goContainer(View view) {
        Intent intent=new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }
}
