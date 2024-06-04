package uk.gov.hmcts.reform.pcq.commons.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PcqScannableItemsTest {

    private static final String DCN_NUMBER = "102020303";
    private static final String DOCUMENT_TYPE = "EQ11";
    private static final String SCANNING_DATE = "23-03-2000";
    private static final String OCR_ACCURACY = "Decent";
    private static final String OCR_DATA = "223aasdsddqeqwe";
    private static final String FILE_NAME = "metadata.json";
    private static final String NOTES = "Ink Spilled";

    @Test
    void testPcqScannableItems() {
        PcqScannableItems pcqScannableItems = new PcqScannableItems();
        pcqScannableItems.setOcrData(OCR_DATA);
        pcqScannableItems.setDocumentControlNumber(DCN_NUMBER);
        pcqScannableItems.setDocumentType(DOCUMENT_TYPE);
        pcqScannableItems.setFileName(FILE_NAME);
        pcqScannableItems.setNotes(NOTES);
        pcqScannableItems.setOcrAccuracy(OCR_ACCURACY);
        pcqScannableItems.setScanningDate(SCANNING_DATE);

        assertEquals(OCR_DATA, pcqScannableItems.getOcrData(), "Ocr Data is invalid");
        assertEquals(DCN_NUMBER, pcqScannableItems.getDocumentControlNumber(), "DCN Number is invalid");
        assertEquals(DOCUMENT_TYPE, pcqScannableItems.getDocumentType(), "Document Type is invalid");
        assertEquals(FILE_NAME, pcqScannableItems.getFileName(), "File Name is invalid");
        assertEquals(NOTES, pcqScannableItems.getNotes(), "Notes is invalid");
        assertEquals(OCR_ACCURACY, pcqScannableItems.getOcrAccuracy(), "Ocr Accuracy is invalid");
        assertEquals(SCANNING_DATE, pcqScannableItems.getScanningDate(), "Scanning Date is invalid");
    }
}
