package com.example.myapplication;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    
    // Mọi thứ đều lưu trong cấu trúc dữ liệu ở trên... lưu ở trong cái list rồi đẩy qua đẩy qua về
    public static List<Article> articleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // khi chạy lần đầu tiêu có thể có 2-3 bài viết vào trong code luôn và ảnh luôn
        if (articleList.isEmpty()) {
            articleList.add(new Article("Bài viết 1", "Nội dung bài viết 1. Đây là bài mẫu đầu tiên.", R.mipmap.ic_launcher));
            articleList.add(new Article("Bài viết 2", "con chó thuốc nhỏ mắt", R.drawable.dongvat));
            articleList.add(new Article("Bài viết 3", "con chó mắt rưng rưng", R.drawable.dongvat1));
            articleList.add(new Article("Bài viết 4", "con mèo", R.drawable.img));
        }

        recyclerView = findViewById(R.id.recyclerView);

        myAdapter = new MyAdapter(this, articleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (myAdapter != null) {
            myAdapter.notifyDataSetChanged();
        }
    }
}
