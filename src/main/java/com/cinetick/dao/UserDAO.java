package com.cinetick.dao;

import com.cinetick.config.DatabaseConfig;
import com.cinetick.models.User;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class UserDAO {

    // ১. ইউজার রেজিস্ট্রেশন
    public static boolean register(User user) {
        String sql = "INSERT INTO users (name, email, password, profile_pic, otp_code) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            String hashedPw = BCrypt.hashpw(user.password, BCrypt.gensalt());
            String otp = String.valueOf((int)(Math.random() * 900000) + 100000); // 6 Digit OTP
            
            pst.setString(1, user.name);
            pst.setString(2, user.email);
            pst.setString(3, hashedPw);
            pst.setString(4, user.profilePic);
            pst.setString(5, otp);
            
            System.out.println("📩 DEBUG: OTP for " + user.email + " is: " + otp); // টার্মিনালে ওটিপি দেখাবে
            return pst.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    // ২. ওটিপি ভেরিফিকেশন
    public static boolean verifyOTP(String email, String code) {
        String sql = "UPDATE users SET is_verified = TRUE, otp_code = NULL WHERE email = ? AND otp_code = ?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, email);
            pst.setString(2, code);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    // ৩. লগইন লজিক
    public static boolean login(String email, String password) {
        String sql = "SELECT password, is_verified FROM users WHERE email = ?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                boolean pwMatch = BCrypt.checkpw(password, rs.getString("password"));
                boolean verified = rs.getBoolean("is_verified");
                if (!verified) {
                    System.out.println("❌ Account not verified!");
                    return false;
                }
                return pwMatch;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}