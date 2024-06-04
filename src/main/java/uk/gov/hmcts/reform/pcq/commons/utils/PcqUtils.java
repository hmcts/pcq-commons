package uk.gov.hmcts.reform.pcq.commons.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.HtmlUtils;
import uk.gov.hmcts.reform.pcq.commons.model.SubmitResponse;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.SecretKey;

@Slf4j
public class PcqUtils {

    private static final String COMPLETED_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String DOB_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static final int START_INDEX = 0;
    private static final String SEPARATOR = "_";
    private static final String DOB_REGEX = "\\d{4}-[01]\\d-[0-3]\\d";
    private static final String DOB_TIME_CONSTANT = "T00:00:00.000Z";

    private PcqUtils() {
    }

    public static String convertTimeStampToString(Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(COMPLETED_DATE_FORMAT, Locale.UK);
        return dateFormat.format(timestamp);
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DOB_FORMAT, Locale.UK);
        return dateFormat.format(date);
    }

    public static ResponseEntity<Object> generateResponseEntity(String pcqId, HttpStatus code, String message) {

        Map<String, Object> responseMap = new ConcurrentHashMap<>();
        responseMap.put("pcqId", HtmlUtils.htmlEscape(pcqId));
        responseMap.put("responseStatus", message);
        responseMap.put("responseStatusCode", String.valueOf(code.value()));

        return new ResponseEntity<>(responseMap, code);
    }

    public static Timestamp getTimeFromString(String timeStampStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(COMPLETED_DATE_FORMAT);
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(timeStampStr));

        return Timestamp.valueOf(localDateTime);
    }

    public static Date getDateFromString(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DOB_FORMAT);
        LocalDate localDate = LocalDate.from(formatter.parse(dateStr));
        return Date.valueOf(localDate);
    }

    public static Timestamp getDateTimeInPast(long numberOfDays) {
        LocalDateTime currentDateTime = LocalDateTime.now(Clock.systemUTC());

        return Timestamp.valueOf(currentDateTime.minusDays(numberOfDays));
    }

    public static ResponseEntity<SubmitResponse> generateSubmitResponseEntity(String pcqId, HttpStatus code,
                                                                              String message) {

        SubmitResponse submitResponse = new SubmitResponse();
        submitResponse.setPcqId(pcqId);
        submitResponse.setResponseStatus(message);
        submitResponse.setResponseStatusCode(String.valueOf(code.value()));

        return new ResponseEntity<>(submitResponse, code);
    }

    public static String extractDcnNumberFromFile(String fileName) {
        //Based on the Exela JSON schema, the zip file name format will be unqiueId_datetimecreated.zip where
        //the datetimecreated will be of format - DD-MM-YYYY-HH-MM-SS.
        //The DCN Number will be the uniqueId that needs to be extracted.
        if (!StringUtils.isEmpty(fileName)) {
            return fileName.substring(START_INDEX, fileName.indexOf(SEPARATOR));
        }

        return null;
    }

    public static String getCurrentCompletedDate() {
        Timestamp completedTime = Timestamp.valueOf(LocalDateTime.now());
        SimpleDateFormat dateFormat = new SimpleDateFormat(COMPLETED_DATE_FORMAT, Locale.UK);
        return dateFormat.format(completedTime);
    }

    public static String generateUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static boolean isDobValid(String suppliedDob) {
        // Step 1 - Check the format is correct.
        Pattern pattern = Pattern.compile(DOB_REGEX);
        Matcher matcher = pattern.matcher(suppliedDob);
        if (matcher.matches()) {
            // Step 2 - Convert to Date object and confirm it is correct
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(suppliedDob);
                return true;
            } catch (ParseException e) {
                log.error("Dob supplied is invalid - " + e.getMessage());
                return false;
            }
        }

        return false;
    }

    public static String generateCompleteDobString(String suppliedDob) {
        return suppliedDob + DOB_TIME_CONSTANT;
    }

    @SuppressWarnings("PMD.UseStringBufferForStringAppends")
    public static String formatDobField(String field) {
        String returnField = field;
        if (!StringUtils.isEmpty(field) && field.length() == 1) {
            returnField = "0" + returnField;
        }
        return returnField;
    }

    public static String nullIfEmpty(String strObject) {
        return StringUtils.defaultIfEmpty(strObject, null);
    }

    public static String generateAuthorizationToken(String secretKey, String subject, String authoritiesStr) {
        List<String> authorities = new ArrayList<>();
        long currentTime = System.currentTimeMillis();
        authorities.add(authoritiesStr);

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        return Jwts.builder()
                .subject(subject)
                .claim("authorities", authorities)
                .issuedAt(new Date(currentTime))
                .expiration(new Date(currentTime + 500_000))  // in milliseconds
                .signWith(key)
                .compact();
    }
}
