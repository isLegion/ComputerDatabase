import computerDatabase.AddEditComputerPage;
import computerDatabase.MainPage;
import data.Computer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.dataUtils;

@DisplayName("Smoke CRUD tests")
public class SmokeTests {

    private MainPage mainPage = new MainPage();
    private AddEditComputerPage addEditComputerPage = new AddEditComputerPage();
    private Computer pcWithAllFields;

    @BeforeEach
    public void openPage() {
        pcWithAllFields = Computer.builder()
                .name(dataUtils.getRandomSimpleWord())
                .introducedDate("2014-06-12")
                .discontinuedDate("2016-07-20")
                .company("Atari")
                .build();
        mainPage.open()
                .openAddNewPcPage();
        addEditComputerPage
                .fillPCdata(pcWithAllFields)
                .clickCreatePcButton();
    }

    @DisplayName("Check that computer with all correct fields was created")
    @Test
    public void createNewComputer() {
        mainPage.checkAlertComputerWasCreated(pcWithAllFields.getName())
                .searchPc(pcWithAllFields.getName())
                .checkComputerWasCreated(pcWithAllFields, 1);
    }

    @DisplayName("Check that existing computer was removed ")
    @Test
    public void deleteExistingComputer() {
        mainPage.editComputer(pcWithAllFields);
        addEditComputerPage.clickDeletePc();
        mainPage.checkAlertComputerWasDeleted()
                .searchPc(pcWithAllFields.getName())
                .checkComputerIsNotExist();
    }

    @DisplayName("Check that cancel button works correctly ")
    @Test
    public void cancelCratingOfComputer() {
        mainPage.editComputer(pcWithAllFields);
        addEditComputerPage.clickCancelBtn();
        mainPage.searchPc(pcWithAllFields.getName())
                .checkComputerWasCreated(pcWithAllFields, 1);
    }

    @DisplayName("Check that existing computer was edited successfully")
    @Test
    public void editExistingComputer() {
        Computer newPcWithAllFields = Computer.builder()
                .name(dataUtils.getRandomSimpleWord())
                .introducedDate("2015-07-12")
                .discontinuedDate("2018-10-01")
                .company("RCA")
                .build();
        mainPage.editComputer(pcWithAllFields);
        addEditComputerPage
                .fillPCdata(newPcWithAllFields)
                .clickCreatePcButton();
        mainPage.checkAlertComputerWasUpdated(newPcWithAllFields.getName())
                .searchPc(newPcWithAllFields.getName())
                .checkComputerWasCreated(newPcWithAllFields, 1)
                .searchPc(pcWithAllFields.getName())
                .checkComputerIsNotExist();
    }

    @DisplayName("Check that search of existing computer works correct")
    @Test
    public void searchComputer() {
        String fullName = pcWithAllFields.getName();
        String halfName = pcWithAllFields.getName().substring(0, fullName.length() - 3);
        String incorrectName = pcWithAllFields.getName() + "23232";
        mainPage.searchPc(fullName)
                .checkComputerWasCreated(pcWithAllFields, 1)
                .searchPc(halfName)
                .checkComputerWasCreated(pcWithAllFields, 1)
                .searchPc(incorrectName)
                .checkComputerIsNotExist();
    }

    @DisplayName("Check creating computer with minimal needed fields")
    @Test
    public void createMinimalComputer() {
        pcWithAllFields.setIntroducedDate("");
        pcWithAllFields.setDiscontinuedDate("");
        pcWithAllFields.setCompany("-- Choose a company --");
        mainPage.editComputer(pcWithAllFields);
        addEditComputerPage
                .fillPCdata(pcWithAllFields)
                .clickCreatePcButton();
        mainPage.checkAlertComputerWasUpdated(pcWithAllFields.getName())
                .searchPc(pcWithAllFields.getName())
                .checkEmptyComputer(pcWithAllFields, 1);
    }

}
