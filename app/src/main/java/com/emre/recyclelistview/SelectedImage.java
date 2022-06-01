package com.emre.recyclelistview;

public class SelectedImage {
    private University uni;
    private static SelectedImage image;
    private SelectedImage(){}
    public static SelectedImage getInstance(){
        if (image == null)
            image= new SelectedImage();
        return  image;
    }

    public University getUni() {
        return uni;
    }

    public void setUni(University uni) {
        this.uni = uni;
    }
}
