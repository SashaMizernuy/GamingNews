package com.example.gamingnews.component;

import com.example.gamingnews.NewsViewModal;
import com.example.gamingnews.request.NewsServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mobile on 9.04.2018.
 * Bu sınıf işin beyni. Hangi modüllerin nereye inject edileceğini dagger framework'une anlatıyorsun.
 */

@Component(modules = {NewsServiceModule.class})
@Singleton
public interface AppComponent {
    /*
    Hangi sınıfta @inject anotasyonunun kullanılacağını yazıyorsun.
     */
    void inject(NewsViewModal newsViewModal);
}
