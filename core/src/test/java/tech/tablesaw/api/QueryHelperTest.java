package tech.tablesaw.api;

import org.junit.Before;
import org.junit.Test;

import static tech.tablesaw.api.QueryHelper.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QueryHelperTest {

    private Table t;

    @Before
    public void setUp() throws Exception {
        t = Table.read().csv("../data/bush.csv");
    }

    @Test
    public void test1() {
        Table result = t.where(
                and(
                        stringColumn("who").startsWith("f"),
                        dateColumn("date").isInYear(2002),
                        numberColumn("approval").isLessThan(75)
                )
        );
        assertTrue(result.get(0, "who").startsWith("f"));
    }

    @Test
    public void test3() {
        Table result = t.where(
                        stringColumn("who").isIn("fox"));
        assertTrue(result.get(0, "who").equals("fox"));

        result = t.where(
                        stringColumn("who").isNotIn("fox", "zogby"));
        assertFalse(result.get(0, "who").startsWith("f"));
    }

    @Test
    public void test2() {
        Table result = t.where(
                t.stringColumn("who").startsWith("f"));

        assertTrue(result.get(0, "who").startsWith("f"));
    }
}
