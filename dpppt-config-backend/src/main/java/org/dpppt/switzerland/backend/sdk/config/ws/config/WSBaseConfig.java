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
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.dpppt.switzerland.backend.sdk.config.ws.controller.GaenConfigController;
import org.dpppt.switzerland.backend.sdk.config.ws.filter.ResponseWrapperFilter;
import org.dpppt.switzerland.backend.sdk.config.ws.interceptor.HeaderInjector;
import org.dpppt.switzerland.backend.sdk.config.ws.poeditor.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
@EnableScheduling
public abstract class WSBaseConfig implements SchedulingConfigurer, WebMvcConfigurer {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	final SignatureAlgorithm algorithm = SignatureAlgorithm.ES256;

	@Value("${ws.interops.countrycodes:}")
	List<String> interOpsCountryCodes;

	@Value("${ws.headers.protected:}")
	List<String> protectedHeaders;

	@Value("${ws.retentiondays: 21}")
	int retentionDays;
	@Value("${ws.checkinupdatenotificationenabled: true}")
	boolean checkInUpdateNotificationEnabled;
	
	@Value("#{${ws.security.headers: {'X-Content-Type-Options':'nosniff', 'X-Frame-Options':'DENY','X-Xss-Protection':'1; mode=block'}}}")
	Map<String,String> additionalHeaders;


	abstract String getPublicKey();
	abstract String getPrivateKey();

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/ios_agency_image")
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS));
	}

	@Bean
	public GaenConfigController gaenConfigController(Messages messages) {
		return new GaenConfigController(messages,  interOpsCountryCodes, checkInUpdateNotificationEnabled);
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
		return messages;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:i18n/messages", "classpath:i18n/messages_v1_legacy");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setDefaultLocale(null);
        return messageSource;
    }

}
