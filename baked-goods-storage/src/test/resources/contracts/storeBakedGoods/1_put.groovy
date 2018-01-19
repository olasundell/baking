package storeBakedGoods

import org.springframework.cloud.contract.spec.Contract

/**
 * TODO write documentation 
 */
Contract.make {
	requiredScenarioState:
	request {
		method 'PUT'
		url '/store'
		body("""
    { "recipeId" : 1, "bakedAt" : "2018-01-18T11:16:15.588+01:00[Europe/Stockholm]" }
    """)
		headers {
			header('Content-Type', 'application/json')
		}
	}
	response {
		status 201
	}
}

