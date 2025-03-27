package Models;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import Models.Orders.BlockadeOrderTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        OrdersTest.class,
        BlockadeOrderTest.class,
        PlayerTest.class,
        WarMapTest.class,})
public class ModelsTestSuite {
}