package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description(""" 
Transaction info representation
""")
    request {
        method 'GET'
        url '/api/trans/all'
    }

    response {
        status 200
        body([
                amount: regex("[1-9]{1,5}"),
                address: regex("[1-9]{1,5}")
        ])
        headers {
            contentType(applicationJson())
        }
    }
}
