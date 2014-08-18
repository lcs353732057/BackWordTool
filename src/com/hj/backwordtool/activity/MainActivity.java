package com.hj.backwordtool.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.hj.backwordtool.R;
import com.hj.backwordtool.fragment.AboutFragment;
import com.hj.backwordtool.fragment.LoginFragment;
import com.hj.backwordtool.fragment.SettingFragment;
import com.hj.backwordtool.fragment.WordFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/8/12.
 */
public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    public final static String ICON = "icon";
    public final static String TITLE = "text";

    private SlidingMenu mSlidingMenu;

    private LinearLayout mContentLayout;

    private int currentPos = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initTitle();

        mContentLayout = (LinearLayout) findViewById(R.id.content_layout);

        mSlidingMenu = new SlidingMenu(this);
        // 滑动方向(LEFT,RIGHT,LEFT_RIGHT)
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        // 滑动显示SlidingMenu的范围
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 菜单的宽度
        mSlidingMenu.setBehindWidth(getResources().getDimensionPixelSize(R.dimen.menu_width));
        // 把SlidingMenu附加在Activity上
        // SlidingMenu.SLIDING_WINDOW:菜单拉开后高度是全屏的
        // SlidingMenu.SLIDING_CONTENT:菜单拉开后高度是不包含Title/ActionBar的内容区域
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        // 菜单的布局文件
        mSlidingMenu.setMenu(R.layout.sliding_menu);

        // 监听slidingmenu打开
        mSlidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
            @Override
            public void onOpened() {
            }
        });
        // 监听slidingmenu关闭
        mSlidingMenu.setOnClosedListener(new SlidingMenu.OnClosedListener() {
            @Override
            public void onClosed() {
            }
        });

        // 设置菜单内容
        ListView listView = (ListView) mSlidingMenu.findViewById(R.id.menu_list);

        final List<Integer> itemsIcon = new ArrayList<Integer>();
        itemsIcon.add(R.drawable.icon_home);
        itemsIcon.add(R.drawable.icon_account);
        itemsIcon.add(R.drawable.icon_group);
        itemsIcon.add(R.drawable.icon_refresh);

        final List<String> itemsTitle = new ArrayList<String>();
        itemsTitle.add("单词列表");
        itemsTitle.add("我的账号");
        itemsTitle.add("设置");
        itemsTitle.add("关于");

        List<Map<String, MyMenuItem>> items = new ArrayList<Map<String, MyMenuItem>>();
        for (int i = 0; i < itemsTitle.size(); i++) {
            Map<String, MyMenuItem> map = new HashMap<String, MyMenuItem>();
            MyMenuItem menuItem = new MyMenuItem();
            menuItem.icon = itemsIcon.get(i);
            menuItem.title = itemsTitle.get(i);

            map.put(ICON, menuItem);
            map.put(TITLE, menuItem);
            items.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, items,
                R.layout.sliding_menu_item, new String[]{ICON, TITLE},
                new int[]{R.id.icon, R.id.title});

        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                MyMenuItem menuItem = (MyMenuItem) data;
                switch (view.getId()) {
                    case R.id.icon:
                        ((ImageView) view).setImageResource(menuItem.icon);
                        break;
                    case R.id.title:
                        ((TextView) view).setText(menuItem.title);
                        break;
                }
                return true;
            }
        });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setSelection(0);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, WordFragment.newInstance()).commit();
    }

    private void initTitle() {
        ImageView imageView = (ImageView) findViewById(R.id.left_title_imageView);
        imageView.setImageResource(R.drawable.menu_top_selector);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(this);

        TextView textView = (TextView) findViewById(R.id.middle_title_textView);
        textView.setText("单词列表");
        textView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // 关闭SlidingMenu
        mSlidingMenu.toggle();

        if (currentPos == i) {
            return;
        }
        if (i == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, WordFragment.newInstance()).commit();
        } else if (1 == i) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, LoginFragment.newInstance()).commit();
        } else if (2 == i) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, SettingFragment.newInstance()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, AboutFragment.newInstance()).commit();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.left_title_imageView) {
            mSlidingMenu.toggle();
        }
    }

    private class MyMenuItem {
        public int icon;
        public String title;
    }
}