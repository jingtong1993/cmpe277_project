package com.example.chatEats.push.helper;

import android.content.Context;
import android.util.SparseArray;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



public class NavHelper<T> {
    private final SparseArray<Tab<T>> tabs = new SparseArray<>();

    private final FragmentManager fragmentManager;
    private final int containerId;
    private final Context context;
    private final OnTabChangedListener<T> listener;

    private Tab<T> currentTab;

    public NavHelper(FragmentManager fragmentManager, int containerId, Context context, OnTabChangedListener<T> listener) {
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
        this.context = context;
        this.listener = listener;
    }

    public NavHelper<T> add(int menuId, Tab<T> tab) {
        tabs.put(menuId, tab);
        return this;
    }

    public Tab<T> getCurrentTab() {
        return currentTab;
    }

    public boolean performClickMenu(int menuId) {
        Tab<T> tab = tabs.get(menuId);
        if (tab != null) {
            doSelect(tab);
            return true;
        }
        return false;
    }

    public void doSelect(Tab<T> tab) {
        Tab<T> oldTab = null;

        if (currentTab != null) {
            oldTab = currentTab;
            if (oldTab == tab) {
                notifyTabReselect(tab);
                return;
            }
        }
        currentTab = tab;
        doTabChanged(currentTab, oldTab);

    }

    private void doTabChanged(Tab<T> newTab, Tab<T> oldTab) {
        FragmentTransaction ft = fragmentManager.beginTransaction();

        if (oldTab != null) {
            if (oldTab.fragment != null) {
                ft.detach(oldTab.fragment);
            }
        }

        if (newTab != null) {
            if (newTab.fragment == null) {
                Fragment fragment = Fragment.instantiate(context, newTab.clx.getName(), null);
                newTab.fragment = fragment;
                ft.add(containerId, fragment, newTab.clx.getName());
            }
            else {
                ft.attach(newTab.fragment);
            }
        }

        ft.commit();
        notifyTabSelect(newTab, oldTab);
    }

    private void notifyTabSelect(Tab<T> newTab, Tab<T> oldTab) {
        if (listener != null) {
            listener.onTabChanged(newTab, oldTab);
        }
    }

    private void notifyTabReselect(Tab<T> tab) {

    }

    public static class Tab<T> {
        public Class<?> clx;
        public T extra;
        Fragment fragment;

        public Tab(Class<?> clx, T extra) {
            this.clx = clx;
            this.extra = extra;
        }
    }

    public interface OnTabChangedListener<T> {
        void onTabChanged(Tab<T> newTab, Tab<T> oldTab);
    }

}
