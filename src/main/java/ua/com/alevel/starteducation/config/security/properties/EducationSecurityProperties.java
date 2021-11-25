package ua.com.alevel.starteducation.config.security.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@Setter
@Getter
@Validated
@ConfigurationProperties(prefix = "education.security")
public class EducationSecurityProperties {

    @Valid
    @NestedConfigurationProperty
    private EducationJWTProperties jwt;

    private Map<@NotBlank String, @Valid EducationTeacherProperties> teachers;

}
