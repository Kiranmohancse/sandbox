
package com.apple.sandbox.sandbox.service.manager.impl;

import com.apple.sandbox.sandbox.model.JavaBeans;
import com.apple.sandbox.sandbox.service.manager.ShortnerHandler;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ShortnerHandlerImpl implements ShortnerHandler {
	@Autowired
	private RedisTemplate<String, JavaBeans> genericRedisTemplate;

	@Override
	public String getURL(@NotBlank String key) {
		JavaBeans url = genericRedisTemplate.opsForValue().get(key);
		return url.getUrl();
	}

	@Override
	public JavaBeans vanityURL(@NotBlank String url) {

		// Here key can be genertaed by multiple ways:
		/**
		 * Way 1: 1. Randomly generated an integer and store it in memory/DB 2. Use this
		 * to map each URL and key 3. While generating any new randon number, first
		 * check if there is any existing key with the same number
		 * 
		 * Way 2: 1. Create a strong algorithm that would convert the each character of
		 * the string and give the unquie identifier
		 * 
		 * Way 3: 1. Using the MURMUR algo - it was developed by Google and easy to use
		 * 
		 * Here in this example i am using MURMUR algo as this is widely used by the
		 * developer and why to re-onvent the wheel when there is a solution in hand.
		 * 
		 *
		 * 
		 **/

		String key = Hashing.murmur3_32().hashString(url, Charset.defaultCharset()).toString();

		JavaBeans urlmapping = new JavaBeans();
		urlmapping.setKey(key);
		urlmapping.setCreatedAt(LocalDateTime.now());
		urlmapping.setUrl(url);

		/**
		 * 
		 * Here rather than using the external DB or MS Access to save values, redis
		 * template is being used beacuse of t=its advantages like quick
		 * processing,manipulation and other in-memory saving of data
		 * 
		 * 
		 **/
		genericRedisTemplate.opsForValue().set(key, urlmapping, 36000L, TimeUnit.SECONDS);

		return urlmapping;
	}
}
