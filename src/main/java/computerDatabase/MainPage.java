package computerDatabase;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import data.Computer;
import util.dataUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private SelenideElement addNewPcBtn = $(".btn.success");
    private SelenideElement searchInput = $("#searchbox");
    private SelenideElement filterBtn = $("#searchsubmit");


    public MainPage open() {
        Selenide.open("http://computer-database.herokuapp.com/computers");
        return this;
    }

    public void openAddNewPcPage() {
        addNewPcBtn.click();
    }

    public MainPage searchPc(String name) {
        searchInput.clear();
        searchInput.sendKeys(name);
        filterBtn.click();
        return this;
    }

    public MainPage checkComputerWasCreated(Computer pc, int nbr){
        SelenideElement row = $("tr", nbr);
        SelenideElement computerNameColumn = row.find("td", 0);
        SelenideElement introducedDateColumn = row.find("td", 1);
        SelenideElement discontinuedDateColumn = row.find("td", 2);
        SelenideElement companyColumn = row.find("td", 3);
        computerNameColumn.shouldHave(exactText(pc.getName()));
        introducedDateColumn.shouldHave(exactText(dataUtils.formatDateOutput(pc.getIntroducedDate())));
        discontinuedDateColumn.shouldHave(exactText(dataUtils.formatDateOutput(pc.getDiscontinuedDate())));
        companyColumn.shouldHave(exactText(pc.getCompany()));
        return this;
    }

    public MainPage checkEmptyComputer(Computer pc, int nbr) {
        pc.setDiscontinuedDate("-");
        pc.setIntroducedDate("-");
        pc.setCompany("-");
        checkComputerWasCreated(pc, nbr);
        return this;
    }

    public MainPage checkAlertComputerWasCreated(String pcName) {
        String message = String.format("Done! Computer %s has been created", pcName);
        $(".alert-message.warning").shouldHave(text(message));
        return this;
    }

    public MainPage checkAlertComputerWasUpdated(String pcName) {
        String message = String.format("Done! Computer %s has been updated", pcName);
        $(".alert-message.warning").shouldHave(text(message));
        return this;
    }

    public MainPage checkAlertComputerWasDeleted() {
        String message = "Done! Computer has been deleted";
        $(".alert-message.warning").shouldHave(text(message));
        return this;
    }

    public int getComputerQuantity() {
       return Integer.valueOf($(byXpath("//section//h1")).getText().split(" ")[0]);
    }

    public MainPage checkComputerIncrement(String quantity) {
        $(byXpath("//section//h1")).shouldHave(text(quantity));
        return this;
    }

    public void editComputer(Computer pc) {
        searchPc(pc.getName());
        $("tr", 1).$("td", 0).$("a").click();
    }

    public MainPage checkComputerIsNotExist(){
        $("em").shouldHave(text("Nothing to display"));
        return this;
    }
}
