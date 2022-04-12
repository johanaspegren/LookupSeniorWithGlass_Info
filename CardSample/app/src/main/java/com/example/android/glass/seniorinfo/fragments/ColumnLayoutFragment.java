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

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.glass.seniorinfo.R;

/**
 * Fragment with the two column layout.
 */
public class ColumnLayoutFragment extends BaseFragment {

  private static final String IMAGE_KEY = "image_key";
  private static final String TEXT_KEY = "text_key";
  private static final String BG_KEY = "footer_key";
  private static final String REMEMBER_KEY = "timestamp_key";
  private static final int TEXT_SIZE = 30;
  private static final int IMAGE_PADDING = 40;

  /**
   * Returns new instance of {@link ColumnLayoutFragment}.
   *  @param image is a android image resource to create a imageView on the left column.
   * @param name
   * @param text is a String with the card main text.
   * @param footer is a String with the card footer text.
   * @param timestamp is a String with the card timestamp text.
   */
  public static ColumnLayoutFragment newInstance(int image,
                                                 String name,
                                                 String background,
                                                 String remember,
                                                 String uid) {
    final ColumnLayoutFragment myFragment = new ColumnLayoutFragment();

    final Bundle args = new Bundle();
    args.putInt(IMAGE_KEY, image);
    args.putString(TEXT_KEY, name);
    args.putString(BG_KEY, background);
    args.putString(REMEMBER_KEY, remember);
    myFragment.setArguments(args);

    return myFragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.left_column_layout_constr, container, false);

//    WebView myWebView = view.findViewById(R.id.webView);
    //myWebView.loadUrl("http://www.dn.se");
//    WebSettings webSettings = myWebView.getSettings();
//    webSettings.setJavaScriptEnabled(true);

    if (getArguments() != null) {
      ImageView img = view.findViewById(R.id.imgPortrait);
      img.setImageResource(getArguments().getInt(IMAGE_KEY));
      TextView tvName = view.findViewById(R.id.tvNameConstr);
      tvName.setText(getArguments().getString(TEXT_KEY));
      TextView tvBackground = view.findViewById(R.id.tvBackground);
      tvBackground.setText(getArguments().getString(BG_KEY));
    }
    return view;
  }

  public class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
      mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
      Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
  }
}
