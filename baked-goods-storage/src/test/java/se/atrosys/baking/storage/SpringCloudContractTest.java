package se.atrosys.baking.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import se.atrosys.baking.model.Recipe;
import se.atrosys.baking.storage.client.RecipeClient;
import se.atrosys.baking.storage.config.JacksonConfig;
import se.atrosys.baking.storage.model.StoredGoods;
import se.atrosys.baking.storage.repo.StorageRepo;
import se.atrosys.baking.storage.resource.StorageResource;
import se.atrosys.baking.storage.service.StorageService;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * TODO write documentation
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringCloudContractTest.Config.class, JacksonConfig.class})
public abstract class SpringCloudContractTest {
	@Autowired
    private WebApplicationContext context;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	final static AtomicLong idCounter = new AtomicLong();
	final static ConcurrentHashMap<Long, StoredGoods> storedGoods = new ConcurrentHashMap<>();
	@Autowired
	StorageResource storageResource;

	@Autowired
	ObjectMapper objectMapper;

	@Before
	public void setup() {
		RestAssuredMockMvc.webAppContextSetup(context);
	}

	@Configuration
	@EnableAutoConfiguration
	static class Config {
		private final Logger logger = LoggerFactory.getLogger(this.getClass());
		private final StorageRepo repo;
		private final StorageService storageService;

		public Config() {
			repo = mockRepo();
			storageService = new StorageService(repo, new RecipeClient() {
				@Override
				public Recipe getRecipe(Integer id) {
					return Recipe.builder().name("" + id).id(id).build();
				}
			});
		}

		private StorageRepo mockRepo() {
			StorageRepo repo;
			logger.info("Mocking repo");
			repo = mock(StorageRepo.class);

			when(repo.save(any(StoredGoods.class))).then(invocation -> {
				StoredGoods goods = invocation.getArgument(0);
				goods.setId(idCounter.getAndIncrement());
				storedGoods.put(goods.getId(), goods);

				logger.warn("Storing {}", goods);

				return goods;
			});

			when(repo.findById(anyLong())).then(invocation -> {
				final Long argument = invocation.getArgument(0);
				logger.warn("Getting {}", argument);
				return Optional.ofNullable(storedGoods.get(argument));
			});

			when(repo.findAll()).thenReturn(storedGoods.values());
			return repo;
		}

		@Bean
		public StorageResource storageResource() {
			logger.info("Creating new storage resource");
			return new StorageResource(storageService);
		}
	}
}
