package ru.emitrohin.roox.testdata;

import ru.emitrohin.roox.matcher.ModelMatcher;
import ru.emitrohin.roox.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */
public class CustomerTestData {

    public final static List<Customer> TEST_CUSTOMERS = new ArrayList<>();

    public static final ModelMatcher<Customer> MATCHER = ModelMatcher.of(Customer.class,
            (expected, actual) -> expected == actual ||
                    (
                            Objects.equals(expected.getId(), actual.getId())
                                    && Objects.equals(expected.getLogin(), actual.getLogin())
                                    && Objects.equals(expected.getPassword(), actual.getPassword())
                                    && Objects.equals(expected.getBalance(), actual.getBalance())
                                    && Objects.equals(expected.getFirstName(), actual.getFirstName())
                                    && Objects.equals(expected.getLastName(), actual.getLastName())
                                    && Objects.equals(expected.getMiddleName(), actual.getMiddleName())
                                    && Objects.equals(expected.isEnabled(), actual.isEnabled())
                    )
    );

    static {
        TEST_CUSTOMERS.add(new Customer(100000, "abc", "$2a$10$//Qolv9hoJK1coC0rkDo4OdvWOtc8em6qJ.hdqnOyJT.SMiEj16Xq", "Иванов", "Иван", "Иванович", 1000, true, null));
        TEST_CUSTOMERS.add(new Customer(100001, "def", "$2a$10$Fue5h7hmiSVcBRQLREzMfefyhZfv.AUn0OUuZUpfvu/zUnreulz1e", "Johnson", "John", "Jay", 5000, true, null));
        TEST_CUSTOMERS.add(new Customer(100002, "ghi", "$2a$10$yGcdpygkCN1Ei0.HM2jDhuUb2fGsRlS2A6syb0HX2W2ydXw7atz9W", "Xian", "Xion", "Xian", 200, true, null));
        TEST_CUSTOMERS.add(new Customer(100003, "xyz", "$2a$10$OV4/veNOnydoaGwL3SSuuODovkU.yLEuojWJlzCMpyoOlntCPOCsa", "", "", "", 0, false, null));
    }
}
