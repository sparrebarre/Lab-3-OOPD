package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestVolvo.class,
        TestSaab.class,
        TestTransportTruck.class,
        TestScania.class,
        TestWorkShop.class

})
public class TestAll {
}
