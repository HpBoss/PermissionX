package com.permissionx.guolindev.request;

import androidx.fragment.app.FragmentActivity;

import com.permissionx.guolindev.PermissionMediator;

public interface PermissionCallback {
    void onPermissionChecked(PermissionMediator mediator, FragmentActivity activity);
}