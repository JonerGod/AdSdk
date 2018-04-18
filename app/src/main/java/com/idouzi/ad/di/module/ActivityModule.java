/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.idouzi.ad.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.idouzi.ad.data.network.model.BlogResponse;
import com.idouzi.ad.data.network.model.OpenSourceResponse;
import com.idouzi.ad.di.ActivityContext;
import com.idouzi.ad.di.PerActivity;
import com.idouzi.ad.ui.about.AboutMvpPresenter;
import com.idouzi.ad.ui.about.AboutMvpView;
import com.idouzi.ad.ui.about.AboutPresenter;
import com.idouzi.ad.ui.feed.FeedMvpPresenter;
import com.idouzi.ad.ui.feed.FeedMvpView;
import com.idouzi.ad.ui.feed.FeedPagerAdapter;
import com.idouzi.ad.ui.feed.FeedPresenter;
import com.idouzi.ad.ui.feed.blogs.BlogAdapter;
import com.idouzi.ad.ui.feed.blogs.BlogMvpPresenter;
import com.idouzi.ad.ui.feed.blogs.BlogMvpView;
import com.idouzi.ad.ui.feed.blogs.BlogPresenter;
import com.idouzi.ad.ui.feed.opensource.OpenSourceAdapter;
import com.idouzi.ad.ui.feed.opensource.OpenSourceMvpPresenter;
import com.idouzi.ad.ui.feed.opensource.OpenSourceMvpView;
import com.idouzi.ad.ui.feed.opensource.OpenSourcePresenter;
import com.idouzi.ad.ui.login.LoginMvpPresenter;
import com.idouzi.ad.ui.login.LoginMvpView;
import com.idouzi.ad.ui.login.LoginPresenter;
import com.idouzi.ad.ui.main.MainMvpPresenter;
import com.idouzi.ad.ui.main.MainMvpView;
import com.idouzi.ad.ui.main.MainPresenter;
import com.idouzi.ad.ui.main.rating.RatingDialogMvpPresenter;
import com.idouzi.ad.ui.main.rating.RatingDialogMvpView;
import com.idouzi.ad.ui.main.rating.RatingDialogPresenter;
import com.idouzi.ad.ui.splash.SplashMvpPresenter;
import com.idouzi.ad.ui.splash.SplashMvpView;
import com.idouzi.ad.ui.splash.SplashPresenter;
import com.idouzi.ad.utils.rx.AppSchedulerProvider;
import com.idouzi.ad.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by janisharali on 27/01/17.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    AboutMvpPresenter<AboutMvpView> provideAboutPresenter(
            AboutPresenter<AboutMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    RatingDialogMvpPresenter<RatingDialogMvpView> provideRateUsPresenter(
            RatingDialogPresenter<RatingDialogMvpView> presenter) {
        return presenter;
    }

    @Provides
    FeedMvpPresenter<FeedMvpView> provideFeedPresenter(
            FeedPresenter<FeedMvpView> presenter) {
        return presenter;
    }

    @Provides
    OpenSourceMvpPresenter<OpenSourceMvpView> provideOpenSourcePresenter(
            OpenSourcePresenter<OpenSourceMvpView> presenter) {
        return presenter;
    }

    @Provides
    BlogMvpPresenter<BlogMvpView> provideBlogMvpPresenter(
            BlogPresenter<BlogMvpView> presenter) {
        return presenter;
    }

    @Provides
    FeedPagerAdapter provideFeedPagerAdapter(AppCompatActivity activity) {
        return new FeedPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    OpenSourceAdapter provideOpenSourceAdapter() {
        return new OpenSourceAdapter(new ArrayList<OpenSourceResponse.Repo>());
    }

    @Provides
    BlogAdapter provideBlogAdapter() {
        return new BlogAdapter(new ArrayList<BlogResponse.Blog>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
