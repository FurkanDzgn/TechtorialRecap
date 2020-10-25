package Utils;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIUtils {

    public static Map<String,Object> apiYaml;
    public static RequestSpecification reqSpec;
    public static ResponseSpecification resSpec;
    public static Response response;
    public static String endpoint;
    public static DataStorage dataStorage = DataStorage.getInstance();
    public static Map<String,Object> responseData;

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

    public static void apiConfig() throws IOException {
        reqSpec = given();
        reqSpec.baseUri(Config.getProperty("apiconfigs","baseURI"))
                .basePath(Config.getProperty("apiconfigs","basePath"))
                .accept(apiYaml.get("accept").toString())
                .contentType(apiYaml.get("content-type").toString());
        resSpec = given().accept(apiYaml.get("accept").toString()).response();
    }

    private static void SetEndpoint(Map<String, Object> data) {
        for (String key:data.keySet()){
            if(key.equals(":param")){
                endpoint = endpoint.replace("$",data.get(key).toString());
            }
        }
    }


    public static void getCall(String apiName, Map<String, Object> data) throws IOException {

        apiConfig();
        endpoint = apiYaml.get("endpoint").toString();
        SetEndpoint(data);
        response = reqSpec
                .when().get(endpoint);
        response.then().statusCode(200);
        response.then().log().all();
        responseData = JsonFlattener.flattenAsMap(response.body().asString());
        dataStorage.jsonPayloads.put(apiName,responseData);
    }


    public static void postCall(String apiName, Map<String, Object> data) throws IOException {

        apiConfig();
        endpoint = apiYaml.get("endpoint").toString();
        SetEndpoint(data);
        Map<String,Object> requestData = JsonParser.parseJson(apiName,data);
        String strBody = JsonUnflattener.unflatten(requestData.toString());
        response = reqSpec
                .when().body(apiYaml.get(strBody))
                .post(endpoint);
        response.then().statusCode(201);
        response.then().log().all();

        dataStorage.jsonPayloads.put(apiName, JsonFlattener.flattenAsMap(response.body().toString()));


    }

    public static void putCall(String apiName, Map<String, Object> data) throws IOException {

        apiConfig();
    }

    public static void deleteCall(String apiName, Map<String, Object> data) throws IOException {

        apiConfig();
    }


//    public static void main(String[] args) throws IOException {
//        parseYaml("getEmployee");
//        System.out.println(apiYaml.get("method"));
//    }
}
