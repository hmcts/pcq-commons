package uk.gov.hmcts.reform.pcq.commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * This is the object representing the REST model for the PcqAnswers entity in the submit Answers API.
 * The PMD warnings are suppressed because this is a JSON object and hence can't be split into two different classes.
 * The properties below are not primitives because JSON interprets primitive as 0 if the value is not supplied which
 * has a different meaning to actual value coming from the front-end.
 */
@SuppressWarnings({"PMD.ExcessivePublicCount", "PMD.TooManyFields"})
@Getter
@Setter
@NoArgsConstructor
public class PcqAnswers implements Serializable {

    @Serial
    private static final long serialVersionUID = 3328743L;

    @JsonProperty("dob_provided")
    private Integer dobProvided;

    @JsonProperty("dob")
    private String dob;

    @JsonProperty("language_main")
    private Integer languageMain;

    @JsonProperty("language_other")
    private String languageOther;

    @JsonProperty("english_language_level")
    private Integer englishLanguageLevel;

    @JsonProperty("sex")
    private Integer sex;

    @JsonProperty("gender_different")
    private Integer genderDifferent;

    @JsonProperty("gender_other")
    private String genderOther;

    @JsonProperty("sexuality")
    private Integer sexuality;

    @JsonProperty("sexuality_other")
    private String sexualityOther;

    @JsonProperty("marriage")
    private Integer marriage;

    @JsonProperty("ethnicity")
    private Integer ethnicity;

    @JsonProperty("ethnicity_other")
    private String ethnicityOther;

    @JsonProperty("religion")
    private Integer religion;

    @JsonProperty("religion_other")
    private String religionOther;

    @JsonProperty("disability_conditions")
    private Integer disabilityConditions;

    @JsonProperty("disability_impact")
    private Integer disabilityImpact;

    @JsonProperty("disability_vision")
    private Integer disabilityVision;

    @JsonProperty("disability_hearing")
    private Integer disabilityHearing;

    @JsonProperty("disability_mobility")
    private Integer disabilityMobility;

    @JsonProperty("disability_dexterity")
    private Integer disabilityDexterity;

    @JsonProperty("disability_learning")
    private Integer disabilityLearning;

    @JsonProperty("disability_memory")
    private Integer disabilityMemory;

    @JsonProperty("disability_mental_health")
    private Integer disabilityMentalHealth;

    @JsonProperty("disability_stamina")
    private Integer disabilityStamina;

    @JsonProperty("disability_social")
    private Integer disabilitySocial;

    @JsonProperty("disability_other")
    private Integer disabilityOther;

    @JsonProperty("disability_other_details")
    private String disabilityConditionOther;

    @JsonProperty("disability_none")
    private Integer disabilityNone;

    @JsonProperty("pregnancy")
    private Integer pregnancy;

    @JsonProperty("opt_out")
    private Boolean optOut;

}
