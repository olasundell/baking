package se.atrosys.baking.storage.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.baking.model.StoreRequest;
import se.atrosys.baking.model.StoredGoodsResponse;
import se.atrosys.baking.storage.model.StoredGoods;
import se.atrosys.baking.storage.service.StorageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO write documentation
 */
@RestController
@CrossOrigin
public class StorageResource {
	private final StorageService service;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public StorageResource(StorageService service) {
		this.service = service;
	}

	@PutMapping("/store")
	@ResponseStatus(HttpStatus.CREATED)
	public Long store(@RequestBody StoreRequest request) {
		return service.store(request);
	}

	@GetMapping("/store")
	public List<StoredGoodsResponse> readAll() {
		return service.readAll();
	}

	@GetMapping("/store/{id}")
	public Optional<StoredGoodsResponse> readSpecific(@PathVariable Long id) {
		return service.findById(id);
	}
}
