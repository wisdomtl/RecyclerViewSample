package telanx.cooee.coordinate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import telanx.cooee.recyclerviewsample.MainActivity;
import telanx.cooee.recyclerviewsample.R;
import telanx.cooee.recyclerviewsample.adapter.VariousItemTypeAdapter;
import telanx.cooee.recyclerviewsample.entity.ItemData;

public class CoordinateActivity extends Activity implements RecyclerView.OnItemTouchListener {

    private ArrayList<String> datas;
    private List<ItemData> itemDataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinate_layout);
        initList();
    }

    protected void initData() {
        datas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            datas.add("" + (char) i);
        }

        itemDataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ItemData itemData = new ItemData();
            itemData.setTime(i + "点");
            itemData.setTitle("新闻" + i);
            itemData.setLayoutType(i % 2 == 0 ? ItemData.LAYOUT_1 : ItemData.LAYOUT_2);
            itemDataList.add(itemData);
        }
    }

    private void initList() {
        initData();
        RecyclerView recyclerView = ((RecyclerView) findViewById(R.id.rv_coordinate));
        LinearLayoutManager layoutManager = new LinearLayoutManager(CoordinateActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        VariousItemTypeAdapter adapter = new VariousItemTypeAdapter(CoordinateActivity.this, itemDataList);
        recyclerView.setAdapter(adapter);
        //touch event case1: make ScrollView which is in an item of RecyclerView scrollable
        //the way is requesting parent not to intercept touch event, let item deal itself
        recyclerView.addOnItemTouchListener(this);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        //touch event case1: make ScrollView which is in an item of RecyclerView scrollable
        //the way is requesting parent not to intercept touch event, let item deal itself
        rv.requestDisallowInterceptTouchEvent(true);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}
