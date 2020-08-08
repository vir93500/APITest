package reporting;

import org.testng.asserts.IAssert;

public class SoftAssert extends org.testng.asserts.SoftAssert {

    @Override
    public void onAssertSuccess(IAssert<?> assertCommand) {

    }
    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {

    }
}
