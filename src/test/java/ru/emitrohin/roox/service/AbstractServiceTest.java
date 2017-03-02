package ru.emitrohin.roox.service;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@Sql(scripts = {"/test-schema.sql", "/test-data.sql"}, config = @SqlConfig(encoding = "UTF-8"))
@SpringBootTest
abstract public class AbstractServiceTest {

    protected final int CUSTOMER_ID = 100001;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

}