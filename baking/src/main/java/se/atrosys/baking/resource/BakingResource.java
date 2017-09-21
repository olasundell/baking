package se.atrosys.baking.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import se.atrosys.baking.model.BakingResult;
import se.atrosys.baking.service.BakingService;

/**
 * TODO write documentation
 */
@RestController
public class BakingResource {
	private final BakingService bakingService;

	@Autowired
	public BakingResource(BakingService bakingService) {
		this.bakingService = bakingService;
	}

	@GetMapping("/bake/{recipe}")
	public BakingResult dryRunBake(@PathVariable String recipe) {
		return bake(recipe);
	}

	@PostMapping("/bake/{recipe}")
	public BakingResult bake(@PathVariable String recipe) {
		return bakingService.bake(recipe);
	}
}
