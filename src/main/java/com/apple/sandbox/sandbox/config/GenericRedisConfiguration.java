package com.apple.sandbox.sandbox.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.apple.sandbox.sandbox.model.JavaBeans;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class GenericRedisConfiguration {

	@Autowired
	RedisConnectionFactory connectionFactory;

	@Autowired
	ObjectMapper objectMapper;
	
	
	//Generic bean class mapping to confogure Redis template
	@Bean
	RedisTemplate<String, JavaBeans> redisTemplate() {
		final RedisTemplate<String, JavaBeans> redisTemplate = new RedisTemplate<>();
		Jackson2JsonRedisSerializer valueSerializer = new Jackson2JsonRedisSerializer(JavaBeans.class);
		valueSerializer.setObjectMapper(objectMapper);
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(valueSerializer);
		return redisTemplate;
	}
}
