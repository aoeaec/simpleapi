package org.gaurav.simpleapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql({"/data_setup01.sql"})
class SimpleapiApplicationTests {

	@Test
	void contextLoads() {
	}

}
