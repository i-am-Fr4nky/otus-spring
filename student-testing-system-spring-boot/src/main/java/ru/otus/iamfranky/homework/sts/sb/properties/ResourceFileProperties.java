package ru.otus.iamfranky.homework.sts.sb.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "student.testing.system.resource.file")
public class ResourceFileProperties {

    private String path;
    private String delimiter;
}
