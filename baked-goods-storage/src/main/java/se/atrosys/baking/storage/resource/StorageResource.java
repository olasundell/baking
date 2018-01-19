package se.atrosys.baking.storage.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.baking.model.StoreRequest;
import se.atrosys.baking.storage.model.StoredGoods;
import se.atrosys.baking.storage.repo.StorageRepo;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO write documentation
 */
@RestController
public class StorageResource {
	private final StorageRepo repo;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public StorageResource(StorageRepo repo) {
		this.repo = repo;
	}

	@PutMapping("/store")
	@ResponseStatus(HttpStatus.CREATED)
	public Long store(@RequestBody StoreRequest request) {
		logger.info("Storing {}", request);
		return repo.save(StoredGoods.builder()
			.recipeId(request.getRecipeId())
			.bakedAt(request.getBakedAt())
			.build()).getId();
	}

	@GetMapping("/store")
	public List<StoredGoods> read() {
		List<StoredGoods> list = new ArrayList<>();
		repo.findAll().forEach(list::add);

		logger.info("Getting all, size {}", list.size());

		return list;
	}

	@GetMapping("/store/{id}")
	public Optional<StoredGoods> readSpecific(@PathVariable Long id) {
		return repo.findById(id);
	}
}
