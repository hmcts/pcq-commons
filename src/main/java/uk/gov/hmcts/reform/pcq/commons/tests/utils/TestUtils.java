package uk.gov.hmcts.reform.pcq.commons.tests.utils;

import org.springframework.core.io.ClassPathResource;
import tools.jackson.databind.ObjectMapper;
import uk.gov.hmcts.reform.pcq.commons.model.PcqAnswerRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by the components in their test framework.
 */
public class TestUtils {

    private static final String CO_RELATION_ID_FOR_TEST = "Test-Id";

    /**
     * Obtains a JSON String from a JSON file in the classpath (Resources directory).
     * @param fileName - The name of the Json file from classpath.
     * @return - JSON String from the file.
     * @throws IOException - If there is any issue when reading from the file.
     */
    public static String jsonStringFromFile(String fileName) throws IOException {
        File resource = new ClassPathResource(fileName).getFile();
        return new String(Files.readAllBytes(resource.toPath()));
    }

    public static PcqAnswerRequest jsonObjectFromString(String jsonString) throws IOException {
        return new ObjectMapper().readValue(jsonString, PcqAnswerRequest.class);
    }

    public static List<String> getTestHeader() {
        List<String> headerList =  new ArrayList<>();
        headerList.add(CO_RELATION_ID_FOR_TEST);

        return headerList;
    }

    private Timestamp getTimeFromString(String timeStr) {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(timeStr));

        return Timestamp.valueOf(localDateTime);
    }

}
