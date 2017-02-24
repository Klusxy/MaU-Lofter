package oracle.mau.view;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;

/**
 * 仿iOS底部弹出框
 * Created by 田帅 on 2017/2/17.
 */

public class BottomMenuDialog extends Dialog {

    public BottomMenuDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Params {
        private final List<BottomMenu> menuList = new ArrayList<>();
        private View.OnClickListener cancelListener;
        private String menuTitle;
        private String cancelText;
        private Context context;
    }

    public static class Builder {
        private boolean canCancel = true;
        private boolean shadow = true;
        private final Params p;

        public Builder(Context context) {
            p = new Params();
            p.context = context;
        }

        public Builder setCanCancel(boolean canCancel) {
            this.canCancel = canCancel;
            return this;
        }

        public Builder setShadow(boolean shadow) {
            this.shadow = shadow;
            return this;
        }

        public Builder setTitle(String title) {
            this.p.menuTitle = title;
            return this;
        }

        public Builder addMenu(String text, View.OnClickListener listener) {
            BottomMenu bm = new BottomMenu(text, listener);
            this.p.menuList.add(bm);
            return this;
        }

        public Builder addMenu(int textId, View.OnClickListener listener) {
            return addMenu(p.context.getString(textId), listener);
        }

        public Builder setCancelListener(View.OnClickListener cancelListener) {
            p.cancelListener = cancelListener;
            return this;
        }

        public Builder setCancelText(int resId) {
            p.cancelText = p.context.getString(resId);
            return this;
        }

        public Builder setCancelText(String text) {
            p.cancelText = text;
            return this;
        }

        public BottomMenuDialog create() {
            final BottomMenuDialog dialog = new BottomMenuDialog(p.context, shadow ? R.style.Theme_Light_NoTitle_Dialog : R.style.Theme_Light_NoTitle_NoShadow_Dialog);
            Window window = dialog.getWindow();
            window.setWindowAnimations(R.style.Animation_Bottom_Rising);

            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
            window.setGravity(Gravity.BOTTOM);

            View view = LayoutInflater.from(p.context).inflate(R.layout.dialog_bottom_menu, null);

            TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
            ViewGroup layContainer = (ViewGroup) view.findViewById(R.id.lay_container);
            LayoutParams lpItem = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            LayoutParams lpDivider = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
            int dip1 = (int) (1 * p.context.getResources().getDisplayMetrics().density + 0.5f);
            int spacing = dip1 * 12;

            boolean hasTitle = !TextUtils.isEmpty(p.menuTitle);
            if (hasTitle) {
                TextView tTitle = new TextView(p.context);
                tTitle.setLayoutParams(lpItem);
                tTitle.setGravity(Gravity.CENTER);
                tTitle.setTextColor(0xFF8F8F8F);
                tTitle.setText(p.menuTitle);
                tTitle.setTextSize(17);
                tTitle.setPadding(0, spacing, 0, spacing);
                tTitle.setBackgroundResource(R.drawable.common_dialog_selection_selector_top);
                layContainer.addView(tTitle);

                View viewDivider = new View(p.context);
                viewDivider.setLayoutParams(lpDivider);
                viewDivider.setBackgroundColor(0xFFCED2D6);
                layContainer.addView(viewDivider);
            }

            for (int i = 0; i < p.menuList.size(); i++) {
                BottomMenu bottomMenu = p.menuList.get(i);
                TextView bbm = new TextView(p.context);
                bbm.setLayoutParams(lpItem);
                int backgroundResId = R.drawable.common_dialog_selection_selector_center;
                if (p.menuList.size() > 1) {
                    if (i == 0) {
                        if (hasTitle) {
                            backgroundResId = R.drawable.common_dialog_selection_selector_center;
                        } else {
                            backgroundResId = R.drawable.common_dialog_selection_selector_top;
                        }
                    } else if (i == p.menuList.size() - 1) {
                        backgroundResId = R.drawable.common_dialog_selection_selector_bottom;
                    }
                } else if (p.menuList.size() == 1) {
                    backgroundResId = R.drawable.common_dialog_selection_selector_singleton;
                }
                bbm.setBackgroundResource(backgroundResId);
                bbm.setPadding(0, spacing, 0, spacing);
                bbm.setGravity(Gravity.CENTER);
                bbm.setText(bottomMenu.funName);
                bbm.setTextColor(0xFF007AFF);
                bbm.setTextSize(19);
                bbm.setOnClickListener(bottomMenu.listener);
                layContainer.addView(bbm);

                if (i != p.menuList.size() - 1) {
                    View viewDivider = new View(p.context);
                    viewDivider.setLayoutParams(lpDivider);
                    viewDivider.setBackgroundColor(0xFFCED2D6);
                    layContainer.addView(viewDivider);
                }
            }

            if (!TextUtils.isEmpty(p.cancelText)) {
                btnCancel.setText(p.cancelText);
            }

            if (p.cancelListener != null) {
                btnCancel.setOnClickListener(p.cancelListener);
            } else {
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }


            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(canCancel);
            dialog.setCancelable(canCancel);
            return dialog;
        }


    }

    private static class BottomMenu {
        public String funName;
        public View.OnClickListener listener;

        public BottomMenu(String funName, View.OnClickListener listener) {
            this.funName = funName;
            this.listener = listener;
        }
    }
}