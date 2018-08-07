package com.move4mobile.lichtstad.photo.album;

import androidx.fragment.app.Fragment;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.move4mobile.lichtstad.BaseContentFragment;
import com.move4mobile.lichtstad.R;
import com.move4mobile.lichtstad.adapter.YearFragmentPagerAdapter;
import com.move4mobile.lichtstad.databinding.FragmentAlbumsBinding;

public class AlbumsFragment extends BaseContentFragment implements YearFragmentPagerAdapter.FragmentSupplier {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FragmentAlbumsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_albums, container, false);

        PagerAdapter adapter = new YearFragmentPagerAdapter(getChildFragmentManager(), getYears(), this);
        binding.component.viewPager.setAdapter(adapter);
        binding.component.viewPager.setCurrentItem(adapter.getCount() - 1);

        binding.component.tabLayout.setupWithViewPager(binding.component.viewPager);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.component.toolbar.toolbar);

        return binding.getRoot();
    }

    @NonNull
    private int[] getYears() {
        return getActivity().getResources().getIntArray(R.array.history_years);
    }

    @Override
    public Fragment supplyFragment(int year) {
        return AlbumYearFragment.newInstance(year);
    }

}
