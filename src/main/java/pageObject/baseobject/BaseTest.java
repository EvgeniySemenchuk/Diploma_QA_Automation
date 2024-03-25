package pageObject.baseobject;

import driver.DriverTypes;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import testngUtils.InvokedListener;
import testngUtils.SimpleListener;

import java.lang.reflect.InvocationTargetException;

import static driver.DriverCreation.*;
import static driver.DriverTypes.CHROME;
import static propertyUtils.PropertyReader.*;

@Listeners({SimpleListener.class, InvokedListener.class})
public abstract class BaseTest {

    @BeforeTest
    protected void setUp() {
        startWebDriver(System.getProperties().containsKey("config")
                ? DriverTypes.valueOf(getProperties().getProperty("browser").toUpperCase())
                : CHROME
        );
    }

    protected <T> T get(Class<T> page) {
        T instance;
        try {
            instance = page.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                IllegalAccessException e ) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    @AfterTest
    public void quit() {
        quitWebDriver();
    }

}
