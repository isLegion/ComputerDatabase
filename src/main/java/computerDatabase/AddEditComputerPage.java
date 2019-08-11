package computerDatabase;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.Computer;
import data.Fields;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class AddEditComputerPage {

    private SelenideElement name = $("#name");
    private SelenideElement introduced = $("#introduced");
    private SelenideElement discontinued = $("#discontinued");
    private SelenideElement company = $("#company");
    private SelenideElement createPcButton = $(".btn.primary");
    private SelenideElement deleteBtn = $(".btn.danger");
    private List<SelenideElement> inputFields = Arrays.asList(name, introduced, discontinued);

    public AddEditComputerPage fillPCdata(Computer computer) {
        name.clear();
        name.sendKeys(computer.getName());
        introduced.clear();
        introduced.sendKeys(computer.getIntroducedDate());
        discontinued.clear();
        discontinued.sendKeys(computer.getDiscontinuedDate());
        company.selectOption(computer.getCompany());
        return this;
    }

    public AddEditComputerPage clickCreatePcButton() {
        createPcButton.click();
        return this;
    }

    public AddEditComputerPage checkFieldIsInvalid(Fields field) {
        name.getTagName();
        inputFields.stream().filter(el -> el.getAttribute("name")
                .equals(field.toString())).findFirst().get()
                .shouldHave(Condition.cssValue("border-color", "rgb(200, 120, 114)"));
        return this;
    }

    public void clickDeletePc() {
        deleteBtn.click();
    }

    public void clickCancelBtn() {
        $(".btn").click();
    }

}
