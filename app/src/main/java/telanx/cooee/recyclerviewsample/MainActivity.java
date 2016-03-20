package telanx.cooee.recyclerviewsample;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import telanx.cooee.recyclerviewsample.adapter.BaseAdapter;
import telanx.cooee.recyclerviewsample.adapter.AlphabetAdapter;
import telanx.cooee.recyclerviewsample.adapter.SimpleAdapter;
import telanx.cooee.recyclerviewsample.itemdecoration.Line;
import telanx.cooee.recyclerviewsample.viewholder.StringViewHolder;

import static android.util.Log.e;
import static android.util.Log.w;


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

        /**RecyclerView用法3:监听RecyclerView滚动状态并设置滚动监听*/
//        InitRecyclerViewWByScrollListener recyclerViewSample3 = new InitRecyclerViewWByScrollListener(recyclerView);
//        recyclerViewSample3.setAdapter();
//        recyclerViewSample3.setLayoutManager();
//        //用Paint绘制表项分割线
//        itemDecorations.add(new Line(4, Color.BLUE).setMarginLeft(10)
//                                                   .setMarginRight(10));
//        recyclerViewSample3.drawDecoration(itemDecorations);
//        recyclerViewSample3.setListeners();
    }

    protected void initData()
    {
        datas = new ArrayList<>();
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
     * 4.设置动画(可选)
     * 5.设置监听器(可选)
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
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
        }

        void setAdapter()
        {
            RecyclerView.Adapter adapter = new SimpleAdapter(MainActivity.this, datas);
            recyclerView.setAdapter(adapter);
        }

        void setItemAnimator()
        {
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }

        void setListeners()
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

            @Override
            public void onItemLongClick(int position,
                                        BaseAdapter adapter)
            {
                //删除长按表项并触发动画
                adapter.removeData(position);
            }
        }
    }

    /**
     * RecyclerView用法3:为RecyclerView添加滚动监听
     */
    private class InitRecyclerViewWByScrollListener extends InitRecyclerView
    {


        public InitRecyclerViewWByScrollListener(RecyclerView recyclerView)
        {
            super(recyclerView);
        }

        @Override
        void setAdapter()
        {
            BaseAdapter adapter = new AlphabetAdapter(MainActivity.this, datas);
            recyclerView.setAdapter(adapter);
        }

        @Override
        void setListeners()
        {
            recyclerView.addOnScrollListener(new OnScrollStateListener()
            {
                private BaseAdapter adapter = (BaseAdapter) recyclerView.getAdapter();

                @Override
                void onBottom()
                {
                    //插入数据到列表尾部
                    adapter.addData("the item added");
                }

                @Override
                void onTop()
                {
                    //插入数据到列表第三个位置 可以看到插入动画效果
                    adapter.addDataAt("item inserted", 2);
                }

                @Override
                void onRightmost()
                {
                }

                @Override
                void onLeftmost()
                {
                }
            });
        }

        /**
         * 滚动状态监听器(可监听滚动到开头和结尾,仅适用于LinearLayoutManager)
         */
        private abstract class OnScrollStateListener extends RecyclerView.OnScrollListener
        {
            /**
             * 是否正在向左滚动
             */
            private boolean scrollingLeft;
            /**
             * 是否正在向下滚动
             */
            private boolean scrollingDown;
            /**
             * 是否正在向右滚动
             */
            private boolean scrollingRight;
            /**
             * 是否正在向上滚动
             */
            private boolean scrollingUp;

            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx,
                                   int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                //记忆滚动方向
                scrollingLeft = (dx > 0);
                scrollingDown = (dy > 0);
                scrollingUp = (dy < 0);
                scrollingRight = (dx < 0);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                //当滚动停止后 检测滚动位置
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                    int lastItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                    int totalItemNum = layoutManager.getItemCount();
                    if (scrollingDown && lastItemPosition == totalItemNum - 1)
                    {
                        onBottom();
                    }
                    if (scrollingUp && firstItemPosition == 0)
                    {
                        onTop();
                    }
                    if (scrollingRight && firstItemPosition == 0)
                    {
                        onLeftmost();
                    }
                    if (scrollingLeft && lastItemPosition == totalItemNum - 1)
                    {
                        onRightmost();
                    }
                }
            }

            void onBottom()
            {

            }

            void onTop()
            {

            }

            void onRightmost()
            {

            }

            void onLeftmost()
            {

            }
        }
    }
}
