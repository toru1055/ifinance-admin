package jp.thotta.ifinance.admin.tool;

import junit.framework.TestCase;

public class IndustryHandlerTest extends TestCase {
    public void testIndustry() {
        String line = "industry list";
        new IndustryHandler().exec(line.split(" "));
    }
}
