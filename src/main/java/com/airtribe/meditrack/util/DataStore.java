package com.airtribe.meditrack.util;

import java.util.ArrayList;
import java.util.List;

public class DataStore<T> {

    private List<T> dataList = new ArrayList<>();

    public void add(T item) {
        dataList.add(item);
    }

    public void remove(T item) {
        dataList.remove(item);
    }

    public List<T> getAll() {
        return new ArrayList<>(dataList);
    }

    public int size() {
        return dataList.size();
    }
}