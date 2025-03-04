package com.example.bai1;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputA = findViewById(R.id.inputA);
        EditText inputB = findViewById(R.id.inputB);
        EditText inputC = findViewById(R.id.inputC);
        Button solveButton = findViewById(R.id.solveButton);
        TextView resultView = findViewById(R.id.resultView);
        Button playAudioButton = findViewById(R.id.playAudioButton);
        Button playVideoButton = findViewById(R.id.playVideoButton);
        videoView = findViewById(R.id.videoView);

        // Bắt sự kiện giải phương trình bậc hai
        solveButton.setOnClickListener(v -> {
            try {
                double a = Double.parseDouble(inputA.getText().toString());
                double b = Double.parseDouble(inputB.getText().toString());
                double c = Double.parseDouble(inputC.getText().toString());
                resultView.setText(solveQuadratic(a, b, c));
            } catch (Exception e) {
                resultView.setText("Lỗi nhập liệu!");
            }
        });

        // Bắt sự kiện phát âm thanh
        playAudioButton.setOnClickListener(v -> {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.audio);

            }
            mediaPlayer.start();
        });

        // Bắt sự kiện phát video
        playVideoButton.setOnClickListener(v -> {
            videoView.setVisibility(View.VISIBLE);
            String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
            videoView.setVideoURI(Uri.parse(videoPath));
            videoView.start();
        });
    }

    // Phương thức giải phương trình bậc hai
    private String solveQuadratic(double a, double b, double c) {
        double delta = b * b - 4 * a * c;
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return "Nghiệm x1 = " + x1 + ", x2 = " + x2;
        } else if (delta == 0) {
            double x = -b / (2 * a);
            return "Nghiệm kép x = " + x;
        } else {
            return "Phương trình vô nghiệm!";
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}