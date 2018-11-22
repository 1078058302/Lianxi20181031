package soexample.umeng.com.lianxi20181031;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private CallBack callBack;
    private MyAdapter myAdapter;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
//        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, Main2Activity.class));
//            }
//        });
        viewPager = findViewById(R.id.vp);
        tabLayout = findViewById(R.id.tab);
        myAdapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager);
        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                callBack.back("Fan");
                Fragment fragment = myAdapter.getItem(0);
                if (fragment instanceof FragmentDemo1) {
                    ((FragmentDemo1) fragment).setMessage("666");
                }
            }
        });

    }

    public void onChangeItem() {
        viewPager.setCurrentItem(1);
    }

    public void onChangeItem1() {
        viewPager.setCurrentItem(0);
    }

    class MyAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();
        private String[] title = {"首页", "第二页"};

        public MyAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(new FragmentDemo1());
            fragments.add(new FragmentDemo2());
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(List<Message> list) {
        /*
         * Do something
         * */
        Toast.makeText(this, list.size() + "", Toast.LENGTH_SHORT).show();
    }
}
