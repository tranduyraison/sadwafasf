package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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
    Button btLoad;
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
            articleList.add(new Article("Bài viết 2", "Nội dung bài viết 2", R.drawable.dongvat));
            articleList.add(new Article("Bài viết 3", "Nội dung bài viết 3", R.drawable.dongvat1));
            articleList.add(new Article("Bài viết 4", "Nội dung bài viết 4", R.drawable.img));

        }

        recyclerView = findViewById(R.id.recyclerView);
        btLoad = findViewById(R.id.btLoad);

        myAdapter = new MyAdapter(this, articleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        // bài thứ 4-5 trở đi để mình tự điền nhé không cần thêm nội dung
        btLoad.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Thêm bài viết");

            LinearLayout layout = new LinearLayout(MainActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(50, 40, 50, 10);

            final EditText titleBox = new EditText(MainActivity.this);
            titleBox.setHint("Tiêu đề bài viết");
            layout.addView(titleBox);

            final EditText contentBox = new EditText(MainActivity.this);
            contentBox.setHint("Nội dung bài viết");
            layout.addView(contentBox);

            builder.setView(layout);

            builder.setPositiveButton("Thêm", (dialog, which) -> {
                String title = titleBox.getText().toString();
                String content = contentBox.getText().toString();
                if (!title.isEmpty()) {
                    articleList.add(new Article(title, content, R.mipmap.ic_launcher));
                    myAdapter.notifyItemInserted(articleList.size() - 1);
                    recyclerView.scrollToPosition(articleList.size() - 1);
                }
            });
            
            builder.setNegativeButton("Hủy", (dialog, which) -> dialog.cancel());
            builder.show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (myAdapter != null) {
            myAdapter.notifyDataSetChanged();
        }
    }
}
