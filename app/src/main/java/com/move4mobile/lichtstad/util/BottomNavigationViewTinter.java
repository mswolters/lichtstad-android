package com.move4mobile.lichtstad.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.ColorRes;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BottomNavigationViewTinter {

    private static final String TAG = BottomNavigationViewTinter.class.getSimpleName();

    /**
     * Tints a {@link BottomNavigationView}'s buttons according to {@code colorIds}.
     *
     * @param navigationView The navigationView to tint
     * @param context        The context to use to retrieve the colors
     * @param colorIds       The ids of the colors to use for tinting
     * @see #tintBottomNavigationButtons(BottomNavigationView, ColorStateList...)
     */
    public static void tintBottomNavigationButtons(BottomNavigationView navigationView, Context context, @ColorRes int... colorIds) {
        ColorStateList[] colorStateLists = new ColorStateList[colorIds.length];
        for (int i = 0; i < colorIds.length; i++) {
            int id = colorIds[i];
            colorStateLists[i] = context.getResources().getColorStateList(id);
        }
        tintBottomNavigationButtons(navigationView, colorStateLists);
    }

    /**
     * Tints a {@link BottomNavigationView}'s buttons according to {@code tintLists}.
     * <p>
     * This is done using reflection.
     * {@code tintLists.length} should equal {@code navigationView.getMenu().size()}
     *
     * @param navigationView The navigationView to tint
     * @param tintLists      The lists to use for tinting
     */
    public static void tintBottomNavigationButtons(BottomNavigationView navigationView, ColorStateList... tintLists) {
        try {
            Field menuViewField = BottomNavigationView.class.getDeclaredField("mMenuView");
            menuViewField.setAccessible(true);
            Object menuView = menuViewField.get(navigationView);
            if (!menuView.getClass().getSimpleName().equals("BottomNavigationMenuView")) {
                Log.e(TAG, "Menu view found, but not of correct class");
                return;
            }
            Field buttonsField = menuView.getClass().getDeclaredField("mButtons");
            buttonsField.setAccessible(true);
            Object foundButtons = buttonsField.get(menuView);
            Class<?> buttonType = foundButtons.getClass().getComponentType();
            if (!foundButtons.getClass().isArray() || !buttonType.getSimpleName().equals("BottomNavigationItemView")) {
                Log.e(TAG, "Buttons found, but not of correct class");
                return;
            }
            Object[] buttons = (Object[]) foundButtons;
            if (buttons.length != tintLists.length) {
                throw new IllegalArgumentException("Invalid number of tint lists. Found " + buttons.length + " buttons, but " + tintLists.length + " tint lists.");
            }
            Method imageTinter = buttonType.getMethod("setIconTintList", ColorStateList.class);
            imageTinter.setAccessible(true);
            Method textTinter = buttonType.getMethod("setTextColor", ColorStateList.class);
            textTinter.setAccessible(true);

            for (int i = 0; i < buttons.length; i++) {
                Object button = buttons[i];
                imageTinter.invoke(button, tintLists[i]);
                textTinter.invoke(button, tintLists[i]);
            }
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

}