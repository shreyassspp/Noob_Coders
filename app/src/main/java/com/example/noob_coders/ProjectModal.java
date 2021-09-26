package com.example.noob_coders;

public class ProjectModal {

    private String project_name;
    private String project_desc;
    private int project_image;

    // Constructor
    public ProjectModal(String project_name, String project_desc, int project_image) {
        this.project_name = project_name;
        this.project_desc = project_desc;
        this.project_image = project_image;
    }

    // Getter and Setter
    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_desc() {
        return project_desc;
    }

    public void setProject_desc(String project_desc) {
        this.project_desc = project_desc;
    }

    public int getProject_image() {
        return project_image;
    }

    public void setProject_image(int project_image) {
        this.project_image = project_image;
    }
}


