package pageObject.wildberries;

public class NavigationBarPage extends BaseWBPage<NavigationBarPage> {

    private String getFirstCategory(String firstCategory) {
        return "//*[@data-tag=\"pagesContainer\"]//div[@data-level=\"0\"]//span[contains(.,\""+firstCategory+"\")]";
    }

    private String getSecondCategory(String firstCategory, String secondCategory) {
        return getFirstCategory(firstCategory).concat("ancestor::*[@data-tag=\"pagesContainer\"]//div[@data-level=\"1\"]//span[contains(.,\""+secondCategory+"\")]");
    }

    private String getThirdCategory(String firstCategory, String secondCategory, String thirdCategory) {
        return getSecondCategory(firstCategory,secondCategory).concat("//ancestor::*[@data-tag=\"pagesContainer\"]//div[@data-level=\"2\"]//span[contains(.,\""+thirdCategory+"\")]");
    }

    private String getFourthCategory(String firstCategory, String secondCategory, String thirdCategory, String fourthCategory) {
        return getThirdCategory(firstCategory,secondCategory,thirdCategory).concat("//ancestor::*[@data-tag=\"pagesContainer\"]//div[@data-level=\"3\"]//span[contains(.,\""+fourthCategory+"\")]");
    }

}
