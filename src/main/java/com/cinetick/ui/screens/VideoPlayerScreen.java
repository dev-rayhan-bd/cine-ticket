package com.cinetick.ui.screens;

import com.cinetick.ui.MainDashboard;
import com.cinetick.ui.theme.Theme;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import javax.swing.*;
import java.awt.*;

public class VideoPlayerScreen extends JPanel {
    private EmbeddedMediaPlayerComponent mediaPlayer;
    private MainDashboard dashboard;

    public VideoPlayerScreen(MainDashboard dashboard) {
        this.dashboard = dashboard;
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        mediaPlayer = new EmbeddedMediaPlayerComponent();
        add(mediaPlayer, BorderLayout.CENTER);

        // Control Bar
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topBar.setBackground(new Color(20, 20, 20));
        JButton closeBtn = new JButton("\u2715 CLOSE TRAILER");
        closeBtn.setBackground(Theme.PRIMARY_RED);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.addActionListener(e -> {
            mediaPlayer.mediaPlayer().controls().stop();
            dashboard.navigateTo("HOME");
        });
        topBar.add(closeBtn);
        add(topBar, BorderLayout.NORTH);
    }

//   public void playVideo(String url) {
//     System.out.println("🎥 Attempting to play: " + url);
    
  
//     // url = "https://www.w3schools.com/html/mov_bbb.mp4"; 

//     mediaPlayer.mediaPlayer().media().play(url);


//     mediaPlayer.mediaPlayer().events().addMediaPlayerEventListener(new uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter() {
//         @Override
//         public void error(uk.co.caprica.vlcj.player.base.MediaPlayer mediaPlayer) {
//             System.err.println("❌ VLC Error: Could not play this video. Check if YouTube URL is restricted.");
//         }
//     });
// }

public void playVideo(String url) {
    // ইউটিউব ইউআরএল এর বদলে নিচের এই ডাইরেক্ট লিঙ্কটি দিয়ে একবার রান করে দেখুন
    String testUrl = "https://www.w3schools.com/html/mov_bbb.mp4"; 
    mediaPlayer.mediaPlayer().media().play(testUrl);
}

}