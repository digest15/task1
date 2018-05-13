package com.edu.task1.reports;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractReport<T> implements Reportable {
    protected String title = "";
    protected String body = "";
    protected String basement = "";
    protected List<T> list;
    protected Function<Object, String> toStringFunc;

    public AbstractReport(List<T> list) {
        this.list = list;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getBody() {
        return this.body;
    }

    @Override
    public String getBasement() {
        return this.basement;
    }

    public void setBasement(String basement) {
        this.basement = basement;
    }

    public Function<Object, String> getToStringFunc() {
        return toStringFunc;
    }

    public void setToStringFunc(Function<Object, String> toStringFunc) {
        this.toStringFunc = toStringFunc;
    }

    protected void printCollection(List list, Function<Object, String> functionToString) {
        int i = 1;
        for (Object o : list) {
            body += i + ". " + functionToString.apply(o);
            i++;
        }
    }
}
