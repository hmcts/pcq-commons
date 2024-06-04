package uk.gov.hmcts.reform.pcq.commons.utils;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.hmcts.reform.pcq.commons.model.SubmitResponse;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("PMD.TooManyMethods")
class PcqUtilsTest {

    // Timestamp is Saturday, 23 May 2020 15:12:00 GMT
    private static final Long EPOCH_TIMESTAMP = 1590246720000L;
    private static final String STRING_TIMESTAMP = "2020-05-23T15:12:00.000Z";

    private static final String DOB_VALIDATION_MSG = "Dob validation should not return true";

    @Test
    void testConvertTimeStampToStringSuccess() {
        String stringConversion = PcqUtils.convertTimeStampToString(new Timestamp(EPOCH_TIMESTAMP));

        assertEquals(STRING_TIMESTAMP, stringConversion, "String conversion different.");
    }

    @Test
    void testConvertDateToString() {
        String stringConversion = PcqUtils.convertDateToString(new Date(EPOCH_TIMESTAMP));

        assertEquals(STRING_TIMESTAMP, stringConversion, "String conversion different.");
    }

    @Test
    void testGenerateResponseEntity() {
        String testPcqId = "1234567890";
        HttpStatus testHttpStatus = HttpStatus.OK;
        String testMessage = "Test Message";

        ResponseEntity<Object> responseEntity = PcqUtils.generateResponseEntity(testPcqId, testHttpStatus, testMessage);

        String responseEntityString =
                "<200 OK OK,{pcqId=1234567890, responseStatusCode=200, responseStatus=Test Message},[]>";

        assertNotNull(responseEntity, "Response entity is null.");
        assertEquals(responseEntityString, responseEntity.toString(), "Response entity different.");
    }

    @Test
    void testGetTimeFromString() {
        Timestamp timestampConversion = PcqUtils.getTimeFromString(STRING_TIMESTAMP);

        assertEquals(EPOCH_TIMESTAMP, timestampConversion.getTime(), "Timestamp conversion different.");
    }

    @Test
    void testGetDateFromString() {
        Date dateConversion = PcqUtils.getDateFromString(STRING_TIMESTAMP);

        assertEquals("2020-05-23", dateConversion.toString(), "Date conversion different.");
    }

    @Test
    void testGetDateTimeInPast() {
        Timestamp timestampInPast = PcqUtils.getDateTimeInPast(3L);
        LocalDate dateInPast = timestampInPast.toLocalDateTime().toLocalDate();

        Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
        LocalDate dateNow = timestampNow.toLocalDateTime().toLocalDate();

        Long daysBetween = ChronoUnit.DAYS.between(dateInPast, dateNow);

        assertEquals(3L, daysBetween, "Days in past different.");
    }

    @Test
    void testGenerateSubmitResponseEntity() {
        String testPcqId = "1234567890";
        HttpStatus testHttpStatus = HttpStatus.OK;
        String testMessage = "Test Message";

        ResponseEntity<SubmitResponse> responseEntity =
                PcqUtils.generateSubmitResponseEntity(testPcqId, testHttpStatus, testMessage);

        assertNotNull(responseEntity, "Response entity is null.");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(),
                "Submit response HttpStatus different.");
        assertEquals(testPcqId, Objects.requireNonNull(responseEntity.getBody()).getPcqId(),
                "Submit response PcqId different.");
        assertEquals(testMessage, Objects.requireNonNull(responseEntity.getBody()).getResponseStatus(),
                "Submit response message different.");
    }

    @Test
    void testExtractDcnNumberSuccess() {
        String testFileName = "1789034567_01-01-1900-12-00-00.zip";

        String extractedDcnNumber = PcqUtils.extractDcnNumberFromFile(testFileName);

        assertEquals("1789034567", extractedDcnNumber, "DCN Number Different.");
    }

    @Test
    void testExtractDcnNumberEmpty() {
        String testFileName = "";

        String extractedDcnNumber = PcqUtils.extractDcnNumberFromFile(testFileName);

        assertNull(extractedDcnNumber, "DCN Number Different.");
    }

    @Test
    void testExtractDcnNumberNull() {
        String extractedDcnNumber = PcqUtils.extractDcnNumberFromFile(null);

        assertNull(extractedDcnNumber, "DCN Number Different.");
    }

    @Test
    void testGetCurrentCompletedDate() {
        Pattern pattern = Pattern.compile("\\d{4}-[01]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\d:[0-5]\\d(?:\\.\\d+)?Z?");
        Matcher matcher = pattern.matcher(PcqUtils.getCurrentCompletedDate());

        assertTrue(matcher.matches(), "Completed Date is in wrong format");
    }

    @Test
    void testGenerateUuidNotNull() {
        assertNotNull(PcqUtils.generateUuid(), "Uuid is null");
    }

    @Test
    void testGenerateUuidRandom() {
        String pcqIdOne = PcqUtils.generateUuid();
        String pcqIdSecond = PcqUtils.generateUuid();

        assertNotEquals(pcqIdOne, pcqIdSecond, "PcqIds are not random");
    }

    @Test
    void invalidDobCharacters() {
        // User has not supplied any dob data. So the day, month and year will be empty strings.
        String invalidDob = "--";
        boolean isDobValid = PcqUtils.isDobValid(invalidDob);
        assertFalse(isDobValid, DOB_VALIDATION_MSG);

        // User supplied invalid characters in the day field.
        String invalidDob2 = "--Abba";
        isDobValid = PcqUtils.isDobValid(invalidDob2);
        assertFalse(isDobValid, DOB_VALIDATION_MSG);

        // User supplied invalid characters in the month field.
        String invalidDob3 = "-asdsd-";
        isDobValid = PcqUtils.isDobValid(invalidDob3);
        assertFalse(isDobValid, DOB_VALIDATION_MSG);

        // User supplied invalid characters in the year field.
        String invalidDob4 = "ipip--01";
        isDobValid = PcqUtils.isDobValid(invalidDob4);
        assertFalse(isDobValid, DOB_VALIDATION_MSG);

        // User supplied invalid characters in the all fields.
        String invalidDob5 = "ipip-asdsd-Abba";
        isDobValid = PcqUtils.isDobValid(invalidDob5);
        assertFalse(isDobValid, DOB_VALIDATION_MSG);
    }

    @Test
    void invalidDobDay() {
        //User has supplied an numeric but invalid day field
        String invalidDobDay = "1900-01-00";
        boolean isDobValid = PcqUtils.isDobValid(invalidDobDay);
        assertFalse(isDobValid, DOB_VALIDATION_MSG);

        String invalidDobDay2 = "1900-01-32";
        isDobValid = PcqUtils.isDobValid(invalidDobDay2);
        assertFalse(isDobValid, DOB_VALIDATION_MSG);

        String invalidDobDay3 = "1900-02-30";
        isDobValid = PcqUtils.isDobValid(invalidDobDay3);
        assertFalse(isDobValid, DOB_VALIDATION_MSG);
    }

    @Test
    void invalidDobMonth() {
        //User has supplied an numeric but invalid month field
        String invalidDobMonth = "1900-00-01";
        boolean isDobValid = PcqUtils.isDobValid(invalidDobMonth);
        assertFalse(isDobValid, DOB_VALIDATION_MSG);

        String invalidDobMonth2 = "1900-13-30";
        isDobValid = PcqUtils.isDobValid(invalidDobMonth2);
        assertFalse(isDobValid, DOB_VALIDATION_MSG);
    }

    @Test
    void invalidDobYear() {
        //User has supplied an numeric but invalid year field
        String invalidDobYear = "01-01-01";
        boolean isDobValid = PcqUtils.isDobValid(invalidDobYear);
        assertFalse(isDobValid, DOB_VALIDATION_MSG);

        String invalidDobYear2 = "21234-12-30";
        isDobValid = PcqUtils.isDobValid(invalidDobYear2);
        assertFalse(isDobValid, DOB_VALIDATION_MSG);
    }

    @Test
    void dobValidation() {
        //User has supplied an numeric and valid dob data
        String validDob = "2001-01-31";
        boolean isDobValid = PcqUtils.isDobValid(validDob);
        assertTrue(isDobValid, "Dob validation should not return false");

        String validDob2 = "2000-02-29";
        isDobValid = PcqUtils.isDobValid(validDob2);
        assertTrue(isDobValid, "Dob validation should not return false");

        String validDob3 = "1951-06-30";
        isDobValid = PcqUtils.isDobValid(validDob3);
        assertTrue(isDobValid, "Dob validation should not return false");
    }

    @Test
    void testCompleteDobString() {
        Pattern pattern = Pattern.compile("\\d{4}-[01]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\d:[0-5]\\d(?:\\.\\d+)?Z?");
        String dobPartial = "1900-01-01";
        String completeDob = PcqUtils.generateCompleteDobString(dobPartial);
        Matcher matcher = pattern.matcher(completeDob);
        assertTrue(matcher.matches(), "Dob string is not valid.");
    }

    @Test
    void testNullIfEmpty() {
        assertNull(PcqUtils.nullIfEmpty(""), "Empty string is not null.");
    }

    @Test
    void testAuthorisationToken() {
        String a256bitSecretKey = "a-very-long-and-boring-secret-key";
        String token = PcqUtils.generateAuthorizationToken(a256bitSecretKey, "TestSubject", "TestAuthority");
        assertThat(token).startsWith("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0U3ViamVjdCIsImF1dGhvcml0aWVzIjpbIlRlc3RBd");
    }

}
