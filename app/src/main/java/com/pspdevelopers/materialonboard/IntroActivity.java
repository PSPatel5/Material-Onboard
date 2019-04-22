package com.pspdevelopers.materialonboard;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.pspdevelopers.materialonboard.helper.BaseFragment;
import com.pspdevelopers.materialonboard.helper.Constants;
import com.pspdevelopers.materialonboard.helper.VerticalViewPager;
import com.pspdevelopers.materialonboard.helper.ViewPagerAdapter;
import com.pspdevelopers.materialonboard.widget.PageIndicatorView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public abstract class IntroActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    private ViewPager horizontalViewPager;
    private VerticalViewPager verticalViewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private PageIndicatorView pageIndicatorView;
    private ConstraintLayout constraintLayout;
    private Button btnSkip, btnNext, btnPrevious;
    private ArrayList<Fragment> arrayList;
    private int previousPosition;
    private boolean previousVisible, nextVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isVertical())
            setContentView(R.layout.intro_activity_vertical);
        else
            setContentView(R.layout.intro_activity_horizontal);
        initCommonVariables();

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
    }

    private void initCommonVariables() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        constraintLayout = findViewById(R.id.view_constraint_layout);
        pageIndicatorView = findViewById(R.id.page_indicator);
        btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextPressed();
            }
        });
        btnPrevious = findViewById(R.id.btn_previous);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPreviousPressed();
            }
        });
        btnSkip = findViewById(R.id.btn_skip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isVertical())
//                    verticalViewPager.setCurrentItem(viewPagerAdapter.getCount(), true);
//                else
//                    horizontalViewPager.setCurrentItem(viewPagerAdapter.getCount(), true);
                onSkipPressed();
            }
        });
        arrayList = new ArrayList<>();
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        previousPosition = 0;
        previousVisible = true;
        if (isVertical())
            initVerticalVariables();
        else
            initHorizontalVariables();

    }


    private void setIndicatorColors(int selectedColor, int unSelectedColor) {
        if (selectedColor != 0)
            pageIndicatorView.setSelectedColor(selectedColor);
        if (unSelectedColor != 0)
            pageIndicatorView.setUnselectedColor(unSelectedColor);
    }


    private void setSkipTextColor(int textColor) {
        btnSkip.setTextColor(textColor);
    }

    private void setnextTextColor(int textColor) {
        btnNext.setTextColor(textColor);
    }

    private void setpreviousTextColor(int textColor) {
        btnPrevious.setTextColor(textColor);
    }

    private void onPreviousPressed() {
        if (isVertical())
            verticalViewPager.setCurrentItem(verticalViewPager.getCurrentItem() - 1);
        else
            horizontalViewPager.setCurrentItem(horizontalViewPager.getCurrentItem() - 1);
    }

    private void initVerticalVariables() {
        verticalViewPager = findViewById(R.id.on_board_view_pager);
        verticalViewPager.setAdapter(viewPagerAdapter);
        verticalViewPager.addOnPageChangeListener(this);
    }

    public abstract boolean isVertical();

    public void showIndicators(boolean visibility) {
        if (visibility)
            pageIndicatorView.setVisibility(View.VISIBLE);
        else
            pageIndicatorView.setVisibility(View.INVISIBLE);
    }

    public void showSkipButton(boolean visibility) {
        if (visibility)
            btnSkip.setVisibility(View.VISIBLE);
        else
            btnSkip.setVisibility(View.INVISIBLE);
    }

    public void showNextButton(boolean visibility) {
        nextVisible = visibility;
        if (visibility)
            btnNext.setVisibility(View.VISIBLE);
        else
            btnNext.setVisibility(View.INVISIBLE);
    }


    public void showPreviousButton(boolean visibility) {
        previousVisible = visibility;
        if (visibility)
            btnPrevious.setVisibility(View.VISIBLE);
        else
            btnPrevious.setVisibility(View.INVISIBLE);
    }

    private void initHorizontalVariables() {
        horizontalViewPager = findViewById(R.id.on_board_view_pager);
        horizontalViewPager.setAdapter(viewPagerAdapter);
        horizontalViewPager.addOnPageChangeListener(this);
    }

    public void addNewPage(Fragment fragment) {
        arrayList.add(fragment);
        viewPagerAdapter.addNewFragment(fragment);
    }

    public void addNewPage(int layoutId) {
        addNewPage(BaseFragment.newInstance(layoutId));
    }


    public void onNextPressed() {
        if (isVertical()) {
            if (verticalViewPager.getCurrentItem() < viewPagerAdapter.getCount() - 1)
                verticalViewPager.setCurrentItem(verticalViewPager.getCurrentItem() + 1, true);
            else if (arrayList.get(verticalViewPager.getCurrentItem()) instanceof PermissionTemplate) {
                if (((PermissionTemplate) arrayList.get(verticalViewPager.getCurrentItem())).isPermissionDenied())
                    onPermissionDenied();
                onDonePressed();
            } else
                onDonePressed();
        } else {
            if (horizontalViewPager.getCurrentItem() < viewPagerAdapter.getCount() - 1)
                horizontalViewPager.setCurrentItem(horizontalViewPager.getCurrentItem() + 1, true);
            else if (arrayList.get(horizontalViewPager.getCurrentItem()) instanceof PermissionTemplate) {
                if (((PermissionTemplate) arrayList.get(horizontalViewPager.getCurrentItem())).isPermissionDenied())
                    onPermissionDenied();
                onDonePressed();
            }
        }
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
        if (position == 0)
            btnPrevious.setVisibility(View.INVISIBLE);
        else {
            if (previousVisible)
                btnPrevious.setVisibility(View.VISIBLE);
        }

        if (previousPosition < position)
            if (arrayList.get(previousPosition) instanceof PermissionTemplate)
                if (((PermissionTemplate) arrayList.get(previousPosition)).isPermissionDenied())
                    onPermissionDenied();

        if (position == viewPagerAdapter.getCount() - 1) {
            btnSkip.setVisibility(View.GONE);
            btnNext.setVisibility(View.VISIBLE);
            btnNext.setText(getString(R.string.done));
        } else {
            btnSkip.setVisibility(View.VISIBLE);
            if (!nextVisible)
                btnNext.setVisibility(View.INVISIBLE);
            else
                btnNext.setVisibility(View.VISIBLE);
            btnNext.setText(getString(R.string.next));

        }
        previousPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


}