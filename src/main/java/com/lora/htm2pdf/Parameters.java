/**
 * Copyright (C) 2016, CodeBit.
 */
package com.lora.htm2pdf;

import java.util.Map;

/**
 *
 * @author lora
 */
public class Parameters {

    private Boolean collate;
    private Integer copies;
    private Boolean grayScale;
    private Boolean lowQuality;
    private Boolean portrait;
    private String pageSize;
    private Boolean printMediaType;
    private Float zoom;
    private boolean ignoreErrors;
    private Integer javascriptDelay;
    private String windowStatus;
    private Boolean disableSmartShrinking;
    private Integer dpi;
    private String topMargin;
    private String rightMargin;
    private String bottomMargin;
    private String leftMargin;
    private String proxy;
    private Map<String, String> cookies;

    public Boolean getCollate() {
        return collate;
    }

    public void setCollate(Boolean collate) {
        this.collate = collate;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Boolean getGrayScale() {
        return grayScale;
    }

    public void setGrayScale(Boolean grayScale) {
        this.grayScale = grayScale;
    }

    public Boolean getLowQuality() {
        return lowQuality;
    }

    public void setLowQuality(Boolean lowQuality) {
        this.lowQuality = lowQuality;
    }

    public Boolean getPortrait() {
        return portrait;
    }

    public void setPortrait(Boolean portrait) {
        this.portrait = portrait;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getPrintMediaType() {
        return printMediaType;
    }

    public void setPrintMediaType(Boolean printMediaType) {
        this.printMediaType = printMediaType;
    }

    public Float getZoom() {
        return zoom;
    }

    public void setZoom(Float zoom) {
        this.zoom = zoom;
    }

    public boolean isIgnoreErrors() {
        return ignoreErrors;
    }

    public void setIgnoreErrors(boolean ignoreErrors) {
        this.ignoreErrors = ignoreErrors;
    }

    public Integer getJavascriptDelay() {
        return javascriptDelay;
    }

    public void setJavascriptDelay(Integer javascriptDelay) {
        this.javascriptDelay = javascriptDelay;
    }

    public String getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(String windowStatus) {
        this.windowStatus = windowStatus;
    }

    public Boolean getDisableSmartShrinking() {
        return disableSmartShrinking;
    }

    public void setDisableSmartShrinking(Boolean disableSmartShrinking) {
        this.disableSmartShrinking = disableSmartShrinking;
    }

    public Integer getDpi() {
        return dpi;
    }

    public void setDpi(Integer dpi) {
        this.dpi = dpi;
    }

    public String getTopMargin() {
        return topMargin;
    }

    public void setTopMargin(String topMargin) {
        this.topMargin = topMargin;
    }

    public String getRightMargin() {
        return rightMargin;
    }

    public void setRightMargin(String rightMargin) {
        this.rightMargin = rightMargin;
    }

    public String getBottomMargin() {
        return bottomMargin;
    }

    public void setBottomMargin(String bottomMargin) {
        this.bottomMargin = bottomMargin;
    }

    public String getLeftMargin() {
        return leftMargin;
    }

    public void setLeftMargin(String leftMargin) {
        this.leftMargin = leftMargin;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }
}
