package com.abc.abc.Service;

import java.util.List;



/**
 * PageManagement
 */
public class PageManagement {
    private static int defaultAmountPerPage = 5;
    public static List<?> getListByPage(String page,List<?> dataList){  

        int pageNumber = Integer.parseInt(page);      
        int amountPerPage = (dataList.size() > defaultAmountPerPage * pageNumber) ? defaultAmountPerPage * pageNumber : dataList.size();
        List<?> dataPageList = dataList.subList(defaultAmountPerPage * (pageNumber - 1), amountPerPage);
        return dataPageList;
    }

    public static int getSize(List<?> dataList){
        return (dataList.size() % defaultAmountPerPage == 0) ? (dataList.size() / defaultAmountPerPage)
                : ((int) (dataList.size() / defaultAmountPerPage) + 1);
    }
}