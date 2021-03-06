package com.wq.freeze.mvpwithmosby.view;


import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpDelegate;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpViewStateDelegateCallback;
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpViewStateDelegateImpl;
import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

/**
 * This is a enhancement of {@link com.hannesdorfmann.mosby.mvp.MvpActivity} that introduces the
 * support of {@link RestorableViewState}.
 * <p>
 * You can change the behaviour of what to do if the viewstate is empty (usually if the activity
 * creates the viewState for the very first time and therefore has no state / data to restore) by
 * overriding {@link #onNewViewStateInstance()}
 * </p>
 *
 * @author Hannes Dorfmann
 * @since 1.0.0
 *
 * to fix getViewState cast by freeze
 */
public abstract class MvpViewStateActivity<V extends MvpView, P extends MvpPresenter<V>, S extends ViewState<V>>
        extends MvpActivity<V, P> implements ActivityMvpViewStateDelegateCallback<V, P> {

    protected S viewState;

    /**
     * A simple flag that indicates if the restoring ViewState  is in progress right now.
     */
    protected boolean restoringViewState = false;

    @Override protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ActivityMvpViewStateDelegateImpl<>(this);
        }

        return mvpDelegate;
    }

    @Override public ViewState<V> getViewState() {
        return viewState;
    }

    @Override public void setViewState(ViewState<V> viewState) {
        this.viewState = (S) viewState;
    }

    @Override public void setRestoringViewState(boolean restoringViewState) {
        this.restoringViewState = restoringViewState;
    }

    @Override public boolean isRestoringViewState() {
        return restoringViewState;
    }

    @Override public void onViewStateInstanceRestored(boolean instanceStateRetained) {
        // not needed. You could override this is subclasses if needed
    }

    /**
     * Creates the ViewState instance
     */
    public abstract ViewState<V> createViewState();
}