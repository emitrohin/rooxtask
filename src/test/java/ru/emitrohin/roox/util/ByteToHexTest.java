package ru.emitrohin.roox.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ByteToHexTest {

	@Test
	public void encode() {
        System.out.println(TestUtil.bytesToHex(new byte[] {0x01,0x00,0x01,0x00,0x01,0x00,0x01,0x00,0x01}));
    }

}
