package org.dpppt.switzerland.backend.sdk.config.ws.poeditor;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

public class Messages {

    private MessageSource messageSource;
    private static final Logger logger =
            LoggerFactory.getLogger(
                    org.dpppt.switzerland.backend.sdk.config.ws.poeditor.Messages.class);

    public Messages(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key) {
        return getMessage(key, LocaleContextHolder.getLocale());
    }

    /**
     * returns message for the given message key and locale.
     * throws a {@link NoSuchMessageException} if no such message exists
     *
     * @param messageKey
     * @param locale
     * @return
     */
    public String getMessage(String messageKey, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(messageKey, null, locale);
    }

    /**
     * FOR USE DURING DEVELOPMENT WHEN NOT ALL TRANSLATIONS ARE PRESENT
     * returns message for the given messagekey and locale.
     * fallback language: EN
     * returns null if no such message exists.
     *
     * @param messageKey
     * @param locale
     * @return
     */
    public String getNullableMessage(String messageKey, Locale locale) {
        try {
            return messageSource.getMessage(messageKey, null, locale);
        } catch (NoSuchMessageException e) {
            try {
                return messageSource.getMessage(messageKey, null, Locale.ENGLISH);
            } catch (NoSuchMessageException e2) {
                return null;
            }
        }
    }
}
