package com.pspdevelopers.materialonboard;


import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pspdevelopers.materialonboard.helper.Constants;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;


public class IntroTemplate extends Fragment {

    private View rootView;
    private TextView tvTitle, tvBody;
    private ImageView ivImage;
    private String title, body;
    private int imageResource;
    private int backgroundColor;
    private int titleColor;
    private int bodyColor;
    private int titleTypeFace, bodyTypeFace;
    private float titleSize, bodySize;
    private ConstraintLayout rootLayout;

    public static IntroTemplate newInstance(String title, int image, String body) {
        IntroTemplate fragmentFirst = new IntroTemplate();
        Bundle args = new Bundle();
        args.putBoolean(Constants.FROM_CONSTRUCTOR, true);
        args.putString(Constants.TITLE_TEXT, title);
        args.putInt(Constants.IMAGE, image);
        args.putString(Constants.BODY_TEXT, body);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    public static IntroTemplate newInstance(IntroTemplate introTemplate) {
        IntroTemplate fragmentFirst = new IntroTemplate();
        Bundle args = new Bundle();
        args.putBoolean(Constants.FROM_CONSTRUCTOR, false);
        args.putString(Constants.TITLE_TEXT, introTemplate.getTitle());
        args.putInt(Constants.TITLE_COLOR, introTemplate.getTitleColor());
        args.putInt(Constants.TITLE_TYPEFACE, introTemplate.getTitleTypeFace());
        args.putFloat(Constants.TITLE_SIZE, introTemplate.getTitleSize());

        args.putString(Constants.BODY_TEXT, introTemplate.getContent());
        args.putInt(Constants.BODY_COLOR, introTemplate.getContentColor());
        args.putInt(Constants.BODY_TYPEFACE, introTemplate.getContentTypeFace());
        args.putFloat(Constants.BODY_SIZE, introTemplate.getContentSize());

        args.putInt(Constants.IMAGE, introTemplate.getImageResource());
        args.putInt(Constants.BACKGROUND, introTemplate.getBackgroundColor());
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }


    //    region Getters And Setters
    private int getTitleTypeFace() {
        return titleTypeFace;
    }

    public void setTitleTypeFace(int titleTypeFace) {
        this.titleTypeFace = titleTypeFace;
    }

    private int getContentTypeFace() {
        return bodyTypeFace;
    }

    public void setContentTypeFace(int bodyTypeFace) {
        this.bodyTypeFace = bodyTypeFace;
    }

    private float getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(float titleSize) {
        this.titleSize = titleSize;
    }


    private float getContentSize() {
        return bodySize;
    }

    public void setContentSize(float bodySize) {
        this.bodySize = bodySize;
    }

    private int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    private int getContentColor() {
        return bodyColor;
    }

    public void setContentColor(int bodyColor) {
        this.bodyColor = bodyColor;
    }

    public void setImage(int imageResource) {
        this.imageResource = imageResource;
    }

    private String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String getContent() {
        return body;
    }

    public void setContent(String body) {
        this.body = body;
    }

    private int getImageResource() {
        return imageResource;
    }

    private int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int colorResource) {
        this.backgroundColor = colorResource;
    }

//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_template, container, false);
        initBasics();
        initData();
        return rootView;
    }

    private void initBasics() {
        tvTitle = rootView.findViewById(R.id.tv_title);
        ivImage = rootView.findViewById(R.id.iv_image);
        tvBody = rootView.findViewById(R.id.tv_body);
        rootLayout = rootView.findViewById(R.id.root_layout);
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            tvTitle.setText(bundle.getString(Constants.TITLE_TEXT));
            tvBody.setText(bundle.getString(Constants.BODY_TEXT));
            ivImage.setImageResource(bundle.getInt(Constants.IMAGE));
            rootLayout.setBackgroundColor(bundle.getInt(Constants.BACKGROUND));
            if (!bundle.getBoolean(Constants.FROM_CONSTRUCTOR)) {
                if (bundle.getInt(Constants.TITLE_COLOR) != 0)
                    tvTitle.setTextColor(bundle.getInt(Constants.TITLE_COLOR));
                if (bundle.getInt(Constants.TITLE_TYPEFACE) != 0)
                    tvTitle.setTypeface(Typeface.defaultFromStyle(bundle.getInt(Constants.TITLE_TYPEFACE)));
                if (bundle.getFloat(Constants.TITLE_SIZE) != 0)
                    tvTitle.setTextSize(bundle.getFloat(Constants.TITLE_SIZE));
                if (bundle.getInt(Constants.BODY_COLOR) != 0)
                    tvBody.setTextColor(bundle.getInt(Constants.BODY_COLOR));
                if (bundle.getInt(Constants.BODY_TYPEFACE) != 0)
                    tvBody.setTypeface(Typeface.defaultFromStyle(bundle.getInt(Constants.BODY_TYPEFACE)));
                if (bundle.getFloat(Constants.BODY_SIZE) != 0)
                    tvBody.setTextSize(bundle.getFloat(Constants.BODY_SIZE));
            }
        }
    }


}
