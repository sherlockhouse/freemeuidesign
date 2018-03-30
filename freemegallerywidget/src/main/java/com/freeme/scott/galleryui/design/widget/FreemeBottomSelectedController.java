package com.freeme.scott.galleryui.design.widget;


public class FreemeBottomSelectedController {

    private FreemeBottomSelectedView mView;

    public FreemeBottomSelectedController(FreemeBottomSelectedView view) {
        mView = view;
    }

    public void showActions(String[] actionNames, int[] actionCodes,
                            FreemeBottomSelectedView.IFreemeBottomActionCallBack callBack) {
        mView.setFreemeBottomActionCallBack(callBack);
        mView.addActions(actionNames, actionCodes);
        mView.showAction(true);
    }

    public void showActions(int[] actionNames, int[] actionCodes,
                            FreemeBottomSelectedView.IFreemeBottomActionCallBack callBack) {
        mView.setFreemeBottomActionCallBack(callBack);
        mView.addActions(actionNames, actionCodes);
        mView.showAction(true);
    }

    public void hideActions() {
        mView.showAction(false);
    }

    public void updateAction(String actionName, int actionCodes) {
        mView.updateActionName(actionName, actionCodes);
    }

    public void updateAction(int actionName, int actionCodes) {
        mView.updateActionName(actionName, actionCodes);
    }

    public void updateActionEnabled(boolean enabled, int actionCode) {
        mView.updateActionEnabled(enabled, actionCode);
    }
}
