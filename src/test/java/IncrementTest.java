import computerDatabase.AddEditComputerPage;
import computerDatabase.MainPage;
import data.Computer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.dataUtils;

@DisplayName("Computers increment tests")
public class IncrementTest {

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
    }

    @DisplayName("Check increase quantity of computers  after creating one")
    @Test
    void checkComputerIncreaseIncrement() {
        mainPage.open();
        int startQuantity  = mainPage.getComputerQuantity();
        mainPage.openAddNewPcPage();
        addEditComputerPage
                .fillPCdata(pcWithAllFields)
                .clickCreatePcButton();
        String expectedQuantity  = String.valueOf(startQuantity + 1);
        mainPage.checkComputerIncrement(expectedQuantity);
    }

    @DisplayName("Check decrease quantity of computers  after removing one")
    @Test
    void checkComputerDecreaseIncrement() {
        mainPage.open();
        mainPage.openAddNewPcPage();
        addEditComputerPage
                .fillPCdata(pcWithAllFields)
                .clickCreatePcButton();
        int startQuantity  = mainPage.getComputerQuantity();
        mainPage.editComputer(pcWithAllFields);
        addEditComputerPage.clickDeletePc();
        String expectedQuantity  = String.valueOf(startQuantity - 1);
        mainPage.checkComputerIncrement(expectedQuantity);
    }

}
