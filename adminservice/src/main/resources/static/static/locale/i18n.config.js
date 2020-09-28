import Vue from 'vue'
import VueI18n from 'vue-i18n'
import axios from 'axios'

import messages from './en'
import {dateTimeFormats} from "./en"

Vue.use(VueI18n);

export const i18n = new VueI18n({
    locale: 'en', //  set locale
    fallbackLocale: 'en',
    silentFallbackWarn: true, //  To suppress the duplicate warnings if the default locale falling back to fallbackLocale
    dateTimeFormats,
    messages //  set locale messages
});

const loadedLanguages = ['en']; // our default language that is preloaded

function setI18nLanguage(lang) {
    i18n.locale = lang;
    axios.defaults.headers.common['Accept-Language'] = lang;
    document.querySelector('html').setAttribute('lang', lang);
    return lang
}

export function loadLanguageAsync(lang) {
    console.log("The i18n locale is: " + i18n.locale + " and the lang set to now is: " + lang);
    if ('' !== lang) {
        if (!loadedLanguages.includes(lang)) {
            console.log("The lang " + lang + " is not included in loadedLanguages");
            return import(/* webpackChunkName: "lang-[request]" */ `./${lang}`).then(msgs => {
                i18n.setLocaleMessage(lang, msgs.default);
                i18n.setDateTimeFormat(lang, msgs.dateTimeFormats);

                loadedLanguages.push(lang);
                return setI18nLanguage(lang)
            })
        }
        return Promise.resolve(setI18nLanguage(lang))
    }
    return Promise.resolve(lang)
}

export default i18n
