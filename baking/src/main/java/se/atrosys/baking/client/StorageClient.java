package se.atrosys.baking.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import se.atrosys.baking.model.StoreRequest;

/**
 * TODO write documentation
 */
@FeignClient("storage")
public interface StorageClient {
	@PutMapping("/store")
	public Long store(StoreRequest storeRequest);
}
