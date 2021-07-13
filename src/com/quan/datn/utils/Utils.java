/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.utils;

import java.awt.Image;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Utils {

    public static String randomString(int length) {
        int leftLimit = 65;
        int rightLimit = 122;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 90 || i >= 65) && (i <= 122 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public static String getTimeString(String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    public static String formatTimeString(String time) {
        try {
            DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
            SimpleDateFormat dtf = new SimpleDateFormat("HH:mm:ss");
            return dtf.format(df.parse(time));
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            return "00:00:00";
        }
    }

    public static Image getImage(String name, int with, int hight) {
        Image image = null;
        try {
            if (with > 0 && hight > 0) {
                image = ImageIO.read(Utils.class.getResource("/com/quan/datn/images/" + name)).getScaledInstance(with, hight, Image.SCALE_SMOOTH);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static ImageIcon getIcon(String name, int with, int hight) {
        ImageIcon icon = null;
        try {
            if (with > 0 && hight > 0) {
                Image image = ImageIO.read(Utils.class.getResource("/com/quan/datn/images/" + name)).getScaledInstance(with, hight, Image.SCALE_SMOOTH);
                icon = new ImageIcon(image);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return icon;
    }

    public static ImageIcon resizeImageIcon(String imagePath, JLabel lbImg) {
        ImageIcon myImage = new ImageIcon(imagePath);
        Image pic = myImage.getImage();
        Image pic2 = pic.getScaledInstance(lbImg.getWidth(), lbImg.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon im = new ImageIcon(pic2);
        return (im);
    }
}
