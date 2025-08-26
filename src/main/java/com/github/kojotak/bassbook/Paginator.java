package com.github.kojotak.bassbook;

public class Paginator {

    // Incoming/request-bound fields
    private Integer pageNumber;
    private Integer pageSize;

    // Computed fields
    private int currentPage;
    private int totalPages;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
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
