package telanx.cooee.recyclerviewsample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import telanx.cooee.recyclerviewsample.adapter.AlphabetAdapter;
import telanx.cooee.recyclerviewsample.adapter.BaseRecyclerViewAdapter;
import telanx.cooee.recyclerviewsample.adapter.PartRefreshAdapter;
import telanx.cooee.recyclerviewsample.adapter.SimpleAdapter;
import telanx.cooee.recyclerviewsample.entity.News;


public class MainActivity extends Activity {
    private static final int MESSAGE_REFRESH_TIME = 1;
    private ArrayList<String> datas;
    private List<News> newsList;
    private ArrayList<RecyclerView.ItemDecoration> itemDecorations = new ArrayList<>();
    private ScheduledExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initData();
        executor = Executors.newSingleThreadScheduledExecutor();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);

        /**RecyclerView usage1:the most simplest way to build RecyclerView*/
//        InitSimpleRecyclerView recyclerViewSample1 = new InitSimpleRecyclerView(recyclerView);
//        recyclerViewSample1.setAdapter();
//        recyclerViewSample1.setLayoutManager();
//        //用Drawable绘制表项分割线
//        itemDecorations.add(new Line(this)) ;
//        recyclerViewSample1.drawDecoration(itemDecorations);

        /**RecyclerView usage2:set click listener to RecyclerView*/
//        InitRecyclerViewWithItemClickListener recyclerViewSample2 = new InitRecyclerViewWithItemClickListener(recyclerView);
//        recyclerViewSample2.setAdapter();
//        recyclerViewSample2.setLayoutManager();
//        //用Paint绘制表项分割线
//        itemDecorations.add(new Line(4, Color.BLUE).setMarginLeft(10)
//                                                   .setMarginRight(10));
//        recyclerViewSample2.drawDecoration(itemDecorations);

        /**RecyclerView usage3:set scroll listener to RecyclerView*/
//        InitRecyclerViewWByScrollListener recyclerViewSample3 = new InitRecyclerViewWByScrollListener(recyclerView);
//        recyclerViewSample3.setAdapter();
//        recyclerViewSample3.setLayoutManager();
//        //用Paint绘制表项分割线
//        itemDecorations.add(new Line(4, Color.BLUE).setMarginLeft(10)
//                                                   .setMarginRight(10));
//        recyclerViewSample3.drawDecoration(itemDecorations);
//        recyclerViewSample3.setListeners();

        /**RecyclerView usage4:refresh RecyclerView's item partially*/
//        InitRecyclerViewWithPartRefresh recyclerViewSample4 = new InitRecyclerViewWithPartRefresh(recyclerView);
//        recyclerViewSample4.setAdapter();
//        recyclerViewSample4.setLayoutManager();

        /**RecyclerView usage5:add empty view to RecyclerView*/
        InitRecyclerViewByEmptyView recyclerViewSample5 = new InitRecyclerViewByEmptyView(recyclerView);
        recyclerViewSample5.setLayoutManager();
        recyclerViewSample5.setAdapter();
        recyclerViewSample5.addDataDelay(2000);
    }

    protected void initData() {
        datas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            datas.add("" + (char) i);
        }

        newsList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            News news = new News();
            news.setTime(i + "点");
            news.setTitle("新闻" + i);
            newsList.add(news);
        }
    }

    /**
     * abstract the things you should do when building RecyclerView
     * 1.set LayoutManager(required)
     * 2.set Adapter(required)
     * 3.set Decoration(optional)
     * 4.set Animation(optional)
     * 5.set listener(optional)
     */
    public abstract class InitRecyclerView {
        protected RecyclerView recyclerView;

        public InitRecyclerView(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        void setLayoutManager() {
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
        }

        void setAdapter() {
            RecyclerView.Adapter adapter = new SimpleAdapter(MainActivity.this, datas);
            recyclerView.setAdapter(adapter);
        }

        void setItemAnimator() {
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }

        void setListeners() {

        }

        final void drawDecoration(List<RecyclerView.ItemDecoration> itemDecoration) {
            int itemDecorationNum = itemDecoration.size();
            for (int i = 0; i < itemDecorationNum; i++) {
                recyclerView.addItemDecoration(itemDecoration.get(i));
            }
        }
    }

    /**
     * RecyclerView usage1:the most simplest way to build RecyclerView
     */
    public class InitSimpleRecyclerView extends InitRecyclerView {
        public InitSimpleRecyclerView(RecyclerView recyclerView) {
            super(recyclerView);
        }
    }

    /**
     * RecyclerView usage2:set click listener to RecyclerView
     */
    public class InitRecyclerViewWithItemClickListener extends InitRecyclerView {
        public InitRecyclerViewWithItemClickListener(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        public void setAdapter() {
            AlphabetAdapter adapter = new AlphabetAdapter(MainActivity.this, datas);
//            adapter.setOnItemClickListener(new AlphabetClickListener());
            recyclerView.setAdapter(adapter);
        }

//        private class AlphabetClickListener implements BaseRecyclerViewAdapter.OnItemClickListener<String> {
//
//            @Override
//            public void onItemClick(String data) {
//                Toast.makeText(MainActivity.this, "click " + data, Toast.LENGTH_SHORT)
//                        .show();
//            }
//
//            @Override
//            public void onItemLongClick(int position,
//                                        BaseRecyclerViewAdapter adapter) {
//                //删除长按表项并触发动画
//                adapter.removeData(position);
//            }
//        }
    }

    /**
     * RecyclerView usage3:set scroll listener to RecyclerView
     */
    private class InitRecyclerViewWByScrollListener extends InitRecyclerView {


        public InitRecyclerViewWByScrollListener(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        void setAdapter() {
            BaseRecyclerViewAdapter adapter = new AlphabetAdapter(MainActivity.this, datas);
            recyclerView.setAdapter(adapter);
        }

        @Override
        void setListeners() {
            recyclerView.addOnScrollListener(new OnScrollStateListener() {
                private BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) recyclerView.getAdapter();

                @Override
                void onBottom() {
                    //插入数据到列表尾部
                    adapter.addData("the item added");
                }

                @Override
                void onTop() {
                    //插入数据到列表第三个位置 可以看到插入动画效果
                    adapter.addDataAt("item inserted", 2);
                }

                @Override
                void onRightmost() {
                }

                @Override
                void onLeftmost() {
                }
            });

            recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                @Override
                public void onChildViewAttachedToWindow(View view) {
                }

                @Override
                public void onChildViewDetachedFromWindow(View view) {
                }
            });

        }

        /**
         * the listener which listener the scrolling state of RecyclerView(limited to LinearLayoutManager)
         */
        private abstract class OnScrollStateListener extends RecyclerView.OnScrollListener {
            /**
             * whether RecyclerView is scrolling from right to left
             */
            private boolean scrollingLeft;
            /**
             * whether RecyclerView is scrolling from down to up
             */
            private boolean scrollingUp;
            /**
             * whether RecyclerView is scrolling from left to right
             */
            private boolean scrollingRight;
            /**
             * whether RecyclerView is scrolling from down to up
             */
            private boolean scrollingDown;

            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx,
                                   int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //record scrolling direction
                scrollingLeft = (dx > 0);
                scrollingUp = (dy > 0);
                scrollingDown = (dy < 0);
                scrollingRight = (dx < 0);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //detect scrolling position when RecyclerView stop scrolling
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                    int lastItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                    int totalItemNum = layoutManager.getItemCount();
                    if (scrollingUp && lastItemPosition == totalItemNum - 1) {
                        onBottom();
                    }
                    if (scrollingDown && firstItemPosition == 0) {
                        onTop();
                    }
                    if (scrollingRight && firstItemPosition == 0) {
                        onLeftmost();
                    }
                    if (scrollingLeft && lastItemPosition == totalItemNum - 1) {
                        onRightmost();
                    }
                }
            }

            void onBottom() {

            }

            void onTop() {

            }

            void onRightmost() {

            }

            void onLeftmost() {

            }
        }
    }

    /**
     * RecyclerView usage4:refresh RecyclerView's item partially
     */
    private class InitRecyclerViewWithPartRefresh extends InitRecyclerView {
        private Handler refreshHandler = new RefreshHandler();
        private PartRefreshAdapter adapter;

        public InitRecyclerViewWithPartRefresh(RecyclerView recyclerView) {
            super(recyclerView);
            executor.scheduleAtFixedRate(new RefreshTimeRunnable(), 0, PartRefreshAdapter.REFRESH_INTERVAL, TimeUnit.SECONDS);
        }


        @Override
        void setAdapter() {
            adapter = new PartRefreshAdapter(MainActivity.this, newsList);
            recyclerView.setAdapter(adapter);
        }

        private class RefreshHandler extends Handler {
            @Override
            public void handleMessage(Message msg) {

                switch (msg.what) {
                    case MESSAGE_REFRESH_TIME:
                        Integer integer = msg.arg1;
                        adapter.notifyItemRangeChanged(0, newsList.size() - 1, integer);
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        }

        private class RefreshTimeRunnable implements Runnable {
            private int count;

            @Override
            public void run() {
                Message message = refreshHandler.obtainMessage();
                message.arg1 = count;
                message.what = MESSAGE_REFRESH_TIME;
                count++;
                refreshHandler.sendMessage(message);
            }
        }
    }

    /**
     * RecyclerView usage5:add empty view to RecyclerView
     */
    private class InitRecyclerViewByEmptyView extends InitRecyclerView {

        private AlphabetAdapter adapter;

        private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
            }
        };

        public InitRecyclerViewByEmptyView(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        void setAdapter() {
            adapter = new AlphabetAdapter(MainActivity.this);
            adapter.setEmptyViewLayout(R.layout.empty_view);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        public void addDataDelay(int delay) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    adapter.setData(datas);
                }
            }, delay);
        }
    }
}
