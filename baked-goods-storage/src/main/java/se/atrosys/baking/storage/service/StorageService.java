package se.atrosys.baking.storage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import se.atrosys.baking.model.StoreRequest;
import se.atrosys.baking.model.StoredGoodsResponse;
import se.atrosys.baking.storage.client.RecipeClient;
import se.atrosys.baking.storage.model.StoredGoods;
import se.atrosys.baking.storage.repo.StorageRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO write documentation
 */
@Component
public class StorageService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final StorageRepo repo;
	private final RecipeClient recipeClient;

	public StorageService(StorageRepo repo, RecipeClient recipeClient) {
		this.repo = repo;
		this.recipeClient = recipeClient;
	}

	public List<StoredGoodsResponse> readAll() {
		List<StoredGoodsResponse> list = new ArrayList<>();
		for (StoredGoods storedGoods : repo.findAll()) {
			list.add(convert(storedGoods));
		}

		logger.info("Getting all, size {}", list.size());

		return list;
	}

	private StoredGoodsResponse convert(StoredGoods storedGoods) {
		return StoredGoodsResponse.of(recipeClient.getRecipe(storedGoods.getRecipeId()),
			storedGoods.getBakedAt(),
			storedGoods.getId());
	}

	public Long store(StoreRequest request) {
		logger.info("Storing {}", request);
		return repo.save(StoredGoods.builder()
			.recipeId(request.getRecipeId())
			.bakedAt(request.getBakedAt())
			.build()).getId();
	}

	public Optional<StoredGoodsResponse> findById(Long id) {
		return repo.findById(id).map(this::convert);
	}
}
