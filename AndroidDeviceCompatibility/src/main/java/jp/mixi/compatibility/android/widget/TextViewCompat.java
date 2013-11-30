/*
 * Copyright (C) 2012 mixi, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package jp.mixi.compatibility.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Workaround for the following issue of {@link TextView}.
 * - Issue #6716343
 * -- Reported at: http://code.google.com/p/android/issues/detail?id=35259
 * -- Reported at: http://code.google.com/p/android/issues/detail?id=35466
 */
public class TextViewCompat extends TextView {
    /**
     * Initialize text view for a specified context.
     * @param context a context for this view
     * @param attrs attributes for this view
     * @param defStyle styles for this view
     */
    public TextViewCompat(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Initialize text view for a specified context.
     * @param context a context for this view
     * @param attrs attributes for this view
     */
    public TextViewCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Initialize text view for a specified context.
     * @param context a context for this view
     */
    public TextViewCompat(Context context) {
        super(context);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        try {
            super.setText(text, type);
        } catch (ArrayIndexOutOfBoundsException exp) {
            // workaround of android framework issue #6716343
            if (text != null) super.setText(text.toString());
            else super.setText(null);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } catch (ArrayIndexOutOfBoundsException exp) {
            // workaround of android framework issue #6716343
            CharSequence c = getText();
            if (c != null) {
                super.setText(c.toString());
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }
    }
}