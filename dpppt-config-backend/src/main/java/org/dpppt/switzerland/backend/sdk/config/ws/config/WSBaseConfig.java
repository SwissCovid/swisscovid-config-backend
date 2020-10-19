/*
 * Copyright (c) 2020 Ubique Innovation AG <https://www.ubique.ch>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package org.dpppt.switzerland.backend.sdk.config.ws.config;

import io.jsonwebtoken.SignatureAlgorithm;
import java.io.ByteArrayInputStream;
import java.io.Reader;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.dpppt.switzerland.backend.sdk.config.ws.controller.DPPPTConfigController;
import org.dpppt.switzerland.backend.sdk.config.ws.filter.ResponseWrapperFilter;
import org.dpppt.switzerland.backend.sdk.config.ws.interceptor.HeaderInjector;
import org.dpppt.switzerland.backend.sdk.config.ws.model.ConfigResponse;
import org.dpppt.switzerland.backend.sdk.config.ws.model.FaqEntry;
import org.dpppt.switzerland.backend.sdk.config.ws.model.InfoBox;
import org.dpppt.switzerland.backend.sdk.config.ws.model.WhatToDoPositiveTestTexts;
import org.dpppt.switzerland.backend.sdk.config.ws.model.WhatToDoPositiveTestTextsCollection;
import org.dpppt.switzerland.backend.sdk.config.ws.poeditor.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableScheduling
public abstract class WSBaseConfig implements SchedulingConfigurer, WebMvcConfigurer {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	final SignatureAlgorithm algorithm = SignatureAlgorithm.ES256;

	@Value("${ws.headers.protected:}")
	List<String> protectedHeaders;

	@Value("${ws.retentiondays: 21}")
	int retentionDays;
	
	@Value("#{${ws.security.headers: {'X-Content-Type-Options':'nosniff', 'X-Frame-Options':'DENY','X-Xss-Protection':'1; mode=block'}}}")
	Map<String,String> additionalHeaders;


	abstract String getPublicKey();
	abstract String getPrivateKey();

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	@Bean
	public DPPPTConfigController dppptSDKController() {
		return new DPPPTConfigController();
	}

	@Bean
	public ResponseWrapperFilter hashFilter() {
		return new ResponseWrapperFilter(getKeyPair(algorithm), retentionDays, protectedHeaders);
	}

	@Bean
	public HeaderInjector securityHeaderInjector(){
		return new HeaderInjector(additionalHeaders);
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityHeaderInjector());
	}

	public KeyPair getKeyPair(SignatureAlgorithm algorithm) {
		Security.addProvider(new BouncyCastleProvider());
		Security.setProperty("crypto.policy", "unlimited");
		return new KeyPair(loadPublicKeyFromString(),loadPrivateKeyFromString());
	}

	private PrivateKey loadPrivateKeyFromString() {
		try {
			String privateKey = getPrivateKey();
			Reader reader = new StringReader(privateKey);
			PemReader readerPem = new PemReader(reader);
			PemObject obj = readerPem.readPemObject();
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(obj.getContent());
			KeyFactory kf = KeyFactory.getInstance("ECDSA", "BC");
			return (PrivateKey) kf.generatePrivate(pkcs8KeySpec);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException();
		}
	}

	private PublicKey loadPublicKeyFromString() {
		try {
			return CertificateFactory
			.getInstance("X.509")
			.generateCertificate(new ByteArrayInputStream(getPublicKey().getBytes()))
			.getPublicKey();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException();
		}
	}

    @Bean
    public Messages messages(MessageSource messageSource) {
		Messages messages = new Messages(messageSource);
		loadTexts(messages);
		return messages;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    public void loadTexts(Messages messages) {
        ConfigResponse.setWhatToDoPositiveTestTexts(
                new WhatToDoPositiveTestTextsCollection() {
                    {
						setDe(getWhatToDoPositiveTestText(messages, Locale.forLanguageTag("de")));
						setFr(getWhatToDoPositiveTestText(messages, Locale.forLanguageTag("fr")));
						setIt(getWhatToDoPositiveTestText(messages, Locale.forLanguageTag("it")));
						setEn(getWhatToDoPositiveTestText(messages, Locale.forLanguageTag("en")));
						setPt(getWhatToDoPositiveTestText(messages, Locale.forLanguageTag("pt")));
						setEs(getWhatToDoPositiveTestText(messages, Locale.forLanguageTag("es")));
						setSq(getWhatToDoPositiveTestText(messages, Locale.forLanguageTag("sq")));
						setBs(getWhatToDoPositiveTestText(messages, Locale.forLanguageTag("bs")));
						setHr(getWhatToDoPositiveTestText(messages, Locale.forLanguageTag("hr")));
						setSr(getWhatToDoPositiveTestText(messages, Locale.forLanguageTag("sr")));
						setRm(getWhatToDoPositiveTestText(messages, Locale.forLanguageTag("rm")));
						setTr(getWhatToDoPositiveTestText(messages, Locale.forLanguageTag("tr")));
						setTi(getWhatToDoPositiveTestText(messages, Locale.forLanguageTag("ti")));
                    }
                });
    }

    private WhatToDoPositiveTestTexts getWhatToDoPositiveTestText(
            Messages messages, Locale locale) {
        return new WhatToDoPositiveTestTexts() {
            {
                setEnterCovidcodeBoxSupertitle(
                        messages.getNullableMessage("inform_detail_box_subtitle", locale));
                setEnterCovidcodeBoxTitle(
                        messages.getNullableMessage("inform_detail_box_title", locale));
                setEnterCovidcodeBoxText(
                        messages.getNullableMessage("inform_detail_box_text", locale));
                setEnterCovidcodeBoxButtonTitle(
                        messages.getNullableMessage("inform_detail_box_button", locale));

                setInfoBox(
                        new InfoBox() {
                            {
                                setTitle("Important information with more than 100 characters and a very long and confusing title");
                                setMsg("This is an important test information. It has the goal to test if the infoxbox can also handle long text properly. Therefore it conatins a lot of sentences describing nothing but the testcase itself. We could also just repeat the same sentence over and over again, but this would be much less fun to read for the testers..."); // TODO
                                setUrl("https://www.bag.admin.ch");
                                setUrlTitle("Go to BAG website");
                                setIsDismissible(false);
                            }
                        });

                setFaqEntries(
                        Arrays.asList(
                                new FaqEntry() {
                                    {
                                        setTitle(
                                                messages.getNullableMessage(
                                                        "inform_detail_faq1_title", locale));
                                        setText(
                                                messages.getNullableMessage(
                                                        "inform_detail_faq1_text", locale));
                                        setLinkTitle(
                                                messages.getNullableMessage(
                                                        "infoline_coronavirus_number", locale));
                                        setLinkUrl("tel://"+messages.getNullableMessage(
												"infoline_coronavirus_number", locale).replace(" ", ""));
                                        setIconAndroid("ic_verified_user");
                                        setIconIos("ic-verified-user");
                                    }
                                },
                                new FaqEntry() {
                                    {
                                        setTitle(
                                                messages.getNullableMessage(
                                                        "inform_detail_faq2_title", locale));
                                        setText(
                                                messages.getNullableMessage(
                                                        "inform_detail_faq2_text", locale));
                                        setIconAndroid("ic_key");
                                        setIconIos("ic-key");
                                    }
                                },
								new FaqEntry() {
									{
										setTitle(
												messages.getNullableMessage(
														"inform_detail_faq3_title", locale));
										setText(
												messages.getNullableMessage(
														"inform_detail_faq3_text", locale));
										setIconAndroid("ic_person");
										setIconIos("ic-user");
									}
								},
                                new FaqEntry() {
                                    {
                                        setTitle("What is the new question?");
                                        setText("This is the answer to the new question");
                                        setLinkTitle("More information on Github");
                                        setLinkUrl("https://github.com/DP-3T");
                                        setIconAndroid("ic_new_icon");
                                        setIconIos("ic-new-icon");
                                    }
                                }));
            }
        };
    }
}
