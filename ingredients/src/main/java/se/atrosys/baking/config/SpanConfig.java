package se.atrosys.baking.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanTextMap;
import org.springframework.cloud.sleuth.instrument.web.HttpSpanExtractor;
import org.springframework.cloud.sleuth.util.TextMapUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Map;
import java.util.Random;

/**
 * TODO write documentation
 */
//@Configuration
public class SpanConfig {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final Random random;

	public SpanConfig() {
		random = new Random(Instant.now().getEpochSecond());
	}

	@Bean
	public HttpSpanExtractor httpSpanExtractor() {
		return carrier -> {
			Map<String, String> map = TextMapUtil.asMap(carrier);
			Span.SpanBuilder builder = Span.builder();

			if (map.containsKey("X-B3-TraceId")) {
				builder.traceId(Span.hexToId(map.get("X-B3-TraceId")));
				builder.spanId(random.nextLong());
			} else if (map.containsKey("x-haproxy-unique-id")) {
				final String string = map.get("x-haproxy-unique-id");

				builder.baggage("haproxy-unique-id", string);

				final long hash = hash(string);

				builder.traceId(hash);
				builder.spanId(hash);
			} else {
				final long traceId = random.nextLong();
				builder.traceId(traceId);
				builder.spanId(traceId);
			}

			return builder.name("ingredientsdamnit").build();
		};
	}

	private long hash(String string) {
		long h = Instant.now().toEpochMilli(); // prime

		int len = string.length();

		for (int i = 0; i < len; i++) {
			h = 31 * h + string.charAt(i);
		}

		return h;
	}
}
