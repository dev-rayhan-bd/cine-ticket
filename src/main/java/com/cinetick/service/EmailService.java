package com.cinetick.service;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailService {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String SENDER_EMAIL = dotenv.get("MAIL_USERNAME");
    private static final String SENDER_PASS = dotenv.get("MAIL_PASSWORD");
    
    // লোগো ইউআরএল (Cloudinary)
    private static final String LOGO_URL = "https://res.cloudinary.com/dffzmnts9/image/upload/v1781444387/CineTick_ekrjzp.png";

    public static void sendOTP(String recipientEmail, String userName, String otp) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        // ডিবাগ অন করা হলো যাতে টার্মিনালে এরর ডিটেইলস দেখা যায়
        // props.put("mail.debug", "true"); 

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASS);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            // নামসহ সেন্ডার (স্প্যাম ফিল্টার কমাতে সাহায্য করে)
            message.setFrom(new InternetAddress(SENDER_EMAIL, "CineTick Pro Team"));
            message.setReplyTo(new Address[]{new InternetAddress(SENDER_EMAIL)});
            
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            
            // সাবজেক্ট লাইন পরিবর্তন (আরও হিউম্যান মনে হবে)
            message.setSubject("Welcome to CineTick Pro, " + userName + "! Your security code is " + otp);

            // --- Simplified & Modern HTML Template ---
            String htmlContent = 
                "<div style='background-color:#f9f9f9; padding:50px 20px;'>" +
                "  <table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:600px; background-color:#ffffff; border-radius:10px; border:1px solid #e0e0e0; box-shadow:0 4px 10px rgba(0,0,0,0.05);'>" +
                "    <tr>" +
                "      <td align='center' style='padding:40px 0 20px 0; background-color:#141414; border-radius:10px 10px 0 0;'>" +
                "        <img src='" + LOGO_URL + "' alt='CineTick Pro' width='180' style='display:block; border:0; outline:none;'>" +
                "      </td>" +
                "    </tr>" +
                "    <tr>" +
                "      <td style='padding:40px; text-align:center; font-family:Arial, sans-serif;'>" +
                "        <h2 style='color:#333; font-size:24px; margin-bottom:10px;'>Verify Your Account</h2>" +
                "        <p style='color:#666; font-size:16px; line-height:1.5;'>Hi " + userName + ", thank you for joining our cinematic community. Use the code below to complete your registration.</p>" +
                "        <div style='margin:30px 0; padding:20px; background-color:#f4f4f4; border:2px dashed #e50914; border-radius:8px; display:inline-block;'>" +
                "          <span style='font-size:36px; font-weight:bold; letter-spacing:10px; color:#e50914;'>" + otp + "</span>" +
                "        </div>" +
                "        <p style='color:#999; font-size:13px;'>This code is valid for 5 minutes. If you didn't request this, ignore this email.</p>" +
                "      </td>" +
                "    </tr>" +
                "    <tr>" +
                "      <td style='padding:20px; background-color:#fcfcfc; text-align:center; border-top:1px solid #eeeeee; border-radius:0 0 10px 10px;'>" +
                "        <p style='color:#999; font-size:12px; margin:0;'>CineTick Pro | Dhaka, Bangladesh</p>" +
                "      </td>" +
                "    </tr>" +
                "  </table>" +
                "</div>";

            message.setContent(htmlContent, "text/html; charset=utf-8");

            new Thread(() -> {
                try {
                    Transport.send(message);
                    System.out.println("💎 [EmailService] Premium OTP sent to: " + recipientEmail);
                } catch (MessagingException e) {
                    System.err.println("❌ [EmailService] Failed: " + e.getMessage());
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}