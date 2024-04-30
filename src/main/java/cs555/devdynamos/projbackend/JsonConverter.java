package cs555.devdynamos.projbackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

public class JsonConverter {
    public static String convertToJSON(List<Map<String, Object>> eegDataListMap) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(eegDataListMap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
