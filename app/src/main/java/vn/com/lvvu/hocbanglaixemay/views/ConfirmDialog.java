package vn.com.lvvu.hocbanglaixemay.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import vn.com.lvvu.hocbanglaixemay.Common;
import vn.com.lvvu.hocbanglaixemay.R;

/**
 * Dialog confirm
 * Created by levan on 7/18/2019.
 */

@SuppressLint("ValidFragment")
public class ConfirmDialog extends DialogFragment {

    private String title;

    private String content;

    private String btnPositiveString;

    private TextView tvTitleDialog;

    private TextView tvContent;

    private Button btnAccept;

    private OnConfirmListener onConfirmListener;

    @SuppressLint("ValidFragment")
    public ConfirmDialog(String title, String content, OnConfirmListener onConfirmListener) {
        this.title = title;
        this.content = content;
        this.onConfirmListener = onConfirmListener;
    }

    @SuppressLint("ValidFragment")
    public ConfirmDialog(String title, String content, String buttonPositive, OnConfirmListener onConfirmListener) {
        this.title = title;
        this.content = content;
        this.btnPositiveString = buttonPositive;
        this.onConfirmListener = onConfirmListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_confirm_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        initView(view);

        return view;
    }

    private void initView(View view) {
        try {
            tvTitleDialog = view.findViewById(R.id.tv_title_toolbar);
            tvContent = view.findViewById(R.id.tv_content);
            btnAccept = view.findViewById(R.id.btn_accept);

            tvTitleDialog.setText(title);
            tvContent.setText(content);
            if (!Common.isTextNullOrEmpty(btnPositiveString)) {
                btnAccept.setText(btnPositiveString);
            }

            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if (onConfirmListener != null) {
                            onConfirmListener.onAccept();
                        }
                        dismiss();
                    } catch (Exception e) {
                        Common.handleException(e);
                    }
                }
            });

        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public interface OnConfirmListener {

        void onAccept();

    }

}
