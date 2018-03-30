package redroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.view.View;

import redroid.R;

/**
 * 文本中带链接的TextView.
 *
 * @author RobinVanYang created at 2018-03-08 00:16.
 */

public class UrlTextView extends android.support.v7.widget.AppCompatTextView {
    private OnLinkClickedListener mLinkClickedListener;

    public UrlTextView(Context context) {
        super(context);
        init();
    }

    public UrlTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttrs(context, attrs, 0);
    }

    public UrlTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initAttrs(context, attrs, defStyleAttr);
    }

    private void init() {
        setAutoLinkMask(Linkify.ALL);
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UrlTextView, defStyleAttr, 0);

        try {
            String text = typedArray.getString(R.styleable.UrlTextView_android_text);
            setText(text);
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (!TextUtils.isEmpty(text))
            super.setText(getClickableHtml(text.toString()), type);
        else
            super.setText(text, type);
    }

    private CharSequence getClickableHtml(String html) {
        Spanned spannedHtml = Html.fromHtml(html);
        SpannableStringBuilder clickableHtmlBuilder = new SpannableStringBuilder(spannedHtml);
        URLSpan[] urls = clickableHtmlBuilder.getSpans(0, spannedHtml.length(), URLSpan.class);
        for (final URLSpan span : urls) {
            setLinkClickable(clickableHtmlBuilder, span);
        }
        return clickableHtmlBuilder;
    }

    private void setLinkClickable(final SpannableStringBuilder clickableHtmlBuilder,
                                  final URLSpan urlSpan) {
        int start = clickableHtmlBuilder.getSpanStart(urlSpan);
        int end = clickableHtmlBuilder.getSpanEnd(urlSpan);
        int flags = clickableHtmlBuilder.getSpanFlags(urlSpan);
        ClickableSpan clickableSpan = new ClickableSpan() {
            public void onClick(View view) {
                if (null != mLinkClickedListener) {
                    mLinkClickedListener.onLinkClicked(Uri.parse(urlSpan.getURL()).toString());
                }
            }
        };
        clickableHtmlBuilder.setSpan(clickableSpan, start, end, flags);
    }

    public void setOnLinkClickedListener(OnLinkClickedListener listener) {
        mLinkClickedListener = listener;
    }

    public interface OnLinkClickedListener {
        void onLinkClicked(String link);
    }
}
