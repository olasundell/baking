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
		[]
""")
	}
}
