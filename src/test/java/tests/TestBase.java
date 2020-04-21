package tests;

import application.Application;
import org.junit.After;
import org.junit.Before;

public class TestBase {

    protected Application app = new Application();

    @Before
    public void setUp(){
        app.init();
        app.goToStore();
    }


    @After
    public void tearDown() {
        app.quite();

    }



}
