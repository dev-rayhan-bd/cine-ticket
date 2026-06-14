package com.cinetick.dao;

import com.cinetick.config.DatabaseConfig;
import com.cinetick.models.User;
import java.sql.*;

public class UserDAO {

 public static boolean register(User user) {
    String sql = "INSERT INTO users (name, email, password, profile_pic, otp_code) VALUES (?, ?, ?, ?, ?)";
    try (Connection con = DatabaseConfig.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        String hashedPw = org.mindrot.jbcrypt.BCrypt.hashpw(user.password, org.mindrot.jbcrypt.BCrypt.gensalt());
      
        String otp = String.valueOf((int)(Math.random() * 900000) + 100000); 
        
        pst.setString(1, user.name);
        pst.setString(2, user.email);
        pst.setString(3, hashedPw);
        pst.setString(4, user.profilePic);
        pst.setString(5, otp);
        
        int res = pst.executeUpdate();
        if (res > 0) {
        
            System.out.println("\n-------------------------------------------");
            System.out.println("📩 DEBUG: OTP for " + user.email + " is: " + otp);
            System.out.println("-------------------------------------------\n");
            return true;
        }
    } catch (Exception e) { e.printStackTrace(); }
    return false;
}

  
    public static boolean verifyOTP(String email, String code) {
        String sql = "UPDATE users SET is_verified = TRUE, otp_code = NULL WHERE email = ? AND otp_code = ?";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, email);
            pst.setString(2, code);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

public static User login(String email, String password) {
    String sql = "SELECT * FROM users WHERE email = ?";
    try (Connection con = DatabaseConfig.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            if (!rs.getBoolean("is_verified")) return null;
            
            if (org.mindrot.jbcrypt.BCrypt.checkpw(password, rs.getString("password"))) {
               
                User user = new User(rs.getString("name"), rs.getString("email"), "", rs.getString("profile_pic"));
                user.id = rs.getInt("id");
                return user;
            }
        }
    } catch (Exception e) { e.printStackTrace(); }
    return null;
}



}