package ru.emitrohin.roox;

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


}
