package ru.emitrohin.roox.testdata;

import ru.emitrohin.roox.matcher.ModelMatcher;
import ru.emitrohin.roox.model.PartnerMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.emitrohin.roox.testdata.CustomerTestData.TEST_CUSTOMERS;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */
public class PartnerMappingTestData {

    public final static List<PartnerMapping> TEST_MAPPINGS = new ArrayList<>();

    public static final ModelMatcher<PartnerMapping> MATCHER = ModelMatcher.of(PartnerMapping.class,
            (expected, actual) -> expected == actual ||
                    (
                            Objects.equals(expected.getId(), actual.getId())
                                    && Objects.equals(expected.getPartnerId(), actual.getPartnerId())
                                    && Objects.equals(expected.getPartnerCustomerId(), actual.getPartnerCustomerId())
                                    //&& Objects.equals(expected.getCustomer().getId(), actual.getCustomer().getId())
                                    && Objects.equals(expected.getFirstName(), actual.getFirstName())
                                    && Objects.equals(expected.getLastName(), actual.getLastName())
                                    && Objects.equals(expected.getMiddleName(), actual.getMiddleName())
                                    && Arrays.equals(expected.getAvatarImage(), actual.getAvatarImage())
                    )
    );

    static {
        TEST_MAPPINGS.add(new PartnerMapping(100004, "id1", "sid1","Иванов","Иван", "Иванович", null, TEST_CUSTOMERS.get(0)));
        TEST_MAPPINGS.add(new PartnerMapping(100005, "id2", "sid2","Иванов","Иван", null, null, TEST_CUSTOMERS.get(0)));
        TEST_MAPPINGS.add(new PartnerMapping(100006, "id3", "sid3",null,"Ivan", null, new byte[] {0x01,0x00,0x01,0x00,0x01,0x00,0x01,0x00,0x01}, TEST_CUSTOMERS.get(0)));
        TEST_MAPPINGS.add(new PartnerMapping(100007, "id1", "sid4","Johnson","Johny", "CoolNoy", new byte[] {0x01,0x00,0x01,0x00,0x01,0x00,0x01,0x00,0x01}, TEST_CUSTOMERS.get(1)));
        TEST_MAPPINGS.add(new PartnerMapping(100008, "id5", "sid5","Cool","Prog", "Master", new byte[] {0x01,0x00,0x01,0x00,0x01,0x00,0x01,0x00,0x01}, TEST_CUSTOMERS.get(1)));
        TEST_MAPPINGS.add(new PartnerMapping(100009, "id4", "sid6","China",null, null, null, TEST_CUSTOMERS.get(2)));
    }

    public static PartnerMapping getCreated() {
        return new PartnerMapping(null, "id8", "sid65","New","Active", "Person", new byte[] {0x0,0x00,0x01,0x00,0x01,0x00,0x00,0x00,0x01}, null);
    }

    public static PartnerMapping getUpdated() {
        return new PartnerMapping(100008, "id000", "no-id","Master","Java", "Person", new byte[] {0x06,0x00,0x01,0x00,0x01,0x00,0x00,0x00,0x01}, TEST_CUSTOMERS.get(1));
    }

}
