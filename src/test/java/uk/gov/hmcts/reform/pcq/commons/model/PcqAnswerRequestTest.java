package uk.gov.hmcts.reform.pcq.commons.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


class PcqAnswerRequestTest {
    private static final String PCQ_ID = "TEST_PCQ";
    private static final String CASE_ID = "CASE_ID";
    private static final String PARTY_ID = "PARTY_ID";
    private static final Integer CHANNEL = 1;
    private static final String SERVICE_ID = "Service_1";
    private static final String ACTOR = "Actor_1";
    private static final Integer VERSION_NO = 1;
    private static final String DCN_NUMBER = "TEST_DCN";
    private static final String FORM_ID = "Test_Form_Id";
    private static final String OPT_OUT = "Y";
    private static final String COMPLETED_DATE = "01-01-2000T10:00:00.000Z";
    private static final String DOB = "01-01-1999T00:00:00.000Z";
    private static final Integer ANSWER_RESPONSE_INT = 1;
    private static final String ANSWER_RESPONSE_TEXT = "Other Test";
    private static final Boolean ANSWER_OPT_OUT = true;

    @Test
    void testPcqAnswerRequest() {
        PcqAnswerRequest pcqAnswerRequest = new PcqAnswerRequest();
        pcqAnswerRequest.setActor(ACTOR);
        pcqAnswerRequest.setCaseId(CASE_ID);
        pcqAnswerRequest.setChannel(CHANNEL);
        pcqAnswerRequest.setCompletedDate(COMPLETED_DATE);
        pcqAnswerRequest.setDcnNumber(DCN_NUMBER);
        pcqAnswerRequest.setFormId(FORM_ID);
        pcqAnswerRequest.setOptOut(OPT_OUT);
        pcqAnswerRequest.setPartyId(PARTY_ID);
        pcqAnswerRequest.setPcqId(PCQ_ID);
        pcqAnswerRequest.setServiceId(SERVICE_ID);
        pcqAnswerRequest.setVersionNo(VERSION_NO);

        PcqAnswers pcqAnswers = new PcqAnswers();
        pcqAnswers.setLanguageMain(ANSWER_RESPONSE_INT);
        pcqAnswers.setLanguageOther(ANSWER_RESPONSE_TEXT);
        pcqAnswers.setEnglishLanguageLevel(ANSWER_RESPONSE_INT);
        pcqAnswers.setDob(DOB);
        pcqAnswers.setDobProvided(ANSWER_RESPONSE_INT);
        pcqAnswers.setReligion(ANSWER_RESPONSE_INT);
        pcqAnswers.setReligionOther(ANSWER_RESPONSE_TEXT);
        pcqAnswers.setEthnicity(ANSWER_RESPONSE_INT);
        pcqAnswers.setEthnicityOther(ANSWER_RESPONSE_TEXT);
        pcqAnswers.setDisabilityConditions(ANSWER_RESPONSE_INT);
        pcqAnswers.setDisabilityImpact(ANSWER_RESPONSE_INT);
        pcqAnswers.setDisabilityVision(ANSWER_RESPONSE_INT);
        pcqAnswers.setDisabilityConditionOther(ANSWER_RESPONSE_TEXT);
        pcqAnswers.setDisabilityOther(ANSWER_RESPONSE_INT);
        pcqAnswers.setGenderDifferent(ANSWER_RESPONSE_INT);
        pcqAnswers.setGenderOther(ANSWER_RESPONSE_TEXT);
        pcqAnswers.setSex(ANSWER_RESPONSE_INT);
        pcqAnswers.setSexuality(ANSWER_RESPONSE_INT);
        pcqAnswers.setSexualityOther(ANSWER_RESPONSE_TEXT);
        pcqAnswers.setPregnancy(ANSWER_RESPONSE_INT);
        pcqAnswers.setMarriage(ANSWER_RESPONSE_INT);
        pcqAnswers.setOptOut(ANSWER_OPT_OUT);

        pcqAnswerRequest.setPcqAnswers(pcqAnswers);

        assertEquals(PCQ_ID, pcqAnswerRequest.getPcqId(), "Pcq Id is invalid");
        assertEquals(ACTOR, pcqAnswerRequest.getActor(), "Actor is invalid");
        assertEquals(CASE_ID, pcqAnswerRequest.getCaseId(), "Case Id is invalid");
        assertEquals(CHANNEL, pcqAnswerRequest.getChannel(), "Channel is invalid");
        assertEquals(COMPLETED_DATE, pcqAnswerRequest.getCompletedDate(), "Completed Date is invalid");
        assertEquals(DCN_NUMBER, pcqAnswerRequest.getDcnNumber(), "Dcn Number is invalid");
        assertEquals(FORM_ID, pcqAnswerRequest.getFormId(), "Form Id is invalid");
        assertEquals(OPT_OUT, pcqAnswerRequest.getOptOut(), "Opt Out is invalid");
        assertEquals(PARTY_ID, pcqAnswerRequest.getPartyId(), "Party Id is invalid");
        assertEquals(SERVICE_ID, pcqAnswerRequest.getServiceId(), "Service Id is invalid");
        assertEquals(VERSION_NO, pcqAnswerRequest.getVersionNo(), "Version No is invalid");

        PcqAnswers answers = pcqAnswerRequest.getPcqAnswers();
        assertNotNull(answers, "Pcq Answers is invalid");
        assertEquals(ANSWER_RESPONSE_INT, answers.getLanguageMain(), "Language Main is invalid");
        assertEquals(ANSWER_RESPONSE_TEXT, answers.getLanguageOther(), "Language Other is invalid");
        assertEquals(ANSWER_RESPONSE_INT, answers.getEnglishLanguageLevel(), "English Language Level is invalid");
        assertEquals(DOB, answers.getDob(), "Dob is invalid");
        assertEquals(ANSWER_RESPONSE_INT, answers.getDobProvided(), "Dob provided is invalid");
        assertEquals(ANSWER_RESPONSE_INT, answers.getReligion(), "Religion is invalid");
        assertEquals(ANSWER_RESPONSE_TEXT, answers.getReligionOther(), "Religion Other is invalid");
        assertEquals(ANSWER_RESPONSE_INT, answers.getEthnicity(), "Ethnicity is invalid");
        assertEquals(ANSWER_RESPONSE_TEXT, answers.getEthnicityOther(), "Ethnicity Other is invalid");
        assertEquals(ANSWER_RESPONSE_INT, answers.getDisabilityConditions(), "Disability Conditions is invalid");
        assertEquals(ANSWER_RESPONSE_INT, answers.getDisabilityImpact(), "Disability Impact is invalid");
        assertEquals(ANSWER_RESPONSE_INT, answers.getDisabilityVision(), "Disability Vision is invalid");
        assertNull(answers.getDisabilityHearing(), "Disability Hearing is invalid");
        assertEquals(ANSWER_RESPONSE_INT, answers.getDisabilityOther(), "Disability Other is invalid");
        assertEquals(ANSWER_RESPONSE_TEXT, answers.getDisabilityConditionOther(),
                "Disability Other Condition is invalid");
        assertEquals(ANSWER_RESPONSE_INT, answers.getGenderDifferent(), "Gender Different is invalid");
        assertEquals(ANSWER_RESPONSE_TEXT, answers.getGenderOther(), "Gender Other is invalid");
        assertEquals(ANSWER_RESPONSE_INT, answers.getSex(), "Sex is invalid");
        assertEquals(ANSWER_RESPONSE_INT, answers.getSexuality(), "Sexuality is invalid");
        assertEquals(ANSWER_RESPONSE_TEXT, answers.getSexualityOther(), "Sexuality Other is invalid");
        assertEquals(ANSWER_RESPONSE_INT, answers.getPregnancy(), "Pregnancy is invalid");
        assertEquals(ANSWER_RESPONSE_INT, answers.getMarriage(), "Marriage is invalid");
        assertEquals(ANSWER_OPT_OUT, answers.getOptOut(), "OptOut is invalid");
    }

}
