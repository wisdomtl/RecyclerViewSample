package telanx.cooee.recyclerviewsample;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import telanx.cooee.recyclerviewsample.adapter.BaseAdapter;
import telanx.cooee.recyclerviewsample.adapter.AlphabetAdapter;
import telanx.cooee.recyclerviewsample.adapter.SimpleAdapter;
import telanx.cooee.recyclerviewsample.itemdecoration.Line;


public class MainActivity extends ActionBarActivity
{
    private ArrayList<String> datas;
    private ArrayList<RecyclerView.ItemDecoration> itemDecorations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init()
    {
        initData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);

        /**RecyclerView用法1:最简单的用法*/
//        InitSimpleRecyclerView recyclerViewSample1 = new InitSimpleRecyclerView(recyclerView);
//        recyclerViewSample1.setAdapter();
//        recyclerViewSample1.setLayoutManager();
//        //用Drawable绘制表项分割线
//        itemDecorations.add(new Line(this)) ;
//        recyclerViewSample1.drawDecoration(itemDecorations);

        /**RecyclerView用法2:为表项设置监听器*/
        InitRecyclerViewWithItemClickListener recyclerViewSample2 = new InitRecyclerViewWithItemClickListener(recyclerView);
        recyclerViewSample2.setAdapter();
        recyclerViewSample2.setLayoutManager();
        //用Paint绘制表项分割线
        itemDecorations.add(new Line(4, Color.BLUE).setMarginLeft(10)
                                                   .setMarginRight(10));
        recyclerViewSample2.drawDecoration(itemDecorations);
    }

    protected void initData()
    {
        datas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            datas.add("" + (char) i);
        }
    }

    /**
     * 抽象出使用RecyclerView要做的几件事情
     * 1.设置LayoutManager(必选)
     * 2.设置适配器(必选)
     * 3.设置装饰器(可选)
     */
    public abstract class InitRecyclerView
    {
        protected RecyclerView recyclerView;

        public InitRecyclerView(RecyclerView recyclerView)
        {
            this.recyclerView = recyclerView;
        }

        void setLayoutManager()
        {
        }

        void setAdapter()
        {
        }

        final void drawDecoration(List<RecyclerView.ItemDecoration> itemDecoration)
        {
            int itemDecorationNum = itemDecoration.size();
            for (int i = 0; i < itemDecorationNum; i++)
            {
                recyclerView.addItemDecoration(itemDecoration.get(i));
            }
        }
    }

    /**
     * RecyclerView用法1:最简单的用法
     */
    public class InitSimpleRecyclerView extends InitRecyclerView
    {
        public InitSimpleRecyclerView(RecyclerView recyclerView)
        {
            super(recyclerView);
        }

        @Override
        public void setLayoutManager()
        {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
        }

        @Override
        public void setAdapter()
        {
            RecyclerView.Adapter adapter = new SimpleAdapter(MainActivity.this, datas);
            recyclerView.setAdapter(adapter);
        }
    }

    /**
     * RecyclerView用法2:为表项设置监听器
     */
    public class InitRecyclerViewWithItemClickListener extends InitRecyclerView
    {
        public InitRecyclerViewWithItemClickListener(RecyclerView recyclerView)
        {
            super(recyclerView);
        }

        @Override
        public void setLayoutManager()
        {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
        }

        @Override
        public void setAdapter()
        {
            AlphabetAdapter adapter = new AlphabetAdapter(MainActivity.this, datas);
            adapter.setOnItemClickListener(new AlphabetClickListener());
            recyclerView.setAdapter(adapter);
        }

        private class AlphabetClickListener implements BaseAdapter.OnItemClickListener<String>
        {

            @Override
            public void onItemClick(String data)
            {
                Toast.makeText(MainActivity.this, "click " + data, Toast.LENGTH_SHORT)
                     .show();
            }
        }
    }
}
