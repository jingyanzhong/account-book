package com.accountbook.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * [Json 工具]
 * 
 * <pre>
 * [反序列化]
 * * 反序列時是依照 json 中屬性順序反序列化, 若反序列化屬性有順序問題, 必須設定順序
 *   (可參考 JournalBookItem.allDiscountAmount(先) 與 JournalBookItem.discount(後))
 *   1 使用 &#64;JsonPropertyOrder 設定
 *   2 ObjectMapper 設定 .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS), 由屬性在 class 中的順序決定
 * </pre>
 * 
 * @author cano.su
 * @since 2021/08/31
 */
public class JsonUtils {

    private final static Logger logger = LogManager.getLogger(JsonUtils.class);

    public static final String PATH_TEST_FILE = "D://.test.asx";

    // 避免 import 錯
    static final com.fasterxml.jackson.databind.ObjectMapper mapper;

    static {
        mapper = new com.fasterxml.jackson.databind.ObjectMapper()
                .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
                .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS); // 數值轉換不使用科學符號;
    }

    public static void main(String[] args) {
        File dir = new File("D:/Desktop/_asx/_json.test.temp");
        dir.mkdir();
    }

    /** 轉換物件 */
    public static <T> T parse(String json, Class<T> parseClass) {
        try {
            return mapper.readValue(json, parseClass);
        } catch (IOException e) {
            throw new IllegalArgumentException("json 轉換資料異常!!", e);
        }
    }

    /** 轉換集合 */
    public static <T> T parseCollection(String json, TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (IOException e) {
            throw new IllegalArgumentException("json 轉換資料異常!!", e);
        }
    }

    /**
     * [產生 .json 檔案]
     * 
     * @author cano
     * @since 2021/09/06
     */
    public static void procJsonFileOfWholePath(String wholePath, Object obj) {
        procJsonFile(wholePath, toJson(obj));
    }

    public static void procJsonFileOfObject(FolderName folderName, FileName fileName, Object obj) {
        procJsonFile(JsonPath.build(folderName, fileName), toJson(obj));
    }

    public static void procJsonFileOfObject(String fileName, Object obj) {
        procJsonFile(JsonPath.build(FileName.of(fileName)), toJson(obj));
    }

    public static void procJsonFileOfJson(FolderName folderName, FileName fileName, String json) {
        procJsonFile(JsonPath.build(folderName, fileName), json);
    }

    public static void procJsonFileOfJson(String fileName, String json) {
        procJsonFile(JsonPath.build(FileName.of(fileName)), json);
    }

    public static void procJsonFileOfJsonForFullFilename(String filename, String json) {
        procJsonFile(filename, json);
    }

    private static void procJsonFile(String path, String json) {
        if (StringUtils.isEmpty(path))
            throw new IllegalArgumentException("path 不可為空 !!");
        try {
            initFolderOfWholePath(path);
            Files.write(Paths.get(path), Arrays.asList(json), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initFolderOfWholePath(String wholePath) {
        int lastSlash = wholePath.lastIndexOf("/");
        inifFolderOfFolderPath(wholePath.substring(0, lastSlash));
    }

    private static void inifFolderOfFolderPath(String folderPath) {
        File file = new File(folderPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * [轉換 為 json]
     * 
     * @author cano
     * @since 2021/09/06
     */
    public static String toJson(Object obj) {
        String json = "";
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("", e);
        }
        return json;
    }

    /**
     * [建立 json] -含產生 .json 檔案
     * 
     * @author cano
     * @since 2021/09/06
     */
    public static void buildJackson(Object obj) {
        procJsonFileOfJson("D:/Desktop/buildJackson.json", toJson(obj));
    }

    /**
     * [讀取 json] -from .json 檔案
     * 
     * @author cano
     * @since 2021/09/06
     */
    public static String readJson(FolderName folderName, FileName fileName) {
        return readJson(JsonPath.build(folderName, fileName));
    }

    public static String readJson(FileName fileName) {
        return readJson(JsonPath.build(fileName));
    }

    public static <T> T readJson(String path, Class<T> returnClass) {
        try {
            return mapper.readValue(readJson(path), returnClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readJson(String path) {
        if (StringUtils.isEmpty(path))
            throw new IllegalArgumentException("path 不可為空 !!");
        File file = new File(path);
        BufferedReader in = null;
        StringBuffer jsonSb = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            jsonSb = new StringBuffer();
            while ((line = in.readLine()) != null)
                jsonSb.append(line);
        } catch (Exception e) {
            logger.error(e.getMessage() + "[path=" + path + "]", e);
        }
        return jsonSb.toString();
    }

    public static class JsonPath {

        public static final String ROOT_PATH = "D:/Desktop/_asx";
        public static final String DEFAULT_FOLDER = "/_json.test.data";

        private JsonPath() {
            super();
        }

        public static String build(FileName fileName) {
            String file = checkFileName(fileName).orElse("temp_" + new Date().getTime());
            return String.format("%s/%s/%s.json", ROOT_PATH, DEFAULT_FOLDER, file);
        }

        public static String build(FolderName folderName, FileName fileName) {
            String folder = checkFolderName(folderName).orElse(DEFAULT_FOLDER);
            String file = checkFileName(fileName).orElse("temp_" + new Date().getTime());
            return String.format("%s/%s/%s.json", ROOT_PATH, folder, file);
        }

        static Optional<String> checkFolderName(FolderName folderName) {
            return Optional.ofNullable(folderName.get());
        }

        static Optional<String> checkFileName(FileName fileName) {
            return Optional.ofNullable(fileName.get());
        }
    }

    public static class FileName {
        String fileName;

        FileName(String fileName) {
            this.fileName = fileName;
        }

        public static FileName of(String fileName) {
            return new FileName(fileName);
        }

        String get() {
            return fileName;
        }
    }

    public static class FolderName {
        String folderName;

        FolderName(String folderName) {
            this.folderName = folderName;
        }

        public static FolderName of(String folderName) {
            return new FolderName(folderName);
        }

        String get() {
            return folderName;
        }
    }

}
