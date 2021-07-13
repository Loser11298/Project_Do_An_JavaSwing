/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quan.datn.network;

import com.google.gson.Gson;
import static com.quan.datn.common.Constants.BASE_URL;
import com.quan.datn.utils.Utils;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author NguyenHongQuan
 */
public class APIManager {

    public static Image loadImage(String link, int with, int hight) {
        try {
            if (link != null && !link.equals("") && with > 0 && hight > 0) {
                URL url = new URL(BASE_URL + link);
                Image image = ImageIO.read(url).getScaledInstance(with, hight, Image.SCALE_SMOOTH);
                return new ImageIcon(image).getImage();
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return Utils.getImage("avatar.jpeg", with, hight);
    }

    public static ImageIcon loadImageIcon(String link, int with, int hight) {
        try {
            if (link != null && !link.equals("") && with > 0 && hight > 0) {
                URL url = new URL(BASE_URL + link);
                Image image = ImageIO.read(url).getScaledInstance(with, hight, Image.SCALE_SMOOTH);;
                return new ImageIcon(image);
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return Utils.getIcon("avatar.jpeg", with, hight);
    }

    public static String get(String url) {
        try {
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
            conn.setRequestProperty("Content-type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Authorization", "bearer NguyenHongQuan");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("referer", url);
            conn.setRequestProperty("sec-fetch-dest", "empty");
            conn.setRequestProperty("sec-fetch-mode", "cors");
            conn.setRequestProperty("sec-fetch-site", "same-origin");
            conn.setRequestProperty("x-requested-with", "XMLHttpRequest");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            if (conn.getResponseCode() == 200) {
                StringBuilder response;
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                    response = new StringBuilder();
                    String responseLine = "";
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                }
                return response.toString();
            }
        } catch (ConnectException exception) {
            System.out.println("Service Error");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String post(String link, Object obj) {
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
            conn.setRequestProperty("Content-type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Authorization", "bearer NguyenHongQuan");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("referer", link);
            conn.setRequestProperty("sec-fetch-dest", "empty");
            conn.setRequestProperty("sec-fetch-mode", "cors");
            conn.setRequestProperty("sec-fetch-site", "same-origin");
            conn.setRequestProperty("x-requested-with", "XMLHttpRequest");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream())) {
                String data = new Gson().toJson(obj);
                if (!data.equals("")) {
                    writer.write(data);
                    writer.flush();
                }
            }
            if (conn.getResponseCode() == 200) {
                StringBuilder response;
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                    response = new StringBuilder();
                    String responseLine = "";
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                }
                return response.toString();
            }
        } catch (ConnectException exception) {
            System.out.println("Service Error");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
