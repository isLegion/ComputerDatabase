import computerDatabase.AddEditComputerPage;
import computerDatabase.MainPage;
import data.Computer;
import data.Fields;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.dataUtils;

@DisplayName("Validation fields tests")
public class ValidateComputerFieldsTests {

    private Computer pcWithAllFields;
    private MainPage mainPage = new MainPage();
    private AddEditComputerPage addEditComputerPage = new AddEditComputerPage();

    @BeforeEach
    void openAddEditPage() {
        pcWithAllFields = Computer.builder()
                .name(dataUtils.getRandomSimpleWord())
                .introducedDate("2014-06-12")
                .discontinuedDate("2016-07-20")
                .company("Atari")
                .build();
        mainPage.open()
                .openAddNewPcPage();
    }

    @DisplayName("Check validation for introduced and discontinued fields")
    @Test
    void checkValidationDateFields() {
        pcWithAllFields.setIntroducedDate("2014/06/12");
        pcWithAllFields.setDiscontinuedDate("2014/06/12");

        addEditComputerPage
                .fillPCdata(pcWithAllFields)
                .clickCreatePcButton()
                .checkFieldIsInvalid(Fields.INTRODUCED)
                .checkFieldIsInvalid(Fields.DISCONTINUED);
        pcWithAllFields.setIntroducedDate("dnjknj123");
        pcWithAllFields.setDiscontinuedDate("dnjknj123");

        addEditComputerPage
                .fillPCdata(pcWithAllFields)
                .clickCreatePcButton()
                .checkFieldIsInvalid(Fields.INTRODUCED)
                .checkFieldIsInvalid(Fields.DISCONTINUED);
        pcWithAllFields.setIntroducedDate("12-06-2014");
        pcWithAllFields.setDiscontinuedDate("12-06-2014");

        addEditComputerPage
                .fillPCdata(pcWithAllFields)
                .clickCreatePcButton()
                .checkFieldIsInvalid(Fields.INTRODUCED)
                .checkFieldIsInvalid(Fields.DISCONTINUED);

        pcWithAllFields.setIntroducedDate("12 Jun 2014");
        pcWithAllFields.setDiscontinuedDate("12 Jun 2014");

        addEditComputerPage
                .fillPCdata(pcWithAllFields)
                .clickCreatePcButton()
                .checkFieldIsInvalid(Fields.INTRODUCED)
                .checkFieldIsInvalid(Fields.DISCONTINUED);
    }

    @DisplayName("Check validation for name field")
    @Test
    void checkValidationComputerNameField() {
        pcWithAllFields.setName(dataUtils.getRandomWordWithNmb());
        addEditComputerPage
                .fillPCdata(pcWithAllFields)
                .clickCreatePcButton();
        mainPage
                .checkAlertComputerWasCreated(pcWithAllFields.getName())
                .openAddNewPcPage();
        pcWithAllFields.setName(dataUtils.getRandomWordAnyLength(100));
        addEditComputerPage
                .fillPCdata(pcWithAllFields)
                .clickCreatePcButton();
        mainPage
                .checkAlertComputerWasCreated(pcWithAllFields.getName())
                .openAddNewPcPage();
        pcWithAllFields.setName("#%#%&@@&^&*&(*(^#^^%");
        addEditComputerPage
                .fillPCdata(pcWithAllFields)
                .clickCreatePcButton();
        mainPage
                .checkAlertComputerWasCreated(pcWithAllFields.getName())
                .openAddNewPcPage();
        pcWithAllFields.setName("");
        addEditComputerPage
                .fillPCdata(pcWithAllFields)
                .clickCreatePcButton()
                .checkFieldIsInvalid(Fields.NAME);
    }

}
