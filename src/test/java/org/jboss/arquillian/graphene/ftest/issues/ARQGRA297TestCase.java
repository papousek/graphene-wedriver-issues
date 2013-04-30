package org.jboss.arquillian.graphene.ftest.issues;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.enricher.findby.FindBy;
import org.jboss.arquillian.graphene.spi.annotations.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@RunWith(Arquillian.class)
public class ARQGRA297TestCase {

    @Drone
    private WebDriver browser;

    @Page
    protected Uberfire uberfire;

    @Test
    public void testSimple() {
        browser.get("http://localhost:8080/kie-ide");
        uberfire.login("admin");
    }

    public static class Uberfire {


        @FindBy(css="div#login-content")
        private LoginDialog login;

        @FindBy(jquery="div.navbar a:contains('Logout')")
        private WebElement signout;



        public void login(String username) {
            login.login(username, "admin");
            Graphene.waitModel().until().element(signout).is().present();
        }


    }

    public static class LoginDialog {

        @FindBy(css="input[value*='Sign In']")
        private WebElement signin;

        @FindBy(css="input[name*='j_username']")
        private WebElement user;

        @FindBy(css="input[name*='j_password']")
        private WebElement pasw;


        public void login(String username, String password) {
            user.click();
            user.clear();
            user.sendKeys(username);
            pasw.click();
            pasw.clear();
            pasw.sendKeys(password);
            signin.click();
        }
    }

}
