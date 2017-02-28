package ru.emitrohin.roox.util;

import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */
public class TestUtil {

    public static ResultActions print(ResultActions action) throws UnsupportedEncodingException {
        System.out.println(getContent(action));
        return action;
    }

    public static String getContent(ResultActions action) throws UnsupportedEncodingException {
        return action.andReturn().getResponse().getContentAsString();
    }

    /*  public static RequestPostProcessor userHttpBasic(Customer customer) {
          return SecurityMockMvcRequestPostProcessors.httpBasic(customer.getLogin(), customer.getPassword());
      }

      public static RequestPostProcessor userAuth(Customer customer) {
          return SecurityMockMvcRequestPostProcessors.authentication(new UsernamePasswordAuthenticationToken(customer.getLogin(), customer.getPassword()));
      }*/
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
