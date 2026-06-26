package com.paypal.subscription.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class DataUtils {

    public static String testDataPath = "src/test/resources/data/";


    public static String getJsonData(String fileName , String field){
        try{
            FileReader File = new FileReader(testDataPath+fileName+".json");
            JsonElement jsonFile = JsonParser.parseReader(File);

            return jsonFile.getAsJsonObject().get(field).getAsString();
        }
        catch (Exception e){
            LogsUtils.error("Faild to retrieve Json File ",e.toString());
        }
        return "";
    }

}
