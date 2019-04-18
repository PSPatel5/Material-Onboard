package com.pspdevelopers.materialonboard;

import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pspdevelopers.materialonboard.helper.Constants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

public class PermissionTemplate extends Fragment {

    private static final int REQUEST_CODE = 100;
    private View rootView;
    private TextView tvDescription;
    private ImageView ivImage;
    private ConstraintLayout constraintLayout;
    private Button btnPermission;
    private String description;
    private int descriptionTypeFace, descriptionColor;
    private int buttonTypeFace, buttonColor;
    private float buttonSize, descriptionSize;
    private int backgroundColor;
    private String buttonText;
    private int imageResource;
    private String permission;


    private boolean isPermissionDenied = false;


    public static PermissionTemplate newInstance(int imageResource, String description, String permission) {
        PermissionTemplate template = new PermissionTemplate();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.IMAGE, imageResource);
        bundle.putString(Constants.DESCRIPTION_TEXT, description);
        bundle.putString(Constants.PERMISSIONS, permission);
        template.setArguments(bundle);
        return template;
    }


    public static PermissionTemplate newInstance(PermissionTemplate permissionTemplate) {
        PermissionTemplate template = new PermissionTemplate();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.IMAGE, permissionTemplate.getImageResource());
        bundle.putString(Constants.DESCRIPTION_TEXT, permissionTemplate.getDescription());
        bundle.putString(Constants.PERMISSIONS, permissionTemplate.getPermissions());

        bundle.putFloat(Constants.DESCRIPTION_SIZE, permissionTemplate.getDescriptionSize());
        bundle.putInt(Constants.DESCRIPTION_TYPEFACE, permissionTemplate.getDescriptionTypeFace());
        bundle.putInt(Constants.DESCRIPTION_COLOR, permissionTemplate.getDescriptionColor());

        bundle.putString(Constants.BUTTON_TEXT, permissionTemplate.getButtonText());
        bundle.putFloat(Constants.BUTTON_SIZE, permissionTemplate.getButtonSize());
        bundle.putInt(Constants.BUTTON_TYPEFACE, permissionTemplate.getButtonTypeFace());
        bundle.putInt(Constants.BUTTON_COLOR, permissionTemplate.getButtonColor());

        bundle.putInt(Constants.BACKGROUND, permissionTemplate.getBackgroundColor());
        template.setArguments(bundle);
        return template;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_permission, container, false);
        initVariables();
        initData();
        return rootView;
    }

    private void initVariables() {
        constraintLayout = rootView.findViewById(R.id.view_constraint_layout);
        tvDescription = rootView.findViewById(R.id.tv_description);
        ivImage = rootView.findViewById(R.id.iv_image);
        btnPermission = rootView.findViewById(R.id.btn_permission);
        btnPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED)
                        ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, REQUEST_CODE);
                    else {
                        Toast.makeText(getActivity(), "Already Have That Permission", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            tvDescription.setText(bundle.getString(Constants.DESCRIPTION_TEXT));
            ivImage.setImageResource(bundle.getInt(Constants.IMAGE));
            permission = bundle.getString(Constants.PERMISSIONS);

            if (bundle.getString(Constants.BUTTON_TEXT) != null)
                btnPermission.setText(bundle.getString(Constants.BUTTON_TEXT));
            if (bundle.getInt(Constants.BUTTON_COLOR) != 0)
                btnPermission.setTextColor(bundle.getInt(Constants.BUTTON_COLOR));
            if (bundle.getFloat(Constants.BUTTON_SIZE) != 0)
                btnPermission.setTextSize(bundle.getFloat(Constants.BUTTON_SIZE));
            if (bundle.getInt(Constants.BUTTON_TYPEFACE) != 0)
                btnPermission.setTypeface(Typeface.defaultFromStyle(bundle.getInt(Constants.BUTTON_TYPEFACE)));

            if (bundle.getInt(Constants.DESCRIPTION_COLOR) != 0)
                tvDescription.setTextColor(bundle.getInt(Constants.DESCRIPTION_COLOR));
            if (bundle.getFloat(Constants.DESCRIPTION_SIZE) != 0)
                tvDescription.setTextSize(bundle.getFloat(Constants.DESCRIPTION_SIZE));
            if (bundle.getInt(Constants.DESCRIPTION_TYPEFACE) != 0)
                tvDescription.setTypeface(Typeface.defaultFromStyle(bundle.getInt(Constants.DESCRIPTION_TYPEFACE)));

            if (bundle.getInt(Constants.BACKGROUND) != 0)
                constraintLayout.setBackgroundColor(bundle.getInt(Constants.BACKGROUND));
        }

    }

    //    region Getter And Setters


    private int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    private String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    private String getPermissions() {
        return permission;
    }

    public void setPermissions(String permissions) {
        this.permission = permissions;
    }


    private float getDescriptionSize() {
        return descriptionSize;
    }

    public void setDescriptionSize(float descriptionSize) {
        this.descriptionSize = descriptionSize;
    }

    private int getDescriptionTypeFace() {
        return descriptionTypeFace;
    }

    public void setDescriptionTypeFace(int descriptionTypeFace) {
        this.descriptionTypeFace = descriptionTypeFace;
    }

    private int getDescriptionColor() {
        return descriptionColor;
    }

    public void setDescriptionColor(int descriptionColor) {
        this.descriptionColor = descriptionColor;
    }

    private float getButtonSize() {
        return buttonSize;
    }

    public void setButtonSize(float buttonSize) {
        this.buttonSize = buttonSize;
    }

    private int getButtonTypeFace() {
        return buttonTypeFace;
    }

    public void setButtonTypeFace(int buttonTypeFace) {
        this.buttonTypeFace = buttonTypeFace;
    }

    private int getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(int buttonColor) {
        this.buttonColor = buttonColor;
    }

    private String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    //    endregion

    boolean isPermissionDenied() {
        if (getActivity() != null)
            isPermissionDenied = ActivityCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED;
        return isPermissionDenied;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0)
            isPermissionDenied = grantResults[0] != PackageManager.PERMISSION_GRANTED;
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
