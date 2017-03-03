package ru.emitrohin.roox.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import ru.emitrohin.roox.web.json.JsonUtil;

/**
 * GKislin
 * 05.01.2015.
 */
abstract class TestMatcher<T> extends BaseMatcher<String> {
    private final T expected;

    public TestMatcher(T expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(Object actual) {
        return compare(expected, (String) actual);
    }

    abstract protected boolean compare(T expected, String actual);

    @Override
    public void describeTo(Description description) {
        description.appendText(JsonUtil.writeValue(expected));
    }
}
