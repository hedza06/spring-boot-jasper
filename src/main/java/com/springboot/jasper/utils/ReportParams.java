package com.springboot.jasper.utils;

import org.springframework.context.annotation.Description;

@Description(value = "Report Params Constants.")
public final class ReportParams {

    public static final String PARAM_FIRST_NAME         = "FIRST_NAME";
    public static final String PARAM_LAST_NAME          = "LAST_NAME";
    public static final String PARAM_AGE                = "AGE";
    public static final String PARAM_CUSTOM_DATA_SOURCE = "CUSTOM_SOURCE_DATA";

    /**
     * Private constructor - can not be instantiated.
     */
    private ReportParams() { }
}
