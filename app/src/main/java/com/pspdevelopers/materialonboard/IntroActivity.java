package com.pspdevelopers.materialonboard;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.pspdevelopers.materialonboard.helper.BaseFragment;
import com.pspdevelopers.materialonboard.helper.Constants;
import com.pspdevelopers.materialonboard.helper.ViewPagerAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public abstract class IntroActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private Button btnSkip;
    private ViewPagerAdapter viewPagerAdapter;
    private ArrayList<Fragment> arrayList;
    private Button btnNext;
    private int previousPosition;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_activity);
        initVariables();
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
    }

    private void initVariables() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        constraintLayout = findViewById(R.id.view_constraint_layout);
        viewPager = findViewById(R.id.on_board_view_pager);
        btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener((view) -> onNextPressed());
        btnSkip = findViewById(R.id.btn_skip);
        btnSkip.setOnClickListener((view) -> onSkipPressed());
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        arrayList = new ArrayList<>();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(this);
        previousPosition = 0;
    }

    public void addNewPage(Fragment fragment) {
        arrayList.add(fragment);
        viewPagerAdapter.addNewFragment(fragment);
    }

    public void addNewPage(int layoutId) {
        addNewPage(BaseFragment.newInstance(layoutId));
    }


    public void onNextPressed() {
        if (viewPager.getCurrentItem() < viewPagerAdapter.getCount() - 1)
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        else if (arrayList.get(viewPager.getCurrentItem()) instanceof PermissionTemplate) {
            if (((PermissionTemplate) arrayList.get(viewPager.getCurrentItem())).isPermissionDenied())
                onPermissionDenied();
            else
                onDonePressed();
        } else
            onDonePressed();


    }

    private void onPermissionDenied() {
        Snackbar.make(constraintLayout, Constants.PERMISSION_DENIED, Snackbar.LENGTH_SHORT).show();
    }

    protected abstract void onDonePressed();

    protected abstract void onSkipPressed();

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (previousPosition < position)
            if (arrayList.get(previousPosition) instanceof PermissionTemplate)
                if (((PermissionTemplate) arrayList.get(previousPosition)).isPermissionDenied())
                    onPermissionDenied();

        if (viewPager.getCurrentItem() == viewPagerAdapter.getCount() - 1) {
            btnSkip.setVisibility(View.GONE);
            btnNext.setText(getString(R.string.done));
        } else {
            btnSkip.setVisibility(View.VISIBLE);
            btnNext.setText(getString(R.string.next));
        }
        previousPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


}