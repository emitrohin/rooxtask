package ru.emitrohin.roox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.emitrohin.roox.util.PasswordUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncoder {

	@Test
	public void encode() {
        System.out.println(PasswordUtil.encode("qwerty"));

        System.out.println(PasswordUtil.encode("asdfgh"));

        System.out.println(PasswordUtil.encode("zxcvbn"));

        System.out.println(PasswordUtil.encode("123456"));
    }

}
