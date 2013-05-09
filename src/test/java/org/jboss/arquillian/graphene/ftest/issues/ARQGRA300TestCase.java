package org.jboss.arquillian.graphene.ftest.issues;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.spi.annotations.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@RunWith(Arquillian.class)
public class ARQGRA300TestCase {

    @Drone
    private WebDriver browser;

    @Page
    protected ARQGRA297TestCase.Uberfire uberfire;
    @Page
    protected ARQGRA300TestCase.Knowledge knowledge;

    @Test
    public void testSimple() {
        browser.get("http://localhost:8080/kie-ide");
        uberfire.login("admin");
        knowledge.goToKnowledge();
        knowledge.choose("uf-playground");

    }

    public static class Knowledge {

        @FindBy(xpath="//a[@data-field='authorKnowledgeAnchor']")
        private WebElement knowledge;

        @FindBy(className="gwt-Anchor")
        private WebElement anchor;

        @Drone
        private WebDriver browser;

        public void goToKnowledge() {
            knowledge.click();
            Graphene.waitAjax()
                    .until()
                    .element(anchor)
                    .is()
                    .present();
        }

        public void choose(String file) {
            By by = By.xpath("//a[@class='gwt-Anchor' and text()='"+file+"']");
//            Graphene.waitAjax()
//                    .until()
//                    .element(by)
//                    .is().present();
            Graphene.guardAjax(browser.findElement(by)).click();
        }

    }
}
