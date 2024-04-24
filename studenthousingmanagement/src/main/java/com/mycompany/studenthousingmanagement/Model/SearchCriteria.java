package com.mycompany.studenthousingmanagement.Model;
public class SearchCriteria {
    private String searchInput;
    private String filterOptions;

    // Default constructor
    public SearchCriteria() {
    }

    // Parameterized constructor
    public SearchCriteria(String searchInput, String filterOptions) {
        this.searchInput = searchInput;
        this.filterOptions = filterOptions;
    }

    // Getters and setters
    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }

    public String getFilterOptions() {
        return filterOptions;
    }

    public void setFilterOptions(String filterOptions) {
        this.filterOptions = filterOptions;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "searchInput='" + searchInput + '\'' +
                ", filterOptions='" + filterOptions + '\'' +
                '}';
    }
}
