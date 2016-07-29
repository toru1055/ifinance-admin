package jp.thotta.ifinance.admin.tool;

import jp.thotta.ifinance.common.entity.Industry;
import junit.framework.TestCase;

public class IndustryHandlerTest extends TestCase {
    public void testIndustry() {
        String line = "industry list";
        new MasterDataHandler<Industry>(Industry.class).exec(line.split(" "));
    }
}
