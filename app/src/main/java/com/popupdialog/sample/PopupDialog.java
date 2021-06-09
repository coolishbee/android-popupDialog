package com.popupdialog.sample;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public class PopupDialog{

    public static class Builder {
        private Context context;
        private String title, message, positiveBtnText;

        private boolean cancelOnTouchOutside = true;

        private PopupDialogListener okListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setCancelableOnTouchOutside(boolean cancelOnTouchOutside) {
            this.cancelOnTouchOutside = cancelOnTouchOutside;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setPositiveBtnText(String positiveBtnText) {
            this.positiveBtnText = positiveBtnText;
            return this;
        }

        public Builder OnPositiveClicked(PopupDialogListener okListener) {
            this.okListener = okListener;
            return this;
        }

        public void build() {
            final AlertDialog dialog = new AlertDialog.Builder(context).create();

            dialog.setCanceledOnTouchOutside(cancelOnTouchOutside);

            View v = LayoutInflater.from(context).inflate(R.layout.popup_dialog, null);
            dialog.setView(v);

            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            }

            TextView tvTitle = v.findViewById(R.id.pubsdk_alert_dialog_layout_title),
                    tvMessage = v.findViewById(R.id.pubsdk_alert_dialog_layout_message);

            TextView btnPositiveText = v.findViewById(R.id.pubsdk_alert_dialog_layout_confirm_text);
            View btnPositive = v.findViewById(R.id.pubsdk_alert_dialog_layout_confirm);

            if(title != null)
                tvTitle.setText(title);
            else
                tvTitle.setVisibility(View.GONE);

            if(message != null)
                tvMessage.setText(message);
            else
                tvMessage.setVisibility(View.GONE);

            if (positiveBtnText != null) {
                btnPositiveText.setText(positiveBtnText);
            }

            if (okListener != null) {
                btnPositive.setVisibility(View.VISIBLE);
                btnPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        okListener.OnClick();
                        dialog.dismiss();
                    }
                });

            } else {
                btnPositive.setVisibility(View.GONE);
            }

            dialog.show();
        }
    }

}

