package storeBakedGoods

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	request {
		method 'GET'
		url '/store'
		headers {
			header('Content-Type', 'application/json')
		}
	}
	response {
		status 200
		body("""
		[
			{
				"id": 0,
				"recipe": {
					"id":1,
					"ingredients":{},
					"name":"1"
				},
				"bakedAt" : "2018-01-18T11:16:15.588+01:00[Europe/Stockholm]"
			}
		]
""")
	}
}
