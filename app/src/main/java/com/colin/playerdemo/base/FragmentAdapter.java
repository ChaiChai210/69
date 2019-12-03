package com.colin.playerdemo.base;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentAdapter<T extends Fragment> extends FragmentPagerAdapter {
    private List<T> mFragmentList = new ArrayList();
    private List<String> mTitleList = new ArrayList();

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentAdapter(FragmentManager fm, List<T> fragments) {
        super(fm);
        this.setFragments(fragments);
    }

    public FragmentAdapter(FragmentManager fm, T[] fragments) {
        super(fm);
        this.setFragments(Arrays.asList(fragments));
    }

    public FragmentAdapter setFragments(List<T> fragments) {
        if (fragments != null && fragments.size() > 0) {
            this.mFragmentList.clear();
            this.mFragmentList.addAll(fragments);
        }

        return this;
    }

    public FragmentAdapter addFragments(List<T> fragments) {
        if (fragments != null && fragments.size() > 0) {
            this.mFragmentList.addAll(fragments);
        }

        return this;
    }

    public FragmentAdapter setTitles(List<String> titles) {
        if (titles != null && titles.size() > 0) {
            this.mTitleList.clear();
            this.mTitleList.addAll(titles);
        }

        return this;
    }

    public FragmentAdapter addTitles(List<String> titles) {
        if (titles != null && titles.size() > 0) {
            this.mTitleList.addAll(titles);
        }

        return this;
    }

    public FragmentAdapter addFragment(T fragment, String title) {
        if (fragment != null) {
            this.mFragmentList.add(fragment);
            this.mTitleList.add(title);
        }

        return this;
    }

    public T getItem(int position) {
        return this.mFragmentList.get(position);
    }

    public int getCount() {
        return this.mFragmentList.size();
    }

    @Nullable
    public CharSequence getPageTitle(int position) {
        return (CharSequence)this.mTitleList.get(position);
    }

    public List<T> getFragmentList() {
        return this.mFragmentList;
    }

    public List<String> getTitleList() {
        return this.mTitleList;
    }
}
