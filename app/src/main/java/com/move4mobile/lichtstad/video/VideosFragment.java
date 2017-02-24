package com.move4mobile.lichtstad.video;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.move4mobile.lichtstad.R;
import com.move4mobile.lichtstad.databinding.FragmentVideosBinding;

public class VideosFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentVideosBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_videos, container, false);

        getActivity().setActionBar(binding.toolbar);

        return binding.getRoot();
    }
}