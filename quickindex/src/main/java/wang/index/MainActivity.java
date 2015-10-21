package wang.index;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import wang.index.Adapter.QucikAdapter;
import wang.index.Bean.Person;
import wang.index.util.Cheeses;
import wang.index.view.QuickIndexBar;


/**
 * 快速索引:
 * 1 A-Z 索引的绘制
 * 2 处理Touch事件
 * 3 提供使用回调
 * 4 汉子转拼音
 * 5 进行排序展示
 * 6 进行分组
 * 7 讲自定义控件和ListView合体
 */

public class MainActivity extends AppCompatActivity {
    ListView quick_lv;
    ArrayList<Person> persons;
    TextView tv_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuickIndexBar bar = (QuickIndexBar) findViewById(R.id.bar);
        bar.setLetterListener(new QuickIndexBar.onLetterListener() {
            @Override
            public void onLetter(String letter) {

                for (int i = 0; i < persons.size(); i++) {
                    Person person = persons.get(i);
                    String str = person.getPinyin().charAt(0) + "";
                    if (TextUtils.equals(str, letter)) {

                        quick_lv.setSelection(i);
                        showLetter(letter);
                        break;
                    }

                }
            }
        });
        quick_lv = (ListView) findViewById(R.id.quick_lv);
        tv_center = (TextView) findViewById(R.id.tv_center);


        //整合  数组的 名字 和 拼音
        persons = new ArrayList<Person>();
        //填充数据,排序
        fillAndSortData(persons);

        quick_lv.setAdapter(new QucikAdapter(MainActivity.this, persons));
    }


    private Handler handler = new Handler();

    /**
     * 显示 中间的 dialog
     */
    private void showLetter(String letter) {
        tv_center.setVisibility(View.VISIBLE);
        tv_center.setText(letter);
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_center.setVisibility(View.GONE);
            }
        }, 2000);
    }

    /**
     * 让list排序
     *
     * @param persons
     */
    private void fillAndSortData(ArrayList<Person> persons) {
int b;
        for (int i = 0; i < Cheeses.NAMES.length; i++) {
        b=1;
            String name = Cheeses.NAMES[i];
            persons.add(new Person(name));

            Collections.sort(persons);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
