package Utils;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class APIUtils {

    public static Map<String,Object> apiYaml;

    public static Map<String, Object> parseYaml(String yamlName) throws IOException {

        String path = "src\\test\\resources\\config\\api\\"+yamlName+".yaml";
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
            apiYaml = yaml.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }

        return apiYaml;
    }

    public static void getCall(String apiName, Map<String, Object> data){

    }

    public static void postCall(String apiName, Map<String, Object> data){

    }

    public static void putCall(String apiName, Map<String, Object> data){

    }

    public static void deleteCall(String apiName, Map<String, Object> data){

    }


//    public static void main(String[] args) throws IOException {
//        parseYaml("getEmployee");
//        System.out.println(apiYaml.get("method"));
//    }
}
