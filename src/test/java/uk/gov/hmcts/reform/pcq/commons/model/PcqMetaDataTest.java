package uk.gov.hmcts.reform.pcq.commons.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PcqMetaDataTest {

    private static final String PO_BOX = "123";
    private static final String JURISDICTION = "TEST_SERVICE";
    private static final String DELIVERY_DATE = "01-01-2000T12:30:45.000z";
    private static final String OPENING_DATE = "03-01-2000T12:30:45.000z";
    private static final String ZIP_CREATED_DATE = "31-12-1999T12:30:45.000z";
    private static final String ZIP_FILE_NAME = "11323121_Test.zip";
    private static final String ORIGINATING_DCN_NUMBER = "230450608920";

    @Test
    void testMetaDataEmptyScanableItems() {
        PcqMetaData pcqMetaData = new PcqMetaData();
        pcqMetaData.setPoBox(PO_BOX);
        pcqMetaData.setDeliveryDate(DELIVERY_DATE);
        pcqMetaData.setJurisdiction(JURISDICTION);
        pcqMetaData.setOpeningDate(OPENING_DATE);
        pcqMetaData.setOriginatingDcnNumber(ORIGINATING_DCN_NUMBER);
        pcqMetaData.setZipFileCreatedDate(ZIP_CREATED_DATE);
        pcqMetaData.setZipFileName(ZIP_FILE_NAME);
        pcqMetaData.setScannableItems(null);

        assertEquals(PO_BOX, pcqMetaData.getPoBox(), "PO Box is invalid");
        assertEquals(DELIVERY_DATE, pcqMetaData.getDeliveryDate(), "Delivery is invalid");
        assertEquals(JURISDICTION, pcqMetaData.getJurisdiction(), "Jurisdiction is invalid");
        assertEquals(OPENING_DATE, pcqMetaData.getOpeningDate(), "Opening Date is invalid");
        assertEquals(ORIGINATING_DCN_NUMBER, pcqMetaData.getOriginatingDcnNumber(), "DCN is invalid");
        assertEquals(ZIP_CREATED_DATE, pcqMetaData.getZipFileCreatedDate(), "Zip file created date is invalid");
        assertEquals(ZIP_FILE_NAME, pcqMetaData.getZipFileName(), "Zip File name is invalid");
        assertNull(pcqMetaData.getScannableItems(), "Scannable Items is Invalid");
    }

    @Test
    void testMetaDataWithScanableItems() {
        PcqMetaData pcqMetaData = getPcqMetaData();

        assertEquals(PO_BOX, pcqMetaData.getPoBox(), "PO Box is invalid");
        assertEquals(DELIVERY_DATE, pcqMetaData.getDeliveryDate(), "Delivery is invalid");
        assertEquals(JURISDICTION, pcqMetaData.getJurisdiction(), "Jurisdiction is invalid");
        assertEquals(OPENING_DATE, pcqMetaData.getOpeningDate(), "Opening Date is invalid");
        assertEquals(ORIGINATING_DCN_NUMBER, pcqMetaData.getOriginatingDcnNumber(), "DCN is invalid");
        assertEquals(ZIP_CREATED_DATE, pcqMetaData.getZipFileCreatedDate(), "Zip file created date is invalid");
        assertEquals(ZIP_FILE_NAME, pcqMetaData.getZipFileName(), "Zip File name is invalid");
        assertNotNull(pcqMetaData.getScannableItems(), "Scannable Items is Invalid");

        PcqScannableItems[] scannableItemsTest = pcqMetaData.getScannableItems();
        assertEquals(2, scannableItemsTest.length, "Scannable Items length is not correct.");
        assertEquals("DCN_Test", scannableItemsTest[0].getDocumentControlNumber(),
                "Document control nunber is not correct.");
        assertEquals("Ocr_1", scannableItemsTest[0].getOcrData(),
                "Ocr Data is not correct.");
        assertEquals("DCN_Test2", scannableItemsTest[1].getDocumentControlNumber(),
                "Document control nunber is not correct.");
        assertEquals("Ocr_2", scannableItemsTest[1].getOcrData(),
                "Ocr Data is not correct.");
    }

    private static PcqMetaData getPcqMetaData() {
        PcqMetaData pcqMetaData = new PcqMetaData();
        pcqMetaData.setPoBox(PO_BOX);
        pcqMetaData.setDeliveryDate(DELIVERY_DATE);
        pcqMetaData.setJurisdiction(JURISDICTION);
        pcqMetaData.setOpeningDate(OPENING_DATE);
        pcqMetaData.setOriginatingDcnNumber(ORIGINATING_DCN_NUMBER);
        pcqMetaData.setZipFileCreatedDate(ZIP_CREATED_DATE);
        pcqMetaData.setZipFileName(ZIP_FILE_NAME);

        PcqScannableItems scannableItems = new PcqScannableItems();
        scannableItems.setDocumentControlNumber("DCN_Test");
        scannableItems.setOcrData("Ocr_1");

        PcqScannableItems scannableItems1 = new PcqScannableItems();
        scannableItems1.setDocumentControlNumber("DCN_Test2");
        scannableItems1.setOcrData("Ocr_2");

        PcqScannableItems[] pcqScannableItems = {scannableItems, scannableItems1};

        pcqMetaData.setScannableItems(pcqScannableItems);
        return pcqMetaData;
    }
}
