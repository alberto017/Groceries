package com.example.sanchez.groceries;

public class ProductSpecificationModel {

    private String featureName;
    private String featureValue;
    public static final int SPECIFICATION_TITLE = 0;
    public static final int SPECIFICATION_BODY = 1;
    private int type;
    private String title;

    /*
    public ProductSpecificationModel(String featureName, String featureValue, int type, String title) {
        this.featureName = featureName;
        this.featureValue = featureValue;
        this.type = type;
        this.title = title;
    }
    */

    public ProductSpecificationModel(String featureName, String featureValue) {
        this.featureName = featureName;
        this.featureValue = featureValue;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(String featureValue) {
        this.featureValue = featureValue;
    }

    public static int getSpecificationTitle() {
        return SPECIFICATION_TITLE;
    }

    public static int getSpecificationBody() {
        return SPECIFICATION_BODY;
    }


    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
