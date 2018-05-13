package com.edu.task1.reports.Print;

import com.edu.task1.reports.Reportable;

public class PrintToConsole implements ReportPrintable {
    @Override
    public void print(Reportable report) {
        System.out.println("----" + report.getTitle() + "----");
        System.out.println(report.getBody());
        System.out.println(report.getBasement());
    }
}
