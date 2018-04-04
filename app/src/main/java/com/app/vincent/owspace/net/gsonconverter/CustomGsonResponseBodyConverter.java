package com.app.vincent.owspace.net.gsonconverter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 处理json格式的返回body.
 *
 * @author RobinVanYang created at 2017-07-06 12:16.
 */

final class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private static final String TAG = "CustomGsonResponseBodyC";
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String response = value.string();

        int ret = getRet(response);
        if (1 != ret) {
            Log.d(TAG, "ret: " + ret);
            dealError(ret, response);
        }

        MediaType contentType = value.contentType();
        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
        Reader reader = new InputStreamReader(inputStream, charset);
        JsonReader jsonReader = gson.newJsonReader(reader);

        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }

    private void dealError(int ret, String jsonString) {
        switch (ret) {
            case 0://token invalid.
//                EventBus.getDefault().post(new TokenInvalidEvent());
                break;
            case -1:
            case -2:
            case 97:
                throw new RuntimeException(getErrorMsg(jsonString));
            case -3:
            case 60:
            case -21:
            case -49:
            case -48:
            case -50:
                // TODO: 2017/7/13
                break;
            default:
                break;
        }
    }

    private int getRet(String json) {
        int return_code;
        if (json.startsWith("[")) {
            return_code = 1;
        } else {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(json);
                // *hack. 配置文件中无ret
                if (!jsonObject.has("ret")) {
                    return_code = 1;
                } else {
                    return_code = jsonObject.getInt("ret");
                }
            } catch (JSONException e) {
                return_code = 1;
            }
        }

        return return_code;
    }

    private String getErrorMsg(String json) {
        try {
            return new JSONObject(json).getString("errorMsg");
        } catch (JSONException e) {
            return e.getMessage();
        }
    }

    // TODO: 2017/7/18 delete
    private String getErrorMsgFromResult(String json) {
        try {
            return new JSONObject(json).getString("result");
        } catch (JSONException e) {
            return e.getMessage();
        }
    }
}
