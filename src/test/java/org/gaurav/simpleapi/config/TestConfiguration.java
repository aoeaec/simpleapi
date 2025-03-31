package org.gaurav.simpleapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.gaurav.simpleapi.service",
        "com.gaurav.simpleapi.config",
        "com.gaurav.simpleapi.repository",
        "com.gaurav.simpleapi.controller"

})
public class TestConfiguration {

}
