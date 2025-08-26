package com.github.kojotak.bassbook;

public class BassbookPaginator {

    // Incoming/request-bound fields
    private int pageNumber;
    private PageSize pageSize = PageSize.TWENTY;

    // Computed fields
    private int currentPage;
    private int totalPages;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public PageSize getPageSize() {
        return pageSize;
    }

    public void setPageSize(PageSize pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "Paginator{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                '}';
    }
}
