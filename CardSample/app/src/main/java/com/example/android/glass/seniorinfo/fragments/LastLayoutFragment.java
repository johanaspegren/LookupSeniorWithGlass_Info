/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.glass.seniorinfo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.glass.seniorinfo.R;

/**
 * Fragment with the main card layout.
 */
public class LastLayoutFragment extends BaseFragment {

  private static final String TEXT_KEY = "text_key";
  private static final String FOOTER_KEY = "footer_key";
  private static final String TIMESTAMP_KEY = "timestamp_key";
  private static final int BODY_TEXT_SIZE = 40;

  /**
   * Returns new instance of {@link LastLayoutFragment}.
   *
   */
  public static LastLayoutFragment newInstance() {
    final LastLayoutFragment myFragment = new LastLayoutFragment();

    final Bundle args = new Bundle();
    myFragment.setArguments(args);

    return myFragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.last_layout, container, false);
    return view;
  }
}
