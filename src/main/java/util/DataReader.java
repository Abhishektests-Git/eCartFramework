package util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
    // JSON data reader
    public List<HashMap<String, Object>> getJsonData(String filePath)//object is taken so that we can take multiple strings if needed.
            throws StreamReadException, DatabindException, IOException {
        File file = new File(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, new TypeReference<List<HashMap<String, Object>>>() {});
    }

    // DataProvider for test data
    @DataProvider(parallel = true) //enable parallel data-driven tests
    public Object[][] getData() throws StreamReadException, DatabindException, IOException {
//        System.out.println("Reading data from JSON");
        List<HashMap<String, Object>> data = getJsonData(System.getProperty("user.dir")
                + File.separator + "TestData" + File.separator + "PurchaseOrder.json");

        Object[][] obj = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            obj[i][0] = data.get(i);
        }
        return obj;
    }
}
