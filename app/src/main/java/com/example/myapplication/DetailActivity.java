package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainDetail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int index = getIntent().getIntExtra("ARTICLE_INDEX", -1);
        if (index != -1) {
            Article article = MainActivity.articleList.get(index);

            // Tăng view lên 1
            article.incrementViewCount();

            ImageView imgCoverDetail = findViewById(R.id.imgCoverDetail);
            TextView tvTitleDetail = findViewById(R.id.tvTitleDetail);
            TextView tvContentDetail = findViewById(R.id.tvContentDetail);
            TextView tvViewCountDetail = findViewById(R.id.tvViewCountDetail);

            imgCoverDetail.setImageResource(article.getCoverImageResId());
            tvTitleDetail.setText(article.getTitle());
            tvContentDetail.setText(article.getContent());
            tvViewCountDetail.setText("Views: " + article.getViewCount());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
