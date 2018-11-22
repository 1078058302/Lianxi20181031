package soexample.umeng.com.lianxi20181031;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.jump2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Message> list = new ArrayList<>();
                Message message = new Message();
                message.setMsg("Fanzhuopu");
                list.add(message);
                EventBus.getDefault().post(list);
            }
        });
    }
}
