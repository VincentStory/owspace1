package redroid.util;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Locale;

/**
 * 字符串帮助类
 * @author RobinVanYang created at 2017-6-19.
 */

public class StringUtils {
    /**
     * MD5消息摘要算法
     * @param message  the input string.
     * @return digest.
     */
    public static String md5(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(message.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
        }
        return "";
    }

    public static boolean notNull(String str) {
        return null != str && str.trim().length() != 0 && (!"null".equals(str.trim()));
    }

    /**
     * 如果字符串的第一个字符是[A-z], 返回字符串第一个字符的大写,
     * 如果第一个字符是中文,则返回中文拼音第一个字母的大写,否则返回#
     *
     * @param name
     * @return
     */
    public static char getFirstLetter(String name) {
        String letter = "#";

        if (!TextUtils.isEmpty(name.trim())) {
            String first = name.trim().substring(0, 1).toUpperCase(Locale.CHINESE);

            if (first.matches("[A-Z]"))
                letter = first;
            else {
                ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().get(first);

                if (tokens != null && tokens.size() > 0) {
                    String firstPinyin = tokens.get(0).target.substring(0, 1).toUpperCase(Locale.CHINESE);
                    if (firstPinyin.matches("[A-Z]")) {
                        letter = firstPinyin;
                    }
                }
            }
        }

        return letter.charAt(0);
    }

    /**
     * if the string is json format, this method will format it.
     * @param string
     * @return
     */
    public static String toPrettyJson(String string) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        JsonReader reader = new JsonReader(new StringReader(string));
        reader.setLenient(true);
        JsonElement element = parser.parse(reader);
        if (element.isJsonObject() || element.isJsonArray()) {
            return gson.toJson(element);
        } else {
            return string;
        }
    }
}