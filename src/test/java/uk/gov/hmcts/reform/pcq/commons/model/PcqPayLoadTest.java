package uk.gov.hmcts.reform.pcq.commons.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PcqPayLoadTest {

    private static final String TEST_FIELD_NAME_1 = "TEST_FIELD1";
    private static final String TEST_FIELD_NAME_2 = "TEST_FIELD2";
    private static final String TEST_FIELD_VALUE_1 = "TEST_FIELD1_VALUE";
    private static final String TEST_FIELD_VALUE_2 = "TEST_FIELD2_VALUE";

    @Test
    void testPcqPayLoad() {
        PcqPayloadContents testPayLoadContents = new PcqPayloadContents();
        testPayLoadContents.setFieldName(TEST_FIELD_NAME_1);
        testPayLoadContents.setFieldValue(TEST_FIELD_VALUE_1);

        PcqPayloadContents testPayLoadContents1 = new PcqPayloadContents();
        testPayLoadContents1.setFieldName(TEST_FIELD_NAME_2);
        testPayLoadContents1.setFieldValue(TEST_FIELD_VALUE_2);

        PcqPayloadContents[] payloadContents = {testPayLoadContents, testPayLoadContents1};

        PcqPayLoad pcqPayLoad = new PcqPayLoad();
        pcqPayLoad.setMetaDataContents(payloadContents);

        PcqPayloadContents[] testContents = pcqPayLoad.getMetaDataContents();
        assertNotNull(testContents, "Payload contents are null");
        assertEquals(2, testContents.length, "Payload contents length is invalid");
        assertEquals(TEST_FIELD_NAME_1, testContents[0].getFieldName(), "Field Name 1 is invalid");
        assertEquals(TEST_FIELD_NAME_2, testContents[1].getFieldName(), "Field Name 2 is invalid");
        assertEquals(TEST_FIELD_VALUE_1, testContents[0].getFieldValue(), "Field Value 1 is invalid");
        assertEquals(TEST_FIELD_VALUE_2, testContents[1].getFieldValue(), "Field Value 2 is invalid");
    }

}
