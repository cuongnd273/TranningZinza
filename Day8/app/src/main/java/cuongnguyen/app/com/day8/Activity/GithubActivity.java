package cuongnguyen.app.com.day8.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import cuongnguyen.app.com.day8.AsynTask.SearchUserGithub;
import cuongnguyen.app.com.day8.Model.UserGithub;
import cuongnguyen.app.com.day8.R;

public class GithubActivity extends AppCompatActivity implements View.OnClickListener{
    EditText search_login;
    Button search;
    ImageView image;
    TextView created,updated;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);
        getControls();
    }
    public void getControls(){
        search_login= (EditText) findViewById(R.id.search_login);
        search= (Button) findViewById(R.id.search);
        image= (ImageView) findViewById(R.id.avatar);
        created= (TextView) findViewById(R.id.create_at);
        updated=(TextView)findViewById(R.id.updated_at);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.search){
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            if(search_login.getText().length()==0)
                Toast.makeText(GithubActivity.this,"Nhập thông tin cần tìm",Toast.LENGTH_SHORT).show();
            else
            {
                SearchUserGithub searchUserGithub= (SearchUserGithub) new SearchUserGithub(GithubActivity.this, new SearchUserGithub.SearchFinish() {
                    @Override
                    public void finish(UserGithub user) {
                        if(user==null){
                            Toast.makeText(GithubActivity.this, "Không tìm thấy kết quả", Toast.LENGTH_SHORT).show();
                            image.setImageResource(0);
                            created.setText("");
                            updated.setText("");
                        }else{
                            Picasso.with(GithubActivity.this).load(user.getAvatar()).into(image);
                            created.setText("Created at: "+user.getCreated());
                            updated.setText("Updated at: "+user.getUpdated());
                        }
                    }
                }).execute(search_login.getText().toString());
            }
        }
    }
}
