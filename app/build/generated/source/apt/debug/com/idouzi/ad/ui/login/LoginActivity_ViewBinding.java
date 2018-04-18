// Generated code from Butter Knife. Do not modify!
package com.idouzi.ad.ui.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.idouzi.ad.R;
import com.idouzi.adsdk.flowbanksdk.view.BannerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131230769;

  private View view2131230825;

  private View view2131230824;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.mEmailEditText = Utils.findRequiredViewAsType(source, R.id.et_email, "field 'mEmailEditText'", EditText.class);
    target.mPasswordEditText = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'mPasswordEditText'", EditText.class);
    target.bv = Utils.findRequiredViewAsType(source, R.id.bv, "field 'bv'", BannerView.class);
    view = Utils.findRequiredView(source, R.id.btn_server_login, "method 'onServerLoginClick'");
    view2131230769 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onServerLoginClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ib_google_login, "method 'onGoogleLoginClick'");
    view2131230825 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGoogleLoginClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ib_fb_login, "method 'onFbLoginClick'");
    view2131230824 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onFbLoginClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mEmailEditText = null;
    target.mPasswordEditText = null;
    target.bv = null;

    view2131230769.setOnClickListener(null);
    view2131230769 = null;
    view2131230825.setOnClickListener(null);
    view2131230825 = null;
    view2131230824.setOnClickListener(null);
    view2131230824 = null;
  }
}
