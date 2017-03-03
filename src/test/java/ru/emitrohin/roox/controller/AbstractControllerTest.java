package ru.emitrohin.roox.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.emitrohin.roox.security.TokenAuthenticationFilter;
import ru.emitrohin.roox.service.CustomerService;

import javax.annotation.PostConstruct;


@RunWith(SpringRunner.class)
@Sql(scripts = {"/test-schema.sql", "/test-data.sql"}, config = @SqlConfig(encoding = "UTF-8"))
@SpringBootTest
abstract public class AbstractControllerTest {

    final int CUSTOMER_ID = 100001;
    final String LITERAL_STRING = "@me/";

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CustomerService customerService;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .addFilter(new TokenAuthenticationFilter(customerService))
                .build();
    }
}