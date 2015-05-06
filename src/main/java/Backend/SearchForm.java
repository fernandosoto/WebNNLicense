package Backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2015-05-06.
 */
public class SearchForm {

    private String searchCriteriaProductName;
    private Purchase purchase;

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }


    public String getSearchCriteriaProductName() {
        return searchCriteriaProductName;
    }

    public void setSearchCriteriaProductName(String searchCriteriaProductName) {
        this.searchCriteriaProductName = searchCriteriaProductName;
    }


}
